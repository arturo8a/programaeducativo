package com.progeduc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.progeduc.dto.rubricaPuntajeDto;
import com.progeduc.model.EvaluacionResultado;
import com.progeduc.model.EvaluacionResultadoNacional;

@Repository
public interface IEvaluacionRespuestaNacionalRepo extends CrudRepository<EvaluacionResultadoNacional,Integer>{
	
	/*@Transactional
	@Modifying	
	@Query("update Evaluacion p set p.estado = ?2 WHERE p.id = ?1")
	int updateestado(Integer id, Integer estado);	*/
	
	
	@Query(value="SELECT tb.* FROM EVALUACIONES_RESPUESTAS_NA tb WHERE tb.TRABAJOID=?1  and tb.PREGUNTAID=?2",nativeQuery = true)
	EvaluacionResultadoNacional getPorCodigoTrabajoAndCodigoPreginta(Integer idTrabajo, Integer idPregunta);
	
	@Query(value="SELECT tb.* FROM EVALUACIONES_RESPUESTAS_NA tb WHERE tb.TRABAJOID=?1",nativeQuery = true)
	List<EvaluacionResultadoNacional> getRespuestas(Integer idTrabajo);
	
	@Query(value="SELECT tb.* FROM EVALUACIONES_RESPUESTAS_NA tb WHERE tb.TRABAJOID=?1 and tb.usuario_alianzaid=?2",nativeQuery = true)
	List<EvaluacionResultadoNacional> listaEvaluacionResultado(Integer idTrabajo, Integer idUsuario);
	
	@Transactional
	@Modifying	
	@Query(value="delete from EVALUACIONES_RESPUESTAS_NA tb where tb.TRABAJOID = ?1",nativeQuery = true)
	void borrarEvaluacionesPorTrabajo(Integer idTrabajo);
	
	@Query(value="select preguntaid,round(avg(puntaje),2) as puntaje from EVALUACIONES_RESPUESTAS_NA where trabajoid = ?1 and tipo=1  group by preguntaid order by preguntaid asc",nativeQuery=true)
	public List<rubricaPuntajeDto> listaRubricaPuntajeDto(Integer trabajoid);
}
