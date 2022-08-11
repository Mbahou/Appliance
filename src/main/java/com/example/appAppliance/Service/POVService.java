package com.example.appAppliance.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.appAppliance.Dto.POVdto;
import com.example.appAppliance.Model.POV;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

public interface POVService {
	public POVdto add(POVdto povdto);
	public List<POVdto> findALLpov();
	public Optional<POV> findByid(Long id);
	public void delete(Long id);
	public POV  updatepov(POVdto povdto);
	public Page<POV> getapp(Integer pageNumber , Integer pageSize);
	public JasperPrint jasperPrint() throws IOException, JRException;

}
