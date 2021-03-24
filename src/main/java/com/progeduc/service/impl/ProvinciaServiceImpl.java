package com.progeduc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Departamento;
import com.progeduc.model.Provincia;
import com.progeduc.repo.IProvinciaRepo;
import com.progeduc.service.IProvinciaService;

@Service
public class ProvinciaServiceImpl implements IProvinciaService{
	
	@Autowired
	IProvinciaRepo provRepo;

	@Override
	public List<Provincia> listProvinciaByDepa(Integer id) {
		return provRepo.listProvinciaByDepa(id);
	}
	
	@Override
	public Provincia getById(Integer id) {
		return provRepo.getOne(id);//  .getById(id);
	}
}
