package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.CerrarEtapaNacional;
import com.progeduc.repo.ICerrarNacionalRepo;
import com.progeduc.service.ICerrarNacionalService;

@Service
public class CerrarNacionalImpl implements ICerrarNacionalService{

	@Autowired
	ICerrarNacionalRepo cerrarRepo;
	
	@Override
	public CerrarEtapaNacional registrar(CerrarEtapaNacional obj) {
		// TODO Auto-generated method stub
		return cerrarRepo.save(obj);
	}

	@Override
	public CerrarEtapaNacional modificar(CerrarEtapaNacional obj) {
		// TODO Auto-generated method stub
		return cerrarRepo.save(obj);
	}

	@Override
	public List<CerrarEtapaNacional> listar() {
		return (List<CerrarEtapaNacional>) cerrarRepo.findAll();
	}

	@Override
	public CerrarEtapaNacional ListarporId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CerrarEtapaNacional> listaNacionalEmpates() {
		// TODO Auto-generated method stub
		return cerrarRepo.listaNacionalEmpates();
	}

	@Override
	public List<CerrarEtapaNacional> listaPorAnio() {
		return cerrarRepo.listaPorAnio();
	}

}
