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

-Before you do steps below, make sure to check mysql Server password:

```
Application properties: 

spring.datasource.driverClassName=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/kucniSavet?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true&createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=root

It is currently set up as default root/root
```

-If your SQL server username and password is set as default root/root, follow these steps:
1. git clone https://github.com/nemop5/KucniSavetApp.git
2. use terminal to navigate to KucniSavetApp, run java -jar Application-0.0.1-SNAPSHOT.jar
3. use terminal to navigate to directory KucniSavet-front, run npm install & npm start
4. follow link: http://localhost:3000
5. use miroslav as username and passoword to login

-If your SQL server username and password is set as somethnig else (not as root/root), than you have to follow these steps:
1. open up your local IDE - select KucniSavetApp as workspace
2. import existing maven project - "kucniSavet-back" 
3. right click on class KucniSavetApplication.java => Run as Java application
4. open up a new window terminal and navigate to directory "kucniSavet-frontend", run: npm install , than run: npm start
5. follow the link to see the app running in development mode: http://localhost:3000 
6. use miroslav as username and password to login

