package ru.effective_mobile.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        title = "Task Management System",
        version = "1.0.0",
        description = "Реализация тестового задания",
        contact = @Contact(name = "Юра Мазуров",
            url = "https://github.com/Yura-0910",
            email = "mazurovyura09@yandex.ru")
    ),
    servers = {
        @Server(url = "http://localhost:8080", description = "Сервер локальной разработки")
    }
)
public class SwaggerConfig {

}
