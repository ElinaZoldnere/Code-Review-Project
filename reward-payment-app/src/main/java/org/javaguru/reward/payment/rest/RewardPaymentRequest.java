package org.javaguru.reward.payment.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RewardPaymentRequest {

    private Long employeeId;
    private Double amount;

}
