package com.progeduc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Departamento;
import com.progeduc.repo.IDepartamentoRepo;
import com.progeduc.service.IDepartamentoService;

@Service
public class DepartamentoServiceImpl implements IDepartamentoService{
	
	@Autowired
	IDepartamentoRepo deparepo;
	
	@Override
	public Departamento registrar(Departamento obj) {
		// TODO Auto-generated method stub
		return deparepo.save(obj);
	}

	@Override
	public Departamento modificar(Departamento obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Departamento> listar() {
		// TODO Auto-generated method stub
		return deparepo.findAll();
	}

	@Override
	public Departamento ListarporId(Integer id) {
		// TODO Auto-generated method stub
		Optional<Departamento> op = deparepo.findById(id);
		return op.isPresent() ? op.get() : new Departamento();
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		deparepo.deleteById(id);
		return true;
	}

}
