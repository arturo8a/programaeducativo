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
@Table(name="DOCENTE")
public class Docente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="appaterno")
	private String appaterno;
	
	@Column(name="apmaterno")
	private String apmaterno;
	
	@Column(name="nombre")
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="tipodocid",nullable=true,foreignKey=@ForeignKey(name="FK_docente_tipodoc"))
	private Tipodocumento tipodocumento;
	
	@Column(name="nrodocumento")
	private String nrodocumento; 
	
	@ManyToOne
	@JoinColumn(name="GENEROPROFID",nullable=false,foreignKey=@ForeignKey(name="FK_docente_genero"))
	private Generoprof genero;
	
	@Column(name="nrotelefono")
	private String nrotelefono; 
	
	@Column(name="correoelectronico")
	private String correoelectronico; 
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="programaeducativoid",nullable=false,foreignKey=@ForeignKey(name="FK_docente_progeduc"))
	private Programaeducativo programaeducativo;
	
	@JsonIgnore
	@Column(name="ANHIO",nullable=true)
	private Integer anhio;
	
	@JsonIgnore
	@Column(name="FECHA_REGISTRO",nullable=true)
	private Timestamp fecha_registro;	

	@Column(name="estado")
	private Integer estado;
	
	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppaterno() {
		return appaterno;
	}

	public void setAppaterno(String appaterno) {
		this.appaterno = appaterno;
	}

	public String getApmaterno() {
		return apmaterno;
	}

	public void setApmaterno(String apmaterno) {
		this.apmaterno = apmaterno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tipodocumento getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(Tipodocumento tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public String getNrodocumento() {
		return nrodocumento;
	}

	public void setNrodocumento(String nrodocumento) {
		this.nrodocumento = nrodocumento;
	}

	public Generoprof getGenero() {
		return genero;
	}

	public void setGenero(Generoprof genero) {
		this.genero = genero;
	}

	public String getNrotelefono() {
		return nrotelefono;
	}

	public void setNrotelefono(String nrotelefono) {
		this.nrotelefono = nrotelefono;
	}

	public String getCorreoelectronico() {
		return correoelectronico;
	}

	public void setCorreoelectronico(String correoelectronico) {
		this.correoelectronico = correoelectronico;
	}

	public Programaeducativo getProgramaeducativo() {
		return programaeducativo;
	}

	public void setProgramaeducativo(Programaeducativo programaeducativo) {
		this.programaeducativo = programaeducativo;
	}

	public Integer getAnhio() {
		return anhio;
	}

	public void setAnhio(Integer anhio) {
		this.anhio = anhio;
	}

	public Timestamp getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Timestamp fecha_registro) {
		this.fecha_registro = fecha_registro;
	}	
}
