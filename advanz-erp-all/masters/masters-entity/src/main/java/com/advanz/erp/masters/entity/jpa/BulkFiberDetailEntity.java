package com.advanz.erp.masters.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name="t_bulk_fiber_detail")
public class BulkFiberDetailEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sno")
	private Integer sno;
	
	@Column(name="bulk_fiber_id")
	private Integer bulkFiberId;
	
	@Column(name="quantity")
	private Integer quantity;
	
	@Column(name="no_of_bag")
	private Integer noOfBag;
	
	@Column(name="total_bag")
	private Integer totalBag;
		
		
	@Column(name="remark")
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
