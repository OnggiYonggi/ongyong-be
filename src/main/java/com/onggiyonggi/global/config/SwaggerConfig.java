package com.onggiyonggi.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springBootAPI() {

        Info info = new Info()
            .title("OnggiYonggi Rest API Documentation")
            .description("2025년도 경기대 심화캡스톤 \"옹기용기\" 팀 API 문서")
            .version("0.1");

        return new OpenAPI()
            .addServersItem(new Server().url("/"))
            .info(info);
    }

}

