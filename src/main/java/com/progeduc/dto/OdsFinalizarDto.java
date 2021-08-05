package com.progeduc.dto;

import java.util.List;

import com.progeduc.model.CerrarOds;
import com.progeduc.model.Ods;

public class OdsFinalizarDto {
	
	List<Ods> listOds;
	List<CerrarOds> listCerrarOds;
	Integer status;
	
	public List<Ods> getListOds() {
		return listOds;
	}
	public void setListOds(List<Ods> listOds) {
		this.listOds = listOds;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public List<CerrarOds> getListCerrarOds() {
		return listCerrarOds;
	}
	public void setListCerrarOds(List<CerrarOds> listCerrarOds) {
		this.listCerrarOds = listCerrarOds;
	}
	
	

}
