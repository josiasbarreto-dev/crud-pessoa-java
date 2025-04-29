# Desafio CRUD - Pessoa e Endereço

Este projeto é um desafio técnico que implementa uma API REST utilizando Java com Spring Boot, seguindo os requisitos propostos para a criação de um CRUD (Create, Read, Update, Delete) das entidades **Pessoa** e **Endereço**, com relacionamento de **um-para-muitos**.

## 🧩 Entidades

### Pessoa
- `id`: Long  
- `nome`: String *(Obrigatório)*  
- `dataNascimento`: LocalDate  
- `cpf`: String *(Obrigatório, único)*  

### Endereço
- `id`: Long  
- `rua`: String  
- `numero`: String  
- `bairro`: String  
- `cidade`: String  
- `estado`: String  
- `cep`: String  

## 🔗 Relacionamento

Uma **Pessoa** pode ter **um ou mais Endereços**, caracterizando um relacionamento **um-para-muitos**.

## ✅ Funcionalidades

- [x] Listar todas as pessoas e seus respectivos endereços  
- [x] Criar uma nova pessoa com um ou mais endereços  
- [x] Atualizar os dados de uma pessoa e/ou seus endereços  
- [x] Excluir uma pessoa e todos os seus endereços  
- [x] Exibir a idade da pessoa (calculada a partir da data de nascimento)  

## ⚙️ Tecnologias Utilizadas

- Java 17+  
- Spring Boot  
- Spring Data JPA  
- Banco de Dados H2 (em memória)  
- Lombok  
- Maven  
- Swagger (opcional, se implementado)  
- JUnit/Testes de Integração (opcional, se implementado)  

## 📦 Requisitos do Projeto

- Banco de dados H2 com console acessível em: `http://localhost:8080/h2-console`  
- Todas as respostas da API devem estar em formato JSON  
- Implementação de validações:  
  - Nome e CPF são obrigatórios  
  - CPF deve ser único  

## 🚀 Como Executar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   cd seu-repositorio
2. **Execute o projeto via Maven:**
   ```bash
   ./mvnw spring-boot:run
  Ou, se preferir, abra o projeto em uma IDE como IntelliJ ou Eclipse e execute a classe Application.java
3. **Acesse a aplicação:**
  ```bash
    API Base: http://localhost:8080/persons
