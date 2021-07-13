package com.progeduc.service;

import java.util.List;

import com.progeduc.model.TrabajosfinalesParticipante;

public interface ITrabajosfinales_UsuarioAlianzaService{
	
	Integer guardar(Integer trabajosfinalesid, Integer usuarioalianzaid);
	
	Integer eliminar(Integer trabajosfinalesid);
	
	List<TrabajosfinalesParticipante> listarByTrabajosfinalesId(Integer trabajofinalid);
	
}
