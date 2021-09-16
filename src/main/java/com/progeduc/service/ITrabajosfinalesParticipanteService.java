package com.progeduc.service;

import java.util.List;

import com.progeduc.model.TrabajosfinalesParticipante;

public interface ITrabajosfinalesParticipanteService {
	
	Integer guardar(Integer trabajosfinalesid, Integer participanteid);
	
	List<TrabajosfinalesParticipante> listar(Integer trabajofinalid);
	
	Integer eliminar(Integer trabajosfinalesid);
	
	List<TrabajosfinalesParticipante> listarTodos();
	
	List<TrabajosfinalesParticipante> listarPorParticipante(Integer participanteid,Integer idCategoria, Integer idModalidad,Integer idTrabajo);
	
	List<TrabajosfinalesParticipante> listarPorParticipante(Integer participanteid,Integer idCategoria, Integer idModalidad);

}
