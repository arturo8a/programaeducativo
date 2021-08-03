package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Rubrica;
import com.progeduc.repo.IRubricaRepo;
import com.progeduc.service.IRubricaService;

@Service
public class RubricaServiceImpl implements IRubricaService{
	
	@Autowired
	IRubricaRepo repo;

	@Override
	public Rubrica registrar(Rubrica obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public Rubrica modificar(Rubrica obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rubrica> listar() {
		// TODO Auto-generated method stub
		return (List<Rubrica>) repo.findAll();
	}

	@Override
	public Rubrica ListarporId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public int updateestado(Integer id,Integer estado) {
		return repo.updateestado(id, estado);
	}
}
