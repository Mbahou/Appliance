package com.example.appAppliance.Repository.serviceImplementation;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.appAppliance.Dto.ContactDto;
import com.example.appAppliance.Mapper.ContactMapper;
import com.example.appAppliance.Model.Contact;
import com.example.appAppliance.Repository.ContactRepository;
import com.example.appAppliance.Service.ContactService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
@Service
public class ContactImplementation implements ContactService {
	
	public static final String RESOURCE1 = "static/Contact.jrxml";
	@Autowired
	private ContactRepository contactrepository;
	@Autowired
	private ContactMapper contactmapper;

	@Override
	public ContactDto add(ContactDto contactdto) {
		
		return contactmapper.contactEntityTocontactDto(contactrepository.save(contactmapper.contactDtoTocontactEntity(contactdto)));
	}

	@Override
	public List<ContactDto> findALLcontact() {
		
		return contactmapper.listEntityTolistDto(contactrepository.findAll());
	}

	@Override
	public Optional<Contact> findByID(Long id) {
		
		return contactrepository.findById(id);
	}

	@Override
	public void delete(Long id) {
	    contactrepository.deleteById(id);
		
	}

	@Override
	public void updatbyid(ContactDto contactdto) {
		contactrepository.save(contactmapper.contactDtoTocontactEntity(contactdto));
		
	}

	@Override	public Page<Contact> getapp(Integer pageNumber, Integer pageSize) {
	
		Pageable pageable = PageRequest.of(pageNumber==null?0:pageNumber, pageSize==null?10:pageSize);
		Page<Contact> app=contactrepository.getcontact(pageable);
		
		return app;

	}

	@Override
	public JasperPrint jasperPrint() throws IOException, JRException {
		InputStream resource = new ClassPathResource(RESOURCE1).getInputStream();
		JasperReport jasperReport = JasperCompileManager.compileReport(resource);
		List<ContactDto> contactdto = contactmapper.listEntityTolistDto(contactrepository.findAll());
		if (contactdto == null) {
			return null;
		}

		List<ContactDto> data = new ArrayList<ContactDto>();
		for (ContactDto contactdt : contactdto) {
			data.add(contactdt);
		}
		
		Map<String, Object> parameters = new HashMap<String,Object>();

		parameters.put(JRParameter.REPORT_LOCALE, Locale.FRENCH);

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		return jasperPrint;
	}

}
