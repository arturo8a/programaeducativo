package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Estadoevaluacion;
import com.progeduc.model.Estadotrabajo;
import com.progeduc.repo.IEstadotrabajoRepo;
import com.progeduc.service.IEstadotrabajoService;

@Service
public class EstadotrabajoServiceImpl implements IEstadotrabajoService{
	
	@Autowired
	IEstadotrabajoRepo repo;

	@Override
	public Estadotrabajo registrar(Estadotrabajo obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public Estadotrabajo modificar(Estadotrabajo obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estadotrabajo> listar() {
		// TODO Auto-generated method stub
		return (List<Estadotrabajo>) repo.findAll();
	}

	@Override
	public Estadotrabajo ListarporId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
