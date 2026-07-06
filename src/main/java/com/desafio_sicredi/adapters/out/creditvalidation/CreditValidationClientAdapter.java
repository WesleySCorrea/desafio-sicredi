package com.desafio_sicredi.adapters.out.creditvalidation;

import org.springframework.stereotype.Component;
import com.desafio_sicredi.domain.model.SegmentType;
import com.desafio_sicredi.domain.ports.out.CreditValidationPort;
import com.desafio_sicredi.adapters.out.creditvalidation.response.ValidationResponse;

import java.math.BigDecimal;

@Component
public class CreditValidationClientAdapter implements CreditValidationPort {

    private final CreditValidationClient client;

    public CreditValidationClientAdapter(CreditValidationClient client) {
        this.client = client;
    }

    @Override
    public ValidationResponse validate(String codigoProdutoCredito, SegmentType segmento, BigDecimal valorFinanciado) {

        return client.validate(codigoProdutoCredito, segmento, valorFinanciado);
    }
}
