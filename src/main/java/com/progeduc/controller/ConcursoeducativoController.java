package com.progeduc.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.progeduc.dto.ListaparticipanteDto;
import com.progeduc.model.Aperturaranio;
import com.progeduc.model.Gradoparticipante;
import com.progeduc.model.Participante;
import com.progeduc.model.Postulacionconcurso;
import com.progeduc.model.Programaeducativo;
import com.progeduc.service.IAperturaranioService;
import com.progeduc.service.IGradoparticipanteService;
import com.progeduc.service.IParticipanteService;
import com.progeduc.service.IPostulacionconcursoService;
import com.progeduc.service.IProgramaeducativoService;
import com.progeduc.service.impl.UploadFileService;

@RestController
@RequestMapping("")
public class ConcursoeducativoController {
	
	@Autowired
	IProgramaeducativoService progeducService;
	
	@Autowired
	IAperturaranioService aperturaranioService;
	
	@Autowired
	IParticipanteService participanteService;
	
	@Autowired
	private IPostulacionconcursoService postulacionconcursoServ;
	
	@Autowired
	private IGradoparticipanteService gradoparticipanteServ;
	
	@Autowired
	private UploadFileService uploadfile;
	
	ListaparticipanteDto dto;
	
	@PostMapping(value="/registrarconcurso")
	public String registrarconcurso(@Valid @RequestBody Postulacionconcurso dto)  {
		
		Postulacionconcurso pc = postulacionconcursoServ.registrar(dto);
		String respuesta="0";
		if(pc !=null) 
			respuesta = aperturaranioService.buscar(pc.getAnio()).getNombreconcurso();
		return respuesta;
	}
	
	@GetMapping(value="/listargradopornivel/{id}")
	public ResponseEntity<List<Gradoparticipante>> listargradopornivel(@PathVariable("id") Integer id){
		return new ResponseEntity<List<Gradoparticipante>>(gradoparticipanteServ.listargradopornivel(id),HttpStatus.OK);
	}
	
	@GetMapping(value="/eliminarparticipanteid/{id}")
	public Integer eliminarparticipanteid(@PathVariable("id") Integer id) {
		return participanteService.updateestado(id, 0);
	}
	
	@PostMapping(value="/registrarparticipante")
	public Integer registrarparticipante(@Valid @RequestBody Participante participante,HttpSession ses) {
		
		String codmod = ses.getAttribute("usuario").toString();
		Programaeducativo pe = progeducService.getActualByCodmod(codmod);
		if(pe!=null) {
			participante.setProgramaeducativo(pe);
			Date date= new Date();
			long time = date.getTime();
			Timestamp ts = new Timestamp(time);
			participante.setFecha_registro(ts);
			participante.setAnhio(ts.toLocalDateTime().getYear());
			
			Participante p =  participanteService.registrar(participante);
			if(p!=null)
				return p.getId();
			return 0;
		}
		return -1;
		
	}
	
	@PostMapping(value="/subirarchivoparticipante")
	public Integer subirarchivoparticipante(@RequestParam("file") MultipartFile file,@RequestParam("id") Integer id) {
		
		if(file.isEmpty())
			return 0;
		try {
			uploadfile.saveFile(file,id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1; 
	}
	
	@PostMapping(value="/actualizararchivoparticipante")
	public Integer actualizararchivoparticipante(@RequestParam("file") MultipartFile file,@RequestParam("id") Integer id) {
		
		if(file.isEmpty())
			return 0;
		try {
			if(uploadfile.borrarArchivo(id)) {
				uploadfile.saveNuevoFile(file,id);
			}
			else {
				return 0;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1; 
	}
	
	@GetMapping("/participar_concurso")
	public String participar_concurso(HttpSession ses) throws ParseException {
		
		Calendar fecha = Calendar.getInstance();
		Date date = Calendar.getInstance().getTime();
		DateFormat formato = new SimpleDateFormat("dd/MM/yy");
        String today = formato.format(date);
        Aperturaranio ap = aperturaranioService.buscar(fecha.get(Calendar.YEAR));
        if(ap != null) {        	
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        	LocalDate fechaactual = LocalDate.parse(today, formatter);
        	//LocalDate fechaactual = LocalDate.parse("20/04/21", formatter);
            if(fechaactual.compareTo(ap.getSegundaetapadesde())>=0 && fechaactual.compareTo(ap.getCuartaetapahasta())<=0) {
            	String codmod = ses.getAttribute("usuario").toString();
            	Programaeducativo pe = progeducService.verificarEstadoAnio(codmod,fecha.get(Calendar.YEAR), "Aprobado");  
            	if(pe!=null) {
            		return pe.getId().toString();
            	}
            	return "c";
            }        	
        	return "b"; /*esta fuera de las fechas - fecha desde segunda y fecha hasta cuarta*/
        }
		return "a";/*no esta aperturado el año*/
	}	
	
	@GetMapping(value = "/listaparticipantes")
	public ResponseEntity<List<ListaparticipanteDto>> listaparticipante(HttpSession ses){
		
		String codmod = ses.getAttribute("usuario").toString();
		Programaeducativo pe = progeducService.getActualByCodmod(codmod);
		
		List<ListaparticipanteDto> lista = new ArrayList<ListaparticipanteDto>();
		List<Participante> listaParticipante = participanteService.listarhabilitados(pe.getId());
		if(listaParticipante!=null) {
			listaParticipante.forEach(obj->{			
				String categoria = "";
				String modalidad = "";
				dto =new ListaparticipanteDto();
				dto.setId(obj.getId());
				dto.setAppaterno(obj.getAppaternoestudiante());
				dto.setApmaterno(obj.getApmaternoestudiante());
				dto.setNombre(obj.getNombreestudiante());
				dto.setTipodocumento(obj.getTipodocumentoestudiante().getDescripcion());
				dto.setNrodocumento(obj.getNrodocumentoestudiante());
				categoria = obj.getCategoriacuento()==1?"Cuento/":"";
				categoria += obj.getCategoriapoesia()==1?"Poesía/":"";
				categoria += obj.getCategoriadibujopintura()==1?"Dibujo o Pintura/":"";
				categoria += obj.getCategoriacomposicionmusical()==1?"Composición musical/":"";
				categoria += obj.getCategoriaahorroagua()==1?"Ahorro del agua en tu hogar/" : "";
				if(categoria.length()>0)
					categoria = categoria.substring(0,categoria.length()-1);				
				dto.setCategoria(categoria);
				modalidad = obj.getModalidadpostulacionindividual()==1?"Individual/":"";
				modalidad += obj.getModalidadpostulaciongrupal()==1?"Grupal/":"";
				if(modalidad.length()>0)
					modalidad = modalidad.substring(0,modalidad.length()-1);
				dto.setModalidad(modalidad);
				lista.add(dto);
			});
		}		
		return new ResponseEntity<List<ListaparticipanteDto>>(lista, HttpStatus.OK) ;
	}

}
