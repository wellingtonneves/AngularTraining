package br.com.java.solution.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket detalheApi() {

        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        docket.select().apis(RequestHandlerSelectors.basePackage("br.com.java.solution")).paths(PathSelectors.any())
                .build().apiInfo(this.informationsApi().build())
                .securityContexts(Arrays.asList(securityContext()));
//                .securitySchemes(Arrays.asList(securityScheme()))

        return docket;
    }

    @Bean
    public SecurityConfiguration security() {
        return springfox.documentation.swagger.web.SecurityConfigurationBuilder.builder()
                .clientId("")
                .clientSecret("")
                .scopeSeparator(" ")
                .useBasicAuthenticationWithAccessCodeGrant(true)
                .build();
    }

    private AuthorizationScope[] scopes() {
        AuthorizationScope[] scopes = {
                new AuthorizationScope("read", "for read operations"),
                new AuthorizationScope("write", "for write operations")};
        return scopes;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(
                        Arrays.asList(new SecurityReference("spring_oauth", scopes())))
                .forPaths(PathSelectors.any())
                .build();
    }

    private ApiInfoBuilder informationsApi() {
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        apiInfoBuilder.title("Cadastro Básico");
        apiInfoBuilder.description("Módulo de Manutenção");
        apiInfoBuilder.contact(this.contact());

        return apiInfoBuilder;
    }

    private Contact contact() {
        return new Contact("Solutions Architecture", "https://github.com/wellingtonneves/AngularTraining", "wellington.diasneves@gmail.com");
    }
}