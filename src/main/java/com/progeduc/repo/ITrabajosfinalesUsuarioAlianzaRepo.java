package com.progeduc.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.dto.CategoriaModalidadByOds;
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
	
	@Transactional
	@Modifying
	@Query(value="UPDATE TRABAJOSFINALES_USUARIOALIANZA SET NOTA=?3 WHERE trabajosfinalesid = ?1 and usuarioalianzaid=?2",nativeQuery = true)
	Integer actualizarNotaPorParticiante(Integer trabajofinalid, Integer usuarioalianzaid, Float nota);
	
	@Query(value="SELECT TB1.* FROM TRABAJOSFINALES_USUARIOALIANZA TB1 WHERE TB1.trabajosfinalesid = ?1 AND TB1.nota=-1",nativeQuery = true)
	List<TrabajosfinalesUsuarioAlianza> listarTrabajosFinalesSinNota(Integer trabajofinalid);
	
	@Query(value="select tf.categoriatrabajoid, tf.modalidadtrabajoid from trabajosfinales tf "
			+ "inner join programaeducativo pe on tf.programaeducativoid = pe.id "
			+ "inner join distrito d on pe.distritoid = d.id "
			+ "inner join ods o on d.odsid = o.id "
			+ "where tf.nota is not null and o.id=?1 and tf.anio = EXTRACT(YEAR FROM sysdate) "
			+ "group by tf.categoriatrabajoid, tf.modalidadtrabajoid",nativeQuery = true)
	List<CategoriaModalidadByOds> listarCategoriaModalidadByOds(Integer odsId);
	
	/*@Query(value="SELECT TB1.* FROM TRABAJOSFINALES_USUARIOALIANZA TB1 WHERE TB1.trabajosfinalesid = ?1 AND TB1.nota=-1",nativeQuery = true)
	List<TrabajosfinalesUsuarioAlianza> listarTrabajosFinalesConNotaPromedioPorCategoriaPorModalidad(Integer categoiraId, Integer modalidadId);*/
}
