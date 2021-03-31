package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Parentesco;
import com.progeduc.repo.IParentescoRepo;
import com.progeduc.service.IParentescoService;

@Service
public class ParentescoServiceImpl implements IParentescoService{
	
	@Autowired
	IParentescoRepo repo;
	
	@Override
	public Parentesco registrar(Parentesco obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Parentesco modificar(Parentesco obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Parentesco> listar() {
		// TODO Auto-generated method stub
		return (List<Parentesco>) repo.findAll();
	}

	@Override
	public Parentesco ListarporId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
