package com.progeduc.repo;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.progeduc.model.Usuario;
import com.progeduc.model.Usuario_Ods;

@Repository
@Transactional(readOnly = true)
public interface IUsuarioRepo extends CrudRepository<Usuario,Integer>{
	
	@Query(value="SELECT U.* FROM USUARIO U WHERE U.USUARIO=?1 AND U.PASSWORD=?2 and u.estado=1",nativeQuery = true)
	Usuario login(@Param("usuario") String usuario,@Param("password") String password);	
	
	@Query(value="SELECT U.* FROM USUARIO U WHERE U.USUARIO=?1",nativeQuery = true)
	Usuario byUsuario(@Param("usuario") String usuario);
	
	@Query(value="SELECT U.* FROM USUARIO U WHERE U.USUARIO=?1  ",nativeQuery = true)
	Usuario verificarexistenciausuario(@Param("usuario") String usuario);
	
	@Transactional
	@Modifying	
	@Query("update Usuario p set p.password = ?2 WHERE p.id = ?1")
	int updatecontrasenia(@Param("id") Integer id, @Param("password") String password);
	

	@Transactional
	@Modifying	
	@Query("update Usuario p set p.estado = 0 WHERE p.usuario = ?1")
	int estadoEliminar(@Param("usuario") String usuario);
	
	@Transactional
	@Modifying	
	@Query("update Usuario p set p.estado = 1 WHERE p.usuario = ?1")
	int estadoActivar(@Param("usuario") String usuario);

}
