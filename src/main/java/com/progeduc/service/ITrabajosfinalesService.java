package com.progeduc.service;

import java.util.List;

import com.progeduc.dto.CategoriaModalidadByOds;
import com.progeduc.dto.CategoriaNivelParticipacionByOds;
import com.progeduc.dto.EtapaNacionalDto;
import com.progeduc.dto.TrabajosFinalizados;
import com.progeduc.dto.TrabajosfinalesParticipanteDto;
import com.progeduc.model.Trabajosfinales;

public interface ITrabajosfinalesService extends ICRUD<Trabajosfinales,Integer>{
	
	Trabajosfinales saveTrabajofinaParticipante(TrabajosfinalesParticipanteDto dto);
	
	List<Trabajosfinales> listarhabilitados(Integer programaeducativoid);
	
	List<Trabajosfinales> listarHabilitadosEnviado(Integer programaeducativoid);
	
	List<Trabajosfinales> listarhabilitados();
	
	List<Trabajosfinales> listarhabilitadosbyanio(Integer programaeducativoid,Integer anio);
	
	int updateestado(Integer id, Integer estado, Integer peid);
	
	int updateenviado(Integer id, Integer estado,Integer peid);	
	
	int updateEnviados(Integer estado, Integer Sid);
	
	Integer maxNumeracion(Integer programaeducativoid);
	
	Integer getNumeracion(Integer trabajofinalid);
	
    List<Trabajosfinales> listarTrabajosPendientes();
    
    List<Trabajosfinales> listarHabilitadosEnviados(Integer programaeducativoid);
    
    int updateEstadoTrabajo(Integer id,Integer estadoTrabajoId);
    
    int updateNotaVacio(Integer id);
    
    List<Trabajosfinales> listarTrabajosRegionales(Integer programaeducativoid);
    
    List<Trabajosfinales> listarTrabajosRegionales();
    
    List<Trabajosfinales> listarTrabajosEvaluados();
    
	List<Trabajosfinales> BuscarCategoriaModalidad(Integer idcategoria, Integer idmodalidad,Integer peid);
	
	List<Trabajosfinales> listaTrabajosFinalesConNotaPromedioPorCategoriaModalidadDds(Integer idcategoria, Integer idmodalidad,Integer odsId);
	
	List<CategoriaModalidadByOds> listarCategoriaModalidadByOds(Integer odsId);
	
	List<Trabajosfinales> listarTrabajosfinalesPorOds(Integer odsId);
	
	List<Trabajosfinales> listaTrabajosEmpatadosPorCatModOdsPuesto(Integer idcategoria, Integer idmodalidad,Integer odsId, Integer puesto);
	
	List<Trabajosfinales> listaTrabajosEmpatados();
	
	List<Trabajosfinales> listarhabilitadosPE(Integer peid);
	
	List<TrabajosFinalizados> listaTrabajosFinalesConNotaPromedioPorCategoriaNivelOds(Integer idcategoria, String nivel,Integer odsId);
	
	List<CategoriaNivelParticipacionByOds> listarCategoriaNivelByOds(Integer odsId);
	
	List<TrabajosFinalizados> listaTrabajosEmpatadosPorCatNivOdsPuesto(Integer idcategoria, String nivel,Integer odsId, Integer puesto);
	
	List<TrabajosFinalizados> listaTrabajosFinalesConNotaPromedioPorCategoriaNivelOdsEmpatados(Integer idcategoria, String nivel,Integer odsId);
	
	List<TrabajosFinalizados> listaTrabajosFinalesConNotaPromedioPorCategoriaNivelOdsEmpatadosPendientes(Integer idcategoria, String nivel,Integer odsId);
	
	List<Trabajosfinales> listaTrabajosEmpatadosPorODS(Integer odsId);
	
	List<Trabajosfinales> listarTrabajosConsursoNacional();
	
	List<Trabajosfinales> listaTrabajoEvaluadEmpateNacional();
	
	List<Trabajosfinales> listaTrabajoEvaluadNacional();
	
	List<Trabajosfinales> listarTrabajosfinalesPorNivelCategoria(Integer categoriaId, String nivel);
	
	List<Trabajosfinales> listarTrabajosfinalesPorNivelCategoriaEmpatadosConNota(Integer categoriaId, String nivel);
	
	List<Trabajosfinales> listaTrabajosEmpatadosNacionalPorCatNivPuesto(Integer idcategoria, String nivel, Integer puesto);
	
	List<EtapaNacionalDto> listarTrabajosConsursoNacionalaFinalizar();
	
	int updateEstadoTrabajoNacional(Integer id,Integer estadoTrabajoId);
}
