package com.example.appAppliance.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.example.appAppliance.Dto.TypePrestationDto;
import com.example.appAppliance.Model.TypePrestation;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface TypePrestationService {
	public TypePrestationDto add(TypePrestationDto typeprestationdto);
	public List<TypePrestationDto> findAllType();
	public Optional<TypePrestation> findByid(Long id);
	public void delete(Long id);
	public void update(TypePrestationDto typeprestationdto);
	public JasperPrint jasperPrint() throws IOException, JRException;

}
