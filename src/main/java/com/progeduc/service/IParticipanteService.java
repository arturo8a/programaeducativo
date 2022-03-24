package com.progeduc.service;

import java.util.List;

import com.progeduc.model.Participante;

public interface IParticipanteService extends ICRUD<Participante,Integer>{
	
	int updateestado(Integer id, Integer estado);
	
	List<Participante> listarhabilitados(Integer programaeducativoid);
	
	List<Participante> listarhabilitadosdocentes(Integer programaeducativoid);
	
	List<Participante> listarReporte(String filtro_categoria,String filtro_modalidad,String filtro_nivel_participante);
	
	List<Participante> listarhabilitados();
	
	List<Participante> listarhabilitadosbyanio(Integer programaeducativoid, Integer anio);
	
	List<Participante> buscaTipoNroDocumento(Integer tipoDocumentoid, String nroDocumento);
	
	List<Participante> buscaTipoNroDocumento(Integer id, Integer tipoDocumentoid, String nroDocumento);
		
}
