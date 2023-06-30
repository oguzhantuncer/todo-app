package com.oguzhantuncer.todo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@OpenAPIDefinition(
        info = @Info(
                title = "${Spring.application.name}",
                description = "",
                version = "",
                termsOfService = "")
)
@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    private static final String SWAGGER_UI_HTML = "/swagger-ui.html";


    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", SWAGGER_UI_HTML);
        registry.addRedirectViewController("/swagger-ui", SWAGGER_UI_HTML);
    }

}
