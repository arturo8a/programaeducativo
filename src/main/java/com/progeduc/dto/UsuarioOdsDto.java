package com.progeduc.dto;

import java.util.List;

import com.progeduc.model.Usuario;

public class UsuarioOdsDto {
	
	Usuario usuario;
	List<Integer> ods;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Integer> getOds() {
		return ods;
	}
	public void setOds(List<Integer> ods) {
		this.ods = ods;
	}

}
