package com.progeduc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Genero;
import com.progeduc.model.Generodir;
import com.progeduc.repo.IGeneroRepo;
import com.progeduc.service.IGeneroService;

@Service
public class GeneroServiceImpl implements IGeneroService{
	
	@Autowired
	IGeneroRepo repo;
	
	@Override
	public Genero registrar(Genero obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Genero modificar(Genero obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Genero> listar() {
		return (List<Genero>) repo.findAll();
	}

	@Override
	public Genero ListarporId(Integer id) {
		Optional<Genero> op = repo.findById(id);
		return op.get();
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
