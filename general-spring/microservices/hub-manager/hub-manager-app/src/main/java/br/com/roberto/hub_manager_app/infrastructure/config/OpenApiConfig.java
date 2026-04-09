package br.com.roberto.hub_manager_app.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * OpenAPI configuration for Swagger UI documentation.
 * Generates API documentation automatically and exposes it at /swagger-ui.html
 */
@Configuration
public class OpenApiConfig {

    @Value("${spring.application.name:Hub Manager API}")
    private String applicationName;

    @Value("${application.version:1.0.0}")
    private String applicationVersion;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hub Manager Payment Link API")
                        .version(applicationVersion)
                        .description("API for managing payment links with hexagonal architecture")
                        .contact(new Contact()
                                .name("Roberto")
                                .email("roberto@example.com")
                                .url("https://github.com/roberto"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8041")
                                .description("Local Development Server"),
                        new Server()
                                .url("http://localhost:8041")
                                .description("Local API Server")
                ));
    }
}
