package com.progeduc.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.progeduc.model.Questionario;

@Repository
public interface IQuestionarioRepo extends CrudRepository<Questionario,Integer>{
	
	@Transactional
	@Modifying	
	@Query("update Questionario p set p.estado = ?2 WHERE p.id = ?1")
	int updateestado(@Param("id") Integer id, @Param("estado") Integer estado);
}
