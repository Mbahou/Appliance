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

import com.example.appAppliance.Dto.TypeDto;
import com.example.appAppliance.Mapper.TypeMapper;
import com.example.appAppliance.Model.Type;
import com.example.appAppliance.Repository.TypeRepository;
import com.example.appAppliance.Service.TypeService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ServiceImplementation implements TypeService {
	public static final String RESOURCE1 = "static/type.jrxml";
	
	
    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private TypeMapper typeMapper;


    @Override
    public TypeDto addType(TypeDto typeDto) {
  
         return typeMapper.TypeEntityToTypeDto(typeRepository.save(typeMapper.TypeDtoToTypeEntity(typeDto)));
    }

    @Override
    public List<TypeDto> findALLType() {
        return typeMapper.listTypeEntiteTolistTypeDto(typeRepository.findAll());
    }

    @Override
    public Optional<Type> findTypeById(Long id){
        return typeRepository.findById(id);

    }

    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);

    }

    @Override
    public Type updatTYpe(TypeDto typedto) {
        return  typeRepository.save(typeMapper.TypeDtoToTypeEntity(typedto));
    }

	@Override
	public JasperPrint jasperPrint() throws IOException, JRException {
		
		InputStream resource = new ClassPathResource(RESOURCE1).getInputStream();
		JasperReport jasperReport = JasperCompileManager.compileReport(resource);
		List<TypeDto> typedto =typeMapper.listTypeEntiteTolistTypeDto(typeRepository.findAll());
		if (typedto == null) {
			return null;
		}

		List<TypeDto> data = new ArrayList<TypeDto>();
		for (TypeDto types : typedto){
			data.add(types);
		}
		
		Map<String, Object> parameters = new HashMap<String,Object>();

		parameters.put(JRParameter.REPORT_LOCALE, Locale.FRENCH);

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		return jasperPrint;
	}
}
