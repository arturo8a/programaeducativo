package com.progeduc.model;

import java.sql.Timestamp;
import java.time.LocalDate;

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
@Table(name="PARTICIPANTE")
public class Participante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="APPATERNOESTUDIANTE",nullable=true,length=150)
	private String appaternoestudiante;
	
	@Column(name="APMATERNOESTUDIANTE",nullable=true,length=150)
	private String apmaternoestudiante;
	
	@Column(name="NOMBREESTUDIANTE",nullable=true,length=150)
	private String nombreestudiante;
	
	@ManyToOne
	@JoinColumn(name="TIPODOCUMENTOESTUDIANTE",nullable=true,foreignKey=@ForeignKey(name="FK_participante_tipodocest"))
	private Tipodocumento tipodocumentoestudiante;
	
	@Column(name="NRODOCUMENTOESTUDIANTE",nullable=true,length=15)
	private String nrodocumentoestudiante;
	
	@Column(name="FECHANACIMIENTOESTUDIANTE")
	private LocalDate fechanacimientoestudiante;
	
	@ManyToOne
	@JoinColumn(name="GENEROESTUDIANTE",nullable=true,foreignKey=@ForeignKey(name="FK_participante_generoest"))
	private Genero generoestudiante;
	
	@ManyToOne
	@JoinColumn(name="GRADOOESTUDIANTE",nullable=true,foreignKey=@ForeignKey(name="FK_participante_gradoest"))
	private Gradoparticipante gradoestudiante;
	
	@Column(name="SECCION",nullable=true,length=20)
	private String seccion;
	
	@Column(name="CATEGORIACUENTO")
	private Integer categoriacuento;
	
	@Column(name="CATEGORIAPOESIA")
	private Integer categoriapoesia;
	
	@Column(name="CATEGORIADIBUJOPINTURA")
	private Integer categoriadibujopintura;
	
	@Column(name="CATEGORIACOMPOSICIONMUSICAL")
	private Integer categoriacomposicionmusical;
	
	@Column(name="CATEGORIAAHORROAGUA")
	private Integer categoriaahorroagua;
	
	@Column(name="CATEGORIADOCENTE")
	private Integer categoriadocente;
	
	@Column(name="MODALIDADPINDIVIDUAL")
	private Integer modalidadpostulacionindividual;
	
	@Column(name="MODALIDADPGRUPAL")
	private Integer modalidadpostulaciongrupal;
	
	@Column(name="APPATERNOPMT",nullable=true,length=150)
	private String appaternopmt;
	
	@Column(name="APMATERNOPMT",nullable=true,length=150)
	private String apmaternopmt;
	
	@Column(name="NOMBREPMT",nullable=true,length=150)
	private String nombrepmt;
	
	@ManyToOne
	@JoinColumn(name="PARENTESCO",nullable=true,foreignKey=@ForeignKey(name="FK_participante_parentesco"))
	private Parentesco parentesco;
	
	@ManyToOne
	@JoinColumn(name="TIPODOCUMENTOPMT",nullable=true,foreignKey=@ForeignKey(name="FK_participante_tipodocpmt"))
	private Tipodocumento tipodocumentopmt;
	
	@Column(name="NRODOCUMENTOPMT",nullable=true,length=15)
	private String nrodocumentopmt;
	
	@Column(name="NROTELEFONOPMT",nullable=true,length=10)
	private String nrotelefonopmt;
	
	@Column(name="CORREOELECTRONICOPMT",nullable=true,length=150)
	private String correoelectronicopmt;	
	
	@Column(name="ARCHIVO",nullable=true,length=150)
	private String archivo;
	
	@Column(name="ESTADO")
	private Integer estado;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="PROGRAMAEDUCATIVOID",nullable=true,foreignKey=@ForeignKey(name="FK_participante_pe"))
	private Programaeducativo programaeducativo;
	
	@JsonIgnore
	@Column(name="ANHIO",nullable=true)
	private Integer anhio;
	
	@JsonIgnore
	@Column(name="FECHA_REGISTRO",nullable=true)
	private Timestamp fecha_registro;
	
	@Column(name="TIPOPARTICIPANTE",nullable=true,length=150)
	private String tipoparticipante;
	
	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getCategoriacuento() {
		return categoriacuento;
	}

	public void setCategoriacuento(Integer categoriacuento) {
		this.categoriacuento = categoriacuento;
	}

	public Integer getCategoriapoesia() {
		return categoriapoesia;
	}

	public void setCategoriapoesia(Integer categoriapoesia) {
		this.categoriapoesia = categoriapoesia;
	}

	public Integer getCategoriadibujopintura() {
		return categoriadibujopintura;
	}

	public void setCategoriadibujopintura(Integer categoriadibujopintura) {
		this.categoriadibujopintura = categoriadibujopintura;
	}

	public Integer getCategoriacomposicionmusical() {
		return categoriacomposicionmusical;
	}

	public void setCategoriacomposicionmusical(Integer categoriacomposicionmusical) {
		this.categoriacomposicionmusical = categoriacomposicionmusical;
	}

	public Integer getCategoriaahorroagua() {
		return categoriaahorroagua;
	}

	public void setCategoriaahorroagua(Integer categoriaahorroagua) {
		this.categoriaahorroagua = categoriaahorroagua;
	}

	public Integer getModalidadpostulacionindividual() {
		return modalidadpostulacionindividual;
	}

	public void setModalidadpostulacionindividual(Integer modalidadpostulacionindividual) {
		this.modalidadpostulacionindividual = modalidadpostulacionindividual;
	}

	public Integer getModalidadpostulaciongrupal() {
		return modalidadpostulaciongrupal;
	}

	public void setModalidadpostulaciongrupal(Integer modalidadpostulaciongrupal) {
		this.modalidadpostulaciongrupal = modalidadpostulaciongrupal;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppaternoestudiante() {
		return appaternoestudiante;
	}

	public void setAppaternoestudiante(String appaternoestudiante) {
		this.appaternoestudiante = appaternoestudiante;
	}

	public String getApmaternoestudiante() {
		return apmaternoestudiante;
	}

	public void setApmaternoestudiante(String apmaternoestudiante) {
		this.apmaternoestudiante = apmaternoestudiante;
	}

	public String getNombreestudiante() {
		return nombreestudiante;
	}

	public void setNombreestudiante(String nombreestudiante) {
		this.nombreestudiante = nombreestudiante;
	}

	public Tipodocumento getTipodocumentoestudiante() {
		return tipodocumentoestudiante;
	}

	public void setTipodocumentoestudiante(Tipodocumento tipodocumentoestudiante) {
		this.tipodocumentoestudiante = tipodocumentoestudiante;
	}

	public String getNrodocumentoestudiante() {
		return nrodocumentoestudiante;
	}

	public void setNrodocumentoestudiante(String nrodocumentoestudiante) {
		this.nrodocumentoestudiante = nrodocumentoestudiante;
	}

	public LocalDate getFechanacimientoestudiante() {
		return fechanacimientoestudiante;
	}

	public void setFechanacimientoestudiante(LocalDate fechanacimientoestudiante) {
		this.fechanacimientoestudiante = fechanacimientoestudiante;
	}

	public Genero getGeneroestudiante() {
		return generoestudiante;
	}

	public void setGeneroestudiante(Genero generoestudiante) {
		this.generoestudiante = generoestudiante;
	}

	public Gradoparticipante getGradoestudiante() {
		return gradoestudiante;
	}

	public void setGradoestudiante(Gradoparticipante gradoestudiante) {
		this.gradoestudiante = gradoestudiante;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public String getAppaternopmt() {
		return appaternopmt;
	}

	public void setAppaternopmt(String appaternopmt) {
		this.appaternopmt = appaternopmt;
	}

	public String getApmaternopmt() {
		return apmaternopmt;
	}

	public void setApmaternopmt(String apmaternopmt) {
		this.apmaternopmt = apmaternopmt;
	}

	public String getNombrepmt() {
		return nombrepmt;
	}

	public void setNombrepmt(String nombrepmt) {
		this.nombrepmt = nombrepmt;
	}

	public Parentesco getParentesco() {
		return parentesco;
	}

	public void setParentesco(Parentesco parentesco) {
		this.parentesco = parentesco;
	}

	public Tipodocumento getTipodocumentopmt() {
		return tipodocumentopmt;
	}

	public void setTipodocumentopmt(Tipodocumento tipodocumentopmt) {
		this.tipodocumentopmt = tipodocumentopmt;
	}

	public String getNrodocumentopmt() {
		return nrodocumentopmt;
	}

	public void setNrodocumentopmt(String nrodocumentopmt) {
		this.nrodocumentopmt = nrodocumentopmt;
	}

	public String getNrotelefonopmt() {
		return nrotelefonopmt;
	}

	public void setNrotelefonopmt(String nrotelefonopmt) {
		this.nrotelefonopmt = nrotelefonopmt;
	}

	public String getCorreoelectronicopmt() {
		return correoelectronicopmt;
	}

	public void setCorreoelectronicopmt(String correoelectronicopmt) {
		this.correoelectronicopmt = correoelectronicopmt;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
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

	public Integer getCategoriadocente() {
		return categoriadocente;
	}

	public void setCategoriadocente(Integer categoriadocente) {
		this.categoriadocente = categoriadocente;
	}

	public String getTipoparticipante() {
		return tipoparticipante;
	}

	public void setTipoparticipante(String tipoparticipante) {
		this.tipoparticipante = tipoparticipante;
	}

}
