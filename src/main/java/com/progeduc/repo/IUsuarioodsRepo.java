package com.progeduc.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.progeduc.model.UsuarioOds;

@Repository
public interface IUsuarioodsRepo extends CrudRepository<UsuarioOds,Integer>{
	
	@Query(value="SELECT U.* FROM USUARIO_ODS U WHERE U.IDDEPARTAMENTO=?1",nativeQuery = true)
	UsuarioOds byDepartamento(@Param("iddepartamento") Integer iddepartamento);	
	
	@Query(value="SELECT U.* FROM USUARIO_ODS U WHERE U.USUARIO=?1 AND U.PASSWORD=?2 ",nativeQuery = true)
	UsuarioOds login(@Param("usuario") String usuario,@Param("password") String password);	
	
	@Query(value="SELECT U.* FROM USUARIO_ODS U WHERE U.CORREO=?1  ",nativeQuery = true)
	UsuarioOds verificarexistenciacorreo(@Param("correo") String correo);	
	
	@Transactional
	@Modifying	
	@Query("update UsuarioOds p set p.password = ?2 WHERE p.id = ?1")
	int updatecontrasenia(@Param("id") Integer id, @Param("contrasenia") String contrasenia);
	
}
