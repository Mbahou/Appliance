package com.example.appAppliance.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.appAppliance.Dto.POVdto;
import com.example.appAppliance.Model.Appliance;
import com.example.appAppliance.Model.Client;
import com.example.appAppliance.Model.POV;
import com.example.appAppliance.Repository.ApplianceRepository;
import com.example.appAppliance.Repository.ClientRepository;
@Component

public class POVMapper {
	@Autowired
	private DozerBeanMapper dozerbeanmapper;
	@Autowired
	private ApplianceMapper appliancemapper;
	@Autowired
	private ApplianceRepository appliancerepository;
	@Autowired
	private ClientMapper clientmapper;
	@Autowired
	private ClientRepository clientrepository;
	
	public POVdto POVentityToPOVdto(POV pov) {
		 POVdto povDto= dozerbeanmapper.map(pov, POVdto.class);
		 Appliance appliance=pov.getAppliance();
		 Client client=pov.getClient();
		 if(appliance!=null && client!=null) {
			 povDto.setAppliancedto(appliancemapper.ApplianceEntitytoApplianceDto(appliance));
			 povDto.setClientdto(clientmapper.ClientEntityToClientDto(client));
			
		 }
		 else {
			 System.out.println(appliance);
			 System.out.println(client);
		 }
		 System.out.println(povDto);
		 
		 
		 return povDto;
		 
		
	}
	
	
	public POV POVdtoToPOVentity(POVdto povdto) {
		POV pov = dozerbeanmapper.map(povdto, POV.class);
		
		if(povdto.getAppliancedto()!=null && povdto.getClientdto()!=null) {
			pov.setAppliance(appliancerepository.getOne(povdto.getAppliancedto().getId()));
			pov.setClient(clientrepository.getOne(povdto.getClientdto().getId()));
		}
		return pov;
		
	}
	public List<POVdto> ListEntityToListDto(List<POV> listentity){
		List<POVdto> povlist= new ArrayList<POVdto>();
		for(POV pov:listentity) {
			povlist.add(POVentityToPOVdto(pov));
			
		}
		
		return povlist;
		
	}
	public List<POV> ListDtoToListEntity(List<POVdto> listdto){
		List<POV> povlist=new ArrayList<POV>();
		for(POVdto povdto:listdto) {
			povlist.add(POVdtoToPOVentity(povdto));
		}
		return povlist;
		
	}

}
