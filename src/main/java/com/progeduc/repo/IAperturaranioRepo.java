package com.progeduc.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.progeduc.model.Aperturaranio;

public interface IAperturaranioRepo extends JpaRepository<Aperturaranio,Integer>{
	
	@Query(value="SELECT * FROM APERTURARANIO A WHERE A.ANIO=?1 ",nativeQuery = true)
	Aperturaranio buscarSiExiste(@Param("anio") Integer anio);
	
	@Query(value="SELECT * FROM APERTURARANIO A WHERE A.ANIO=?1 ",nativeQuery = true)
	Aperturaranio buscar(@Param("anio") Integer anio);
	
	@Query(value="SELECT * FROM APERTURARANIO A WHERE to_date(?1 between A.SEGUNDAETAPADESDE<=?1 AND A.CUARTAETAPAHASTA ",nativeQuery = true)
	Aperturaranio buscarsicumpleconcurso(@Param("fecha") String fecha);

}
