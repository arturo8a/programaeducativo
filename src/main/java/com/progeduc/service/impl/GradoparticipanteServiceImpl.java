package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Gradoparticipante;
import com.progeduc.repo.IGradoparticipanteRepo;
import com.progeduc.service.IGradoparticipanteService;

@Service
public class GradoparticipanteServiceImpl implements IGradoparticipanteService{
	
	@Autowired
	IGradoparticipanteRepo repo;
	
	@Override
	public Gradoparticipante registrar(Gradoparticipante obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gradoparticipante modificar(Gradoparticipante obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gradoparticipante> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gradoparticipante ListarporId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<Gradoparticipante> listargradopornivel(Integer id){
		return repo.listargradopornivel(id);
	}

}
