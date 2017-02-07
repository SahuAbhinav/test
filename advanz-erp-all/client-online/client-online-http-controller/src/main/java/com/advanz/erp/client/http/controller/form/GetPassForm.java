package com.advanz.erp.client.http.controller.form;

import java.util.ArrayList;
import java.util.List;

import com.advanz.erp.masters.model.GetPassDetailDTO;
import com.advanz.erp.masters.model.GetPassMasterDTO;

public class GetPassForm {
	private List<GetPassMasterDTO> getPassMasterList = new ArrayList<GetPassMasterDTO>();
	private GetPassMasterDTO getPassMasterDTO;
	private Integer gatePassAutoId;
	private Integer indexNo;
	private String timeToshow;
	private String message;
	private Integer next;
	private Integer previous;
	private List<GetPassDetailDTO> getPassDetailDTOList = new ArrayList<GetPassDetailDTO>();
	private String lastGetPassDate;

	public String getLastGetPassDate() {
		return lastGetPassDate;
	}

	public void setLastGetPassDate(String lastGetPassDate) {
		this.lastGetPassDate = lastGetPassDate;
	}

	public List<GetPassMasterDTO> getGetPassMasterList() {
		return getPassMasterList;
	}

	public void setGetPassMasterList(List<GetPassMasterDTO> getPassMasterList) {
		this.getPassMasterList = getPassMasterList;
	}

	public GetPassMasterDTO getGetPassMasterDTO() {
		return getPassMasterDTO;
	}

	public void setGetPassMasterDTO(GetPassMasterDTO getPassMasterDTO) {
		this.getPassMasterDTO = getPassMasterDTO;
	}

	public Integer getGatePassAutoId() {
		return gatePassAutoId;
	}

	public void setGatePassAutoId(Integer gatePassAutoId) {
		this.gatePassAutoId = gatePassAutoId;
	}

	public List<GetPassDetailDTO> getGetPassDetailDTOList() {
		return getPassDetailDTOList;
	}

	public void setGetPassDetailDTOList(
			List<GetPassDetailDTO> getPassDetailDTOList) {
		this.getPassDetailDTOList = getPassDetailDTOList;
	}

	public Integer getIndexNo() {
		return indexNo;
	}

	public void setIndexNo(Integer indexNo) {
		this.indexNo = indexNo;
	}

	public String getTimeToshow() {
		return timeToshow;
	}

	public void setTimeToshow(String timeToshow) {
		this.timeToshow = timeToshow;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getNext() {
		return next;
	}

	public void setNext(Integer next) {
		this.next = next;
	}

	public Integer getPrevious() {
		return previous;
	}

	public void setPrevious(Integer previous) {
		this.previous = previous;
	}

}
