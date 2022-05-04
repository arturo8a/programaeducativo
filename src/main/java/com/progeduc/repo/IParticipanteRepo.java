package com.progeduc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.progeduc.model.Participante;

@Repository
@Transactional(readOnly = true)
public interface IParticipanteRepo extends CrudRepository<Participante,Integer>{
	
	@Transactional
	@Modifying
	@Query("update Participante p set p.estado = ?2 WHERE p.id = ?1")
	int updateestado(@Param("id") Integer id, @Param("estado") Integer estado);	
	
	@Query(value="SELECT tb1.* FROM Participante tb1 where tb1.programaeducativoid=?1 and tb1.tipoparticipante=1 and tb1.estado=1",nativeQuery = true)
	List<Participante> listarhabilitados(Integer programaeducativoid);
	
	@Query(value="SELECT tb1.* FROM Participante tb1 where tb1.programaeducativoid=?1 and tb1.tipoparticipante=2 and tb1.estado=1",nativeQuery = true)
	List<Participante> listarhabilitadosdocentes(Integer programaeducativoid);
	
	@Query(value="SELECT tb1.* FROM Participante tb1 where tb1.programaeducativoid=?1 and tb1.anhio=?2 and tb1.estado=1",nativeQuery = true)
	List<Participante> listarhabilitadosbyanio(Integer programaeducativoid, Integer anio);
	
	@Query(value="SELECT TB1.* FROM Participante PA where ?1 ?2 ?3 and tb1.estado=1",nativeQuery = true)
	List<Participante> listarReporte(String filtro_categoria,String filtro_modalidad,String filtro_nivel_participante);
	
	@Query(value="SELECT TB1.* FROM Participante TB1 where tb1.estado=1",nativeQuery = true)
	List<Participante> listarhabilitados();
	
	@Query(value="SELECT TB1.* FROM Participante TB1 where tb1.tipodocumentoestudiante=?1 and tb1.nrodocumentoestudiante=?2 and tb1.anhio= EXTRACT(YEAR FROM sysdate)",nativeQuery = true)
	List<Participante> buscaTipoNroDocumento(Integer tipoDocumentoid, String nroDocumento);
	
	@Query(value="SELECT TB1.* FROM Participante TB1 where tb1.id!=?1 and tb1.tipodocumentoestudiante=?2 and tb1.nrodocumentoestudiante=?3 and tb1.anhio= EXTRACT(YEAR FROM sysdate)",nativeQuery = true)
	List<Participante> buscaTipoNroDocumento(Integer id, Integer tipoDocumentoid, String nroDocumento);
	
	@Query(value="select " + 
			"  od.desc_ods as ods, " + 
			"  dep.descripcion as departamento, " + 
			"  prov.descripcion as provincia, " + 			
			"  part.appaternoestudiante as apellidopaterno, " + 
			"  part.apmaternoestudiante as apellidomaterno,   " + 
			"  part.nombreestudiante as nombre, " + 
			"  part.nrodocumentoestudiante as nrodni, " + 
			"  pe.codmod as codigoie, " + 
			"  pe.nomie as nombreie, " + 
			"  pe.ugel as nombreugel, " + 
			"  part.fecha_registro " +  
			" from participante part " + 
			" inner join programaeducativo pe on pe.id=part.programaeducativoid " + 
			" inner join tipodocumento td on td.id = part.tipodocumentoestudiante " + 
			" inner join distrito dist on dist.id = pe.distritoid " + 
			" inner join provincia prov on prov.id= dist.provinciaid " + 
			" inner join departamento dep on dep.id = prov.departamentoid " + 
			" inner join ods od on od.id = dist.odsid " + 
			" where "  +
			" 't'=(case ?1 when 0 then 't' else (case ?1 when od.id then 't' else 'f' end) end) and" +
			" 't'=(case ?2 when 0 then 't' else (case ?2 when pe.anhio then 't' else 'f' end) end) and part.estado=1 and part.tipoparticipante=2", nativeQuery=true)
	List<Object[]> buscarDocente(Integer idods,Integer anio);
	
}
