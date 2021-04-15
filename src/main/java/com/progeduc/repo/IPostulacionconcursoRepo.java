package com.progeduc.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.Postulacionconcurso;
import com.progeduc.model.Programaeducativo;

@Repository
public interface IPostulacionconcursoRepo  extends CrudRepository<Postulacionconcurso,Integer> {
	
	
	@Query(value="SELECT TB1.* FROM POSTULACIONCONCURSO TB1 WHERE TB1.programaeducativoid = ?1 and TB1.ANIO = EXTRACT(YEAR FROM sysdate) FETCH FIRST 1 ROWS ONLY",nativeQuery = true)
	Postulacionconcurso getById(Integer programaeducativoid);
	
	@Query(value="SELECT TB1.* FROM POSTULACIONCONCURSO TB1 WHERE TB1.programaeducativoid = ?1 and TB1.ANIO = ?2",nativeQuery = true)
	Postulacionconcurso getByIdAnio(Integer programaeducativoid, Integer anio);
	
}
