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
@Table(name="TRABAJOSFINALES")
public class Trabajosfinales {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	
	@ManyToOne
	@JoinColumn(name="categoriatrabajoid",nullable=true,foreignKey=@ForeignKey(name="FK_trabajosfinales_cattrabajo"))
	private Categoriatrabajo categoriatrabajo;
	
	@ManyToOne
	@JoinColumn(name="modalidadtrabajoid",nullable=true,foreignKey=@ForeignKey(name="FK_trabajosfinales_modtrabajo"))
	private Modalidadtrabajo modalidadtrabajo;
	
	@Column(name="TITULOTRABAJO",nullable=true,length=250)
	private String titulotrabajo;
	
	@Column(name="LINKVIDEO",nullable=true,length=250)
	private String linkvideo;
	
	@Column(name="CONVERSACION",nullable=true)
	private Integer conversacion;
	
	@Column(name="VALORACIONAGUA",nullable=true)
	private Integer valoracionagua;
	
	@Column(name="VALORACIONALCANTARILLADO",nullable=true)
	private Integer valoracionalcantarillado;
	
	@Column(name="BUENUSO",nullable=true)
	private Integer buenuso;
	
	@Column(name="IMPORTANCIA",nullable=true)
	private Integer importancia;
	
	@Column(name="VINCULO",nullable=true)
	private Integer vinculo;
	
	@Column(name="CARENCIAS",nullable=true)
	private Integer carencias;
	
	@Column(name="APPATERNO",nullable=true,length=150)
	private String appaterno;
	
	@Column(name="APMATERNO",nullable=true,length=150)
	private String apmaterno;
	
	@Column(name="NOMBRE",nullable=true,length=150)
	private String nombre;	

	@ManyToOne
	@JoinColumn(name="tipodocumentoid",nullable=true,foreignKey=@ForeignKey(name="FK_trabajosfinales_tipodoc"))
	private Tipodocumento tipodocumento;
	
	@Column(name="NRODOCUMENTO",nullable=true,length=15)
	private String nrodocumento;	
	
	@Column(name="TELEFONO",nullable=true,length=15)
	private String telefono;	
	
	@Column(name="CORREO",nullable=true,length=150)
	private String correo;
	
	@ManyToOne
	@JoinColumn(name="generoprofid",nullable=true,foreignKey=@ForeignKey(name="FK_trabajosfinales_genero"))
	private Genero genero;
	
	@ManyToOne
	@JoinColumn(name="PROGRAMAEDUCATIVOID",nullable=true,foreignKey=@ForeignKey(name="FK_trabajosfinales_progeduc"))
	private Programaeducativo programaeducativo;
	
	@JsonIgnore
	@Column(name="FECHA_REGISTRO",nullable=true)
	private Timestamp fecha_registro;	
	
	@Column(name="ESTADO",nullable=true)
	private Integer estado;
	
	@Column(name="ENVIADO",nullable=true)
	private Integer enviado;
	
	@JsonIgnore
	@Column(name="ANIO",nullable=true)
	private Integer anio;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Categoriatrabajo getCategoriatrabajo() {
		return categoriatrabajo;
	}

	public void setCategoriatrabajo(Categoriatrabajo categoriatrabajo) {
		this.categoriatrabajo = categoriatrabajo;
	}

	public Modalidadtrabajo getModalidadtrabajo() {
		return modalidadtrabajo;
	}

	public void setModalidadtrabajo(Modalidadtrabajo modalidadtrabajo) {
		this.modalidadtrabajo = modalidadtrabajo;
	}

	public String getTitulotrabajo() {
		return titulotrabajo;
	}

	public void setTitulotrabajo(String titulotrabajo) {
		this.titulotrabajo = titulotrabajo;
	}

	public String getLinkvideo() {
		return linkvideo;
	}

	public void setLinkvideo(String linkvideo) {
		this.linkvideo = linkvideo;
	}

	public Integer getConversacion() {
		return conversacion;
	}

	public void setConversacion(Integer conversacion) {
		this.conversacion = conversacion;
	}

	public Integer getValoracionagua() {
		return valoracionagua;
	}

	public void setValoracionagua(Integer valoracionagua) {
		this.valoracionagua = valoracionagua;
	}

	public Integer getValoracionalcantarillado() {
		return valoracionalcantarillado;
	}

	public void setValoracionalcantarillado(Integer valoracionalcantarillado) {
		this.valoracionalcantarillado = valoracionalcantarillado;
	}

	public Integer getBuenuso() {
		return buenuso;
	}

	public void setBuenuso(Integer buenuso) {
		this.buenuso = buenuso;
	}

	public Integer getImportancia() {
		return importancia;
	}

	public void setImportancia(Integer importancia) {
		this.importancia = importancia;
	}

	public Integer getVinculo() {
		return vinculo;
	}

	public void setVinculo(Integer vinculo) {
		this.vinculo = vinculo;
	}

	public Integer getCarencias() {
		return carencias;
	}

	public void setCarencias(Integer carencias) {
		this.carencias = carencias;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Programaeducativo getProgramaeducativo() {
		return programaeducativo;
	}

	public void setProgramaeducativo(Programaeducativo programaeducativo) {
		this.programaeducativo = programaeducativo;
	}

	public Timestamp getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Timestamp fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Integer getEnviado() {
		return enviado;
	}

	public void setEnviado(Integer enviado) {
		this.enviado = enviado;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}
}
