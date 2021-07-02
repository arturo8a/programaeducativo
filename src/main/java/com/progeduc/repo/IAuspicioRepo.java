package com.progeduc.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.progeduc.model.Auspicio;

@Repository
@Transactional(readOnly = true)
public interface IAuspicioRepo extends CrudRepository<Auspicio,Integer>{
	
	@Transactional
	@Modifying	
	@Query(value="delete from auspicio a where a.usuarioid = ?1",nativeQuery = true)
	void eliminarAuspocioByUsuarioId(@Param("id") Integer id);

}
