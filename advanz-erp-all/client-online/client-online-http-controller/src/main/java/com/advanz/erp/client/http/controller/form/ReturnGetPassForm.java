package com.advanz.erp.client.http.controller.form;

import java.util.ArrayList;
import java.util.List;

import com.advanz.erp.masters.model.GetPassDetailDTO;
import com.advanz.erp.masters.model.GetPassMasterDTO;
import com.advanz.erp.masters.model.ReturnGetPassDetailDTO;
import com.advanz.erp.masters.model.ReturnGetPassMasterDTO;

public class ReturnGetPassForm {
	private List<ReturnGetPassMasterDTO> returnGetPassMasterList = new ArrayList<ReturnGetPassMasterDTO>();
	private ReturnGetPassMasterDTO returnGetPassMasterDTO;
	private Integer returnGatePassAutoId;
	private Integer indexNo;
	private String timeToshow;
	private Integer next;
	private Integer previous;
	private String message;
	private List<ReturnGetPassDetailDTO> returnGetPassDetailDTOList = new ArrayList<ReturnGetPassDetailDTO>();
	private String lastReturnGetPassDate;

	public String getLastReturnGetPassDate() {
		return lastReturnGetPassDate;
	}

	public void setLastReturnGetPassDate(String lastReturnGetPassDate) {
		this.lastReturnGetPassDate = lastReturnGetPassDate;
	}

	public List<ReturnGetPassMasterDTO> getReturnGetPassMasterList() {
		return returnGetPassMasterList;
	}

	public void setReturnGetPassMasterList(
			List<ReturnGetPassMasterDTO> returnGetPassMasterList) {
		this.returnGetPassMasterList = returnGetPassMasterList;
	}

	public ReturnGetPassMasterDTO getReturnGetPassMasterDTO() {
		return returnGetPassMasterDTO;
	}

	public void setReturnGetPassMasterDTO(
			ReturnGetPassMasterDTO returnGetPassMasterDTO) {
		this.returnGetPassMasterDTO = returnGetPassMasterDTO;
	}

	public Integer getReturnGatePassAutoId() {
		return returnGatePassAutoId;
	}

	public void setReturnGatePassAutoId(Integer returnGatePassAutoId) {
		this.returnGatePassAutoId = returnGatePassAutoId;
	}

	public List<ReturnGetPassDetailDTO> getReturnGetPassDetailDTOList() {
		return returnGetPassDetailDTOList;
	}

	public void setReturnGetPassDetailDTOList(
			List<ReturnGetPassDetailDTO> returnGetPassDetailDTOList) {
		this.returnGetPassDetailDTOList = returnGetPassDetailDTOList;
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
