package com.progeduc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.progeduc.model.Proveedor;
import com.progeduc.model.Tipoie;
import com.progeduc.repo.IProveedorRepo;

@Service
public class ProveedorService {
	
	@Autowired
	private IProveedorRepo proveedorRepo;
	
	public List<Proveedor> findAll(){
		return (List<Proveedor>) proveedorRepo.list();
	}
	
	
}
