package com.advanz.erp.masters.entity.jpa;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name = "t_melter_log_book")
public class MelterLogBookEntity extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-incr")
	@GenericGenerator(name = "system-incr", strategy = "increment")
	@Column(name = "sno")
	private Integer sno;

	@Column(name = "log_date")
	private Date logDate;

	@Column(name = "run_no")
	private String runNo;

	

	@Column(name = "operator_name")
	private String operatorName;

	@Column(name = "log_time")
	private Time logTime;

	@Column(name = "input_curr_amp")
	private Double inputCurrAmp;

	@Column(name = "input_voltage")
	private Double inputVoltage;

	@Column(name = "input_kw")
	private Double inputKw;

	@Column(name = "power_1_dc_voltage")
	private Double power1DcVoltage;

	@Column(name = "power_1_dc_amp")
	private Double power1DcAmp;

	@Column(name = "power_2_dc_voltage")
	private Double power2DcVoltage;

	@Column(name = "power_2_dc_amp")
	private Double power2DcAmp;

	@Column(name = "power_3_dc_voltage")
	private Double power3DcVoltage;

	@Column(name = "power_3_dc_amp")
	private Double power3DcAmp;

	@Column(name = "power_1_ac_voltage")
	private Double power1AcVoltage;

	@Column(name = "power_1_ac_amp")
	private Double power1AcAmp;

	@Column(name = "power_2_ac_voltage")
	private Double power2AcVoltage;

	@Column(name = "power_2_ac_amp")
	private Double power2AcAmp;

	@Column(name = "power_3_ac_voltage")
	private Double power3AcVoltage;

	@Column(name = "power_3_ac_amp")
	private Double power3AcAmp;

	@Column(name = "tap_electrode_ac_voltage")
	private Double tapElectrodeAcVoltage;

	@Column(name = "tap_electrode_ac_amp")
	private Double tapElectrodeAcAmp;

	@Column(name = "electrode_position_power1")
	private Double electrodePositionPower1;

	@Column(name = "electrode_position_power2")
	private Double electrodePositionPower2;

	@Column(name = "electrode_position_power3")
	private Double electrodePositionPower3;

	@Column(name = "h2_cyl_pressure")
	private Double h2CylPressure;

	@Column(name = "total_power")
	private Double totalPower;

	@Column(name = "pool_level")
	private Double poolLevel;

	@Column(name = "final_level")
	private Double finalLevel;

	@Column(name = "shift_melter_power")
	private Double shiftMelterPower;

	@Column(name = "melter_log_remark")
	private String melterLogRemark;
	
	@Column(name = "electrode_position2_power1")
	private Double electrodePosition2Power1;

	@Column(name = "electrode_position2_power2")
	private Double electrodePosition2Power2;

	@Column(name = "electrode_position2_power3")
	private Double electrodePosition2Power3;

	public Double getElectrodePosition2Power1() {
		return electrodePosition2Power1;
	}

	public void setElectrodePosition2Power1(Double electrodePosition2Power1) {
		this.electrodePosition2Power1 = electrodePosition2Power1;
	}

	public Double getElectrodePosition2Power2() {
		return electrodePosition2Power2;
	}

	public void setElectrodePosition2Power2(Double electrodePosition2Power2) {
		this.electrodePosition2Power2 = electrodePosition2Power2;
	}

	public Double getElectrodePosition2Power3() {
		return electrodePosition2Power3;
	}

	public void setElectrodePosition2Power3(Double electrodePosition2Power3) {
		this.electrodePosition2Power3 = electrodePosition2Power3;
	}

	@ManyToOne
	@JoinColumn(name="shift_id")
	private MastersEntity mastersEntity;
	
	public MastersEntity getMastersEntity() {
		return mastersEntity;
	}

	public void setMastersEntity(MastersEntity mastersEntity) {
		this.mastersEntity = mastersEntity;
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
		"finalLevel="+finalLevel+"shiftMelterPower="+shiftMelterPower+"melterLogRemark="+melterLogRemark+"mastersEntity="+mastersEntity+"]"; 
	   }

}
