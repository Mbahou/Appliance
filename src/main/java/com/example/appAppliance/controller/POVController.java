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

import com.example.appAppliance.Dto.POVdto;
import com.example.appAppliance.Model.POV;
import com.example.appAppliance.Repository.POVRepository;
import com.example.appAppliance.Service.POVService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping(path="POV")
public class POVController {
	@Autowired
	private POVService povservice;
	@Autowired
	private POVRepository povrepository;
	
	@PostMapping(path="/ajouter")
	public POVdto  ajouter( @RequestBody POVdto povdto) {
		
		return povservice.add(povdto);
	}
	@GetMapping(path="/afficherall")
	public List<POVdto> afficher(){
		return povservice.findALLpov();
	}
	@GetMapping(path="/afficherbyid/{id}")
	public Optional<POV> affiocherbyid( @PathVariable Long id) 	{
		return povservice.findByid(id);
		
	}
	@DeleteMapping(path="/delete/{id}")
	public void delete( @PathVariable Long id) {
		povservice.delete(id);
		
	}
	@PutMapping(path="/update/{id}")
	public void  update( @RequestBody POVdto povdto, @PathVariable Long id) {
		if(povrepository.existsById(id))
			povservice.updatepov(povdto);
		
	}
	@GetMapping(path="/page/{pageNumber}/{pageSize}")
	public Page<POV> getApp( @PathVariable Integer pageNumber , @PathVariable Integer pageSize){
		Page<POV> app= povservice.getapp(pageNumber,pageSize);
		return app;
	}
	 @GetMapping(path = "afficherpovPdf")
		public void exporttPovPdf(HttpServletResponse response) throws Exception{
			JasperPrint jasperPrint = povservice.jasperPrint();
			if(jasperPrint == null) {
				return;
			}
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename = pov.pdf");
			
			OutputStream outputStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		}
	
	
	

}
