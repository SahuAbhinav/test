package com.advanz.erp.masters.model;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class BulkFiberMasterDTO extends BaseDTO{
	private Integer bulkFiberId;
	private Date bulkFiberDate;
	private String runNo;
	private MastersDTO shiftMasterDTO;
	private MastersDTO gradeMasterDTO;
	
	private String shiftEng;
	private String remark;
	private String productionPlanning;
	private String batchNo;
	private String totalBag;
	private String totalBulk;
	
	private Date toDate;
	private Date fromDate;
	
	private Integer next;
	private Integer previous;
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	private List<BulkFiberDetailDTO> bulkFiberDetailDTOList;
	
	public Integer getBulkFiberId() {
		return bulkFiberId;
	}
	public void setBulkFiberId(Integer bulkFiberId) {
		this.bulkFiberId = bulkFiberId;
	}
	public Date getBulkFiberDate() {
		return bulkFiberDate;
	}
	public void setBulkFiberDate(Date bulkFiberDate) {
		this.bulkFiberDate = bulkFiberDate;
	}
	public String getRunNo() {
		return runNo;
	}
	public void setRunNo(String runNo) {
		this.runNo = runNo;
	}
	public MastersDTO getShiftMasterDTO() {
		return shiftMasterDTO;
	}
	public void setShiftMasterDTO(MastersDTO shiftMasterDTO) {
		this.shiftMasterDTO = shiftMasterDTO;
	}
	public MastersDTO getGradeMasterDTO() {
		return gradeMasterDTO;
	}
	public void setGradeMasterDTO(MastersDTO gradeMasterDTO) {
		this.gradeMasterDTO = gradeMasterDTO;
	}
	public String getShiftEng() {
		return shiftEng;
	}
	public void setShiftEng(String shiftEng) {
		this.shiftEng = shiftEng;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getProductionPlanning() {
		return productionPlanning;
	}
	public void setProductionPlanning(String productionPlanning) {
		this.productionPlanning = productionPlanning;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getTotalBag() {
		return totalBag;
	}
	public void setTotalBag(String totalBag) {
		this.totalBag = totalBag;
	}
	public String getTotalBulk() {
		return totalBulk;
	}
	public void setTotalBulk(String totalBulk) {
		this.totalBulk = totalBulk;
	}
	public List<BulkFiberDetailDTO> getBulkFiberDetailDTOList() {
		return bulkFiberDetailDTOList;
	}
	public void setBulkFiberDetailDTOList(
			List<BulkFiberDetailDTO> bulkFiberDetailDTOList) {
		this.bulkFiberDetailDTOList = bulkFiberDetailDTOList;
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
