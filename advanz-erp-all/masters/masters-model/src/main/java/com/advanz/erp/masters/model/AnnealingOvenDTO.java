package com.advanz.erp.masters.model;



import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;

public class AnnealingOvenDTO extends BaseDTO {

	private Integer sno;

	private Date ovenDate;

	private Time ovenTime;

	private Integer shiftMastersId;

	private MastersDTO shiftMastersDTO;

	private Double hsdDipReading;

	private Double lpgReading;

	private String shiftEngineerName;

	private Double transformerTemp;

	private Double ovenTempZone2;

	private Double ovenTempZone3;

	private Double ovenTempZone4;

	private Double ovenTempZone1;

	private Double totalFuelUsed;

	private Double initialReading;

	private Double finalReading;

	private Double actualReading;

	private String fuelUsedType;

	private String ovenRemark;

	private Date fromDate;
	private Date toDate;
	public Integer getShiftMastersId() {
		return shiftMastersId;
	}

	public void setShiftMastersId(Integer shiftMastersId) {
		this.shiftMastersId = shiftMastersId;
	}

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public Date getOvenDate() {
		return ovenDate;
		
	}
	
	public String getOvenDate1() {
		if(ovenDate!=null){
			SimpleDateFormat obj =new SimpleDateFormat("dd-MMM-yyyy");
			return obj.format(ovenDate);
		}
		return "";
		
	}
	
	

	public void setOvenDate(Date ovenDate) {

		this.ovenDate = ovenDate;
	}

	public Time getOvenTime() {
		return ovenTime;
	}

	public void setOvenTime(Time ovenTime) {
		this.ovenTime = ovenTime;
	}

	public MastersDTO getShiftMastersDTO() {
		return shiftMastersDTO;
	}

	public void setShiftMastersDTO(MastersDTO shiftMastersDTO) {
		this.shiftMastersDTO = shiftMastersDTO;
	}

	public Double getHsdDipReading() {
		return hsdDipReading;
	}

	public void setHsdDipReading(Double hsdDipReading) {
		this.hsdDipReading = hsdDipReading;
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

	public Double getTransformerTemp() {
		return transformerTemp;
	}

	public void setTransformerTemp(Double transformerTemp) {
		this.transformerTemp = transformerTemp;
	}

	public Double getOvenTempZone2() {
		return ovenTempZone2;
	}

	public void setOvenTempZone2(Double ovenTempZone2) {
		this.ovenTempZone2 = ovenTempZone2;
	}

	public Double getOvenTempZone3() {
		return ovenTempZone3;
	}

	public void setOvenTempZone3(Double ovenTempZone3) {
		this.ovenTempZone3 = ovenTempZone3;
	}

	public Double getOvenTempZone4() {
		return ovenTempZone4;
	}

	public void setOvenTempZone4(Double ovenTempZone4) {
		this.ovenTempZone4 = ovenTempZone4;
	}

	public Double getOvenTempZone1() {
		return ovenTempZone1;
	}

	public void setOvenTempZone1(Double ovenTempZone1) {
		this.ovenTempZone1 = ovenTempZone1;
	}

	public Double getTotalFuelUsed() {
		return totalFuelUsed;
	}

	public void setTotalFuelUsed(Double totalFuelUsed) {
		this.totalFuelUsed = totalFuelUsed;
	}

	public Double getInitialReading() {
		return initialReading;
	}

	public void setInitialReading(Double initialReading) {
		this.initialReading = initialReading;
	}

	public Double getFinalReading() {
		return finalReading;
	}

	public void setFinalReading(Double finalReading) {
		this.finalReading = finalReading;
	}

	public Double getActualReading() {
		return actualReading;
	}

	public void setActualReading(Double actualReading) {
		this.actualReading = actualReading;
	}

	public String getFuelUsedType() {
		return fuelUsedType;
	}

	public void setFuelUsedType(String fuelUsedType) {
		this.fuelUsedType = fuelUsedType;
	}

	public String getOvenRemark() {
		return ovenRemark;
	}

	public void setOvenRemark(String ovenRemark) {
		this.ovenRemark = ovenRemark;
	}

	

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

}
