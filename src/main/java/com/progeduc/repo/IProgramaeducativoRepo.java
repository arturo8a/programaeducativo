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
import com.progeduc.interfac.AprobacionInscripciones;
import com.progeduc.interfac.ProgeducDto;
import com.progeduc.model.Programaeducativo;

@Repository
@Transactional(readOnly = true)
public interface IProgramaeducativoRepo extends CrudRepository<Programaeducativo,Integer>{
	
	
	@Query(value="SELECT p.* FROM Programaeducativo p where ('true' = (CASE WHEN ?1 =' ' THEN 'true' ELSE (CASE WHEN FECHA_REGISTRO >=to_date(?1,'dd-mm-yyyy') THEN 'true' ELSE 'false' END) END)) and  ('true' = (CASE WHEN ?2 =' ' THEN 'true' ELSE (CASE WHEN FECHA_REGISTRO <=to_date(?2,'dd-mm-yyyy') THEN 'true' ELSE 'false' END) END))  and   ('true'= (CASE WHEN (NOMIE LIKE '%'||?3||'%') THEN 'true' ELSE 'false' END)) and ('true'= (CASE WHEN ?4 ='0'  THEN 'true' ELSE (CASE WHEN DEP=?4 THEN 'true' ELSE 'false' END ) END)) and ('true'= (CASE WHEN ?5 ='0'  THEN 'true' ELSE (CASE WHEN PROV=?5 THEN 'true' ELSE 'false' END ) END))  and ('true'= (CASE WHEN ?6 ='0'  THEN 'true' ELSE (CASE WHEN DISTRITOID=?6 THEN 'true' ELSE 'false' END ) END))",nativeQuery = true)
	List<Programaeducativo> listarfiltro(@Param("fecha_desde") String fecha_desde,@Param("fecha_hasta") String fecha_hasta,@Param("nombreie") String nombreie,@Param("depa") Integer depa,@Param("prov") Integer prov,@Param("dist") Integer dist);
	
	@Query(value="SELECT TB1.* FROM PROGRAMAEDUCATIVO TB1 WHERE TB1.CODMOD = ?1 AND ANHIO = (SELECT MAX(ANHIO) AS max_anio FROM PROGRAMAEDUCATIVO WHERE CODMOD = ?1) FETCH FIRST 1 ROWS ONLY",nativeQuery = true)
	Programaeducativo getCodmod(String codmod);
	
	@Query(value="SELECT TB1.* FROM PROGRAMAEDUCATIVO TB1 WHERE TB1.CODMOD = ?1 AND TB1.ANHIO = EXTRACT(YEAR FROM sysdate) FETCH FIRST 1 ROWS ONLY",nativeQuery = true)
	Programaeducativo getCodmodByAnioActual(String codmod);
	
	@Query(value="SELECT TB1.* FROM PROGRAMAEDUCATIVO TB1 WHERE TB1.CODMOD = ?1 AND TB1.ANHIO = EXTRACT(YEAR FROM sysdate)",nativeQuery = true)
	List<Programaeducativo> listaCodmodByAnioActual(String codmod);
	
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
	
	@Query(value="select p.id,o.descripcion as ods,p.codmod,p.nomie,p.anhio,p.motivoobservacion,p.estado from programaeducativo p"
			+ " inner join distrito d on d.id = p.distritoid "
			+ " inner join ods o on o.id = d.odsid"
			+ " where 't'=(case ?1 when 0 then 't' else ( case ?1 when o.id then 't' else 'f' end) end) "
			+ " and 't'=(case ?2 when 0	then 't' else ( case ?2 when p.anhio then 't' else 'f' end) end) "
			+ " and UPPER(p.nomie) like UPPER(?3) "
			+ " and 't'=(case ?4 when 'Todos' then 't' else( case ?4 when p.estado then 't' else 'f' end ) end) ",nativeQuery = true)
	List<AprobacionInscripciones> listarAprobacionInscripcion(Integer idods,Integer anio,String nombre,String estado);
	
	
	@Query(value="select p.id , o.descripcion as ods,dep.descripcion as departamento,pr.descripcion as provincia,d.descripcion as distrito,p.nomie as insteduc,"
			+ " p.codmod as codlocalie, to_date(to_char(p.fecha_registro,'DD/MM/YYYY')) as fecharegistro, a.descripcion as ambito, me.descripcion as modensenianza,  t.descripcion as tipoieid,"
			+ " p.dirie , p.dre, p.ugel, p.telfie,p.mailie , p.facebook, l.descripcion as lengua, g.descripcion as genero, pro.descripcion as proveedor, "
			+ " p.abastecimiento as horaabastecimiento, pi.descripcion as piscina , tdd.descripcion as tipodocdir, p.docdir as nrodocidentdir, p.apedir, p.nomdir,"
			+ " gd.descripcion as generodir, p.telfdir as teldir, p.celdir, p.maildir as correodir, tdp.descripcion as tipodocprof,p.docprof as nrodocidentprof,p.apeprof,p.nomprof,"
			+ " gp.descripcion as generoprof,p.telfprof as telprof, p.celprof,p.mailprof as correoprof,p.anhio as anio,p.concurso"
			+ " from programaeducativo p "
			+ " left join distrito d on d.id = p.distritoid"
			+ " left join provincia pr on pr.id = d.provinciaid"
			+ " left join departamento dep on dep.id = pr.departamentoid"
			+ " left join ods o on o.id = d.odsid"
			+ " left join usuarioods uo on uo.odsid=o.id"
			+ " left join usuario u on u.id = uo.usuarioid"
			+ " left join ambito a on a.id = p.ambitoid"
			+ " left join modensenianza me on me.id = p.modensenianzaid"
			+ " left join tipoie t on t.id = tipoieid"
			+ " left join lengua l on l.id = p.lenguaid"
			+ " left join genero g on g.id = p.generoid"
			+ " left join proveedorservicio pro on pro.id = p.proveedorid"
			+ " left join piscina pi on pi.id = p.piscinaid"
			+ " left join tipodocidentdir tdd on tdd.id = p.tipodocidentdirid"
			+ " left join generodir gd on gd.id = p.generodirid"
			+ " left join tipodocidentprof tdp on tdp.id = p.tipodocidentprofid"
			+ " left join generoprof gp on gp.id = p.generoprofid"
			+ " where 't' = (case ?1 when '-1' then 't' else( case ?1  when u.usuario then 't' else 'f' end) end)"
			+ " and 't'=(case ?2 when 'todos' then 't' else ( case when to_date(?2)<= to_date(to_char(p.fecha_registro,'DD/MM/YYYY')) then 't' else 'f' end) end)" 
			+ " and 't'=(case ?3 when 'todos' then 't' else ( case when to_date(?3) >=to_date(to_char(p.fecha_registro,'DD/MM/YYYY')) then 't' else 'f' end) end)"
			+ " and UPPER(p.nomie) like UPPER(?4)"
			+ " and 't'=(case ?5 when 0 then 't' else (case ?5 when dep.id then 't' else 'f' end) end)"
			+ " and 't'=(case ?6 when 0 then 't' else (case ?6 when pr.id then 't' else 'f' end) end)"
			+ " and 't'=(case ?7 when 0 then 't' else (case ?7 when d.id then 't' else 'f' end) end)"
			+ " and 't' = (case ?8 when 2 then 't' else (case ?8 when p.concurso then 't' else 'f' end)end) "
			+ " and p.fecha_registro is not null and p.anhio is not null"
			+ " group by o.descripcion,dep.descripcion,pr.descripcion,d.descripcion ,p.nomie," 
			+ " p.codmod , p.fecha_registro , a.descripcion , me.descripcion , p.id, t.descripcion," 
			+ " p.dirie , p.dre, p.ugel, p.telfie,p.mailie , p.facebook, l.descripcion, g.descripcion, pro.descripcion," 
			+ " p.abastecimiento , pi.descripcion , tdd.descripcion, p.docdir, p.apedir, p.nomdir," 
			+ " gd.descripcion , p.telfdir , p.celdir, p.maildir, tdp.descripcion,p.docprof,p.apeprof,p.nomprof,"  
			+ " gp.descripcion ,p.telfprof, p.celprof,p.mailprof,p.anhio,p.concurso"
			+ " order by p.fecha_registro desc",nativeQuery = true)
	List<ProgeducDto> listarConsultaPe(String usuario,String fechaDesde,String fechaHasta,String nombreie,Integer idDepartamento,Integer idProvincia,Integer idDistrito,Integer inscritoce);
	
	
	
	@Query(value="SELECT TB1.* FROM Programaeducativo TB1",nativeQuery = true)
	List<Programaeducativo> listarTodos();
	
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
	@Query("update Programaeducativo p set p.concurso= ?2  WHERE p.id = ?1")
	int updateConcurso(Integer idpe,Integer valor);	
	
	
	@Transactional
	@Modifying	
	@Query("update Programaeducativo p set p.estado = ?2 WHERE p.id = ?1")
	int updateestadoaprobar(@Param("id") Integer id, @Param("estado") String estado);	
	
	@Query(value="SELECT TB1.* FROM PROGRAMAEDUCATIVO TB1 WHERE TB1.CODMOD=?1 AND TB1.ANHIO =?2 AND TB1.ESTADO =?3",nativeQuery = true)
	Programaeducativo verificarEstadoAnio(String codmod,Integer anio, String estado);
	
	@Query(value="SELECT TB1.* FROM Programaeducativo TB1 where TB1.distritoid=?1 and TB1.anhio is not null and TB1.ANHIO = ?2",nativeQuery = true)
	List<Programaeducativo> listarPorAnio(Integer iddistrito, Integer anio);
	
	@Query(value="SELECT TB1.* FROM PROGRAMAEDUCATIVO TB1 where TB1.ANHIO = ?1 and TB1.estado='Aprobado'",nativeQuery = true)
	List<Programaeducativo> getListarHabilitadosPorAnio(Integer anio);
	
	@Query(value="SELECT TB1.* FROM PROGRAMAEDUCATIVO TB1 where TB1.estado='Aprobado'",nativeQuery = true)
	List<Programaeducativo> getListarHabilitados();
	
}