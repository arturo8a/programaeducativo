package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Categoria;
import com.progeduc.model.Categoriatrabajo;
import com.progeduc.repo.ICategoriatrabajoRepo;
import com.progeduc.service.ICategoriatrabajoService;

@Service
public class CategoriatrabajoServiceImpl implements ICategoriatrabajoService{
	
	@Autowired
	ICategoriatrabajoRepo repo;

	@Override
	public Categoriatrabajo registrar(Categoriatrabajo obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public Categoriatrabajo modificar(Categoriatrabajo obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categoriatrabajo> listar() {
		// TODO Auto-generated method stub
		return (List<Categoriatrabajo>) repo.findAll();
	}

	@Override
	public Categoriatrabajo ListarporId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
