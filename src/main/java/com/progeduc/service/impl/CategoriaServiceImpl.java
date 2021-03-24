package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.dto.ListaCategoriaDto;
import com.progeduc.model.Categoria;
import com.progeduc.repo.ICategoriaRepo;
import com.progeduc.repo.IProgramaeducativoRepo;
import com.progeduc.service.ICategoriaService;

@Service
public class CategoriaServiceImpl implements ICategoriaService{
	
	@Autowired
	ICategoriaRepo repo;
	
	@Autowired
	IProgramaeducativoRepo progeducRepo;
	
	Integer progeducid;
	Integer numeroCat;

	@Override
	public Categoria registrar(Categoria obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public Categoria modificar(Categoria obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categoria> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categoria ListarporId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Integer guardar(ListaCategoriaDto lista) {
		// TODO Auto-generated method stub		
		if(lista.getListCategoria()!=null) {			
			lista.getListCategoria().forEach(obj->{
				progeducid = obj.getProgramaeducativo().getId();
				numeroCat = obj.getNumeroCat();
				repo.save(obj);
			});
			//return progeducRepo.updateConcurso(progeducid);
		}
		return 0;
	}

}
