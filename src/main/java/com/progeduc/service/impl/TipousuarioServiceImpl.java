package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Tipousuario;
import com.progeduc.repo.ITipousuarioRepo;
import com.progeduc.service.ITipousuarioService;

@Service
public class TipousuarioServiceImpl implements ITipousuarioService{

	@Autowired
	ITipousuarioRepo repo;
	
	@Override
	public Tipousuario byTipousuario(int id) {
		return repo.byTipousuario(id);
	}
	
	@Override
	public List<Tipousuario> lista(){
		return repo.lista();
	}

}
