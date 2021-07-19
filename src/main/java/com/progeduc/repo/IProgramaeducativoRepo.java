package com.progeduc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.progeduc.dto.ColegioDto;
import com.progeduc.dto.ListaInstitucionEducativa;
import com.progeduc.model.Programaeducativo;

@Repository
@Transactional(readOnly = true)
public interface IProgramaeducativoRepo extends CrudRepository<Programaeducativo,Integer>{
	
	
	@Query(value="SELECT p.* FROM Programaeducativo p where ('true' = (CASE WHEN ?1 =' ' THEN 'true' ELSE (CASE WHEN FECHA_REGISTRO >=to_date(?1,'dd-mm-yyyy') THEN 'true' ELSE 'false' END) END)) and  ('true' = (CASE WHEN ?2 =' ' THEN 'true' ELSE (CASE WHEN FECHA_REGISTRO <=to_date(?2,'dd-mm-yyyy') THEN 'true' ELSE 'false' END) END))  and   ('true'= (CASE WHEN (NOMIE LIKE '%'||?3||'%') THEN 'true' ELSE 'false' END)) and ('true'= (CASE WHEN ?4 ='0'  THEN 'true' ELSE (CASE WHEN DEP=?4 THEN 'true' ELSE 'false' END ) END)) and ('true'= (CASE WHEN ?5 ='0'  THEN 'true' ELSE (CASE WHEN PROV=?5 THEN 'true' ELSE 'false' END ) END))  and ('true'= (CASE WHEN ?6 ='0'  THEN 'true' ELSE (CASE WHEN DISTRITOID=?6 THEN 'true' ELSE 'false' END ) END))",nativeQuery = true)
	List<Programaeducativo> listarfiltro(@Param("fecha_desde") String fecha_desde,@Param("fecha_hasta") String fecha_hasta,@Param("nombreie") String nombreie,@Param("depa") Integer depa,@Param("prov") Integer prov,@Param("dist") Integer dist);
	
	/*@Query(value="SELECT p.* FROM Programaeducativo p where 'true'= (CASE WHEN (NOMIE LIKE '%'||?1||'%') THEN 'true' ELSE 'false' END)",nativeQuery = true)
	List<Programaeducativo> listarfiltro(@Param("id") String nombreie);*/
	
	@Query(value="SELECT TB1.* FROM PROGRAMAEDUCATIVO TB1 WHERE TB1.CODMOD = ?1 AND ANHIO = (SELECT MAX(ANHIO) AS max_anio FROM PROGRAMAEDUCATIVO WHERE CODMOD = ?1) FETCH FIRST 1 ROWS ONLY",nativeQuery = true)
	Programaeducativo getCodmod(String codmod);
	
	@Query(value="SELECT TB1.* FROM PROGRAMAEDUCATIVO TB1 WHERE TB1.CODMOD = ?1 AND TB1.ANHIO = EXTRACT(YEAR FROM sysdate) FETCH FIRST 1 ROWS ONLY",nativeQuery = true)
	Programaeducativo getCodmodByAnioActual(String codmod);
	
	@Query(value="SELECT TB1.* FROM PROGRAMAEDUCATIVO TB1 where TB1.ANHIO = EXTRACT(YEAR FROM sysdate) and TB1.estado='Aprobado'",nativeQuery = true)
	List<Programaeducativo> getListarHabilitadosAnioActual();
	
	@Query(value="SELECT TB1.* FROM PROGRAMAEDUCATIVO TB1 WHERE TB1.CODMOD = ?1 and TB1.ANHIO = EXTRACT(YEAR FROM sysdate)",nativeQuery = true)
	Programaeducativo getActualByCodmod(String codmod);
	
	@Query(value="SELECT CODMOD FROM Programaeducativo WHERE CODMOD IS NOT NULL GROUP BY CODMOD",nativeQuery = true)
	List<String> listCentrosEducativosGroupbyCodmod();
	
	@Query(value="SELECT CODMOD as codmod,NOMIE as nomie FROM Programaeducativo p WHERE CODMOD IS NOT NULL GROUP BY CODMOD,NOMIE order by nomie",nativeQuery = true)
	List<ColegioDto> listCentrosEducativosGroupbyNomie();
	
	@Query(value="SELECT TB1.*,TB1.ANHIO FROM Programaeducativo TB1",nativeQuery = true)
	List<Programaeducativo> listCentrosEducativosInscritos();
	
	@Query(value="SELECT TB1.* FROM Programaeducativo TB1 where tb1.fecha_registro is not null and TB1.anhio is not null order by fecha_registro desc",nativeQuery = true)
	List<Programaeducativo> listar();
	
	@Query(value="SELECT TB1.* FROM Programaeducativo TB1 where tb1.estado='Aprobado' and tb1.fecha_registro is not null and TB1.anhio is not null order by fecha_registro desc",nativeQuery = true)
	List<Programaeducativo> listarAprobados();
	
	@Query(value="SELECT TB1.* FROM Programaeducativo TB1 where /*tb1.fecha_registro is not null and*/ TB1.distritoid=?1 and TB1.anhio is not null",nativeQuery = true)
	List<Programaeducativo> listar(Integer iddistrito);
	
	@Query(value="SELECT TB1.CODMOD,TB1.ANHIO,TB1.NOMIE,TB1.ID,TB1.ESTADO,MOTIVOOBSERVACION FROM Programaeducativo TB1",nativeQuery = true)
	List<ListaInstitucionEducativa> listar1();
	
	@Transactional
	@Modifying	
	@Query("update Programaeducativo p set p.estado = ?2 , p.motivoobservacion = ?3  WHERE p.id = ?1")
	int updateestado(@Param("id") Integer id, @Param("estado") String estado, @Param("motivoobservacion") String motivoobservacion);	
	
	@Transactional
	@Modifying	
	@Query("update Programaeducativo p set p.estado = ?2 WHERE p.id = ?1")
	int updateestadoaprobar(@Param("id") Integer id, @Param("estado") String estado);	
	
	@Query(value="SELECT TB1.* FROM PROGRAMAEDUCATIVO TB1 WHERE TB1.CODMOD=?1 AND TB1.ANHIO =?2 AND TB1.ESTADO =?3",nativeQuery = true)
	Programaeducativo verificarEstadoAnio(String codmod,Integer anio, String estado);
	
}