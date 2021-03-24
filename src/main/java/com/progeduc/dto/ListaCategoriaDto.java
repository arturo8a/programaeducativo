package com.progeduc.dto;

import java.util.List;

import com.progeduc.model.Categoria;

public class ListaCategoriaDto {
	
	List<Categoria> listCategoria;

	public List<Categoria> getListCategoria() {
		return listCategoria;
	}

	public void setListCategoria(List<Categoria> listCategoria) {
		this.listCategoria = listCategoria;
	}
}
