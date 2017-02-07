package com.advanz.erp.masters.model;

import com.advanz.erp.common.model.BaseDTO;

public class BulkFiberDetailDTO extends BaseDTO{
	private Integer sno;
	private Integer bulkFiberId;
	private Integer quantity;
	private Integer noOfBag;
	private Integer totalBag;
	private String remark;
	public Integer getSno() {
		return sno;
	}
	public void setSno(Integer sno) {
		this.sno = sno;
	}
	public Integer getBulkFiberId() {
		return bulkFiberId;
	}
	public void setBulkFiberId(Integer bulkFiberId) {
		this.bulkFiberId = bulkFiberId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getNoOfBag() {
		return noOfBag;
	}
	public void setNoOfBag(Integer noOfBag) {
		this.noOfBag = noOfBag;
	}
	public Integer getTotalBag() {
		return totalBag;
	}
	public void setTotalBag(Integer totalBag) {
		this.totalBag = totalBag;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	
}
