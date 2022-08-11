package com.example.appAppliance.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.appAppliance.Dto.ClientDto;
import com.example.appAppliance.Model.Client;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;



public interface ClientService {
	public ClientDto add(ClientDto clientdto);
	public List<ClientDto> findAllClient();
	public Optional<Client> findById(Long id);
	public void delete(Long id);
	public void updatebyid(ClientDto clientdto);
    public JasperPrint jasperPrint() throws IOException, JRException;
    public Page<Client> getapp(Integer pageNumber , Integer pageSize);
   
    

}
