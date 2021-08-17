package com.progeduc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.progeduc.model.Ods;

public interface IOdsRepo extends CrudRepository<Ods,Integer>{
	
	@Query(value="SELECT U.* FROM ODS U WHERE U.ID=?1",nativeQuery = true)
	Ods byOds(@Param("id") int id);	
	
	@Query(value="select od.* from ods od where od.id>0 order by od.descripcion",nativeQuery = true)
	List<Ods> listarAll();
	
	@Query(value="select o.* from trabajosfinales tf "
			+ "inner join programaeducativo pe "
			+ "on tf.programaeducativoid = pe.id "
			+ "inner join distrito d "
			+ "on pe.distritoid = d.id "
			+ "inner join ods o "
			+ "on d.odsid = o.id "
			+ "where tf.nota is not null group by o.id, o.desc_ods, o.descripcion order by o.descripcion",nativeQuery = true)
	List<Ods> listarOdsDeTrabajosEvaluados();
	
	@Query(value="select o.* from cerrar_ods co "
			+ "inner join ods o "
			+ "on co.odsid = o.id  "
			+ "where co.estado = '2' ",nativeQuery = true)
	List<Ods> listarOdsEmpatadas();
	
	
}
