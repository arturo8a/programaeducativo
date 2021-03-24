package com.progeduc.dto;

import java.util.List;

import com.progeduc.model.Nivel;
import com.progeduc.model.Programaeducativo;
import com.progeduc.model.Turno;

public class ProgeducUpdateTurnoNivelDto {
	
	private Programaeducativo progeduc;	
	private List<Turno> listTurno;	
	private List<Nivel> listNivel;

	public Programaeducativo getProgeduc() {
		return progeduc;
	}

	public void setProgeduc(Programaeducativo progeduc) {
		this.progeduc = progeduc;
	}

	public List<Turno> getlistTurno() {
		return listTurno;
	}

	public void setListTurno(List<Turno> listTurno) {
		this.listTurno = listTurno;
	}

	public List<Nivel> getListNivel() {
		return listNivel;
	}

	public void setListNivel(List<Nivel> listNivel) {
		this.listNivel = listNivel;
	}
	
}
