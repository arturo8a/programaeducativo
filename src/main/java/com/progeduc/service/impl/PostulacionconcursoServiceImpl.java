package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Postulacionconcurso;
import com.progeduc.repo.IPostulacionconcursoRepo;
import com.progeduc.service.IPostulacionconcursoService;

@Service
public class PostulacionconcursoServiceImpl implements IPostulacionconcursoService{
	
	@Autowired
	IPostulacionconcursoRepo postulacionconcursoRepo;

	@Override
	public Postulacionconcurso registrar(Postulacionconcurso obj) {
		// TODO Auto-generated method stub
		return postulacionconcursoRepo.save(obj);
	}

	@Override
	public Postulacionconcurso modificar(Postulacionconcurso obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Postulacionconcurso> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Postulacionconcurso ListarporId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Postulacionconcurso getById(Integer programaeducativoid) {
		return postulacionconcursoRepo.getById(programaeducativoid);
	}
	
	@Override
	public Postulacionconcurso getByIdAnio(Integer programaeducativoid, Integer anio) {
		return postulacionconcursoRepo.getByIdAnio(programaeducativoid, anio);
	}
	
	@Override
	public int updatefinalizarparticipaciontrabajo(Integer id) {
		return postulacionconcursoRepo.updatefinalizarparticipaciontrabajo(id);
	}
	
	@Override
	public List<Integer> aniosConcurso(String codmod){
		return postulacionconcursoRepo.aniosConcurso(codmod);
	}

}
