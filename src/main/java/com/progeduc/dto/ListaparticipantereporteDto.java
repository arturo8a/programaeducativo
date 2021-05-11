package com.progeduc.dto;

public class ListaparticipantereporteDto  implements Comparable{
	
	String ods;
	String codigoie;
	String nomie;
	Integer anio;
	String appaternoest;
	String apmaternoest;
	String nombreest;
	String tipodocest;
	String nrodocest;
	String fechanacimientoest;
	String generoest;
	String nivelest;
	String gradoest;
	String nivelparticipacion;
	String seccionest;
	String categoriaest;
	String modalidadest;
	String appaternoapoderado;
	String apmaternoapoderado;
	String nombreapoderado;
	String parentescoapoderado;
	String tipodocapoderado;
	String nrodocapoderado;
	String nrotelfapoderado;
	String correoapoderado;
	
	public String getNivelparticipacion() {
		return nivelparticipacion;
	}
	public void setNivelparticipacion(String nivelparticipacion) {
		this.nivelparticipacion = nivelparticipacion;
	}
	public String getAppaternoest() {
		return appaternoest;
	}
	public String getCodigoie() {
		return codigoie;
	}
	public void setCodigoie(String codigoie) {
		this.codigoie = codigoie;
	}
	public String getNomie() {
		return nomie;
	}
	public void setNomie(String nomie) {
		this.nomie = nomie;
	}
	public void setAppaternoest(String appaternoest) {
		this.appaternoest = appaternoest;
	}
	public String getApmaternoest() {
		return apmaternoest;
	}
	public void setApmaternoest(String apmaternoest) {
		this.apmaternoest = apmaternoest;
	}
	public String getNombreest() {
		return nombreest;
	}
	public void setNombreest(String nombreest) {
		this.nombreest = nombreest;
	}
	public String getTipodocest() {
		return tipodocest;
	}
	public void setTipodocest(String tipodocest) {
		this.tipodocest = tipodocest;
	}
	public String getNrodocest() {
		return nrodocest;
	}
	public void setNrodocest(String nrodocest) {
		this.nrodocest = nrodocest;
	}
	public String getFechanacimientoest() {
		return fechanacimientoest;
	}
	public void setFechanacimientoest(String fechanacimientoest) {
		this.fechanacimientoest = fechanacimientoest;
	}
	public String getGeneroest() {
		return generoest;
	}
	public void setGeneroest(String generoest) {
		this.generoest = generoest;
	}
	public String getNivelest() {
		return nivelest;
	}
	public void setNivelest(String nivelest) {
		this.nivelest = nivelest;
	}
	public String getGradoest() {
		return gradoest;
	}
	public void setGradoest(String gradoest) {
		this.gradoest = gradoest;
	}
	public String getSeccionest() {
		return seccionest;
	}
	public void setSeccionest(String seccionest) {
		this.seccionest = seccionest;
	}
	public String getCategoriaest() {
		return categoriaest;
	}
	public void setCategoriaest(String categoriaest) {
		this.categoriaest = categoriaest;
	}
	public String getModalidadest() {
		return modalidadest;
	}
	public void setModalidadest(String modalidadest) {
		this.modalidadest = modalidadest;
	}
	public String getAppaternoapoderado() {
		return appaternoapoderado;
	}
	public void setAppaternoapoderado(String appaternoapoderado) {
		this.appaternoapoderado = appaternoapoderado;
	}
	public String getApmaternoapoderado() {
		return apmaternoapoderado;
	}
	public void setApmaternoapoderado(String apmaternoapoderado) {
		this.apmaternoapoderado = apmaternoapoderado;
	}
	public String getNombreapoderado() {
		return nombreapoderado;
	}
	public void setNombreapoderado(String nombreapoderado) {
		this.nombreapoderado = nombreapoderado;
	}
	public String getParentescoapoderado() {
		return parentescoapoderado;
	}
	public void setParentescoapoderado(String parentescoapoderado) {
		this.parentescoapoderado = parentescoapoderado;
	}
	public String getTipodocapoderado() {
		return tipodocapoderado;
	}
	public void setTipodocapoderado(String tipodocapoderado) {
		this.tipodocapoderado = tipodocapoderado;
	}
	public String getNrodocapoderado() {
		return nrodocapoderado;
	}
	public void setNrodocapoderado(String nrodocapoderado) {
		this.nrodocapoderado = nrodocapoderado;
	}
	public String getNrotelfapoderado() {
		return nrotelfapoderado;
	}
	public void setNrotelfapoderado(String nrotelfapoderado) {
		this.nrotelfapoderado = nrotelfapoderado;
	}
	public String getCorreoapoderado() {
		return correoapoderado;
	}
	public void setCorreoapoderado(String correoapoderado) {
		this.correoapoderado = correoapoderado;
	}
	public String getOds() {
		return ods;
	}
	public void setOds(String ods) {
		this.ods = ods;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	
	@Override
	public int compareTo(Object o) {
		
		ListaparticipantereporteDto otro = (ListaparticipantereporteDto) o;
		int comparacionPorTipo = ods.compareTo(otro.getOds());
		if(comparacionPorTipo!=0)
			return comparacionPorTipo;
		int rpta = nomie.compareTo(otro.getNomie());
		return rpta;
		
	}
}
