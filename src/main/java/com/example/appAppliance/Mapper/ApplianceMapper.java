package com.example.appAppliance.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.appAppliance.Dto.ApplianceDto;
import com.example.appAppliance.Model.Appliance;
import com.example.appAppliance.Model.Type;
import com.example.appAppliance.Repository.TypeRepository;

@Component
public class ApplianceMapper {
	@Autowired
	private DozerBeanMapper dozerbeanmapper;
	@Autowired
	TypeMapper typemapper;
	
	@Autowired
	TypeRepository typerepository;
	
	
	public ApplianceDto ApplianceEntitytoApplianceDto(Appliance appliance){
			
		 ApplianceDto appliancedto= dozerbeanmapper.map(appliance,ApplianceDto.class);
		 
		 Type type=appliance.getType();
		   
         if(type!=null){
			 appliancedto.setTypedto(typemapper.TypeEntityToTypeDto(type));
		 }
		 else {
			  System.out.println(type);
		 }
		 System.out.println(appliancedto);
		 return appliancedto;
	}
	public Appliance ApplianceDtoToApplianceEntity(ApplianceDto appliancedto) {
		
		 Appliance appliance = dozerbeanmapper.map(appliancedto,Appliance.class);
		 if(appliancedto.getTypedto()!=null) {
				appliance.setType(typerepository.getOne(appliancedto.getTypedto().getId()));
		 }
		 
		 return appliance;
		 
		 }
	
		public List<ApplianceDto> listEntitytolistDto(List<Appliance> listEntity){
			
			List<ApplianceDto> applianceList=new ArrayList<ApplianceDto>();
			for(Appliance appliance:listEntity)
			{
				applianceList.add(ApplianceEntitytoApplianceDto(appliance));
			}
				
			return  applianceList;
			
		}
		public List<Appliance>  listDtoTolistEntity(List<ApplianceDto> listDto){
			List<Appliance> appliancedtolist=new ArrayList<Appliance>();
			for(ApplianceDto appliancedto:listDto) {
				appliancedtolist.add( ApplianceDtoToApplianceEntity(appliancedto));
			}
			return appliancedtolist;
		}
	

}
