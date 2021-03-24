package com.progeduc.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.Generoprof;

@Repository
public interface IGeneroprofRepo extends CrudRepository<Generoprof,Integer>{

}
