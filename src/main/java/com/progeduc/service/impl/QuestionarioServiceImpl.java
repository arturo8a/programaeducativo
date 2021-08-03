package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.progeduc.model.Questionario;
import com.progeduc.repo.IQuestionarioRepo;
import com.progeduc.service.IQuestionarioService;

@Service
public class QuestionarioServiceImpl implements IQuestionarioService{
	
	@Autowired
	IQuestionarioRepo repo;

	@Override
	public Questionario registrar(Questionario obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public Questionario modificar(Questionario obj) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Questionario> listar() {
		// TODO Auto-generated method stub
		return (List<Questionario>) repo.findAll();
	}

	@Override
	public Questionario ListarporId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override 
	public int updateestado(Integer id,Integer estado) {
		return repo.updateestado(id, estado);
	}
}
