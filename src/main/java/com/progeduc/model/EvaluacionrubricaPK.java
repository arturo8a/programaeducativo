package com.progeduc.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class EvaluacionrubricaPK implements Serializable{
	
	@ManyToOne
	@JoinColumn(name="evaluacionid",nullable=false)
	private Evaluacion evaluacion;
	
	@ManyToOne
	@JoinColumn(name="rubricaid",nullable=false)
	private Rubrica rubrica;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((evaluacion == null) ? 0 : evaluacion.hashCode());
		result = prime * result + ((rubrica == null) ? 0 : rubrica.hashCode());
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
		EvaluacionrubricaPK other = (EvaluacionrubricaPK) obj;
		if (evaluacion == null) {
			if (other.evaluacion != null)
				return false;
		} else if (!evaluacion.equals(other.evaluacion))
			return false;
		if (rubrica == null) {
			if (other.rubrica!= null)
				return false;
		} else if (!rubrica.equals(other.rubrica))
			return false;
		return true;
	}	

}
