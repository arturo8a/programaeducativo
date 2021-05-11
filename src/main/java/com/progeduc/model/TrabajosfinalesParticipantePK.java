package com.progeduc.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class TrabajosfinalesParticipantePK implements Serializable{
	
	@ManyToOne
	@JoinColumn(name="trabajosfinalesid",nullable=false)
	private Trabajosfinales trabajosfinales;
	
	@ManyToOne
	@JoinColumn(name="participanteid",nullable=false)
	private Participante participante;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((trabajosfinales == null) ? 0 : trabajosfinales.hashCode());
		result = prime * result + ((participante == null) ? 0 : participante.hashCode());
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
		TrabajosfinalesParticipantePK other = (TrabajosfinalesParticipantePK) obj;
		if (participante == null) {
			if (other.participante != null)
				return false;
		} else if (!participante.equals(other.participante))
			return false;
		if (trabajosfinales == null) {
			if (other.trabajosfinales != null)
				return false;
		} else if (!trabajosfinales.equals(other.trabajosfinales))
			return false;
		return true;
	}	

}
