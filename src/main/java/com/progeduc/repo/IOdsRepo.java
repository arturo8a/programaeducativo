package com.progeduc.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.progeduc.model.Ods;

public interface IOdsRepo extends CrudRepository<Ods,Integer>{
	
	@Query(value="SELECT U.* FROM ODS U WHERE U.ID=?1",nativeQuery = true)
	Ods byOds(@Param("id") int id);	
}
