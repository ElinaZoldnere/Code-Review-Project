package org.javaguru.reward.calculation.service.repositories;

import org.javaguru.reward.calculation.service.domain.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TariffRepository extends JpaRepository<Tariff, Long> {

    Optional<Tariff> findByJobType(String jobType);

}
