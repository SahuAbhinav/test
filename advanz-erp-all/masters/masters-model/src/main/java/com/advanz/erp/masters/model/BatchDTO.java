package com.advanz.erp.masters.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class BatchDTO extends BaseDTO {
	
	private  ItemDTO itemDTO=new ItemDTO();
	
	//private Integer itemId;;
private Integer sno;
	private String batchNo;

	private Date mfgDate;

	private Date expiryDate;

	private Double mrp;

	private Double purchaseRate;

	private Double invoiceRate;

	private Double tradeRate;

	private Double excisePerc;

	private Double discountPerc;

	private Double cstPerc;

	private Double vatPerc;

	private Double surcharge;

	private Double cess;

	private Double netRate;

	private int exciseFlag;

	private Integer activeStatus;

	private Double openingStock;

	private Double closingStock;
	

	private Date cstValidFrom;	
	
	private Date vatValidFrom;
	
	private Double stockTotal;

	private List<ItemDTO> itemDTOList;
	private List<ItemDTO> selectItemDTOList;
	
	private List<Integer> itemIdsList;
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

	

	public List<ItemDTO> getSelectItemDTOList() {
		return selectItemDTOList;
	}

	

	public List<Integer> getItemIdsList() {
		return itemIdsList;
	}



	public void setItemIdsList(List<Integer> itemIdsList) {
		this.itemIdsList = itemIdsList;
	}



	public void setSelectItemDTOList(List<ItemDTO> selectItemDTOList) {
		this.selectItemDTOList = selectItemDTOList;
	}

	public List<ItemDTO> getItemDTOList() {
		return itemDTOList;
	}

	public void setItemDTOList(List<ItemDTO> itemDTOList) {
		this.itemDTOList = itemDTOList;
	}

	public Double getStockTotal() {
		return stockTotal;
	}

	public void setStockTotal(Double stockTotal) {
		this.stockTotal = stockTotal;
	}

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

	/**
	 * @param invoiceRate the invoiceRate to set
	 */
	public void setInvoiceRate(Double invoiceRate) {
		this.invoiceRate = invoiceRate;
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

	/**
	 * @param discountPerc the discountPerc to set
	 */
	public void setDiscountPerc(Double discountPerc) {
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

	/**
	 * @param surcharge the surcharge to set
	 */
	public void setSurcharge(Double surcharge) {
		this.surcharge = surcharge;
	}

	/**
	 * @return the cess
	 */
	public Double getCess() {
		return cess;
	}

	/**
	 * @param cess the cess to set
	 */
	public void setCess(Double cess) {
		this.cess = cess;
	}

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
	
	public String getId(){
		return batchNo;
	}
	
	
	
	



	/**
	 * @return the itemDTO
	 */
	public ItemDTO getItemDTO() {
		return itemDTO;
	}

	/**
	 * @param itemDTO the itemDTO to set
	 */
	public void setItemDTO(ItemDTO itemDTO) {
		this.itemDTO = itemDTO;
	}

	public int getExciseFlag() {
		return exciseFlag;
	}

	public void setExciseFlag(int exciseFlag) {
		this.exciseFlag = exciseFlag;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BatchDTO [item=" + itemDTO+ ", batchNo=" + batchNo + "]";
	}

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}


}
