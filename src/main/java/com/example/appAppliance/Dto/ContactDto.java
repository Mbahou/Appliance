package com.example.appAppliance.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {
	private Long id;
	private String nom;
	private String prenom;
	private String telephone;
	private String fonction;
	private String email;
	
	private ClientDto clientdto;

}
