package com.progeduc.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.progeduc.model.ProgramaeducativoNivel;

public interface IProgeducNivelService {
	
	List<ProgramaeducativoNivel> listProgeducNivel(Integer id);
	
	Integer guardar(Integer nivelid, Integer programaeducativoid);
	
	int deleteByProgramaeducativoId(@Param("id") Integer id);
}
