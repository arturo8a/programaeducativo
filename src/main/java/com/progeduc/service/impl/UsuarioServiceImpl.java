package com.progeduc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.progeduc.dto.UsuarioOdsDto;
import com.progeduc.model.Programaeducativo;
import com.progeduc.model.Usuario;
import com.progeduc.repo.IUsuarioOdsRepo;
import com.progeduc.repo.IUsuarioRepo;
import com.progeduc.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
	
	@Autowired
	IUsuarioRepo usuarioRepo;
	
	@Autowired
	IUsuarioOdsRepo usuarioodsRepo;
	
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
		return (List<Usuario>) usuarioRepo.findAll();
	}

	@Override
	public Usuario ListarporId(Integer id) {
		Optional<Usuario> pe = usuarioRepo.findById(id);
		return pe.isPresent() ? pe.get() : new Usuario();
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public int estadoEliminar(String usuario) {
		return usuarioRepo.estadoEliminar(usuario);
	}
	
	@Override
	public int estadoActivar(String usuario) {
		return usuarioRepo.estadoActivar(usuario);
	}
	
	@Override
	public Usuario verificarexistenciausuario(String usuario) {
		return usuarioRepo.verificarexistenciausuario(usuario);
	}
	
	@Override
	public int updatecontrasenia(Integer id, String contrasenia) {
		return usuarioRepo.updatecontrasenia(id, contrasenia);
	}
	
	@Override
	public Usuario saveUsuarioOds(UsuarioOdsDto dto) {
		System.out.println("1");
		usuarioRepo.save(dto.getUsuario());
		System.out.println("2");
		dto.getOds().forEach(obj->{
			System.out.println("3");
			usuarioodsRepo.guardar(dto.getUsuario().getId(), obj);
			System.out.println("4");
		});
		return dto.getUsuario();
	}
	
	@Override
	public Usuario updateUsuarioOds(UsuarioOdsDto dto) {
		usuarioRepo.save(dto.getUsuario());
		
		usuarioodsRepo.eliminarByUsuarioID(dto.getUsuario().getId());
		dto.getOds().forEach(obj->{
			usuarioodsRepo.guardar(dto.getUsuario().getId(), obj);
		});
		return dto.getUsuario();
	}
}
