package com.progeduc.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.Estadotrabajo;

@Repository
public interface IEstadotrabajoRepo extends CrudRepository<Estadotrabajo,Integer>{

}
