package com.example.appAppliance.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.appAppliance.Dto.SeanceDto;
import com.example.appAppliance.Model.Seance;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface SeanceService {
	public SeanceDto add(SeanceDto seancedto);
	public List<SeanceDto> findALLseance();
	public Optional<Seance> findbyid(Long id);
	public void delete(Long id);
	public void update(SeanceDto seancedto);
	public Page<Seance> getapp(Integer pageNumber , Integer pageSize);
	public JasperPrint jasperPrint() throws IOException, JRException;
 
}
