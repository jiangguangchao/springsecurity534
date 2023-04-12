package com.jgc.springsecurity.config;

import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Date;

//@Configuration
public class WebConfig extends WebMvcConfigurationSupport {


//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//				.allowedMethods("*")
//				.allowedOrigins("*")
//				.allowedHeaders("*");
//	}
//
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/**")
//				.addResourceLocations("classpath:/static/");
//
//	}

    public static void main(String[] args) {
        Date date = new Date(1653990423844l);
    }

}