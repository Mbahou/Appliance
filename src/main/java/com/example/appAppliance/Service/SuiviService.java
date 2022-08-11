package com.example.appAppliance.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.appAppliance.Dto.SuiviDto;
import com.example.appAppliance.Model.Suivi;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface SuiviService {
	public SuiviDto add(SuiviDto suividto);
	List<SuiviDto> findAllsuivi();
	Optional<Suivi> findByid(Long id);
	public void delete(Long id);
	public void update(SuiviDto suividto);
	public Page<Suivi> getapp(Integer pageNumber , Integer pageSize);
	public JasperPrint jasperPrint() throws IOException, JRException;

}
 