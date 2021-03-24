package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.progeduc.model.Turno;
import com.progeduc.repo.ITurnoRepo;
import com.progeduc.service.ITurnoService;

public class TurnoServiceImpl implements ITurnoService{
	
	@Autowired
	ITurnoRepo repo;
	
	@Override
	public Turno registrar(Turno obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Turno modificar(Turno obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Turno> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Turno ListarporId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
