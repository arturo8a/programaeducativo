package com.progeduc.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.Categoria;

@Repository
public interface ICategoriaRepo extends CrudRepository<Categoria,Integer>{

}
