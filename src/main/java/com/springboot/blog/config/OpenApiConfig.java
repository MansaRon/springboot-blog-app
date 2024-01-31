package com.springboot.blog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Thendo
 * @date 2024/01/31
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        Server server = new Server()
                .url("localhost:8080")
                .description("Localhost Server URL");

        Contact contact = new Contact()
                .email("kramashia101@gmail.com")
                .name("Thendo Ramashia");

        Info info = new Info()
                .title("Blog Application API")
                .description("A blog application with Springboot 3 best practices.")
                .termsOfService("terms")
                .contact(contact)
                .license(new License().name("GNU"))
                .version("1.0");

        return new OpenAPI().info(info).addServersItem(server);
    }
}