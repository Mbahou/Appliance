package com.example.appAppliance.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.appAppliance.Dto.TypePrestationDto;
import com.example.appAppliance.Model.TypePrestation;

public interface TypePrestationRepository extends JpaRepository<TypePrestation, Long>{
	 @Query(value = GET_Typepre , nativeQuery= true)
	  List<TypePrestationDto >gettypeprestation(@Param ("id") Long id);
	 
	 public static final String GET_Typepre="select "
			 +"id as  id ,"
			 +"libelle as libelle "
			 +"from type_prestation ";
}


