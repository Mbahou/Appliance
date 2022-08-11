package com.example.appAppliance.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.appAppliance.Dto.ApplianceDto;
import com.example.appAppliance.Model.Appliance;

public interface ApplianceRepository  extends JpaRepository<Appliance, Long>{
	Page<Appliance> findAll(Pageable pageable);
	@Query(value = "select  * from appliance ",nativeQuery= true)
	 List<ApplianceDto >getappliance(@Param ("id") Long id);
	@Query(value = "select  * from appliance ",nativeQuery= true)
	 Page<Appliance> getappliance( Pageable pageable);
}
	 
	 
