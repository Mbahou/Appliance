package com.example.appAppliance.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.appAppliance.Dto.SuiviDto;
import com.example.appAppliance.Model.POV;
import com.example.appAppliance.Model.Suivi;
import com.example.appAppliance.Model.TypePrestation;
import com.example.appAppliance.Repository.POVRepository;
import com.example.appAppliance.Repository.TypePrestationRepository;

@Component

public class SuiviMapper {
	@Autowired
	private DozerBeanMapper dozerbeanmapper;
	@Autowired
	private TypePrestationMapper typepresmapper;
	@Autowired
	private TypePrestationRepository reposi;
	@Autowired 
	private POVMapper povmapper;
	@Autowired
	private POVRepository povrepository;
	
	public SuiviDto SuiviEntityToSuiviDto(Suivi suivi) {
		    SuiviDto suividto=dozerbeanmapper.map(suivi, SuiviDto.class);
		    TypePrestation typeprestation=suivi.getTypeprestation();
		    POV pov=suivi.getPov();
		    
		    
		    if(typeprestation !=null && pov!=null) {
		    	suividto.setTypeprestationdto(typepresmapper.EntityToDto(typeprestation));
		    	suividto.setPov(povmapper.POVentityToPOVdto(pov));
		    }
		    else {
		   
		    	
		    	
		    }
		    System.out.println(suivi);
		    
		    return suividto;
		
	}
	public Suivi SuiviDtoToSuiviEntity(SuiviDto suividto) {
		Suivi suivi= dozerbeanmapper.map(suividto, Suivi.class);
		if(suividto.getTypeprestationdto()!=null && suividto.getPov()!=null) {
			suivi.setTypeprestation(reposi.getOne(suividto.getTypeprestationdto().getId()));
			suivi.setPov(povrepository.getOne(suividto.getPov().getId()));
		}
		
		System.out.println(suivi);
		return suivi;
		
	}
	public List<SuiviDto> listEntityTolistDto(List<Suivi> listentity){
		List<SuiviDto> suividto=new ArrayList<SuiviDto>();
		for(Suivi suivi:listentity) {
			suividto.add(SuiviEntityToSuiviDto(suivi));
		}
		return suividto;
		
	}
	public List<Suivi> listDtoTolistEntity(List<SuiviDto> listdto){
		List<Suivi> suiviEntity=new ArrayList<Suivi>();
		for(SuiviDto suividto:listdto) {
			suiviEntity.add(SuiviDtoToSuiviEntity(suividto));
		}
		return suiviEntity;
		
	}
	

}
