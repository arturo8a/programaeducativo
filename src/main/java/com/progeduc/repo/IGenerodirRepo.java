package com.progeduc.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.Generodir;

@Repository
public interface IGenerodirRepo extends CrudRepository<Generodir,Integer>{

}
