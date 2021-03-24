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
@Table(name="PROGRAMAEDUCATIVO")
public class Programaeducativo {	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	
	@Column(name="CODMOD",nullable=true,length=70)
	private String codmod;	//codigo de local I.E
	
	@Column(name="NOMIE",nullable=true,length=100)
	private String nomie; //nombre de la I.E	
	
	@ManyToOne
	@JoinColumn(name="tipoieid",nullable=true,foreignKey=@ForeignKey(name="FK_programaeducativo_tipoie"))
	private Tipoie tipoie;
	
	@ManyToOne
	@JoinColumn(name="distritoid",nullable=true,foreignKey=@ForeignKey(name="FK_programaeducativo_distrito"))
	private Distrito distrito;	
	
	@Column(name="DIRIE",nullable=true,length=250)
	private String dirie;//direccion	
	
	@Column(name="DRE",nullable=true,length=80)
	private String dre;	
	
	@Column(name="UGEL",nullable=true,length=80)
	private String ugel;
	
	@Column(name="TELFIE",nullable=true,length=10)
	private String telfie;//telefono institucional
	
	@Column(name="MAILIE",nullable=true,length=80)
	private String mailie;//correo institucional
	
	@Column(name="FACEBOOK",nullable=true,length=120)
	private String facebook;	
	
	@ManyToOne
	@JoinColumn(name="lenguaid",nullable=true,foreignKey=@ForeignKey(name="FK_programaeducativo_lengua"))
	private Lengua lengua;	
	
	@ManyToOne
	@JoinColumn(name="ambitoid",nullable=true,foreignKey=@ForeignKey(name="FK_programaeducativo_ambito"))
	private Ambito ambito;	
	
	@ManyToOne
	@JoinColumn(name="modensenianzaid",nullable=true,foreignKey=@ForeignKey(name="FK_programaeducativo_modense"))
	private Modensenianza modensenianza;	
	
	@ManyToOne
	@JoinColumn(name="generoid",nullable=true,foreignKey=@ForeignKey(name="FK_programaeducativo_genero"))
	private Genero genero;
	
	@ManyToOne
	@JoinColumn(name="proveedorid",nullable=true,foreignKey=@ForeignKey(name="FK_programaeducativo_proveedor"))
	private Proveedor proveedor;
	
	@Column(name="ABASTECIMIENTO",nullable=true,length=80)
	private Integer abastecimiento;	
	
	@ManyToOne
	@JoinColumn(name="tipodocidentdirid",nullable=true,foreignKey=@ForeignKey(name="FK_progedu_tipodocidentdir"))
	private Tipodocidentdir tipodocidentdir;
	
	@Column(name="DOCDIR",nullable=true,length=12)
	private String docdir;//nro de documento de director
	
	@Column(name="APEDIR",nullable=true,length=80)
	private String apedir;//apellidos de director
	
	@Column(name="NOMDIR",nullable=true,length=80)
	private String nomdir;//nombre de director
	
	@ManyToOne
	@JoinColumn(name="GENERODIRID",nullable=true,foreignKey=@ForeignKey(name="FK_progeduc_generodir"))
	private Generodir generodir;
	
	@Column(name="TELFDIR",nullable=true,length=15)
	private String telfdir;	//telefono director
	
	@Column(name="CELDIR",nullable=true,length=15)
	private String celdir;//celular director
	
	@Column(name="MAILDIR",nullable=true,length=70)
	private String maildir;	//correo electronico director
	
	@ManyToOne
	@JoinColumn(name="PISCINAID",nullable=true,foreignKey=@ForeignKey(name="FK_programaeducativo_piscina"))
	private Piscina piscina;
	
	@ManyToOne
	@JoinColumn(name="tipodocidentprofid",nullable=true,foreignKey=@ForeignKey(name="FK_progedu_tipodocidentprof"))
	private Tipodocidentprof tipodocidentprof;
	
	@Column(name="DOCPROF",nullable=true,length=12)
	private String docprof;//nro de documento de profesor
	
	@Column(name="APEPROF",nullable=true,length=80)
	private String apeprof;//apellidos de profesor
	
	@Column(name="NOMPROF",nullable=true,length=80)
	private String nomprof;//nombre de profesor
	
	@ManyToOne
	@JoinColumn(name="GENEROPROFID",nullable=true,foreignKey=@ForeignKey(name="FK_progeduc_generoprof"))
	private Generoprof generoprof;
	
	@Column(name="TELFPROF",nullable=true,length=15)
	private String telfprof;//telefono del profesor
	
	@Column(name="CELPROF",nullable=true,length=15)
	private String celprof;//celular del profesor
	
	@Column(name="MAILPROF",nullable=true,length=70)
	private String mailprof;//correo electronico del profesor
	
	@Column(name="DEP",nullable=true)
	private Integer dep;//departamento
	
	@Column(name="PROV",nullable=true)
	private Integer prov;//departamento
	
	@OneToMany(mappedBy="programaeducativo", cascade= {CascadeType.ALL}, orphanRemoval=true)
	private List<Suministro> suministro;
	
	/*@OneToMany(mappedBy="programaeducativo", cascade= {CascadeType.ALL}, orphanRemoval=true)
	private List<Categoria> categoria;*/
	
	@Column(name="CONCURSO",nullable=true)
	private Integer concurso;/*0 no participa, 1 si participa*/
	
	@Column(name="ESTADO",nullable=true,length=30)
	private String estado;
	
	@Column(name="MOTIVOOBSERVACION",nullable=true,length=150)
	private String motivoobservacion;/*0 no participa, 1 si participa*/
	
	@JsonIgnore
	@Column(name="ODS",nullable=true)
	private Integer ods;//
	
	@JsonIgnore
	@Column(name="FECHA_REGISTRO",nullable=true)
	private Timestamp fecha_registro;	//correo electronico director
	
	@JsonIgnore
	@Column(name="ANHIO",nullable=true)
	private Integer anhio;
	
	
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

	public Tipoie getTipoie() {
		return tipoie;
	}

	public void setTipoie(Tipoie tipoie) {
		this.tipoie = tipoie;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public String getDirie() {
		return dirie;
	}

	public void setDirie(String dirie) {
		this.dirie = dirie;
	}

	public String getDre() {
		return dre;
	}

	public void setDre(String dre) {
		this.dre = dre;
	}

	public String getUgel() {
		return ugel;
	}

	public void setUgel(String ugel) {
		this.ugel = ugel;
	}

	public String getTelfie() {
		return telfie;
	}

	public void setTelfie(String telfie) {
		this.telfie = telfie;
	}

	public String getMailie() {
		return mailie;
	}

	public void setMailie(String mailie) {
		this.mailie = mailie;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public Lengua getLengua() {
		return lengua;
	}

	public void setLengua(Lengua lengua) {
		this.lengua = lengua;
	}

	public Ambito getAmbito() {
		return ambito;
	}

	public void setAmbito(Ambito ambito) {
		this.ambito = ambito;
	}
	
	public Modensenianza getModensenianza() {
		return modensenianza;
	}

	public void setModensenianza(Modensenianza modensenianza) {
		this.modensenianza = modensenianza;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Generodir getGenerodir() {
		return generodir;
	}

	public void setGenerodir(Generodir generodir) {
		this.generodir = generodir;
	}

	public Generoprof getGeneroprof() {
		return generoprof;
	}

	public void setGeneroprof(Generoprof generoprof) {
		this.generoprof = generoprof;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Integer getAbastecimiento() {
		return abastecimiento;
	}

	public void setAbastecimiento(Integer abastecimiento) {
		this.abastecimiento = abastecimiento;
	}

	public Tipodocidentdir getTipodocidentdir() {
		return tipodocidentdir;
	}

	public void setTipodocidentdir(Tipodocidentdir tipodocidentdir) {
		this.tipodocidentdir = tipodocidentdir;
	}

	public String getDocdir() {
		return docdir;
	}

	public void setDocdir(String docdir) {
		this.docdir = docdir;
	}

	public String getApedir() {
		return apedir;
	}

	public void setApedir(String apedir) {
		this.apedir = apedir;
	}

	public String getNomdir() {
		return nomdir;
	}

	public void setNomdir(String nomdir) {
		this.nomdir = nomdir;
	}

	public String getTelfdir() {
		return telfdir;
	}

	public void setTelfdir(String telfdir) {
		this.telfdir = telfdir;
	}

	public String getCeldir() {
		return celdir;
	}

	public void setCeldir(String celdir) {
		this.celdir = celdir;
	}

	public String getMaildir() {
		return maildir;
	}

	public void setMaildir(String maildir) {
		this.maildir = maildir;
	}

	public Piscina getPiscina() {
		return piscina;
	}

	public void setPiscina(Piscina piscina) {
		this.piscina = piscina;
	}

	public Tipodocidentprof getTipodocidentprof() {
		return tipodocidentprof;
	}

	public void setTipodocidentprof(Tipodocidentprof tipodocidentprof) {
		this.tipodocidentprof = tipodocidentprof;
	}

	public String getDocprof() {
		return docprof;
	}

	public void setDocprof(String docprof) {
		this.docprof = docprof;
	}

	public String getApeprof() {
		return apeprof;
	}

	public void setApeprof(String apeprof) {
		this.apeprof = apeprof;
	}

	public String getNomprof() {
		return nomprof;
	}

	public void setNomprof(String nomprof) {
		this.nomprof = nomprof;
	}

	public String getTelfprof() {
		return telfprof;
	}

	public void setTelfprof(String telfprof) {
		this.telfprof = telfprof;
	}

	public String getCelprof() {
		return celprof;
	}

	public void setCelprof(String celprof) {
		this.celprof = celprof;
	}

	public String getMailprof() {
		return mailprof;
	}

	public void setMailprof(String mailprof) {
		this.mailprof = mailprof;
	}
	
	public List<Suministro> getSuministro() {
		return suministro;
	}

	public void setSuministro(List<Suministro> suministro) {
		this.suministro = suministro;
	}
	
	public Timestamp getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Timestamp fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	
	public Integer getDep() {
		return dep;
	}

	public void setDep(Integer dep) {
		this.dep = dep;
	}

	public Integer getProv() {
		return prov;
	}

	public void setProv(Integer prov) {
		this.prov = prov;
	}

	public Integer getOds() {
		return ods;
	}

	public void setOds(Integer ods) {
		this.ods = ods;
	}
	
	public String getMotivoobservacion() {
		return motivoobservacion;
	}

	public void setMotivoobservacion(String motivoobservacion) {
		this.motivoobservacion = motivoobservacion;
	}
	
	public Integer getAnhio() {
		return anhio;
	}

	public void setAnhio(Integer anhio) {
		this.anhio = anhio;
	}
	
	/*public List<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<Categoria> categoria) {
		this.categoria = categoria;
	}*/

	public Integer getConcurso() {
		return concurso;
	}

	public void setConcurso(Integer concurso) {
		this.concurso = concurso;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Programaeducativo other = (Programaeducativo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
