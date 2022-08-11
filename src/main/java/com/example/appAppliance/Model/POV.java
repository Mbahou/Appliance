package com.example.appAppliance.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class POV {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date date_debut;
	private Date date_fin;
	private String description;
	private String compte_manager;
	private String ingenieur_cybersecurite;
	private String analyse_cybersecurite;
	private String libelle_pov;
	@ManyToOne
	private Appliance appliance;
	@ManyToOne
	private Client client;


	

}
