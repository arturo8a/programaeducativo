package com.progeduc.service;

import com.progeduc.model.Auspicio;

public interface IAuspicioService extends ICRUD<Auspicio,Integer>{
	
	void eliminarAuspocioByUsuarioId(int usuarioId);
	
}
