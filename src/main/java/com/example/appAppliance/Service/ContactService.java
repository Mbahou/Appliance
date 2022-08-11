package com.example.appAppliance.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.appAppliance.Dto.ContactDto;
import com.example.appAppliance.Model.Contact;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface ContactService {
	public ContactDto add(ContactDto contactdto);
	public List<ContactDto> findALLcontact();
	public Optional<Contact> findByID(Long id) ;
	public void delete(Long id) ;
	public void updatbyid(ContactDto contactdto);
	public Page<Contact> getapp(Integer pageNumber , Integer pageSize);
	public JasperPrint jasperPrint() throws IOException, JRException;
	

}
