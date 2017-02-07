package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class StoreLocationDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8523846108693965501L;
	
	private Integer storeLocationId;
	private String locationCode;
	private String storeLocation;
	private String storeArea;
	private Double storeLength;
	private Double storeWidth;
	private Double storeHeight;
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
	public String getStoreArea() {
		return storeArea;
	}
	/**
	 * @param storeArea the storeArea to set
	 */
	public void setStoreArea(String storeArea) {
		this.storeArea = storeArea;
	}
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StoreLocationDTO [storeLocationId=" + storeLocationId + ", locationCode="
				+ locationCode + ", storeLocation=" + storeLocation
				+ ", storeArea=" + storeArea + ", storeLength=" + storeLength
				+ ", storeWidth=" + storeWidth + ", storeHeight=" + storeHeight
				+ ", storeCondition=" + storeCondition + "]";
	}	
}
