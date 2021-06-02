package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Estadoevaluacion;
import com.progeduc.repo.IEstadoevaluacionRepo;
import com.progeduc.service.IEstadoevaluacionService;

@Service
public class EstadoevaluacionServiceImpl implements IEstadoevaluacionService{
	
	@Autowired
	IEstadoevaluacionRepo repo;

	@Override
	public Estadoevaluacion registrar(Estadoevaluacion obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public Estadoevaluacion modificar(Estadoevaluacion obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estadoevaluacion> listar() {
		// TODO Auto-generated method stub
		return (List<Estadoevaluacion>) repo.findAll();
	}

	@Override
	public Estadoevaluacion ListarporId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
