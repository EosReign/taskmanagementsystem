# TaskManagementSystem
***

## Test Task

#### Requirements:

1) Service must support user authentication and authorization by email and password;
2) API Access must be authenticated using JWT token;
3) Users can to rule yourself tasks: Create new, edit existing, review and delete, change status and assign task executors;
4) Users can review tasks another users, and task executors can change status yourself tasks;
5) Сan leave comments on tasks;
6) API must allow get tasks definite author or executor, and all comments with them. Necessery provide filtration and pagination output;
7) Service must handle errors correctly, and to valid input data;
8) Service must be good documented. API must be described with help of Open API and Swagger. In Service must be configured Swagger UI. Necessery to write README with instructions for local launch of project. Need up dev environment with help docker-compose.
9) Write some basic tests for checking general functions of your system.
10) Use for system realisation language Java 17+, Spring, Spring Boot. You can use PostgreSQL or MySQL for DB. Necessary use Spring Security for realisation authentication and authorization. You can use additional tools, if you need it.


***

#### Steps for start Api (Windows):

1) Start the Docker Desktop;
2) Go to file docker-compose.yml in path: "../taskmanagementsystem/docker-compose.yml" and start container;
3) Go to file TaskmanagementsystemApplication in path: "../taskmanagementsystem/src/main/java/ru/eosreign/taskmanagementsystem/TaskmanagementsystemApplication" and run application;

***

#### For testing api with Swagger.ui: 

1) Go to some browser;
2) In address stroke write this: "http://localhost:8080/swagger-ui/index.html";
3) Check methods for interaction with Api;
