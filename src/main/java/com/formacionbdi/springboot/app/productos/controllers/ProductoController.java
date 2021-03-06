package com.formacionbdi.springboot.app.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.app.commons.models.entity.Producto;
import com.formacionbdi.springboot.app.productos.anotaciones.UserInfo;
import com.formacionbdi.springboot.app.productos.anotaciones.WithUser;
import com.formacionbdi.springboot.app.productos.models.service.IProductoService;

@RestController
public class ProductoController {

	@Autowired
	private IProductoService productoService;

//	@Autowired
//	private Environment env;

	@Value("${server.port}")
	private Integer port;

	@GetMapping(value = "/listar")
	public List<Producto> listar() {
		return productoService.findAll().stream().map(producto -> {
//			producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			producto.setPort(port);
			return producto;
		}).collect(Collectors.toList());
	}

	@GetMapping(value = "/ver/{id}")
	public Producto detalle(@PathVariable Long id) throws Exception {
		Producto producto = productoService.findById(id);
		// con esto obtienes el puerto en el que está el microservicio
//		producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		producto.setPort(1);

		/**
		 * Prueba con histryx cuando se tiene un timeout
		 * 
		 * Thread.sleep(2_000L);
		 */

		/**
		 * Prueba con hystrix cuando se manda una excepción boolean ok = false; if (!ok)
		 * { throw new Exception("Algo"); }
		 */

		return producto;
	}

	@PostMapping(value = "/crear")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return productoService.save(producto);
	}

	@PutMapping(value = "/editar/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
		Producto productoDb = productoService.findById(id);
		productoDb.setNombre(producto.getNombre());
		productoDb.setPrecio(producto.getPrecio());
		return productoService.save(productoDb);
	}

	@DeleteMapping(value = "/eliminar/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		productoService.deleteById(id);
	}

}
