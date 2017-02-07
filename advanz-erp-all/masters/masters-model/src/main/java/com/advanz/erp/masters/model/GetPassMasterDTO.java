package com.advanz.erp.masters.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.advanz.erp.common.model.BaseDTO;

public class GetPassMasterDTO extends BaseDTO {
	
	
	private String transactionSeries;
	private String finyr;
	private Integer gatePassAutoId;
	private String gatePassType;
	private String gatePassNumber;
	private Integer gatePassId;
	private Date gatePassDate;
	private String gatePassIssuedBy;
	private Time gatePassIssueTime;
	private BranchDTO branchDTO;
	private PartyDTO partyDTO;
	private List<GetPassDetailDTO> getPassDetailDTOList;
	private String sentThrough;
	private String vehicalNumber;
	private Integer approved;
	private Date approvedDate;
	private String gatePassPurpose;
	private String getPassYearId; 
	private String itemName;
	
	private String partyName;
	private String remark;
	private Integer itemId;
	private Integer partyId;
	private Date toDate;
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
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
	public Integer getGatePassAutoId() {
		return gatePassAutoId;
	}
	public void setGatePassAutoId(Integer gatePassAutoId) {
		this.gatePassAutoId = gatePassAutoId;
	}
	public String getGatePassType() {
		return gatePassType;
	}
	public void setGatePassType(String gatePassType) {
		this.gatePassType = gatePassType;
	}
	public String getGatePassNumber() {
		return gatePassNumber;
	}
	public void setGatePassNumber(String gatePassNumber) {
		this.gatePassNumber = gatePassNumber;
	}
	public Integer getGatePassId() {
		return gatePassId;
	}
	public void setGatePassId(Integer gatePassId) {
		this.gatePassId = gatePassId;
	}
	public Date getGatePassDate() {
		return gatePassDate;
	}
	public void setGatePassDate(Date gatePassDate) {
		this.gatePassDate = gatePassDate;
	}
	public String getGatePassIssuedBy() {
		return gatePassIssuedBy;
	}
	public void setGatePassIssuedBy(String gatePassIssuedBy) {
		this.gatePassIssuedBy = gatePassIssuedBy;
	}
	public Time getGatePassIssueTime() {
		return gatePassIssueTime;
	}
	public void setGatePassIssueTime(Time gatePassIssueTime) {
		this.gatePassIssueTime = gatePassIssueTime;
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
	public String getSentThrough() {
		return sentThrough;
	}
	public void setSentThrough(String sentThrough) {
		this.sentThrough = sentThrough;
	}
	public String getVehicalNumber() {
		return vehicalNumber;
	}
	public void setVehicalNumber(String vehicalNumber) {
		this.vehicalNumber = vehicalNumber;
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
	public String getGatePassPurpose() {
		return gatePassPurpose;
	}
	public void setGatePassPurpose(String gatePassPurpose) {
		this.gatePassPurpose = gatePassPurpose;
	}
	
	public String getGetPassYearId() {
		return getPassYearId;
	}
	public void setGetPassYearId(String getPassYearId) {
		this.getPassYearId = getPassYearId;
	}
	public List<GetPassDetailDTO> getGetPassDetailDTOList() {
		return getPassDetailDTOList;
	}
	public void setGetPassDetailDTOList(List<GetPassDetailDTO> getPassDetailDTOList) {
		this.getPassDetailDTOList = getPassDetailDTOList;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getPartyId() {
		return partyId;
	}
	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	
}
