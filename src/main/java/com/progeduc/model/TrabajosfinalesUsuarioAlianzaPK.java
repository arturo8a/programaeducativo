package com.progeduc.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class TrabajosfinalesUsuarioAlianzaPK implements Serializable{
	
	@ManyToOne
	@JoinColumn(name="trabajosfinalesid",nullable=false)
	private Trabajosfinales trabajosfinales;
	
	@ManyToOne
	@JoinColumn(name="usuarioalianzaid",nullable=false)
	private UsuarioAlianza usuarioalianza;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((trabajosfinales == null) ? 0 : trabajosfinales.hashCode());
		result = prime * result + ((usuarioalianza == null) ? 0 : usuarioalianza.hashCode());
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
		TrabajosfinalesUsuarioAlianzaPK other = (TrabajosfinalesUsuarioAlianzaPK) obj;
		if (usuarioalianza == null) {
			if (other.usuarioalianza != null)
				return false;
		} else if (!usuarioalianza.equals(other.usuarioalianza))
			return false;
		if (trabajosfinales == null) {
			if (other.trabajosfinales != null)
				return false;
		} else if (!trabajosfinales.equals(other.trabajosfinales))
			return false;
		return true;
	}	
}
