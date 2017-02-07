package com.advanz.erp.masters.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;
@Entity
@Table(name="m_store_location")
public class StoreLocationEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7228112474060537495L;
	
	@Id
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name = "STORE_LOCATION_ID", unique = true, updatable = false, length=11)
	private Integer storeLocationId;
	
	@Column(name="LOCATION_CODE", length=4)
	private String locationCode;
	
	@Column(name="STORE_LOCATION", length=55)
	private String storeLocation;
	
//	@Column(name="STORE_AREA", length=35)
//	private String storeArea;
	
	@Column(name="STORE_LENGTH", length=11)
	private Double storeLength;
	
	@Column(name="STORE_WIDTH", length=11)
	private Double storeWidth;
	
	@Column(name="STORE_HEIGHT", length=11)
	private Double storeHeight;
	
	@Column(name="STORE_CONDITION", length=35)
	private String storeCondition;
	

	
	/**
	 * @return the storeLocationId
	 */
	public Integer getStoreLocationId() {
		return storeLocationId;
	}
	/**
	 * @param storeLocationId the storeLocationId to set
	 */
	public void setStoreLocationId(Integer storeLocationId) {
		this.storeLocationId = storeLocationId;
	}
	/**
	 * @return the locationCode
	 */
	public String getLocationCode() {
		return locationCode;
	}
	/**
	 * @param locationCode the locationCode to set
	 */
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	/**
	 * @return the storeLocation
	 */
	public String getStoreLocation() {
		return storeLocation;
	}
	/**
	 * @param storeLocation the storeLocation to set
	 */
	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}
	/**
	 * @return the storeArea
	 */
//	public String getStoreArea() {
//		return storeArea;
//	}
//	/**
//	 * @param storeArea the storeArea to set
//	 */
//	public void setStoreArea(String storeArea) {
//		this.storeArea = storeArea;
//	}
	/**
	 * @return the storeLength
	 */
	public Double getStoreLength() {
		return storeLength;
	}
	/**
	 * @param storeLength the storeLength to set
	 */
	public void setStoreLength(Double storeLength) {
		this.storeLength = storeLength;
	}
	/**
	 * @return the storeWidth
	 */
	public Double getStoreWidth() {
		return storeWidth;
	}
	/**
	 * @param storeWidth the storeWidth to set
	 */
	public void setStoreWidth(Double storeWidth) {
		this.storeWidth = storeWidth;
	}
	/**
	 * @return the storeHeight
	 */
	public Double getStoreHeight() {
		return storeHeight;
	}
	/**
	 * @param storeHeight the storeHeight to set
	 */
	public void setStoreHeight(Double storeHeight) {
		this.storeHeight = storeHeight;
	}
	/**
	 * @return the storeCondition
	 */
	public String getStoreCondition() {
		return storeCondition;
	}
	/**
	 * @param storeCondition the storeCondition to set
	 */
	public void setStoreCondition(String storeCondition) {
		this.storeCondition = storeCondition;
	}	

}
