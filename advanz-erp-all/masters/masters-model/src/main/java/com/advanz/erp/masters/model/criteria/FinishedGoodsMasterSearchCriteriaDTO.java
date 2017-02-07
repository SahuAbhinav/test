package com.advanz.erp.masters.model.criteria;

import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class FinishedGoodsMasterSearchCriteriaDTO extends BaseDTO {
	private String soNumber;
	private String batchNo;
	private String finishedGoodsNumber;
	private Date finishGoodDate;
	private String batchNoSearch;
	
	private String itemName;
	
	private Date fromDate;
	private Date toDate;
	
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getBatchNoSearch() {
		return batchNoSearch;
	}
	public void setBatchNoSearch(String batchNoSearch) {
		this.batchNoSearch = batchNoSearch;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getSoNumber() {
		return soNumber;
	}
	public void setSoNumber(String soNumber) {
		this.soNumber = soNumber;
	}
	
	public String getFinishedGoodsNumber() {
		return finishedGoodsNumber;
	}
	public void setFinishedGoodsNumber(String finishedGoodsNumber) {
		this.finishedGoodsNumber = finishedGoodsNumber;
	}
	public Date getFinishGoodDate() {
		return finishGoodDate;
	}
	public void setFinishGoodDate(Date finishGoodDate) {
		this.finishGoodDate = finishGoodDate;
	}
	
	
	
	
	

}
