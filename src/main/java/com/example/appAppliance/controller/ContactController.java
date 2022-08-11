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

import com.example.appAppliance.Dto.ContactDto;
import com.example.appAppliance.Model.Contact;
import com.example.appAppliance.Repository.ContactRepository;
import com.example.appAppliance.Service.ContactService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping(path="/Contact")

public class ContactController {
	@Autowired
	 private ContactService contactservice;
	@Autowired
	private ContactRepository contactRepository;
	
	
	
	@PostMapping(path="/ajouter")
	 public ContactDto add(@RequestBody ContactDto contactdto) {
		return  contactservice.add(contactdto);
		
		
	}
	@GetMapping(path="/affichet")
	public List<ContactDto> afficher(){
		return contactservice.findALLcontact();
		
	}
	@GetMapping(path="/affichetbyid/{id}")
	public Optional<Contact> afficherbyid(@PathVariable Long id){
		return contactservice.findByID(id);
		
	}
	@DeleteMapping(path="/delete/{id}")
	public void delete(@PathVariable Long id) {
		contactservice.delete(id);
	}
	@PutMapping(path="/update/{id}")
	public void update(@RequestBody ContactDto contactdto,@PathVariable Long id) {
		if(contactRepository.existsById(id))
			contactservice.add(contactdto);
		
	}
	@GetMapping(path="/page/{pageNumber}/{pageSize}")
	public Page<Contact> getApp( @PathVariable Integer pageNumber , @PathVariable Integer pageSize){
		Page<Contact> app= contactservice.getapp(pageNumber,pageSize);
		return app;
	}
	 @GetMapping(path = "affichercontactPdf")
		public void exporttPovPdf(HttpServletResponse response) throws Exception{
			JasperPrint jasperPrint = contactservice.jasperPrint();
			if(jasperPrint == null) {
				return;
			}
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename = appliance.pdf");
			
			OutputStream outputStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		}
	

}
