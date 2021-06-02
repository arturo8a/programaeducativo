package com.progeduc.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.progeduc.model.Usuario_Ods;

@Repository
public interface IUsuario_odsRepo extends CrudRepository<Usuario_Ods,Integer>{
	
	@Query(value="SELECT U.* FROM USUARIO_ODS U WHERE U.IDDEPARTAMENTO=?1",nativeQuery = true)
	Usuario_Ods byDepartamento(@Param("iddepartamento") Integer iddepartamento);	
	
	@Query(value="SELECT U.* FROM USUARIO_ODS U WHERE U.USUARIO=?1 AND U.PASSWORD=?2 ",nativeQuery = true)
	Usuario_Ods login(@Param("usuario") String usuario,@Param("password") String password);	
	
	@Query(value="SELECT U.* FROM USUARIO_ODS U WHERE U.CORREO=?1  ",nativeQuery = true)
	Usuario_Ods verificarexistenciacorreo(@Param("correo") String correo);	
	
	@Transactional
	@Modifying	
	@Query("update Usuario_Ods p set p.password = ?2 WHERE p.id = ?1")
	int updatecontrasenia(@Param("id") Integer id, @Param("contrasenia") String contrasenia);
	
}
