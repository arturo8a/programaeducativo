package com.progeduc.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.progeduc.model.QuestionarioRespuesta;

@Repository
public interface IQuestionarioRespuestaRepo extends JpaRepository<QuestionarioRespuesta,Integer>{
	
	@Transactional
	@Modifying
	@Query(value="INSERT INTO QUESTIONARIORESPUESTA(PUNTAJE,RESPUESTA,QUESTIONARIOID) VALUES(?1,?2,?3)",nativeQuery = true)
	Integer guardar(Integer puntaje, String respuesta,Integer Questionario);

}
