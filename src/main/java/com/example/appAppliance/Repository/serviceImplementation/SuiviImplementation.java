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

import com.example.appAppliance.Dto.SuiviDto;
import com.example.appAppliance.Mapper.SuiviMapper;
import com.example.appAppliance.Model.Suivi;
import com.example.appAppliance.Repository.SuiviRepository;
import com.example.appAppliance.Service.SuiviService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
@Service

public class SuiviImplementation implements SuiviService{
	public static final String RESOURCE1 = "static/Suivi.jrxml";
	@Autowired
	private SuiviRepository suivirepository;
	@Autowired
	private SuiviMapper suivimapper;

	@Override
	public SuiviDto add(SuiviDto suividto) {
		
		return suivimapper.SuiviEntityToSuiviDto(suivirepository.save(suivimapper.SuiviDtoToSuiviEntity(suividto)));
	}

	@Override
	public List<SuiviDto> findAllsuivi() {
		
		return suivimapper.listEntityTolistDto(suivirepository.findAll());
	}

	@Override
	public Optional<Suivi> findByid(Long id) {
		
		return suivirepository.findById(id);
	}

	@Override
	public void delete(Long id) {
	   suivirepository.deleteById(id);
		
	}

	@Override
	public void update(SuiviDto suividto) {
		suivirepository.save(suivimapper.SuiviDtoToSuiviEntity(suividto));
		
	}

	@Override
	public Page<Suivi> getapp(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber==null?0:pageNumber, pageSize==null?10:pageSize);
		Page<Suivi> app= suivirepository.getSuivi(pageable);
		
		return app;
	}

	@Override
	public JasperPrint jasperPrint() throws IOException, JRException {
		InputStream resource = new ClassPathResource(RESOURCE1).getInputStream();
		JasperReport jasperReport = JasperCompileManager.compileReport(resource);
		List<SuiviDto> suividto =suivimapper.listEntityTolistDto(suivirepository.findAll());
		if (suividto == null) {
			return null;
		}

		List<SuiviDto> data = new ArrayList<SuiviDto>();
		for (SuiviDto suividt : suividto) {
			data.add(suividt);
		
		}
		
		Map<String, Object> parameters = new HashMap<String,Object>();

		parameters.put(JRParameter.REPORT_LOCALE, Locale.FRENCH);

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		return jasperPrint;
	}
	}
	


