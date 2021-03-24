package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Usuario;
import com.progeduc.model.UsuarioOds;
import com.progeduc.repo.IUsuarioodsRepo;
import com.progeduc.service.IUsuarioodsService;

@Service
public class UsuarioodsServiceImpl implements IUsuarioodsService{
	
	@Autowired
	IUsuarioodsRepo usuarioodsRepo;
	
	@Override
	public UsuarioOds login(String usuario, String password) {
		
		return usuarioodsRepo.login(usuario, password);
	}
	
	public UsuarioOds byDepartamento(Integer iddepartamento) {
		return usuarioodsRepo.byDepartamento(iddepartamento);
	}

	@Override
	public UsuarioOds registrar(UsuarioOds obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioOds modificar(UsuarioOds obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UsuarioOds> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioOds ListarporId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public UsuarioOds verificarexistenciacorreo(String correo) {
		return usuarioodsRepo.verificarexistenciacorreo(correo);
	}
	
	@Override
	public int updatecontrasenia(Integer id, String contrasenia) {
		return usuarioodsRepo.updatecontrasenia(id, contrasenia);
	}
}
