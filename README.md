# KucniSavetApp
Aplikacija koja obaveštava stanare zgrade o događajima i daje predloge za radove sa mogućnošću glasanja.

## Prerequisites

You will need the following tools:
- JDK
- node.js
- mysql

## Technologies used:
- BACKEND: Java, Maven, Spring MVC, MySQL, Hibernate, REST API 
- FRONTEND: React.js, AJAX, Bootstrap 

## To start app:

-Before you do steps below, make sure to check mysql password:

```
Application properties: 

spring.datasource.driverClassName=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/kucniSavet?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true&createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=root

It is currently set up as default root/root
```


Follow these steps to get your app running on your local machine:
1. git clone https://github.com/nemop5/KucniSavetApp.git
2. use terminal to navigate to KucniSavetApp, run java -jar Application-0.0.1-SNAPSHOT.jar
3. use terminal to navigate to directory KucniSavet-front, run npm install & npm start
4. follow link: http://localhost:3000
5. use miroslav as username and passoword to login

