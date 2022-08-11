package com.example.appAppliance.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.appAppliance.Model.Suivi;

public interface SuiviRepository extends JpaRepository<Suivi, Long> {
	@Query(value="Select * from suivi",nativeQuery = true)
	Page<Suivi> getSuivi(Pageable pageable);

}
