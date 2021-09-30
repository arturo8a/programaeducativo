package com.progeduc.service;

import java.util.List;

import com.progeduc.model.Usuario;
import com.progeduc.model.UsuarioAlianza;

public interface IUsuarioAlianzaService extends ICRUD<UsuarioAlianza,Integer>{
	
	Usuario saveUsuario(UsuarioAlianza dto);
	List<UsuarioAlianza> listarByOds(Integer odsid);
	UsuarioAlianza getUsuarioEvaluador(String usuario, String password);
	List<UsuarioAlianza>  listaUsuarioFiltro(String ods, String anio, String estado, String role6, String role7, String role8, String role9);
	List<UsuarioAlianza> buscarEvaluador(String usuarioEvaluador);
	List<UsuarioAlianza> buscarEvaluador(Integer idusu,String usuarioEvaluador);
	List<UsuarioAlianza> listarPorAnio(Integer anio);
	List<UsuarioAlianza> listarByOdsAnio(Integer odsid, Integer anio);

}
