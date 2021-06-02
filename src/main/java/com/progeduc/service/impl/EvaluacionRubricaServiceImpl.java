package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Evaluacionrubrica;
import com.progeduc.repo.IEvaluacionRubricaRepo;
import com.progeduc.repo.IQuestionarioRepo;
import com.progeduc.repo.IRubricaRepo;
import com.progeduc.service.IEvaluacionRubricaService;

@Service
public class EvaluacionRubricaServiceImpl implements IEvaluacionRubricaService{
	
	@Autowired
	IEvaluacionRubricaRepo evaluacionrubricarepo;
	
	@Autowired
	IRubricaRepo rubricarepo;
	
	@Autowired
	IQuestionarioRepo questionariorepo;

	@Override
	public Evaluacionrubrica registrar(Evaluacionrubrica obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Evaluacionrubrica modificar(Evaluacionrubrica obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evaluacionrubrica> listar() {
		// TODO Auto-generated method stub
		return (List<Evaluacionrubrica>) evaluacionrubricarepo.findAll();
	}

	@Override
	public Evaluacionrubrica ListarporId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Integer guardar(Integer evaluacionid, Integer rubricaid) {
		return evaluacionrubricarepo.guardar(evaluacionid, rubricaid);
	}
	
	@Override
	public List<Evaluacionrubrica> listarPorEvaluacionId(Integer evaluacionid){
		return evaluacionrubricarepo.listarPorEvaluacionId(evaluacionid);
	}
	
}
