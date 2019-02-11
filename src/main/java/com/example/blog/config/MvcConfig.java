package com.example.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/jsp/**")
                .addResourceLocations("classpath:WEB-INF/jsp/");
    }

//    @Bean
//    public MappingJackson2HttpMessageConverter objectMapper() {
//        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//        jackson2HttpMessageConverter.setObjectMapper(getMapper());
//        return jackson2HttpMessageConverter;
//    }

//    @Bean(name = "messageSource")
//    public MessageSource getMessageResource() {
//        ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
//
//        // Read i18n/messages_xxx.properties file.
//        // For example: i18n/messages_en.properties
////        messageResource.setCacheSeconds(5);
////        messageResource.setBasenames("classpath:static/i18n/messages");
//        messageResource.setDefaultEncoding("UTF-8");
//        messageResource.setFallbackToSystemLocale(false);
//        return messageResource;
//    }

    @Bean
    public StringHttpMessageConverter supportedMediaTypes() {
        StringHttpMessageConverter messageConverter = new StringHttpMessageConverter();
        messageConverter.setSupportedMediaTypes(Arrays.asList(
                new MediaType("text", "plain", Charset.forName("UTF-8")),
                new MediaType("text", "html", Charset.forName("UTF-8"))));
        return messageConverter;
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        final Map<String, String> parameterMap = new HashMap<String, String>();
        parameterMap.put("charset", "utf-8");

        configurer.defaultContentType(new MediaType(
                MediaType.APPLICATION_JSON, parameterMap));
    }
}
