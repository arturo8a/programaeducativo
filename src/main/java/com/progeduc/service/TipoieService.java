package com.progeduc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Tipoie;
import com.progeduc.repo.ITipoieRepo;

@Service
public class TipoieService {
	
	@Autowired
	private ITipoieRepo tipoieRepo;
	
	public List<Tipoie> findAll(){
		return (List<Tipoie>) tipoieRepo.findAll();
	}
	
	public List<Tipoie> findList(){
		return tipoieRepo.listTie();
	}
	

}
