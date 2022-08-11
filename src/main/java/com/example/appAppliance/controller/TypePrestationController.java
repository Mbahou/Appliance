package com.example.appAppliance.controller;

import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.appAppliance.Dto.TypePrestationDto;
import com.example.appAppliance.Model.TypePrestation;
import com.example.appAppliance.Repository.TypePrestationRepository;
import com.example.appAppliance.Service.TypePrestationService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping(path="/TypePrestation")

public class TypePrestationController {
	@Autowired
	private TypePrestationService typepreservice;
	@Autowired
	private  TypePrestationRepository typeprestationrepository;
	
	@PostMapping(path="/ajouter")
	public TypePrestationDto ajouter(@RequestBody TypePrestationDto typeprestationdto) {
		return typepreservice.add(typeprestationdto);
		
	}
	@GetMapping(path="/afficherAll")
	public List<TypePrestationDto> afficherAll(){
		 return typepreservice.findAllType();
	}
	@GetMapping(path="/afficherbyid/{id}")
	public Optional<TypePrestation> afficherbyid(@PathVariable Long id){
		return typepreservice.findByid(id);
	}
	@DeleteMapping(path="/delete/{id}")
	public void delete(@PathVariable Long id) {
		typepreservice.delete(id);
	}
	@PutMapping(path="/updat/{id}")
	public void update(@RequestBody TypePrestationDto typeprestationDto,@PathVariable Long id) {
		if(typeprestationrepository.existsById(id))
			typepreservice.add(typeprestationDto);
		     
		
	}
	  @GetMapping(path = "affichertypeprestationPdf")
			public void exporttPovPdf(HttpServletResponse response) throws Exception{
				JasperPrint jasperPrint = typepreservice.jasperPrint();
				if(jasperPrint == null) {
					return;
				}
				response.setContentType("application/pdf");
				response.addHeader("Content-Disposition", "attachment; filename = typeprestation.pdf");
				
				OutputStream outputStream = response.getOutputStream();
				JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
			}

}
