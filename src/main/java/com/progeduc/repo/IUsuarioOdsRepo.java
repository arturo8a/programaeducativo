package com.progeduc.repo;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.interfac.AprobacionInscripciones;
import com.progeduc.interfac.DepartamentoDto;
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
	
	@Query(value="select  p.id,o.descripcion as ods,p.anhio,p.nomie,p.codmod,p.estado,p.motivoobservacion from programaeducativo p"
			+ " inner join distrito d on d.id = p.distritoid"
			+ " inner join ods o on o.id = d.odsid"
			+ " inner join usuarioods uo on uo.odsid = o.id"
			+ " where uo.usuarioid = ?1"
			+ " and 't'=(case ?2 when 0 then 't' else (case ?2 when o.id then 't' else 'f' end) end)"
			+ " and 't'=(case ?3 when 0	then 't' else (case ?3 when p.anhio then 't' else 'f' end) end) "
			+ " and UPPER(p.nomie) like UPPER(?4) "
			+ " and 't'=(case ?5 when 'Todos' then 't' else ( case ?5 when p.estado then 't' else 'f' end ) end) ",nativeQuery = true)
	List<AprobacionInscripciones> listarByUsuarioAprobacionInscripciones(Integer idusuario,Integer idods,Integer anio,String nombre,String estado);
	
	
	@Query(value="select dep.id,dep.descripcion from usuario u "
			+ " inner join usuarioods uo on u.id = uo.usuarioid"
			+ " inner join ods o on o.id = uo.odsid"
			+ " inner join distrito d on d.odsid = o.id"
			+ " inner join provincia p on p.id = d.provinciaid"
			+ " inner join departamento dep on dep.id = p.departamentoid"
			+ " where u.usuario=?1 group by dep.id, dep.descripcion order by dep.descripcion", nativeQuery = true)
	List<DepartamentoDto> listarDepartamentoByUsuario(String usuario);
	
}
