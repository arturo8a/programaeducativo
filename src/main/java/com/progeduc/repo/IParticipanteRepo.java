package com.progeduc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.progeduc.model.Participante;

@Repository
@Transactional(readOnly = true)
public interface IParticipanteRepo extends CrudRepository<Participante,Integer>{
	
	@Transactional
	@Modifying	
	@Query("update Participante p set p.estado = ?2 WHERE p.id = ?1")
	int updateestado(@Param("id") Integer id, @Param("estado") Integer estado);	
	
	@Query(value="SELECT tb1.* FROM Participante tb1 where tb1.programaeducativoid=?1 and tb1.estado=1",nativeQuery = true)
	List<Participante> listarhabilitados(Integer programaeducativoid);
	
	@Query(value="SELECT TB1.* FROM Participante PA where ?1 ?2 ?3 and tb1.estado=1",nativeQuery = true)
	List<Participante> listarReporte(String filtro_categoria,String filtro_modalidad,String filtro_nivel_participante);
	
	@Query(value="SELECT TB1.* FROM Participante TB1 where tb1.estado=1",nativeQuery = true)
	List<Participante> listarhabilitados();
	
}
