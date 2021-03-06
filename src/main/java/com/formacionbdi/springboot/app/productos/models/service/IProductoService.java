package com.formacionbdi.springboot.app.productos.models.service;

import java.util.List;

import com.formacionbdi.springboot.app.commons.annotation.UserInfo;
import com.formacionbdi.springboot.app.commons.models.entity.Producto;


public interface IProductoService {

	public List<Producto> findAll();
	
	public Producto save(Producto producto);
	
	public void deleteById(Long id);

	public Producto findById(Long id, UserInfo user);

}
