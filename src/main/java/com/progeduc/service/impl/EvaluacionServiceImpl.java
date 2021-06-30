package com.progeduc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.dto.EvaluacionRubricaQuestionarioDto;
import com.progeduc.model.Evaluacion;
import com.progeduc.repo.IEvaluacionQuestionarioRepo;
import com.progeduc.repo.IEvaluacionRepo;
import com.progeduc.repo.IEvaluacionRubricaRepo;
import com.progeduc.repo.IQuestionarioRepo;
import com.progeduc.repo.IQuestionarioRespuestaRepo;
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
	IQuestionarioRespuestaRepo questionariorespuestarepo;
	
	@Autowired
	IEvaluacionRubricaRepo evaluacionrubricarepo;
	
	@Autowired
	IEvaluacionQuestionarioRepo evaluacionquestionariorepo;
	
	Integer anio;
	Integer idcategoria;
	Integer idnivelparticipacion; 

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
	public Evaluacion getPorAnio(Integer anio) {
		return evaluacionrepo.getPorAnio(anio);
	}
	
	@Override
	public Evaluacion getPorAnioNivelparticipacion(Integer anio,Integer nivelparticipacion) {
		return evaluacionrepo.getPorAnioNivelparticipacion(anio,nivelparticipacion);
	}
	
	@Override
	public Evaluacion getPorAnioCategoriaNivelparticipacion(Integer anio,Integer idcategoria,Integer idnivelparticipacion) {
		return evaluacionrepo.getPorAnioCategoriaNivelparticipacion(anio, idcategoria, idnivelparticipacion);
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Integer saveEvalRubQuest(EvaluacionRubricaQuestionarioDto dto) {
		
		anio = dto.getEvaluacion().getAnio();
		idcategoria = dto.getEvaluacion().getCategoriaevaluacion().getId();
		idnivelparticipacion = dto.getEvaluacion().getNivelparticipacion().getId();
		
		if(getPorAnioCategoriaNivelparticipacion(anio, idcategoria, idnivelparticipacion) !=null) {
			return 0;
		}
		
		evaluacionrepo.save(dto.getEvaluacion());
		dto.getListarubrica().forEach(obj->{
			rubricarepo.save(obj);			
			evaluacionrubricarepo.guardar(dto.getEvaluacion().getId(), obj.getId());
		});
		
		dto.getListaquestionario().forEach(obj->{			
			obj.getQuestionariorespuesta().forEach(obj1->{
				obj1.setQuestionario(obj);
			});
			questionariorepo.save(obj);
			evaluacionquestionariorepo.guardar(dto.getEvaluacion().getId(), obj.getId());
		});
		return dto.getEvaluacion().getId();
	}
	
	@Override
	public Evaluacion updateEvalRubQuest(EvaluacionRubricaQuestionarioDto dto) {
		
		evaluacionrepo.save(dto.getEvaluacion());
		dto.getListarubrica().forEach(obj->{
			rubricarepo.save(obj);
		});
		
		dto.getListaquestionario().forEach(obj->{
			obj.getQuestionariorespuesta().forEach(obj1->{
				obj1.setQuestionario(obj);
			});
			questionariorepo.save(obj);
		});
		return dto.getEvaluacion();
	}
	
	@Override
	public int updateestado(Integer id, Integer estado) {
		return evaluacionrepo.updateestado(id, estado);
	}
	
	
}
