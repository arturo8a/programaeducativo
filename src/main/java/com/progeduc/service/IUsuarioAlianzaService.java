package com.progeduc.service;

import com.progeduc.model.Usuario;
import com.progeduc.model.UsuarioAlianza;

public interface IUsuarioAlianzaService extends ICRUD<UsuarioAlianza,Integer>{
	
	Usuario saveUsuario(UsuarioAlianza dto);

}
