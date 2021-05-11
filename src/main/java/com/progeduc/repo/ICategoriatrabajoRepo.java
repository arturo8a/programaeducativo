package com.progeduc.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.Categoriatrabajo;

@Repository
public interface ICategoriatrabajoRepo extends CrudRepository<Categoriatrabajo,Integer>{

}
