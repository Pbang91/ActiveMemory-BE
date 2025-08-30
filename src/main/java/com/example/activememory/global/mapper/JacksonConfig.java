package com.example.activememory.global.mapper;

import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class JacksonConfig implements WebMvcConfigurer {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonNullableCustomizer() {
        return builder -> builder.modules(new JsonNullableModule());
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> c : converters) {
            if (c instanceof MappingJackson2HttpMessageConverter m) {
                List<MediaType> types = new ArrayList<>(m.getSupportedMediaTypes());
                types.add(MediaType.valueOf("application/merge-patch+json"));
                m.setSupportedMediaTypes(types);
            }
        }
    }
}
