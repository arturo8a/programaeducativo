package com.progeduc.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class EvaluacionquestionarioPK implements Serializable{
	
	@ManyToOne
	@JoinColumn(name="evaluacionid",nullable=false)
	private Evaluacion evaluacion;
	
	@ManyToOne
	@JoinColumn(name="questionarioid",nullable=false)
	private Questionario questionario;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((evaluacion == null) ? 0 : evaluacion.hashCode());
		result = prime * result + ((questionario == null) ? 0 : questionario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EvaluacionquestionarioPK other = (EvaluacionquestionarioPK) obj;
		if (evaluacion == null) {
			if (other.evaluacion != null)
				return false;
		} else if (!evaluacion.equals(other.evaluacion))
			return false;
		if (questionario == null) {
			if (other.questionario!= null)
				return false;
		} else if (!questionario.equals(other.questionario))
			return false;
		return true;
	}	

}
