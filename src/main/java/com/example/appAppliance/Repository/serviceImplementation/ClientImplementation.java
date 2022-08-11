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

import com.example.appAppliance.Dto.ClientDto;
import com.example.appAppliance.Mapper.ClientMapper;
import com.example.appAppliance.Model.Client;
import com.example.appAppliance.Repository.ClientRepository;
import com.example.appAppliance.Service.ClientService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
@Service

public class ClientImplementation implements ClientService {
	
	 public static final String RESOURCE1 = "static/Client.jrxml";
	 
	 
	@Autowired
	private ClientRepository clientrepository;
	@Autowired
	private ClientMapper  clientmapper;
	


	@Override
	public ClientDto add(ClientDto clientdto) {
		
		return clientmapper.ClientEntityToClientDto(clientrepository.save(clientmapper.ClientDtoToClientEntity(clientdto)));
	}

	@Override
	public List<ClientDto> findAllClient() {
		
		return clientmapper.listEntityToDto(clientrepository.findAll());
	}

	@Override
	public Optional<Client> findById(Long id) {
	
		
		return clientrepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		 clientrepository.deleteById(id);
		
	}

	@Override
	public void updatebyid(ClientDto clientdto) {
		clientrepository.save(clientmapper.ClientDtoToClientEntity(clientdto));
		
	}

	@Override
	public JasperPrint jasperPrint() throws IOException, JRException {
		
		InputStream resource = new ClassPathResource(RESOURCE1).getInputStream();
		JasperReport jasperReport = JasperCompileManager.compileReport(resource);
		List<ClientDto> client = clientmapper.listEntityToDto(clientrepository.findAll());
		if (client == null) {
			return null;
		}

		List<ClientDto> data = new ArrayList<ClientDto>();
		for (ClientDto clients : client) {
			data.add(clients);
		}
		
		Map<String, Object> parameters = new HashMap<String,Object>();

		parameters.put(JRParameter.REPORT_LOCALE, Locale.FRENCH);

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		return jasperPrint;
		
		 
	}

	@Override
	public Page<Client> getapp(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber==null?0:pageNumber, pageSize==null?10:pageSize);
		Page<Client> app= clientrepository.getClient(pageable);
		
		return app;
		
	}



}
