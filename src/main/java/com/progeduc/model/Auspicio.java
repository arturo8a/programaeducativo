package com.progeduc.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="AUSPICIO")
public class Auspicio {
	
	@Id
	@Column(name="ID")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="tipodoc",nullable=true,foreignKey=@ForeignKey(name="FK_auspicio_tipodoc"))
	private Tipodocumento tipodocumento;
	
	@Column(name="CANTIDAD")
	private Integer cantidad;
	
	@Column(name="DESCIPCION")
	private String descripcion;
	
	@Column(name="MONTO_UNITARIO")
	private float montounitario;
	
	@Column(name="MONTO_TOTAL")
	private float montototal;
	
	@Column(name="FECHA_REGISTRO",nullable=true)
	private Timestamp fecha_registro;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="USUARIOID",nullable=false,foreignKey=@ForeignKey(name="FK_auspicio_usualianza"))
	private UsuarioAlianza usuario_alianza;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Tipodocumento getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(Tipodocumento tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getMontounitario() {
		return montounitario;
	}

	public void setMontounitario(float montounitario) {
		this.montounitario = montounitario;
	}

	public float getMontototal() {
		return montototal;
	}

	public void setMontototal(float montototal) {
		this.montototal = montototal;
	}

	public Timestamp getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Timestamp fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public UsuarioAlianza getUsuario_alianza() {
		return usuario_alianza;
	}

	public void setUsuario_alianza(UsuarioAlianza usuario_alianza) {
		this.usuario_alianza = usuario_alianza;
	}

}
