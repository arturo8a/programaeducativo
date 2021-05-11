package com.progeduc.dto;

import java.util.List;

import com.progeduc.model.Trabajosfinales;

public class TrabajosfinalesParticipanteDto {
	
	Trabajosfinales trabajosfinales;	
	List<DatoDto> listaparticipante;

	public Trabajosfinales getTrabajosfinales() {
		return trabajosfinales;
	}

	public void setTrabajosfinales(Trabajosfinales trabajosfinales) {
		this.trabajosfinales = trabajosfinales;
	}

	public List<DatoDto> getListaparticipante() {
		return listaparticipante;
	}

	public void setListaparticipante(List<DatoDto> listaparticipante) {
		this.listaparticipante = listaparticipante;
	}
}
