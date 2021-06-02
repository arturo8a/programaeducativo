package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Categoriaevaluacion;
import com.progeduc.repo.ICategoriaevaluacionRepo;
import com.progeduc.service.ICategoriaevaluacionService;

@Service
public class CategoriaevaluacionServiceImpl implements ICategoriaevaluacionService{
	
	@Autowired
	ICategoriaevaluacionRepo repo;

	@Override
	public Categoriaevaluacion registrar(Categoriaevaluacion obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public Categoriaevaluacion modificar(Categoriaevaluacion obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categoriaevaluacion> listar() {
		// TODO Auto-generated method stub
		return (List<Categoriaevaluacion>) repo.findAll();
	}

	@Override
	public Categoriaevaluacion ListarporId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
