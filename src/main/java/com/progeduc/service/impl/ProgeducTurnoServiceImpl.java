package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.ProgramaeducativoTurno;
import com.progeduc.repo.IProgeducTurnoRepo;
import com.progeduc.service.IProgeducTurnoService;

@Service
public class ProgeducTurnoServiceImpl implements IProgeducTurnoService{

	
	@Autowired
	IProgeducTurnoRepo progeducTurnoRepo;
	
	@Override
	public List<ProgramaeducativoTurno> listProgeducTurno(Integer id) {
		return progeducTurnoRepo.listProgeducTurno(id);
	}

	@Override
	public Integer guardar(Integer turnoid, Integer programaeducativoid) {
		// TODO Auto-generated method stub
		return progeducTurnoRepo.guardar(turnoid, programaeducativoid);
	}
		
	@Override
	public int deleteByProgramaeducativoId(Integer id) {
		return progeducTurnoRepo.deleteByProgramaeducativoId(id);
	}
	

	
}
