package com.progeduc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.progeduc.model.Distrito;
import com.progeduc.repo.IDistritoRepo;
import com.progeduc.service.IDistritoService;

@Service
public class DistritoServiceImpl implements IDistritoService{

	@Autowired
	IDistritoRepo distritoRepo;
	
	@Override
	public List<Distrito> listByProvincia(Integer id) {
		return distritoRepo.listByProvincia(id);
	}
	
	@Override
	public Distrito getById(Integer id) {
		Optional<Distrito> op = distritoRepo.findById(id);
		if(op.isPresent()) {
			return op.get();
		}
		return null;
	}
	
	@Override
	public Distrito listByOdsid(@Param("id") Integer id) {
		return distritoRepo.listByOdsid(id);
	}

}
