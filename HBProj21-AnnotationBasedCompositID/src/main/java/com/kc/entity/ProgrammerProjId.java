package com.kc.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ProgrammerProjId implements Serializable{
	@Type(type = "int")
	private Integer pId;
	@Type(type = "int")
	private Integer projId;
	
}
