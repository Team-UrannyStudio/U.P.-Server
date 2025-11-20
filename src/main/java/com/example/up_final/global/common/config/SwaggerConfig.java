package com.example.up_final.global.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        String jwtSchemeName = "JWT Token";
        SecurityScheme securityScheme = new SecurityScheme()
                .name(jwtSchemeName)
                .type(Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(In.HEADER)
                .description("Access Token을 입력하세요.");

        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);

        return new OpenAPI()
                .addSecurityItem(securityRequirement)
                .components(new io.swagger.v3.oas.models.Components().addSecuritySchemes(jwtSchemeName, securityScheme))
                .info(new Info()
                        .title("Auth API")
                        .description("회원 인증 및 JWT 관련 API 명세서")
                        .version("v1.0.0"));
    }
}