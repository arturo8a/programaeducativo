package com.progeduc.model;

import java.util.Date;

public class ProgeducfiltroDto{
	
	private String fecha_desde;
	
	private String fecha_hasta;
	
	private String nombreie;
	
	private Integer departamento;
	
	private Integer provincia;
	
	private Integer distrito;
	
	private Integer estado;

	public String getFecha_desde() {
		return fecha_desde;
	}

	public void setFecha_desde(String fecha_desde) {
		this.fecha_desde = fecha_desde;
	}

	public String getFecha_hasta() {
		return fecha_hasta;
	}

	public void setFecha_hasta(String fecha_hasta) {
		this.fecha_hasta = fecha_hasta;
	}

	public String getNombreie() {
		return nombreie;
	}

	public void setNombreie(String nombreie) {
		this.nombreie = nombreie;
	}

	public Integer getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Integer departamento) {
		this.departamento = departamento;
	}

	public Integer getProvincia() {
		return provincia;
	}

	public void setProvincia(Integer provincia) {
		this.provincia = provincia;
	}

	public Integer getDistrito() {
		return distrito;
	}

	public void setDistrito(Integer distrito) {
		this.distrito = distrito;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

}
