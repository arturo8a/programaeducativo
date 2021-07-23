package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Proveedor;
import com.progeduc.model.TipoAuspicio;
import com.progeduc.model.Tipousuario;
import com.progeduc.repo.ITipoAuspicioRepo;
import com.progeduc.repo.ITipousuarioRepo;
import com.progeduc.service.ITipoAuspicioService;
import com.progeduc.service.ITipousuarioService;

@Service
public class TipoAuspicioServiceImpl implements ITipoAuspicioService{

	@Autowired
	ITipoAuspicioRepo repo;

	@Override
	public TipoAuspicio registrar(TipoAuspicio obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoAuspicio modificar(TipoAuspicio obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoAuspicio> listar() {
		return (List<TipoAuspicio>) repo.findAll();
	}

	@Override
	public TipoAuspicio ListarporId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
