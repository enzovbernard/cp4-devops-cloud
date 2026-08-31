# Clyvo API

## Descrição do Projeto

A Clyvo API é uma aplicação REST desenvolvida para gerenciamento de informações veterinárias, permitindo o cadastro e gerenciamento de animais, responsáveis, veterinários, doenças, vacinas, consultas e históricos clínicos.

O sistema foi construído utilizando arquitetura em camadas e segue princípios de desenvolvimento com Java e Spring Boot, oferecendo endpoints documentados via Swagger/OpenAPI e autenticação baseada em JWT.

---

## Objetivo

O projeto tem como objetivo centralizar o gerenciamento de dados relacionados ao ambiente veterinário, permitindo:

- Cadastro e gerenciamento de animais;
- Controle de responsáveis;
- Cadastro de veterinários;
- Registro de doenças e vacinas;
- Histórico clínico de animais;
- Agendamento e gerenciamento de consultas;
- Segurança de acesso utilizando autenticação JWT.

---

## Tecnologias Utilizadas

- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- Oracle Database
- Swagger/OpenAPI
- JWT Authentication
- Maven

---

## Arquitetura

O projeto segue arquitetura em camadas:

### Controller Layer
Responsável por receber requisições HTTP e retornar respostas ao cliente.

### Service Layer
Responsável pelas regras de negócio e processamento das informações.

### Repository Layer
Responsável pela comunicação com o banco de dados através do Spring Data JPA.

### Model Layer
Contém as entidades utilizadas no sistema.

### Security Layer
Responsável pela autenticação, autorização e geração/validação de tokens JWT.

### Validation Layer
Responsável pela validação de dados enviados e tratamento centralizado de erros.

---

## Configuração

### Clonar repositório

```bash
git clone https://github.com/nfreitas2000/Challenge_Clyvo_JavaApplication.git

cd clyvo_java
```

### Executar projeto

```bash
mvn clean install

mvn spring-boot:run
```

---

## Banco de Dados

A aplicação está configurada para Oracle Database.

Exemplo:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
spring.jpa.hibernate.ddl-auto=update
```

---

# Autenticação

A API utiliza autenticação JWT.

Para utilizar os endpoints protegidos:

### 1 - Criar ou possuir um usuário SwaggerUser

Exemplo:

```sql
insert into T_CLYVO_SWAGGER_PESSOA
(nome,cpf,data_nascimento,email_pessoal)
values
('Pessoa 1','529.982.247-25',DATE '2002-05-05','pessoal1@gmail.com');

insert into T_CLYVO_SWAGGER_USER
(fk_pessoa,username,senha,permissao,data_criacao)
values
(1, 'RM1', '$2a$10$0BhZzH8hG2FEW6qhs9UpV.XlaRPf.ODWDDevV67y3TzXeDzUUaRTK', 'USER', DATE '2026-05-05');
```

Senha correspondente:

```text
1234
```

---

### 2 - Realizar login

Endpoint:

```http
POST /autenticacao/login
```

Exemplo:

```http
localhost:8080/autenticacao/login?username=RM1&password=1234&duracao=10
```

A resposta retornará um token JWT.

---

### 3 - Inserir token no Swagger

Após receber o token:

1. Abrir Swagger:

```http
http://localhost:8080/swagger-ui/index.html
```

2. Clicar em:

```text
Authorize
```

(localizado no topo da página)

3. Inserir:

```text
Bearer seu_token
```

4. Confirmar.

Todos os endpoints protegidos estarão liberados para uso.

---

## Endpoints da API

### Veterinários

#### GET
- `localhost:8080/veterinarios/todos` → Retorna todos os veterinários
- `localhost:8080/veterinarios/{id}` → Retorna um veterinário por ID
- `localhost:8080/veterinarios/buscarNome` → Retorna veterinários com base em uma substring do nome
- `localhost:8080/veterinarios/paginado` → Retorna veterinários utilizando paginação e DTO

#### POST
- `localhost:8080/veterinarios/inserir` → Cria um novo veterinário

#### PUT
- `localhost:8080/veterinarios/{id}` → Atualiza um veterinário por ID

#### DELETE
- `localhost:8080/veterinarios/{id}` → Remove um veterinário por ID

---

### Vacinas

#### GET
- `localhost:8080/vacinas/todos` → Retorna todas as vacinas
- `localhost:8080/vacinas/{id}` → Retorna uma vacina por ID
- `localhost:8080/vacinas/buscarNome` → Retorna vacinas com base em uma substring do nome
- `localhost:8080/vacinas/paginado` → Retorna vacinas utilizando paginação e DTO

#### POST
- `localhost:8080/vacinas/inserir` → Cria uma nova vacina

#### PUT
- `localhost:8080/vacinas/{id}` → Atualiza uma vacina por ID

#### DELETE
- `localhost:8080/vacinas/{id}` → Remove uma vacina por ID

---

### Vacina x Animal

#### GET
- `localhost:8080/vacinasAnimal/todos` → Retorna todos os registros
- `localhost:8080/vacinasAnimal/{id}` → Retorna um registro por ID
- `localhost:8080/vacinasAnimal/paginado` → Retorna registros paginados

#### POST
- `localhost:8080/vacinasAnimal/inserir` → Cria um novo registro

#### PUT
- `localhost:8080/vacinasAnimal/{id}` → Atualiza um registro por ID

#### DELETE
- `localhost:8080/vacinasAnimal/{id}` → Remove um registro por ID

---

### Responsáveis

#### GET
- `localhost:8080/responsaveis/todos` → Retorna todos os responsáveis
- `localhost:8080/responsaveis/{id}` → Retorna um responsável por ID
- `localhost:8080/responsaveis/buscarNome` → Retorna responsáveis por substring
- `localhost:8080/responsaveis/paginado` → Retorna responsáveis paginados

#### POST
- `localhost:8080/responsaveis/inserir` → Cria um responsável

#### PUT
- `localhost:8080/responsaveis/{id}` → Atualiza um responsável

#### DELETE
- `localhost:8080/responsaveis/{id}` → Remove um responsável

---

### Histórico

#### GET
- `localhost:8080/historicos/todos` → Retorna todos os históricos
- `localhost:8080/historicos/{id}` → Retorna um histórico por ID
- `localhost:8080/historicos/paginado` → Retorna históricos paginados

#### POST
- `localhost:8080/historicos/inserir` → Cria um histórico

#### PUT
- `localhost:8080/historicos/{id}` → Atualiza um histórico

#### DELETE
- `localhost:8080/historicos/{id}` → Remove um histórico

---

### Doença x Animal

#### GET
- `localhost:8080/doencasAnimal/todos` → Retorna todos os registros
- `localhost:8080/doencasAnimal/{id}` → Retorna um registro por ID
- `localhost:8080/doencasAnimal/paginado` → Retorna registros paginados

#### POST
- `localhost:8080/doencasAnimal/inserir` → Cria um novo registro

#### PUT
- `localhost:8080/doencasAnimal/{id}` → Atualiza um registro

#### DELETE
- `localhost:8080/doencasAnimal/{id}` → Remove um registro

---

### Doenças

#### GET
- `localhost:8080/doencas/todos` → Retorna todas as doenças
- `localhost:8080/doencas/{id}` → Retorna uma doença por ID
- `localhost:8080/doencas/buscarNome` → Retorna doenças por substring
- `localhost:8080/doencas/paginado` → Retorna doenças paginadas

#### POST
- `localhost:8080/doencas/inserir` → Cria uma nova doença

#### PUT
- `localhost:8080/doencas/{id}` → Atualiza uma doença

#### DELETE
- `localhost:8080/doencas/{id}` → Remove uma doença

---

### Consultas

#### GET
- `localhost:8080/consultas/todos` → Retorna todas as consultas
- `localhost:8080/consultas/{id}` → Retorna uma consulta por ID
- `localhost:8080/consultas/paginado` → Retorna consultas paginadas

#### POST
- `localhost:8080/consultas/inserir` → Cria uma consulta

#### PUT
- `localhost:8080/consultas/{id}` → Atualiza uma consulta

#### DELETE
- `localhost:8080/consultas/{id}` → Remove uma consulta

---

### Animais

#### GET
- `localhost:8080/animais/todos` → Retorna todos os animais
- `localhost:8080/animais/{id}` → Retorna um animal por ID
- `localhost:8080/animais/buscarNome` → Retorna animais por substring
- `localhost:8080/animais/paginado` → Retorna animais paginados

#### POST
- `localhost:8080/animais/inserir` → Cria um novo animal

#### PUT
- `localhost:8080/animais/{id}` → Atualiza um animal

#### DELETE
- `localhost:8080/animais/{id}` → Remove um animal por ID

---

## Integrantes

- RM562979 - Caio Kenzo Tayra – 2TDSPI
- RM563000 - Enzo Vieira Bernardini - 2TDSPI
- RM564992 - Natan Freitas de Moraes – 2TDSPI
- RM561857 - Nicolas Mota Cândido - 2TDSPI