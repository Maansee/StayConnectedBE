package com.niit.stayconnected.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//this tells that we have some bean configuration inside the class
import org.springframework.web.servlet.view.InternalResourceViewResolver;
@Configuration
/*<mvc:annotation-driven></mvc:annotation-driven>*/
@EnableWebMvc
//scan the components for creating the beans - controllers, services and repository
@ComponentScan("com.niit.stayconnected")
public class WebAppConfig extends WebMvcConfigurerAdapter{
	
	//create an instance
	
	private static final Logger log = LoggerFactory.getLogger(WebAppConfig.class);
@Bean
public InternalResourceViewResolver viewResolver(){
	log.debug("Starting of the Method viewResolver");
	InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
	viewResolver.setPrefix("/WEB-INF/views/");
	viewResolver.setSuffix(".jsp");
	log.debug("Ending of the Method viewResolver");
	return viewResolver;
}
/*@Bean(name = "multipartResolver")
public CommonsMultipartResolver getCommonsMultipartResolver() {
	CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	multipartResolver.setMaxUploadSize(20971520); // 20MB
	multipartResolver.setMaxInMemorySize(1048576);	// 1MB
	return multipartResolver;
}
*/
public void addResourceHandlers(ResourceHandlerRegistry registry){
	registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
}
}
