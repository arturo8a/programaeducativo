package com.progeduc.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.Responsableregistro;

@Repository
public interface IResponsableregistroRepo extends CrudRepository<Responsableregistro,Integer>{

}
