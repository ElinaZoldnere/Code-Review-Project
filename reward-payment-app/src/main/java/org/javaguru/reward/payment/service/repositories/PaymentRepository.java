package org.javaguru.reward.payment.service.repositories;

import org.javaguru.reward.payment.service.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository  extends JpaRepository<Payment, Long> {


}
