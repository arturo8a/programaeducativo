package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.dto.CategoriaModalidadByOds;
import com.progeduc.model.TrabajosfinalesUsuarioAlianza;
import com.progeduc.repo.ITrabajosfinalesUsuarioAlianzaRepo;
import com.progeduc.service.ITrabajosfinales_UsuarioAlianzaService;

@Service
public class Trabajosfinales_UsuarioAlianzaServiceImpl implements ITrabajosfinales_UsuarioAlianzaService{
	
	@Autowired
	ITrabajosfinalesUsuarioAlianzaRepo trabajosfinalesUsuarioAlianzaRepo;
	
	@Override
	public Integer guardar(Integer trabajosfinalesid, Integer usuarioalianzaid, Float nota) {
		return trabajosfinalesUsuarioAlianzaRepo.guardar(trabajosfinalesid, usuarioalianzaid, nota);
	}

	@Override
	public Integer eliminar(Integer trabajosfinalesid) {
		return trabajosfinalesUsuarioAlianzaRepo.eliminar(trabajosfinalesid);
	}

	@Override
	public List<TrabajosfinalesUsuarioAlianza> listarByTrabajosfinalesId(Integer trabajofinalid) {
		return trabajosfinalesUsuarioAlianzaRepo.listarByTrabajosfinalesId(trabajofinalid);
	}
	
	@Override
	public List<TrabajosfinalesUsuarioAlianza> listarAll(){
		return trabajosfinalesUsuarioAlianzaRepo.listarAll();
	}
	
	@Override
	public TrabajosfinalesUsuarioAlianza buscar(Integer trabajofinalid, Integer usuarioalianzaid) {
		return trabajosfinalesUsuarioAlianzaRepo.buscar(trabajofinalid, usuarioalianzaid);
	}

	@Override
	public List<TrabajosfinalesUsuarioAlianza> listaTrabajosIdByUsuarioId(Integer usuarioalianzaid) {
		return trabajosfinalesUsuarioAlianzaRepo.listaTrabajosIdByUsuarioId(usuarioalianzaid);
	}

	@Override
	public TrabajosfinalesUsuarioAlianza modificarUsuarioAlianza(
			TrabajosfinalesUsuarioAlianza trabajosfinalesUsuarioAlianza) {
		return trabajosfinalesUsuarioAlianzaRepo.save(trabajosfinalesUsuarioAlianza);
	}

	@Override
	public Integer actualizarNotaPorParticiante(Integer trabajofinalid, Integer usuarioalianzaid, Float nota) {
		return trabajosfinalesUsuarioAlianzaRepo.actualizarNotaPorParticiante(trabajofinalid, usuarioalianzaid, nota);
	}
	
	@Override
	public List<TrabajosfinalesUsuarioAlianza> listarTrabajosFinalesSinNota(Integer trabajofinalid) {
		return trabajosfinalesUsuarioAlianzaRepo.listarTrabajosFinalesSinNota(trabajofinalid);
	}

	@Override
	public List<TrabajosfinalesUsuarioAlianza> listarTrabajosFinalesConNotaPromedioPorCategoriaPorModalidad(
			Integer categoiraId, Integer modalidadId) {
		return null;
	}

	@Override
	public List<CategoriaModalidadByOds> listarCategoriaModalidadByOds(Integer odsId) {
		return trabajosfinalesUsuarioAlianzaRepo.listarCategoriaModalidadByOds(odsId);
	}

}
