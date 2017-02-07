package com.advanz.erp.masters.entity.jpa;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name = "t_gate_pass_mast")
public class GetPassMasterEntity extends BaseEntity {

	@Column(name="transaction_series")
	private String transactionSeries;
	
	@Column(name="finyr")
	private String finyr;
	
	
	@Id
	@GeneratedValue(generator = "system-incr")
	@GenericGenerator(name = "system-incr", strategy = "increment")
	@Column(name = "gate_pass_auto_id")
	private Integer gatePassAutoId;
	
	@Column(name="gate_pass_type")
	private String gatePassType;

	@Column(name="gate_pass_number")
	private String gatePassNumber;
	
	@Column(name="gate_pass_id")
	private Integer gatePassId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="gate_pass_date")
	private Date gatePassDate;
	
	@Column(name="gate_pass_issued_by")
	private String gatePassIssuedBy;
	
	@Column(name="gate_pass_issue_time")
	private Time gatePassIssueTime;
	
	@ManyToOne
	@JoinColumn(name="branch_id")
	private BranchEntity branchEntity;
	
	@ManyToOne
	@JoinColumn(name="party_id")
	private PartyEntity partyEntity;
	
	@Column(name="sent_through")
	private String sentThrough;
	
	@Column(name="vehical_number")
	private String vehicalNumber;
	
	@Column(name="approved")
	private Integer approved;
	
	@Column(name="approved_date")
	private Date approvedDate;

	@Column(name="gate_pass_purpose")
	private String gatePassPurpose;


	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="gate_pass_auto_id")
	private List<GetPassDetailEntity> getPassDetailEntitiesList;
	
	public List<GetPassDetailEntity> getGetPassDetailEntitiesList() {
		return getPassDetailEntitiesList;
	}

	public void setGetPassDetailEntitiesList(
			List<GetPassDetailEntity> getPassDetailEntitiesList) {
		this.getPassDetailEntitiesList = getPassDetailEntitiesList;
	}

	public String getTransactionSeries() {
		return transactionSeries;
	}

	public void setTransactionSeries(String transactionSeries) {
		this.transactionSeries = transactionSeries;
	}

	public String getFinyr() {
		return finyr;
	}

	public void setFinyr(String finyr) {
		this.finyr = finyr;
	}

	public Integer getGatePassAutoId() {
		return gatePassAutoId;
	}

	public void setGatePassAutoId(Integer gatePassAutoId) {
		this.gatePassAutoId = gatePassAutoId;
	}

	public String getGatePassType() {
		return gatePassType;
	}

	public void setGatePassType(String gatePassType) {
		this.gatePassType = gatePassType;
	}

	public String getGatePassNumber() {
		return gatePassNumber;
	}

	public void setGatePassNumber(String gatePassNumber) {
		this.gatePassNumber = gatePassNumber;
	}

	public Integer getGatePassId() {
		return gatePassId;
	}

	public void setGatePassId(Integer gatePassId) {
		this.gatePassId = gatePassId;
	}

	public Date getGatePassDate() {
		return gatePassDate;
	}

	public void setGatePassDate(Date gatePassDate) {
		this.gatePassDate = gatePassDate;
	}

	public String getGatePassIssuedBy() {
		return gatePassIssuedBy;
	}

	public void setGatePassIssuedBy(String gatePassIssuedBy) {
		this.gatePassIssuedBy = gatePassIssuedBy;
	}

	public Time getGatePassIssueTime() {
		return gatePassIssueTime;
	}

	public void setGatePassIssueTime(Time gatePassIssueTime) {
		this.gatePassIssueTime = gatePassIssueTime;
	}

	public BranchEntity getBranchEntity() {
		return branchEntity;
	}

	public void setBranchEntity(BranchEntity branchEntity) {
		this.branchEntity = branchEntity;
	}

	public PartyEntity getPartyEntity() {
		return partyEntity;
	}

	public void setPartyEntity(PartyEntity partyEntity) {
		this.partyEntity = partyEntity;
	}

	public String getSentThrough() {
		return sentThrough;
	}

	public void setSentThrough(String sentThrough) {
		this.sentThrough = sentThrough;
	}

	public String getVehicalNumber() {
		return vehicalNumber;
	}

	public void setVehicalNumber(String vehicalNumber) {
		this.vehicalNumber = vehicalNumber;
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

	public String getGatePassPurpose() {
		return gatePassPurpose;
	}

	public void setGatePassPurpose(String gatePassPurpose) {
		this.gatePassPurpose = gatePassPurpose;
	}
	
	
	
}
