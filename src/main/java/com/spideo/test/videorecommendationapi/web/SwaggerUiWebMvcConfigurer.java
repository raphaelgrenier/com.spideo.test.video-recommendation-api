package com.spideo.test.videorecommendationapi.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.temporal.Temporal;

import static java.util.Collections.emptyList;
import static org.springframework.util.StringUtils.trimTrailingCharacter;

@EnableSwagger2
@Configuration
public class SwaggerUiWebMvcConfigurer implements WebMvcConfigurer {

    private final String baseUrl;

    @Value("${project.version}")
    private String apiVersion;
    @Value("${project.title}")
    private String apiTitle;
    @Value("${project.description}")
    private String apiDescription;
    @Value("${project.email}")
    private String apiContactEmail;

    public SwaggerUiWebMvcConfigurer(
            @Value("${springfox.documentation.swagger-ui.baseUrl:}") String baseUrl) {
        this.baseUrl = trimTrailingCharacter(baseUrl, '/');
    }

    @Bean
    Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.spideo.test.videorecommendationapi.controller"))
                .build()
                .pathMapping("/")
                .apiInfo(new ApiInfo(apiTitle,
                        apiDescription,
                        apiVersion, "",
                        new Contact(apiContactEmail, "", apiContactEmail),
                        "", "", emptyList()))
                .directModelSubstitute(Temporal.class, String.class);
    }

    @Bean
    SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .clientId("com.spideo.test")
                .clientSecret("no-secret")
                .realm("no-realm")
                .appName("video-recommendation-api")
                .scopeSeparator(",")
                .additionalQueryStringParams(null)
                .useBasicAuthenticationWithAccessCodeGrant(false)
                .enableCsrfSupport(false)
                .build();
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(true)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(true)
                .docExpansion(DocExpansion.NONE)
                .filter(true)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(true)
                .showCommonExtensions(true)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                baseUrl + "/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                .resourceChain(false);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController(baseUrl, baseUrl + "/swagger-ui/");
        registry.addViewController(baseUrl)
                .setViewName("forward:%s/swagger-ui/index.html".formatted(baseUrl));
    }

}