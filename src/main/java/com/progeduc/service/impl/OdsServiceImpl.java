package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Ods;
import com.progeduc.repo.IOdsRepo;
import com.progeduc.service.IOdsService;

@Service
public class OdsServiceImpl implements IOdsService{
	
	@Autowired
	IOdsRepo repo;
	
	@Override
	public Ods byOds(int id) {
		return repo.byOds(id);
	}
	
	@Override
	public List<Ods> listarAll(){
		return repo.listarAll();
	}

	@Override
	public List<Ods> listarOdsDeTrabajosEvaluados() {
		return repo.listarOdsDeTrabajosEvaluados();
	}

}
