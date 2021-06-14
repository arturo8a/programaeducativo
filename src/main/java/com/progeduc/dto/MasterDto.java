package com.progeduc.dto;

import java.util.List;

public class MasterDto {
	
	List<OdsPersonaMasterDto> odspersona;
	List<PersonaMasterDto> persona;
	public List<OdsPersonaMasterDto> getOdspersona() {
		return odspersona;
	}
	public void setOdspersona(List<OdsPersonaMasterDto> odspersona) {
		this.odspersona = odspersona;
	}
	public List<PersonaMasterDto> getPersona() {
		return persona;
	}
	public void setPersona(List<PersonaMasterDto> persona) {
		this.persona = persona;
	}
	
	
	
}
