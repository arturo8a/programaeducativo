package com.progeduc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.progeduc.model.Evaluacion;

@Repository
public interface IEvaluacionRepo extends CrudRepository<Evaluacion,Integer>{
	
	@Transactional
	@Modifying	
	@Query("update Evaluacion p set p.estado = ?2 WHERE p.id = ?1")
	int updateestado(Integer id, Integer estado);	
	
	
	@Query(value="SELECT tb.* FROM Evaluacion tb WHERE tb.anio=?1  and tb.estado=1",nativeQuery = true)
	Evaluacion getPorAnio(Integer anio);
}
