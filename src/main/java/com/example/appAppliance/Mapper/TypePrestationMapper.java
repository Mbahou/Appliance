package com.example.appAppliance.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.appAppliance.Dto.TypePrestationDto;
import com.example.appAppliance.Model.TypePrestation;

@Component

public class TypePrestationMapper {
	@Autowired
	private DozerBeanMapper dozerbeanmapper;
	
	public TypePrestationDto EntityToDto(TypePrestation typeprestation) {
		return dozerbeanmapper.map(typeprestation, TypePrestationDto.class);
		
	}
	public TypePrestation DtoToEntity(TypePrestationDto typeprestationdto) {
		return dozerbeanmapper.map(typeprestationdto, TypePrestation.class);
		
	}
	public List<TypePrestationDto> listentityTolistdto(List<TypePrestation> listEntity){
		List<TypePrestationDto> typedto=new ArrayList<TypePrestationDto>();
		for(TypePrestation typeprestation:listEntity) {
			typedto.add(EntityToDto( typeprestation));
		}
		return typedto;
		
	}
	public List<TypePrestation> listDtoTolistEntity(List<TypePrestationDto> listdto){
		List<TypePrestation> typeEntity=new ArrayList<TypePrestation>();
		for(TypePrestationDto typedto:listdto) {
			typeEntity.add(DtoToEntity(typedto));
		}
		return typeEntity ;
		
	}

}
