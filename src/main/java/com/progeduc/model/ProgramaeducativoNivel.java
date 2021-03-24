package com.progeduc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="PROGRAMAEDUCATIVO_NIVEL")
@IdClass(ProgramaeducativoNivelPK.class)/*se comunica con la clase*/
public class ProgramaeducativoNivel {
	
	@Id
	private Programaeducativo programaeducativo;
	
	@Id
	private Nivel nivel;

	public Programaeducativo getProgramaeducativo() {
		return programaeducativo;
	}

	public void setProgramaeducativo(Programaeducativo programaeducativo) {
		this.programaeducativo = programaeducativo;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}
}
