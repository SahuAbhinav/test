package com.advanz.erp.masters.model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import com.advanz.erp.common.model.BaseDTO;



@SuppressWarnings("serial")
public class ShiftReportMasterDTO  extends BaseDTO {
	
	private Integer shiftReportId;
	private String update;
	private Date shifReportDate;
	private String runNo;
	//private Integer shiftId;

	private String shiftEngineerName;
	private Time shiftOperationTime;
	private Time meltingTime;
	private Time poutingTime;
	private Time spiningTime;
	private Double initialPoolLevel;
	private Double finalPoolLevel;
	private Double h2cyMain;
	private Double h2cySpare;
	private Double h2cyUsed;
	private Double dieselUsed;
	private Double lpgUsed;
	private Double melterPower;
	private Double md;
	private Double kwh;
	private Double kvah;
	private Double powerFector;
	private Integer noOfBlankets;
	private Double blanketsWeigth;
	private Double shortLengthBlanketsWeight;
	private Double edgeTrimWeight;
	private Double bulkFibreWeight;
	private Double totalWeight;
	private Double htWeight;
	private Double rtWeight;
	private Double shot;
	private String shiftHistory;
	private ItemGroupDTO itemGroupDTO;
	private MastersDTO mastersDTO;
	
	private Double blanketPower;
	private Double dgPowerOption;
	public MastersDTO getMastersDTO() {
		return mastersDTO;
	}
	public void setMastersDTO(MastersDTO mastersDTO) {
		this.mastersDTO = mastersDTO;
	}
	private List<ShiftConsumedDetailDTO> shiftConsumedDetailDTOList;
	private List<ShiftEngInterruptionDetailDTO> shiftEngInterruptionDetailDTOList;
	private List<ShiftSpinInterruptionDetailDTO> shiftSpinInterruptionDetailDTOList;
	
	private ShiftConsumedDetailDTO shiftConsumedDetailDTO;
	private ShiftEngInterruptionDetailDTO shiftEngInterruptionDetailDTO;
	private ShiftSpinInterruptionDetailDTO shiftSpinInterruptionDetailDTO;
	private String blankets;
	private String remark;
	private String shiftRemark;
	private String finishGoodNumber;
	
	private Double spiningSet1;
	private Double spiningSet2;
	
	
	private Double ltPanel;
	private Double rtzWeight;
	private Double totalRmConsuptionWeight;
	private Double spiningSet1Initial;
	private Double spiningSet1Final;
	private Double spiningSet2Initial;
	private Double spiningSet2Final;
	
	
	
	public ShiftSpinInterruptionDetailDTO getShiftSpinInterruptionDetailDTO() {
		return shiftSpinInterruptionDetailDTO;
	}
	public void setShiftSpinInterruptionDetailDTO(
			ShiftSpinInterruptionDetailDTO shiftSpinInterruptionDetailDTO) {
		this.shiftSpinInterruptionDetailDTO = shiftSpinInterruptionDetailDTO;
	}
	public ShiftEngInterruptionDetailDTO getShiftEngInterruptionDetailDTO() {
		return shiftEngInterruptionDetailDTO;
	}
	public void setShiftEngInterruptionDetailDTO(
			ShiftEngInterruptionDetailDTO shiftEngInterruptionDetailDTO) {
		this.shiftEngInterruptionDetailDTO = shiftEngInterruptionDetailDTO;
	}
	public ShiftConsumedDetailDTO getShiftConsumedDetailDTO() {
		return shiftConsumedDetailDTO;
	}
	public void setShiftConsumedDetailDTO(
			ShiftConsumedDetailDTO shiftConsumedDetailDTO) {
		this.shiftConsumedDetailDTO = shiftConsumedDetailDTO;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBlankets() {
		return blankets;
	}
	public void setBlankets(String blankets) {
		this.blankets = blankets;
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
	public ItemGroupDTO getItemGroupDTO() {
		return itemGroupDTO;
	}
	public void setItemGroupDTO(ItemGroupDTO itemGroupDTO) {
		this.itemGroupDTO = itemGroupDTO;
	}
	
	public List<ShiftConsumedDetailDTO> getShiftConsumedDetailDTOList() {
		return shiftConsumedDetailDTOList;
	}
	public void setShiftConsumedDetailDTOList(
			List<ShiftConsumedDetailDTO> shiftConsumedDetailDTOList) {
		this.shiftConsumedDetailDTOList = shiftConsumedDetailDTOList;
	}
	public List<ShiftEngInterruptionDetailDTO> getShiftEngInterruptionDetailDTOList() {
		return shiftEngInterruptionDetailDTOList;
	}
	public void setShiftEngInterruptionDetailDTOList(
			List<ShiftEngInterruptionDetailDTO> shiftEngInterruptionDetailDTOList) {
		this.shiftEngInterruptionDetailDTOList = shiftEngInterruptionDetailDTOList;
	}
	
	public List<ShiftSpinInterruptionDetailDTO> getShiftSpinInterruptionDetailDTOList() {
		return shiftSpinInterruptionDetailDTOList;
	}
	public void setShiftSpinInterruptionDetailDTOList(
			List<ShiftSpinInterruptionDetailDTO> shiftSpinInterruptionDetailDTOList) {
		this.shiftSpinInterruptionDetailDTOList = shiftSpinInterruptionDetailDTOList;
	}
	
	
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	
	public String getShiftHistory() {
		return shiftHistory;
	}
	public void setShiftHistory(String shiftHistory) {
		this.shiftHistory = shiftHistory;
	}
	
	public String getFinishGoodNumber() {
		return finishGoodNumber;
	}
	public void setFinishGoodNumber(String finishGoodNumber) {
		this.finishGoodNumber = finishGoodNumber;
	}
	
	
	public String getShiftRemark() {
		return shiftRemark;
	}
	public void setShiftRemark(String shiftRemark) {
		this.shiftRemark = shiftRemark;
	}
	
	

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
	
	
	public Double getShot() {
		return shot;
	}
	public void setShot(Double shot) {
		this.shot = shot;
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
	@Override
	public String toString() {
		return "ShiftReportMasterDTO [shiftReportId=" + shiftReportId
				+ ", shifReportDate=" + shifReportDate + ", runNo=" + runNo
				 + ", shiftEngineerName="
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
				+ ", itemGroupDTO=" + itemGroupDTO + "]";
	}
	

}
