package com.progeduc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.progeduc.model.Generoprof;
import com.progeduc.repo.IGeneroprofRepo;
import com.progeduc.service.IGeneroprofService;

@Service
public class GeneroprofServiceImpl implements IGeneroprofService{
	
	@Autowired
	IGeneroprofRepo repo;
	
	@Override
	public Generoprof registrar(Generoprof obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Generoprof modificar(Generoprof obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Generoprof> listar() {
		return (List<Generoprof>) repo.findAll();
	}

	@Override
	public Generoprof ListarporId(Integer id) {
		Optional<Generoprof> op = repo.findById(id);
		return op.get();
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
