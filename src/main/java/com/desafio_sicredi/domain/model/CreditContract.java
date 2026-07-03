package com.desafio_sicredi.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreditContract {

    private Long idOperacaoCredito;
    private Long idAssociado;
    private BigDecimal valorOperacao;
    private SegmentType segmento;
    private String codigoProdutoCredito;
    private String codigoConta;
    private BigDecimal areaBeneficiadaHa;
    private LocalDateTime dataContratacao;

    public CreditContract(Long idAssociado, BigDecimal valorOperacao, SegmentType segmento, String codigoProdutoCredito, String codigoConta, BigDecimal areaBeneficiadaHa) {
        this.idAssociado = idAssociado;
        this.valorOperacao = valorOperacao;
        this.segmento = segmento;
        this.codigoProdutoCredito = codigoProdutoCredito;
        this.codigoConta = codigoConta;
        this.areaBeneficiadaHa = areaBeneficiadaHa;
        this.dataContratacao = LocalDateTime.now();
    }

    public CreditContract(Long idOperacaoCredito, Long idAssociado, BigDecimal valorOperacao, SegmentType segmento, String codigoProdutoCredito, String codigoConta, BigDecimal areaBeneficiadaHa, LocalDateTime dataContratacao) {
        this.idOperacaoCredito = idOperacaoCredito;
        this.idAssociado = idAssociado;
        this.valorOperacao = valorOperacao;
        this.segmento = segmento;
        this.codigoProdutoCredito = codigoProdutoCredito;
        this.codigoConta = codigoConta;
        this.areaBeneficiadaHa = areaBeneficiadaHa;
        this.dataContratacao = dataContratacao;
    }

    public Long getIdOperacaoCredito() {
        return idOperacaoCredito;
    }

    public Long getIdAssociado() {
        return idAssociado;
    }

    public BigDecimal getValorOperacao() {
        return valorOperacao;
    }

    public SegmentType getSegmento() {
        return segmento;
    }

    public String getCodigoProdutoCredito() {
        return codigoProdutoCredito;
    }

    public String getCodigoConta() {
        return codigoConta;
    }

    public BigDecimal getAreaBeneficiadaHa() {
        return areaBeneficiadaHa;
    }

    public LocalDateTime getDataContratacao() {
        return dataContratacao;
    }
}
