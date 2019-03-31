package com.baeldung.boot.bootstrapsimpleapplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private Environment environment;

    private static final String CLIENT_ID = "";
    private static final String CLIENT_SECRET = "";
    private static final String AUTH_SERVER = "";
    private static final String TOKEN_ENDPOINT = "";
    private static final String AUTHORIZATION_ENDPOINT = "";

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .host("localhost:8080")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
                /**
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .securitySchemes(Arrays.asList(securityScheme()))
                .securityContexts(Arrays.asList(securityContext()))
                .globalResponseMessage(
                        RequestMethod.GET,
                        newArrayList(
                            new ResponseMessageBuilder()
                                    .code(500)
                                    .message("500 message")
                                    .responseModel(new ModelRef("Error"))
                                    .build(),
                            new ResponseMessageBuilder()
                                .code(403)
                                .message("Forbidden!")
                                .build()
                        )
                );
                /**/
    }
    /**
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(
                        Arrays.asList(
                                new SecurityReference("spring_oauth", scopes())
                        )
                )
                .forPaths(PathSelectors.any())
                .build();
    }

    @Bean
    public SecurityConfiguration securityConfiguration(){
        return SecurityConfigurationBuilder.builder()
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .scopeSeparator(" ")
                .useBasicAuthenticationWithAccessCodeGrant(true)
                .build();
    }

    private SecurityScheme securityScheme() {
        GrantType grantType = new AuthorizationCodeGrantBuilder()
                .tokenEndpoint(
                        new TokenEndpoint(AUTH_SERVER + TOKEN_ENDPOINT, "oauthtoken")
                )
                .tokenRequestEndpoint(
                        new TokenRequestEndpoint(AUTH_SERVER + AUTHORIZATION_ENDPOINT, CLIENT_ID, CLIENT_SECRET)
                )
                .build();
        SecurityScheme oauth = new OAuthBuilder()
                .name("spring_oauth")
                .grantTypes(Arrays.asList(grantType))
                .scopes(Arrays.asList(scopes()))
                .build();
        return oauth;
    }

    private AuthorizationScope[] scopes(){
        AuthorizationScope[] scopes = {
                new AuthorizationScope("read", "for read operations"),
                new AuthorizationScope("write", "for write operations"),
                new AuthorizationScope("foo", "Access foo API") };
        return scopes;
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("TestTitle",
                "Test Description",
                "TestVersion",
                "Test Term of service",
                new Contact("TestContactName",
                        "www.TestURL.com",
                        "testEmail@Test.com"),
                "testLicense",
                "TestLincendeUrl",
                Collections.emptyList());
    }
    /**/
}
