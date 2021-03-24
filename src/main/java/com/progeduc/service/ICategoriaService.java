package com.progeduc.service;

import com.progeduc.dto.ListaCategoriaDto;
import com.progeduc.model.Categoria;

public interface ICategoriaService extends ICRUD<Categoria,Integer>{
	
	Integer guardar(ListaCategoriaDto lista);

}
