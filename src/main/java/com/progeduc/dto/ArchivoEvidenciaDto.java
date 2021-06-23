package com.progeduc.dto;

import java.util.List;

public class ArchivoEvidenciaDto {
	
	String archivo;
	List<String> evidencia;
	
	public ArchivoEvidenciaDto(String archivo1, List<String> evidencia1) {
		this.archivo = archivo1;
		this.evidencia = evidencia1;
	}
	
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	public List<String> getEvidencia() {
		return evidencia;
	}
	public void setEvidencia(List<String> evidencia) {
		this.evidencia = evidencia;
	}
}
