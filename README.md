# Desafio Sicredi

API REST desenvolvida para gerenciamento de contratos de crédito.

O projeto foi desenvolvido utilizando Java 21, Spring Boot e Arquitetura Hexagonal (Ports & Adapters), priorizando separação de responsabilidades, baixo acoplamento e facilidade de testes.

---

# Objetivo

A API permite:

- Criar contratos de crédito
- Validar contratos através de um serviço externo
- Persistir contratos no PostgreSQL
- Executar migrações automaticamente com Flyway

---

# Tecnologias

- Java 21
- Spring Boot 4
- Spring Data JPA
- PostgreSQL
- Flyway
- OpenFeign
- Maven
- Docker
- Docker Compose

---

## Arquitetura

O projeto segue o padrão **Ports and Adapters (Arquitetura Hexagonal)**.

``` text
                 Cliente HTTP
                       │
                       ▼
              Adapter de Entrada
              (Controllers)
                       │
                       ▼
               Porta de Entrada
                 (Use Cases)
                       │
                       ▼
                Domínio / Regras
                       │
          ┌────────────┴────────────┐
          ▼                         ▼
     Porta de Saída           Porta de Saída
      Repository             Cliente Externo
          │                         │
          ▼                         ▼
      Adapter JPA           Adapter OpenFeign
          │                         │
          ▼                         ▼
     PostgreSQL            API de Validação
```

---

## Benefícios

-   Baixo acoplamento
-   Alta testabilidade
-   Independência do framework
-   Fácil substituição de tecnologias
-   Organização por responsabilidades

---

## Pré-requisitos

Antes de executar o projeto é necessário possuir instalado:

- Docker
- Docker Compose

Não é necessário instalar PostgreSQL localmente.

---

## Executando o projeto

Clone o repositório:

``` bash
git clone <url>
```

Entre na pasta do projeto:

``` bash
cd desafio-sicredi
```

Suba os containers:

``` bash
docker compose up --build
```

A API ficará disponível na porta 8080.

---

## Variáveis de Ambiente

| Variável | Descrição |
|----------|-----------|
| `DB_URL` | URL do PostgreSQL |
| `DB_USERNAME` | Usuário do banco de dados |
| `DB_PASSWORD` | Senha do banco de dados |
| `CREDIT_VALIDATION_URL` | URL do serviço de validação de crédito |

---

## Banco de Dados

O banco utilizado é o **PostgreSQL**.

As tabelas são criadas automaticamente pelo **Flyway** durante a
inicialização da aplicação.

---

## Endpoints

### Criar Contrato

**POST**

``` text
/api/v1/contracts
```

#### Request

``` json
{
  "idAssociado": 2,
  "valorOperacao": 3000.00,
  "segmento": "PF",
  "codigoProdutoCredito": "101A",
  "codigoConta": "1234567891",
  "areaBeneficiadaHa": 0
}
```

#### cURL

``` bash
curl --location 'http://localhost:8080/api/v1/contracts' \
--header 'Content-Type: application/json' \
--data '{
      "idAssociado": 2,
      "valorOperacao": 3000.00,
      "segmento": "PF",
      "codigoProdutoCredito": "101A",
      "codigoConta": "1234567891",
      "areaBeneficiadaHa": 0
}'
```

#### Response 201

``` json
{
  "idOperacaoCredito": 1
}
```

### Response 400

``` json
{
  "message": "Dados inválidos"
}
```

### Response 422

``` json
{
    "message": "Credito não aprovado.",
    "status": 400,
    "timestamp": "2026-07-03T21:32:35.855944531"
}
```

### Response 500

``` json
{
    "message": "Erro interno no servidor",
    "status": 500,
    "timestamp": "2026-07-03T21:32:48.158993215"
}
```

---

## Estrutura do Projeto

``` text
src
├── adapters
│   ├── in
│   └── out
├── application
└── domain
```

### Domain

Contém o núcleo da aplicação, com as entidades, regras de negócio puras e as portas (interfaces) que definem como o domínio se comunica com o mundo externo.

### Application

Contém os casos de uso da aplicação. É responsável por orquestrar o fluxo de execução, coordenando o domínio e as portas de saída para atender às regras de negócio.

### Adapters In

Implementam os pontos de entrada da aplicação, como controllers REST. São responsáveis por receber as requisições, validar os dados de entrada e invocar os casos de uso.

### Adapters Out

Implementam as portas de saída definidas pelo domínio, realizando integrações com tecnologias externas, como PostgreSQL (JPA) e serviços HTTP (OpenFeign).

---

## Docker

### Subir aplicação

``` bash
docker compose up --build
```

### Parar aplicação

``` bash
docker compose down
```

### Remover containers e volumes

``` bash
docker compose down -v
```






