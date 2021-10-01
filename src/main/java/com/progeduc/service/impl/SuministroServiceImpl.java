package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Suministro;
import com.progeduc.repo.ISuministroRepo;
import com.progeduc.service.ISuministroService;

@Service
public class SuministroServiceImpl implements ISuministroService{

	@Autowired
	ISuministroRepo repo;
	
	@Override
	public List<Suministro> listarPorProgramaeducativo(Integer idpe) {
		return repo.listarPorProgramaeducativo(idpe);
	}

	@Override
	public Suministro registrar(Suministro obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Suministro modificar(Suministro obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Suministro> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Suministro ListarporId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
