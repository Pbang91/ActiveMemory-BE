package com.example.activememory.global.logging;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogFilterConfig {
    @Bean
    public FilterRegistrationBean<CustomLogFilter> logFilter(CustomLogFilter filter) {
        FilterRegistrationBean<CustomLogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(filter);
        registrationBean.setOrder(-200); // security는 -100 언저리
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}
