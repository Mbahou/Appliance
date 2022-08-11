package com.example.appAppliance.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appliance {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id", nullable=false)
	private Long id;
	private String libelle;
	private String  dbid; 
	private Boolean disponibilite;
	private String ref;
	@OneToOne
	private   Type type;
	
  


}
