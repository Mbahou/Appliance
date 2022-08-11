package com.example.appAppliance.controller;

import com.example.appAppliance.Dto.TypeDto;
import com.example.appAppliance.Model.Type;
import com.example.appAppliance.Repository.TypeRepository;
import com.example.appAppliance.Service.TypeService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

@RequestMapping(path="/type")
@RestController
public class TypeController {
    @Autowired
    private TypeService typeservice;
    @Autowired
    private TypeRepository typerepository;
    


    @PostMapping(path="/TypeAjout")
    public TypeDto ajouter(@RequestBody TypeDto type){
    	if(type==null) {
    		return null;
    	}
      return typeservice.addType(type);
    }

    @GetMapping(path = "/TypeAfficher")
    public List<TypeDto> affichet(){
        return typeservice.findALLType();
    }


    @GetMapping(path = "/TypeAfficher/{id}")
    public Optional<Type>chercher(@PathVariable Long id){
        return typeservice.findTypeById(id);
    }


    @DeleteMapping(path ="/supprimer/{id}")
    public void supprimerbyId( @PathVariable Long id){
    	typeservice.deleteType(id);
    }

    @PutMapping(value = "/modifier/{id}")
    public void modifier( @RequestBody  TypeDto typedto, @PathVariable  Long id){
    	if(typerepository.existsById(id))
    	    typeservice.updatTYpe(typedto);
    
    }
	  @GetMapping(path = "affichertypePdf")
		public void exporttPovPdf(HttpServletResponse response) throws Exception{
			JasperPrint jasperPrint = typeservice.jasperPrint();
			if(jasperPrint == null) {
				return;
			}
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename = type.pdf");
			
			OutputStream outputStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		}
}
