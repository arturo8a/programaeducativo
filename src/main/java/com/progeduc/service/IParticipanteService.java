package com.progeduc.service;

import java.util.List;

import com.progeduc.model.Participante;

public interface IParticipanteService extends ICRUD<Participante,Integer>{
	
	int updateestado(Integer id, Integer estado);
	
	List<Participante> listarhabilitados(Integer programaeducativoid);
		
}
