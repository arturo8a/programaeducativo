package com.progeduc.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.progeduc.model.ProgramaeducativoTurno;

public interface IProgeducTurnoService {
	
	List<ProgramaeducativoTurno> listProgeducTurno(Integer id);
	
	Integer guardar(Integer turnoid, Integer programaeducativoid);
	
	int deleteByProgramaeducativoId(@Param("id") Integer id);
}
