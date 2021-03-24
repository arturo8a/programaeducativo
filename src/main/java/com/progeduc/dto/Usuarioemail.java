package com.progeduc.dto;

import com.progeduc.model.EmailBody;
import com.progeduc.model.Usuario;

public class Usuarioemail {
	
	Usuario usuario;	
	EmailBody emailbody;
	
	public Usuarioemail() {}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public EmailBody getEmailbody() {
		return emailbody;
	}

	public void setEmailbody(EmailBody emailbody) {
		this.emailbody = emailbody;
	}
	
	

}
