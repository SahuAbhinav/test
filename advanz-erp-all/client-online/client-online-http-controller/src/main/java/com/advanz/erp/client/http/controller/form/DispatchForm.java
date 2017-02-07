package com.advanz.erp.client.http.controller.form;

import java.util.ArrayList;
import java.util.List;

import com.advanz.erp.masters.model.DispatchDetailDTO;
import com.advanz.erp.masters.model.DispatchMasterDTO;

public class DispatchForm {

	private List<DispatchDetailDTO> dispatchDetailList = new ArrayList<DispatchDetailDTO>();

	private String cityName;
	private String billingAddress;
	private String phonNo;
	private String contactPerson;
	private String state;
	private String transporterName;
	private int indexNo;
	private String lastDispatchDate;

	public String getLastDispatchDate() {
		return lastDispatchDate;
	}

	public void setLastDispatchDate(String lastDispatchDate) {
		this.lastDispatchDate = lastDispatchDate;
	}

	private String dispatchNumber;

	public String getDispatchNumber() {
		return dispatchNumber;
	}

	public void setDispatchNumber(String dispatchNumber) {
		this.dispatchNumber = dispatchNumber;
	}

	public int getIndexNo() {
		return indexNo;
	}

	public void setIndexNo(int indexNo) {
		this.indexNo = indexNo;
	}

	public String getTransporterName() {
		return transporterName;
	}

	public void setTransporterName(String transporterName) {
		this.transporterName = transporterName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getPhonNo() {
		return phonNo;
	}

	public void setPhonNo(String phonNo) {
		this.phonNo = phonNo;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public DispatchMasterDTO getDispatchMasterDTO() {
		return dispatchMasterDTO;
	}

	public void setDispatchMasterDTO(DispatchMasterDTO dispatchMasterDTO) {
		this.dispatchMasterDTO = dispatchMasterDTO;
	}

	private DispatchMasterDTO dispatchMasterDTO;
	private Integer dispatchAutoId;

	public Integer getDispatchAutoId() {
		return dispatchAutoId;
	}

	public void setDispatchAutoId(Integer dispatchAutoId) {
		this.dispatchAutoId = dispatchAutoId;
	}

	public List<DispatchDetailDTO> getDispatchDetailList() {
		return dispatchDetailList;
	}

	public void setDispatchDetailList(List<DispatchDetailDTO> dispatchDetailList) {
		this.dispatchDetailList = dispatchDetailList;
	}

	@Override
	public String toString() {
		return "DispatchForm [dispatchMasterDTO=" + dispatchMasterDTO + "]";
	}

}
