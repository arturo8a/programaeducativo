package com.progeduc.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {
	
	@PostMapping(value = "/redirect")
    public ResponseEntity<Void> redirect(){
 
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("http://prometeo.sunass.gob.pe/pedesa")).build();
    }
}
