package com.progeduc.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.Nivel;

@Repository
public interface INivelRepo extends CrudRepository<Nivel,Integer>{

}
