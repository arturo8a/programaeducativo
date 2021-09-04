package com.progeduc.repo;

import java.util.List;

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
	Integer guardar(float puntaje, String respuesta,Integer Questionario);
	
	@Transactional
	@Modifying	
	@Query(value="delete from questionariorespuesta a where a.id = ?1",nativeQuery = true)
	Integer eliminar(Integer id);
	
	@Query(value="SELECT TB1.* FROM QUESTIONARIORESPUESTA TB1 WHERE TB1.QUESTIONARIOID = ?1",nativeQuery = true)
	List<QuestionarioRespuesta> listarByTrabajo(Integer trabajoid);

}
