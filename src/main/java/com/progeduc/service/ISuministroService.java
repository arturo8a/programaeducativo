package com.progeduc.service;

import java.util.List;

import com.progeduc.model.Suministro;

public interface ISuministroService extends ICRUD<Suministro,Integer>{
	
	List<Suministro> listarPorProgramaeducativo(Integer idpe);
}
