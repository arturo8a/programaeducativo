package com.progeduc.service;

import java.util.List;

import com.progeduc.model.Gradoparticipante;

public interface IGradoparticipanteService extends ICRUD<Gradoparticipante,Integer>{
	
	List<Gradoparticipante> listargradopornivel(Integer id);
	
	List<Gradoparticipante> listarParaCierre();
}
