package com.kc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name="ARTIST")
@Data
@AllArgsConstructor
public class Artist implements Serializable {
	@Id
	@Column(name = "AID")
	private Integer aid;
	@Column(name = "NAME", length = 15)
	private String name;
	@Column(name = "ADDRESS", length = 20)
	private String address;
	@Column(name = "MOBILENO")
	private Long mobileNo;
	@Column(name = "CATEGORY", length = 15)
	private String category;
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Artist() {
		super();
	}
	public Artist(Integer aid, String name, String address, Long mobileNo, String category) {
		super();
		this.aid = aid;
		this.name = name;
		this.address = address;
		this.mobileNo = mobileNo;
		this.category = category;
	}
	@Override
	public String toString() {
		return "Artist [aid=" + aid + ", name=" + name + ", address=" + address + ", mobileNo=" + mobileNo
				+ ", category=" + category + "]";
	}
	
	
	
	//As we are using @Data from Lombok so no need to write)
	
}
