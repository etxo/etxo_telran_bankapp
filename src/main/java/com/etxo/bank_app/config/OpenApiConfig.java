package com.etxo.bank_app.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "etxo",
                        email = "etxo@gmx.de"),
                description = "OpenApi documentation for BANK_APP",
                title = "ETXO_Bank_App",
                version = "1.0"),
        servers = {
                @Server(
                        description = "Development ENV",
                        url = "http://localhost:8080")},
        security = {
                @SecurityRequirement(name = "bearer")})
@SecurityScheme(
        name = "bearer",
        description = "JWT authentication",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER)

public class OpenApiConfig {
}
