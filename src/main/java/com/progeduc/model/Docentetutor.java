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
@Table(name="DOCENTETUTOR")
public class Docentetutor {
	
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
	@JoinColumn(name="tipodocid",nullable=true,foreignKey=@ForeignKey(name="FK_progedu_tipodoc"))
	private Tipodocidentprof tipodocumento;
	
	@Column(name="nrodocumento")
	private String nrodocumento; 
	
	@ManyToOne
	@JoinColumn(name="GENEROPROFID",nullable=false,foreignKey=@ForeignKey(name="FK_progeduc_genero"))
	private Generoprof genero;
	
	@Column(name="nrotelefono")
	private String nrotelefono; 
	
	@Column(name="correoelectronico")
	private String correoelectronico; 
	
	@ManyToOne
	@JoinColumn(name="RESPONSABLEREGISTROID",nullable=false,foreignKey=@ForeignKey(name="FK_progeduc_responsable"))
	private Responsableregistro responsable;
	
	@Column(name="curso")
	private String curso;
	
	@Column(name="anio")
	private Integer anio;
	
	@ManyToOne
	@JoinColumn(name="programaeducativoid",nullable=false,foreignKey=@ForeignKey(name="FK_progeduc_docente"))
	private Programaeducativo programaeducativo;
	
	public Docentetutor() {
		
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
	
	public Tipodocidentprof getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(Tipodocidentprof tipodocumento) {
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

	public Responsableregistro getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsableregistro responsable) {
		this.responsable = responsable;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public Programaeducativo getProgramaeducativo() {
		return programaeducativo;
	}

	public void setProgramaeducativo(Programaeducativo programaeducativo) {
		this.programaeducativo = programaeducativo;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

}
