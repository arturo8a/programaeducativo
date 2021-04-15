package com.progeduc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Tipodocidentprof;
import com.progeduc.model.Tipodocumento;
import com.progeduc.repo.ITipodocRepo;

@Service
public class TipodocService {
	
	@Autowired
	private ITipodocRepo tipodocRepo;
	
	public List<Tipodocidentprof> findAll(){
		return (List<Tipodocidentprof>) tipodocRepo.findAll();
	}
}
