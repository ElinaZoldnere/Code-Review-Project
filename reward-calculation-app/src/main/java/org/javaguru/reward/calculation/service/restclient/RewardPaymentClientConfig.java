package org.javaguru.reward.calculation.service.restclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RewardPaymentClientConfig {

    @Value( "${reward.payment.api.url}" )
    private String rewardPaymentApiUrl;

    @Value( "${reward.payment.api.port}" )
    private int rewardPaymentApiPort;

    @Bean
    public RestClient rewardPaymentRestClient() {
        // Combine URL and port to create the full base URL
        String baseUrl = String.format("%s:%d", rewardPaymentApiUrl, rewardPaymentApiPort);

        // Create and configure the RestClient
        return RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

}
