package com.advanz.erp.masters.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.advanz.erp.common.model.BaseDTO;
  
@SuppressWarnings("serial")
public class ItemDTO extends BaseDTO {
	private Integer itemId;

	private ItemCategoryDTO itemCategoryDTO;

	private String itemCode;

	private String invoiceName;

	private String itemGroupName;

	private Integer next;
	private Integer previous;
	
	
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

	public String getItemGroupName() {
		return itemGroupName;
	}

	public void setItemGroupName(String itemGroupName) {
		this.itemGroupName = itemGroupName;
	}

	private String itemName;

	private MastersDTO masterGrade;

	private MastersDTO masterPack;

	private MastersDTO masterPackDTO;

	public MastersDTO getMasterPackDTO() {
		return masterPackDTO;
	}

	public void setMasterPackDTO(MastersDTO masterPackDTO) {
		this.masterPackDTO = masterPackDTO;
	}

	private MastersDTO masterUnit;

	private MastersDTO masterUnitDTO;

	private Double salesRate;

	private Date launchDate;

	private Integer activeStatus;

	private Double tradeRate;

	private Double mrp;

	private Double purchaseRate;

	private Double vatPerc;

	private Date vatValidFrom;

	private String vatValidFromString;

	private Double cstPerc;

	private Date cstValidFrom;

	private String cstValidFromString;

	private Double discountPer;

	private Double excisePerc;

	private Date exciseValidFrom;

	private String exciseValidFromString;

	private String exciseTypeFlag;

	private Double surCharge;

	private Double netRate;

	private Double primaryUnit;

	private Integer masterPrimaryUnit;

	private Double secondaryUnit;

	private Integer masterSecondaryUnit;

	private Double primaryConversion;

	private Integer masterPrimaryConverUnit;

	private Double secondaryConversion;

	private Integer masterSecondaryConverUnit;

	private Double grossWeight;

	private Integer masterGrossWeightUnit;

	private Double netWeight;

	private Integer masterNetWeightUnit;

	private Double productionValue;

	private Double unitPerCase;

	private String rateType;

	private Integer purchaseOrderFlag;

	private String chapterNumber;

	private Integer cenvatAllowFlag;

	private Integer batchFlag;

	private Integer itemClassId;

	private Double itemLength;

	private Double itemWidth;

	private Double itemHeight;

	private Double itemDensity;

	private Integer masterLengthUnit;

	private Integer masterWidthUnit;

	private Integer masterThiknessUnit;

	private Integer masterDensityUnit;

	private Double customDutyPer;

	private Double surchargeOnCustom;

	private Double openingStock;

	private Double closingStock;

	private Double minStock;

	private Double maxStock;

	private Double reorderLevel;

	private Double shelifLifePeriod;

	private String packingDetails;

	private Double suggReorderQty;

	private StoreLocationDTO storeLocationDTO;

	private String storingInstruction;

	private Integer vendorApprovalFlag;

	private Double underDeliveryTolerance;

	private Double overDeliveryTolerance;

	private Integer suggestedVendorId;

	private Integer leadTimeDay;

	private Integer grProcessingDay;

	private Integer poAlertFreqDay;

	private String generalRemark;

	private Integer productManagerId;

	private String lunchDateString;

	// add by me
	private Double stockTotal;
	private String tariffHeadNo;
	
	private Integer itemGroupFlagId;
	
	private Double maximumWeight;
	private Double minimumWeight;
	private Double nominalWeight;
	private Double itemSequenceNo;
	
	private Date modifiedDate;
	
	
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Double getMaximumWeight() {
		return maximumWeight;
	}

	public void setMaximumWeight(Double maximumWeight) {
		this.maximumWeight = maximumWeight;
	}

	public Double getMinimumWeight() {
		return minimumWeight;
	}

	public void setMinimumWeight(Double minimumWeight) {
		this.minimumWeight = minimumWeight;
	}

	public Double getNominalWeight() {
		return nominalWeight;
	}

	public void setNominalWeight(Double nominalWeight) {
		this.nominalWeight = nominalWeight;
	}



	public Integer getItemGroupFlagId() {
		return itemGroupFlagId;
	}

	public void setItemGroupFlagId(Integer itemGroupFlagId) {
		this.itemGroupFlagId = itemGroupFlagId;
	}

	public String getTariffHeadNo() {
		return tariffHeadNo;
	}

	public void setTariffHeadNo(String tariffHeadNo) {
		this.tariffHeadNo = tariffHeadNo;
	}

	public Double getStockTotal() {
		return stockTotal;
	}

	public void setStockTotal(Double stockTotal) {
		this.stockTotal = stockTotal;
	}

	private String masterName;

	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	public String getLunchDateString() {
		if (launchDate != null) {
			SimpleDateFormat obj = new SimpleDateFormat("MM/dd/yyyy");
			return obj.format(launchDate);
		}
		return lunchDateString;
	}

	public void setLunchDateString(String lunchDateString) {
		this.lunchDateString = lunchDateString;
	}

	public Date getLaunchDate() {
		try {
			if (lunchDateString != null && !lunchDateString.trim().equals("")) {
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				launchDate = format.parse(lunchDateString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return launchDate;
	}

	/**
	 * @param launchDate
	 *            the launchDate to set
	 */
	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public ItemCategoryDTO getItemCategoryDTO() {
		return itemCategoryDTO;
	}

	public void setItemCategoryDTO(ItemCategoryDTO itemCategoryDTO) {
		this.itemCategoryDTO = itemCategoryDTO;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getInvoiceName() {
		return invoiceName;
	}

	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getSalesRate() {
		return salesRate;
	}

	public void setSalesRate(Double salesRate) {
		this.salesRate = salesRate;
	}

	public Integer getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Integer activeStatus) {
		
		this.activeStatus = activeStatus;
	}

	public Double getTradeRate() {
		return tradeRate;
	}

	public void setTradeRate(Double tradeRate) {
		this.tradeRate = tradeRate;
	}

	public Double getMrp() {
		return mrp;
	}

	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}

	public Double getPurchaseRate() {
		return purchaseRate;
	}

	public void setPurchaseRate(Double purchaseRate) {
		this.purchaseRate = purchaseRate;
	}

	public Double getVatPerc() {
		return vatPerc;
	}

	public void setVatPerc(Double vatPerc) {
		this.vatPerc = vatPerc;
	}

	public Date getVatValidFrom() {

		try {
			if (vatValidFromString != null
					&& !vatValidFromString.trim().equals("")) {
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				vatValidFrom = format.parse(vatValidFromString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vatValidFrom;
	}

	public void setVatValidFrom(Date vatValidFrom) {
		this.vatValidFrom = vatValidFrom;
	}

	public Double getCstPerc() {
		return cstPerc;
	}

	public void setCstPerc(Double cstPerc) {
		this.cstPerc = cstPerc;
	}

	public Date getCstValidFrom() {
		try {
			if (cstValidFromString != null
					&& !cstValidFromString.trim().equals("")) {
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				cstValidFrom = format.parse(cstValidFromString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cstValidFrom;
	}

	public void setCstValidFrom(Date cstValidFrom) {
		this.cstValidFrom = cstValidFrom;
	}

	public Double getDiscountPer() {
		return discountPer;
	}

	public void setDiscountPer(Double discountPer) {
		this.discountPer = discountPer;
	}

	public Double getExcisePerc() {
		return excisePerc;
	}

	public void setExcisePerc(Double excisePerc) {
		this.excisePerc = excisePerc;
	}

	public Date getExciseValidFrom() {
		try {
			if (exciseValidFromString != null
					&& !exciseValidFromString.trim().equals("")) {
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				exciseValidFrom = format.parse(exciseValidFromString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exciseValidFrom;
	}

	public void setExciseValidFrom(Date exciseValidFrom) {
		this.exciseValidFrom = exciseValidFrom;
	}

	public String getExciseTypeFlag() {
		return exciseTypeFlag;
	}

	public void setExciseTypeFlag(String exciseTypeFlag) {
		this.exciseTypeFlag = exciseTypeFlag;
	}

	public Double getSurCharge() {
		return surCharge;
	}

	public void setSurCharge(Double surCharge) {
		this.surCharge = surCharge;
	}

	public Double getNetRate() {
		return netRate;
	}

	public void setNetRate(Double netRate) {
		this.netRate = netRate;
	}

	public Double getPrimaryUnit() {
		return primaryUnit;
	}

	public void setPrimaryUnit(Double primaryUnit) {
		this.primaryUnit = primaryUnit;
	}

	public Integer getMasterPrimaryUnit() {
		return masterPrimaryUnit;
	}

	public void setMasterPrimaryUnit(Integer masterPrimaryUnit) {
		this.masterPrimaryUnit = masterPrimaryUnit;
	}

	public Double getSecondaryUnit() {
		return secondaryUnit;
	}

	public void setSecondaryUnit(Double secondaryUnit) {
		this.secondaryUnit = secondaryUnit;
	}

	public Integer getMasterSecondaryUnit() {
		return masterSecondaryUnit;
	}

	public void setMasterSecondaryUnit(Integer masterSecondaryUnit) {
		this.masterSecondaryUnit = masterSecondaryUnit;
	}

	public Double getPrimaryConversion() {
		return primaryConversion;
	}

	public void setPrimaryConversion(Double primaryConversion) {
		this.primaryConversion = primaryConversion;
	}

	public Integer getMasterPrimaryConverUnit() {
		return masterPrimaryConverUnit;
	}

	public void setMasterPrimaryConverUnit(Integer masterPrimaryConverUnit) {
		this.masterPrimaryConverUnit = masterPrimaryConverUnit;
	}

	public Double getSecondaryConversion() {
		return secondaryConversion;
	}

	public void setSecondaryConversion(Double secondaryConversion) {
		this.secondaryConversion = secondaryConversion;
	}

	public Integer getMasterSecondaryConverUnit() {
		return masterSecondaryConverUnit;
	}

	public void setMasterSecondaryConverUnit(Integer masterSecondaryConverUnit) {
		this.masterSecondaryConverUnit = masterSecondaryConverUnit;
	}

	public Double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}

	public Integer getMasterGrossWeightUnit() {
		return masterGrossWeightUnit;
	}

	public void setMasterGrossWeightUnit(Integer masterGrossWeightUnit) {
		this.masterGrossWeightUnit = masterGrossWeightUnit;
	}

	public Double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}

	public Integer getMasterNetWeightUnit() {
		return masterNetWeightUnit;
	}

	public void setMasterNetWeightUnit(Integer masterNetWeightUnit) {
		this.masterNetWeightUnit = masterNetWeightUnit;
	}

	public Double getProductionValue() {
		return productionValue;
	}

	public void setProductionValue(Double productionValue) {
		this.productionValue = productionValue;
	}

	public Double getUnitPerCase() {
		return unitPerCase;
	}

	public void setUnitPerCase(Double unitPerCase) {
		this.unitPerCase = unitPerCase;
	}

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public Integer getPurchaseOrderFlag() {
		if(purchaseOrderFlag==null)
			purchaseOrderFlag=0;
		return purchaseOrderFlag;
	}

	public void setPurchaseOrderFlag(Integer purchaseOrderFlag) {
		this.purchaseOrderFlag = purchaseOrderFlag;
	}

	public String getChapterNumber() {
		return chapterNumber;
	}

	public void setChapterNumber(String chapterNumber) {
		this.chapterNumber = chapterNumber;
	}

	public Integer getCenvatAllowFlag() {
		if(cenvatAllowFlag==null)
			cenvatAllowFlag=0;
			return cenvatAllowFlag;
		
	}

	public void setCenvatAllowFlag(Integer cenvatAllowFlag) {
		this.cenvatAllowFlag = cenvatAllowFlag;
	}

	public Integer getBatchFlag() {
		if(batchFlag==null)
			batchFlag=0;
		return batchFlag;
	}

	public void setBatchFlag(Integer batchFlag) {
		this.batchFlag = batchFlag;
	}

	public Integer getItemClassId() {
		return itemClassId;
	}

	public void setItemClassId(Integer itemClassId) {
		this.itemClassId = itemClassId;
	}

	public Double getItemLength() {
		return itemLength;
	}

	public void setItemLength(Double itemLength) {
		this.itemLength = itemLength;
	}

	public Double getItemWidth() {
		return itemWidth;
	}

	public void setItemWidth(Double itemWidth) {
		this.itemWidth = itemWidth;
	}

	public Double getItemHeight() {
		return itemHeight;
	}

	public void setItemHeight(Double itemHeight) {
		this.itemHeight = itemHeight;
	}

	public Double getItemDensity() {
		return itemDensity;
	}

	public void setItemDensity(Double itemDensity) {
		this.itemDensity = itemDensity;
	}

	public MastersDTO getMasterUnitDTO() {
		return masterUnitDTO;
	}

	public void setMasterUnitDTO(MastersDTO masterUnitDTO) {
		this.masterUnitDTO = masterUnitDTO;
	}

	public Integer getMasterLengthUnit() {
		return masterLengthUnit;
	}

	public void setMasterLengthUnit(Integer masterLengthUnit) {
		this.masterLengthUnit = masterLengthUnit;
	}

	public Integer getMasterWidthUnit() {
		return masterWidthUnit;
	}

	public void setMasterWidthUnit(Integer masterWidthUnit) {
		this.masterWidthUnit = masterWidthUnit;
	}

	public Integer getMasterThiknessUnit() {
		return masterThiknessUnit;
	}

	public void setMasterThiknessUnit(Integer masterThiknessUnit) {
		this.masterThiknessUnit = masterThiknessUnit;
	}

	public Integer getMasterDensityUnit() {
		return masterDensityUnit;
	}

	public void setMasterDensityUnit(Integer masterDensityUnit) {
		this.masterDensityUnit = masterDensityUnit;
	}

	public Double getCustomDutyPer() {
		return customDutyPer;
	}

	public void setCustomDutyPer(Double customDutyPer) {
		this.customDutyPer = customDutyPer;
	}

	public Double getSurchargeOnCustom() {
		return surchargeOnCustom;
	}

	public void setSurchargeOnCustom(Double surchargeOnCustom) {
		this.surchargeOnCustom = surchargeOnCustom;
	}

	public Double getOpeningStock() {
		return openingStock;
	}

	public void setOpeningStock(Double openingStock) {
		this.openingStock = openingStock;
	}

	public Double getClosingStock() {
		return closingStock;
	}

	public void setClosingStock(Double closingStock) {
		this.closingStock = closingStock;
	}

	public Double getMinStock() {
		return minStock;
	}

	public void setMinStock(Double minStock) {
		this.minStock = minStock;
	}

	public Double getMaxStock() {
		return maxStock;
	}

	public void setMaxStock(Double maxStock) {
		this.maxStock = maxStock;
	}

	public Double getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(Double reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public Double getShelifLifePeriod() {
		return shelifLifePeriod;
	}

	public void setShelifLifePeriod(Double shelifLifePeriod) {
		this.shelifLifePeriod = shelifLifePeriod;
	}

	public String getPackingDetails() {
		return packingDetails;
	}

	public void setPackingDetails(String packingDetails) {
		this.packingDetails = packingDetails;
	}

	public Double getSuggReorderQty() {
		return suggReorderQty;
	}

	public void setSuggReorderQty(Double suggReorderQty) {
		this.suggReorderQty = suggReorderQty;
	}

	public StoreLocationDTO getStoreLocationDTO() {
		return storeLocationDTO;
	}

	public void setStoreLocationDTO(StoreLocationDTO storeLocationDTO) {
		this.storeLocationDTO = storeLocationDTO;
	}

	public String getStoringInstruction() {
		return storingInstruction;
	}

	public void setStoringInstruction(String storingInstruction) {
		this.storingInstruction = storingInstruction;
	}

	public Integer getVendorApprovalFlag() {
		if(vendorApprovalFlag==null)
			vendorApprovalFlag=0;
		return vendorApprovalFlag;
	}

	public void setVendorApprovalFlag(Integer vendorApprovalFlag) {
		this.vendorApprovalFlag = vendorApprovalFlag;
	}

	public Double getUnderDeliveryTolerance() {
		return underDeliveryTolerance;
	}

	public void setUnderDeliveryTolerance(Double underDeliveryTolerance) {
		this.underDeliveryTolerance = underDeliveryTolerance;
	}

	public Double getOverDeliveryTolerance() {
		return overDeliveryTolerance;
	}

	public void setOverDeliveryTolerance(Double overDeliveryTolerance) {
		this.overDeliveryTolerance = overDeliveryTolerance;
	}

	public Integer getSuggestedVendorId() {
		return suggestedVendorId;
	}

	public void setSuggestedVendorId(Integer suggestedVendorId) {
		this.suggestedVendorId = suggestedVendorId;
	}

	public Integer getLeadTimeDay() {
		return leadTimeDay;
	}

	public void setLeadTimeDay(Integer leadTimeDay) {
		this.leadTimeDay = leadTimeDay;
	}

	public Integer getGrProcessingDay() {
		return grProcessingDay;
	}

	public void setGrProcessingDay(Integer grProcessingDay) {
		this.grProcessingDay = grProcessingDay;
	}

	public Integer getPoAlertFreqDay() {
		return poAlertFreqDay;
	}

	public void setPoAlertFreqDay(Integer poAlertFreqDay) {
		this.poAlertFreqDay = poAlertFreqDay;
	}

	public String getGeneralRemark() {
		return generalRemark;
	}

	public void setGeneralRemark(String generalRemark) {
		this.generalRemark = generalRemark;
	}

	public Integer getProductManagerId() {
		return productManagerId;
	}

	public void setProductManagerId(Integer productManagerId) {
		this.productManagerId = productManagerId;
	}

	public String getVatValidFromString() {
		if (vatValidFrom != null) {
			SimpleDateFormat obj = new SimpleDateFormat("MM/dd/yyyy");
			return obj.format(vatValidFrom);
		}
		return vatValidFromString;
	}

	public void setVatValidFromString(String vatValidFromString) {
		this.vatValidFromString = vatValidFromString;
	}

	public String getCstValidFromString() {
		if (cstValidFrom != null) {
			SimpleDateFormat obj = new SimpleDateFormat("MM/dd/yyyy");
			return obj.format(cstValidFrom);
		}
		return cstValidFromString;
	}

	public void setCstValidFromString(String cstValidFromString) {
		this.cstValidFromString = cstValidFromString;
	}

	public String getExciseValidFromString() {
		if (exciseValidFrom != null) {
			SimpleDateFormat obj = new SimpleDateFormat("MM/dd/yyyy");
			return obj.format(exciseValidFrom);
		}
		return exciseValidFromString;
	}

	public void setExciseValidFromString(String exciseValidFromString) {
		this.exciseValidFromString = exciseValidFromString;
	}

	public MastersDTO getMasterGrade() {
		return masterGrade;
	}

	public void setMasterGrade(MastersDTO masterGrade) {
		this.masterGrade = masterGrade;
	}

	public MastersDTO getMasterPack() {
		return masterPack;
	}

	public void setMasterPack(MastersDTO masterPack) {
		this.masterPack = masterPack;
	}

	public MastersDTO getMasterUnit() {
		return masterUnit;
	}

	public void setMasterUnit(MastersDTO masterUnit) {
		this.masterUnit = masterUnit;
	}

	public Double getItemSequenceNo() {
		return itemSequenceNo;
	}

	public void setItemSequenceNo(Double itemSequenceNo) {
		this.itemSequenceNo = itemSequenceNo;
	}
	
	

}
