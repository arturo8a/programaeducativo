package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Nivelparticipacion;
import com.progeduc.repo.INivelparticipacionRepo;
import com.progeduc.service.INivelparticipacionService;

@Service
public class NivelparticipacionServiceImpl implements INivelparticipacionService{
	
	@Autowired
	INivelparticipacionRepo repo;
	
	@Override
	public Nivelparticipacion registrar(Nivelparticipacion obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Nivelparticipacion modificar(Nivelparticipacion obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Nivelparticipacion> listar() {
		// TODO Auto-generated method stub
		return  (List<Nivelparticipacion>) repo.findAll();
	}

	@Override
	public Nivelparticipacion ListarporId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
