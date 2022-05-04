package com.progeduc.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.dto.DocentesReporteDto;
import com.progeduc.model.Participante;
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
	public List<Participante> listarhabilitadosdocentes(Integer programaeducativoid){
		return repo.listarhabilitadosdocentes(programaeducativoid);
	}
	
	@Override
	public List<Participante> listarhabilitadosbyanio(Integer programaeducativoid, Integer anio){
		return repo.listarhabilitadosbyanio(programaeducativoid, anio);
	}
	
	@Override
	public List<Participante> listarReporte(String filtro_categoria,String filtro_modalidad,String filtro_nivel_participante){
		return repo.listarReporte(filtro_categoria,filtro_modalidad,filtro_nivel_participante);
	}
	
	@Override
	public List<Participante> listarhabilitados(){
		return repo.listarhabilitados();
	}
	
	@Override
	public List<Participante> buscaTipoNroDocumento(Integer tipoDocumentoid, String nroDocumento){
		return repo.buscaTipoNroDocumento(tipoDocumentoid, nroDocumento);
	}
	
	@Override
	public List<Participante> buscaTipoNroDocumento(Integer id, Integer tipoDocumentoid, String nroDocumento){
		return repo.buscaTipoNroDocumento(id, tipoDocumentoid, nroDocumento);
	}
	
	@Override
	public List<DocentesReporteDto> buscarDocente(Integer idods,Integer anio) {
		
		List<Object[]> docentesObjetos = repo.buscarDocente(idods, anio);
		List<DocentesReporteDto> lista = new ArrayList<>();
		for (Object[] objects : docentesObjetos) {
			lista.add(new DocentesReporteDto(
											objects[0].toString(),
											objects[1].toString(),
											objects[2].toString(),
											objects[3].toString(),
											objects[4].toString(),
											objects[5].toString(),
											objects[6].toString(),
											objects[7].toString(),
											objects[8].toString(),
											objects[9].toString(),
											objects[10].toString()));
		}		
		return lista;
	}
	

}
