package com.desafio_sicredi.adapters.out.persistence.creditContract;

import org.springframework.stereotype.Repository;
import com.desafio_sicredi.domain.model.CreditContract;
import com.desafio_sicredi.domain.ports.out.CreditContractRepository;

import java.util.Optional;

@Repository
public class CreditContractJpaAdapter implements CreditContractRepository {

    private final CreditContractJpaRepository jpaRepository;

    public CreditContractJpaAdapter(CreditContractJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public CreditContract save(CreditContract contract) {

        CreditContractEntity entity =
                new CreditContractEntity(
                        contract
                );

        CreditContractEntity saved =
                jpaRepository.save(entity);

        return saved.toDomain();
    }

    @Override
    public Optional<CreditContract> findCreditById(Long id) {

        return jpaRepository.findById(id)
                .map(CreditContractEntity::toDomain);
    }
}