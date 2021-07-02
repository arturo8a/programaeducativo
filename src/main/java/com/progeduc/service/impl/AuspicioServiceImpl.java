package com.progeduc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.progeduc.model.Auspicio;
import com.progeduc.repo.IAuspicioRepo;
import com.progeduc.service.IAuspicioService;

@Service
public class AuspicioServiceImpl implements IAuspicioService{
	
	@Autowired
	IAuspicioRepo auspicioRepo;

	@Override
	public Auspicio registrar(Auspicio obj) {
		return auspicioRepo.save(obj);
	}

	@Override
	public Auspicio modificar(Auspicio obj) {
		return auspicioRepo.save(obj);
	}

	@Override
	public List<Auspicio> listar() {
		return (List<Auspicio>) auspicioRepo.findAll();
	}

	@Override
	public Auspicio ListarporId(Integer id) {
		Optional<Auspicio> optionalEntity =  auspicioRepo.findById(id);
		if(optionalEntity.isPresent()) {
			return optionalEntity.get();
		}else {
			return null;
		}
	}

	@Override
	public boolean Eliminar(Integer id) {
		return false;
	}

	@Override
	public void eliminarAuspocioByUsuarioId(int usuarioId) {
		auspicioRepo.eliminarAuspocioByUsuarioId(usuarioId);
	}

}
