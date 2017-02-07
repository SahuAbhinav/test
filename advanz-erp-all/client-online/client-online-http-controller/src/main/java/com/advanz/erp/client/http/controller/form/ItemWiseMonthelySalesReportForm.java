package com.advanz.erp.client.http.controller.form;

import java.util.Date;

public class ItemWiseMonthelySalesReportForm {
   private String fromMonth;
   private String toMonth;
   
   private Date fromDate;
   private Date toDate;
   private String year;
   private String itemName;
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
public String getFromMonth() {
	return fromMonth;
}
public void setFromMonth(String fromMonth) {
	this.fromMonth = fromMonth;
}
public String getToMonth() {
	return toMonth;
}
public void setToMonth(String toMonth) {
	this.toMonth = toMonth;
}
public String getYear() {
	return year;
}
public void setYear(String year) {
	this.year = year;
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

}
