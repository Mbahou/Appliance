package com.example.appAppliance.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.appAppliance.Dto.ClientDto;
import com.example.appAppliance.Dto.ContactDto;
import com.example.appAppliance.Model.Client;
import com.example.appAppliance.Model.Contact;
import com.example.appAppliance.Repository.ClientRepository;

@Component
public class ContactMapper {
	@Autowired
	private DozerBeanMapper dozerbeanmapper;
	
	@Autowired
	private ClientRepository clientrepository;
	
	@Autowired
	ClientMapper clientmp;
	
	public ContactDto contactEntityTocontactDto(Contact contact) {
		ContactDto contactdto= dozerbeanmapper.map(contact, ContactDto.class);
		
		Client cli=contact.getClient();
		
		if(cli!=null)
		{
		
			contactdto.setClientdto(clientmp.ClientEntityToClientDto(cli));
		}
		else
		{
			System.out.println("contact entity "+cli);
		}
		System.out.println(contactdto);
		return contactdto;
		
	}

	public Contact contactDtoTocontactEntity(ContactDto contactDto) {
		
		Contact c=	dozerbeanmapper.map(contactDto, Contact.class);
		
		if(contactDto.getClientdto()!=null)
		{
			
			c.setClient(clientrepository.getOne(contactDto.getClientdto().getId()));
		}
		
//		System.out.println(c);
		return c;
		
		
	}
	public List<ContactDto> listEntityTolistDto(List<Contact> listentity){
		List<ContactDto>  contactentity=new ArrayList<ContactDto>();
		
		for(Contact contact:listentity) {
			contactentity.add(contactEntityTocontactDto(contact));
		}
		return contactentity;
		
	}
	

}
