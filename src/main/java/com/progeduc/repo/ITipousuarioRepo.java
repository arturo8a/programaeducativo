package com.progeduc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.progeduc.model.Tipousuario;

public interface ITipousuarioRepo extends CrudRepository<Tipousuario,Integer>{
	
	@Query(value="SELECT U.* FROM TIPOUSUARIO U WHERE U.ID=?1",nativeQuery = true)
	Tipousuario byTipousuario(@Param("id") int id);
	
	@Query(value="SELECT U.* FROM TIPOUSUARIO U where id in(1,2,11) order by orden asc",nativeQuery = true)
	List<Tipousuario> lista();
}