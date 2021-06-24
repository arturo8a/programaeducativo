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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="TRABAJOEVALUADORTMP")
public class TrabajoEvaluadorTmp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="CODIGOID")
	private Integer codigoid;	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="evaluadortmpid",nullable=false,foreignKey=@ForeignKey(name="fk_trabajoevatmp_evatmp"))
	private Evaluadortmp evaluadortmp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCodigoid() {
		return codigoid;
	}

	public void setCodigoid(Integer codigoid) {
		this.codigoid = codigoid;
	}

	public Evaluadortmp getEvaluadortmp() {
		return evaluadortmp;
	}

	public void setEvaluadortmp(Evaluadortmp evaluadortmp) {
		this.evaluadortmp = evaluadortmp;
	}

}
