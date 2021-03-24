package com.progeduc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="PROGRAMAEDUCATIVO_TURNO")
@IdClass(ProgramaeducativoTurnoPK.class)/*se comunica con la clase*/
public class ProgramaeducativoTurno {
	
	@Id
	private Programaeducativo programaeducativo;
	
	@Id
	private Turno turno;

	public Programaeducativo getProgramaeducativo() {
		return programaeducativo;
	}

	public void setProgramaeducativo(Programaeducativo programaeducativo) {
		this.programaeducativo = programaeducativo;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}	
}
