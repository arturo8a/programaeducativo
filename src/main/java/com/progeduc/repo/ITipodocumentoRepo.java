package com.progeduc.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.Tipodocumento;


@Repository
public interface ITipodocumentoRepo extends CrudRepository<Tipodocumento,Integer>{	
	
	
}
