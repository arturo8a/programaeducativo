package com.progeduc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.progeduc.dto.CategoriaModalidadByOds;
import com.progeduc.model.Trabajosfinales;

@Repository
@Transactional(readOnly = true)
public interface ITrabajosfinalesRepo  extends CrudRepository<Trabajosfinales,Integer>{
	
	@Query(value="SELECT TB1.* FROM Trabajosfinales TB1 where TB1.programaeducativoid=?1 and tb1.estado=1 and tb1.anio = EXTRACT(YEAR FROM sysdate)",nativeQuery = true)
	List<Trabajosfinales> listarhabilitados(Integer programaeducativoid);
	
	@Query(value="SELECT TB1.* FROM Trabajosfinales TB1 where TB1.programaeducativoid=?1 and tb1.estado=1 and tb1.anio = EXTRACT(YEAR FROM sysdate) and enviado=1",nativeQuery = true)
	List<Trabajosfinales> listarHabilitadosEnviado(Integer programaeducativoid);
	
	@Query(value="SELECT TB1.* FROM Trabajosfinales TB1 where TB1.estado=1",nativeQuery = true)
	List<Trabajosfinales> listarhabilitados();
	
	@Query(value="SELECT TB1.* FROM Trabajosfinales TB1 where TB1.estado=1 and TB1.programaeducativoid=?1",nativeQuery = true)
	List<Trabajosfinales> listarhabilitadosPE(Integer peid);
	
	@Query(value="SELECT TB1.* FROM Trabajosfinales TB1 where TB1.programaeducativoid=?1 and TB1.anio=?2 and TB1.estado=1",nativeQuery = true)
	List<Trabajosfinales> listarhabilitadosbyanio(Integer programaeducativoid,Integer anio);
	
	@Query(value="SELECT TB1.* FROM Trabajosfinales TB1 where TB1.estado=1 and TB1.enviado=1 and TB1.programaeducativoid=?1",nativeQuery = true)
	List<Trabajosfinales> listarHabilitadosEnviados(Integer programaeducativoid);
	
	@Query(value="SELECT TB1.* FROM Trabajosfinales TB1 where TB1.categoriatrabajoid=?1 and TB1.modalidadtrabajoid=?2 and TB1.programaeducativoid=?3 and TB1.estado=1",nativeQuery = true)
	List<Trabajosfinales> BuscarCategoriaModalidad(Integer idcategoria, Integer idmodalidad,Integer peid);
	
	@Query(value="SELECT max(numeracion) FROM Trabajosfinales tr where tr.programaeducativoid=?1",nativeQuery = true)
	Integer maxNumeracion(Integer programaeducativoid);
	
	@Query(value="SELECT numeracion FROM Trabajosfinales tr where tr.id=?1",nativeQuery = true)
	Integer getNumeracion(Integer trabajofinalid);
	
	@Transactional
	@Modifying	
	@Query(value="update Trabajosfinales p set p.estado = ?2 WHERE p.id = ?1 and p.programaeducativoid=?3 and p.anio = EXTRACT(YEAR FROM sysdate)  ",nativeQuery = true)
	int updateestado(Integer id, Integer estado, Integer peid);
	
	@Transactional
	@Modifying	
	@Query(value="update Trabajosfinales p set p.enviado = ?2 WHERE p.id = ?1 and p.programaeducativoid=?3  and p.anio = EXTRACT(YEAR FROM sysdate) ",nativeQuery = true)
	int updateenviado(Integer id, Integer estado, Integer peid);
	
	@Transactional
	@Modifying	
	@Query(value="update Trabajosfinales p set p.enviado = ?1 WHERE p.id = ?2 and p.anio = EXTRACT(YEAR FROM sysdate) ",nativeQuery = true)
	int updateEnviados(Integer estado, Integer id);
	
	@Transactional
	@Modifying	
	@Query(value="update Trabajosfinales p set p.estadotrabajoid = ?2 WHERE p.id = ?1 ",nativeQuery = true)
	int updateEstadoTrabajo(Integer id,Integer estadoTrabajoId);
       
    @Query(value="SELECT * FROM Trabajosfinales WHERE enviado=1 and estado=1",nativeQuery = true)
	List<Trabajosfinales> listarTrabajosPendientesAsignados();
    
    @Query(value="SELECT * FROM Trabajosfinales WHERE enviado=1 and estado=3",nativeQuery = true)
	List<Trabajosfinales> listarTrabajosEvaluados();
    
    @Query(value="select tf.* from trabajosfinales tf  "
    		+ "inner join programaeducativo pe on tf.programaeducativoid = pe.id "
    		+ "inner join distrito d on pe.distritoid = d.id "
    		+ "inner join ods o on d.odsid = o.id "
    		+ "where tf.nota is not null and o.id=?3 AND tf.categoriatrabajoid = ?1 and tf.modalidadtrabajoid=?2 "
    		+ "and tf.anio = EXTRACT(YEAR FROM sysdate) AND tf.nota >= 16"
    		+ "order by tf.nota desc ",nativeQuery = true)
    List<Trabajosfinales> listaTrabajosFinalesConNotaPromedioPorCategoriaModalidadDds(Integer idcategoria, Integer idmodalidad,Integer odsId);
    
    @Query(value="select tf.categoriatrabajoid, tf.modalidadtrabajoid from trabajosfinales tf "
			+ "inner join programaeducativo pe on tf.programaeducativoid = pe.id "
			+ "inner join distrito d on pe.distritoid = d.id "
			+ "inner join ods o on d.odsid = o.id "
			+ "where tf.nota is not null and o.id=?1 and tf.anio = EXTRACT(YEAR FROM sysdate) "
			+ "group by tf.categoriatrabajoid, tf.modalidadtrabajoid",nativeQuery = true)
	List<Object[]> listarCategoriaModalidadByOds(Integer odsId);
	
	 @Query(value="select tf.* from trabajosfinales tf  "
	    		+ "inner join programaeducativo pe on tf.programaeducativoid = pe.id "
	    		+ "inner join distrito d on pe.distritoid = d.id "
	    		+ "inner join ods o on d.odsid = o.id "
	    		+ "where o.id=?1  "
	    		+ "and tf.anio = EXTRACT(YEAR FROM sysdate) ",nativeQuery = true)
	List<Trabajosfinales> listarTrabajosfinalesPorOds(Integer odsId);
	 
	@Query(value="select tf.* from trabajosfinales tf  "
	    		+ "inner join programaeducativo pe on tf.programaeducativoid = pe.id "
	    		+ "inner join distrito d on pe.distritoid = d.id "
	    		+ "inner join ods o on d.odsid = o.id "
	    		+ "where tf.nota is not null and o.id=?3 AND tf.categoriatrabajoid = ?1 and tf.modalidadtrabajoid=?2 "
	    		+ "and tf.anio = EXTRACT(YEAR FROM sysdate) and tf.puesto= ?4 "
	    		+ "order by tf.nota desc ",nativeQuery = true)
	List<Trabajosfinales> listaTrabajosEmpatadosPorCatModOdsPuesto(Integer idcategoria, Integer idmodalidad,Integer odsId, Integer puesto);
	
	@Query(value="select tf.* from trabajosfinales tf  "
    		+ "inner join programaeducativo pe on tf.programaeducativoid = pe.id "
    		+ "inner join distrito d on pe.distritoid = d.id "
    		+ "inner join ods o on d.odsid = o.id "
    		+ "inner join cerrar_ods co on co.odsid = o.id  "
    		+ "where tf.estado=1 and tf.estadotrabajoid=21 and tf.puesto!= 0 and tf.anio = EXTRACT(YEAR FROM sysdate) ",nativeQuery = true)
	List<Trabajosfinales> listaTrabajosEmpatados();
	
	/*Finalizar ODS*/
	@Query(value="select tf.id, d.odsid, tf.categoriatrabajoid, gp.nivelgradopartdesc, tf.nota from trabajosfinales tf "
			+ "inner join programaeducativo pe on tf.programaeducativoid = pe.id "
			+ "inner join distrito d on pe.distritoid = d.id "
			+ "inner join ods o on d.odsid = o.id "
			+ "inner join trabajosfinales_participante tfp on tfp.trabajosfinalesid = tf.id "
			+ "inner join participante p on p.id = tfp.participanteid "
			+ "inner join gradoparticipante gp on gp.id = p.gradooestudiante "
    		+ "where tf.nota is not null and o.id=?3 AND tf.categoriatrabajoid = ?1 and gp.nivelgradopartdesc=?2 "
    		+ "and tf.anio = EXTRACT(YEAR FROM sysdate) AND tf.nota >= 16 "
    		+ "group by tf.id, d.odsid, tf.categoriatrabajoid, gp.nivelgradopartdesc, tf.nota "
    		+ "order by tf.nota desc ",nativeQuery = true)
    List<Object[]> listaTrabajosFinalesConNotaPromedioPorCategoriaNivelOds(Integer idcategoria, String nivel,Integer odsId);
    
    @Query(value="select tf.categoriatrabajoid, gp.nivelgradopartdesc from trabajosfinales tf "
			+ "inner join programaeducativo pe on tf.programaeducativoid = pe.id "
			+ "inner join distrito d on pe.distritoid = d.id "
			+ "inner join ods o on d.odsid = o.id "
			+ "inner join trabajosfinales_participante tfp on tfp.trabajosfinalesid = tf.id "
			+ "inner join participante p on p.id = tfp.participanteid "
			+ "inner join gradoparticipante gp on gp.id = p.gradooestudiante "
			+ "where tf.nota is not null and o.id=?1 and tf.anio = EXTRACT(YEAR FROM sysdate) "
			+ "group by tf.categoriatrabajoid, gp.nivelgradopartdesc",nativeQuery = true)
	List<Object[]> listarCategoriaNivelByOds(Integer odsId);
	
	@Query(value="select tf.id, d.odsid, tf.categoriatrabajoid, gp.nivelgradopartdesc, tf.nota from trabajosfinales tf "
			+ "inner join programaeducativo pe on tf.programaeducativoid = pe.id "
			+ "inner join distrito d on pe.distritoid = d.id "
			+ "inner join ods o on d.odsid = o.id "
			+ "inner join trabajosfinales_participante tfp on tfp.trabajosfinalesid = tf.id "
			+ "inner join participante p on p.id = tfp.participanteid "
			+ "inner join gradoparticipante gp on gp.id = p.gradooestudiante "
    		+ "where tf.nota is not null and o.id=?3 AND tf.categoriatrabajoid = ?1 and gp.nivelgradopartdesc=?2 "
    		+ "and tf.anio = EXTRACT(YEAR FROM sysdate) AND tf.puesto= ?4 "
    		+ "group by tf.id, d.odsid, tf.categoriatrabajoid, gp.nivelgradopartdesc, tf.nota "
    		+ "order by tf.nota desc ",nativeQuery = true)
	List<Object[]> listaTrabajosEmpatadosPorCatNivOdsPuesto(Integer idcategoria, String nivel,Integer odsId, Integer puesto);
	
	@Query(value="select tf.id, d.odsid, tf.categoriatrabajoid, gp.nivelgradopartdesc, tf.nota, tf.puesto from trabajosfinales tf "
			+ "inner join programaeducativo pe on tf.programaeducativoid = pe.id "
			+ "inner join distrito d on pe.distritoid = d.id "
			+ "inner join ods o on d.odsid = o.id "
			+ "inner join trabajosfinales_participante tfp on tfp.trabajosfinalesid = tf.id "
			+ "inner join participante p on p.id = tfp.participanteid "
			+ "inner join gradoparticipante gp on gp.id = p.gradooestudiante "
    		+ "where tf.nota is not null and o.id=?3 AND tf.categoriatrabajoid = ?1 and gp.nivelgradopartdesc=?2 "
    		+ "and tf.anio = EXTRACT(YEAR FROM sysdate) AND tf.empate=1 AND tf.puesto > 0 "
    		+ "group by tf.id, d.odsid, tf.categoriatrabajoid, gp.nivelgradopartdesc, tf.nota, tf.puesto "
    		+ "order by tf.puesto asc, tf.nota desc ",nativeQuery = true)
    List<Object[]> listaTrabajosFinalesConNotaPromedioPorCategoriaNivelOdsEmpatados(Integer idcategoria, String nivel,Integer odsId);
    
    @Query(value="select tf.id, d.odsid, tf.categoriatrabajoid, gp.nivelgradopartdesc, tf.nota from trabajosfinales tf "
			+ "inner join programaeducativo pe on tf.programaeducativoid = pe.id "
			+ "inner join distrito d on pe.distritoid = d.id "
			+ "inner join ods o on d.odsid = o.id "
			+ "inner join trabajosfinales_participante tfp on tfp.trabajosfinalesid = tf.id "
			+ "inner join participante p on p.id = tfp.participanteid "
			+ "inner join gradoparticipante gp on gp.id = p.gradooestudiante "
    		+ "where tf.nota is not null and o.id=?3 AND tf.categoriatrabajoid = ?1 and gp.nivelgradopartdesc=?2 "
    		+ "and tf.anio = EXTRACT(YEAR FROM sysdate) AND tf.empate=1 and tf.estadotrabajoid=2 "
    		+ "group by tf.id, d.odsid, tf.categoriatrabajoid, gp.nivelgradopartdesc, tf.nota " 
    		+ "order by tf.puesto desc ",nativeQuery = true)
    		//+ "order by tf.nota desc ",nativeQuery = true)
    List<Object[]> listaTrabajosFinalesConNotaPromedioPorCategoriaNivelOdsEmpatadosPendientes(Integer idcategoria, String nivel,Integer odsId);
    
    @Query(value="select tf.id, d.odsid, tf.categoriatrabajoid, gp.nivelgradopartdesc, tf.nota from trabajosfinales tf "
			+ "inner join programaeducativo pe on tf.programaeducativoid = pe.id "
			+ "inner join distrito d on pe.distritoid = d.id "
			+ "inner join ods o on d.odsid = o.id "
			+ "inner join trabajosfinales_participante tfp on tfp.trabajosfinalesid = tf.id "
			+ "inner join participante p on p.id = tfp.participanteid "
			+ "inner join gradoparticipante gp on gp.id = p.gradooestudiante "
    		+ "where tf.nota is not null and o.id=?3 AND tf.categoriatrabajoid = ?1 and gp.nivelgradopartdesc=?2 "
    		+ "and tf.anio = EXTRACT(YEAR FROM sysdate) AND tf.puesto= ?4 and tf.estadotrabajoid=2 "
    		+ "group by tf.id, d.odsid, tf.categoriatrabajoid, gp.nivelgradopartdesc, tf.nota "
    		+ "order by tf.nota desc ",nativeQuery = true)
	List<Object[]> listaTrabajosEmpatadosPorCatNivOdsPuestoPendientes(Integer idcategoria, String nivel,Integer odsId, Integer puesto);
	
	
	@Query(value="select tf.* from trabajosfinales tf  "
    		+ "inner join programaeducativo pe on tf.programaeducativoid = pe.id "
    		+ "inner join distrito d on pe.distritoid = d.id "
    		+ "inner join ods o on d.odsid = o.id "
    		+ "inner join cerrar_ods co on co.odsid = o.id  "
    		+ "where tf.estado=1 and tf.estadotrabajoid=21 and tf.puesto!= 0 and tf.anio = EXTRACT(YEAR FROM sysdate) and tf.programaeducativoid=?1 ",nativeQuery = true)
	List<Trabajosfinales> listaTrabajosEmpatadosPorODS(Integer odsId);
	
	@Query(value="SELECT TB1.* FROM Trabajosfinales TB1 where tb1.estado=1 and tb1.anio = EXTRACT(YEAR FROM sysdate) and TB1.estadotrabajoid=3 and TB1.puesto = 1 ",nativeQuery = true)
	List<Trabajosfinales> listarTrabajosConsursoNacional();
	
	@Query(value="SELECT TB1.* FROM Trabajosfinales TB1 where tb1.estado=1 and tb1.anio = EXTRACT(YEAR FROM sysdate) and TB1.estadotrabajoid=3 and TB1.puesto = 1 and (TB1.estadonacional !=3 OR TB1.estadonacional IS NULL ) and TB1.empate_nacional=1 and TB1.estadonacional=21 ",nativeQuery = true)
	List<Trabajosfinales> listaTrabajoEvaluadEmpateNacional();
	
	@Query(value="SELECT TB1.* FROM Trabajosfinales TB1 where tb1.estado=1 and tb1.anio = EXTRACT(YEAR FROM sysdate) and TB1.estadotrabajoid=3 and TB1.puesto = 1 and (TB1.estadonacional !=3 OR TB1.estadonacional IS NULL ) and TB1.empate_nacional!=1 ",nativeQuery = true)
	List<Trabajosfinales> listaTrabajoEvaluadNacional();
	
	
	@Query(value="select tf.* from trabajosfinales tf "
			+ "inner join programaeducativo pe on tf.programaeducativoid = pe.id "
			+ "inner join distrito d on pe.distritoid = d.id "
			+ "inner join ods o on d.odsid = o.id "
			+ "inner join trabajosfinales_participante tfp on tfp.trabajosfinalesid = tf.id "
			+ "inner join participante p on p.id = tfp.participanteid "
			+ "inner join gradoparticipante gp on gp.id = p.gradooestudiante "
    		+ "where tf.nota is not null and gp.nivelgradopartdesc=?2 AND tf.categoriatrabajoid = ?1  and tf.estadotrabajoid=3 "
    		+ "and tf.anio = EXTRACT(YEAR FROM sysdate) AND tf.puesto = 1	"
    		+ "order by tf.nota_nacional desc ",nativeQuery = true)
	List<Trabajosfinales> listarTrabajosfinalesPorNivelCategoria(Integer categoriaId, String nivel);
	
	@Query(value="select tf.* from trabajosfinales tf "
			+ "inner join programaeducativo pe on tf.programaeducativoid = pe.id "
			+ "inner join distrito d on pe.distritoid = d.id "
			+ "inner join ods o on d.odsid = o.id "
			+ "inner join trabajosfinales_participante tfp on tfp.trabajosfinalesid = tf.id "
			+ "inner join participante p on p.id = tfp.participanteid "
			+ "inner join gradoparticipante gp on gp.id = p.gradooestudiante "
    		+ "where tf.nota is not null and gp.nivelgradopartdesc=?2 AND tf.categoriatrabajoid = ?1 and tf.estadotrabajoid=3 AND tf.puesto = 1 "
    		+ "and tf.anio = EXTRACT(YEAR FROM sysdate) AND tf.puesto_nacional > 0 AND tf.estadonacional=21 and tf.nota_nacional > 0 "
    		+ "order by tf.nota_nacional desc ",nativeQuery = true)
	List<Trabajosfinales> listarTrabajosfinalesPorNivelCategoriaEmpatadosConNota(Integer categoriaId, String nivel);
	
	@Query(value="select tf.* from trabajosfinales tf "
			+ "inner join programaeducativo pe on tf.programaeducativoid = pe.id "
			+ "inner join distrito d on pe.distritoid = d.id "
			+ "inner join ods o on d.odsid = o.id "
			+ "inner join trabajosfinales_participante tfp on tfp.trabajosfinalesid = tf.id "
			+ "inner join participante p on p.id = tfp.participanteid "
			+ "inner join gradoparticipante gp on gp.id = p.gradooestudiante "
    		+ "where tf.nota is not null AND tf.categoriatrabajoid = ?1 and gp.nivelgradopartdesc=?2  AND tf.puesto = 1 "
    		+ "and tf.anio = EXTRACT(YEAR FROM sysdate) AND tf.puesto_nacional= ?3 AND tf.puesto_nacional > 0 "
    		+ "order by tf.nota desc ",nativeQuery = true)
	List<Trabajosfinales> listaTrabajosEmpatadosNacionalPorCatNivPuesto(Integer idcategoria, String nivel, Integer puesto);
	
	
	@Query(value="select gp.nivelgradopartdesc, tf.categoriatrabajoid, ct.descripcion, NVL(cn.estado, 0) from trabajosfinales tf "
			+ "inner join programaeducativo pe on tf.programaeducativoid = pe.id "
			+ "inner join distrito d on pe.distritoid = d.id "
			+ "inner join ods o on d.odsid = o.id "
			+ "inner join trabajosfinales_participante tfp on tfp.trabajosfinalesid = tf.id "
			+ "inner join participante p on p.id = tfp.participanteid "
			+ "inner join gradoparticipante gp on gp.id = p.gradooestudiante "
			+ "inner join categoriaTrabajo ct on ct.id = tf.categoriatrabajoid "
			+ "left join cerrar_nacional cn on cn.categoria_id = tf.categoriatrabajoid and cn.nivel = gp.nivelgradopartdesc "
			+ "group by gp.nivelgradopartdesc, tf.categoriatrabajoid, ct.descripcion, NVL(cn.estado, 0) "
			+ "order by tf.categoriatrabajoid asc, gp.nivelgradopartdesc asc ",nativeQuery = true)
	List<Object[]> listarTrabajosConsursoNacionalaFinalizar();
	
	
	@Transactional
	@Modifying	
	@Query(value="update Trabajosfinales p set p.estadonacional = ?2 WHERE p.id = ?1 ",nativeQuery = true)
	int updateEstadoTrabajoNacional(Integer id,Integer estadoTrabajoId);
	
}
