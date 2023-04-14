package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Value("${imgPath}")
	String imgPath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/uploadfile/**").addResourceLocations("file:"+imgPath+"/");
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
//	C:\\Users\\user\\Desktop\\BackEnd_Project\\uploadfile

}
