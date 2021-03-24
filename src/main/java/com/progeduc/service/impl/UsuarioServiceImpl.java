package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Usuario;
import com.progeduc.repo.IUsuarioRepo;
import com.progeduc.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
	
	@Autowired
	IUsuarioRepo usuarioRepo;
	
	@Override
	public Usuario login(String usuario, String password) {
		
		return usuarioRepo.login(usuario, password);
	}

	@Override
	public Usuario byUsuario(String usuario) {
		return usuarioRepo.byUsuario(usuario);
	}

	@Override
	public Usuario registrar(Usuario obj) {
		// TODO Auto-generated method stub
		return usuarioRepo.save(obj);
	}

	@Override
	public Usuario modificar(Usuario obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario ListarporId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Usuario verificarexistenciausuario(String usuario) {
		return usuarioRepo.verificarexistenciausuario(usuario);
	}
	
	@Override
	public int updatecontrasenia(Integer id, String contrasenia) {
		return usuarioRepo.updatecontrasenia(id, contrasenia);
	}
}
