# Requirements
 For building and running the application you need:

1. JDK 1.8
2. Maven 3
3. MySQL

# Package Structure:
Java Resources
  - src/main/java
 
     - com/bitmosys/abc
     
     - controller  (for the request mappings)
      
                 1. CoinAmountController (for Listing available User Coins for exchanging.)
                 2. ExchangeModalController (for handling the exchange between coins.)
                 3. CoinController (for Listing Coins for Buying.)
                 4. BuyModalController (for handling the buying of coin.)
                 5. UserController (Rest Controller. For Listing All Users.)
                 
      - dto  (Data Transfer Object)
      - model  (for POJOs)
      - exceptionHandler  (for exception handling)
      - repository  (for implementation of persistence layer)
      - service  (for business logic)
      - serviceImpl  (for service implementation)
       
    -src/main/resources
    
            - static  (for the css and js files)
                 -js
                 -css
                 
             - templates(for the html files)
             
             - application.properties
               - copy paste this configuration:
                  spring.datasource.url=jdbc:mysql://localhost:3306/abc
                  spring.datasource.username= your_userName
                  spring.datasource.password= your_password

                  spring.thymeleaf.mode=HTML5
                  spring.thymeleaf.prefix=classpath:/templates/
                  spring.thymeleaf.suffix=.html

                  spring.jpa.show-sql=true

                  spring.jpa.hibernate.ddl-auto=update


                  spring.thymeleaf.cache=false 

                  server.error.whitelabel.enabled=false

                  spring.mvc.throw-exception-if-no-handler-found=true
       
    - src/test/java
      

# Running the application locally

There are several ways to run a Spring Boot application on your local machine. 
One way is to execute the main method in the AbcApplication class from your IDE.

Alternatively you can use the Spring Boot Maven plugin like so in the command line enter the following commands:
1.Build the project using:  mvn clean install
2.Run using: mvn spring-boot:run
3.The web application is accessible via localhost:8080

1.User's Coins Listing and Exchange Feature: [ Supports for multiple user based on user ID i.e. {userId} ]

| URL | Method	 | Sample Valid Request Body |
| :-----: | :-: | :-: |
| http://localhost:8080/usercoins/{userId}  | Get | JSON |


2. Exchange User's Coin 

| URL | Method	 | Sample Valid Request Body |
| :-----: | :-: | :-: |
| http://localhost:8080/usercoins/{userId}/exchange	 | Post/Put	 | JSON |


3.List Coins to Buy

| URL | Method	 | Sample Valid Request Body |
| :-----: | :-: | :-: |
| http://localhost:8080/usercoins/{userId}/coins	 	 | Get	 | JSON |


4.Buy Coins

| URL | Method	 | Sample Valid Request Body |
| :-----: | :-: | :-: |
| http://localhost:8080/usercoins/{userId}/buy	  	 | Post/Put	 | JSON |

