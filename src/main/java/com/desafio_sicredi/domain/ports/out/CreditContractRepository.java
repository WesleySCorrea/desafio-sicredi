package com.desafio_sicredi.domain.ports.out;

import com.desafio_sicredi.domain.model.CreditContract;

import java.util.Optional;

public interface CreditContractRepository {
    CreditContract save(CreditContract contract);

    Optional<CreditContract> findCreditById(Long id);
}
