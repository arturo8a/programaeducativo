package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Usuario;
import com.progeduc.model.Usuario_Ods;
import com.progeduc.repo.IUsuario_odsRepo;
import com.progeduc.service.IUsuario_odsService;

@Service
public class Usuario_odsServiceImpl implements IUsuario_odsService{
	
	@Autowired
	IUsuario_odsRepo usuario_odsRepo;
	
	@Override
	public Usuario_Ods login(String usuario, String password) {
		
		return usuario_odsRepo.login(usuario, password);
	}
	
	public Usuario_Ods byDepartamento(Integer iddepartamento) {
		return usuario_odsRepo.byDepartamento(iddepartamento);
	}

	@Override
	public Usuario_Ods registrar(Usuario_Ods obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario_Ods modificar(Usuario_Ods obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario_Ods> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario_Ods ListarporId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Usuario_Ods verificarexistenciacorreo(String correo) {
		return usuario_odsRepo.verificarexistenciacorreo(correo);
	}
	
	@Override
	public int updatecontrasenia(Integer id, String contrasenia) {
		return usuario_odsRepo.updatecontrasenia(id, contrasenia);
	}
}
