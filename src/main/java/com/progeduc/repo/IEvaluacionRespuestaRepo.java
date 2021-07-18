package com.progeduc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.progeduc.model.Evaluacion;
import com.progeduc.model.EvaluacionResultado;

@Repository
public interface IEvaluacionRespuestaRepo extends CrudRepository<EvaluacionResultado,Integer>{
	
	/*@Transactional
	@Modifying	
	@Query("update Evaluacion p set p.estado = ?2 WHERE p.id = ?1")
	int updateestado(Integer id, Integer estado);	*/
	
	
	@Query(value="SELECT tb.* FROM EVALUACIONES_RESPUESTAS tb WHERE tb.TRABAJOID=?1  and tb.PREGUNTAID=?2",nativeQuery = true)
	EvaluacionResultado getPorCodigoTrabajoAndCodigoPreginta(Integer idTrabajo, Integer idPregunta);
	
	/*
	@Query(value="SELECT tb.* FROM Evaluacion tb WHERE tb.anio=?1  and tb.idcategoriaevaluacion=?2 and tb.idnivelparticipacion=?3 and tb.estado=1",nativeQuery = true)
	Evaluacion getPorAnioCategoriaNivelparticipacion(Integer anio,Integer idcategoria,Integer idnivelparticipacion);
	
	@Query(value="SELECT tb.* FROM Evaluacion tb WHERE tb.anio=?1  and tb.idnivelparticipacion=?2 and tb.idcategoriaevaluacion=?3 and tb.estado=1",nativeQuery = true)
	Evaluacion getPorAnioNivelparticipacionCategoria(Integer anio,Integer nivelparticipacion,Integer idcategoria);*/
}
