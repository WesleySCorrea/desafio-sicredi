package com.desafio_sicredi.adapters.in.web.controller.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank;
import com.desafio_sicredi.domain.model.SegmentType;

import java.math.BigDecimal;

public record CreateCreditContractRequest(
        @NotNull(message = "idAssociado é obrigatório")
        Long idAssociado,

        @NotNull(message = "valorOperacao é obrigatório")
        @Positive(message = "valorOperacao deve ser maior que zero")
        BigDecimal valorOperacao,

        @NotNull(message = "segmento é obrigatório")
        SegmentType segmento,

        @NotBlank(message = "codigoProdutoCredito é obrigatório")
        String codigoProdutoCredito,

        @NotBlank(message = "codigoConta é obrigatório")
        @Pattern(regexp = "^\\d{10}$", message = "codigoConta deve ter 10 digitos")
        String codigoConta,

        BigDecimal areaBeneficiadaHa
) {
}
