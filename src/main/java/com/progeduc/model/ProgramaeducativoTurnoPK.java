package com.progeduc.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ProgramaeducativoTurnoPK implements Serializable{
	
	@ManyToOne
	@JoinColumn(name="programaeducativoid",nullable=false)
	private Programaeducativo programaeducativo;
	
	@ManyToOne
	@JoinColumn(name="turnoid",nullable=false)
	private Turno turno;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((programaeducativo == null) ? 0 : programaeducativo.hashCode());
		result = prime * result + ((turno == null) ? 0 : turno.hashCode());
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
		ProgramaeducativoTurnoPK other = (ProgramaeducativoTurnoPK) obj;
		if (programaeducativo == null) {
			if (other.programaeducativo != null)
				return false;
		} else if (!programaeducativo.equals(other.programaeducativo))
			return false;
		if (turno == null) {
			if (other.turno != null)
				return false;
		} else if (!turno.equals(other.turno))
			return false;
		return true;
	}	
}
