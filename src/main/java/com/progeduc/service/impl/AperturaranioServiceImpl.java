package com.progeduc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Aperturaranio;
import com.progeduc.repo.IAperturaranioRepo;
import com.progeduc.service.IAperturaranioService;

@Service
public class AperturaranioServiceImpl implements IAperturaranioService{
	
	@Autowired
	IAperturaranioRepo repo;
	
	@Override
	public Aperturaranio registrar(Aperturaranio obj) {
		
		return repo.save(obj);
	}

	@Override
	public Aperturaranio modificar(Aperturaranio obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public List<Aperturaranio> listar() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Aperturaranio ListarporId(Integer id) {
		// TODO Auto-generated method stub
		Optional<Aperturaranio> op = repo.findById(id);
		System.out.println("op.get :"+op.get());
		return op.get();
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean buscarSiExiste(Integer anio) {
		
		Optional<Aperturaranio> op = Optional.ofNullable( repo.buscarSiExiste(anio));
		if(op.isPresent()) {
			return true;
		}		
		return false; 
	}
	
	@Override
	public Aperturaranio buscar(Integer anio) {
		return repo.buscar(anio);
	}
	
	@Override
	public Aperturaranio buscarsicumpleconcurso(String fecha) {
		
		/*Optional<Aperturaranio> op = Optional.ofNullable( repo.buscarsicumpleconcurso(fecha));
		if(op.isPresent()) {
			return true;
		}		
		return false; */
		return repo.buscarsicumpleconcurso(fecha);
	}
}
