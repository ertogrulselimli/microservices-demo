package com.togrul.ms.accounts.config;


import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration

@OpenAPIDefinition(info = @Info(title = "Accounts microservices REST API documentation",
        description = "EazyBank Accounts microservice REST API Documentation",
        version = "v1",
        contact = @Contact(name = "Ertogrul Selimli", email = "ertogrul.selimli@gmail.com", url = "dsffd"),
        license = @License(name = "Apache 2.0", url = "http://easybank.com")),
externalDocs = @ExternalDocumentation(description = "EazyBank accounts microservice REST API Documentation",
url = "https://eazybutes/swagger-ui.html"))
public class OpenApiConfiguration {
}
