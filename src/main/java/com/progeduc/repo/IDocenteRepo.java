package com.progeduc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.progeduc.model.Docente;
import com.progeduc.model.Participante;

@Repository
public interface IDocenteRepo  extends CrudRepository<Docente,Integer> {
	
	@Transactional
	@Modifying	
	@Query("update Docente p set p.estado = ?2 WHERE p.id = ?1")
	int updateestado(@Param("id") Integer id, @Param("estado") Integer estado);	
	
	@Query(value="SELECT TB1.* FROM docente TB1 WHERE TB1.programaeducativoid = ?1 and TB1.ANHIO = EXTRACT(YEAR FROM sysdate) FETCH FIRST 1 ROWS ONLY",nativeQuery = true)
	Docente getByProgeduc(Integer programaeducativoid);
	
	@Query(value="SELECT TB1.* FROM docente TB1 WHERE TB1.programaeducativoid = ?1 and TB1.ANHIO = EXTRACT(YEAR FROM sysdate) ",nativeQuery = true)
	List<Docente> listByProgeduc(Integer programaeducativoid);
	
	@Query(value="SELECT TB1.* FROM Docente TB1 where TB1.programaeducativoid=?1 and tb1.estado=1",nativeQuery = true)
	List<Docente> listarhabilitados(Integer programaeducativoid);
}
