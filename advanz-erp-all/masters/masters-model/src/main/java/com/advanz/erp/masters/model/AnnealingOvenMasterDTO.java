package com.advanz.erp.masters.model;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.model.BaseDTO;
@SuppressWarnings("serial")
public class AnnealingOvenMasterDTO extends BaseDTO{

	
	private Integer ovenId;
	
	private Date ovenDate;
	
	private MastersDTO shiftMasterDTO;
	
	private Double lpgReading;
	
	private String shiftEngineerName;
	
	private Double initialReading;
	
	private Double finalReading;
	
	private Double totalReading;
	
	private Double refilling;
	
	private Double initialReading1;
	
	private Double finalReading1;
	
	private Double totalReading1;
	
	private Double hsdDipReading;

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

	private List<AnnealingOvenDetailDTO> annealingOvenDetailDTOList;
	
	public List<AnnealingOvenDetailDTO> getAnnealingOvenDetailDTOList() {
		return annealingOvenDetailDTOList;
	}

	public void setAnnealingOvenDetailDTOList(
			List<AnnealingOvenDetailDTO> annealingOvenDetailDTOList) {
		this.annealingOvenDetailDTOList = annealingOvenDetailDTOList;
	}

	
	public Integer getOvenId() {
		return ovenId;
	}

	public void setOvenId(Integer ovenId) {
		this.ovenId = ovenId;
	}

	public Date getOvenDate() {
		return ovenDate;
	}

	public void setOvenDate(Date ovenDate) {
		this.ovenDate = ovenDate;
	}

	

	public MastersDTO getShiftMasterDTO() {
		return shiftMasterDTO;
	}

	public void setShiftMasterDTO(MastersDTO shiftMasterDTO) {
		this.shiftMasterDTO = shiftMasterDTO;
	}

	public Double getinitialReading1() {
		return initialReading1;
	}

	public void setinitialReading1(Double initialReading1) {
		this.initialReading1 = initialReading1;
	}

	public Double getLpgReading() {
		return lpgReading;
	}

	public void setLpgReading(Double lpgReading) {
		this.lpgReading = lpgReading;
	}

	public String getShiftEngineerName() {
		return shiftEngineerName;
	}

	public void setShiftEngineerName(String shiftEngineerName) {
		this.shiftEngineerName = shiftEngineerName;
	}

	public Double getinitialReading() {
		return initialReading;
	}

	public void setinitialReading(Double initialReading) {
		this.initialReading = initialReading;
	}

	public Double getFinalReading() {
		return finalReading;
	}

	public void setFinalReading(Double finalReading) {
		this.finalReading = finalReading;
	}

	public Double getTotalReading() {
		return totalReading;
	}

	public void setTotalReading(Double totalReading) {
		this.totalReading = totalReading;
	}

	public Double getRefilling() {
		return refilling;
	}

	public void setRefilling(Double refilling) {
		this.refilling = refilling;
	}

	public Double getFinalReading1() {
		return finalReading1;
	}

	public void setFinalReading1(Double finalReading1) {
		this.finalReading1 = finalReading1;
	}

	public Double getTotalReading1() {
		return totalReading1;
	}

	public void setTotalReading1(Double totalReading1) {
		this.totalReading1 = totalReading1;
	}

	public Double getHsdDipReading() {
		return hsdDipReading;
	}

	public void setHsdDipReading(Double hsdDipReading) {
		this.hsdDipReading = hsdDipReading;
	}
	
}
