package com.progeduc.dto;

public class ListaInstitucionEducativa {
	
	Integer id;
	String ods;
	String codmod;
	String nomie;
	Integer anhio;
	String estado;
	String motivoobservacion;
	
	public ListaInstitucionEducativa() {
		
	}
	
	
	public ListaInstitucionEducativa(Integer id, String ods,String codmod,String nomie, Integer anhio, String estado,String motivoobservacion) {
		super();
		this.ods = ods;
		this.id = id;
		this.codmod = codmod;
		this.nomie = nomie;
		this.anhio = anhio;
		this.estado = estado;
		this.motivoobservacion = motivoobservacion;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodmod() {
		return codmod;
	}
	public void setCodmod(String codmod) {
		this.codmod = codmod;
	}
	public String getNomie() {
		return nomie;
	}
	public void setNomie(String nomie) {
		this.nomie = nomie;
	}
	public Integer getAnhio() {
		return anhio;
	}
	public void setAnhio(Integer anhio) {
		this.anhio = anhio;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getMotivoobservacion() {
		return motivoobservacion;
	}
	public void setMotivoobservacion(String motivoobservacion) {
		this.motivoobservacion = motivoobservacion;
	}


	public String getOds() {
		return ods;
	}


	public void setOds(String ods) {
		this.ods = ods;
	}
}
