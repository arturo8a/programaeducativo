package com.progeduc.dto;

import java.sql.Timestamp;

public class ListaDocente {
	
	Integer id;
	String appaterno;
	String apmaterno;
	String nombre;
	String tipodocumento;
	String nrodocumento;
	String nrotelefono;
	String correoelectronico;
	Timestamp fecha_registro;
	String nomie;
	
	public String getCorreoelectronico() {
		return correoelectronico;
	}
	public void setCorreoelectronico(String correoelectronico) {
		this.correoelectronico = correoelectronico;
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
	public String getNrotelefono() {
		return nrotelefono;
	}
	public void setNrotelefono(String nrotelefono) {
		this.nrotelefono = nrotelefono;
	}
	
	public Timestamp getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(Timestamp fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	public String getNomie() {
		return nomie;
	}
	public void setNomie(String nomie) {
		this.nomie = nomie;
	}
	
	
}
