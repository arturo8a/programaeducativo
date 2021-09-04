package com.progeduc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.progeduc.dto.ColegioDto;
import com.progeduc.dto.ListaInstitucionEducativa;
import com.progeduc.dto.ProgeducTurnoNivelDto;
import com.progeduc.dto.ProgeducUpdateTurnoNivelDto;
import com.progeduc.model.Programaeducativo;
import com.progeduc.model.ProgramaeducativoNivel;
import com.progeduc.model.ProgramaeducativoTurno;
import com.progeduc.repo.INivelRepo;
import com.progeduc.repo.IProgeducNivelRepo;
import com.progeduc.repo.IProgeducTurnoRepo;
import com.progeduc.repo.IProgramaeducativoRepo;
import com.progeduc.service.IProgramaeducativoService;

@Service
public class ProgramaeducativoServiceImpl implements IProgramaeducativoService{
	
	@Autowired
	IProgramaeducativoRepo progeducRepo;
	
	@Autowired
	INivelRepo nivelRepo;
	
	@Autowired
	IProgeducNivelRepo progeducNivelRepo;
	
	@Autowired
	IProgeducTurnoRepo progeducTurnoRepo;
	
	Integer progeducid;	
	
	ProgramaeducativoNivel pen;	
	
	ProgramaeducativoTurno pet;

	@Override
	public Programaeducativo registrar(Programaeducativo obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Programaeducativo modificar(Programaeducativo obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Programaeducativo> listar() {
		// TODO Auto-generated method stub
		return  (List<Programaeducativo>) progeducRepo.listar();
	}
	
	@Override
	public List<Programaeducativo> getListarHabilitadosAnioActual(){
		return progeducRepo.getListarHabilitadosAnioActual();
	}
	
	@Override
	public List<Programaeducativo> listar(Integer iddistrito){
		return progeducRepo.listar(iddistrito);
	}
	
	@Override
	public List<ListaInstitucionEducativa> listar1(){
		return progeducRepo.listar1();
	}
	
	@Override
	public List<Programaeducativo> listarTodos(){
		return progeducRepo.listarTodos();
	}

	@Override
	public Programaeducativo ListarporId(Integer id) {
		Optional<Programaeducativo> pe = progeducRepo.findById(id);
		return pe.isPresent() ? pe.get() : new Programaeducativo();
		//return progeducRepo.findById(id);
	}	
	
	@Override
	public String buscarMotivoPrograma(Integer id) {
		Optional<Programaeducativo> pe = progeducRepo.findById(id);
		return pe.isPresent() ? pe.get().getMotivoobservacion() : "";
		//return progeducRepo.findById(id);
	}	

	@Override
	public boolean Eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Programaeducativo> listarfiltro(String fecha_desde, String fecha_hasta, String nombreie, Integer depa,Integer prov,Integer dist){
	//public List<Programaeducativo> listarfiltro(String nombreie) {
		// TODO Auto-generated method stub
		return progeducRepo.listarfiltro(fecha_desde, fecha_hasta, nombreie, depa,prov,dist);
	}

	@Override
	public Programaeducativo save(Programaeducativo pe) {
		pe.getSuministro().forEach(su->{
			su.setProgramaeducativo(pe);
		});
		return progeducRepo.save(pe);
	}
	
	@Override
	public Programaeducativo saveProgeducTurnoNivel(ProgeducTurnoNivelDto dto) {
		
		dto.getProgeduc().getSuministro().forEach(su->{
			su.setProgramaeducativo(dto.getProgeduc());
		});
		progeducRepo.save(dto.getProgeduc());
		
		dto.getListNivel().forEach(niv->{
			nivelRepo.save(niv);
			progeducNivelRepo.guardar(niv.getId(), dto.getProgeduc().getId());
		});		
		dto.getlistTurno().forEach(tur-> 
			progeducTurnoRepo.guardar(tur.getId(), dto.getProgeduc().getId())
		);
		return dto.getProgeduc();
	}
	
	@Override
	public Programaeducativo updateProgeducTurnoNivel(ProgeducUpdateTurnoNivelDto dto) {
		
		dto.getProgeduc().getSuministro().forEach(su->{
			su.setProgramaeducativo(dto.getProgeduc());
		});
		progeducRepo.save(dto.getProgeduc());
		
		progeducNivelRepo.deleteByProgramaeducativoId(dto.getProgeduc().getId());
		dto.getListNivel().forEach(niv->{
			nivelRepo.save(niv);			
			progeducNivelRepo.guardar(niv.getId(), dto.getProgeduc().getId());		
		});		
		progeducTurnoRepo.deleteByProgramaeducativoId(dto.getProgeduc().getId());
		dto.getlistTurno().forEach(tur-> {
			progeducTurnoRepo.guardar(tur.getId(), dto.getProgeduc().getId());		
		});
		return dto.getProgeduc();
	}
	
	@Override
	public Programaeducativo getCodmod(String codmod) {
		return progeducRepo.getCodmod(codmod);
	}

	@Override
	public List<String> listCentrosEducativosGroupbyCodmod() {
		// TODO Auto-generated method stub
		return progeducRepo.listCentrosEducativosGroupbyCodmod();
	}
	
	@Override
	public List<ColegioDto> listCentrosEducativosGroupbyNomie(){
		return progeducRepo.listCentrosEducativosGroupbyNomie();
	}

	@Override
	public Programaeducativo getCodmodByAnioActual(String codmod) {
		// TODO Auto-generated method stub
		return progeducRepo.getCodmodByAnioActual(codmod);
	}

	@Override
	public List<Programaeducativo> listCentrosEducativosInscritos(){
		return progeducRepo.listCentrosEducativosInscritos();
	}
	
	@Override
	public int updateestado(Integer id, String estado, String motivoobservacion) {
		return progeducRepo.updateestado(id, estado, motivoobservacion);
	}
	
	@Override
	public int updateestadoaprobar(Integer id, String estado) {
		return progeducRepo.updateestadoaprobar(id, estado);
	}
	
	@Override
	public Programaeducativo verificarEstadoAnio(String codmod,Integer anio, String estado) {
		return progeducRepo.verificarEstadoAnio(codmod,anio, estado);
	}
	
	/*public Programaeducativo searchId(Integer id) {
		Optional<Programaeducativo> op = progEduc.
		return op.isPresent() ? op.get() : new Programaeducativo();
	}*/
	
	@Override
	public Programaeducativo getActualByCodmod(String codmod) {
		return progeducRepo.getActualByCodmod(codmod);
	}
	
	@Override
	public List<Programaeducativo> listarAprobados(){
		return progeducRepo.listarAprobados();
	}
}
