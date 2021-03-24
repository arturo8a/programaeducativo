package com.progeduc.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.Tipodocidentprof;


@Repository
public interface ITipodocRepo extends CrudRepository<Tipodocidentprof,Integer>{

}
