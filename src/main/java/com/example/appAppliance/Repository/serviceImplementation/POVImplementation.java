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

import com.example.appAppliance.Dto.POVdto;
import com.example.appAppliance.Mapper.POVMapper;
import com.example.appAppliance.Model.POV;
import com.example.appAppliance.Repository.POVRepository;
import com.example.appAppliance.Service.POVService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
@Service

public class POVImplementation implements POVService {
	public static final String RESOURCE1 = "static/povs.jrxml";
	@Autowired
	private POVRepository povrepository;
	@Autowired
	private POVMapper povmapper;

	@Override
	public POVdto add(POVdto povdto) {

		return povmapper.POVentityToPOVdto(povrepository.save(povmapper.POVdtoToPOVentity(povdto)));
	}

	@Override
	public List<POVdto> findALLpov() {
	     
		return povmapper.ListEntityToListDto(povrepository.findAll());
	}

	@Override
	public Optional<POV> findByid(Long id) {
		
		return povrepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		povrepository.deleteById(id);
        
	}

	@Override
	public POV updatepov(POVdto povdto) {
		  
		return povrepository.save(povmapper.POVdtoToPOVentity(povdto));
	}

	@Override
	public Page<POV> getapp(Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber==null?0:pageNumber, pageSize==null?10:pageSize);
		Page<POV> app=povrepository.getPov(pageable);
		
		return app;
	
	}

	@Override
	public JasperPrint jasperPrint() throws IOException, JRException {
		
		InputStream resource = new ClassPathResource(RESOURCE1).getInputStream();
		JasperReport jasperReport = JasperCompileManager.compileReport(resource);
		List<POVdto> povdto = povmapper.ListEntityToListDto(povrepository.findAll());
		if (povdto == null) {
			return null;
		}

		List<POVdto> data = new ArrayList<POVdto>();
		for (POVdto povdt : povdto) {
			data.add(povdt);
		}
		
		Map<String, Object> parameters = new HashMap<String,Object>();

		parameters.put(JRParameter.REPORT_LOCALE, Locale.FRENCH);

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		return jasperPrint;
	}
}


