package com.example.appAppliance.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.appAppliance.Model.Seance;

public interface SeanceRepository  extends JpaRepository<Seance, Long>{
	@Query(value="Select * from seance",nativeQuery=true)
	Page<Seance> getSeance(Pageable pageable);

}
