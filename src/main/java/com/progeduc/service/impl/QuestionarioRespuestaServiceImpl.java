package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.QuestionarioRespuesta;
import com.progeduc.repo.IQuestionarioRespuestaRepo;
import com.progeduc.service.IQuestionarioRespuestaService;

@Service
public class QuestionarioRespuestaServiceImpl implements IQuestionarioRespuestaService{
	
	@Autowired
	IQuestionarioRespuestaRepo repo;

	@Override
	public QuestionarioRespuesta registrar(QuestionarioRespuesta obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuestionarioRespuesta modificar(QuestionarioRespuesta obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuestionarioRespuesta> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuestionarioRespuesta ListarporId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Integer eliminar(Integer id) {
		// TODO Auto-generated method stub
		return repo.eliminar(id);
	}
	
	@Override
	public List<QuestionarioRespuesta> listarByTrabajo(Integer trabajoid){
		return repo.listarByTrabajo(trabajoid);
	}
	
	@Override
	public List<QuestionarioRespuesta> listarByQuestionario(Integer questionarioid){
		return repo.listarByQuestionario(questionarioid);
	}

}
