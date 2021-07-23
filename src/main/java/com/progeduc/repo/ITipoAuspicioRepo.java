package com.progeduc.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.TipoAuspicio;

@Repository
public interface ITipoAuspicioRepo extends CrudRepository<TipoAuspicio,Integer>{
	

}
