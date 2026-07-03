package com.desafio_sicredi.adapters.out.persistence.creditOperationAssociation;

import com.desafio_sicredi.domain.model.CreditOperationAssociation;
import jakarta.persistence.*;

@Entity
@Table(name = "credit_operation_association")
public class CreditOperationAssociationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCreditOperationAssociation;

    @Column(name = "id_operacao_credito")
    private Long idOperacaoCredito;

    @Column(name = "id_associado")
    private Long idAssociado;

    protected CreditOperationAssociationEntity() {
    }

    public CreditOperationAssociationEntity(CreditOperationAssociation operationAssociation) {

        this.idOperacaoCredito = operationAssociation.getIdOperacaoCredito();
        this.idAssociado = operationAssociation.getIdAssociado();
    }
}
