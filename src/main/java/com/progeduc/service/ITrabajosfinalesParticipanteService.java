package com.progeduc.service;

import java.util.List;

import com.progeduc.model.TrabajosfinalesParticipante;

public interface ITrabajosfinalesParticipanteService {
	
	Integer guardar(Integer trabajosfinalesid, Integer participanteid);
	
	List<TrabajosfinalesParticipante> listar(Integer trabajofinalid);
	
	Integer eliminar(Integer trabajosfinalesid);

}
