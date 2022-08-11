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
import org.springframework.stereotype.Service;

import com.example.appAppliance.Dto.TypePrestationDto;
import com.example.appAppliance.Mapper.TypePrestationMapper;
import com.example.appAppliance.Model.TypePrestation;
import com.example.appAppliance.Repository.TypePrestationRepository;
import com.example.appAppliance.Service.TypePrestationService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
@Service

public class TypePrestationImplementation implements TypePrestationService {
	
	 public static final String RESOURCE1 = "static/Typeprestation.jrxml";
	
	
	
	@Autowired
	private TypePrestationMapper typeprestationmapper ;
	@Autowired
	private TypePrestationRepository typeprestationrepository;

	@Override
	public TypePrestationDto add(TypePrestationDto typeprestationdto) {
		return typeprestationmapper.EntityToDto(typeprestationrepository.save(typeprestationmapper.DtoToEntity(typeprestationdto))) ;
	}

	@Override
	public List<TypePrestationDto> findAllType() {
		 typeprestationmapper.listentityTolistdto(typeprestationrepository.findAll());
		return typeprestationmapper.listentityTolistdto(typeprestationrepository.findAll());
	}

	@Override
	public Optional<TypePrestation> findByid(Long id) {
		
		return typeprestationrepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		typeprestationrepository.deleteById(id);
		
	}

	@Override
	public void update(TypePrestationDto typeprestationdto) {
		typeprestationrepository.save(typeprestationmapper.DtoToEntity(typeprestationdto));
		
	}

	@Override
	public JasperPrint jasperPrint() throws IOException, JRException {
		
		InputStream resource = new ClassPathResource(RESOURCE1).getInputStream();
		JasperReport jasperReport = JasperCompileManager.compileReport(resource);
		List<TypePrestationDto> typeprestationdto = typeprestationmapper.listentityTolistdto(typeprestationrepository.findAll());
		if (typeprestationdto == null) {
			return null;
		}

		List<TypePrestationDto> data = new ArrayList<TypePrestationDto>();
		for (TypePrestationDto types : typeprestationdto) {
			data.add(types);
		}
		
		Map<String, Object> parameters = new HashMap<String,Object>();

		parameters.put(JRParameter.REPORT_LOCALE, Locale.FRENCH);

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		return jasperPrint;
	}

}
