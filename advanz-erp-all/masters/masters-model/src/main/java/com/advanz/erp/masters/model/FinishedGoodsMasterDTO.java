package com.advanz.erp.masters.model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import com.advanz.erp.common.model.BaseDTO;



@SuppressWarnings("serial")
public class FinishedGoodsMasterDTO  extends BaseDTO {
	
	private String transactionSeries;
	private Integer finishGoodAutoId;
	private String finYear;
	private String finishedGoodsNumber;
	private Integer finishGoodId;
	private Date finishGoodDate;
	private Integer branchId;
	private String batchNo;
	private Date mfgDate;
	private String itemValue;
private Integer finishedGoodsAutoId;
private Double totalQuantity;

private Integer approvalFlag;
private Date aprovedDate;
private Integer next;
private Integer previous;
private String checkShiftReport;

private String runNo;
private MastersDTO mastersDTO;
	public String getCheckShiftReport() {
	return checkShiftReport;
}

public void setCheckShiftReport(String checkShiftReport) {
	this.checkShiftReport = checkShiftReport;
}

	public Double getTotalQuantity() {
	return totalQuantity;
}

public void setTotalQuantity(Double totalQuantity) {
	this.totalQuantity = totalQuantity;
}

	public Integer getFinishedGoodsAutoId() {
	return finishedGoodsAutoId;
}

public void setFinishedGoodsAutoId(Integer finishedGoodsAutoId) {
	this.finishedGoodsAutoId = finishedGoodsAutoId;
}




	private Date expiryDate;
	
	private Double mrp;
	

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public Double getMrp() {
		return mrp;
	}

	public Date getMfgDate() {
		return mfgDate;
	}

	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	

	
	
	
	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public void setMfgDate(Date mfgDate) {
		this.mfgDate = mfgDate;
	}




	private List<FinishedGoodsDetailDTO> finishedGoodsDetailDTOList;
	
	private BranchDTO branchDTO;
	
	private BatchDTO batchDTO;
	
	private Integer basicRate;
	
	public BatchDTO getBatchDTO() {
		return batchDTO;
	}

	public void setBatchDTO(BatchDTO batchDTO) {
		this.batchDTO = batchDTO;
	}

	private String orderSeries;

	public String getOrderSeries() {
		return orderSeries;
	}

	public void setOrderSeries(String orderSeries) {
		this.orderSeries = orderSeries;
	}

	public String getTransactionSeries() {
		return transactionSeries;
	}

	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}

	

	public Integer getFinishGoodAutoId() {
		return finishGoodAutoId;
	}

	public void setFinishGoodAutoId(Integer finishGoodAutoId) {
		this.finishGoodAutoId = finishGoodAutoId;
	}

	public String getFinYear() {
		return finYear;
	}

	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}

	public String getFinishedGoodsNumber() {
		return finishedGoodsNumber;
	}

	public void setFinishedGoodsNumber(String finishedGoodsNumber) {
		this.finishedGoodsNumber = finishedGoodsNumber;
	}

	public Integer getFinishGoodId() {
		return finishGoodId;
	}

	public void setFinishGoodId(Integer finishGoodId) {
		this.finishGoodId = finishGoodId;
	}

	public Date getFinishGoodDate() {
		return finishGoodDate;
	}

	public void setFinishGoodDate(Date finishGoodDate) {
		this.finishGoodDate = finishGoodDate;
	}

	public Integer getBranchId() {
		return branchId;
	}

	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}

	

	public List<FinishedGoodsDetailDTO> getFinishedGoodsDetailDTOList() {
		return finishedGoodsDetailDTOList;
	}

	public void setFinishedGoodsDetailDTOList(
			List<FinishedGoodsDetailDTO> finishedGoodsDetailDTOList) {
		this.finishedGoodsDetailDTOList = finishedGoodsDetailDTOList;
	}

	public BranchDTO getBranchDTO() {
		return branchDTO;
	}

	public void setBranchDTO(BranchDTO branchDTO) {
		this.branchDTO = branchDTO;
	}

	public Integer getBasicRate() {
		return basicRate;
	}

	public void setBasicRate(Integer basicRate) {
		this.basicRate = basicRate;
	}

	public Integer getApprovalFlag() {
		return approvalFlag;
	}

	public void setApprovalFlag(Integer approvalFlag) {
		this.approvalFlag = approvalFlag;
	}

	public Date getAprovedDate() {
		return aprovedDate;
	}

	public void setAprovedDate(Date aprovedDate) {
		this.aprovedDate = aprovedDate;
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

	public String getRunNo() {
		return runNo;
	}

	public void setRunNo(String runNo) {
		this.runNo = runNo;
	}

	public MastersDTO getMastersDTO() {
		return mastersDTO;
	}

	public void setMastersDTO(MastersDTO mastersDTO) {
		this.mastersDTO = mastersDTO;
	}	
	
	
}
