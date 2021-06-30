package com.progeduc.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="USUARIO_ALIANZA")
public class UsuarioAlianza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="ODSID")
	private Ods ods;
	
	@Column(name="ANIO")
	private Integer anio;
	
	@ManyToOne
	@JoinColumn(name="CATEGORIAID",foreignKey=@ForeignKey(name="FK_usuario_alianza_cateval"))
	private Categoriaevaluacion categoria;
	
	@Column(name="ENTIDAD")
	private String entidad;
	
	@Column(name="DIRECCION")
	private String direccion;
	
	@Column(name="COMITE_TECNICO")
	private String comitetecnico;
	
	@Column(name="COMITE_EVALUADOR")
	private String comiteevaluador;
	
	@Column(name="AUSPICIADOR")
	private String auspiciador;
	
	@Column(name="ALIADO")
	private String aliado;
	
	@Column(name="NUM_CONTACTO")
	private Integer numcontaccto;
	
	@Column(name="APE_PAT_CONTACTO")
	private String apepatcontacto;
	
	@Column(name="APE_MAT_CONTACTO")
	private String apematcontacto;
	
	@Column(name="NOMBRES_CONTACTO")
	private String nombrecontacto;
	
	@ManyToOne
	@JoinColumn(name="TIPO_DOC_CONTACTO",nullable=true,foreignKey=@ForeignKey(name="FK_usuarioalianza_tipodoc"))
	private Tipodocumento tipodocumento;
	
	@Column(name="NUM_DOC_CONTACTO")
	private String numdocumento;
	
	@Column(name="TELEFONO_UNO")
	private String telefonouno;
	
	@Column(name="TELEFONO_DOS")
	private String telefonodos;
	
	@Column(name="CORREO_CONTACTO")
	private String correocontato;
	
	@Column(name="CARGO_CONTACTO")
	private String cargocontacto;
	
	@Column(name="APE_PAT_AUTORIDAD")
	private String apepatautoridad;
	
	@Column(name="APE_MAT_AUTORIDAD")
	private String apematautoridad;
	
	@Column(name="NOMBRES_AUTORIDAD")
	private String nombresautoridad;
	
	@Column(name="CORREO_AUTORIDAD")
	private String correoautoridad;
	
	@Column(name="CARGO_AUTORIDAD")
	private String cargoautoridad;
	
	@Column(name="USUARIO_AUTORIDAD")
	private String usuarioautoridad;
	
	@JsonIgnore
	@Column(name="PASSWORD_AUTORIDAD")
	private String passwordautoridad;
	
	@Column(name="ENVIAR_OFICIO")
	private String enviaroficio;
	
	@Column(name="NUM_OFICIO")
	private String numoficio;
	
	@Column(name="FECHA_OFICIO")
	private Timestamp fecha_oficio;

	@Column(name="DOC_OFICIO")
	private String docoficio;
	
	@Column(name="ESTADO")
	private String estado;
	
	@OneToMany(mappedBy = "usuario_alianza", cascade = CascadeType.ALL,orphanRemoval=true)
	private List<Auspicio> auspicios;
	
	@Column(name="FECHA_REGISTRO",nullable=true)
	private Timestamp fecha_registro;

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

	public Timestamp getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Timestamp fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public List<Auspicio> getAuspicios() {
		return auspicios;
	}

	public void setAuspicios(List<Auspicio> auspicios) {
		this.auspicios = auspicios;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	/*public Auspicio getAuspicioid() {
		return auspicioid;
	}

	public void setAuspicioid(Auspicio auspicioid) {
		this.auspicioid = auspicioid;
	}*/
	
}
