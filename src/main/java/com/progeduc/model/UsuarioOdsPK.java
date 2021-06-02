package com.progeduc.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class UsuarioOdsPK implements Serializable{
	
	@ManyToOne
	@JoinColumn(name="usuarioid",nullable=false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="odsid",nullable=false)
	private Ods ods;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result + ((ods == null) ? 0 : ods.hashCode());
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
		UsuarioOdsPK other = (UsuarioOdsPK) obj;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (ods == null) {
			if (other.ods != null)
				return false;
		} else if (!ods.equals(other.ods))
			return false;
		return true;
	}	

}
