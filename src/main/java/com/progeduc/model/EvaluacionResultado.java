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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="EVALUACIONES_RESPUESTAS")
public class EvaluacionResultado {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	//@JsonIgnore
	@ManyToOne
	@JoinColumn(name="trabajoid",nullable=false,foreignKey=@ForeignKey(name="fk_trabajos_evaludos_respuestas"))
	private Trabajosfinales trabajosfinales;
	
	@Column(name="preguntaid" ,length=150)
	private Integer preguntaid;
	
	@Column(name="respuestaid" ,length=150)
	private Integer respuestaid;
	
	@Column(name="tipo" ,length=150)
	private Integer tipo;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Trabajosfinales getTrabajosfinales() {
		return trabajosfinales;
	}

	public void setTrabajosfinales(Trabajosfinales trabajosfinales) {
		this.trabajosfinales = trabajosfinales;
	}

	public Integer getPreguntaid() {
		return preguntaid;
	}

	public void setPreguntaid(Integer preguntaid) {
		this.preguntaid = preguntaid;
	}

	public Integer getRespuestaid() {
		return respuestaid;
	}

	public void setRespuestaid(Integer respuestaid) {
		this.respuestaid = respuestaid;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

}
