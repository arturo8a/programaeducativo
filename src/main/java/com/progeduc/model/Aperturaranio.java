package com.progeduc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="APERTURARANIO")
public class Aperturaranio {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="ANIO")
	private Integer anio;	
	
	@Column(name="NOMBRECONCURSO" ,length=150)
	private String nombreconcurso;
	
	@Column(name="PRIMERAetapadesde")
	private LocalDate primeraetapadesde;	
	
	@Column(name="PRIMERAetapahasta")
	private LocalDate primeraetapahasta;
	
	@Column(name="SEGUNDAetapadesde")
	private LocalDate segundaetapadesde;	
	
	@Column(name="SEGUNDAetapahasta")
	private LocalDate segundaetapahasta;
	
	@Column(name="TERCERAetapadesde")
	private LocalDate terceraetapadesde;	
	
	@Column(name="TERCERAetapahasta")
	private LocalDate terceraetapahasta;
	
	@Column(name="CUARTAetapadesde")
	private LocalDate cuartaetapadesde;	
	
	@Column(name="CUARTAetapahasta")
	private LocalDate cuartaetapahasta;
	
	@Column(name="QUINTAetapadesde")
	private LocalDate quintaetapadesde;	
	
	@Column(name="QUINTAetapahasta")
	private LocalDate quintaetapahasta;
	
	@Column(name="SEXTAetapadesde")
	private LocalDate sextaetapadesde;	
	
	@Column(name="SEXTAetapahasta")
	private LocalDate sextaetapahasta;
	
	@Column(name="SEPTIMAetapadesde")
	private LocalDate septimaetapadesde;	
	
	@Column(name="SEPTIMAetapahasta")
	private LocalDate septimaetapahasta;
	
	@Column(name="ESTADO")
	private String estado;	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public String getNombreconcurso() {
		return nombreconcurso;
	}

	public void setNombreconcurso(String nombreconcurso) {
		this.nombreconcurso = nombreconcurso;
	}

	public LocalDate getPrimeraetapadesde() {
		return primeraetapadesde;
	}

	public void setPrimeraetapadesde(LocalDate primeraetapadesde) {
		this.primeraetapadesde = primeraetapadesde;
	}

	public LocalDate getPrimeraetapahasta() {
		return primeraetapahasta;
	}

	public void setPrimeraetapahasta(LocalDate primeraetapahasta) {
		this.primeraetapahasta = primeraetapahasta;
	}

	public LocalDate getSegundaetapadesde() {
		return segundaetapadesde;
	}

	public void setSegundaetapadesde(LocalDate segundaetapadesde) {
		this.segundaetapadesde = segundaetapadesde;
	}

	public LocalDate getSegundaetapahasta() {
		return segundaetapahasta;
	}

	public void setSegundaetapahasta(LocalDate segundaetapahasta) {
		this.segundaetapahasta = segundaetapahasta;
	}

	public LocalDate getTerceraetapadesde() {
		return terceraetapadesde;
	}

	public void setTerceraetapadesde(LocalDate terceraetapadesde) {
		this.terceraetapadesde = terceraetapadesde;
	}

	public LocalDate getTerceraetapahasta() {
		return terceraetapahasta;
	}

	public void setTerceraetapahasta(LocalDate terceraetapahasta) {
		this.terceraetapahasta = terceraetapahasta;
	}

	public LocalDate getCuartaetapadesde() {
		return cuartaetapadesde;
	}

	public void setCuartaetapadesde(LocalDate cuartaetapadesde) {
		this.cuartaetapadesde = cuartaetapadesde;
	}

	public LocalDate getCuartaetapahasta() {
		return cuartaetapahasta;
	}

	public void setCuartaetapahasta(LocalDate cuartaetapahasta) {
		this.cuartaetapahasta = cuartaetapahasta;
	}

	public LocalDate getQuintaetapadesde() {
		return quintaetapadesde;
	}

	public void setQuintaetapadesde(LocalDate quintaetapadesde) {
		this.quintaetapadesde = quintaetapadesde;
	}

	public LocalDate getQuintaetapahasta() {
		return quintaetapahasta;
	}

	public void setQuintaetapahasta(LocalDate quintaetapahasta) {
		this.quintaetapahasta = quintaetapahasta;
	}

	public LocalDate getSextaetapadesde() {
		return sextaetapadesde;
	}

	public void setSextaetapadesde(LocalDate sextaetapadesde) {
		this.sextaetapadesde = sextaetapadesde;
	}

	public LocalDate getSextaetapahasta() {
		return sextaetapahasta;
	}

	public void setSextaetapahasta(LocalDate sextaetapahasta) {
		this.sextaetapahasta = sextaetapahasta;
	}

	public LocalDate getseptimaetapadesde() {
		return septimaetapadesde;
	}

	public void setSeptimaetapadesde(LocalDate septimaetapadesde) {
		this.septimaetapadesde = septimaetapadesde;
	}

	public LocalDate getSeptimaetapahasta() {
		return septimaetapahasta;
	}

	public void setSeptimaetapahasta(LocalDate septimaetapahasta) {
		this.septimaetapahasta = septimaetapahasta;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
