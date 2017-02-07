package com.advanz.erp.masters.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;


@Entity
@Table(name = "M_COUNTRY")
public class CountryEntity extends BaseEntity{
	
	@Id 
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name="country_id")
	private Integer countryId;
	
	
	@Column(name="country_code")
	private String countryCode;
	
	
	
	@Column(name="country_name")
	private String countryName;
	
	@Column(name="description")
	private String description;
	
	
	


	public Integer getCountryId() {
		return countryId;
	}



	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}



	public String getCountryCode() {
		return countryCode;
	}



	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}



	public String getCountryName() {
		return countryName;
	}



	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	


	
	
	
	public Integer getUid() {
		// TODO Auto-generated method stub
	  return countryId;
	}

	public void setUid(Integer uid) {
		// TODO Auto-generated method stub
		countryId=uid;
		
	}


	
}
