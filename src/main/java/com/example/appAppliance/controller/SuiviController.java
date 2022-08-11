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

import com.example.appAppliance.Dto.SuiviDto;
import com.example.appAppliance.Model.Suivi;
import com.example.appAppliance.Repository.SuiviRepository;
import com.example.appAppliance.Service.SuiviService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping(path="/suivi")
public class SuiviController {
	@Autowired
	private SuiviService suiviservice;
	@Autowired
	private SuiviRepository suivirepository;
	
	@PostMapping(path="/ajouter")
	public SuiviDto ajouter(@RequestBody SuiviDto suividto) {
		return suiviservice.add(suividto);
		
	}
	@GetMapping(path="/afficherAll")
	public List<SuiviDto> afficherALL(){
		return suiviservice.findAllsuivi();
		
	}
	@GetMapping(path="/afficherbyid/{id}")
	public Optional<Suivi> afficherbyid(@PathVariable Long id){
		return suiviservice.findByid(id);
		
	}
	@DeleteMapping(path="/delete/{id}")
	public void delete(@PathVariable Long id) {
		suiviservice.delete(id);
	}
	@PutMapping(path="/update/{id}")
	public void update(@RequestBody SuiviDto suividto,@PathVariable Long id) {
		if(suivirepository.existsById(id))
			suiviservice.add(suividto);
	}
	@GetMapping(path="/page/{pageNumber}/{pageSize}")
	public Page<Suivi> getApp(@PathVariable Integer pageNumber , @PathVariable  Integer pageSize){
		Page<Suivi> app= suiviservice.getapp(pageNumber,pageSize);
		return app;
	}
	 @GetMapping(path = "affichersuiviPdf")
		public void exporttPovPdf(HttpServletResponse response) throws Exception{
			JasperPrint jasperPrint = suiviservice.jasperPrint();
			if(jasperPrint == null) {
				return;
			}
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename = suivi.pdf");
			
			OutputStream outputStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		}
	
	

}
