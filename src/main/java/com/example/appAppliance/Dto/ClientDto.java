package com.example.appAppliance.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ClientDto {
	private Long id;
	private String libelle;
	private String secteur;
	private String active;


}
