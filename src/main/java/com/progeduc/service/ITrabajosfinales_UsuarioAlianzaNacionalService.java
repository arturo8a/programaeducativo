package com.progeduc.service;

import java.util.List;

import com.progeduc.dto.CategoriaModalidadByOds;
import com.progeduc.model.TrabajosfinalesUsuarioAlianza;
import com.progeduc.model.TrabajosfinalesUsuarioAlianzaNacional;

public interface ITrabajosfinales_UsuarioAlianzaNacionalService{
	
	Integer guardar(Integer trabajosfinalesid, Integer usuarioalianzaid, Float nota);
	
	Integer eliminar(Integer trabajosfinalesid);
	
	List<TrabajosfinalesUsuarioAlianzaNacional> listarByTrabajosfinalesId(Integer trabajofinalid);
	
	List<TrabajosfinalesUsuarioAlianzaNacional> listarAll();
	
	TrabajosfinalesUsuarioAlianzaNacional buscar(Integer trabajofinalid, Integer usuarioalianzaid);
	
	List<TrabajosfinalesUsuarioAlianzaNacional> listaTrabajosIdByUsuarioId(Integer usuarioalianzaid);
	
	TrabajosfinalesUsuarioAlianzaNacional modificarUsuarioAlianza(TrabajosfinalesUsuarioAlianzaNacional trabajosfinalesUsuarioAlianza);
	
	Integer actualizarNotaPorParticiante(Integer trabajofinalid, Integer usuarioalianzaid, Float nota);
	
	List<TrabajosfinalesUsuarioAlianzaNacional> listarTrabajosFinalesSinNota(Integer trabajofinalid);
	
	List<CategoriaModalidadByOds> listarCategoriaModalidadByOds(Integer odsId);
	
	List<TrabajosfinalesUsuarioAlianzaNacional> listarTrabajosFinalesConNotaPromedioPorCategoriaPorModalidad(Integer categoiraId, Integer modalidadId);
	
	/*List<TrabajosfinalesUsuarioAlianzaNacional> listarTrabajosFinalesConNotaPromedio(Integer trabajofinalid);*/
	
}
