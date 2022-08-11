package com.example.appAppliance.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.appAppliance.Dto.TypeDto;
import com.example.appAppliance.Model.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type,Long>{
	 @Query(value = GET_Type , nativeQuery= true)
	  List<TypeDto >gettype(@Param ("id") Long id);
	 
	 public static final String GET_Type="select "
			 +"id as  id ,"
			 +"libelle as libelle "
			 +"from type ";
}
