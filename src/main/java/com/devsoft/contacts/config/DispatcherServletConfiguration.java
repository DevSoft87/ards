
package com.devsoft.contacts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by Sergey on 30.04.2016.
 */

@Configuration
@ComponentScan({"com.devsoft.contacts.controller"})
@EnableAsync
public class DispatcherServletConfiguration extends WebMvcConfigurationSupport {

    @Bean
    public SessionLocaleResolver localeResolver() {
        return new SessionLocaleResolver();
    }

    @Bean
    public InternalResourceViewResolver configureInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/views");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
