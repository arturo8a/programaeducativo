package com.progeduc.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.Lengua;

@Repository
public interface ILenguaRepo extends CrudRepository<Lengua,Integer>{
	
}
