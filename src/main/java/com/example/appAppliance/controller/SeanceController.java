package com.example.appAppliance.controller;

import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.appAppliance.Dto.SeanceDto;
import com.example.appAppliance.Model.Seance;
import com.example.appAppliance.Repository.SeanceRepository;
import com.example.appAppliance.Service.SeanceService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping(path="Seance")
public class SeanceController {
	@Autowired
	private SeanceService seanceservice;
	@Autowired 
	private SeanceRepository seancereposetory;
	
	
	@PostMapping(path="/ajouter") 
	public SeanceDto ajouter(@RequestBody SeanceDto seancedto) {
		return seanceservice.add(seancedto);
		
	}
	@GetMapping(path="/afficherALL")
	public List<SeanceDto> aficherAll(){
		return seanceservice.findALLseance();
		
	}
	@GetMapping(path="/afficherbyid/{id}")
	public Optional<Seance> afficherbyid(@PathVariable Long id ){
		return seanceservice.findbyid(id);
		
	}
	
	@DeleteMapping(path="/delete/{id}")
	public void delete(@PathVariable Long  id) {
		seanceservice.delete(id);
	}
     
	 
	@PutMapping(path="/update/{id}")
	public void update(@RequestBody SeanceDto seancedto,@PathVariable Long id) {
		if(seancereposetory.existsById(id))
			seanceservice.add(seancedto);
	}
	@GetMapping(path="/page/{pageNumber}/{pageSize}")
	public Page<Seance> getApp( @PathVariable Integer pageNumber , @PathVariable Integer pageSize){
		Page<Seance> app= seanceservice.getapp(pageNumber,pageSize);
		return app;
	}
	 @GetMapping(path = "afficherseancePdf")
		public void exporttPovPdf(HttpServletResponse response) throws Exception{
			JasperPrint jasperPrint = seanceservice.jasperPrint();
			if(jasperPrint == null) {
				return;
			}
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename = seance.pdf");
			
			OutputStream outputStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		}
	
	

}
