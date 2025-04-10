# Votação - API REST

Este projeto é uma API REST desenvolvida em Java com Spring Boot. O objetivo é gerenciar pautas de votação, com abertura de sessões, registro de votos e apuração dos resultados.

---

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database (ambiente local)
- Maven
- Swagger (Documentação da API)

---

## Como Executar o Projeto

### Pré-requisitos

- Java 17 ou superior
- Maven 3.8+

### Passos para execução

1. **Clone este repositório:**

```bash
git clone https://github.com/evelynbittelbrunn/votacao.git
```

2. **Navegue até o diretório do projeto:**

```bash
cd votacao
```

3. **Execute o projeto:**

```bash
./mvnw spring-boot:run
```

4. **Acesse o Swagger para testar a API:**

```
http://localhost:8080/swagger-ui.html
```

---

## Funcionalidades Implementadas

- Cadastro de novas pautas
- Cadastro de novos associados
- Abertura de sessão de votação com tempo de expiração
- Registro de votos (sim/não)
- Cálculo automático do resultado após o fim da sessão
- API REST documentada com Swagger

---

## Observações

- O projeto está utilizando o banco de dados em memória (H2), pois é mais simples e ideal para testes locais.

---

## Contato

Feito por Evelyn Bittelbrunn

[LinkedIn](https://www.linkedin.com/in/seu-linkedin)  
[Email](mailto:evelynbittelbrunn9@gmail.com)