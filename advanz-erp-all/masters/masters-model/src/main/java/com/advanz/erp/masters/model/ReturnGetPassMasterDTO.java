package com.advanz.erp.masters.model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import com.advanz.erp.common.model.BaseDTO;

public class ReturnGetPassMasterDTO extends BaseDTO {
	private String transactionSeries;
	private String finyr;
	private Integer returnGatePassAutoId;
	private String returnGatePassNumber;
	private Integer returnGatePassId;
	private Date returnGatePassDate;
	private String returnGatePassReceivedBy;
	private Time returnGatePassTime;
	private String gatePassNumber;
	private Integer approved;
	private Date approvedDate;
	private String returnGatePassPurpose;
	
	private BranchDTO branchDTO;
	private PartyDTO partyDTO;
	private List<ReturnGetPassDetailDTO> returnGetPassDetailDTOList;
	private String itemName;
	private String returnGetPassYearId;
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public BranchDTO getBranchDTO() {
		return branchDTO;
	}
	public void setBranchDTO(BranchDTO branchDTO) {
		this.branchDTO = branchDTO;
	}
	public PartyDTO getPartyDTO() {
		return partyDTO;
	}
	public void setPartyDTO(PartyDTO partyDTO) {
		this.partyDTO = partyDTO;
	}
	public String getTransactionSeries() {
		return transactionSeries;
	}
	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}
	public String getFinyr() {
		return finyr;
	}
	public void setFinyr(String finyr) {
		this.finyr = finyr;
	}
	public Integer getReturnGatePassAutoId() {
		return returnGatePassAutoId;
	}
	public void setReturnGatePassAutoId(Integer returnGatePassAutoId) {
		this.returnGatePassAutoId = returnGatePassAutoId;
	}
	public String getReturnGatePassNumber() {
		return returnGatePassNumber;
	}
	public void setReturnGatePassNumber(String returnGatePassNumber) {
		this.returnGatePassNumber = returnGatePassNumber;
	}
	public Integer getReturnGatePassId() {
		return returnGatePassId;
	}
	public void setReturnGatePassId(Integer returnGatePassId) {
		this.returnGatePassId = returnGatePassId;
	}
	public Date getReturnGatePassDate() {
		return returnGatePassDate;
	}
	public void setReturnGatePassDate(Date returnGatePassDate) {
		this.returnGatePassDate = returnGatePassDate;
	}
	public String getReturnGatePassReceivedBy() {
		return returnGatePassReceivedBy;
	}
	public void setReturnGatePassReceivedBy(String returnGatePassReceivedBy) {
		this.returnGatePassReceivedBy = returnGatePassReceivedBy;
	}
	public Time getReturnGatePassTime() {
		return returnGatePassTime;
	}
	public void setReturnGatePassTime(Time returnGatePassTime) {
		this.returnGatePassTime = returnGatePassTime;
	}
	public String getGatePassNumber() {
		return gatePassNumber;
	}
	public void setGatePassNumber(String gatePassNumber) {
		this.gatePassNumber = gatePassNumber;
	}
	public Integer getApproved() {
		return approved;
	}
	public void setApproved(Integer approved) {
		this.approved = approved;
	}
	public Date getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	public String getReturnGatePassPurpose() {
		return returnGatePassPurpose;
	}
	public void setReturnGatePassPurpose(String returnGatePassPurpose) {
		this.returnGatePassPurpose = returnGatePassPurpose;
	}
	public List<ReturnGetPassDetailDTO> getReturnGetPassDetailDTOList() {
		return returnGetPassDetailDTOList;
	}
	public void setReturnGetPassDetailDTOList(
			List<ReturnGetPassDetailDTO> returnGetPassDetailDTOList) {
		this.returnGetPassDetailDTOList = returnGetPassDetailDTOList;
	}
	public String getReturnGetPassYearId() {
		return returnGetPassYearId;
	}
	public void setReturnGetPassYearId(String returnGetPassYearId) {
		this.returnGetPassYearId = returnGetPassYearId;
	}
	
}
