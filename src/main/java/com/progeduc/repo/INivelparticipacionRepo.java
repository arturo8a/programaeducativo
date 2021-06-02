package com.progeduc.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.Nivelparticipacion;

@Repository
public interface INivelparticipacionRepo extends CrudRepository<Nivelparticipacion,Integer>{

}
