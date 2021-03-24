package com.progeduc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.progeduc.model.Distrito;
import com.progeduc.model.Provincia;

@Repository
public interface IDistritoRepo extends CrudRepository<Distrito,Integer>{
	
	
	@Query(value="SELECT D.* FROM DISTRITO D WHERE D.PROVINCIAID=?1",nativeQuery = true)
	List<Distrito> listByProvincia(@Param("id") Integer id);	
	
	Distrito getById(Integer id);
	
	@Query(value="SELECT D.* FROM DISTRITO D WHERE D.ODSID=?1 FETCH FIRST 1 ROWS ONLY",nativeQuery = true)
	Distrito listByOdsid(@Param("id") Integer id);
}
