package com.progeduc.service;

import java.util.List;

import com.progeduc.model.TrabajosfinalesUsuarioAlianza;

public interface ITrabajosfinales_UsuarioAlianzaService{
	
	Integer guardar(Integer trabajosfinalesid, Integer usuarioalianzaid, Float nota);
	
	Integer eliminar(Integer trabajosfinalesid);
	
	List<TrabajosfinalesUsuarioAlianza> listarByTrabajosfinalesId(Integer trabajofinalid);
	
	List<TrabajosfinalesUsuarioAlianza> listarAll();
	
	TrabajosfinalesUsuarioAlianza buscar(Integer trabajofinalid, Integer usuarioalianzaid);
	
	List<TrabajosfinalesUsuarioAlianza> listaTrabajosIdByUsuarioId(Integer usuarioalianzaid);
	
}
