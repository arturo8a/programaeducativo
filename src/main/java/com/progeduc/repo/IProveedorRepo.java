package com.progeduc.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.progeduc.model.Proveedor;

@Repository
public interface IProveedorRepo extends CrudRepository<Proveedor,Integer>{
	
	@Query(value="SELECT T.* FROM PROVEEDORSERVICIO T WHERE (T.ID>=1 AND T.ID<=5) OR T.ID=8 ORDER BY T.ID",nativeQuery = true)
	List<Proveedor> list();
}
