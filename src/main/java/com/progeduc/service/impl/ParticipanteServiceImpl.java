package com.progeduc.service.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.progeduc.model.Participante;
import com.progeduc.model.Programaeducativo;
import com.progeduc.repo.IParticipanteRepo;
import com.progeduc.service.IParticipanteService;

@Service
public class ParticipanteServiceImpl implements IParticipanteService{
	
	@Autowired
	IParticipanteRepo repo;

	@Override
	public Participante registrar(Participante obj) {
		return repo.save(obj);
	}

	@Override
	public Participante modificar(Participante obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Participante> listar() {
		return (List<Participante>) repo.findAll();
	}

	@Override
	public Participante ListarporId(Integer id) {
		Optional<Participante> par = repo.findById(id);
		return par.isPresent() ? par.get() : new Participante();
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public int updateestado(Integer id, Integer estado) {
		return repo.updateestado(id, estado);
	}
	
	@Override
	public List<Participante> listarhabilitados(Integer programaeducativoid){
		return repo.listarhabilitados(programaeducativoid);
	}
	
	@Override
	public List<Participante> listarReporte(String filtro_categoria,String filtro_modalidad,String filtro_nivel_participante){
		return repo.listarReporte(filtro_categoria,filtro_modalidad,filtro_nivel_participante);
	}
	
	@Override
	public List<Participante> listarhabilitados(){
		return repo.listarhabilitados();
	}

}
