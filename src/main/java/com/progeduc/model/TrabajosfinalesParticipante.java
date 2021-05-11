package com.progeduc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="TRABAJOSFINALES_PARTICIPANTE")
@IdClass(TrabajosfinalesParticipantePK.class)/*se comunica con la clase*/
public class TrabajosfinalesParticipante {
	
	@Id
	private Trabajosfinales trabajosfinales;
	
	@Id
	private Participante participante;

	public Trabajosfinales getTrabajosfinales() {
		return trabajosfinales;
	}

	public void setTrabajosfinales(Trabajosfinales trabajosfinales) {
		this.trabajosfinales = trabajosfinales;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

}
