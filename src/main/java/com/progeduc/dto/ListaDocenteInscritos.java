package com.progeduc.dto;

import java.sql.Timestamp;

public class ListaDocenteInscritos {
	
	Integer anio;
	String ods;
	String codigo_ie;
	String nombre_ie;
	String inscrito_ie;
	String docente;
	String tipodocumento;
	String nrodocumento;
	String correoelectronico;
	String nrotelefono;
	String genero;
	Timestamp fecha_registro;
	
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public String getOds() {
		return ods;
	}
	public void setOds(String ods) {
		this.ods = ods;
	}
	public String getCodigo_ie() {
		return codigo_ie;
	}
	public void setCodigo_ie(String codigo_ie) {
		this.codigo_ie = codigo_ie;
	}
	public String getNombre_ie() {
		return nombre_ie;
	}
	public void setNombre_ie(String nombre_ie) {
		this.nombre_ie = nombre_ie;
	}
	public String getInscrito_ie() {
		return inscrito_ie;
	}
	public void setInscrito_ie(String inscrito_ie) {
		this.inscrito_ie = inscrito_ie;
	}
	public String getDocente() {
		return docente;
	}
	public void setDocente(String docente) {
		this.docente = docente;
	}
	public String getTipodocumento() {
		return tipodocumento;
	}
	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}
	public String getNrodocumento() {
		return nrodocumento;
	}
	public void setNrodocumento(String nrodocumento) {
		this.nrodocumento = nrodocumento;
	}
	public String getCorreoelectronico() {
		return correoelectronico;
	}
	public void setCorreoelectronico(String correoelectronico) {
		this.correoelectronico = correoelectronico;
	}
	public String getNrotelefono() {
		return nrotelefono;
	}
	public void setNrotelefono(String nrotelefono) {
		this.nrotelefono = nrotelefono;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Timestamp getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(Timestamp fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
}
