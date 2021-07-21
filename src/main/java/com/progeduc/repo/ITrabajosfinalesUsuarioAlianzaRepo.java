package com.progeduc.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.TrabajosfinalesUsuarioAlianza;

@Repository
public interface ITrabajosfinalesUsuarioAlianzaRepo extends CrudRepository<TrabajosfinalesUsuarioAlianza,Integer> {
	
	@Transactional
	@Modifying
	@Query(value="INSERT INTO TRABAJOSFINALES_USUARIOALIANZA(TRABAJOSFINALESID,USUARIOALIANZAID,NOTA) VALUES(?1,?2,?3)",nativeQuery = true)
	Integer guardar(Integer trabajosfinalesid, Integer usuarioalianzaid, Float nota);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM TRABAJOSFINALES_USUARIOALIANZA WHERE TRABAJOSFINALESID = ?1",nativeQuery = true)
	Integer eliminar(Integer trabajosfinalesid);
	
	@Query(value="SELECT TB1.* FROM TRABAJOSFINALES_USUARIOALIANZA TB1 WHERE TB1.trabajosfinalesid = ?1",nativeQuery = true)
	List<TrabajosfinalesUsuarioAlianza> listarByTrabajosfinalesId(Integer trabajofinalid);
	
	@Query(value="SELECT TB1.* FROM TRABAJOSFINALES_USUARIOALIANZA",nativeQuery = true)
	List<TrabajosfinalesUsuarioAlianza> listarAll();
	
	@Query(value="SELECT TB1.* FROM TRABAJOSFINALES_USUARIOALIANZA TB1 WHERE TB1.trabajosfinalesid = ?1 and TB1.usuarioalianzaid=?2",nativeQuery = true)
	TrabajosfinalesUsuarioAlianza buscar(Integer trabajofinalid, Integer usuarioalianzaid);
	
	@Query(value="SELECT TB1.* FROM TRABAJOSFINALES_USUARIOALIANZA TB1 WHERE TB1.usuarioalianzaid=?1",nativeQuery = true)
	List<TrabajosfinalesUsuarioAlianza> listaTrabajosIdByUsuarioId(Integer usuarioalianzaid);
}
