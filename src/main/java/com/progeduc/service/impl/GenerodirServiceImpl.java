package com.progeduc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Generodir;
import com.progeduc.repo.IGenerodirRepo;
import com.progeduc.service.IGenerodirService;

@Service
public class GenerodirServiceImpl implements IGenerodirService{
	
	@Autowired
	IGenerodirRepo repo;
	
	@Override
	public Generodir registrar(Generodir obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Generodir modificar(Generodir obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Generodir> listar() {
		return (List<Generodir>) repo.findAll();
	}

	@Override
	public Generodir ListarporId(Integer id) {
		Optional<Generodir> op = repo.findById(id);
		return op.get();
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
