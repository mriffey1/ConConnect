package com.test.conconnect.config;

import com.test.conconnect.filter.SearchMethodFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<SearchMethodFilter> searchFilter() {
        FilterRegistrationBean<SearchMethodFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SearchMethodFilter());
        registrationBean.addUrlPatterns("/api/search/*");
        return registrationBean;
    }
}
