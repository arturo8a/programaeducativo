package com.progeduc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.progeduc.model.Distrito;
import com.progeduc.service.IDistritoService;

@RestController
@RequestMapping("")
public class DistritoController {	
	
	@Autowired
	IDistritoService distritoServ;
	
	@GetMapping("/distritos/byprovincia/{id}")
	public ResponseEntity<List<Distrito>> listByProvincia(@PathVariable("id") Integer id){
		List<Distrito> lista = distritoServ.listByProvincia(id);
		return new ResponseEntity<List<Distrito>>(lista,HttpStatus.OK);
	}
}
