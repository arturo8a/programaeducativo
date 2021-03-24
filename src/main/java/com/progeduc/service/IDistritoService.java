package com.progeduc.service;

import java.util.List;

import com.progeduc.model.Distrito;

public interface IDistritoService {
	
	List<Distrito> listByProvincia(Integer id);
	Distrito getById(Integer id);
	Distrito listByOdsid(Integer id);
}
