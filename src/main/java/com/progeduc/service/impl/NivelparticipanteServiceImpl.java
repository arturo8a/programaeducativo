package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Nivelparticipante;
import com.progeduc.repo.INivelparticipanteRepo;
import com.progeduc.service.INivelparticipanteService;

@Service
public class NivelparticipanteServiceImpl implements INivelparticipanteService{
	
	@Autowired
	INivelparticipanteRepo repo;
	
	@Override
	public Nivelparticipante registrar(Nivelparticipante obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Nivelparticipante modificar(Nivelparticipante obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Nivelparticipante> listar() {
		// TODO Auto-generated method stub
		return (List<Nivelparticipante>) repo.findAll();
	}

	@Override
	public Nivelparticipante ListarporId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
