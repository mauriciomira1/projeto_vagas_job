# Projeto de vagas para emprego

## Detalhes iniciais

- Resumo: API de vagas para emprego
- Possui tratamento de exceções
- Autenticação (candidato e empresa) e token
- Validação de dados
- Listagem de vagas disponíveis a partir de palavras-chave do usuário

## Linguagem e dependências:

- Java, com SpringBoot e Maven
- Spring Data JPA
- Spring Security
- Postgres
- Docker
- JWT
- Swagger

## URLs de acesso

- POST Cadastro de candidato: {urlhost}/candidato/
- POST Cadastro de empresa: {urlhost}/company/
- POST Criação de novo emprego: {urlhost}/company/job/ _(Requer autenticação como empresa)_

- POST Login de candidato: {urlhost}/auth/candidate
- POST Login de empresa: {urlhost}/auth/company

- GET Perfil do candidato: {urlhost}/candidate/ _(Requer autenticação como candidato)_
- GET Listagem de vagas disponíveis: {urlhost}/candidate/job _(Requer autenticação como candidato)_

## Rota via Swagger

- Endereço: {urlhost}/swagger-ui/index.html
