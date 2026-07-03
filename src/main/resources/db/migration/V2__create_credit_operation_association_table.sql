CREATE TABLE credit_operation_association (
    id_credit_operation_association BIGSERIAL PRIMARY KEY,
    id_operacao_credito BIGINT NOT NULL,
    id_associado BIGINT NOT NULL
);

ALTER TABLE credit_operation_association
ADD CONSTRAINT fk_credit_operation_association_credit_contract
FOREIGN KEY (id_operacao_credito)
REFERENCES credit_contract(id_operacao_credito);

ALTER TABLE credit_operation_association
ADD CONSTRAINT uk_credit_operation_association
UNIQUE (id_operacao_credito);