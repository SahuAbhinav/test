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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name = "t_return_gate_pass_mast")
public class ReturnGetPassMasterEntity extends BaseEntity {

	@Column(name="transaction_series")
	private String transactionSeries;
	
	@Column(name="finyr")
	private String finyr;
	
	
	@Id
	@GeneratedValue(generator = "system-incr")
	@GenericGenerator(name = "system-incr", strategy = "increment")
	@Column(name = "return_gate_pass_auto_id")
	private Integer returnGatePassAutoId;
	

	@Column(name="return_gate_pass_number")
	private String returnGatePassNumber;
	
	@Column(name="return_gate_pass_id")
	private Integer returnGatePassId;
	
	@Column(name="return_gate_pass_date")
	private Date returnGatePassDate;
	
	@Column(name="return_gate_pass_received_by")
	private String returnGatePassReceivedBy;
	
	@Column(name="return_gate_pass_time")
	private Time returnGatePassTime;
	
	@Column(name="gate_pass_number")
	private String gatePassNumber;
	
	@ManyToOne
	@JoinColumn(name="branch_id")
	private BranchEntity branchEntity;
	
	@ManyToOne
	@JoinColumn(name="party_id")
	private PartyEntity partyEntity;
	
	
	@Column(name="approved")
	private Integer approved;
	
	@Column(name="approved_date")
	private Date approvedDate;

	@Column(name="return_gate_pass_purpose")
	private String returnGatePassPurpose;


	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="return_gate_pass_auto_id")
	private List<ReturnGetPassDetailEntity> returnGetPassDetailEntitiesList;

	
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


	public Integer getReturnGatePassAutoId() {
		return returnGatePassAutoId;
	}


	public void setReturnGatePassAutoId(Integer returnGatePassAutoId) {
		this.returnGatePassAutoId = returnGatePassAutoId;
	}


	public String getReturnGatePassNumber() {
		return returnGatePassNumber;
	}


	public void setReturnGatePassNumber(String returnGatePassNumber) {
		this.returnGatePassNumber = returnGatePassNumber;
	}


	public Integer getReturnGatePassId() {
		return returnGatePassId;
	}


	public void setReturnGatePassId(Integer returnGatePassId) {
		this.returnGatePassId = returnGatePassId;
	}


	public Date getReturnGatePassDate() {
		return returnGatePassDate;
	}


	public void setReturnGatePassDate(Date returnGatePassDate) {
		this.returnGatePassDate = returnGatePassDate;
	}


	public String getReturnGatePassReceivedBy() {
		return returnGatePassReceivedBy;
	}


	public void setReturnGatePassReceivedBy(String returnGatePassReceivedBy) {
		this.returnGatePassReceivedBy = returnGatePassReceivedBy;
	}


	public Time getReturnGatePassTime() {
		return returnGatePassTime;
	}


	public void setReturnGatePassTime(Time returnGatePassTime) {
		this.returnGatePassTime = returnGatePassTime;
	}


	public String getGatePassNumber() {
		return gatePassNumber;
	}


	public void setGatePassNumber(String gatePassNumber) {
		this.gatePassNumber = gatePassNumber;
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


	public String getReturnGatePassPurpose() {
		return returnGatePassPurpose;
	}


	public void setReturnGatePassPurpose(String returnGatePassPurpose) {
		this.returnGatePassPurpose = returnGatePassPurpose;
	}


	public List<ReturnGetPassDetailEntity> getReturnGetPassDetailEntitiesList() {
		return returnGetPassDetailEntitiesList;
	}


	public void setReturnGetPassDetailEntitiesList(
			List<ReturnGetPassDetailEntity> returnGetPassDetailEntitiesList) {
		this.returnGetPassDetailEntitiesList = returnGetPassDetailEntitiesList;
	}
	
	
	
	
	
}
