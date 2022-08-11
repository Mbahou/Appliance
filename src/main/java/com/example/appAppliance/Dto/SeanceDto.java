package com.example.appAppliance.Dto;

import java.util.Date;
import java.util.List;

import com.example.appAppliance.Model.POV;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class SeanceDto {
	private Long id;
	private Date dateseance;
	private String resumer;
	private String participant;
	
	private POVdto povdto;
	
	

}
