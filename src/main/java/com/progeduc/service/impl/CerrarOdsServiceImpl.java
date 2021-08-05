package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.CerrarOds;
import com.progeduc.repo.ICerrarOdsRepo;
import com.progeduc.service.ICerrarOdsService;


@Service
public class CerrarOdsServiceImpl implements ICerrarOdsService{
	
	@Autowired
	ICerrarOdsRepo repo;

	@Override
	public CerrarOds guardar(CerrarOds cerrarOds) {
		return repo.save(cerrarOds);
	}

	@Override
	public List<CerrarOds> listCerrarOds() {
		return (List<CerrarOds>) repo.findAll();
	}
	
	
}
