package com.suristore.shop.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
		bean.setBasename("classpath:messages"); 
		bean.setDefaultEncoding("UTF-8");
		return bean;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		 registry.addResourceHandler(
				 	"swagger-ui.html",
	                "/webjars/**",
	                "/img/**",
	                "/css/**",
	                "/js/**",
				 	"/static/upload/**")
	                .addResourceLocations(
	                		"classpath:/META-INF/resources/",
	                        "classpath:/META-INF/resources/webjars/",
	                        "classpath:/static/img/",
	                        "classpath:/static/css/",
	                        "classpath:/static/js/",
	                        "classpath:/static/upload/");
	}
	
}
