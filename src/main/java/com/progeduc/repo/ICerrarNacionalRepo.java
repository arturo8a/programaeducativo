package com.progeduc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.progeduc.model.CerrarEtapaNacional;
import com.progeduc.service.ICRUD;

@Repository
@Transactional(readOnly = true)
public interface ICerrarNacionalRepo extends CrudRepository<CerrarEtapaNacional,Integer>{
	
	@Query(value="SELECT TB1.nivel, TB1.categoria_Id FROM CERRAR_NACIONAL TB1 where TB1.estado=2 GROUP BY TB1.nivel, TB1.categoria_Id",nativeQuery = true)
	List<CerrarEtapaNacional> listaNacionalEmpates();
	
	@Query(value="SELECT * FROM CERRAR_NACIONAL TB1 where TB1.anio=EXTRACT(YEAR FROM sysdate) ",nativeQuery = true)
	List<CerrarEtapaNacional> listaPorAnio();

}
