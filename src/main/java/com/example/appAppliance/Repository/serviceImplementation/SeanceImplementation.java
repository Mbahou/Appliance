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

import com.example.appAppliance.Dto.SeanceDto;
import com.example.appAppliance.Mapper.SeanceMapper;
import com.example.appAppliance.Model.Seance;
import com.example.appAppliance.Repository.SeanceRepository;
import com.example.appAppliance.Service.SeanceService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
@Service
public class SeanceImplementation implements SeanceService {
	
	public static final String RESOURCE1 = "static/Seance.jrxml";
	@Autowired
	private SeanceRepository seancereposetory;
	@Autowired
	private SeanceMapper seancemapper;

	@Override
	public SeanceDto add(SeanceDto seancedto) {
		
		return seancemapper.SeanceEntityToSeanceDto(seancereposetory.save(seancemapper.SeanceDtoToSeanceEntity(seancedto)));
	}

	@Override
	public List<SeanceDto> findALLseance() { 
		
		return seancemapper.listEntityTolistDto(seancereposetory.findAll());
	}

	@Override
	public Optional<Seance> findbyid(Long id) {
		 
		return seancereposetory.findById(id);
	}

	@Override
	public void delete(Long id) {
		seancereposetory.deleteById(id);
		
	}

	@Override
	public void update(SeanceDto seancedto) {
		seancereposetory.save(seancemapper.SeanceDtoToSeanceEntity(seancedto));
		
		
		
	}

	@Override
	public Page<Seance> getapp(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber==null?0:pageNumber, pageSize==null?10:pageSize);
		Page<Seance> app=seancereposetory.getSeance(pageable);
		
		return app;
	
	}

	@Override
	public JasperPrint jasperPrint() throws IOException, JRException {
		InputStream resource = new ClassPathResource(RESOURCE1).getInputStream();
		JasperReport jasperReport = JasperCompileManager.compileReport(resource);
		List<SeanceDto> seancedto = seancemapper.listEntityTolistDto(seancereposetory.findAll());
		if (seancedto == null) {
			return null;
		}

		List<SeanceDto> data = new ArrayList<SeanceDto>();
		for (SeanceDto seancedt : seancedto) {
			data.add(seancedt);
		}
		
		Map<String, Object> parameters = new HashMap<String,Object>();

		parameters.put(JRParameter.REPORT_LOCALE, Locale.FRENCH);

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		return jasperPrint;
	}



}
