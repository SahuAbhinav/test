package com.advanz.erp.masters.entity.jpa;

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
@Table(name = "t_grn_detail")
public class GrnDetailEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name = "sno")
	private Integer sno;

	@Column(name = "grn_auto_id")
	private Integer grnAutoId;

	@Column(name = "grn_number")
	private String grnNumber;
	
	
	
	/**
	 * @return the grnNumber
	 */
	public String getGrnNumber() {
		return grnNumber;
	}

	/**
	 * @param grnNumber the grnNumber to set
	 */
	public void setGrnNumber(String grnNumber) {
		this.grnNumber = grnNumber;
	}

	@Column(name = "transaction_series")
	private String transactionSeries;

	
	@ManyToOne
	@JoinColumn(name = "item_id")
	private ItemEntity itemEntity;

	@Column(name = "measurement_Unit_id")
	private Integer measurementUnitId;

	@Column(name = "bill_qty")
	private Double billQty;

	@Column(name = "bill_tare_weight")
	private Double billTareWeight;

	@Column(name = "bill_net_weight")
	private Double billNetWeight;

	@Column(name = "bill_packet_tot")
	private Double billPacketTot;

	@Column(name = "po_qty")
	private Double poQty;

	@Column(name = "received_qty")
	private Double receivedQty;

	@Column(name = "rec_tare_weight")
	private Double recTareWeight;
	
	@Column(name = "rec_net_weight")
	private Double recNetWeight;
	
	@Column(name = "rec_packet_tot")
	private Double recPacketTot;
	
	@Column(name = "tare_per_packet")
	private Double tarePerPacket;

	@Column(name = "container_description")
	private String containerDescription;

	
	@Column(name = "shrot_qty")
	private Double shrotQty;
	
	@Column(name = "approved_qty")
	private Double approvedQty;
	
	@Column(name = "rejected_qty")
	private Double rejectedQty;
	
	//21-May-2013
	@Column(name = "purchase_rate")
	private Double purchaseRate;
	
	@Column(name = "discount_per")
	private Double discountPer;
	
	@Column(name = "discount_amount")
	private Double discountAmount;
	
	@Column(name = "vat_perc")
	private Double vatPerc;
	
	@Column(name = "vat_amount")
	private Double vatAmount;
	
	@Column(name = "cst_perc")
	private Double cstPerc;
	
	@Column(name = "cst_amount")
	private Double cstAmount;
	
	@Column(name = "net_amount")
	private Double netAmount;

	@Column(name = "item_value")
	private Double itemValue;
	
	// New Column Multiple GRN
	@Column(name = "po_number")
    private String poNumber;
	
	@Column(name = "received_bill_excise_amt")
	private Double receivedBillExciseAmt;
	
	@Column(name = "received_education_cess_amount")
	private Double receivedEducationCessAmount;
	
	@Column(name = "received_high_education_cess_amount")
	private Double receivedHighEducationCessAmount;
	
	@Column(name = "received_item_vat_amount")
	private Double receivedItemVatAmount;
	
	
	@Column(name = "item_basic_amount")
	private Double itemBasicAmount;
	
	
	
	
	public Double getItemBasicAmount() {
		return itemBasicAmount;
	}

	public void setItemBasicAmount(Double itemBasicAmount) {
		this.itemBasicAmount = itemBasicAmount;
	}

	@Column(name = "item_bill_excise_amt")
	private Double itemBillExciseAmt;
	
	
	@Column(name = "item_education_cess_amount")
	private Double itemEducationCessAmount;
	
	
	@Column(name = "item_high_education_cess_amount")
	private Double itemHighEducationCessAmount;
	
	@Column(name = "item_vat_amount")
	private Double itemVatAmount;
	
	@Column(name = "gross_amount")
	private Double grossAmount;
	
	@Column(name = "grn_approval_flag")
	private Integer grnApprovalFlag;

	
	@Column(name="aproved_date")
	private Date aprovedDate;
	
	
	@Column(name="department_id")
	private Integer departmentId;
	
	
	public Double getItemValue() {
		return itemValue;
	}

	public void setItemValue(Double itemValue) {
		this.itemValue = itemValue;
	}

	/**
	 * @return the sno
	 */
	public Integer getSno() {
		return sno;
	}

	/**
	 * @param sno the sno to set
	 */
	public void setSno(Integer sno) {
		this.sno = sno;
	}

	
	public Integer getGrnAutoId() {
		return grnAutoId;
	}

	public void setGrnAutoId(Integer grnAutoId) {
		this.grnAutoId = grnAutoId;
	}

	/**
	 * @return the transactionSeries
	 */
	public String getTransactionSeries() {
		return transactionSeries;
	}

	/**
	 * @param transactionSeries the transactionSeries to set
	 */
	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}

	/**
	 * @return the itemEntity
	 */
	public ItemEntity getItemEntity() {
		return itemEntity;
	}

	/**
	 * @param itemEntity the itemEntity to set
	 */
	public void setItemEntity(ItemEntity itemEntity) {
		this.itemEntity = itemEntity;
	}

	/**
	 * @return the measurementUnitId
	 */
	public Integer getMeasurementUnitId() {
		return measurementUnitId;
	}

	/**
	 * @param measurementUnitId the measurementUnitId to set
	 */
	public void setMeasurementUnitId(Integer measurementUnitId) {
		this.measurementUnitId = measurementUnitId;
	}

	/**
	 * @return the billQty
	 */
	public Double getBillQty() {
		return billQty;
	}

	/**
	 * @param billQty the billQty to set
	 */
	public void setBillQty(Double billQty) {
		this.billQty = billQty;
	}

	/**
	 * @return the billTareWeight
	 */
	public Double getBillTareWeight() {
		return billTareWeight;
	}

	/**
	 * @param billTareWeight the billTareWeight to set
	 */
	public void setBillTareWeight(Double billTareWeight) {
		this.billTareWeight = billTareWeight;
	}

	/**
	 * @return the billNetWeight
	 */
	public Double getBillNetWeight() {
		return billNetWeight;
	}

	/**
	 * @param billNetWeight the billNetWeight to set
	 */
	public void setBillNetWeight(Double billNetWeight) {
		this.billNetWeight = billNetWeight;
	}

	/**
	 * @return the billPacketTot
	 */
	public Double getBillPacketTot() {
		return billPacketTot;
	}

	/**
	 * @param billPacketTot the billPacketTot to set
	 */
	public void setBillPacketTot(Double billPacketTot) {
		this.billPacketTot = billPacketTot;
	}

	/**
	 * @return the poQty
	 */
	public Double getPoQty() {
		return poQty;
	}

	/**
	 * @param poQty the poQty to set
	 */
	public void setPoQty(Double poQty) {
		this.poQty = poQty;
	}

	/**
	 * @return the receivedQty
	 */
	public Double getReceivedQty() {
		return receivedQty;
	}

	/**
	 * @param receivedQty the receivedQty to set
	 */
	public void setReceivedQty(Double receivedQty) {
		this.receivedQty = receivedQty;
	}

	/**
	 * @return the recTareWeight
	 */
	public Double getRecTareWeight() {
		return recTareWeight;
	}

	/**
	 * @param recTareWeight the recTareWeight to set
	 */
	public void setRecTareWeight(Double recTareWeight) {
		this.recTareWeight = recTareWeight;
	}

	/**
	 * @return the recNetWeight
	 */
	public Double getRecNetWeight() {
		return recNetWeight;
	}

	/**
	 * @param recNetWeight the recNetWeight to set
	 */
	public void setRecNetWeight(Double recNetWeight) {
		this.recNetWeight = recNetWeight;
	}

	/**
	 * @return the recPacketTot
	 */
	public Double getRecPacketTot() {
		return recPacketTot;
	}

	/**
	 * @param recPacketTot the recPacketTot to set
	 */
	public void setRecPacketTot(Double recPacketTot) {
		this.recPacketTot = recPacketTot;
	}

	/**
	 * @return the tarePerPacket
	 */
	public Double getTarePerPacket() {
		return tarePerPacket;
	}

	/**
	 * @param tarePerPacket the tarePerPacket to set
	 */
	public void setTarePerPacket(Double tarePerPacket) {
		this.tarePerPacket = tarePerPacket;
	}

	/**
	 * @return the containerDescription
	 */
	public String getContainerDescription() {
		return containerDescription;
	}

	/**
	 * @param containerDescription the containerDescription to set
	 */
	public void setContainerDescription(String containerDescription) {
		this.containerDescription = containerDescription;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Double getShrotQty() {
		return shrotQty;
	}

	public void setShrotQty(Double shrotQty) {
		this.shrotQty = shrotQty;
	}

	public Double getApprovedQty() {
		return approvedQty;
	}

	public void setApprovedQty(Double approvedQty) {
		this.approvedQty = approvedQty;
	}

	public Double getRejectedQty() {
		return rejectedQty;
	}

	public void setRejectedQty(Double rejectedQty) {
		this.rejectedQty = rejectedQty;
	}

	public Double getPurchaseRate() {
		return purchaseRate;
	}

	public void setPurchaseRate(Double purchaseRate) {
		this.purchaseRate = purchaseRate;
	}

	public Double getDiscountPer() {
		return discountPer;
	}

	public void setDiscountPer(Double discountPer) {
		this.discountPer = discountPer;
	}

	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Double getVatPerc() {
		return vatPerc;
	}

	public void setVatPerc(Double vatPerc) {
		this.vatPerc = vatPerc;
	}

	public Double getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(Double vatAmount) {
		this.vatAmount = vatAmount;
	}

	public Double getCstPerc() {
		return cstPerc;
	}

	public void setCstPerc(Double cstPerc) {
		this.cstPerc = cstPerc;
	}

	public Double getCstAmount() {
		return cstAmount;
	}

	public void setCstAmount(Double cstAmount) {
		this.cstAmount = cstAmount;
	}

	public Double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public Double getReceivedBillExciseAmt() {
		return receivedBillExciseAmt;
	}

	public void setReceivedBillExciseAmt(Double receivedBillExciseAmt) {
		this.receivedBillExciseAmt = receivedBillExciseAmt;
	}

	public Double getReceivedEducationCessAmount() {
		return receivedEducationCessAmount;
	}

	public void setReceivedEducationCessAmount(Double receivedEducationCessAmount) {
		this.receivedEducationCessAmount = receivedEducationCessAmount;
	}

	public Double getReceivedHighEducationCessAmount() {
		return receivedHighEducationCessAmount;
	}

	public void setReceivedHighEducationCessAmount(
			Double receivedHighEducationCessAmount) {
		this.receivedHighEducationCessAmount = receivedHighEducationCessAmount;
	}

	public Double getItemBillExciseAmt() {
		return itemBillExciseAmt;
	}

	public void setItemBillExciseAmt(Double itemBillExciseAmt) {
		this.itemBillExciseAmt = itemBillExciseAmt;
	}

	public Double getItemEducationCessAmount() {
		return itemEducationCessAmount;
	}

	public void setItemEducationCessAmount(Double itemEducationCessAmount) {
		this.itemEducationCessAmount = itemEducationCessAmount;
	}

	public Double getItemHighEducationCessAmount() {
		return itemHighEducationCessAmount;
	}

	public void setItemHighEducationCessAmount(Double itemHighEducationCessAmount) {
		this.itemHighEducationCessAmount = itemHighEducationCessAmount;
	}

	public Integer getGrnApprovalFlag() {
		return grnApprovalFlag;
	}

	public void setGrnApprovalFlag(Integer grnApprovalFlag) {
		this.grnApprovalFlag = grnApprovalFlag;
	}

	public Date getAprovedDate() {
		return aprovedDate;
	}

	public void setAprovedDate(Date aprovedDate) {
		this.aprovedDate = aprovedDate;
	}

	public Double getReceivedItemVatAmount() {
		return receivedItemVatAmount;
	}

	public void setReceivedItemVatAmount(Double receivedItemVatAmount) {
		this.receivedItemVatAmount = receivedItemVatAmount;
	}

	public Double getItemVatAmount() {
		return itemVatAmount;
	}

	public void setItemVatAmount(Double itemVatAmount) {
		this.itemVatAmount = itemVatAmount;
	}

	public Double getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(Double grossAmount) {
		this.grossAmount = grossAmount;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	

	
	
}
