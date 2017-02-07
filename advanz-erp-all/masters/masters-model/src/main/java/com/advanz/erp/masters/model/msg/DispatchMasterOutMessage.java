package com.advanz.erp.masters.model.msg;

import java.sql.Timestamp;
import java.util.List;

import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.masters.model.DispatchMasterDTO;

public class DispatchMasterOutMessage extends AdvanzErpBaseOutputMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DispatchMasterDTO dispatchMasterDTO;
	private List<DispatchMasterDTO> dispatchMasterDTOList;

	private Integer dispatchSeries;
	private Timestamp dispatchSeriesDate;

	public Timestamp getDispatchSeriesDate() {
		return dispatchSeriesDate;
	}

	public void setDispatchSeriesDate(Timestamp dispatchSeriesDate) {
		this.dispatchSeriesDate = dispatchSeriesDate;
	}

	public Integer getDispatchSeries() {
		return dispatchSeries;
	}

	public void setDispatchSeries(Integer dispatchSeries) {
		this.dispatchSeries = dispatchSeries;
	}

	public DispatchMasterDTO getDispatchMasterDTO() {
		return dispatchMasterDTO;
	}

	public void setDispatchMasterDTO(DispatchMasterDTO dispatchMasterDTO) {
		this.dispatchMasterDTO = dispatchMasterDTO;
	}

	public List<DispatchMasterDTO> getDispatchMasterDTOList() {
		return dispatchMasterDTOList;
	}

	public void setDispatchMasterDTOList(
			List<DispatchMasterDTO> dispatchMasterDTOList) {
		this.dispatchMasterDTOList = dispatchMasterDTOList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
