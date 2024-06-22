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

- Cadastro de candidato: {urlhost}/candidato/
- Cadastro de empresa: {urlhost}/company/
- Criação de novo emprego: {urlhost}/company/job/ (validação de token)

- Login de candidato: {urlhost}/auth/candidate
- Login de empresa: {urlhost}/auth/company

## Rotas via Swagger

- Endereço: {urlhost}/swagger-ui/index.html
