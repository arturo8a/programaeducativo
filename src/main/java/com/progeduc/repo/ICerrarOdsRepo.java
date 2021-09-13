package com.progeduc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.CerrarOds;

@Repository
public interface ICerrarOdsRepo extends CrudRepository<CerrarOds,Integer>{
	
	@Query(value="SELECT * from cerrar_ods where odsid=?1 and anio = EXTRACT(YEAR FROM sysdate)",nativeQuery = true)
	CerrarOds buscarPorOdsAnioactual(Integer odsid);
}
