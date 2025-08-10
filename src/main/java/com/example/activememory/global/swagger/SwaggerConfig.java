package com.example.activememory.global.swagger;

import com.example.activememory.global.exception.ExceptionCode;
import com.example.activememory.global.exception.annotation.ExceptionData;
import com.example.activememory.global.exception.annotation.ExceptionResponse;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "BearerAuth";

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components().addSecuritySchemes(
                                securitySchemeName,
                                new SecurityScheme()
                                        .name("Authorization")
                                        .type(SecurityScheme.Type.APIKEY)
                                        .scheme("bearer")
                                        .name("JWT")
                        )
                );
    }

    @Bean
    public OperationCustomizer operationCustomizer() {
        return (operation, handlerMethod) -> {
            ExceptionResponse apiExceptionResponse = handlerMethod.getMethodAnnotation(ExceptionResponse.class);

            if (apiExceptionResponse != null) {
                ApiResponses apiResponses = operation.getResponses();
                ExceptionData[] exceptionDataList = apiExceptionResponse.value();

                HashMap<HttpStatus, Map<String, Example>> statusToExamples = new HashMap<>();

                for (ExceptionData exceptionData : exceptionDataList) {
                    ExceptionCode exceptionCode = exceptionData.errorCode();
                    String details = exceptionData.details();

                    Map<String, Object> exampleBody = Map.of(
                            "code", exceptionCode.getCode(),
                            "description", exceptionCode.getDescription(),
                            "details", details
                    );

                    Example example = new Example()
                            .summary(exceptionCode.getCode()) // 예시 코드 요약 표시
                            .value(exampleBody); // 실제 JSON body

                    // 상태 코드별로 묶기
                    statusToExamples
                            .computeIfAbsent(exceptionCode.getHttpStatus(), k -> new HashMap<>())
                            .put(exceptionCode.getCode(), example);
                }

                statusToExamples.forEach((status, exampleMap) -> {
                    Content content = new Content().addMediaType(
                            "application/json",
                            new MediaType().examples(exampleMap)
                    );

                    ApiResponse apiResponse = new ApiResponse()
                            .description(status.getReasonPhrase())
                            .content(content);

                    apiResponses.addApiResponse(String.valueOf(status.value()), apiResponse);
                });
            }

            return operation;
        };
    }

    @Bean
    public OpenApiCustomizer globalExceptionCustomizer() {
        return openApi -> openApi.getPaths().values().forEach(pathItem ->
                pathItem.readOperations().forEach(operation -> {
                    // 이미 정의된 응답에 500이 없다면 추가
                    if (!operation.getResponses().containsKey("500")) {
                        Map<String, Object> exampleBody = Map.of(
                                "code", "9999",
                                "description", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                                "details", ""
                        );

                        Example example = new Example()
                                .summary("9999")
                                .value(exampleBody);

                        Content content = new Content().addMediaType(
                                "application/json",
                                new MediaType().addExamples("9999", example)
                        );

                        ApiResponse apiResponse = new ApiResponse()
                                .description(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                                .content(content);

                        operation.getResponses().addApiResponse("500", apiResponse);
                    }
                })
        );
    }
}
