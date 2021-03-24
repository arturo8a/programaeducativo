package com.progeduc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.progeduc.model.Departamento;
import com.progeduc.service.IDepartamentoService;

@RestController
@RequestMapping("")
public class DepartamentoController {
	
	@Autowired
	private IDepartamentoService depaserv;
	
	@GetMapping("/departamentos")
	public ResponseEntity<List<Departamento>> listar(){
		List<Departamento> lista = depaserv.listar();
		return new ResponseEntity<List<Departamento>>(lista,HttpStatus.OK);
	}
	
	/*@GetMapping("/departamento/{id}")
	public Optional<Departamento> getDepartamentoById(@PathVariable Integer id) {
		//return depaserv.getDepartamentoById(id);
		
		/*Consulta obj = service.listarPorId(id);
		if(obj.getIdConsulta() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		return new ResponseEntity<Consulta>(obj, HttpStatus.OK);*/
	/*}*/
	
	/*@GetMapping("/departamento/{id}/provincia")
	public List<Provincia> getProvinciasByDepa(Integer id){
		Optional<Departamento> depa = depaserv.getDepartamentoById(id);
		if(depa.isPresent()) {
			Departamento newdepa = depa.get();
			return newdepa.getProvincias();
		}
		return null;
	}*/

}
