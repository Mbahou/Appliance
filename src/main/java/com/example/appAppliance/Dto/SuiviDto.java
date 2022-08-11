package com.example.appAppliance.Dto;

import com.example.appAppliance.Model.TypePrestation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SuiviDto {
	private Long id ;
	private boolean offre_Commercial;
	private Float montont;   
	private String compterendu;
    private TypePrestation typeprestation;

    private TypePrestationDto typeprestationdto;
    
    private POVdto pov;
	

}
