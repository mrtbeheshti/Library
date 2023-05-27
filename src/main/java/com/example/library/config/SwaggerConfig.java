package com.example.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

import static com.google.common.base.Predicates.or;


@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .host("127.0.0.1:3030")
                .groupName("public")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.library"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(new ApiKey(HttpHeaders.AUTHORIZATION, HttpHeaders.AUTHORIZATION, "header")))
                .securityContexts(securityContexts());
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("My Tiny Library")
                .description("This is my tiny library for learning Spring Boot")
                .version("1.0.0")
                .termsOfServiceUrl("urn:tos")
                .contact(new Contact("Mohammad Reza Tashakori Beheshti",
                        "https://t.me/mrtb_2000","mrtbeheshti@gmail.com"))
                .license("Don't have any license").build();
    }
    private List<SecurityReference> auth(){
        AuthorizationScope[] authorizationScopes = {
                new AuthorizationScope("public", "access everything")
        };
        return Collections.singletonList(new SecurityReference(HttpHeaders.AUTHORIZATION, authorizationScopes));
    }
    private List<SecurityContext> securityContexts(){
        return Collections.singletonList(SecurityContext.builder()
                .securityReferences(auth())
                .forPaths(or(
                        PathSelectors.ant("/users/**"),
                        PathSelectors.ant("/books/**"),
                        PathSelectors.ant("/reserves/**")
                ))
                .build());
    }
}
