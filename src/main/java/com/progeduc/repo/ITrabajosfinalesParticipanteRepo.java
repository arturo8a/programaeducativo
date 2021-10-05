package com.progeduc.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.interfac.AprobacionInscripciones;
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
	
	
	@Query(value="select   from trabajosfinalesparticipante tf"
			+ " inner join distrito d on d.id = p.distritoid "
			+ " inner join ods o on o.id = d.odsid"
			+ " where 't'=(case ?1 when 0 then 't' else ( case ?1 when o.id then 't' else 'f' end) end) "
			+ " and 't'=(case ?2 when 0	then 't' else ( case ?2 when p.anhio then 't' else 'f' end) end) "
			+ " and UPPER(p.nomie) like UPPER(?3) "
			+ " and 't'=(case ?4 when 'Todos' then 't' else( case ?4 when p.estado then 't' else 'f' end ) end) ",nativeQuery = true)
	List<AprobacionInscripciones> listarAprobacionInscripcion(Integer idods,Integer anio,String nombre,String estado);
	
	
	@Query(value="SELECT TB1.* FROM TRABAJOSFINALES_PARTICIPANTE TB1 INNER JOIN TRABAJOSFINALES TB2 ON TB2.ID=TB1.TRABAJOSFINALESID WHERE TB1.participanteid = ?1 AND TB2.CATEGORIATRABAJOID=?2 AND MODALIDADTRABAJOID=?3 and TB2.id!=?4 and TB2.estado=1",nativeQuery = true)
	List<TrabajosfinalesParticipante> listarPorParticipante(Integer participanteid,Integer idCategoria, Integer idModalidad,Integer idTrabajo);
	
	@Query(value="SELECT TB1.* FROM TRABAJOSFINALES_PARTICIPANTE TB1 INNER JOIN TRABAJOSFINALES TB2 ON TB2.ID=TB1.TRABAJOSFINALESID WHERE TB1.participanteid = ?1 AND TB2.CATEGORIATRABAJOID=?2 AND MODALIDADTRABAJOID=?3 and TB2.estado=1",nativeQuery = true)
	List<TrabajosfinalesParticipante> listarPorParticipante(Integer participanteid,Integer idCategoria, Integer idModalidad);
	
	
	/*@Query(value="SELECT TB1.* FROM TRABAJOSFINALES_PARTICIPANTE TB1 INNER JOIN TRABAJOSFINALES TB2 ON TB2.ID=TB1.TRABAJOSFINALESID WHERE TB1.participanteid = ?1 AND TB2.CATEGORIATRABAJOID=?2 AND MODALIDADTRABAJOID=?3 and TB2.estado=1",nativeQuery = true)
	List<TrabajosfinalesParticipante> listarConcursoEscolar(Integer ods,Integer anio );*/
	
	
	

}
