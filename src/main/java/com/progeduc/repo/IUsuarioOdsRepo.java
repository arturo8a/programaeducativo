package com.progeduc.repo;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.UsuarioOds;

@Repository
public interface IUsuarioOdsRepo extends CrudRepository<UsuarioOds,Integer>{
	
	@Transactional
	@Modifying
	@Query(value="INSERT INTO USUARIOODS(USUARIOID,ODSID) VALUES(?1,?2)",nativeQuery = true)
	Integer guardar(Integer usuarioid, Integer odsid);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM USUARIOODS WHERE USUARIOID = ?1",nativeQuery = true)
	Integer eliminarByUsuarioID(Integer usuarioid);
	
	@Query(value="SELECT TB1.* FROM USUARIOODS TB1 WHERE TB1.USUARIOID = ?1 ",nativeQuery = true)
	List<UsuarioOds> listarByUsuario(Integer usuarioid);
	
}
