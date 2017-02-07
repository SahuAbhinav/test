package com.advanz.erp.masters.model;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class BlanketProductionMasterNewDTO extends BaseDTO {
	private Integer blanketProductionId;
	private Date blanketProductionDate;
	private String runNo;
	private MastersDTO shiftMasterDTO;
	private MastersDTO gradeMasterDTO;
	private String shiftEngineerName;
	private String remark;
	private String productionPlanning;
	private String batchNumber;
	private List<BlanketProductionDetailNewDTO> blanketProductionDetailNewDTOList;
	private Double blnktWeight;

	private Double rollPacked;
	private Double cartonUsed;
	private Double shortLenght;
	private Double edgeTrim;
	private Double bulkFiber;

	private Integer rollNo;
	private Integer approveStatus;
	private String approveUserId;
	private Date approveDate;
	private Integer spliterCount;
	private String splitedCounts;
	
	public String getSplitedCounts() {
		return splitedCounts;
	}

	public void setSplitedCounts(String splitedCounts) {
		this.splitedCounts = splitedCounts;
	}

	public Integer getSpliterCount() {
		return spliterCount;
	}

	public void setSpliterCount(Integer spliterCount) {
		this.spliterCount = spliterCount;
	}

	public Integer getBlanketProductionId() {
		return blanketProductionId;
	}

	public void setBlanketProductionId(Integer blanketProductionId) {
		this.blanketProductionId = blanketProductionId;
	}

	public Date getBlanketProductionDate() {
		return blanketProductionDate;
	}

	public void setBlanketProductionDate(Date blanketProductionDate) {
		this.blanketProductionDate = blanketProductionDate;
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

	public String getShiftEngineerName() {
		return shiftEngineerName;
	}

	public void setShiftEngineerName(String shiftEngineerName) {
		this.shiftEngineerName = shiftEngineerName;
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

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public List<BlanketProductionDetailNewDTO> getBlanketProductionDetailNewDTOList() {
		return blanketProductionDetailNewDTOList;
	}

	public void setBlanketProductionDetailNewDTOList(
			List<BlanketProductionDetailNewDTO> blanketProductionDetailNewDTOList) {
		this.blanketProductionDetailNewDTOList = blanketProductionDetailNewDTOList;
	}

	public Double getBlnktWeight() {
		return blnktWeight;
	}

	public void setBlnktWeight(Double blnktWeight) {
		this.blnktWeight = blnktWeight;
	}

	public Double getRollPacked() {
		return rollPacked;
	}

	public void setRollPacked(Double rollPacked) {
		this.rollPacked = rollPacked;
	}

	public Double getCartonUsed() {
		return cartonUsed;
	}

	public void setCartonUsed(Double cartonUsed) {
		this.cartonUsed = cartonUsed;
	}

	public Double getShortLenght() {
		return shortLenght;
	}

	public void setShortLenght(Double shortLenght) {
		this.shortLenght = shortLenght;
	}

	public Double getEdgeTrim() {
		return edgeTrim;
	}

	public void setEdgeTrim(Double edgeTrim) {
		this.edgeTrim = edgeTrim;
	}

	public Double getBulkFiber() {
		return bulkFiber;
	}

	public void setBulkFiber(Double bulkFiber) {
		this.bulkFiber = bulkFiber;
	}

	public Integer getRollNo() {
		return rollNo;
	}

	public void setRollNo(Integer rollNo) {
		this.rollNo = rollNo;
	}

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getApproveUserId() {
		return approveUserId;
	}

	public void setApproveUserId(String approveUserId) {
		this.approveUserId = approveUserId;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

}
