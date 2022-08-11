package com.example.appAppliance.Model;

import java.util.Date;
import java.util.List;

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

public class Seance {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date dateseance;
	private String resumer;
	private String participant;
	@ManyToOne
	private POV pov;
	

	
	
	

}
