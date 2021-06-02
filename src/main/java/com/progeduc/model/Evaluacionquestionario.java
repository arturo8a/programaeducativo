package com.progeduc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="EVALUACIONQUESTIONARIO")
@IdClass(EvaluacionquestionarioPK.class)/*se comunica con la clase*/
public class Evaluacionquestionario {
	
	@Id
	private Evaluacion evaluacion;
	
	@Id
	private Questionario questionario;

	public Evaluacion getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
	}

	public Questionario getQuestionario() {
		return questionario;
	}

	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}
}
