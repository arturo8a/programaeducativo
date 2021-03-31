package com.progeduc.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.progeduc.model.Parentesco;

@Repository
@Transactional(readOnly = true)
public interface IParentescoRepo  extends CrudRepository<Parentesco,Integer>{

}
