package com.progeduc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.model.TrabajosfinalesParticipante;
import com.progeduc.repo.ITrabajosfinalesParticipanteRepo;
import com.progeduc.service.ITrabajosfinalesParticipanteService;

@Service
public class TrabajosfinalesParticipanteServiceImpl implements ITrabajosfinalesParticipanteService {

	@Autowired
	ITrabajosfinalesParticipanteRepo trabajosfinalesParticipanteRepo;
	
	@Override
	public Integer guardar(Integer trabajosfinalesid, Integer participanteid) {
		return trabajosfinalesParticipanteRepo.guardar(trabajosfinalesid, participanteid);
	}
	
	@Override
	public List<TrabajosfinalesParticipante> listar(Integer trabajofinalid){
		return trabajosfinalesParticipanteRepo.listar(trabajofinalid);
	}
	
	@Override
	public Integer eliminar(Integer trabajosfinalesid) {
		return trabajosfinalesParticipanteRepo.eliminar(trabajosfinalesid);
	}
	
	@Override
	public List<TrabajosfinalesParticipante> listarTodos(){
		return trabajosfinalesParticipanteRepo.listarTodos();
	}
	
	@Override
	public List<TrabajosfinalesParticipante> listarPorParticipante(Integer participanteid){
		return trabajosfinalesParticipanteRepo.listarPorParticipante(participanteid);
	}
		
}
