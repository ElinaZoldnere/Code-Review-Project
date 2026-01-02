package org.javaguru.reward.calculation.service;

import org.javaguru.reward.calculation.service.domain.Employee;
import org.javaguru.reward.calculation.service.domain.Reward;
import org.javaguru.reward.calculation.service.domain.Tariff;
import org.javaguru.reward.calculation.service.repositories.RewardRepository;
import org.javaguru.reward.calculation.service.repositories.TariffRepository;
import org.javaguru.reward.calculation.service.restclient.RewardPaymentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Сервис тарификации вознаграждений сотрудникам за дополнительную работу.
 * Каждый сотрудник может выполнять что-либо помимо основной работы - проводить лекции, выступать на конференциях и т.д.
 * Такие действия оплачиваются согласно тарифам, с учетом заслуг сотрудника (личного бонусного коэффициента).
 * Оплата проходит через внешний сервис, вызываемый по REST.
 */

@Service
public class RewardCalculationService {

    @Autowired private RewardRepository rewardRepository;
    @Autowired private TariffRepository tariffRepository;
    @Autowired private RewardPaymentClient rewardPaymentClient;

    @Transactional
    public void calculateRewards(List<Employee> employees) {
        for (Employee employee : employees) {
            List<Reward> rewards = rewardRepository.findByEmployeeId(employee.getId());
            for (Reward reward : rewards) {
                if (List.of("SPEECH", "LESSON", "HELP").contains(reward.getJobType())) {
                    Tariff tariff = tariffRepository.findByJobType(reward.getJobType()).get();
                    double amount = (1 + employee.getBonusCoefficient()) * tariff.getAmount();
                    rewardPaymentClient.payReward(employee.getId(), amount);
                    System.out.println("Отправлен платеж");
                    reward.setStatus("PAID");
                    rewardRepository.save(reward);
                }
            }
        }
    }

}
