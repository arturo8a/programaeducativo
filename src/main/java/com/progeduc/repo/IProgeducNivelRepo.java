package com.progeduc.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.progeduc.model.ProgramaeducativoNivel;

@Repository
public interface IProgeducNivelRepo extends CrudRepository<ProgramaeducativoNivel,Integer> {
	
	@Query("select pen from ProgramaeducativoNivel pen where pen.programaeducativo.id = ?1")
	List<ProgramaeducativoNivel> listProgeducNivel(Integer id);
	
	@Transactional
	@Modifying
	@Query(value="INSERT INTO PROGRAMAEDUCATIVO_NIVEL(NIVELID,PROGRAMAEDUCATIVOID) VALUES(?1,?2)",nativeQuery = true)
	Integer guardar(Integer nivelid, Integer programaeducativoid);
	
	@Transactional
	@Modifying	
	@Query(value="delete from Programaeducativo_nivel p where p.programaeducativoid = ?1",nativeQuery = true)
	int deleteByProgramaeducativoId(@Param("id") Integer id);
}
