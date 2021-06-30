package com.progeduc.dto;

import java.sql.Timestamp;
import java.util.List;
import com.progeduc.model.Auspicio;
import com.progeduc.model.Categoriaevaluacion;
import com.progeduc.model.Ods;
import com.progeduc.model.Tipodocumento;

public class UsuarioAlianzaDto {
	
	private Integer id;
	private Ods ods;
	private Integer anio;
	private Categoriaevaluacion categoria;
	private String entidad;
	private String direccion;
	private String comitetecnico;
	private String comiteevaluador;
	private String auspiciador;
	private String aliado;
	private Integer numcontaccto;
	private String apepatcontacto;
	private String apematcontacto;
	private String nombrecontacto;
	private Tipodocumento tipodocumento;
	private String numdocumento;
	private String telefonouno;
	private String telefonodos;
	private String correocontato;
	private String cargocontacto;
	private String apepatautoridad;
	private String apematautoridad;
	private String nombresautoridad;
	private String correoautoridad;
	private String cargoautoridad;
	private String usuarioautoridad;
	private String passwordautoridad;
	private String enviaroficio;
	private String numoficio;
	private Timestamp fecha_oficio;
	private String docoficio;
	private List<Auspicio> auspicios;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Ods getOds() {
		return ods;
	}
	public void setOds(Ods ods) {
		this.ods = ods;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public Categoriaevaluacion getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoriaevaluacion categoria) {
		this.categoria = categoria;
	}
	public String getEntidad() {
		return entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getComitetecnico() {
		return comitetecnico;
	}
	public void setComitetecnico(String comitetecnico) {
		this.comitetecnico = comitetecnico;
	}
	public String getComiteevaluador() {
		return comiteevaluador;
	}
	public void setComiteevaluador(String comiteevaluador) {
		this.comiteevaluador = comiteevaluador;
	}
	public String getAuspiciador() {
		return auspiciador;
	}
	public void setAuspiciador(String auspiciador) {
		this.auspiciador = auspiciador;
	}
	public String getAliado() {
		return aliado;
	}
	public void setAliado(String aliado) {
		this.aliado = aliado;
	}
	public Integer getNumcontaccto() {
		return numcontaccto;
	}
	public void setNumcontaccto(Integer numcontaccto) {
		this.numcontaccto = numcontaccto;
	}
	public String getApepatcontacto() {
		return apepatcontacto;
	}
	public void setApepatcontacto(String apepatcontacto) {
		this.apepatcontacto = apepatcontacto;
	}
	public String getApematcontacto() {
		return apematcontacto;
	}
	public void setApematcontacto(String apematcontacto) {
		this.apematcontacto = apematcontacto;
	}
	public String getNombrecontacto() {
		return nombrecontacto;
	}
	public void setNombrecontacto(String nombrecontacto) {
		this.nombrecontacto = nombrecontacto;
	}
	public Tipodocumento getTipodocumento() {
		return tipodocumento;
	}
	public void setTipodocumento(Tipodocumento tipodocumento) {
		this.tipodocumento = tipodocumento;
	}
	public String getNumdocumento() {
		return numdocumento;
	}
	public void setNumdocumento(String numdocumento) {
		this.numdocumento = numdocumento;
	}
	public String getTelefonouno() {
		return telefonouno;
	}
	public void setTelefonouno(String telefonouno) {
		this.telefonouno = telefonouno;
	}
	public String getTelefonodos() {
		return telefonodos;
	}
	public void setTelefonodos(String telefonodos) {
		this.telefonodos = telefonodos;
	}
	public String getCorreocontato() {
		return correocontato;
	}
	public void setCorreocontato(String correocontato) {
		this.correocontato = correocontato;
	}
	public String getCargocontacto() {
		return cargocontacto;
	}
	public void setCargocontacto(String cargocontacto) {
		this.cargocontacto = cargocontacto;
	}
	public String getApepatautoridad() {
		return apepatautoridad;
	}
	public void setApepatautoridad(String apepatautoridad) {
		this.apepatautoridad = apepatautoridad;
	}
	public String getApematautoridad() {
		return apematautoridad;
	}
	public void setApematautoridad(String apematautoridad) {
		this.apematautoridad = apematautoridad;
	}
	public String getNombresautoridad() {
		return nombresautoridad;
	}
	public void setNombresautoridad(String nombresautoridad) {
		this.nombresautoridad = nombresautoridad;
	}
	public String getCorreoautoridad() {
		return correoautoridad;
	}
	public void setCorreoautoridad(String correoautoridad) {
		this.correoautoridad = correoautoridad;
	}
	public String getCargoautoridad() {
		return cargoautoridad;
	}
	public void setCargoautoridad(String cargoautoridad) {
		this.cargoautoridad = cargoautoridad;
	}
	public String getUsuarioautoridad() {
		return usuarioautoridad;
	}
	public void setUsuarioautoridad(String usuarioautoridad) {
		this.usuarioautoridad = usuarioautoridad;
	}
	public String getPasswordautoridad() {
		return passwordautoridad;
	}
	public void setPasswordautoridad(String passwordautoridad) {
		this.passwordautoridad = passwordautoridad;
	}
	public String getEnviaroficio() {
		return enviaroficio;
	}
	public void setEnviaroficio(String enviaroficio) {
		this.enviaroficio = enviaroficio;
	}
	public String getNumoficio() {
		return numoficio;
	}
	public void setNumoficio(String numoficio) {
		this.numoficio = numoficio;
	}
	public Timestamp getFecha_oficio() {
		return fecha_oficio;
	}
	public void setFecha_oficio(Timestamp fecha_oficio) {
		this.fecha_oficio = fecha_oficio;
	}
	public String getDocoficio() {
		return docoficio;
	}
	public void setDocoficio(String docoficio) {
		this.docoficio = docoficio;
	}
	public List<Auspicio> getAuspicios() {
		return auspicios;
	}
	public void setAuspicios(List<Auspicio> auspicios) {
		this.auspicios = auspicios;
	}
}
