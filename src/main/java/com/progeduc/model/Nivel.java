package com.progeduc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="NIVEL")
public class Nivel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="NROSECCIONES")
	private Integer nrosecciones;
	
	@Column(name="NRODOCENTES")
	private Integer nrodocentes;
	
	@Column(name="NROALUMNOS")
	private Integer nroalumnos;
	
	@Column(name="NROVARONES")
	private Integer nrovarones;
	
	@Column(name="NROMUJERES")
	private Integer nromujeres;
	
	@ManyToOne
	@JoinColumn(name="id_tiponivel",nullable=false,foreignKey=@ForeignKey(name="FK_nivel_tiponivel"))
	private Tiponivel tiponivel;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNrosecciones() {
		return nrosecciones;
	}

	public void setNrosecciones(Integer nrosecciones) {
		this.nrosecciones = nrosecciones;
	}

	public Integer getNrodocentes() {
		return nrodocentes;
	}

	public void setNrodocentes(Integer nrodocentes) {
		this.nrodocentes = nrodocentes;
	}

	public Integer getNroalumnos() {
		return nroalumnos;
	}

	public void setNroalumnos(Integer nroalumnos) {
		this.nroalumnos = nroalumnos;
	}

	public Integer getNrovarones() {
		return nrovarones;
	}

	public void setNrovarones(Integer nrovarones) {
		this.nrovarones = nrovarones;
	}

	public Integer getNromujeres() {
		return nromujeres;
	}

	public void setNromujeres(Integer nromujeres) {
		this.nromujeres = nromujeres;
	}

	public Tiponivel getTiponivel() {
		return tiponivel;
	}

	public void setTiponivel(Tiponivel tiponivel) {
		this.tiponivel = tiponivel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Nivel other = (Nivel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
