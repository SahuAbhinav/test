package com.advanz.erp.masters.entity.jpa;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name="t_shift_report_mast")
public class ShiftReportMasterEntity extends BaseEntity {
	
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="shift_report_id")
		private Integer shiftReportId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="shift_report_date")
	private Date shifReportDate;
	
	@Column(name="run_no")
	private String runNo;
	
/*	@Column(name="shift_id")
	private Integer shiftId;*/
	
	@ManyToOne
	@JoinColumn(name="shift_id")
	private MastersEntity mastersEntity;
	
	
	
	@Column(name="shift_engineer_name")
	private String shiftEngineerName;
	
	

	@Column(name="shift_operation_time")
	private Time shiftOperationTime;
	
	@Column(name="melting_time")
	private Time meltingTime;
	
	@Column(name="pouting_time")
	private Time poutingTime;
	
	@Column(name="spining_time")
	private Time spiningTime;
	
	@Column(name="initial_pool_level")
	private Double initialPoolLevel;
	
	@Column(name="final_pool_level")
	private Double finalPoolLevel;
	
	@Column(name="h2cy_main")
	private Double h2cyMain;
	
	@Column(name="h2cy_spare")
	private Double h2cySpare;
	
	@Column(name="h2cy_used")
	private Double h2cyUsed;
	
	@Column(name="diesel_used")
	private Double dieselUsed;
	
	@Column(name="lpg_used")
	private Double lpgUsed;
	
	@Column(name="melter_power")
	private Double melterPower;
	
	@Column(name="md")
	private Double md;
	
	@Column(name="kwh")
	private Double kwh;
	
	@Column(name="kvah")
	private Double kvah;
	
	@Column(name="power_fector")
	private Double powerFector;
	
	@Column(name="no_of_blankets")
	private Integer noOfBlankets;
	

	@Column(name="blankets_weigth")
	private Double blanketsWeigth;
	
	@Column(name="short_length_blankets_weight")
	private Double shortLengthBlanketsWeight;
	
	@Column(name="edge_trim_weight")
	private Double edgeTrimWeight;
	
	@Column(name="bulk_fibre_weight")
	private Double bulkFibreWeight;
	
	@Column(name="total_weight")
	private Double totalWeight;
	
	@Column(name="ht_weight")
	private Double htWeight;
	
	@Column(name="rt_weight")
	private Double rtWeight;
	
	@Column(name="shift_history")
	private String shiftHistory;
	
	
	@Column(name="shift_remark")
	private String shiftRemark;

	public String getShiftRemark() {
		return shiftRemark;
	}

	public void setShiftRemark(String shiftRemark) {
		this.shiftRemark = shiftRemark;
	}

	@Column(name="spining_set1")
	private Double spiningSet1;

	@Column(name="spining_set2")
	private Double spiningSet2;

	
	public Double getSpiningSet1() {
		return spiningSet1;
	}

	public void setSpiningSet1(Double spiningSet1) {
		this.spiningSet1 = spiningSet1;
	}

	public Double getSpiningSet2() {
		return spiningSet2;
	}

	public void setSpiningSet2(Double spiningSet2) {
		this.spiningSet2 = spiningSet2;
	}

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="shift_report_id")
	private List<ShiftSpinInterruptionDetailEntity>shiftSpinInterruptionDetailEntityList;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)	
	@JoinColumn(name="shift_report_id")
	private List<ShiftEngInterruptionDetailEntity>shiftEngInterruptionDetailEntityList;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="shift_report_id")
	private List<ShiftConsumedDetailEntity>shiftConsumedDetailEntityList;


	
	
	@Column(name="finish_good_number")
	private String finishGoodNumber;
	
	
	//
	@Column(name="lt_panel")
	private Double ltPanel;

	@Column(name="rtz_weight")
	private Double rtzWeight;
	
	@Column(name="shot")
	private Double shot;
	
	

	public Double getShot() {
		return shot;
	}

	public void setShot(Double shot) {
		this.shot = shot;
	}

	@Column(name="total_rm_consuption_weight")
	private Double totalRmConsuptionWeight;
	
	@Column(name="spining_set1_initial")
	private Double spiningSet1Initial;
	
	@Column(name="spining_set1_final")
	private Double spiningSet1Final;
	
	@Column(name="spining_set2_initial")
	private Double spiningSet2Initial;
	
	@Column(name="spining_set2_final")
	private Double spiningSet2Final;
	
	@Column(name="blanket_power")
	private Double blanketPower;
	
	@Column(name="dg_power_option")
	private Double dgPowerOption;
	
	public String getFinishGoodNumber() {
		return finishGoodNumber;
	}

	public void setFinishGoodNumber(String finishGoodNumber) {
		this.finishGoodNumber = finishGoodNumber;
	}

	public Integer getShiftReportId() {
		return shiftReportId;
	}

	public void setShiftReportId(Integer shiftReportId) {
		this.shiftReportId = shiftReportId;
	}

	public Date getShifReportDate() {
		return shifReportDate;
	}

	public void setShifReportDate(Date shifReportDate) {
		this.shifReportDate = shifReportDate;
	}

	public String getRunNo() {
		return runNo;
	}

	public void setRunNo(String runNo) {
		this.runNo = runNo;
	}

	/*public Integer getShiftId() {
		return shiftId;
	}

	public void setShiftId(Integer shiftId) {
		this.shiftId = shiftId;
	}*/

	public String getShiftEngineerName() {
		return shiftEngineerName;
	}

	public void setShiftEngineerName(String shiftEngineerName) {
		this.shiftEngineerName = shiftEngineerName;
	}

	public Time getShiftOperationTime() {
		return shiftOperationTime;
	}

	public void setShiftOperationTime(Time shiftOperationTime) {
		this.shiftOperationTime = shiftOperationTime;
	}

	public Time getMeltingTime() {
		return meltingTime;
	}

	public void setMeltingTime(Time meltingTime) {
		this.meltingTime = meltingTime;
	}

	public Time getPoutingTime() {
		return poutingTime;
	}

	public void setPoutingTime(Time poutingTime) {
		this.poutingTime = poutingTime;
	}

	public Time getSpiningTime() {
		return spiningTime;
	}

	public void setSpiningTime(Time spiningTime) {
		this.spiningTime = spiningTime;
	}

	public Double getInitialPoolLevel() {
		return initialPoolLevel;
	}

	public void setInitialPoolLevel(Double initialPoolLevel) {
		this.initialPoolLevel = initialPoolLevel;
	}

	public Double getFinalPoolLevel() {
		return finalPoolLevel;
	}

	public void setFinalPoolLevel(Double finalPoolLevel) {
		this.finalPoolLevel = finalPoolLevel;
	}

	public Double getH2cyMain() {
		return h2cyMain;
	}

	public void setH2cyMain(Double h2cyMain) {
		this.h2cyMain = h2cyMain;
	}

	public Double getH2cySpare() {
		return h2cySpare;
	}

	public void setH2cySpare(Double h2cySpare) {
		this.h2cySpare = h2cySpare;
	}

	public Double getH2cyUsed() {
		return h2cyUsed;
	}

	public void setH2cyUsed(Double h2cyUsed) {
		this.h2cyUsed = h2cyUsed;
	}

	public Double getDieselUsed() {
		return dieselUsed;
	}

	public void setDieselUsed(Double dieselUsed) {
		this.dieselUsed = dieselUsed;
	}

	public Double getLpgUsed() {
		return lpgUsed;
	}

	public void setLpgUsed(Double lpgUsed) {
		this.lpgUsed = lpgUsed;
	}

	public Double getMelterPower() {
		return melterPower;
	}

	public void setMelterPower(Double melterPower) {
		this.melterPower = melterPower;
	}

	public Double getMd() {
		return md;
	}

	public void setMd(Double md) {
		this.md = md;
	}

	public Double getKwh() {
		return kwh;
	}

	public void setKwh(Double kwh) {
		this.kwh = kwh;
	}

	public Double getKvah() {
		return kvah;
	}

	public void setKvah(Double kvah) {
		this.kvah = kvah;
	}

	public Double getPowerFector() {
		return powerFector;
	}

	public void setPowerFector(Double powerFector) {
		this.powerFector = powerFector;
	}

	public Integer getNoOfBlankets() {
		return noOfBlankets;
	}

	public void setNoOfBlankets(Integer noOfBlankets) {
		this.noOfBlankets = noOfBlankets;
	}

	public Double getBlanketsWeigth() {
		return blanketsWeigth;
	}

	public void setBlanketsWeigth(Double blanketsWeigth) {
		this.blanketsWeigth = blanketsWeigth;
	}

	public Double getShortLengthBlanketsWeight() {
		return shortLengthBlanketsWeight;
	}

	public void setShortLengthBlanketsWeight(Double shortLengthBlanketsWeight) {
		this.shortLengthBlanketsWeight = shortLengthBlanketsWeight;
	}

	public Double getEdgeTrimWeight() {
		return edgeTrimWeight;
	}

	public void setEdgeTrimWeight(Double edgeTrimWeight) {
		this.edgeTrimWeight = edgeTrimWeight;
	}

	public Double getBulkFibreWeight() {
		return bulkFibreWeight;
	}

	public void setBulkFibreWeight(Double bulkFibreWeight) {
		this.bulkFibreWeight = bulkFibreWeight;
	}

	public Double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public Double getHtWeight() {
		return htWeight;
	}

	public void setHtWeight(Double htWeight) {
		this.htWeight = htWeight;
	}

	public Double getRtWeight() {
		return rtWeight;
	}

	public void setRtWeight(Double rtWeight) {
		this.rtWeight = rtWeight;
	}

	public List<ShiftSpinInterruptionDetailEntity> getShiftSpinInterruptionDetailEntityList() {
		return shiftSpinInterruptionDetailEntityList;
	}

	public void setShiftSpinInterruptionDetailEntityList(
			List<ShiftSpinInterruptionDetailEntity> shiftSpinInterruptionDetailEntityList) {
		this.shiftSpinInterruptionDetailEntityList = shiftSpinInterruptionDetailEntityList;
	}

	public List<ShiftEngInterruptionDetailEntity> getShiftEngInterruptionDetailEntityList() {
		return shiftEngInterruptionDetailEntityList;
	}

	public void setShiftEngInterruptionDetailEntityList(
			List<ShiftEngInterruptionDetailEntity> shiftEngInterruptionDetailEntityList) {
		this.shiftEngInterruptionDetailEntityList = shiftEngInterruptionDetailEntityList;
	}

	public List<ShiftConsumedDetailEntity> getShiftConsumedDetailEntityList() {
		return shiftConsumedDetailEntityList;
	}

	public void setShiftConsumedDetailEntityList(
			List<ShiftConsumedDetailEntity> shiftConsumedDetailEntityList) {
		this.shiftConsumedDetailEntityList = shiftConsumedDetailEntityList;
	}

	public String getShiftHistory() {
		return shiftHistory;
	}

	public void setShiftHistory(String shiftHistory) {
		this.shiftHistory = shiftHistory;
	}

	@Override
	public String toString() {
		return "ShiftReportMasterEntity [shiftReportId=" + shiftReportId
				+ ", shifReportDate=" + shifReportDate + ", runNo=" + runNo
				+ " shiftEngineerName="
				+ shiftEngineerName + ", shiftOperationTime="
				+ shiftOperationTime + ", meltingTime=" + meltingTime
				+ ", poutingTime=" + poutingTime + ", spiningTime="
				+ spiningTime + ", initialPoolLevel=" + initialPoolLevel
				+ ", finalPoolLevel=" + finalPoolLevel + ", h2cyMain="
				+ h2cyMain + ", h2cySpare=" + h2cySpare + ", h2cyUsed="
				+ h2cyUsed + ", dieselUsed=" + dieselUsed + ", lpgUsed="
				+ lpgUsed + ", melterPower=" + melterPower + ", md=" + md
				+ ", kwh=" + kwh + ", kvah=" + kvah + ", powerFector="
				+ powerFector + ", noOfBlankets=" + noOfBlankets
				+ ", blanketsWeigth=" + blanketsWeigth
				+ ", shortLengthBlanketsWeight=" + shortLengthBlanketsWeight
				+ ", edgeTrimWeight=" + edgeTrimWeight + ", bulkFibreWeight="
				+ bulkFibreWeight + ", totalWeight=" + totalWeight
				+ ", htWeight=" + htWeight + ", rtWeight=" + rtWeight
				+ ", shiftSpinInterruptionDetailEntityList="
				+ shiftSpinInterruptionDetailEntityList
				+ ", shiftEngInterruptionDetailEntityList="
				+ shiftEngInterruptionDetailEntityList
				+ ", shiftConsumedDetailEntityList="
				+ shiftConsumedDetailEntityList + "]";
	}

	public MastersEntity getMastersEntity() {
		return mastersEntity;
	}

	public void setMastersEntity(MastersEntity mastersEntity) {
		this.mastersEntity = mastersEntity;
	}

	public Double getLtPanel() {
		return ltPanel;
	}

	public void setLtPanel(Double ltPanel) {
		this.ltPanel = ltPanel;
	}

	public Double getRtzWeight() {
		return rtzWeight;
	}

	public void setRtzWeight(Double rtzWeight) {
		this.rtzWeight = rtzWeight;
	}

	public Double getTotalRmConsuptionWeight() {
		return totalRmConsuptionWeight;
	}

	public void setTotalRmConsuptionWeight(Double totalRmConsuptionWeight) {
		this.totalRmConsuptionWeight = totalRmConsuptionWeight;
	}

	public Double getSpiningSet1Initial() {
		return spiningSet1Initial;
	}

	public void setSpiningSet1Initial(Double spiningSet1Initial) {
		this.spiningSet1Initial = spiningSet1Initial;
	}

	public Double getSpiningSet1Final() {
		return spiningSet1Final;
	}

	public void setSpiningSet1Final(Double spiningSet1Final) {
		this.spiningSet1Final = spiningSet1Final;
	}

	public Double getSpiningSet2Initial() {
		return spiningSet2Initial;
	}

	public void setSpiningSet2Initial(Double spiningSet2Initial) {
		this.spiningSet2Initial = spiningSet2Initial;
	}

	public Double getSpiningSet2Final() {
		return spiningSet2Final;
	}

	public void setSpiningSet2Final(Double spiningSet2Final) {
		this.spiningSet2Final = spiningSet2Final;
	}

	public Double getBlanketPower() {
		return blanketPower;
	}

	public void setBlanketPower(Double blanketPower) {
		this.blanketPower = blanketPower;
	}

	public Double getDgPowerOption() {
		return dgPowerOption;
	}

	public void setDgPowerOption(Double dgPowerOption) {
		this.dgPowerOption = dgPowerOption;
	}
	

}
