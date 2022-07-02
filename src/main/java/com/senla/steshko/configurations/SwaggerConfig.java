package com.senla.steshko.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebMvc
@EnableSwagger2
@Component
public class SwaggerConfig {

    private static final String AUTH_HEADER = "Authorization";

    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo((apiInfo()))
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Collections.singletonList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", AUTH_HEADER, "header");
    }

    private SecurityContext securityContext() {
        return  SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authenticationScope
                = new AuthorizationScope("global",
                "accessEverything");
        AuthorizationScope[] authenticationScopes = new AuthorizationScope[1];
        authenticationScopes[0] = authenticationScope;
        return Collections.singletonList(new SecurityReference("JWT", authenticationScopes));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("My REST API",
                "Some custom description of API.",
                "1.0",
                "Terms of service",
                new Contact("Alex Steshko", "eu.steshko.com", "steshko02@mail.ru"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }
}
