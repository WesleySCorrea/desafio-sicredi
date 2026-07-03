package com.desafio_sicredi.domain.model;

public class CreditOperationAssociation {

    private Long idCreditOperationAssociation;
    private Long idOperacaoCredito;
    private Long idAssociado;

    public CreditOperationAssociation(Long idOperacaoCredito, Long idAssociado) {
        this.idOperacaoCredito = idOperacaoCredito;
        this.idAssociado = idAssociado;
    }

    public Long getIdOperacaoCredito() {
        return idOperacaoCredito;
    }

    public Long getIdAssociado() {
        return idAssociado;
    }
}
