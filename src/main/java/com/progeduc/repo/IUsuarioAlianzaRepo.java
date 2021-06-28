package com.progeduc.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.progeduc.model.UsuarioAlianza;

@Repository
@Transactional(readOnly = true)
public interface IUsuarioAlianzaRepo extends CrudRepository<UsuarioAlianza,Integer>{

}
