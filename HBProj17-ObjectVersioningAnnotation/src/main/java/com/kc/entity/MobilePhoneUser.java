package com.kc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "MOBILE_PHONE_USER")
public class MobilePhoneUser {
	@Id
	private Long regNo;
	@NonNull
	private Long mobileNo;
	@NonNull
	private boolean prepaid; 
	@NonNull
	@Column(length = 20)
	private String circle;
	@NonNull
	@Column(length = 20)
	private String callerTune;
	@Version
	private Integer updationsCount;
	
}
