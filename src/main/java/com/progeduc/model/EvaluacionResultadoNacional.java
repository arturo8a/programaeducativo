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
@Table(name="EVALUACIONES_RESPUESTAS_NA")
public class EvaluacionResultadoNacional {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	//@JsonIgnore
	@ManyToOne
	@JoinColumn(name="trabajoid",nullable=false,foreignKey=@ForeignKey(name="fk_trab_evaludos_respuestas"))
	private Trabajosfinales trabajosfinales;
	
	@ManyToOne
	@JoinColumn(name="usuario_alianzaid",nullable=false,foreignKey=@ForeignKey(name="fk_usuarios_evaludos_resp"))
	private UsuarioAlianza usuario;
	
	@Column(name="preguntaid" ,length=150)
	private Integer preguntaid;
	
	@Column(name="respuestaid" ,length=150)
	private Integer respuestaid;
	
	@Column(name="tipo" ,length=150)
	private Integer tipo;

	@Column(name="puntaje" ,length=150)
	private Float puntaje;
	
	public UsuarioAlianza getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioAlianza usuario) {
		this.usuario = usuario;
	}
	
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

	public Float getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(Float puntaje) {
		this.puntaje = puntaje;
	}

}
