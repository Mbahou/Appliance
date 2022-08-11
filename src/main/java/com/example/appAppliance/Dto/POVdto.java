package com.example.appAppliance.Dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class POVdto {
	private Long id;
	private Date date_debut;
	private Date date_fin;
	private String description;
	private String compte_manager;
	private String ingenieur_cybersecurite;
	private String analyse_cybersecurite;
	private String libelle_pov;
	
	private ApplianceDto appliancedto;
	
	private ClientDto clientdto;

}
