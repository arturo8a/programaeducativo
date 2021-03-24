package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Nivel;
import com.progeduc.repo.INivelRepo;
import com.progeduc.service.INivelService;

@Service
public class NivelServiceImpl implements INivelService{
	
	@Autowired
	INivelRepo repo;
	
	@Override
	public Nivel registrar(Nivel obj) {
		
		return repo.save(obj);		
	}

	@Override
	public Nivel modificar(Nivel obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Nivel> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Nivel ListarporId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
