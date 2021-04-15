package com.progeduc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Docente;
import com.progeduc.repo.IDocenteRepo;
import com.progeduc.service.IDocenteService;

@Service
public class DocenteServiceImpl implements IDocenteService{
	
	@Autowired
	IDocenteRepo docenteRepo;

	@Override
	public Docente registrar(Docente obj) {
		return docenteRepo.save(obj);
	}

	@Override
	public Docente modificar(Docente obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Docente> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Docente ListarporId(Integer id) {
		Optional<Docente> pe = docenteRepo.findById(id);
		return pe.isPresent() ? pe.get() : new Docente();
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public int updateestado(Integer id, Integer estado) {
		return docenteRepo.updateestado(id, estado);
	}
	
	@Override
	public List<Docente> listarhabilitados(Integer programaeducativoid){
		return docenteRepo.listarhabilitados(programaeducativoid);
	}
	
	@Override
	public Docente getByProgeduc(Integer programaeducativoid) {
		return docenteRepo.getByProgeduc(programaeducativoid);
	}
	
	@Override
	public List<Docente> listByProgeduc(Integer programaeducativoid){
		return docenteRepo.listByProgeduc(programaeducativoid);
	}
}
