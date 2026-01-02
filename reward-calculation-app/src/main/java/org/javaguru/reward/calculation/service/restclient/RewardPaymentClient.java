package org.javaguru.reward.calculation.service.restclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class RewardPaymentClient {

    private final RestClient rewardPaymentRestClient;

    public RewardPaymentClient(RestClient rewardPaymentRestClient) {
        this.rewardPaymentRestClient = rewardPaymentRestClient;
    }

    public void payReward(Long employeeId, Double amount) {

        RewardPaymentRequest request = new RewardPaymentRequest(employeeId, amount);

        rewardPaymentRestClient.post()
                .uri("/reward/payment/")
                .body(request)
                .retrieve()
                .body(RewardPaymentResponse.class);

    }

}
