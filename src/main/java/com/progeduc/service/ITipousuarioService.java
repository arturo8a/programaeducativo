package com.progeduc.service;

import java.util.List;

import com.progeduc.model.Tipousuario;

public interface ITipousuarioService {
	
	Tipousuario byTipousuario(int usuario);
	
	List<Tipousuario> lista();
}
