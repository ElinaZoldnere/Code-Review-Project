package org.javaguru.reward.calculation.service.repositories;

import org.javaguru.reward.calculation.service.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {



}
