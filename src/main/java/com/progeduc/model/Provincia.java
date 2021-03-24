package com.progeduc.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="PROVINCIA")
public class Provincia {
	
	@Id
	private Integer id;
	
	@Column(name="DESCRIPCION" ,length=100)
	private String descripcion;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="departamentoid",nullable=false,foreignKey=@ForeignKey(name="fk_departamento_provincia"))
	private Departamento departamento;
	
	//orphanRemoval=true permite eliminar un elemento de la lista
	@OneToMany(mappedBy="provincia", cascade = {CascadeType.ALL},orphanRemoval=true)
	private List<Distrito> distrito;	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	public List<Distrito> getDistrito() {
		return distrito;
	}
	
	public void setDistrito(List<Distrito> distrito) {
		this.distrito = distrito;
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
		Provincia other = (Provincia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
