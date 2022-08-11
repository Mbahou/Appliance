package com.example.appAppliance.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.appAppliance.Dto.ApplianceDto;
import com.example.appAppliance.Model.Appliance;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface ApplianceService {
	public ApplianceDto add(ApplianceDto appliancedto);
	public List<ApplianceDto> findAllAppliance();
	public Optional<Appliance> findById(Long id);
	public void delete(Long id);
	public Appliance updatById(ApplianceDto appliancedto);
	public JasperPrint jasperPrint() throws IOException, JRException;
	 public Page<Appliance> getApp(Integer pageNumber , Integer pageSize);

}
