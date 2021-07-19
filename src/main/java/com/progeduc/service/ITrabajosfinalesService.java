package com.progeduc.service;

import java.util.List;

import com.progeduc.dto.TrabajosfinalesParticipanteDto;
import com.progeduc.model.Trabajosfinales;

public interface ITrabajosfinalesService extends ICRUD<Trabajosfinales,Integer>{
	
	Trabajosfinales saveTrabajofinaParticipante(TrabajosfinalesParticipanteDto dto);
	
	List<Trabajosfinales> listarhabilitados(Integer programaeducativoid);
	
	List<Trabajosfinales> listarHabilitadosEnviado(Integer programaeducativoid);
	
	List<Trabajosfinales> listarhabilitados();
	
	int updateestado(Integer id, Integer estado, Integer peid);
	
	int updateenviado(Integer id, Integer estado,Integer peid);	

    List<Trabajosfinales> listarTrabajosPendientes();
    
    List<Trabajosfinales> listarHabilitadosEnviados(Integer programaeducativoid);
    
<<<<<<< HEAD
    int updateEstadoTrabajo(Integer id,Integer estadoTrabajoId);
=======
    List<Trabajosfinales> listarTrabajosEvaluados();
>>>>>>> 845748dd599618f43d918749282ac7e2b4641671
}
