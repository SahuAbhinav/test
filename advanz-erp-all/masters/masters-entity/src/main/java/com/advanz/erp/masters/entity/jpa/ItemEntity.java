package com.advanz.erp.masters.entity.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name = "M_ITEM")
public class ItemEntity extends BaseEntity {
	@Id
	@GeneratedValue(generator = "system-incr")
	@GenericGenerator(name = "system-incr", strategy = "increment")
	@Column(name = "item_id")
	private Integer itemId;

	// @Column(name="item_category_id")
	// private Integer itemCategoryId;
	//
	@ManyToOne
	@JoinColumn(name = "item_category_id")
	private ItemCategoryEntity itemCategoryEntity;

	@Column(name = "itemcode")
	private String itemCode;

	@Column(name = "invoice_name")
	private String invoiceName;

	@Column(name = "item_name")
	private String itemName;

	// @ManyToOne
	// @JoinColumns( {
	// @JoinColumn(name = "item_length_measu_unit_id", referencedColumnName =
	// "id" , insertable = false, updatable = false),
	// @JoinColumn(name = "item_id", referencedColumnName = "m_form_id" ,
	// insertable = false, updatable = false)})
	// @JoinColumnsOrFormulas({
	// @JoinColumnOrFormula(formula = @JoinFormula(value = "16",
	// referencedColumnName = "m_form_id")),
	// @JoinColumnOrFormula(column = @JoinColumn(name = "item_grade_id",
	// referencedColumnName = "id", insertable = false, updatable = false)) })

	@ManyToOne
	@JoinColumn(name = "item_grade_id")
	private MastersEntity masterGradeEntity;

	@ManyToOne
	@JoinColumn(name = "pack_type_id")
	private MastersEntity masterPackEntity;

	@ManyToOne
	@JoinColumn(name = "measurement_Unit_id")
	private MastersEntity masterUnitEntity;

	@Column(name = "sales_rate")
	private Double salesRate;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "launch_date")
	private Date launchDate;

	@Column(name = "active_status")
	private Integer activeStatus;

	@Column(name = "trade_rate")
	private Double tradeRate;

	@Column(name = "mrp")
	private Double mrp;

	@Column(name = "purchase_rate")
	private Double purchaseRate;

	@Column(name = "vat_perc")
	private Double vatPerc;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "vat_valid_from")
	private Date vatValidFrom;

	@Column(name = "cst_perc")
	private Double cstPerc;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "cst_valid_from")
	private Date cstValidFrom;

	@Column(name = "discount_per")
	private Double discountPer;

	@Column(name = "excise_perc")
	private Double excisePerc;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "excise_valid_from")
	private Date exciseValidFrom;

	@Column(name = "excise_type_flag")
	private String exciseTypeFlag;

	@Column(name = "surcharge")
	private Double surCharge;

	@Column(name = "net_rate")
	private Double netRate;

	@Column(name = "primary_unit")
	private Double primaryUnit;

	@Column(name = "primary_measu_unit_id")
	private Integer masterPrimaryUnit;

	@Column(name = "secondary_unit")
	private Double secondaryUnit;

	@Column(name = "secondary_measu_unit_id")
	private Integer masterSecondaryUnit;

	@Column(name = "primary_conversion")
	private Double primaryConversion;

	@Column(name = "primary_conver_measu_unit_id")
	private Integer masterPrimaryConverUnit;

	@Column(name = "secondary_conversion")
	private Double secondaryConversion;

	@Column(name = "secondary_conver_measu_unit_id")
	private Integer masterSecondaryConverUnit;

	@Column(name = "gross_weight")
	private Double grossWeight;

	@Column(name = "gross_weight_measu_unit_id")
	private Integer masterGrossWeightUnit;

	@Column(name = "net_weight")
	private Double netWeight;

	@Column(name = "net_weight_measu_unit_id")
	private Integer masterNetWeightUnit;

	@Column(name = "production_value")
	private Double productionValue;

	@Column(name = "unit_per_case")
	private Double unitPerCase;

	@Column(name = "rate_type")
	private String rateType;

	@Column(name = "purchase_order_flag")
	private Integer purchaseOrderFlag;

	@Column(name = "chapter_number")
	private String chapterNumber;

	@Column(name = "cenvat_allow_flag")
	private Integer cenvatAllowFlag;

	@Column(name = "batch_flag")
	private Integer batchFlag;

	@Column(name = "item_class_id")
	private Integer itemClassId;

	@Column(name = "item_length")
	private Double itemLength;

	@Column(name = "item_width")
	private Double itemWidth;

	@Column(name = "item_thikness")
	private Double itemHeight;

	@Column(name = "item_density")
	private Double itemDensity;

	@Column(name = "item_length_measu_unit_id")
	private Integer masterLengthUnit;

	@Column(name = "item_width_measu_unit_id")
	private Integer masterWidthUnit;

	@Column(name = "item_thikness_measu_unit_id")
	private Integer masterThiknessUnit;

	@Column(name = "item_density_measu_unit_id")
	private Integer masterDensityUnit;

	@Column(name = "custom_duty_per")
	private Double customDutyPer;

	@Column(name = "surcharge_on_custom")
	private Double surchargeOnCustom;

	@Column(name = "opening_stock")
	private Double openingStock;

	@Column(name = "closing_stock")
	private Double closingStock;

	@Column(name = "min_stock")
	private Double minStock;

	@Column(name = "max_stock")
	private Double maxStock;

	@Column(name = "reorder_level")
	private Double reorderLevel;

	@Column(name = "shelif_life_period")
	private Double shelifLifePeriod;

	@Column(name = "packing_details")
	private String packingDetails;

	@Column(name = "sugg_reorder_qty")
	private Double suggReorderQty;

	@ManyToOne
	@JoinColumn(name = "store_location_id")
	private StoreLocationEntity storeLocationEntity;

	@Column(name = "storing_instrucation")
	private String storingInstruction;

	@Column(name = "vendor_approval_flag")
	private Integer vendorApprovalFlag;

	@Column(name = "under_delivery_tolerance")
	private Double underDeliveryTolerance;

	@Column(name = "over_delivery_tolerance")
	private Double overDeliveryTolerance;

	@Column(name = "suggested_vendor_id")
	private Integer suggestedVendorId;

	@Column(name = "lead_time_day")
	private Integer leadTimeDay;

	@Column(name = "gr_processing_day")
	private Integer grProcessingDay;

	@Column(name = "po_alert_freq_day")
	private Integer poAlertFreqDay;

	@Column(name = "general_remark")
	private String generalRemark;

	@Column(name = "product_manager_id")
	private Integer productManagerId;

	// add new column
	
	@Column(name = "tariff_head_no")
	private String tariffHeadNo;
	
	@Column(name = "maximum_weight")
	private Double maximumWeight;
	
	@Column(name = "minimum_weight")
	private Double minimumWeight;
	
	@Column(name = "nominal_weight")
	private Double nominalWeight;
	
	@Column(name = "item_sequence_no")
	private Double itemSequenceNo;

	
	public String getTariffHeadNo() {
		return tariffHeadNo;
	}

	public void setTariffHeadNo(String tariffHeadNo) {
		this.tariffHeadNo = tariffHeadNo;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public ItemCategoryEntity getItemCategoryEntity() {
		return itemCategoryEntity;
	}

	public void setItemCategoryEntity(ItemCategoryEntity itemCategoryEntity) {
		this.itemCategoryEntity = itemCategoryEntity;
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

	public Date getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
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

	public Double getSecondaryUnit() {
		return secondaryUnit;
	}

	public void setSecondaryUnit(Double secondaryUnit) {
		this.secondaryUnit = secondaryUnit;
	}

	public Double getPrimaryConversion() {
		return primaryConversion;
	}

	public void setPrimaryConversion(Double primaryConversion) {
		this.primaryConversion = primaryConversion;
	}

	public Double getSecondaryConversion() {
		return secondaryConversion;
	}

	public void setSecondaryConversion(Double secondaryConversion) {
		this.secondaryConversion = secondaryConversion;
	}

	public Double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}

	public Double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
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
		return cenvatAllowFlag;
	}

	public void setCenvatAllowFlag(Integer cenvatAllowFlag) {
		this.cenvatAllowFlag = cenvatAllowFlag;
	}

	public Integer getBatchFlag() {
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

	public StoreLocationEntity getStoreLocationEntity() {
		return storeLocationEntity;
	}

	public void setStoreLocationEntity(StoreLocationEntity storeLocationEntity) {
		this.storeLocationEntity = storeLocationEntity;
	}

	public String getStoringInstruction() {
		return storingInstruction;
	}

	public void setStoringInstruction(String storingInstruction) {
		this.storingInstruction = storingInstruction;
	}

	public Integer getVendorApprovalFlag() {
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

	public Integer getMasterPrimaryUnit() {
		return masterPrimaryUnit;
	}

	public void setMasterPrimaryUnit(Integer masterPrimaryUnit) {
		this.masterPrimaryUnit = masterPrimaryUnit;
	}

	public Integer getMasterSecondaryUnit() {
		return masterSecondaryUnit;
	}

	public void setMasterSecondaryUnit(Integer masterSecondaryUnit) {
		this.masterSecondaryUnit = masterSecondaryUnit;
	}

	public Integer getMasterPrimaryConverUnit() {
		return masterPrimaryConverUnit;
	}

	public void setMasterPrimaryConverUnit(Integer masterPrimaryConverUnit) {
		this.masterPrimaryConverUnit = masterPrimaryConverUnit;
	}

	public Integer getMasterSecondaryConverUnit() {
		return masterSecondaryConverUnit;
	}

	public void setMasterSecondaryConverUnit(Integer masterSecondaryConverUnit) {
		this.masterSecondaryConverUnit = masterSecondaryConverUnit;
	}

	public Integer getMasterGrossWeightUnit() {
		return masterGrossWeightUnit;
	}

	public void setMasterGrossWeightUnit(Integer masterGrossWeightUnit) {
		this.masterGrossWeightUnit = masterGrossWeightUnit;
	}

	public Integer getMasterNetWeightUnit() {
		return masterNetWeightUnit;
	}

	public void setMasterNetWeightUnit(Integer masterNetWeightUnit) {
		this.masterNetWeightUnit = masterNetWeightUnit;
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

	public MastersEntity getMasterGradeEntity() {
		return masterGradeEntity;
	}

	public void setMasterGradeEntity(MastersEntity masterGradeEntity) {
		this.masterGradeEntity = masterGradeEntity;
	}

	public MastersEntity getMasterPackEntity() {
		return masterPackEntity;
	}

	public void setMasterPackEntity(MastersEntity masterPackEntity) {
		this.masterPackEntity = masterPackEntity;
	}

	public MastersEntity getMasterUnitEntity() {
		return masterUnitEntity;
	}

	public void setMasterUnitEntity(MastersEntity masterUnitEntity) {
		this.masterUnitEntity = masterUnitEntity;
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

	public ItemEntity() {
		super();
	}

	public Double getItemSequenceNo() {
		return itemSequenceNo;
	}

	public void setItemSequenceNo(Double itemSequenceNo) {
		this.itemSequenceNo = itemSequenceNo;
	}

	public ItemEntity(Integer itemId, String itemName,String itemCode) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemCode = itemCode;
	}
	public ItemEntity(Integer itemId, String itemName) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
	}

	public ItemEntity(Integer itemId, String itemName,String itemCode,ItemCategoryEntity itemCategoryEntity,MastersEntity masterPackEntity,Double openingStock,Double minStock,Double maxStock,Double reorderLevel) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemCode=itemCode;
		this.itemCategoryEntity=itemCategoryEntity;
		this.masterPackEntity=masterPackEntity;
		this.openingStock=openingStock;
		this.minStock=minStock;
		this.maxStock=maxStock;
		this.reorderLevel=reorderLevel;
	}

}
