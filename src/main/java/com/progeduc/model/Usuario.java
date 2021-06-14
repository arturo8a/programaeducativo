package com.progeduc.model;

import java.sql.Timestamp;

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
@Table(name="USUARIO")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="USUARIO",length=50)
	private String usuario;
	
	@JsonIgnore
	@Column(name="PASSWORD",length=50,nullable=true)
	private String password;
	
	@ManyToOne
	@JoinColumn(name="tipousuarioid",foreignKey=@ForeignKey(name="FK_usuario_tipo"))
	private Tipousuario tipousuario;
	
	@Column(name="ESTADO")
	private Integer estado;
	
	@Column(name="ODSID")
	private Integer odsid;
	
	@JsonIgnore
	@Column(name="ANIO",nullable=true)
	private Integer anio;
	
	@JsonIgnore
	@Column(name="FECHA_REGISTRO",nullable=true)
	private Timestamp fecha_registro;
	
	public Usuario() {
		
	}
	
	public Usuario(String usuario, Tipousuario tipo, Integer estado) {
		this.usuario = usuario;
		this.tipousuario = tipo;
		this.estado = estado;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Tipousuario getTipousuario() {
		return tipousuario;
	}

	public void setTipousuario(Tipousuario tipousuario) {
		this.tipousuario = tipousuario;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Timestamp getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Timestamp fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public Integer getOdsid() {
		return odsid;
	}

	public void setOdsid(Integer odsid) {
		this.odsid = odsid;
	}
}
