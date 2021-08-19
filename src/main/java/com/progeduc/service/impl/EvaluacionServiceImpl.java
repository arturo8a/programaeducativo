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
	Integer rpta;

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
	public Evaluacion getPorAnioNivelparticipacionCategoria(Integer anio,Integer nivelparticipacion,Integer idcategoria) {
		return evaluacionrepo.getPorAnioNivelparticipacionCategoria(anio,nivelparticipacion,idcategoria);
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
	public Integer updateEvalRubQuest(EvaluacionRubricaQuestionarioDto dto) {
		
		if(existe(dto.getEvaluacion().getId(),	dto.getEvaluacion().getAnio(), dto.getEvaluacion().getCategoriaevaluacion().getId(), dto.getEvaluacion().getNivelparticipacion().getId()) !=null) {
			rpta = -1;
		}
		else {
			dto.getEliminado_rubrica().forEach(obj->{
				rubricarepo.updateestado(obj.getId(), 0);
			});
			
			dto.getEliminado_questionario().forEach(obj->{
				questionariorepo.updateestado(obj.getId(), 0);
			});
			
			evaluacionrepo.save(dto.getEvaluacion());
			dto.getListarubrica().forEach(obj->{
				rubricarepo.save(obj);
				if(evaluacionrubricarepo.verificaExiste(dto.getEvaluacion().getId(), obj.getId()).size()==0) {
					evaluacionrubricarepo.guardar(dto.getEvaluacion().getId(), obj.getId());
				}
			});
			
			dto.getListaquestionario().forEach(obj->{
				obj.getQuestionariorespuesta().forEach(obj1->{
					obj1.setQuestionario(obj);
				});
				questionariorepo.save(obj);
				if(evaluacionquestionariorepo.verificaExiste(dto.getEvaluacion().getId(), obj.getId()).size()==0) {
					evaluacionquestionariorepo.guardar(dto.getEvaluacion().getId(), obj.getId());
				}
			});
			
			dto.getEliminado_pregunta_respuesta().forEach(obj->{
				questionariorespuestarepo.eliminar(obj.getId());
			});
			
			dto.getAgregado_rp_edit().forEach(obj->{
				questionariorespuestarepo.guardar(obj.getPuntaje(), obj.getRespuesta(), obj.getCuestionarioid());
			});
			
			rpta = dto.getEvaluacion() != null ? dto.getEvaluacion().getId() : 0;
		}		
		return rpta;
	}
	
	@Override
	public int updateestado(Integer id, Integer estado) {
		return evaluacionrepo.updateestado(id, estado);
	}
	
	@Override
	public Evaluacion existe(Integer id_evaluacion,Integer anio,Integer categoria, Integer nivel_participacion) {
		return evaluacionrepo.existe(id_evaluacion, anio, categoria, nivel_participacion);
	}
	
	
}
