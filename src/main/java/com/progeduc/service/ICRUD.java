package com.progeduc.service;

import java.util.List;

public interface ICRUD <T,V>{
	
	T registrar(T obj);
	T modificar(T obj);
	List<T> listar();
	T ListarporId(V id);
	boolean Eliminar(V id);

}
