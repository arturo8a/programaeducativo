package com.progeduc.service.impl;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {

    //private String upload_folder = "/opt/apache-tomcat-8.0.27/webapps/alfresco_programaeducativo/pedesa/";
	private String upload_folder = "D:/Sunass/ProgramaEducativo_Desarrollo/programaeducativo/src/main/resources/";

    public void saveFile(MultipartFile file,Integer id,String ruta) throws IOException {
        if(!file.isEmpty()){
            byte[] bytes = file.getBytes();            
            File directorio = new File(upload_folder+ ruta+ "//"+id+"//");
            if(directorio.mkdirs()) {
            	System.out.println("name :" + file.getOriginalFilename());
            	Path path = Paths.get(upload_folder + ruta+ "//"+id+"//" + file.getOriginalFilename());
            	Files.write(path,bytes);
            }
            else {
            	System.out.println("name :" + file.getOriginalFilename());
            	Path path = Paths.get(upload_folder + ruta+ "//"+id+"//" + file.getOriginalFilename());
            	Files.write(path,bytes);
            }
        }
    }
    
    public void saveFile(List<MultipartFile> file,Integer id,String ruta) throws IOException {
    	
    	File directorio = new File(upload_folder+ ruta+ "//"+id+"//");
    	System.out.println("file length :" + file.size());
        if(directorio.mkdirs()) {
        	for(int i=0;i<file.size();i++) {
        		byte[] bytes = file.get(i).getBytes();
            	Path path = Paths.get(upload_folder+ ruta + "//"+id+"//" + file.get(i).getOriginalFilename());
            	Files.write(path,bytes);
        	}
        }    	
    }
    
    public void saveNuevoFile(MultipartFile file,String directorio,Integer id) throws IOException {
        if(!file.isEmpty()){
        	byte[] bytes = file.getBytes(); 	
        	Path path = Paths.get(upload_folder+ "//"+directorio+"//" + "//"+id+"//" + file.getOriginalFilename());
            Files.write(path,bytes);
        }
    }
    
    public String buscarArchivo(int id, String ruta) {
    	File carpeta = new File(upload_folder + "//" + ruta + "//" + id + "//");
    	String[] listado = carpeta.list();
    	return listado[0];
    }
    
    public List<String> buscarEvidencias(int id,String  ruta) {
    	List<String> mi_array = new ArrayList<String>();
    	File carpeta = new File(upload_folder + "//" + ruta + "//" + id + "//");
    	String[] listado = carpeta.list();
    	for(int i=0;i<listado.length;i++) {
    		mi_array.add(listado[i]);
        }
    	return mi_array;
    }
    
    
    public Integer nroArchivos(int id, String ruta) {
    	File carpeta = new File(upload_folder + "//" + ruta + "//" + id + "//");
    	String[] listado = carpeta.list();
    	return listado == null ? 0 : listado.length;
    }
    
    public boolean borrarArchivo(Integer id,String ruta) {
    	String nombreArchivoAntiguo = buscarArchivo(id,ruta);
    	File archivoAntiguo = new File(upload_folder+"//"+ ruta +"//"+id+"//"+nombreArchivoAntiguo);
    	return archivoAntiguo.delete();    	
    }
    
    public boolean eliminarArchivoCreado(Integer id,String nombrearchivo) {
    	File archivoAntiguo = new File(upload_folder+"//"+"upload_evidencias"+"//"+id+"//"+nombrearchivo);
    	return archivoAntiguo.delete();
    }
    
}
