package com.progeduc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.progeduc.model.Suministro;

@Repository
public interface ISuministroRepo extends CrudRepository<Suministro,Integer>{
	
	@Query(value="SELECT s.* FROM suministro s where s.programaeducativoid=?1",nativeQuery = true)
	List<Suministro> listarPorProgramaeducativo(Integer idpe);

}
