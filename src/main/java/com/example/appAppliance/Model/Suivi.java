package com.example.appAppliance.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Suivi {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	private boolean offre_Commercial;
	private Float montont;
	private String compterendu;
	@OneToOne
	private TypePrestation typeprestation;
	@ManyToOne
	private POV pov;
	

} 
