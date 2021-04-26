package com.progeduc.service.impl;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {

    private String upload_folder = "/opt/apache-tomcat-8.0.27/webapps/alfresco_programaeducativo/pedesa/upload_participantes/";
	//private String upload_folder = ".//src//main//resources//files//";

    public void saveFile(MultipartFile file,Integer id) throws IOException {
        if(!file.isEmpty()){
            byte[] bytes = file.getBytes();            
            File directorio = new File(upload_folder+ "//"+id+"//");
            if(directorio.mkdirs()) {
            	Path path = Paths.get(upload_folder + "//"+id+"//" + file.getOriginalFilename());
            	Files.write(path,bytes);
            }
        }
    }
    
    public void saveNuevoFile(MultipartFile file,Integer id) throws IOException {
        if(!file.isEmpty()){
        	byte[] bytes = file.getBytes(); 	
        	Path path = Paths.get(upload_folder + "//"+id+"//" + file.getOriginalFilename());
            Files.write(path,bytes);
        }
    }
    
    public String buscarArchivo(int id) {
    	File carpeta = new File(upload_folder + "//" + id + "//");
    	String[] listado = carpeta.list();
    	return listado[0];
    }
    
    public boolean borrarArchivo(Integer id) {
    	String nombreArchivoAntiguo = buscarArchivo(id);
    	File archivoAntiguo = new File(upload_folder+"//"+id+"//"+nombreArchivoAntiguo);
    	return archivoAntiguo.delete();    	
    }
}
