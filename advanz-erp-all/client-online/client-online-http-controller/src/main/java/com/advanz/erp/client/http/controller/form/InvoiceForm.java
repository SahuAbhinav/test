package com.advanz.erp.client.http.controller.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.advanz.erp.masters.model.BillDTO;
import com.advanz.erp.masters.model.BillDetailDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ProformaDetailDTO;
import com.advanz.erp.masters.model.ProformaMasterDTO;

public class InvoiceForm {

	private long id = 0;
	private BillDTO billDTO;
	private List<BillDetailDTO> billDetailList = new ArrayList<BillDetailDTO>();
	private BillDetailDTO billDetailDTO;

	private ProformaMasterDTO proformaMasterDTO;
	private List<ProformaDetailDTO> proformaDetailList = new ArrayList<ProformaDetailDTO>();
	private ProformaDetailDTO proformaDetailDTO;

	public ProformaMasterDTO getProformaMasterDTO() {
		return proformaMasterDTO;
	}

	public void setProformaMasterDTO(ProformaMasterDTO proformaMasterDTO) {
		this.proformaMasterDTO = proformaMasterDTO;
	}

	public List<ProformaDetailDTO> getProformaDetailList() {
		return proformaDetailList;
	}

	public void setProformaDetailList(List<ProformaDetailDTO> proformaDetailList) {
		this.proformaDetailList = proformaDetailList;
	}

	public ProformaDetailDTO getProformaDetailDTO() {
		return proformaDetailDTO;
	}

	public void setProformaDetailDTO(ProformaDetailDTO proformaDetailDTO) {
		this.proformaDetailDTO = proformaDetailDTO;
	}

	private String cityName;
	private String billingAddress;
	private String phonNo;
	private String contactPerson;
	private String state;

	private String buyerCityName;
	private String buyerBillingAddress;
	private String buyerPhonNo;
	private String buyerContactPerson;
	private String buyerState;

	private Integer invoiceAutoId;
	// private int indexNo;
	private Integer indexNo;
	private String taxType;
	private String invoiceNumber;
	private Integer partyId;

	private Integer buyerId;

	private Date salesOrderDate;

	private Integer dutyVideNotification;

	private String invoiceNumberToPrint;
	private String timeToshow;
	private String taxTypeToshow;
	private String lastInvoiceDate;

	public String getLastInvoiceDate() {
		return lastInvoiceDate;
	}

	public void setLastInvoiceDate(String lastInvoiceDate) {
		this.lastInvoiceDate = lastInvoiceDate;
	}

	public String getTimeToshow() {
		return timeToshow;
	}

	public void setTimeToshow(String timeToshow) {
		this.timeToshow = timeToshow;
	}

	public String getInvoiceNumberToPrint() {
		return invoiceNumberToPrint;
	}

	public void setInvoiceNumberToPrint(String invoiceNumberToPrint) {
		this.invoiceNumberToPrint = invoiceNumberToPrint;
	}

	public Integer getDutyVideNotification() {
		return dutyVideNotification;
	}

	public void setDutyVideNotification(Integer dutyVideNotification) {
		this.dutyVideNotification = dutyVideNotification;
	}

	public Date getSalesOrderDate() {
		return salesOrderDate;
	}

	public void setSalesOrderDate(Date salesOrderDate) {
		this.salesOrderDate = salesOrderDate;
	}

	public Integer getPartyId() {
		return partyId;
	}

	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public Integer getIndexNo() {
		return indexNo;
	}

	public void setIndexNo(Integer indexNo) {
		this.indexNo = indexNo;
	}

	public Integer getInvoiceAutoId() {
		return invoiceAutoId;
	}

	public void setInvoiceAutoId(Integer invoiceAutoId) {
		this.invoiceAutoId = invoiceAutoId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getPhonNo() {
		return phonNo;
	}

	public void setPhonNo(String phonNo) {
		this.phonNo = phonNo;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public BillDetailDTO getBillDetailDTO() {
		return billDetailDTO;
	}

	public void setBillDetailDTO(BillDetailDTO billDetailDTO) {
		this.billDetailDTO = billDetailDTO;
	}

	private Integer itemId;
	private ItemDTO itemDTO;

	public ItemDTO getItemDTO() {
		return itemDTO;
	}

	public void setItemDTO(ItemDTO itemDTO) {
		this.itemDTO = itemDTO;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public BillDTO getBillDTO() {
		return billDTO;
	}

	public void setBillDTO(BillDTO billDTO) {
		this.billDTO = billDTO;
	}

	public List<BillDetailDTO> getBillDetailList() {
		return billDetailList;
	}

	public void setBillDetailList(List<BillDetailDTO> billDetailList) {
		this.billDetailList = billDetailList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBuyerCityName() {
		return buyerCityName;
	}

	public void setBuyerCityName(String buyerCityName) {
		this.buyerCityName = buyerCityName;
	}

	public String getBuyerBillingAddress() {
		return buyerBillingAddress;
	}

	public void setBuyerBillingAddress(String buyerBillingAddress) {
		this.buyerBillingAddress = buyerBillingAddress;
	}

	public String getBuyerPhonNo() {
		return buyerPhonNo;
	}

	public void setBuyerPhonNo(String buyerPhonNo) {
		this.buyerPhonNo = buyerPhonNo;
	}

	public String getBuyerContactPerson() {
		return buyerContactPerson;
	}

	public void setBuyerContactPerson(String buyerContactPerson) {
		this.buyerContactPerson = buyerContactPerson;
	}

	public String getBuyerState() {
		return buyerState;
	}

	public void setBuyerState(String buyerState) {
		this.buyerState = buyerState;
	}

	public Integer getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	public String getTaxTypeToshow() {
		return taxTypeToshow;
	}

	public void setTaxTypeToshow(String taxTypeToshow) {
		this.taxTypeToshow = taxTypeToshow;
	}

}
