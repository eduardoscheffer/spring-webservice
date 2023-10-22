# Spring Web Service

Este é um projeto de exemplo que demonstra a criação de um serviço da web usando o framework Spring e Maven como gerenciador de dependências.

## Descrição do Projeto

Este projeto foi desenvolvido com o objetivo de demonstrar como criar um serviço da web simples usando o Spring Framework. Ele inclui um conjunto de recursos e endpoints de amostra que podem ser usados como ponto de partida para criar seu próprio serviço da web.

## Domain model
<img src="https://github.com/eduardoscheffer/spring-webservice/blob/master/src/main/resources/static/domain-model.png?raw=true" alt="domain-model" width="900" height="450"/>

## Domain instance

<img src="https://github.com/eduardoscheffer/spring-webservice/blob/master/src/main/resources/static/domain-instance.png?raw=true" alt="domain-model" width="900" height="450"/>

## Tecnologias utilizadas
<div style ="display: inline">
  <img width ='150' height ='90' src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original-wordmark.svg"/>
  <img width ='150' height ='90' src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg"/>
  <img width ='150' height ='90' src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-plain-wordmark.svg"/>
  <img width ='150' height ='90' src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/tomcat/tomcat-original.svg"/>
</div>

## Endpoint
- GET Order
- GET Product
- GET Category
- GET User
- POST User
- PUT User
- DELETE User

## Pré-requisitos

Antes de começar, certifique-se de atender aos seguintes requisitos:

- JDK 8 ou superior
- Apache Maven
- Banco de dados (se aplicável)

## Instalação

1. Clone o repositório:

```bash
git clone https://github.com/eduardoscheffer/spring-webservice.git
````
2. Compile o orjeto
```bash
cd spring-webservice
mvn clean install
```
3. Execute o aplicativo
```bash
java -jar target/spring-webservice.jar
```

