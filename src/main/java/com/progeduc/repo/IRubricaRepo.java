package com.progeduc.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.Rubrica;

@Repository
public interface IRubricaRepo extends CrudRepository<Rubrica,Integer>{
	
	
}
