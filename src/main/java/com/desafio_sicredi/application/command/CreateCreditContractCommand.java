package com.desafio_sicredi.application.command;

import com.desafio_sicredi.domain.model.SegmentType;
import com.desafio_sicredi.adapters.in.web.controller.request.CreateCreditContractRequest;

import java.math.BigDecimal;

public record CreateCreditContractCommand(
        Long idAssociado,
        BigDecimal valorOperacao,
        SegmentType segmento,
        String codigoProdutoCredito,
        String codigoConta,
        BigDecimal areaBeneficiadaHa
) {}
