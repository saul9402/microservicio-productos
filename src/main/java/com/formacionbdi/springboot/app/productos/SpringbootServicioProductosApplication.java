package com.formacionbdi.springboot.app.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Con esta anotación se habilita este proyecto como cliente de eureka; en el
 * video dice que no es obligatorio hacerlo ya que con el solo hecho de tener la
 * dependencia se configura pero recomienda hacerlo
 */
@EnableEurekaClient
@SpringBootApplication
/**
 * Le indicas donde busar la entidades, ya que de no hacerlo no las va a
 * encontrar puesto que no pertenece al package base. Se encuentran en commons.
 * 
 */
@EntityScan({ "com.formacionbdi.springboot.app.commons.models.entity" })
public class SpringbootServicioProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicioProductosApplication.class, args);
	}

}
