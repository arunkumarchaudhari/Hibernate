package com.kc.entity;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PROGRAMMER_PROJECT_INFO")
public class ProgrammerProjectInfo {
	@EmbeddedId
	private ProgrammerProjId id; //HAS-Property
	
	@Column(length = 20)
	@Type(type = "string")
	private String pName;
	
	@Column(length = 20)
	@Type(type = "string")
	private String projName;
	
	@Type(type = "int")
	private Integer teamSize;
	
}
