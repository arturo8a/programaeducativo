package com.progeduc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Query(value="SELECT TB1.* FROM Trabajosfinales TB1 where TB1.estado=1 and TB1.enviado=1 and TB1.programaeducativoid=?1",nativeQuery = true)
	List<Trabajosfinales> listarHabilitadosEnviados(Integer programaeducativoid);
	
	@Query(value="SELECT max(numeracion) FROM Trabajosfinales tr where tr.programaeducativoid=?1",nativeQuery = true)
	Integer maxNumeracion(Integer programaeducativoid);
	
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

}
