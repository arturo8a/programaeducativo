package com.progeduc.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.Docentetutor;

@Repository
public interface IDocentetutorRepo  extends CrudRepository<Docentetutor,Integer> {
	
	@Query(value="SELECT TB1.* FROM docentetutor TB1 WHERE TB1.programaeducativoid = ?1 and TB1.ANIO = EXTRACT(YEAR FROM sysdate) FETCH FIRST 1 ROWS ONLY",nativeQuery = true)
	Docentetutor getByProgeduc(Integer programaeducativoid);
	
	@Query(value="SELECT TB1.* FROM docentetutor TB1 WHERE TB1.programaeducativoid = ?1 and TB1.ANIO = ?2 FETCH FIRST 1 ROWS ONLY",nativeQuery = true)
	Docentetutor getByProgeducByAnio(Integer programaeducativoid,Integer anio);
}
