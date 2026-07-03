package com.desafio_sicredi.domain.ports.out;

import com.desafio_sicredi.domain.model.CreditOperationAssociation;

public interface CreditOperationAssociationRepository {

    void save(CreditOperationAssociation operationAssociation);
}
