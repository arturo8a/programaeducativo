package com.progeduc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.Departamento;

@Repository
public interface IDepartamentoRepo extends JpaRepository <Departamento,Integer>{

}
