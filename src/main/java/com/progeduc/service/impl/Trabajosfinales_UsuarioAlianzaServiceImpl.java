package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.TrabajosfinalesParticipante;
import com.progeduc.repo.ITrabajosfinalesUsuarioAlianzaRepo;
import com.progeduc.service.ITrabajosfinales_UsuarioAlianzaService;

@Service
public class Trabajosfinales_UsuarioAlianzaServiceImpl implements ITrabajosfinales_UsuarioAlianzaService{
	
	@Autowired
	ITrabajosfinalesUsuarioAlianzaRepo trabajosfinalesUsuarioAlianzaRepo;
	
	@Override
	public Integer guardar(Integer trabajosfinalesid, Integer usuarioalianzaid) {
		// TODO Auto-generated method stub
		return trabajosfinalesUsuarioAlianzaRepo.guardar(trabajosfinalesid, usuarioalianzaid);
	}

	@Override
	public Integer eliminar(Integer trabajosfinalesid) {
		// TODO Auto-generated method stub
		return trabajosfinalesUsuarioAlianzaRepo.eliminar(trabajosfinalesid);
	}

	@Override
	public List<TrabajosfinalesParticipante> listarByTrabajosfinalesId(Integer trabajofinalid) {
		// TODO Auto-generated method stub
		return trabajosfinalesUsuarioAlianzaRepo.listarByTrabajosfinalesId(trabajofinalid);
	}

}
