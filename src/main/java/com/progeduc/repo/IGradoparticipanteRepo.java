package com.progeduc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.Gradoparticipante;


@Repository
public interface IGradoparticipanteRepo extends CrudRepository<Gradoparticipante,Integer>{
	
	@Query(value="SELECT TB1.* FROM GRADOPARTICIPANTE TB1 WHERE TB1.nivelparticipanteid = ?1",nativeQuery = true)
	List<Gradoparticipante> listargradopornivel(Integer idnivel);
	
	@Query(value="SELECT TB1.* FROM GRADOPARTICIPANTE TB1 WHERE TB1.id in (1,7,15)",nativeQuery = true)
	List<Gradoparticipante> listarParaCierre();
	
}
