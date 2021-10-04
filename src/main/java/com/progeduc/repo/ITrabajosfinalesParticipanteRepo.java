package com.progeduc.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.TrabajosfinalesParticipante;

@Repository
public interface ITrabajosfinalesParticipanteRepo extends CrudRepository<TrabajosfinalesParticipante,Integer>{
	
	@Transactional
	@Modifying
	@Query(value="INSERT INTO TRABAJOSFINALES_PARTICIPANTE(TRABAJOSFINALESID,PARTICIPANTEID) VALUES(?1,?2)",nativeQuery = true)
	Integer guardar(Integer trabajosfinalesid, Integer participantesid);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM TRABAJOSFINALES_PARTICIPANTE WHERE TRABAJOSFINALESID = ?1",nativeQuery = true)
	Integer eliminar(Integer trabajosfinalesid);
	
	@Query(value="SELECT TB1.* FROM TRABAJOSFINALES_PARTICIPANTE TB1 WHERE TB1.trabajosfinalesid = ?1",nativeQuery = true)
	List<TrabajosfinalesParticipante> listar(Integer trabajofinalid);
	
	@Query(value="SELECT TB1.* FROM TRABAJOSFINALES_PARTICIPANTE TB1",nativeQuery = true)
	List<TrabajosfinalesParticipante> listarTodos();
	
	@Query(value="SELECT TB1.* FROM TRABAJOSFINALES_PARTICIPANTE TB1 INNER JOIN TRABAJOSFINALES TB2 ON TB2.ID=TB1.TRABAJOSFINALESID WHERE TB1.participanteid = ?1 AND TB2.CATEGORIATRABAJOID=?2 AND MODALIDADTRABAJOID=?3 and TB2.id!=?4 and TB2.estado=1",nativeQuery = true)
	List<TrabajosfinalesParticipante> listarPorParticipante(Integer participanteid,Integer idCategoria, Integer idModalidad,Integer idTrabajo);
	
	@Query(value="SELECT TB1.* FROM TRABAJOSFINALES_PARTICIPANTE TB1 INNER JOIN TRABAJOSFINALES TB2 ON TB2.ID=TB1.TRABAJOSFINALESID WHERE TB1.participanteid = ?1 AND TB2.CATEGORIATRABAJOID=?2 AND MODALIDADTRABAJOID=?3 and TB2.estado=1",nativeQuery = true)
	List<TrabajosfinalesParticipante> listarPorParticipante(Integer participanteid,Integer idCategoria, Integer idModalidad);
	
	
	/*@Query(value="SELECT TB1.* FROM TRABAJOSFINALES_PARTICIPANTE TB1 INNER JOIN TRABAJOSFINALES TB2 ON TB2.ID=TB1.TRABAJOSFINALESID WHERE TB1.participanteid = ?1 AND TB2.CATEGORIATRABAJOID=?2 AND MODALIDADTRABAJOID=?3 and TB2.estado=1",nativeQuery = true)
	List<TrabajosfinalesParticipante> listarConcursoEscolar(Integer ods,Integer anio );*/
	
	
	

}
