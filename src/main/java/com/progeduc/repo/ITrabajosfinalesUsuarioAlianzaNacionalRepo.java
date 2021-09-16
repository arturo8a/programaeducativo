package com.progeduc.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.dto.CategoriaModalidadByOds;
import com.progeduc.model.TrabajosfinalesUsuarioAlianza;
import com.progeduc.model.TrabajosfinalesUsuarioAlianzaNacional;

@Repository
public interface ITrabajosfinalesUsuarioAlianzaNacionalRepo extends CrudRepository<TrabajosfinalesUsuarioAlianzaNacional,Integer> {
	
	@Transactional
	@Modifying
	@Query(value="INSERT INTO TRABAJOS_USUARIOALIANZA_NA(TRABAJOSFINALESID,USUARIOALIANZAID,NOTA) VALUES(?1,?2,?3)",nativeQuery = true)
	Integer guardar(Integer trabajosfinalesid, Integer usuarioalianzaid, Float nota);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM TRABAJOS_USUARIOALIANZA_NA WHERE TRABAJOSFINALESID = ?1",nativeQuery = true)
	Integer eliminar(Integer trabajosfinalesid);
	
	@Query(value="SELECT TB1.* FROM TRABAJOS_USUARIOALIANZA_NA TB1 WHERE TB1.trabajosfinalesid = ?1",nativeQuery = true)
	List<TrabajosfinalesUsuarioAlianzaNacional> listarByTrabajosfinalesId(Integer trabajofinalid);
	
	@Query(value="SELECT TB1.* FROM TRABAJOS_USUARIOALIANZA_NA",nativeQuery = true)
	List<TrabajosfinalesUsuarioAlianzaNacional> listarAll();
	
	@Query(value="SELECT TB1.* FROM TRABAJOS_USUARIOALIANZA_NA TB1 WHERE TB1.trabajosfinalesid = ?1 and TB1.usuarioalianzaid=?2",nativeQuery = true)
	TrabajosfinalesUsuarioAlianzaNacional buscar(Integer trabajofinalid, Integer usuarioalianzaid);
	
	@Query(value="SELECT TB1.* FROM TRABAJOS_USUARIOALIANZA_NA TB1 WHERE TB1.usuarioalianzaid=?1",nativeQuery = true)
	List<TrabajosfinalesUsuarioAlianzaNacional> listaTrabajosIdByUsuarioId(Integer usuarioalianzaid);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE TRABAJOS_USUARIOALIANZA_NA SET NOTA=?3 WHERE trabajosfinalesid = ?1 and usuarioalianzaid=?2",nativeQuery = true)
	Integer actualizarNotaPorParticiante(Integer trabajofinalid, Integer usuarioalianzaid, Float nota);
	
	@Query(value="SELECT TB1.* FROM TRABAJOS_USUARIOALIANZA_NA TB1 WHERE TB1.trabajosfinalesid = ?1 AND TB1.nota=-1",nativeQuery = true)
	List<TrabajosfinalesUsuarioAlianzaNacional> listarTrabajosFinalesSinNota(Integer trabajofinalid);
	
	@Query(value="select tf.categoriatrabajoid, tf.modalidadtrabajoid from trabajosfinales tf "
			+ "inner join programaeducativo pe on tf.programaeducativoid = pe.id "
			+ "inner join distrito d on pe.distritoid = d.id "
			+ "inner join ods o on d.odsid = o.id "
			+ "where tf.nota is not null and o.id=?1 and tf.anio = EXTRACT(YEAR FROM sysdate) "
			+ "group by tf.categoriatrabajoid, tf.modalidadtrabajoid",nativeQuery = true)
	List<CategoriaModalidadByOds> listarCategoriaModalidadByOds(Integer odsId);
	
	/*@Query(value="SELECT TB1.* FROM TRABAJOS_USUARIOALIANZA_NA TB1 WHERE TB1.trabajosfinalesid = ?1 AND TB1.nota=-1",nativeQuery = true)
	List<TrabajosfinalesUsuarioAlianza> listarTrabajosFinalesConNotaPromedioPorCategoriaPorModalidad(Integer categoiraId, Integer modalidadId);*/
}
