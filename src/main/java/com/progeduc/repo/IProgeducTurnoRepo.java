package com.progeduc.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.progeduc.model.ProgramaeducativoTurno;

@Repository
public interface IProgeducTurnoRepo extends CrudRepository<ProgramaeducativoTurno,Integer>{
	
	@Query("select pen from ProgramaeducativoTurno pen where pen.programaeducativo.id = ?1")
	List<ProgramaeducativoTurno> listProgeducTurno(Integer id);
	
	@Transactional
	@Modifying
	@Query(value="INSERT INTO PROGRAMAEDUCATIVO_TURNO(TURNOID,PROGRAMAEDUCATIVOID) VALUES(?1,?2)",nativeQuery = true)
	Integer guardar(Integer turnoid, Integer programaeducativoid);
	
	@Transactional
	@Modifying	
	@Query(value="delete from Programaeducativo_turno p where p.programaeducativoid = ?1",nativeQuery = true)
	int deleteByProgramaeducativoId(@Param("id") Integer id);
}
