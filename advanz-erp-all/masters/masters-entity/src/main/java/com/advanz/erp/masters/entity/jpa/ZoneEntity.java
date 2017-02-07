package com.advanz.erp.masters.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;



@Entity
@Table(name = "M_ZONE")
public class ZoneEntity extends BaseEntity {
	
	/**
	 * 
	 */
	


	@Id 
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name="zone_id")
	private Integer zoneId;
	
	
	@Column(name="zone_code")
	private String zoneCode;
	
	
	@Column(name="zone_name")
	private String zoneName;
	

//	@Column(name="country_id")
//	private Integer countryId;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="country_id")
	private CountryEntity countryEntity;
	
	public CountryEntity getCountryEntity() {
		return countryEntity;
	}

	public void setCountryEntity(CountryEntity countryEntity) {
		this.countryEntity = countryEntity;
	}

//	@Override
	public Integer getUid() {
		// TODO Auto-generated method stub
	  return zoneId;
	}
	
	//@Override
	public void setUid(Integer uid) {
		// TODO Auto-generated method stub
		zoneId=uid;
		
	}

	public String getZoneCode() {
		return zoneCode;
	}

	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

//	public Integer getCountryId() {
//		return countryId;
//	}
//
//	public void setCountryId(Integer countryId) {
//		this.countryId = countryId;
//	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
		

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	//@Override
//	public String toString() {
//		return "BatchEntity [itemId=" + itemId + ", batchNo=" + batchNo + "]";
//	}
	
	


}
