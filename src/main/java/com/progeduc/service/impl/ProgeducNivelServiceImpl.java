package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.progeduc.model.ProgramaeducativoNivel;
import com.progeduc.repo.IProgeducNivelRepo;
import com.progeduc.service.IProgeducNivelService;

@Service
public class ProgeducNivelServiceImpl implements IProgeducNivelService{
	
	@Autowired
	IProgeducNivelRepo progeducNivelRepo;

	@Override
	public List<ProgramaeducativoNivel> listProgeducNivel(Integer id) {
		return progeducNivelRepo.listProgeducNivel(id);
	}

	@Override
	public Integer guardar(Integer nivelid, Integer programaeducativoid) {
		// TODO Auto-generated method stub
		return progeducNivelRepo.guardar(nivelid, programaeducativoid);
	}
	
	@Override
	public int deleteByProgramaeducativoId(Integer id) {
		return progeducNivelRepo.deleteByProgramaeducativoId(id);
	}

}
