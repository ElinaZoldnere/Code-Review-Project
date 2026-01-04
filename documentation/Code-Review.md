# Code Review
Here I inspect the code in its initial state (`inital commit`) to identify issues. It is hinted the 
code has lot of them.
## Reward Calculation App
### RewardCalculationService (referencing also domain and repositories)
**Business and Performance**

Probably the main issue here is that all the list of employees is processed as a single 
transaction, including external service call. That can lead to a situation, if processing of some 
`Employee` fails in the middle on the list, the whole transaction is rolled back with part of 
payments actually sent for processing, what is a major financial and data integrity risk. It 
probably would be less dangerous, if the payment service is idempotent, what would need a unique 
transaction identifier and according implementation, but no such identifier is provided from the 
client side. Also including external service call in the scope of a transaction prolongs database 
blocking for external service waiting time, that is not efficient.

The whole flow should be redesigned, limiting transaction scope, probably to calculation for one 
`Reward` with registering outcome in database. Do the external call outside transaction 
(synchronously or probably asynchronously), persist the payment `status` in separate transaction. 
Also, unique identifiers for payments should be introduced.
It is worth to introduce some "FAILED" `status` along existing and a field to store payment 
reference or payment error.
- Database lookups currently expect per `Employee` `Reward` lookup and per `Reward` `Tariff` lookup 
(inner loop). If every `Employee` had many rewards, this can scale up quite quickly and wouldn't be 
really justified if rewards may share the same `Tariff` type. 
The simplest solution would be to extract all job types per each employee's rewards and perform one 
lookup for their tariffs. Also, a higher (employee list) level db call optimization could be 
considered or rejected after more detailed investigation.
- Depending on business rules, rewards for the same employee could potentially be aggregated into a 
single payment per employee, reducing payment count. That would require domain confirmation for the 
proposal and partial error handling.
- Although `Reward` has `status` is it not used in lookups to filter out already paid rewards. 
Rewards should be filtered, based on their status before processing further.
- `double` is not appropriate data type for financial calculations as it does not guarantee 
precision. `BigDecimal` should be used instead.
- `System.out.println("Отправлен платеж")` message should include context of the action (e.g. 
person identifier, amount, unique identifier etc.) to provide information for debugging failures or 
other issues. Also, it should be outputted by a logger service to make it redirectable to some 
observability service or other output. Console logs lacks this possibility.
- `findByJobType(...).get()` can throw if tariff is missing. Optional should be handled explicitly 
including case of failure.

**Style and Design**
- Job types and reward status ar plain strings ("SPEECH", "PAID" etc.). Using predefined values 
like enums would be more mistake proof. Also, creating a new `List.of()` in each iteration is not 
optimal.
- Constructor injection in private final fields (e.g. with `@RequiredArgsConstructor`) would be 
preferred over `@Autowired`, as it ensures the class would never be instantiated without the 
dependency and provides immutability, making dependencies more explicit.
- Way too much going on in the main method `calculateRewards()` - calculations, database retrievals,
external rest calling, database writings. The concerns should be separated to make the code 
modular and maintainable in future refactorings.
- It can be considered whether the service class could be an interface with a public method and
all other implementations hidden as package-private or private methods. Anyway, the method should 
be split to separate concerns, limiting access control whenever possible to avoid misuse of 
implementation details.
