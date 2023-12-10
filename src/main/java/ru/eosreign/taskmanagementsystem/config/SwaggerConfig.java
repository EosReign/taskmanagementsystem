package ru.eosreign.taskmanagementsystem.config;

import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class SwaggerConfig {
    private static String str = "Requirements:\n" +
            "\n" +
            "1) Service must support user authentication and authorization by email and password;\n" +
            "2) API Access must be authenticated using JWT token;\n" +
            "3) Users can to rule yourself tasks: Create new, edit existing, review and delete, change status and assign" +
            " task executors;\n" +
            "4) Users can review tasks another users, and task executors can change status yourself tasks;\n" +
            "5) Сan leave comments on tasks;\n" +
            "6) API must allow get tasks definite author or executor, and all comments with them. Necessery provide " +
            "filtration and pagination output;\n" +
            "7) Service must handle errors correctly, and to valid input data;\n" +
            "8) Service must be good documented. API must be described with help of Open API and Swagger. In Service " +
            "must be configured Swagger UI. Necessery to write README with instructions for local launch of project. " +
            "Need up dev environment with help docker-compose.\n" +
            "9) Write some basic tests for checking general functions of your system.\n" +
            "10) Use for system realisation language Java 17+, Spring, Spring Boot. You can use PostgreSQL or MySQL " +
            "for DB. Necessary use Spring Security for realisation authentication and authorization. You can use " +
            "additional tools, if you need it.";

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .servers(
                        List.of(
                                new Server().url("http://localhost:8080")
                        )
                )
                .info(
                        new Info()
                                .title("TaskManagementSystem")
                                .description(str)

                );
    }
}
