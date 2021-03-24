package com.progeduc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Lengua;
import com.progeduc.repo.ILenguaRepo;


@Service
public class LenguaService {
	
	@Autowired
	public ILenguaRepo lenguaRepo;
	
	public List<Lengua> findAll(){
		return (List<Lengua>) lenguaRepo.findAll();
	}

}
