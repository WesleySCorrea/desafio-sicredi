package com.desafio_sicredi.adapters.out.creditvalidation;

import com.desafio_sicredi.domain.model.SegmentType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.desafio_sicredi.domain.ports.out.CreditValidationPort;
import com.desafio_sicredi.adapters.out.creditvalidation.response.ValidationResponse;

import java.math.BigDecimal;

@FeignClient(
        name = "credit-validation",
        url = "${credit.validation.url}"
)
public interface CreditValidationClient extends CreditValidationPort {

    @GetMapping("/produtos-credito/{codigoProdutoCredito}/permite-contratacao")
    ValidationResponse validate(
            @PathVariable String codigoProdutoCredito,
            @RequestParam SegmentType segmento,
            @RequestParam BigDecimal valorFinanciado
    );
}
