package com.desafio_sicredi.adapters.out.persistence.creditContract;

import jakarta.persistence.*;
import com.desafio_sicredi.domain.model.SegmentType;
import com.desafio_sicredi.domain.model.CreditContract;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "credit_contract")
public class CreditContractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOperacaoCredito;
    @Column(name = "id_associado")
    private Long idAssociado;
    @Column(name = "valor_operacao")
    private BigDecimal valorOperacao;
    @Column(name = "segmento")
    @Enumerated(EnumType.STRING)
    private SegmentType segmento;
    @Column(name = "codigo_produto_credito")
    private String codigoProdutoCredito;
    @Column(name = "codigo_conta")
    private String codigoConta;
    @Column(name = "area_beneficiada_ha")
    private BigDecimal areaBeneficiadaHa;
    @Column(name = "data_contratacao")
    private LocalDateTime dataContratacao;

    protected CreditContractEntity() {
    }

    public CreditContractEntity(CreditContract contract) {

        this.idAssociado = contract.getIdAssociado();
        this.valorOperacao = contract.getValorOperacao();
        this.segmento = contract.getSegmento();
        this.codigoProdutoCredito = contract.getCodigoProdutoCredito();
        this.codigoConta = contract.getCodigoConta();
        this.areaBeneficiadaHa = contract.getAreaBeneficiadaHa();
        this.dataContratacao = contract.getDataContratacao();

    }

    public CreditContract toDomain(){

        return new CreditContract(
                idOperacaoCredito,
                idAssociado,
                valorOperacao,
                segmento,
                codigoProdutoCredito,
                codigoConta,
                areaBeneficiadaHa,
                dataContratacao
        );
    }

}