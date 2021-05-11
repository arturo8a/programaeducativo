package com.progeduc.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.Modalidadtrabajo;

@Repository
public interface IModalidadtrabajoRepo extends CrudRepository<Modalidadtrabajo,Integer>{

}
