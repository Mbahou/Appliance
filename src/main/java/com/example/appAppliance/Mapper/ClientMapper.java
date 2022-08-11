package com.example.appAppliance.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.appAppliance.Dto.ClientDto;
import com.example.appAppliance.Model.Client;


@Component
public class ClientMapper {
	@Autowired
	private DozerBeanMapper dozerbeanmapper;
	
	public ClientDto ClientEntityToClientDto(Client client) {
		return dozerbeanmapper.map(client, ClientDto.class);
		
	}
	public Client ClientDtoToClientEntity(ClientDto clientdto) {
		return dozerbeanmapper.map(clientdto, Client.class);
		
	}
	public List<ClientDto> listEntityToDto(List<Client > listEntity){
		List<ClientDto> clientList=new ArrayList<ClientDto>();
		for(Client client: listEntity) {
			clientList.add(ClientEntityToClientDto(client));
		}
		return clientList;
		
	}
	public List<Client> listDtoTolistEntity(List<ClientDto> listdto){
	List<Client> clientdto=new ArrayList<Client>();
       for(ClientDto clientdtos:listdto) {
		clientdto.add(ClientDtoToClientEntity(clientdtos));
	}	
       
       return clientdto;
		
}

}
