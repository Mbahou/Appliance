package com.example.appAppliance.Service;



import com.example.appAppliance.Dto.TypeDto;
import com.example.appAppliance.Model.Type;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

public interface TypeService {
    public TypeDto addType(TypeDto typeDto);
    public List<TypeDto> findALLType();
    public Optional<Type> findTypeById(Long id);
    public void deleteType(Long id);
    public Type updatTYpe(TypeDto  typedto);
    public JasperPrint jasperPrint() throws IOException, JRException;

}
