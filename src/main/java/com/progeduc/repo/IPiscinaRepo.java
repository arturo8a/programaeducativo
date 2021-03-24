package com.progeduc.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.Piscina;

@Repository
public interface IPiscinaRepo extends CrudRepository<Piscina,Integer>{
	
	

}
