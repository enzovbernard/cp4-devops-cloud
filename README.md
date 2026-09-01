# Sistema de Containers em Nuvem — DevOps Checkpoint

Documentação da arquitetura e do roteiro prático para o deploy automatizado de uma API Java Spring Boot integrada a um banco de dados Oracle, utilizando Azure Container Registry (ACR) e Azure Container Instances (ACI).

## Visão Geral

Este projeto foi desenvolvido como parte do Checkpoint de DevOps e Cloud Computing, com o objetivo de realizar a containerização completa de uma aplicação Java e de um banco de dados relacional Oracle.

A solução utiliza recursos da Microsoft Azure para realizar o deploy dos containers de forma isolada e automatizada, utilizando scripts executados por meio do Azure CLI.

# Objetivo do Projeto

O projeto demonstra a aplicação prática de conceitos de DevOps, Cloud Computing e Containerização, utilizando serviços da Microsoft Azure para automatizar o provisionamento e a execução de uma aplicação Java integrada a um banco de dados Oracle.

A solução contempla:

- Containerização da aplicação;
- Containerização do banco de dados;
- Provisionamento automatizado;
- Armazenamento persistente;
- Deploy utilizando Azure Container Instances;
- Integração entre API e banco de dados;
- Testes das operações CRUD;
- Execução da aplicação sem privilégios de `root`.

### Tecnologias utilizadas

- Java
- Spring Boot
- Docker
- Oracle Database
- Microsoft Azure
- Azure Container Registry (ACR)
- Azure Container Instances (ACI)
- Azure Cloud Shell
- Azure CLI
- JSON

## Estrutura do Repositório

```text
cp4-devops-cloud/
│
├── api-java/
│   ├── src/
│   └── Dockerfile
│
├── banco-dados/
│   ├── scripts/
│   └── Dockerfile
│
├── testes-json/
│   ├── get.json
│   ├── post.json
│   ├── put.json
│   └── delete.json
│
├── clyvo-01store-account.sh
├── clyvo02aci-oracle.sh
├── clyvo03aci-api-java.sh
│
└── README.md
```

### Descrição dos principais diretórios e arquivos

| Arquivo/Diretório | Descrição |
|---|---|
| `api-java/` | Código-fonte da aplicação Spring Boot e Dockerfile utilizado para a criação da imagem da API. |
| `banco-dados/` | Scripts de configuração e Dockerfile utilizados para o container do Oracle Database. |
| `testes-json/` | Arquivos JSON utilizados para testar as operações CRUD da API. |
| `clyvo-01store-account.sh` | Script responsável pela criação da Storage Account utilizada para armazenamento persistente. |
| `clyvo02aci-oracle.sh` | Script responsável pelo deploy do container Oracle no Azure Container Instances. |
| `clyvo03aci-api-java.sh` | Script responsável pelo deploy da API Java no Azure Container Instances. |

## Pré-requisitos

Antes de iniciar o deploy, é necessário possuir:

- Uma assinatura ativa na Microsoft Azure;
- Acesso ao Azure Cloud Shell;
- Azure CLI configurado;
- Git instalado ou disponível no ambiente;
- Permissões suficientes para criar recursos na assinatura Azure.

# Guia de Execução

## 1. Clonar o Repositório

No terminal do Azure Cloud Shell, clone o repositório:

```bash
git clone https://github.com/enzovbernard/cp4-devops-cloud.git
```

Entre no diretório do projeto:

```bash
cd cp4-devops-cloud
```

## 2. Liberar Permissão de Execução

Conceda permissão para executar os scripts `.sh`:

```bash
chmod +x *.sh
```

## 3. Provisionar a Storage Account

Execute o script responsável pela criação da conta de armazenamento:

```bash
./clyvo-01store-account.sh
```

A Storage Account será utilizada como parte da estratégia de armazenamento persistente dos dados do banco de dados.

## 4. Realizar o Deploy do Banco de Dados Oracle

Execute o script responsável pelo deploy do Oracle Database no Azure Container Instances:

```bash
./clyvo02aci-oracle.sh
```

O script realiza a configuração necessária para disponibilizar o banco de dados em um container com os recursos adequados para sua execução.

## 5. Realizar o Deploy da API Java

Após a disponibilização do banco de dados, execute o script responsável pelo deploy da API:

```bash
./clyvo03aci-api-java.sh
```

A aplicação Spring Boot será executada em um container no Azure Container Instances, configurada para se comunicar com o banco de dados Oracle.

# Testes de CRUD

Após o deploy da API, os endpoints podem ser testados utilizando o FQDN público disponibilizado pelo Azure Container Instance.

A aplicação estará disponível na porta:

```text
8080
```

Os arquivos de teste estão disponíveis no diretório:

```text
testes-json/
```

Eles podem ser utilizados para validar as operações:

- `GET` — Consulta de registros
- `POST` — Criação de registros
- `PUT` — Atualização de registros
- `DELETE` — Exclusão de registros

### Exemplo de acesso

```text
http://<FQDN-DA-API>:8080
```

Substitua `<FQDN-DA-API>` pelo endereço público fornecido pelo Azure.

## Validação da Persistência

Além dos testes realizados diretamente na API, a persistência dos dados deve ser validada por meio de consultas diretamente no banco de dados Oracle.

O fluxo esperado é:

```text
Cliente
   |
   v
API Spring Boot
   |
   v
Azure Container Instance
   |
   v
Oracle Database
   |
   v
Storage Account
```

Dessa forma, é possível verificar se as operações realizadas pela API estão sendo efetivamente persistidas no banco de dados.

# Segurança

O projeto segue algumas boas práticas de segurança e infraestrutura.

### Execução da aplicação sem root

O container da aplicação Java é executado utilizando um usuário restrito, evitando a execução do processo principal como `root`.

### Infraestrutura via Azure CLI

A criação e configuração dos recursos de infraestrutura são realizadas exclusivamente por meio de Azure CLI e scripts shell.

Isso permite maior padronização e automatização do processo de provisionamento.

# Equipe

| RM | Nome |
|---|---|
| **RM563000** | Enzo Bernardini |
| **RM562979** | Caio Kenzo Tayra |
| **RM561857** | Nicolas Mota Cândido |

# Link do Repositório Github:

https://github.com/enzovbernard/cp4-devops-cloud