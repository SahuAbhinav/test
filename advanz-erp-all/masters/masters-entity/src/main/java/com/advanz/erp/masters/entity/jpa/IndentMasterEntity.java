package com.advanz.erp.masters.entity.jpa;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name="t_indent_master")
public class IndentMasterEntity extends BaseEntity {
	
	@Column(name="transaction_series")
	private String transactionSeries;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="indent_auto_id")
		private Integer indentAutoId;
	
	@Column(name="finyr")
	private String finYear;	
	
	
	@Column(name="indent_number")
	private String indentNumber;
	
	@Column(name="indent_id")
	private Integer indentId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="indent_date")
	private Date indentDate;
	
	@Column(name="indent_remark")
	private String indentRemark;
	
	@Column(name="raised_by")
	private String raisedBy;
	
	 
	 
	
	@ManyToOne
	@JoinColumn(name="branch_id")
	private BranchEntity branchEntity;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="indent_auto_id")
	private List<IndentDetailEntity>indentDetailEntity;

	public Integer getIndentAutoId() {
		return indentAutoId;
	}

	public void setIndentAutoId(Integer indentAutoId) {
		this.indentAutoId = indentAutoId;
	}

	public String getIndentNumber() {
		return indentNumber;
	}

	public void setIndentNumber(String indentNumber) {
		this.indentNumber = indentNumber;
	}


	public Integer getIndentId() {
		return indentId;
	}

	public void setIndentId(Integer indentId) {
		this.indentId = indentId;
	}

	public String getIndentRemark() {
		return indentRemark;
	}

	public void setIndentRemark(String indentRemark) {
		this.indentRemark = indentRemark;
	}

	public List<IndentDetailEntity> getIndentDetailEntity() {
		return indentDetailEntity;
	}

	public void setIndentDetailEntity(List<IndentDetailEntity> indentDetailEntity) {
		this.indentDetailEntity = indentDetailEntity;
	}

	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}

	

	
	public String getTransactionSeries() {
		return transactionSeries;
	}

	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}

	public String getFinYear() {
		return finYear;
	}

	


	public BranchEntity getBranchEntity() {
		return branchEntity;
	}

	public void setBranchEntity(BranchEntity branchEntity) {
		this.branchEntity = branchEntity;
	}

	
	

	public Date getIndentDate() {
		return indentDate;
	}

	public void setIndentDate(Date indentDate) {
		this.indentDate = indentDate;
	}

	public String getRaisedBy() {
		return raisedBy;
	}

	public void setRaisedBy(String raisedBy) {
		this.raisedBy = raisedBy;
	}

	
	

}
