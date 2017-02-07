package com.advanz.erp.masters.model;

import java.sql.Time;
import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class MelterLogBookDTO extends BaseDTO {
	private Integer sno;
	private Date logDate;
	private String runNo;
	
	private String operatorName;
	private Time logTime;
	private Double inputCurrAmp;
	private Double inputVoltage;
	private Double inputKw;
	private Double power1DcVoltage;
	private Double power1DcAmp;
	private Double power2DcVoltage;
	private Double power2DcAmp;
	private Double power3DcVoltage;
	private Double power3DcAmp;
	private Double power1AcVoltage;
	private Double power1AcAmp;
	private Double power2AcVoltage;	
	private Double power2AcAmp;
	private Double power3AcVoltage;
	private Double power3AcAmp;
	private Double tapElectrodeAcVoltage;
	private Double tapElectrodeAcAmp;
	private Double electrodePositionPower1;
	private Double electrodePositionPower2;
	private Double electrodePositionPower3;
	private Double electrodePosition2Power1;
	private Double electrodePosition2Power2;
	private Double electrodePosition2Power3;
	private Double h2CylPressure;
	private Double totalPower;
	private Double poolLevel;
	private Double finalLevel;
	private Double shiftMelterPower;
	private String melterLogRemark;

	private Date fromDate;
	private Date toDate;
	MastersDTO mastersDto=new MastersDTO();
	
	public MastersDTO getMastersDto() {
		return mastersDto;
	}

	public void setMastersDto(MastersDTO mastersDto) {
		this.mastersDto = mastersDto;
	}

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public String getRunNo() {
		return runNo;
	}

	public void setRunNo(String runNo) {
		this.runNo = runNo;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Time getLogTime() {
		return logTime;
	}

	public void setLogTime(Time logTime) {
		this.logTime = logTime;
	}

	public Double getInputCurrAmp() {
		return inputCurrAmp;
	}

	public void setInputCurrAmp(Double inputCurrAmp) {
		this.inputCurrAmp = inputCurrAmp;
	}

	public Double getInputVoltage() {
		return inputVoltage;
	}

	public void setInputVoltage(Double inputVoltage) {
		this.inputVoltage = inputVoltage;
	}

	public Double getInputKw() {
		return inputKw;
	}

	public void setInputKw(Double inputKw) {
		this.inputKw = inputKw;
	}

	public Double getPower1DcVoltage() {
		return power1DcVoltage;
	}

	public void setPower1DcVoltage(Double power1DcVoltage) {
		this.power1DcVoltage = power1DcVoltage;
	}

	public Double getPower1DcAmp() {
		return power1DcAmp;
	}

	public void setPower1DcAmp(Double power1DcAmp) {
		this.power1DcAmp = power1DcAmp;
	}

	public Double getPower2DcVoltage() {
		return power2DcVoltage;
	}

	public void setPower2DcVoltage(Double power2DcVoltage) {
		this.power2DcVoltage = power2DcVoltage;
	}

	public Double getPower2DcAmp() {
		return power2DcAmp;
	}

	public void setPower2DcAmp(Double power2DcAmp) {
		this.power2DcAmp = power2DcAmp;
	}

	public Double getPower3DcVoltage() {
		return power3DcVoltage;
	}

	public void setPower3DcVoltage(Double power3DcVoltage) {
		this.power3DcVoltage = power3DcVoltage;
	}

	public Double getPower3DcAmp() {
		return power3DcAmp;
	}

	public void setPower3DcAmp(Double power3DcAmp) {
		this.power3DcAmp = power3DcAmp;
	}

	public Double getPower1AcVoltage() {
		return power1AcVoltage;
	}

	public void setPower1AcVoltage(Double power1AcVoltage) {
		this.power1AcVoltage = power1AcVoltage;
	}

	public Double getPower1AcAmp() {
		return power1AcAmp;
	}

	public void setPower1AcAmp(Double power1AcAmp) {
		this.power1AcAmp = power1AcAmp;
	}

	public Double getPower2AcVoltage() {
		return power2AcVoltage;
	}

	public void setPower2AcVoltage(Double power2AcVoltage) {
		this.power2AcVoltage = power2AcVoltage;
	}

	public Double getPower2AcAmp() {
		return power2AcAmp;
	}

	public void setPower2AcAmp(Double power2AcAmp) {
		this.power2AcAmp = power2AcAmp;
	}

	public Double getPower3AcVoltage() {
		return power3AcVoltage;
	}

	public void setPower3AcVoltage(Double power3AcVoltage) {
		this.power3AcVoltage = power3AcVoltage;
	}

	public Double getPower3AcAmp() {
		return power3AcAmp;
	}

	public void setPower3AcAmp(Double power3AcAmp) {
		this.power3AcAmp = power3AcAmp;
	}

	public Double getTapElectrodeAcVoltage() {
		return tapElectrodeAcVoltage;
	}

	public void setTapElectrodeAcVoltage(Double tapElectrodeAcVoltage) {
		this.tapElectrodeAcVoltage = tapElectrodeAcVoltage;
	}

	public Double getTapElectrodeAcAmp() {
		return tapElectrodeAcAmp;
	}

	public void setTapElectrodeAcAmp(Double tapElectrodeAcAmp) {
		this.tapElectrodeAcAmp = tapElectrodeAcAmp;
	}

	public Double getElectrodePositionPower1() {
		return electrodePositionPower1;
	}

	public void setElectrodePositionPower1(Double electrodePositionPower1) {
		this.electrodePositionPower1 = electrodePositionPower1;
	}

	public Double getElectrodePositionPower2() {
		return electrodePositionPower2;
	}

	public void setElectrodePositionPower2(Double electrodePositionPower2) {
		this.electrodePositionPower2 = electrodePositionPower2;
	}

	public Double getElectrodePositionPower3() {
		return electrodePositionPower3;
	}

	public void setElectrodePositionPower3(Double electrodePositionPower3) {
		this.electrodePositionPower3 = electrodePositionPower3;
	}

	public Double getH2CylPressure() {
		return h2CylPressure;
	}

	public void setH2CylPressure(Double h2CylPressure) {
		this.h2CylPressure = h2CylPressure;
	}

	public Double getTotalPower() {
		return totalPower;
	}

	public void setTotalPower(Double totalPower) {
		this.totalPower = totalPower;
	}

	public Double getPoolLevel() {
		return poolLevel;
	}

	public void setPoolLevel(Double poolLevel) {
		this.poolLevel = poolLevel;
	}

	public Double getFinalLevel() {
		return finalLevel;
	}

	public void setFinalLevel(Double finalLevel) {
		this.finalLevel = finalLevel;
	}

	public Double getShiftMelterPower() {
		return shiftMelterPower;
	}

	public void setShiftMelterPower(Double shiftMelterPower) {
		this.shiftMelterPower = shiftMelterPower;
	}

	public String getMelterLogRemark() {
		return melterLogRemark;
	}

	public void setMelterLogRemark(String melterLogRemark) {
		this.melterLogRemark = melterLogRemark;
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

public Double getElectrodePosition2Power3() {
		return electrodePosition2Power3;
	}

	public void setElectrodePosition2Power3(Double electrodePosition2Power3) {
		this.electrodePosition2Power3 = electrodePosition2Power3;
	}

	public Double getElectrodePosition2Power2() {
		return electrodePosition2Power2;
	}

	public void setElectrodePosition2Power2(Double electrodePosition2Power2) {
		this.electrodePosition2Power2 = electrodePosition2Power2;
	}

	public Double getElectrodePosition2Power1() {
		return electrodePosition2Power1;
	}

	public void setElectrodePosition2Power1(Double electrodePosition2Power1) {
		this.electrodePosition2Power1 = electrodePosition2Power1;
	}

@Override
   public String toString()
   {
	 return "MelterLogBookDto_Values[sno="+sno+"logDate="+logDate+"runNo="+runNo+
	"operatorName="+operatorName+"logTime="+logTime+"inputCurrAmp="+inputCurrAmp+"inputVoltage="+inputVoltage+
	"inputKw="+inputKw+"power1DcVoltage="+power1DcVoltage+"power1DcAmp="+power1DcAmp+"power2DcVoltage="+power2DcVoltage+
	"power2DcAmp="+power2DcAmp+"power3DcVoltage="+power3DcVoltage+"power3DcAmp="+power3DcAmp+"power1AcVoltage="+power1AcVoltage+
	"power1AcAmp="+power1AcAmp+"power2AcVoltage="+power2AcVoltage+"power3AcVoltage="+power3AcVoltage+"power3AcAmp="+power3AcAmp+
	"tapElectrodeAcVoltage="+tapElectrodeAcVoltage+"tapElectrodeAcAmp="+tapElectrodeAcAmp+"electrodePositionPower1="+electrodePositionPower1+"electrodePositionPower2="+electrodePositionPower2+
	"electrodePositionPower3="+electrodePositionPower3+"h2CylPressure="+h2CylPressure+"totalPower="+totalPower+"poolLevel="+poolLevel+
	"finalLevel="+finalLevel+"shiftMelterPower="+shiftMelterPower+"melterLogRemark="+melterLogRemark+"mastersDto="+mastersDto+"]";  
   }
   }

