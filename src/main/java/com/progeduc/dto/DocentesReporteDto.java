package com.progeduc.dto;

public class DocentesReporteDto {
	
	String ods;
	String departamento;
	String provincia;
	String apellidoPaterno;
	String apellidoMaterno;
	String nombre;
	String dni;
	String codigoie;
	String nombreie;
	String  ugel;
	String fecharegistro;	
	
	public DocentesReporteDto(String ods, String departamento, String provincia, String apellidoPaterno,
			String apellidoMaterno, String nombre, String dni, String codigoie, String nombreie, String ugel, String fecharegistro) {
		super();
		this.ods = ods;
		this.departamento = departamento;
		this.provincia = provincia;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.nombre = nombre;
		this.dni = dni;
		this.codigoie = codigoie;
		this.nombreie = nombreie;
		this.ugel = ugel;
		this.fecharegistro = fecharegistro;
	}
	public String getOds() {
		return ods;
	}
	public void setOds(String ods) {
		this.ods = ods;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getCodigoie() {
		return codigoie;
	}
	public void setCodigoie(String codigoie) {
		this.codigoie = codigoie;
	}
	public String getNombreie() {
		return nombreie;
	}
	public void setNombreie(String nombreie) {
		this.nombreie = nombreie;
	}
	public String getUgel() {
		return ugel;
	}
	public void setUgel(String ugel) {
		this.ugel = ugel;
	}
	public String getFecharegistro() {
		return fecharegistro;
	}
	public void setFecharegistro(String fecharegistro) {
		this.fecharegistro = fecharegistro;
	}
	
}

