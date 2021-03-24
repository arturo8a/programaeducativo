package com.progeduc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.progeduc.model.Provincia;
import com.progeduc.service.IProvinciaService;

@RestController
@RequestMapping("")
public class ProvinciaController {
	
	@Autowired
	IProvinciaService  provServ;
	
	@GetMapping("/provincias/bydepa/{id}")
	public ResponseEntity<List<Provincia>> listProvinciaByDepa(@PathVariable("id") Integer id){
		List<Provincia> lista = provServ.listProvinciaByDepa(id);
		return new ResponseEntity<List<Provincia>>(lista,HttpStatus.OK);
	}	
}
