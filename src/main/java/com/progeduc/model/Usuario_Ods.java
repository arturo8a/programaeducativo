package com.progeduc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USUARIO_ODS")
public class Usuario_Ods {
	
	@Id
	@Column(name="ID")
	private Integer id;
	
	@Column(name="ODS",nullable=false,length=100)
	private String ods;
	
	@Column(name="DIRECCION",nullable=false,length=150)
	private String direccion;
	
	@Column(name="USUARIO",nullable=false,length=50)
	private String usuario;
	
	@Column(name="PASSWORD",nullable=false,length=50)
	private String password;
	
	@Column(name="NOMBRES",nullable=false,length=150)
	private String nombres;
	
	@Column(name="APELLIDOS",nullable=false,length=150)
	private String apellidos;
	
	@Column(name="ODSID")
	private Integer odsid;
	
	@Column(name="CORREO",nullable=false,length=150)
	private String correo;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOds() {
		return ods;
	}

	public void setOds(String ods) {
		this.ods = ods;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Integer getOdsid() {
		return odsid;
	}

	public void setOdsid(Integer odsid) {
		this.odsid = odsid;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
}
