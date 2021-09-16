package com.progeduc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.dto.EvaluacionRubricaQuestionarioDto;
import com.progeduc.dto.rubricaPuntajeDto;
import com.progeduc.model.Evaluacion;
import com.progeduc.model.EvaluacionResultado;
import com.progeduc.model.EvaluacionResultadoNacional;
import com.progeduc.model.UsuarioAlianza;
import com.progeduc.repo.IEvaluacionQuestionarioRepo;
import com.progeduc.repo.IEvaluacionRepo;
import com.progeduc.repo.IEvaluacionRespuestaNacionalRepo;
import com.progeduc.repo.IEvaluacionRespuestaRepo;
import com.progeduc.repo.IEvaluacionRubricaRepo;
import com.progeduc.repo.IQuestionarioRepo;
import com.progeduc.repo.IQuestionarioRespuestaRepo;
import com.progeduc.repo.IRubricaRepo;
import com.progeduc.service.IEvaluacionRespuestaNacionalService;
import com.progeduc.service.IEvaluacionService;

@Service
public class EvaluacionRespuestaNacionalServiceImpl implements IEvaluacionRespuestaNacionalService{
	
	@Autowired
	IEvaluacionRespuestaNacionalRepo evaRepo;

	@Override
	public EvaluacionResultadoNacional registrar(EvaluacionResultadoNacional obj) {
		// TODO Auto-generated method stub
		return evaRepo.save(obj);
	}

	@Override
	public EvaluacionResultadoNacional modificar(EvaluacionResultadoNacional obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EvaluacionResultadoNacional> listar() {
		return (List<EvaluacionResultadoNacional>) evaRepo.findAll();
	}

	@Override
	public EvaluacionResultadoNacional ListarporId(Integer id) {
		Optional<EvaluacionResultadoNacional> optionalEntity =  evaRepo.findById(id);
		if(optionalEntity.isPresent()) {
			return optionalEntity.get();
		}else {
			return null;
		}
	}

	@Override
	public boolean Eliminar(Integer id) {
		evaRepo.deleteById(id);
		return true;
	}

	@Override
	public EvaluacionResultadoNacional getPorCodigoTrabajoAndCodigoPreginta(Integer idTrabajo, Integer idPregunta) {
		// TODO Auto-generated method stub
		return evaRepo.getPorCodigoTrabajoAndCodigoPreginta(idTrabajo, idPregunta);
	}

	@Override
	public List<EvaluacionResultadoNacional> getRespuestas(Integer idTrabajo) {
		// TODO Auto-generated method stub
		return evaRepo.getRespuestas(idTrabajo);
	}

	@Override
	public List<EvaluacionResultadoNacional> listaEvaluacionResultado(Integer idTrabajo, Integer idUsuario) {
		return evaRepo.listaEvaluacionResultado(idTrabajo, idUsuario);
	}

	@Override
	public void borrarEvaluacionesPorTrabajo(Integer idTrabajo) {
		evaRepo.borrarEvaluacionesPorTrabajo(idTrabajo);
	}
	
	@Override
	public List<rubricaPuntajeDto> listaRubricaPuntajeDto(Integer trabajoid){
		return evaRepo.listaRubricaPuntajeDto(trabajoid);
	}
	
	
}
