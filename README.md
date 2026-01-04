# Code Review Project  
This repository contains a project in which a simplified distributed backend system is gradually 
evolved by identifying real-world problems and exploring practical solutions.

## About the Project
The project starts from existing baseline of two simplified microservices deployed in Docker 
containers. The initial implementation intentionally contains a number of design and operational 
issues that might be encountered in early-stage or oversimplified distributed systems.

The first step of the project is detailed code review of its initial state, identifying problems
related to transaction boundaries, external side effects, data integrity, scalability, and
observability. The intention is to understand and document the risks before making changes.
(see [Code Review](documentation/Code-Review.md)).

From there, the system is improved step by step, addressing the identified issues and building a 
backend system that can run reliably in a distributed environment, including multiple service 
instances, concurrent requests, and partial failures.
