package com.example.appAppliance.Mapper;

import com.example.appAppliance.Dto.TypeDto;
import com.example.appAppliance.Model.Type;


import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TypeMapper {
    @Autowired
    private Mapper mapper=new DozerBeanMapper();


    public TypeDto TypeEntityToTypeDto(Type produit){
        return mapper.map(produit,TypeDto.class);
    }
    public Type TypeDtoToTypeEntity(TypeDto typedto){
        return  mapper.map(typedto,Type.class);
    }
    public List<TypeDto> listTypeEntiteTolistTypeDto(List<Type> typelist){
        List<TypeDto>  typedtoList =new ArrayList<TypeDto>();
        for(Type produit:typelist)
        {
         typedtoList.add(TypeEntityToTypeDto(produit));
        }
        return typedtoList;
    }
    public  List<Type> listTypetDtoTolistTypeEntity(List<TypeDto> typedto) {
        List<Type>  typeList =new ArrayList<Type>();
        for(TypeDto produitdtos:typedto)
        {
            typeList.add(TypeDtoToTypeEntity(produitdtos));
        }
        return typeList;
    }

}
