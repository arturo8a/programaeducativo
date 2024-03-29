package com.progeduc.service;

import java.util.List;

import com.progeduc.dto.ColegioDto;
import com.progeduc.dto.ListaInstitucionEducativa;
import com.progeduc.dto.ProgeducTurnoNivelDto;
import com.progeduc.dto.ProgeducUpdateTurnoNivelDto;
import com.progeduc.interfac.AprobacionInscripciones;
import com.progeduc.interfac.ProgeducDto;
import com.progeduc.model.Programaeducativo;

public interface IProgramaeducativoService extends ICRUD<Programaeducativo,Integer>{
	
	List<Programaeducativo> listarfiltro(String fecha_desde,String fecha_hasta,String nombreie,Integer depa, Integer prov, Integer dist);
	//List<Programaeducativo> listarfiltro(String nombreie);
	
	Programaeducativo getCodmodByAnioActual(String codmod);
	
	Programaeducativo getCodmod(String codmod);
	
	Programaeducativo getCodmodAnio(String codmod,Integer anio);
	
	List<Programaeducativo> buscarCodmod(String codmod);
	
	Programaeducativo getPenultimoAnioByCodmod(String codmod);
	
	List<Programaeducativo> getListarHabilitadosAnioActual();
	
	List<ColegioDto> listCentrosEducativosGroupbyNomie();
	
	Programaeducativo save(Programaeducativo pe);
	
	List<String> listCentrosEducativosGroupbyCodmod();
	
	Programaeducativo saveProgeducTurnoNivel(ProgeducTurnoNivelDto dto);
	
	Programaeducativo updateProgeducTurnoNivel(ProgeducUpdateTurnoNivelDto dto);
	
	List<Programaeducativo> listCentrosEducativosInscritos();
	
	int updateestado(Integer id, String motivo, String motivoobservacion);
	
	int updateestadoaprobar(Integer id, String motivo);
	
	String buscarMotivoPrograma(Integer id);
	
	List<Programaeducativo> listar(Integer iddistrito);
	
	List<Programaeducativo> listarAprobados();
	
	List<ListaInstitucionEducativa> listar1();
	
	Programaeducativo verificarEstadoAnio(String codmod,Integer anio, String estado);
	
	Programaeducativo getActualByCodmod(String codmod);
	
	List<Programaeducativo> listarTodos();
	
	List<AprobacionInscripciones> listarAprobacionInscripcion(Integer idods,Integer anio,String nombre,String estado);
	
	List<ProgeducDto> listarConsultaPe(String usuario,String fechaDesde,String fechaHasta,String nombreie,Integer idDepartamento,Integer idProvincia,Integer idDistrito,Integer inscritoce);
	
	List<Programaeducativo> listarPorAnio(Integer iddistrito, Integer anio);
	
	List<Programaeducativo> getListarHabilitadosPorAnio(Integer anio);
	
	List<Programaeducativo> listaCodmodByAnioActual(String codmod);
	
	int updateConcurso(Integer idpe,Integer valor);
}
