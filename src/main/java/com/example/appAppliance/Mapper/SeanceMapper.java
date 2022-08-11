package com.example.appAppliance.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.appAppliance.Dto.SeanceDto;
import com.example.appAppliance.Model.POV;
import com.example.appAppliance.Model.Seance;
import com.example.appAppliance.Repository.POVRepository;
import com.example.appAppliance.Repository.SeanceRepository;

@Component
public class SeanceMapper {
	@Autowired
	private DozerBeanMapper dozerbeanmapper;
	@Autowired
	POVRepository povRepository;
	@Autowired
	POVMapper povMapper;
	
	public SeanceDto SeanceEntityToSeanceDto(Seance seance) {
		 SeanceDto seancedto = dozerbeanmapper.map(seance, SeanceDto.class);
		 POV pov=seance.getPov();
		 if(pov!=null) {
			 seancedto.setPovdto(povMapper.POVentityToPOVdto(pov));
		 }else {
			 System.out.println(pov);
		 }
		 System.out.println(seancedto);
		 return seancedto;
		
	}
	public Seance SeanceDtoToSeanceEntity(SeanceDto seancedto) {
		Seance seance =dozerbeanmapper.map(seancedto, Seance.class);
		if(seancedto.getPovdto()!=null) {
			seance.setPov(povRepository.getOne(seancedto.getPovdto().getId()));
		}
		return seance;
		
	}
	public List<SeanceDto> listEntityTolistDto(List<Seance> listentity){
		List<SeanceDto> seanceentity=new ArrayList<SeanceDto>();
		for(Seance seance:listentity) {
			seanceentity.add(SeanceEntityToSeanceDto(seance));
			
		}
		return seanceentity;
		
	}
	public List<Seance> listDtoTolistEntity(List<SeanceDto> listdto){
		List<Seance> seancedto=new ArrayList<Seance>();
		for(SeanceDto seancedtos:listdto) {
			seancedto.add(SeanceDtoToSeanceEntity( seancedtos));
		}
		
		
		return  seancedto;
	}
	

}
