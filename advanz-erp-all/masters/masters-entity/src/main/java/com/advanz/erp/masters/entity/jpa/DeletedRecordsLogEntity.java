package com.advanz.erp.masters.entity.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name = "deleted_records_log")
public class DeletedRecordsLogEntity  {
	

	@Id 
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name="sno")
	private Integer sno;
	
	@Column(name="transaction_series")
	private String transactionSeries;
	
	@Column(name="transaction_number")
	private String transactionNumber;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="transaction_date")
	private Date transactionDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="delete_on_date")
	private Date deleteOnDate;
	
	@Column(name="delete_by_user_id",updatable=false)
	private Integer deleteByUserId;

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public String getTransactionSeries() {
		return transactionSeries;
	}

	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}

	public String getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	

	public Date getDeleteOnDate() {
		return deleteOnDate;
	}

	public void setDeleteOnDate(Date deleteOnDate) {
		this.deleteOnDate = deleteOnDate;
	}

	public Integer getDeleteByUserId() {
		return deleteByUserId;
	}

	public void setDeleteByUserId(Integer deleteByUserId) {
		this.deleteByUserId = deleteByUserId;
	}
	
	
	
	

	

}
