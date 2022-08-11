 package com.example.appAppliance.controller;

import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.appAppliance.Dto.ApplianceDto;
import com.example.appAppliance.Model.Appliance;
import com.example.appAppliance.Repository.ApplianceRepository;
import com.example.appAppliance.Service.ApplianceService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController

@RequestMapping(path ="/appliance")

public class ApplianceController {
	@Autowired
	private  ApplianceService applianceservice;
	@Autowired
	private ApplianceRepository appliancerepository;
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(path="/applianceajout")
	
	public  ApplianceDto ajouter(@RequestBody ApplianceDto appliancedto) {
		return applianceservice.add(appliancedto);
		
	}

//	@PreAuthorize("hasRole('USER')")
	@GetMapping(path = "/ApplianceAfficher")
//	@RolesAllowed("USER")
	 public List<ApplianceDto> afficher(){
		return applianceservice.findAllAppliance();
	}
//	@PreAuthorize("hasRole('USER')")
	@GetMapping(path = "/ApplianceAfficherbyid/{id}")
	public Optional<Appliance> afficherbyid( @PathVariable Long id){
		return applianceservice.findById(id);
		
	}
//	@PreAuthorize("hasRole('USER')")
	@DeleteMapping(path="/supprimerappliance/{id}")
	 public void supprimer(@PathVariable Long id) {
		applianceservice.delete(id);
		
	}
//	@PreAuthorize("hasRole('USER')")
	@PutMapping(path="/modifierAppliance/{id}")
	public void modifier( @RequestBody ApplianceDto appliancedto,@PathVariable Long id) {
		if(appliancerepository.existsById(id))
		   applianceservice.updatById(appliancedto);
	}
	
	@GetMapping(path="/page/{pageNumber}/{pageSize}")
	public Page<Appliance> getApp( @PathVariable Integer pageNumber , @PathVariable Integer pageSize){
		Page<Appliance> app=applianceservice.getApp(pageNumber,pageSize);
		return app;
	}
	  @GetMapping(path = "afficherAppliancePdf")
			public void exporttPovPdf(HttpServletResponse response) throws Exception{
				JasperPrint jasperPrint = applianceservice.jasperPrint();
				if(jasperPrint == null) {
					return;
				}
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "attachment; filename = appliance.pdf");
				
				OutputStream outputStream = response.getOutputStream();
				JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
			}
	
	

}
