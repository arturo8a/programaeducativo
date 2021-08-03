package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.Evaluacionquestionario;
import com.progeduc.model.Evaluacionrubrica;
import com.progeduc.repo.IEvaluacionQuestionarioRepo;
import com.progeduc.repo.IEvaluacionRubricaRepo;
import com.progeduc.repo.IQuestionarioRepo;
import com.progeduc.repo.IRubricaRepo;
import com.progeduc.service.IEvaluacionQuestionarioService;

@Service
public class EvaluacionQuestionarioServiceImpl implements IEvaluacionQuestionarioService{
	
	@Autowired
	IEvaluacionRubricaRepo evaluacionrubricarepo;
	
	@Autowired
	IEvaluacionQuestionarioRepo evaluacionquestionariorepo;
	
	@Autowired
	IRubricaRepo rubricarepo;
	
	@Autowired
	IQuestionarioRepo questionariorepo;

	@Override
	public Evaluacionquestionario registrar(Evaluacionquestionario obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Evaluacionquestionario modificar(Evaluacionquestionario obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evaluacionquestionario> listar() {
		// TODO Auto-generated method stub
		return (List<Evaluacionquestionario>) evaluacionquestionariorepo.findAll();
	}

	@Override
	public Evaluacionquestionario ListarporId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Integer guardar(Integer evaluacionid, Integer questionarioid) {
		return evaluacionquestionariorepo.guardar(evaluacionid, questionarioid);
	}
	
	@Override
	public List<Evaluacionquestionario> listarPorEvaluacionId(Integer evaluacionid){
		return evaluacionquestionariorepo.listarPorEvaluacionId(evaluacionid);
	}
	
	@Override
	public List<Evaluacionquestionario> verificaExiste(Integer evaluacionid, Integer questionarioid){
		return evaluacionquestionariorepo.verificaExiste(evaluacionid, questionarioid);
	}
	
}
