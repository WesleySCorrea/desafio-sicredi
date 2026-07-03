package com.desafio_sicredi.adapters.out.persistence.creditOperationAssociation;

import org.springframework.stereotype.Repository;
import com.desafio_sicredi.domain.model.CreditOperationAssociation;
import com.desafio_sicredi.domain.ports.out.CreditOperationAssociationRepository;

@Repository
public class CreditOperationAssociationJpaAdapter implements CreditOperationAssociationRepository {

    private final CreditOperationAssociationJpaRepository jpaRepository;

    public CreditOperationAssociationJpaAdapter(CreditOperationAssociationJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(CreditOperationAssociation operationAssociation) {

        CreditOperationAssociationEntity entity =
                new CreditOperationAssociationEntity(
                        operationAssociation
                );

        jpaRepository.save(entity);
    }
}