package com.progeduc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="USUARIO")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="USUARIO",length=50)
	private String usuario;
	
	@Column(name="PASSWORD",length=50)
	private String password;
	
	@Column(name="NOMBRE",length=200)
	private String nombre;
	
	@Column(name="EMAIL",length=200)
	private String email;
	
	@Column(name="DNI",length=12)
	private String dni;
	
	@Column(name="CIUDAD",length=100)
	private String ciudad;
	
	@ManyToOne
	@JoinColumn(name="tipousuarioid",foreignKey=@ForeignKey(name="FK_usuario_tipo"))
	private Tipousuario tipousuario;
	
	@Column(name="ESTADO",length=50)
	private String estado;
	
	@ManyToOne
	@JoinColumn(name="odsid",foreignKey=@ForeignKey(name="FK_usuario_ods"))
	private Ods ods;
	
	@Column(name="CICLO",length=5)
	private String ciclo;
	
	public Usuario() {
		
	}
	
	public Usuario(String usuario,String password,String nombre,String email,String dni,String ciudad,Tipousuario tu,String estado,Ods ods,String ciclo) {
		this.usuario=usuario;
		this.password = password;
		this.nombre = nombre;
		this.email = email;
		this.dni = dni;
		this.ciudad = ciudad;
		this.tipousuario=tu;
		this.estado = estado;
		this.ods = ods;
		this.ciclo = ciclo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Tipousuario getTipousuario() {
		return tipousuario;
	}

	public void setTipousuario(Tipousuario tipousuario) {
		this.tipousuario = tipousuario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Ods getOds() {
		return ods;
	}

	public void setOds(Ods ods) {
		this.ods = ods;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}	
}
