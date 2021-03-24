package com.progeduc.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.Genero;

@Repository
public interface IGeneroRepo extends CrudRepository<Genero,Integer>{

}
