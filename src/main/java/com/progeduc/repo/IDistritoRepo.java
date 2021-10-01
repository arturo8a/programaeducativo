package com.progeduc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.progeduc.interfac.AprobacionInscripciones;
import com.progeduc.model.Distrito;

@Repository
public interface IDistritoRepo extends CrudRepository<Distrito,Integer>{
	
	
	@Query(value="SELECT D.* FROM DISTRITO D WHERE D.PROVINCIAID=?1",nativeQuery = true)
	List<Distrito> listByProvincia(@Param("id") Integer id);	
	
	Distrito getById(Integer id);
	
	@Query(value="select p.id,o.descripcion,p.codmod,p.nomie,p.anhio,p.motivoobservacion from programaeducativo p"
			+ " inner join distrito d on d.id = p.distritoid "
			+ " inner join ods o on o.id = d.odsid"
			+ " where d.odsid = ?1 and  't'=(case ?2 when 0	then 't' else (case ?2 when p.anhio then 't' else 'f' end) end) ",nativeQuery = true)
	List<AprobacionInscripciones> listByOdsid(Integer iddistrito, Integer anio);
	
	@Query(value="SELECT D.* FROM DISTRITO D WHERE D.ODSID=?1",nativeQuery = true)
	List<Distrito> listByOdsid(@Param("id") Integer id);
}


/*and (case ?2 when 0 then true else p.anhio=?2 end)*/