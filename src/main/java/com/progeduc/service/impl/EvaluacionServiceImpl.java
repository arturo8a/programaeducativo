package com.progeduc.service.impl;

import java.util.List;
import java.util.Optional;

import javax.tools.DocumentationTool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.dto.EvaluacionRubricaQuestionarioDto;
import com.progeduc.model.Evaluacion;
import com.progeduc.model.Programaeducativo;
import com.progeduc.repo.IEvaluacionQuestionarioRepo;
import com.progeduc.repo.IEvaluacionRepo;
import com.progeduc.repo.IEvaluacionRubricaRepo;
import com.progeduc.repo.IQuestionarioRepo;
import com.progeduc.repo.IRubricaRepo;
import com.progeduc.service.IEvaluacionService;

@Service
public class EvaluacionServiceImpl implements IEvaluacionService{
	
	@Autowired
	IEvaluacionRepo evaluacionrepo;
	
	@Autowired
	IRubricaRepo rubricarepo;
	
	@Autowired
	IQuestionarioRepo questionariorepo;
	
	@Autowired
	IEvaluacionRubricaRepo evaluacionrubricarepo;
	
	@Autowired
	IEvaluacionQuestionarioRepo evaluacionquestionariorepo;

	@Override
	public Evaluacion registrar(Evaluacion obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Evaluacion modificar(Evaluacion obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Evaluacion> listar() {
		// TODO Auto-generated method stub
		return (List<Evaluacion>) evaluacionrepo.findAll();
	}

	@Override
	public Evaluacion ListarporId(Integer id) {
		// TODO Auto-generated method stub
		Optional<Evaluacion> eval = evaluacionrepo.findById(id);
		return eval.isPresent() ? eval.get() : new Evaluacion();
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Evaluacion saveEvalRubQuest(EvaluacionRubricaQuestionarioDto dto) {
		
		evaluacionrepo.save(dto.getEvaluacion());
		dto.getListarubrica().forEach(obj->{
			rubricarepo.save(obj);			
			evaluacionrubricarepo.guardar(dto.getEvaluacion().getId(), obj.getId());
		});
		
		dto.getListaquestionario().forEach(obj->{
			questionariorepo.save(obj);
			evaluacionquestionariorepo.guardar(dto.getEvaluacion().getId(), obj.getId());
		});
		return dto.getEvaluacion();
	}
	
	@Override
	public Evaluacion updateEvalRubQuest(EvaluacionRubricaQuestionarioDto dto) {
		
		evaluacionrepo.save(dto.getEvaluacion());
		dto.getListarubrica().forEach(obj->{
			rubricarepo.save(obj);
		});
		
		dto.getListaquestionario().forEach(obj->{
			questionariorepo.save(obj);
		});
		return dto.getEvaluacion();
	}
	
	@Override
	public int updateestado(Integer id, Integer estado) {
		return evaluacionrepo.updateestado(id, estado);
	}
	
	
}
