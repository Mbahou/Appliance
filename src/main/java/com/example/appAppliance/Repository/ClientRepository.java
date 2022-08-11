package com.example.appAppliance.Repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.appAppliance.Dto.ClientDto;
import com.example.appAppliance.Model.Client;
public interface ClientRepository  extends JpaRepository<Client, Long>{
 @Query(value = GET_Client , nativeQuery= true)
  List<ClientDto >getclient(@Param ("id") Long id);
 @Query(value = GET_Client , nativeQuery= true)
  Page<Client> getClient(Pageable pageable);
 
 public static final String GET_Client="select * "
	
		 + "from  client " ;

}
