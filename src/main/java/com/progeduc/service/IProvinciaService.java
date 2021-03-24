package com.progeduc.service;

import java.util.List;

import com.progeduc.model.Provincia;

public interface IProvinciaService {
	
	List<Provincia> listProvinciaByDepa(Integer id);
	Provincia getById(Integer id);
}
