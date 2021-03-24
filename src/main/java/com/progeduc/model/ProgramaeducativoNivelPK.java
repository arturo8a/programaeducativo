package com.progeduc.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ProgramaeducativoNivelPK implements Serializable{
	
	@ManyToOne
	@JoinColumn(name="programaeducativoid",nullable=false)
	private Programaeducativo programaeducativo;
	
	@ManyToOne
	@JoinColumn(name="nivelid",nullable=false)
	private Nivel nivel;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nivel == null) ? 0 : nivel.hashCode());
		result = prime * result + ((programaeducativo == null) ? 0 : programaeducativo.hashCode());
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
		ProgramaeducativoNivelPK other = (ProgramaeducativoNivelPK) obj;
		if (nivel == null) {
			if (other.nivel != null)
				return false;
		} else if (!nivel.equals(other.nivel))
			return false;
		if (programaeducativo == null) {
			if (other.programaeducativo != null)
				return false;
		} else if (!programaeducativo.equals(other.programaeducativo))
			return false;
		return true;
	}	

}
