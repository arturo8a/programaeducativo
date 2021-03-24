package com.progeduc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.progeduc.model.Provincia;

@Repository
public interface IProvinciaRepo extends JpaRepository<Provincia,Integer>{
	
	@Query(value="SELECT * FROM PROVINCIA P WHERE P.DEPARTAMENTOID=?1",nativeQuery = true)
	List<Provincia> listProvinciaByDepa(@Param("id") Integer id);	
	
	Provincia getById(Integer id);
}
