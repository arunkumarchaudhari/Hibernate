package com.kc.entity;

import java.sql.Timestamp;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class MobilePhoneUser {
	private Long regNo;
	@NonNull
	private Long mobileNo;
	@NonNull
	private boolean prepaid; 
	@NonNull
	private String circle;
	@NonNull
	private String callerTune;
	private Timestamp lastUpdated;
	
}
