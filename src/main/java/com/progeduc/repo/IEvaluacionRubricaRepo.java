package com.progeduc.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.Evaluacionrubrica;
import com.progeduc.model.Programaeducativo;

@Repository
public interface IEvaluacionRubricaRepo extends CrudRepository<Evaluacionrubrica,Integer>{
	
	@Transactional
	@Modifying
	@Query(value="INSERT INTO EVALUACIONRUBRICA(EVALUACIONID,RUBRICAID) VALUES(?1,?2)",nativeQuery = true)
	Integer guardar(Integer evaluacionid, Integer rubricaid);
	
	@Query(value="SELECT TB1.* FROM EVALUACIONRUBRICA TB1 where TB1.evaluacionid = ?",nativeQuery = true)
	List<Evaluacionrubrica> listarPorEvaluacionId(Integer id);

}
