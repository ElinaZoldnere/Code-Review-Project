package org.javaguru.reward.payment.rest;

import org.javaguru.reward.payment.service.RewardPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reward/payment")
public class RewardPaymentController {

    @Autowired private RewardPaymentService rewardPaymentService;

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public RewardPaymentResponse payReward(@RequestBody RewardPaymentRequest request) {
        rewardPaymentService.pay(request.getEmployeeId(), request.getAmount());
        return new RewardPaymentResponse();
    }

}
