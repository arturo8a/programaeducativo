package com.progeduc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Provincia;
import com.progeduc.repo.IProvinciaRepo;


@Service
public class ProvinciaService{
	
	@Autowired
	private IProvinciaRepo provinciaRepo;
	
	public List<Provincia> getAllProvincia() {
		return provinciaRepo.findAll();
	}
	
	public List<Provincia> listProvinciaByDepa(Integer id){
		return provinciaRepo.listProvinciaByDepa(id);
	}
	
	public Provincia getById(Integer id) {
		Optional<Provincia> op = provinciaRepo.findById(id);
		if(op.isPresent()) {
			return op.get();
		}
		return null;
	}
	
	
}
