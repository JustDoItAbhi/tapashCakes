package com.tapash.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map /image/** to your static/image folder
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:D:/TAPASH/tapashCakes/src/main/resources/static/image/")
                .setCachePeriod(3600); // Cache for 1 hour

        // Also handle /static/image/** for backward compatibility
        registry.addResourceHandler("/static/image/**")
                .addResourceLocations("file:D:/TAPASH/tapashCakes/src/main/resources/static/image/")
                .setCachePeriod(3600);
    }
}