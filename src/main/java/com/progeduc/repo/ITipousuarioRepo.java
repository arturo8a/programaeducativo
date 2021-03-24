package com.progeduc.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.progeduc.model.Tipousuario;

public interface ITipousuarioRepo extends CrudRepository<Tipousuario,Integer>{
	
	@Query(value="SELECT U.* FROM TIPOUSUARIO U WHERE U.ID=?1",nativeQuery = true)
	Tipousuario byTipousuario(@Param("id") int id);
}