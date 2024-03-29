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
	
	@Column(name="REVALORACION",nullable=true)
	private Integer revaloracion;
	
	@Column(name="BUENUSO",nullable=true)
	private Integer buenuso;
	
	@Column(name="IMPORTANCIA",nullable=true)
	private Integer importancia;
	
	@Column(name="VINCULO",nullable=true)
	private Integer vinculo;
	
	@Column(name="PUESTO",nullable=true)
	private Integer puesto;
	
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
	
	@ManyToOne
	@JoinColumn(name="estadotrabajoid",nullable=true,foreignKey=@ForeignKey(name="FK_trabajosfinales_esttrab"))
	private Estadotrabajo estadotrabajo;
	
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
	
	@JsonIgnore
	@Column(name="NUMERACION",nullable=true)
	private Integer numeracion;
	
	@Column(name="ENVIADO",nullable=true)
	private Integer enviado;
	
	@JsonIgnore
	@Column(name="ANIO",nullable=true)
	private Integer anio;
	
	@Column(name="NOTA",nullable=true)
	private Float nota;
	
	@Column(name="NOTA_ORIGINAL",nullable=true)
	private Float nota_original;
	
	@Column(name="EMPATE",nullable=true)
	private Integer empate;	
	
	@ManyToOne
	@JoinColumn(name="estadonacional",nullable=true,foreignKey=@ForeignKey(name="FK_trabajos_nacional"))
	private Estadotrabajo estadonacional;
	
	@Column(name="NOTA_NACIONAL",nullable=true)
	private Float nota_nacional;
	
	@Column(name="EMPATE_NACIONAL",nullable=true)
	private Integer empate_nacional;
	
	@Column(name="PUESTO_NACIONAL",nullable=true)
	private Integer puesto_nacional;
	
	@Column(name="NOTA_ORIGINAL_NA",nullable=true)
	private Float nota_original_nacional;
	
	
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

	public Integer getRevaloracion() {
		return revaloracion;
	}

	public void setRevaloracion(Integer revaloracion) {
		this.revaloracion = revaloracion;
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

	public Integer getPuesto() {
		return puesto;
	}

	public void setPuesto(Integer puesto) {
		this.puesto = puesto;
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

	public Estadotrabajo getEstadotrabajo() {
		return estadotrabajo;
	}

	public void setEstadotrabajo(Estadotrabajo estadotrabajo) {
		this.estadotrabajo = estadotrabajo;
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
	
	public Integer getNumeracion() {
		return numeracion;
	}

	public void setNumeracion(Integer numeracion) {
		this.numeracion = numeracion;
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

	public Float getNota() {
		return nota;
	}

	public void setNota(Float nota) {
		this.nota = nota;
	}

	public Float getNota_original() {
		return nota_original;
	}

	public void setNota_original(Float nota_original) {
		this.nota_original = nota_original;
	}

	public Integer getEmpate() {
		return empate;
	}

	public void setEmpate(Integer empate) {
		this.empate = empate;
	}

	public Estadotrabajo getEstadonacional() {
		return estadonacional;
	}

	public void setEstadonacional(Estadotrabajo estadonacional) {
		this.estadonacional = estadonacional;
	}

	public Float getNota_nacional() {
		return nota_nacional;
	}

	public void setNota_nacional(Float nota_nacional) {
		this.nota_nacional = nota_nacional;
	}

	public Integer getEmpate_nacional() {
		return empate_nacional;
	}

	public void setEmpate_nacional(Integer empate_nacional) {
		this.empate_nacional = empate_nacional;
	}

	public Integer getPuesto_nacional() {
		return puesto_nacional;
	}

	public void setPuesto_nacional(Integer puesto_nacional) {
		this.puesto_nacional = puesto_nacional;
	}

	public Float getNota_original_nacional() {
		return nota_original_nacional;
	}

	public void setNota_original_nacional(Float nota_original_nacional) {
		this.nota_original_nacional = nota_original_nacional;
	}
	
	
}
