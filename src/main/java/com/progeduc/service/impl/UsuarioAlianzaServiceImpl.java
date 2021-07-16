package com.progeduc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.progeduc.dto.UsuarioOdsDto;
import com.progeduc.model.Programaeducativo;
import com.progeduc.model.Usuario;
import com.progeduc.model.UsuarioAlianza;
import com.progeduc.repo.IUsuarioAlianzaRepo;
import com.progeduc.repo.IUsuarioOdsRepo;
import com.progeduc.repo.IUsuarioRepo;
import com.progeduc.service.IUsuarioAlianzaService;
import com.progeduc.service.IUsuarioService;

@Service
public class UsuarioAlianzaServiceImpl implements IUsuarioAlianzaService{
	
	@Autowired
	IUsuarioAlianzaRepo usuarioRepo;

	@Override
	public UsuarioAlianza registrar(UsuarioAlianza obj) {
		return usuarioRepo.save(obj);
	}

	@Override
	public UsuarioAlianza modificar(UsuarioAlianza obj) {
		return usuarioRepo.save(obj);
	}

	@Override
	public UsuarioAlianza ListarporId(Integer id) {
		Optional<UsuarioAlianza> optionalEntity =  usuarioRepo.findById(id);
		if(optionalEntity.isPresent()) {
			return optionalEntity.get();
		}else {
			return null;
		}

	}

	@Override
	public Usuario saveUsuario(UsuarioAlianza dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UsuarioAlianza> listar() {
		return (List<UsuarioAlianza>) usuarioRepo.findAll();
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<UsuarioAlianza> listarByOds(Integer odsid){
		return usuarioRepo.listarByOds(odsid);
	}
}
