package com.progeduc.service;

import java.util.List;

import com.progeduc.dto.CategoriaModalidadByOds;
import com.progeduc.model.TrabajosfinalesUsuarioAlianza;

public interface ITrabajosfinales_UsuarioAlianzaService{
	
	Integer guardar(Integer trabajosfinalesid, Integer usuarioalianzaid, Float nota);
	
	Integer eliminar(Integer trabajosfinalesid);
	
	List<TrabajosfinalesUsuarioAlianza> listarByTrabajosfinalesId(Integer trabajofinalid);
	
	List<TrabajosfinalesUsuarioAlianza> listarAll();
	
	TrabajosfinalesUsuarioAlianza buscar(Integer trabajofinalid, Integer usuarioalianzaid);
	
	List<TrabajosfinalesUsuarioAlianza> listaTrabajosIdByUsuarioId(Integer usuarioalianzaid);
	
	TrabajosfinalesUsuarioAlianza modificarUsuarioAlianza(TrabajosfinalesUsuarioAlianza trabajosfinalesUsuarioAlianza);
	
	Integer actualizarNotaPorParticiante(Integer trabajofinalid, Integer usuarioalianzaid, Float nota);
	
	List<TrabajosfinalesUsuarioAlianza> listarTrabajosFinalesSinNota(Integer trabajofinalid);
	
	List<CategoriaModalidadByOds> listarCategoriaModalidadByOds(Integer odsId);
	
	List<TrabajosfinalesUsuarioAlianza> listarTrabajosFinalesConNotaPromedioPorCategoriaPorModalidad(Integer categoiraId, Integer modalidadId);
	
	/*List<TrabajosfinalesUsuarioAlianza> listarTrabajosFinalesConNotaPromedio(Integer trabajofinalid);*/
	
}
