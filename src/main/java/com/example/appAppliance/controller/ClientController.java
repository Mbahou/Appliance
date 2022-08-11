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

import com.example.appAppliance.Dto.ClientDto;
import com.example.appAppliance.Model.Client;
import com.example.appAppliance.Repository.ClientRepository;
import com.example.appAppliance.Service.ClientService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping(path="Client")


public class ClientController {
	private static final String AVIS_FILENAME = "clientpdf";
	@Autowired
	private  ClientService clientservice;
	@Autowired
	private ClientRepository clientrepository;
	
	
	@PostMapping(path="/ajouter")
	 public  ClientDto add( @RequestBody ClientDto clientdto) {
		return clientservice.add(clientdto);
		
	}
	@GetMapping(path="/afficher")
	public List<ClientDto> afficher(){
		return clientservice.findAllClient();
	}
	@GetMapping(path="/afficherbyid/{id}")
	public Optional<Client> afficherbyid(@PathVariable Long id){
		return clientservice.findById(id);
		
	}
	@DeleteMapping(path="/delete/{id}")
	public void delete(@PathVariable Long id) {
		clientservice.delete(id);
	}
	@PutMapping(path="/update/{id}")
	public void update(@RequestBody ClientDto clientdto,@PathVariable Long id ) {
		if(clientrepository.existsById(id))
			clientservice.add(clientdto);
		
	}
	  @GetMapping(path = "afficherClientPdf")
		public void exporttPovPdf(HttpServletResponse response) throws Exception{
			JasperPrint jasperPrint = clientservice.jasperPrint();
			if(jasperPrint == null) {
				return;
			}
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename = client.pdf");
			
			OutputStream outputStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		}
		@GetMapping(path="/page/{pageNumber}/{pageSize}")
		public Page<Client> getApp( @PathVariable Integer pageNumber , @PathVariable Integer pageSize){
			Page<Client> app=clientservice.getapp(pageNumber,pageSize);
			return app;
		}
	
	

}
