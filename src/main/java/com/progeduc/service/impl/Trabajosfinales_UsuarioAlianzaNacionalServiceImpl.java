package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.dto.CategoriaModalidadByOds;
import com.progeduc.model.TrabajosfinalesUsuarioAlianza;
import com.progeduc.model.TrabajosfinalesUsuarioAlianzaNacional;
import com.progeduc.repo.ITrabajosfinalesUsuarioAlianzaNacionalRepo;
import com.progeduc.repo.ITrabajosfinalesUsuarioAlianzaRepo;
import com.progeduc.service.ITrabajosfinales_UsuarioAlianzaNacionalService;
import com.progeduc.service.ITrabajosfinales_UsuarioAlianzaService;

@Service
public class Trabajosfinales_UsuarioAlianzaNacionalServiceImpl implements ITrabajosfinales_UsuarioAlianzaNacionalService{
	
	@Autowired
	ITrabajosfinalesUsuarioAlianzaNacionalRepo trabajosfinalesUsuarioAlianzaNacionalRepo;
	
	@Override
	public Integer guardar(Integer trabajosfinalesid, Integer usuarioalianzaid, Float nota) {
		return trabajosfinalesUsuarioAlianzaNacionalRepo.guardar(trabajosfinalesid, usuarioalianzaid, nota);
	}

	@Override
	public Integer eliminar(Integer trabajosfinalesid) {
		return trabajosfinalesUsuarioAlianzaNacionalRepo.eliminar(trabajosfinalesid);
	}

	@Override
	public List<TrabajosfinalesUsuarioAlianzaNacional> listarByTrabajosfinalesId(Integer trabajofinalid) {
		return trabajosfinalesUsuarioAlianzaNacionalRepo.listarByTrabajosfinalesId(trabajofinalid);
	}
	
	@Override
	public List<TrabajosfinalesUsuarioAlianzaNacional> listarAll(){
		return trabajosfinalesUsuarioAlianzaNacionalRepo.listarAll();
	}
	
	@Override
	public TrabajosfinalesUsuarioAlianzaNacional buscar(Integer trabajofinalid, Integer usuarioalianzaid) {
		return trabajosfinalesUsuarioAlianzaNacionalRepo.buscar(trabajofinalid, usuarioalianzaid);
	}

	@Override
	public List<TrabajosfinalesUsuarioAlianzaNacional> listaTrabajosIdByUsuarioId(Integer usuarioalianzaid) {
		return trabajosfinalesUsuarioAlianzaNacionalRepo.listaTrabajosIdByUsuarioId(usuarioalianzaid);
	}

	@Override
	public TrabajosfinalesUsuarioAlianzaNacional modificarUsuarioAlianza(
			TrabajosfinalesUsuarioAlianzaNacional trabajosfinalesUsuarioAlianza) {
		return trabajosfinalesUsuarioAlianzaNacionalRepo.save(trabajosfinalesUsuarioAlianza);
	}

	@Override
	public Integer actualizarNotaPorParticiante(Integer trabajofinalid, Integer usuarioalianzaid, Float nota) {
		return trabajosfinalesUsuarioAlianzaNacionalRepo.actualizarNotaPorParticiante(trabajofinalid, usuarioalianzaid, nota);
	}
	
	@Override
	public List<TrabajosfinalesUsuarioAlianzaNacional> listarTrabajosFinalesSinNota(Integer trabajofinalid) {
		return trabajosfinalesUsuarioAlianzaNacionalRepo.listarTrabajosFinalesSinNota(trabajofinalid);
	}

	@Override
	public List<TrabajosfinalesUsuarioAlianzaNacional> listarTrabajosFinalesConNotaPromedioPorCategoriaPorModalidad(
			Integer categoiraId, Integer modalidadId) {
		return null;
	}

	@Override
	public List<CategoriaModalidadByOds> listarCategoriaModalidadByOds(Integer odsId) {
		return trabajosfinalesUsuarioAlianzaNacionalRepo.listarCategoriaModalidadByOds(odsId);
	}

}
