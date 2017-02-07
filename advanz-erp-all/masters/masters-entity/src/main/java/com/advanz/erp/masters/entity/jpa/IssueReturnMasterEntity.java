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

import com.advanz.erp.common.entity.jpa.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "t_issue_return_mast")
public class IssueReturnMasterEntity extends BaseEntity {

	@Column(name = "transaction_series")
	private String transactionSeries;

	@Column(name = "finyr")
	private String finYear;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "issue_return_auto_id")
	private Integer issueReturnAutoId;

	@Column(name = "issue_return_number")
	private String issueReturnNumber;

	@Column(name = "issue_return_id")
	private Integer issueReturnId;

	@Column(name = "issue_return_date")
	private Date issueReturnDate;

	@Column(name = "issued_return_by")
	private String issuedReturnBy;

	@Column(name = "issue_return_time")
	private Time issueReturnTime;
	@ManyToOne
	@JoinColumn(name = "branch_id")
	private BranchEntity branchEntity;
	@ManyToOne
	@JoinColumn(name = "department_id")
	private MastersEntity mastersEntity;
	
	@Column(name = "issue_return_remark")
	private String issueReturnRemark;

	@Column(name = "approved")
	private Integer approved;

	@Column(name = "approved_date")
	private Date approvedDate;

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="issue_return_auto_id")
	private List<IssueReturnDetailEntity> issueReturnDetailEntities;
	
	public List<IssueReturnDetailEntity> getIssueReturnDetailEntities() {
		return issueReturnDetailEntities;
	}

	public void setIssueReturnDetailEntities(
			List<IssueReturnDetailEntity> issueReturnDetailEntities) {
		this.issueReturnDetailEntities = issueReturnDetailEntities;
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

	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}

	
	public Integer getIssueReturnAutoId() {
		return issueReturnAutoId;
	}

	public void setIssueReturnAutoId(Integer issueReturnAutoId) {
		this.issueReturnAutoId = issueReturnAutoId;
	}
	

	public String getIssueReturnNumber() {
		return issueReturnNumber;
	}

	public void setIssueReturnNumber(String issueReturnNumber) {
		this.issueReturnNumber = issueReturnNumber;
	}

	public Integer getIssueReturnId() {
		return issueReturnId;
	}

	public void setIssueReturnId(Integer issueReturnId) {
		this.issueReturnId = issueReturnId;
	}

	public Date getIssueReturnDate() {
		return issueReturnDate;
	}

	public void setIssueReturnDate(Date issueReturnDate) {
		this.issueReturnDate = issueReturnDate;
	}

	public String getIssuedReturnBy() {
		return issuedReturnBy;
	}

	public void setIssuedReturnBy(String issuedReturnBy) {
		this.issuedReturnBy = issuedReturnBy;
	}

	public Time getIssueReturnTime() {
		return issueReturnTime;
	}

	public void setIssueReturnTime(Time issueReturnTime) {
		this.issueReturnTime = issueReturnTime;
	}

	public String getIssueReturnRemark() {
		return issueReturnRemark;
	}

	public void setIssueReturnRemark(String issueReturnRemark) {
		this.issueReturnRemark = issueReturnRemark;
	}

	public BranchEntity getBranchEntity() {
		return branchEntity;
	}

	public void setBranchEntity(BranchEntity branchEntity) {
		this.branchEntity = branchEntity;
	}

	public MastersEntity getMastersEntity() {
		return mastersEntity;
	}

	public void setMastersEntity(MastersEntity mastersEntity) {
		this.mastersEntity = mastersEntity;
	}

	public Integer getApproved() {
		return approved;
	}

	public void setApproved(Integer approved) {
		this.approved = approved;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

}
