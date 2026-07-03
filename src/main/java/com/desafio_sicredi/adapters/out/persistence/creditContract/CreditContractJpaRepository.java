package com.desafio_sicredi.adapters.out.persistence.creditContract;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CreditContractJpaRepository extends JpaRepository<CreditContractEntity, Long> {
}
