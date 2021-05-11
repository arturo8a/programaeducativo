package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Modalidadtrabajo;
import com.progeduc.repo.IModalidadtrabajoRepo;
import com.progeduc.service.IModalidadtrabajoService;

@Service
public class ModalidadtrabajoServiceImpl implements IModalidadtrabajoService{
	
	@Autowired
	IModalidadtrabajoRepo repo;

	@Override
	public Modalidadtrabajo registrar(Modalidadtrabajo obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public Modalidadtrabajo modificar(Modalidadtrabajo obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Modalidadtrabajo> listar() {
		// TODO Auto-generated method stub
		return (List<Modalidadtrabajo>) repo.findAll();
	}

	@Override
	public Modalidadtrabajo ListarporId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
