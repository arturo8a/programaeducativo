package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Tipodocumento;
import com.progeduc.repo.ITipodocumentoRepo;
import com.progeduc.service.ITipodocumentoService;

@Service
public class TipodocumentoServiceImpl implements ITipodocumentoService{
	
	@Autowired
	ITipodocumentoRepo repo;

	@Override
	public Tipodocumento registrar(Tipodocumento obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tipodocumento modificar(Tipodocumento obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tipodocumento> listar() {
		// TODO Auto-generated method stub
		return (List<Tipodocumento>) repo.findAll();
	}

	@Override
	public Tipodocumento ListarporId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
