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

import com.example.appAppliance.Dto.ApplianceDto;
import com.example.appAppliance.Mapper.ApplianceMapper;
import com.example.appAppliance.Model.Appliance;
import com.example.appAppliance.Repository.ApplianceRepository;
import com.example.appAppliance.Service.ApplianceService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
@Service

public class ApplianceImpl  implements ApplianceService{
	 public static final String RESOURCE1 = "static/appliance.jrxml";
	
	
	@Autowired
	private ApplianceRepository appliancerepository;
	@Autowired
	private ApplianceMapper appliancemapper;
	

	@Override
	public ApplianceDto add(ApplianceDto appliancedto) {
		return appliancemapper.ApplianceEntitytoApplianceDto(appliancerepository.save(appliancemapper.ApplianceDtoToApplianceEntity(appliancedto)));
		
	}

	@Override
	public List<ApplianceDto> findAllAppliance() {
		return appliancemapper.listEntitytolistDto(appliancerepository.findAll());
		
	}

	@Override
	public Optional<Appliance> findById(Long id) {
	            
		return appliancerepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		appliancerepository.deleteById(id);
		
	}

	@Override
	public Appliance updatById(ApplianceDto appliancedto) {
	    
		return appliancerepository.save(appliancemapper.ApplianceDtoToApplianceEntity(appliancedto));
	}
	

	@Override
	public JasperPrint jasperPrint() throws IOException, JRException {
		InputStream resource = new ClassPathResource(RESOURCE1).getInputStream();
		JasperReport jasperReport = JasperCompileManager.compileReport(resource);
		List<ApplianceDto> appliancedto = appliancemapper.listEntitytolistDto(appliancerepository.findAll());
		if (appliancedto == null) {
			return null;
		}

		List<ApplianceDto> data = new ArrayList<ApplianceDto>();
		for (ApplianceDto appliance : appliancedto) {
			data.add(appliance);
		}
		
		Map<String, Object> parameters = new HashMap<String,Object>();

		parameters.put(JRParameter.REPORT_LOCALE, Locale.FRENCH);

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		return jasperPrint;

	}

	@Override
	public Page<Appliance> getApp(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber==null?0:pageNumber, pageSize==null?10:pageSize);
		Page<Appliance> app=appliancerepository.getappliance(pageable);
		
		return app;
	}

}
