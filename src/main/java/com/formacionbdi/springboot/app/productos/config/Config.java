package com.formacionbdi.springboot.app.productos.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.formacionbdi.springboot.app.productos.anotaciones.WithUserHandlerMethodArgumentResolver;

@Configuration
public class Config implements WebMvcConfigurer {

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new WithUserHandlerMethodArgumentResolver());
	}

}
