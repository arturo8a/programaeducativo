package com.progeduc.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.TrabajosfinalesParticipante;
import com.progeduc.model.TrabajosfinalesUsuarioAlianza;

@Repository
public interface ITrabajosfinalesUsuarioAlianzaRepo extends CrudRepository<TrabajosfinalesUsuarioAlianza,Integer> {
	
	@Transactional
	@Modifying
	@Query(value="INSERT INTO TRABAJOSFINALES_USUARIOALIANZA(TRABAJOSFINALESID,USUARIOALIANZA) VALUES(?1,?2)",nativeQuery = true)
	Integer guardar(Integer trabajosfinalesid, Integer usuarioalianzaid);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM TRABAJOSFINALES_USUARIOALIANZA WHERE TRABAJOSFINALESID = ?1",nativeQuery = true)
	Integer eliminar(Integer trabajosfinalesid);
	
	@Query(value="SELECT TB1.* FROM TRABAJOSFINALES_USUARIOALIANZA TB1 WHERE TB1.trabajosfinalesid = ?1",nativeQuery = true)
	List<TrabajosfinalesParticipante> listarByTrabajosfinalesId(Integer trabajofinalid);
}
