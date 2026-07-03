package com.desafio_sicredi.adapters.in.web.controller.response;

import com.desafio_sicredi.domain.model.SegmentType;
import com.desafio_sicredi.domain.model.CreditContract;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreditContractResponse(

        Long idOperacaoCredito,
        Long idAssociado,
        BigDecimal valorOperacao,
        SegmentType segmento,
        String codigoProdutoCredito,
        String codigoConta,
        BigDecimal areaBeneficiadaHa,
        LocalDateTime dataContratacao
) {
    public static CreditContractResponse from(CreditContract contract) {

        return new CreditContractResponse(
                contract.getIdOperacaoCredito(),
                contract.getIdAssociado(),
                contract.getValorOperacao(),
                contract.getSegmento(),
                contract.getCodigoProdutoCredito(),
                contract.getCodigoConta(),
                contract.getAreaBeneficiadaHa(),
                contract.getDataContratacao()
        );
    }
}
