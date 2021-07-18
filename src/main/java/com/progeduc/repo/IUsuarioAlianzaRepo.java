package com.progeduc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.progeduc.model.Usuario;
import com.progeduc.model.UsuarioAlianza;

@Repository
@Transactional(readOnly = true)
public interface IUsuarioAlianzaRepo extends CrudRepository<UsuarioAlianza,Integer>{
	
	@Query(value="SELECT TB1.* FROM USUARIO_ALIANZA TB1 WHERE TB1.odsid = ?1 and tb1.estado=1",nativeQuery = true)
	List<UsuarioAlianza> listarByOds(Integer odsid);
	
	@Query(value="SELECT U.* FROM USUARIO_ALIANZA U WHERE U.usuario_autoridad = ?1 and U.password_autoridad=?2 and U.estado=1",nativeQuery = true)
	UsuarioAlianza getUsuarioEvaluador(String usuario, String password);
	
}
