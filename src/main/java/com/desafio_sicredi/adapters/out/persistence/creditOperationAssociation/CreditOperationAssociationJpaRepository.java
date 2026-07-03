package com.desafio_sicredi.adapters.out.persistence.creditOperationAssociation;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CreditOperationAssociationJpaRepository extends JpaRepository<CreditOperationAssociationEntity, Long> {
}
