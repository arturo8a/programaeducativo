package com.progeduc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="USUARIOODS")
@IdClass(UsuarioOdsPK.class)/*se comunica con la clase*/
public class UsuarioOds {
	
	@Id
	private Usuario usuario;
	
	@Id
	private Ods ods;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Ods getOds() {
		return ods;
	}

	public void setOds(Ods ods) {
		this.ods = ods;
	}
}
