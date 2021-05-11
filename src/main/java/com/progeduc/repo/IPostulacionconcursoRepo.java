package com.progeduc.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.progeduc.model.Postulacionconcurso;

@Repository
@Transactional(readOnly = true)
public interface IPostulacionconcursoRepo  extends CrudRepository<Postulacionconcurso,Integer> {
	
	
	@Query(value="SELECT TB1.* FROM POSTULACIONCONCURSO TB1 WHERE TB1.programaeducativoid = ?1 and TB1.ANIO = EXTRACT(YEAR FROM sysdate) FETCH FIRST 1 ROWS ONLY",nativeQuery = true)
	Postulacionconcurso getById(Integer programaeducativoid);
	
	@Query(value="SELECT TB1.* FROM POSTULACIONCONCURSO TB1 WHERE TB1.programaeducativoid = ?1 and TB1.ANIO = ?2",nativeQuery = true)
	Postulacionconcurso getByIdAnio(Integer programaeducativoid, Integer anio);
	
	@Transactional
	@Modifying	
	@Query(value = "update POSTULACIONCONCURSO p set p.finalizarparticipaciontrabajo = 1  WHERE p.programaeducativoid = ?1" ,nativeQuery = true)
	int updatefinalizarparticipaciontrabajo(Integer id);	
	
}
