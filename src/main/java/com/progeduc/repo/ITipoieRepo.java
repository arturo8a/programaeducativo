package com.progeduc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.Tipoie;

@Repository
public interface ITipoieRepo extends CrudRepository<Tipoie,Integer>{
	
	@Query(value="SELECT T.* FROM TIPOIE T WHERE T.ID<=4",nativeQuery = true)
	List<Tipoie> listTie();
	
}
