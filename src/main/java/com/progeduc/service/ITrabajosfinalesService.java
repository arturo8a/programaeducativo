package com.progeduc.service;

import java.util.List;

import com.progeduc.dto.TrabajosfinalesParticipanteDto;
import com.progeduc.model.Trabajosfinales;

public interface ITrabajosfinalesService extends ICRUD<Trabajosfinales,Integer>{
	
	Trabajosfinales saveTrabajofinaParticipante(TrabajosfinalesParticipanteDto dto);
	
	List<Trabajosfinales> listarhabilitados(Integer programaeducativoid);
	
	List<Trabajosfinales> listarhabilitados();
	
	int updateestado(Integer id, Integer estado, Integer peid);
	
	int updateenviado(Integer id, Integer estado,Integer peid);	
        
        
<<<<<<< HEAD
    List<Trabajosfinales> listarTarabajosPendientes(TrabajosfinalesParticipanteDto dto);
=======
    List<Trabajosfinales> listarTrabajosPendientes();
>>>>>>> df17c640c8b80a31e7f2913e5c9c4ebbd3c108dc
}
