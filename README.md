## Considerações:
* O projeto foi separado em 3 módulos (store-api, order-api e payment-api). Também foi criado um database para cada serviço.
O mesmo foi separado para que atenda os princípios de microserviços e sejam independentes.
* Banco de dados: O banco utilizado no projeto foi o PostgreSQL com o Flyway para gerenciar versões do banco.
* Docker: O projeto foi configurado para ser executado em um ambiente de containers.
* AWS: Pelo pouco tempo, não consegui fazer o uso da AWS no projeto.
* Swagger: foi implementado, para acessar, segue os links abaixo:
  - Store API Rest: http://localhost:8080/swagger-ui.html
  - Order API Rest: http://localhost:8081/swagger-ui.html
  - Payment API Rest: http://localhost:8082/swagger-ui.html
  

## Melhorias a serem realizadas:
* Implementar testes;
* Implantar a aplicação juntamente com o banco de dados na AWS;
* Criação do RabbitMQ ou ActiveMQ, para que os pedidos e pagamentos fossem enviados para uma fila e criação de um serviço multi-thread para processar essas informações, possibilitando escalar o processamento de pedidos e pagamentos de acordo com a demanda e implementação de métodos assíncronos.
* Criação de um Load Balancer para auxiliar na escalabilidade da aplicação quando necessário;
* Implementar segurança da API utlizando OAuth2.

## Instruções para execução do projeto Backend-Challange:
1 - Para execução do projeto, será necessário que tenha o docker instalado em sua máquina.

2 - DATABASE: O banco de dados utilizado no projeto foi o PostgreSQL. Utilize o comando abaixo para instalar o banco PostgreSQL através do docker:

`docker-compose up`


# Invillia recruitment challenge

[![Build Status](https://travis-ci.org/shelsonjava/invillia.svg?branch=master)](https://travis-ci.org/shelsonjava/invillia)

![Invillia Logo](https://invillia.com/public/assets/img/logo-invillia.svg)
[Invillia](https://https://www.invillia.com/) - A transformação começa aqui.

The ACME company is migrating their monolithic system to a microservice architecture and you’re responsible to build their MVP (minimum viable product)  .
https://en.wikipedia.org/wiki/Minimum_viable_product

Your challenge is:
Build an application with those features described below, if you think the requirements aren’t detailed enough please leave a comment (portuguese or english) and proceed as best as you can.

You can choose as many features you think it’s necessary for the MVP,  IT’S NOT necessary build all the features, we strongly recommend to focus on quality over quantity, you’ll be evaluated by the quality of your solution.

If you think something is really necessary but you don’t have enough time to implement please at least explain how you would implement it.

## Tasks

Your task is to develop one (or more, feel free) RESTful service(s) to:
* Create a **Store**
* Update a **Store** information
* Retrieve a **Store** by parameters
* Create an **Order** with items
* Create a **Payment** for an **Order**
* Retrieve an **Order** by parameters
* Refund **Order** or any **Order Item**

Fork this repository and submit your code with partial commits.

## Business Rules

* A **Store** is composed by name and address
* An **Order** is composed by address, confirmation date and status
* An **Order Item** is composed by description, unit price and quantity.
* A **Payment** is composed by status, credit card number and payment date
* An **Order** just should be refunded until ten days after confirmation and the payment is concluded.

## Non functional requirements

Your service(s) must be resilient, fault tolerant, responsive. You should prepare it/them to be highly scalable as possible.

The process should be closest possible to "real-time", balancing your choices in order to achieve the expected
scalability.

## Nice to have features (describe or implement):
* Asynchronous processing
* Database
* Docker
* AWS
* Security
* Swagger
* Clean Code



