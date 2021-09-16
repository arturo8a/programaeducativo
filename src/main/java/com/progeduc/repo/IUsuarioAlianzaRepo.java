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
	
	@Query(value="SELECT TB1.* FROM USUARIO_ALIANZA TB1 WHERE TB1.odsid = ?1 ",nativeQuery = true)
	List<UsuarioAlianza> listarByOds(Integer odsid);
	
	@Query(value="SELECT U.* FROM USUARIO_ALIANZA U WHERE U.usuario_autoridad = ?1 and U.password_autoridad=?2 and U.estado=1",nativeQuery = true)
	UsuarioAlianza getUsuarioEvaluador(String usuario, String password);
	
	@Query(value="select U.* from USUARIO_ALIANZA U where U.odsid like %?1% and U.anio like %?2% and U.estado like %?3% and U.COMITE_TECNICO like %?4%  and U.COMITE_EVALUADOR like %?5%  and U.AUSPICIADOR like %?6%  and U.ALIADO like %?7%   ",nativeQuery = true)
	List<UsuarioAlianza> listaUsuarioFiltro(String ods, String anio, String estado, String role6, String role7, String role8, String role9);
	
	@Query(value="select U.* from USUARIO_ALIANZA U where U.usuario_autoridad=?1 and U.anio = EXTRACT(YEAR FROM sysdate)",nativeQuery = true)
	List<UsuarioAlianza> buscarEvaluador(String usuarioEvaluador);
	
}
