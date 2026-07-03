CREATE TABLE credit_contract (
    id_operacao_credito BIGSERIAL PRIMARY KEY,
    id_associado BIGINT NOT NULL,
    valor_operacao NUMERIC(18,2) NOT NULL,
    segmento VARCHAR(30) NOT NULL,
    codigo_produto_credito VARCHAR(50) NOT NULL,
    codigo_conta VARCHAR(30) NOT NULL,
    area_beneficiada_ha NUMERIC(18,2),
    data_contratacao TIMESTAMP NOT NULL
);