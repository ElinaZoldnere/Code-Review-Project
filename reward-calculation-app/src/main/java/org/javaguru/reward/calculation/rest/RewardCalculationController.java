package org.javaguru.reward.calculation.rest;

import org.javaguru.reward.calculation.service.RewardCalculationService;
import org.javaguru.reward.calculation.service.domain.Employee;
import org.javaguru.reward.calculation.service.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reward/calculation")
public class RewardCalculationController {

    @Autowired private EmployeeRepository employeeRepository;
    @Autowired private RewardCalculationService rewardCalculationService;

    @PostMapping(path = "/",
            produces = "application/json")
    public RewardCalculationResponse calculateRewards() {
        List<Employee> employees = employeeRepository.findAll();
        rewardCalculationService.calculateRewards(employees);
        return new RewardCalculationResponse();
    }

}
