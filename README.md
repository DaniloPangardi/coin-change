# Coin Change
Tem como objetivo realizar o cálculo de um determinado troco com o menor número de moedas.

## Tecnologias
* Java 11
* Spring Boot 2.6
* Apache Maven
* Lombok
* Swagger
* JUnit 5
* Mockito
* AssertJ
* JPA
* H2 Database

## Notas Técnicas
* A exposição da API é em modelo REST e todos os conteúdos retornados estão em formato JSON.

## Guidelines
- File name:
  - camelCase
- Classes:
  - PascalCase
- Pacotes:
  - config: Configurações da aplicação
  - controller: Controllers da aplicação
  - dto: Objetos que trafegam entre camadas
  - exception: Exceções de regras de negócio
  - exception.handler: Contem a classe responsável por capturar/tratar todas as exceções lançadas pela aplicação.
  - model: Entidades mapeadas com a base de dados
  - repository: Interface de acesso a base de dados
  - service: Regras de negócio     

## Execução
* mvn clean install
* java -jar target/coin-change-1.0.0.jar

## Ou se preferir
* Na raiz da aplicação, abra um interpretador bash e execute os comandos:
* chmod +x run-app.sh
* ./run-app.sh

## Deploy
- O deploy será realizado no "Heroku" automaticamente após o push ou pull request na branch "main" pelo pipeline do Github.

## Swagger UI
* Local: http://localhost:8080/swagger-ui.html
* Heroku: https://coin-change.herokuapp.com/swagger-ui.html
