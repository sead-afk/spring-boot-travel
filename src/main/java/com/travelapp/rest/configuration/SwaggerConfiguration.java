package com.travelapp.rest.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
            info = @Info(
                    title = "Elections app - SDP project",
                    version = "1.0.0",
                    description = "SDP project for Elections",
                    contact = @Contact(
                            name = "Sead Munja Gacanovic", email = "sead.gacanovic@stu.ibu.edu.ba")),
            servers = {
                    @Server(url = "/", description = "Default Server URL")
            }
        )
@SecurityScheme(
        name = "JWT Security",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT"
)

public class SwaggerConfiguration {
}
