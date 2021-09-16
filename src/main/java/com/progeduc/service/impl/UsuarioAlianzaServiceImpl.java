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

	@Override
	public UsuarioAlianza getUsuarioEvaluador(String usuario, String password) {
		return usuarioRepo.getUsuarioEvaluador(usuario, password);
	}

	@Override
	public List<UsuarioAlianza> listaUsuarioFiltro(String ods, String anio, String estado, String role6, String role7, String role8, String role9) {
		return usuarioRepo.listaUsuarioFiltro(ods, anio, estado, role6, role7, role8, role9);
	}
	
	@Override
	public List<UsuarioAlianza> buscarEvaluador(String usuarioEvaluador){
		return usuarioRepo.buscarEvaluador(usuarioEvaluador);
	}
	
}
