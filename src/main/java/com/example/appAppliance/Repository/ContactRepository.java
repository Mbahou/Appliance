package com.example.appAppliance.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.appAppliance.Dto.ApplianceDto;
import com.example.appAppliance.Dto.ContactDto;
import com.example.appAppliance.Model.Contact;

public interface ContactRepository  extends JpaRepository<Contact, Long>{
	@Query(value=GET_Contact,nativeQuery= true)
	Page<Contact> getcontact(Pageable pageable);
	@Query(value=GET_Contact,nativeQuery= true)
	List<ContactDto >getcontact(@Param ("id") Long id);
	
	 public static final String GET_Contact="select  * "
			 +" from contact ";

}
