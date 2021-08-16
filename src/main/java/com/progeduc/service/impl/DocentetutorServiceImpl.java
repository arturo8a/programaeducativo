package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Docentetutor;
import com.progeduc.repo.IDocentetutorRepo;
import com.progeduc.service.IDocentetutorService;

@Service
public class DocentetutorServiceImpl implements IDocentetutorService{
	
	@Autowired
	IDocentetutorRepo docentetutorRepo;

	@Override
	public Docentetutor registrar(Docentetutor obj) {
		return docentetutorRepo.save(obj);
	}

	@Override
	public Docentetutor modificar(Docentetutor obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Docentetutor> listar() {
		// TODO Auto-generated method stub
		return (List<Docentetutor>) docentetutorRepo.findAll();
	}

	@Override
	public Docentetutor ListarporId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Docentetutor getByProgeduc(Integer programaeducativoid) {
		return docentetutorRepo.getByProgeduc(programaeducativoid);
	}
	
	@Override
	public Docentetutor getByProgeducByAnio(Integer programaeducativoid,Integer anio) {
		return docentetutorRepo.getByProgeducByAnio(programaeducativoid, anio);
	}
}
