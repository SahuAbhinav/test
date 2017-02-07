package com.advanz.erp.masters.entity.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;


@Entity
@Table(name = "M_BATCH_OPENING_STOCK")
public class BatchEntity extends BaseEntity {
	@Id 
	@GeneratedValue(generator="system-incr")
	@GenericGenerator(name="system-incr", strategy = "increment")
	@Column(name="sno")
	private Integer sno;

	
	@ManyToOne
	@JoinColumn(name="item_id")
	private ItemEntity item;
	
	
//	@Column(name="item_id")
//	private Integer itemId;
	
	@Id 
	@Column(name="batch_no")
	private String batchNo;
	
	@Temporal(value=TemporalType.DATE)
	@Column(name="mfg_date")
	private Date mfgDate;
	
	@Temporal(value=TemporalType.DATE)
	@Column(name="expiry_date")
	private Date expiryDate;
	
	@Column(name="mrp")
	private Double mrp;
	
	@Column(name="purchase_rate")
	private Double purchaseRate;
	
	@Column(name="sales_rate")
	private Double invoiceRate;
	
	@Column(name="trade_rate")
	private Double tradeRate;
	
	@Column(name="excise_perc")
	private Double excisePerc;
	
	@Column(name="discount_perc")
	private Double discountPerc;
	
	@Column(name="cst_perc")
	private Double cstPerc;
	
	@Column(name="vat_perc")
	private Double vatPerc;
	
	@Column(name="surcharge")
	private Double surcharge;
	
//	@Column(name="cess")
//	private Double cess;
	
	
	@Column(name="net_rate")
	private Double netRate;
	
//	@Column(name="excise_flag")
//	private int exciseFlag;
//	private String exciseFlag;
	
	@Column(name="active_status")
	private Integer activeStatus;
	
	@Column(name="opening_stock")
	private Double openingStock;
	
	@Column(name="closing_stock")
	private Double closingStock;
	
	@Temporal(value=TemporalType.DATE)
	@Column(name="cst_valid_from")
	private Date cstValidFrom;
	
	@Temporal(value=TemporalType.DATE)
	@Column(name="vat_valid_from")
	private Date vatValidFrom;
	
	

	
//	/**
//	 * @return the itemId
//	 */
//	public Integer getItemId() {
//		return itemId;
//	}
//
//	/**
//	 * @param itemId the itemId to set
//	 */
//	public void setItemId(Integer itemId) {
//		this.itemId = itemId;
//	}

	/**
	 * @return the batchNo
	 */
	public String getBatchNo() {
		return batchNo;
	}

	/**
	 * @param batchNo the batchNo to set
	 */
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	/**
	 * @return the mfgDate
	 */
	public Date getMfgDate() {
		return mfgDate;
	}

	/**
	 * @param mfgDate the mfgDate to set
	 */
	public void setMfgDate(Date mfgDate) {
		this.mfgDate = mfgDate;
	}

	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * @return the mrp
	 */
	public Double getMrp() {
		return mrp;
	}

	/**
	 * @param mrp the mrp to set
	 */
	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}

	/**
	 * @return the purchaseRate
	 */
	public Double getPurchaseRate() {
		return purchaseRate;
	}

	/**
	 * @param purchaseRate the purchaseRate to set
	 */
	public void setPurchaseRate(Double purchaseRate) {
		this.purchaseRate = purchaseRate;
	}

	/**
	 * @return the invoiceRate
	 */
	public Double getInvoiceRate() {
		return invoiceRate;
	}
	public Double getSalesRate() {
		return invoiceRate;
	}

	

	/**
	 * @param invoiceRate the invoiceRate to set
	 */
	public void setInvoiceRate(Double invoiceRate) {
		this.invoiceRate = invoiceRate;
	}
	public void setSalesRate(Double salesRate) {
		this.invoiceRate = salesRate;
	}

	/**
	 * @return the tradeRate
	 */
	public Double getTradeRate() {
		return tradeRate;
	}

	/**
	 * @param tradeRate the tradeRate to set
	 */
	public void setTradeRate(Double tradeRate) {
		this.tradeRate = tradeRate;
	}

	/**
	 * @return the excisePerc
	 */
	public Double getExcisePerc() {
		return excisePerc;
	}

	/**
	 * @param excisePerc the excisePerc to set
	 */
	public void setExcisePerc(Double excisePerc) {
		this.excisePerc = excisePerc;
	}

	/**
	 * @return the discountPerc
	 */
	public Double getDiscountPerc() {
		return discountPerc;
	}
	public Double getDiscountPer() {
		return discountPerc;
	}

	/**
	 * @param discountPerc the discountPerc to set
	 */
	public void setDiscountPerc(Double discountPerc) {
		this.discountPerc = discountPerc;
	}
	public void setDiscountPer(Double discountPerc) {
		this.discountPerc = discountPerc;
	}
	/**
	 * @return the cstPerc
	 */
	public Double getCstPerc() {
		return cstPerc;
	}

	/**
	 * @param cstPerc the cstPerc to set
	 */
	public void setCstPerc(Double cstPerc) {
		this.cstPerc = cstPerc;
	}

	/**
	 * @return the vatPerc
	 */
	public Double getVatPerc() {
		return vatPerc;
	}

	/**
	 * @param vatPerc the vatPerc to set
	 */
	public void setVatPerc(Double vatPerc) {
		this.vatPerc = vatPerc;
	}

	/**
	 * @return the surcharge
	 */
	public Double getSurcharge() {
		return surcharge;
	}
	public Double getSurCharge() {
		return surcharge;
	}
	/**
	 * @param surcharge the surcharge to set
	 */
	public void setSurcharge(Double surcharge) {
		this.surcharge = surcharge;
	}
	public void setSurCharge(Double surcharge) {
		this.surcharge = surcharge;
	}

//	/**
//	 * @return the cess
//	 */
//	public Double getCess() {
//		if(cess==null)
//			cess=0.0;
//		return cess;
//	}
//
//	/**
//	 * @param cess the cess to set
//	 */
//	public void setCess(Double cess) {
//		this.cess = cess;
//	}

	/**
	 * @return the netRate
	 */
	public Double getNetRate() {
		return netRate;
	}

	/**
	 * @param netRate the netRate to set
	 */
	public void setNetRate(Double netRate) {
		this.netRate = netRate;
	}

	
	
//	public int getExciseFlag() {
//		return exciseFlag;
//	}
//
//	public void setExciseFlag(int exciseFlag) {
//		this.exciseFlag = exciseFlag;
//	}

	/**
	 * @return the activeStatus
	 */
	public Integer getActiveStatus() {
		return activeStatus;
	}

	/**
	 * @param activeStatus the activeStatus to set
	 */
	public void setActiveStatus(Integer activeStatus) {
		this.activeStatus = activeStatus;
	}

	/**
	 * @return the openingStock
	 */
	public Double getOpeningStock() {
		return openingStock;
	}

	/**
	 * @param openingStock the openingStock to set
	 */
	public void setOpeningStock(Double openingStock) {
		this.openingStock = openingStock;
	}

	/**
	 * @return the closingStock
	 */
	public Double getClosingStock() {
		return closingStock;
	}

	/**
	 * @param closingStock the closingStock to set
	 */
	public void setClosingStock(Double closingStock) {
		this.closingStock = closingStock;
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BatchEntity [item=" + item + ", batchNo=" + batchNo + "]";
	}

	/**
	 * @return the item
	 */
	public ItemEntity getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(ItemEntity item) {
		this.item = item;
	}

	public Date getCstValidFrom() {
		return cstValidFrom;
	}

	public void setCstValidFrom(Date cstValidFrom) {
		this.cstValidFrom = cstValidFrom;
	}

	public Date getVatValidFrom() {
		return vatValidFrom;
	}

	public void setVatValidFrom(Date vatValidFrom) {
		this.vatValidFrom = vatValidFrom;
	}
	
	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}



}
