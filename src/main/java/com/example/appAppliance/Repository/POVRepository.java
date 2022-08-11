package com.example.appAppliance.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.appAppliance.Dto.POVdto;
import com.example.appAppliance.Model.POV;

public interface POVRepository  extends JpaRepository<POV,Long>{
	@Query(value="Select * from pov",nativeQuery= true)
	Page<POV> getPov(Pageable pageable);
	@Query(value="Select * from pov",nativeQuery= true)
	List<POVdto >getpov(@Param ("id") Long id);
	



}
