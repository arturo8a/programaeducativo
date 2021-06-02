package com.progeduc.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.Evaluacionquestionario;
import com.progeduc.model.Evaluacionrubrica;

@Repository
public interface IEvaluacionQuestionarioRepo extends CrudRepository<Evaluacionquestionario,Integer>{
	
	@Transactional
	@Modifying
	@Query(value="INSERT INTO EVALUACIONQUESTIONARIO(EVALUACIONID,QUESTIONARIOID) VALUES(?1,?2)",nativeQuery = true)
	Integer guardar(Integer evaluacionid, Integer questionarioid);
	
	@Query(value="SELECT TB1.* FROM EVALUACIONQUESTIONARIO TB1 where TB1.evaluacionid = ?",nativeQuery = true)
	List<Evaluacionquestionario> listarPorEvaluacionId(Integer id);
}
