package com.progeduc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="EVALUACIONRUBRICA")
@IdClass(EvaluacionrubricaPK.class)/*se comunica con la clase*/
public class Evaluacionrubrica {
	
	@Id
	private Evaluacion evaluacion;
	
	@Id
	private Rubrica rubrica;

	public Evaluacion getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(Evaluacion evaluacion) {
		this.evaluacion = evaluacion;
	}

	public Rubrica getRubrica() {
		return rubrica;
	}

	public void setRubrica(Rubrica rubrica) {
		this.rubrica = rubrica;
	}	
}
