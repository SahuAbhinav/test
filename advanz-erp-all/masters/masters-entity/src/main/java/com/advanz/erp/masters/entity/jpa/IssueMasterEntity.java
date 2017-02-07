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
@Table(name="t_issue_mast")
public class IssueMasterEntity extends BaseEntity {
	
	@Column(name="transaction_series")
	private String transactionSeries;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="issue_auto_id")
		private Integer issueAutoId;
	
	@Column(name="finyr")
	private String finYear;	
	
	
	@Column(name="issue_number")
	private String issueNumber;
	
	@Column(name="issue_id")
	private Integer issueId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="issue_date")
	private Date issueDate;
	
	@Column(name="issued_by")
	private String issuedBy;
	
	@Column(name="issue_time")
	private Time issueTime;
	
	
	
	
	 @Column(name = "issue_type_id")
	 private Integer issueTypeId;
	 
	 
	 
	 
	 
	 
	public Integer getIssueTypeId() {
		return issueTypeId;
	}

	public void setIssueTypeId(Integer issueTypeId) {
		this.issueTypeId = issueTypeId;
	}

	//private IssueTypeMasterEntity issueTypeMasterEntity;
	
	
	@ManyToOne
	@JoinColumn(name="branch_id")
	private BranchEntity branchEntity;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="issue_auto_id")
	private List<IssueDetailMasterEntity>issueDetailMasterEntity;

	@Column(name="indent_number")
	private String indentnumber;
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="indent_date")
	private Date indentDate;
	
	@Column(name="raised_by")
	private String raisedBy;
	
	@Column(name="item_group_flag_id")
	private Integer itemGroupFlagId;

	@Column(name="department_id")
	private Integer departmentId;

	
	@Column(name="loan_type")
	private String loanType;
	
	/*public IssueTypeMasterEntity getIssueTypeMasterEntity() {
		return issueTypeMasterEntity;
	}

	public void setIssueTypeMasterEntity(IssueTypeMasterEntity issueTypeMasterEntity) {
		this.issueTypeMasterEntity = issueTypeMasterEntity;
	}*/

	@Column(name="issue_remark")
	private String issueRemark;

	
	@Column(name="approved")
	private Integer approved;
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="approved_date")
	private Date approvedDate;
	
	public String getTransactionSeries() {
		return transactionSeries;
	}

	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}

	public Integer getIssueAutoId() {
		return issueAutoId;
	}

	public void setIssueAutoId(Integer issueAutoId) {
		this.issueAutoId = issueAutoId;
	}

	public String getFinYear() {
		return finYear;
	}

	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}

	public String getIssueNumber() {
		return issueNumber;
	}

	public void setIssueNumber(String issueNumber) {
		this.issueNumber = issueNumber;
	}

	public Integer getIssueId() {
		return issueId;
	}

	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}

	public Time getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(Time issueTime) {
		this.issueTime = issueTime;
	}

	public BranchEntity getBranchEntity() {
		return branchEntity;
	}

	public void setBranchEntity(BranchEntity branchEntity) {
		this.branchEntity = branchEntity;
	}

	public List<IssueDetailMasterEntity> getIssueDetailMasterEntity() {
		return issueDetailMasterEntity;
	}

	public void setIssueDetailMasterEntity(
			List<IssueDetailMasterEntity> issueDetailMasterEntity) {
		this.issueDetailMasterEntity = issueDetailMasterEntity;
	}

	public String getIndentnumber() {
		return indentnumber;
	}

	public void setIndentnumber(String indentnumber) {
		this.indentnumber = indentnumber;
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

	public Integer getItemGroupFlagId() {
		return itemGroupFlagId;
	}

	public void setItemGroupFlagId(Integer itemGroupFlagId) {
		this.itemGroupFlagId = itemGroupFlagId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}


	public String getIssueRemark() {
		return issueRemark;
	}

	public void setIssueRemark(String issueRemark) {
		this.issueRemark = issueRemark;
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

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	@Override
	public String toString() {
		return "IssueMasterEntity [transactionSeries=" + transactionSeries
				+ ", issueAutoId=" + issueAutoId + ", finYear=" + finYear
				+ ", issueNumber=" + issueNumber + ", issueId=" + issueId
				+ ", issueDate=" + issueDate + ", issuedBy=" + issuedBy
				+ ", issueTime=" + issueTime + ", branchEntity=" + branchEntity
				+ ", issueDetailMasterEntity=" + issueDetailMasterEntity
				+ ", indentnumber=" + indentnumber + ", indentDate="
				+ indentDate + ", raisedBy=" + raisedBy + ", itemGroupFlagId="
				+ itemGroupFlagId + ", departmentId=" + departmentId
				+ ", issueRemark="
				+ issueRemark + "]";
	}
	
	
	


	
	
	
	
	

}
