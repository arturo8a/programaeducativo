package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.interfac.AprobacionInscripciones;
import com.progeduc.interfac.DepartamentoDto;
import com.progeduc.model.UsuarioOds;
import com.progeduc.repo.IUsuarioOdsRepo;
import com.progeduc.service.IUsuarioOdsService;

@Service
public class UsuarioOdsServiceImpl implements IUsuarioOdsService{
	
	@Autowired
	IUsuarioOdsRepo usuarioodsRepo;
	
	public UsuarioOds registrar(UsuarioOds obj) {
		// TODO Auto-generated method stub
		return usuarioodsRepo.save(obj);
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
	public Integer guardar(Integer usuarioid, Integer odsid) {
		return usuarioodsRepo.guardar(usuarioid, odsid);
	}
	
	@Override
	public List<UsuarioOds> listarByUsuario(Integer usuarioid){
		return usuarioodsRepo.listarByUsuario(usuarioid);
	}
	
	@Override
	public List<AprobacionInscripciones> listarByUsuarioAprobacionInscripciones(Integer idusuario,Integer idods,Integer anio,String nombre,String estado){
		return usuarioodsRepo.listarByUsuarioAprobacionInscripciones(idusuario,idods,anio,nombre,estado);
	}
	
	@Override
	public List<DepartamentoDto> listarDepartamentoByUsuario(String usuario){
		return usuarioodsRepo.listarDepartamentoByUsuario(usuario);
	}
}
