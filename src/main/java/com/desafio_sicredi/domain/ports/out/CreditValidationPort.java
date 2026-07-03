package com.desafio_sicredi.domain.ports.out;

import com.desafio_sicredi.domain.model.SegmentType;
import com.desafio_sicredi.adapters.out.creditvalidation.response.ValidationResponse;

import java.math.BigDecimal;

public interface CreditValidationPort {

    ValidationResponse validate(
            String codigoProdutoCredito,
            SegmentType segmento,
            BigDecimal valorOperacao
    );
}
