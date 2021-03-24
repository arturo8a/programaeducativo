package com.progeduc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Responsableregistro;
import com.progeduc.repo.IResponsableregistroRepo;
import com.progeduc.service.IResponsableregistroService;

@Service
public class ResponsableregistroServiceImpl implements IResponsableregistroService{
	
	@Autowired
	IResponsableregistroRepo repo;
	
	@Override
	public Responsableregistro registrar(Responsableregistro obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Responsableregistro modificar(Responsableregistro obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Responsableregistro> listar() {
		return (List<Responsableregistro>) repo.findAll();
	}

	@Override
	public Responsableregistro ListarporId(Integer id) {
		Optional<Responsableregistro> op = repo.findById(id);
		return op.get();
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
