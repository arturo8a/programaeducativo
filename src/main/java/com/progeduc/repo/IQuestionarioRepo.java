package com.progeduc.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.Questionario;

@Repository
public interface IQuestionarioRepo extends CrudRepository<Questionario,Integer>{
}
