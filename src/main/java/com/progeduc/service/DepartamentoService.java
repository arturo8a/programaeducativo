package com.progeduc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Departamento;
import com.progeduc.repo.IDepartamentoRepo;


public class DepartamentoService {
	
	@Autowired
	private IDepartamentoRepo depaRepo;
	
	public List<Departamento> findAll(){
		return (List<Departamento>) depaRepo.findAll();
	}

	public Optional<Departamento> getDepartamentoById(Integer id) {
		return depaRepo.findById(id);
	}

}
