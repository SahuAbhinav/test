package com.advanz.erp.masters.entity.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name = "t_debit_duty_master")
public class DebitDutyMasterEntity extends BaseEntity{
	private static final long serialVersionUID = 7883666338946845254L;
	
	@Id
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name="debit_duty_auto_id", unique=true)
	private Integer debitDutyAutoId;
	
	@Column(name="transaction_series")
	private String transactionSeries;
	
	@Column(name="finyr")
	private String finyr;
	
	@Column(name="debit_duty_number")
	private String debitDutyNumber;
	
	@Column(name="debit_duty_id")
	private Integer debitDutyId;
	
	@Column(name="debit_duty_date")
	private Date debitDutyDate;
	
	@ManyToOne
	@JoinColumn(name="branch_id")
	private BranchEntity branchEntity;
	
	@Column(name="excise_amount")
	private Double exciseAmount;
	
	@Column(name="edu_cess_amount")
	private Double eduCessAmount;
	
	@Column(name="h_edu_cess_amount")
	private Double hEduCessAmount;
	
	@Column(name="approved_flag")
	private Integer approvedFlag;
	
	@Column(name="approved_date")
	private Date approvedDate;
	
	@Column(name="narration")
	private String narration;

	
	
	public Integer getDebitDutyAutoId() {
		return debitDutyAutoId;
	}

	public void setDebitDutyAutoId(Integer debitDutyAutoId) {
		this.debitDutyAutoId = debitDutyAutoId;
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

	public String getDebitDutyNumber() {
		return debitDutyNumber;
	}

	public void setDebitDutyNumber(String debitDutyNumber) {
		this.debitDutyNumber = debitDutyNumber;
	}

	public Integer getDebitDutyId() {
		return debitDutyId;
	}

	public void setDebitDutyId(Integer debitDutyId) {
		this.debitDutyId = debitDutyId;
	}

	public Date getDebitDutyDate() {
		return debitDutyDate;
	}

	public void setDebitDutyDate(Date debitDutyDate) {
		this.debitDutyDate = debitDutyDate;
	}

	public BranchEntity getBranchEntity() {
		return branchEntity;
	}

	public void setBranchEntity(BranchEntity branchEntity) {
		this.branchEntity = branchEntity;
	}

	public Double getExciseAmount() {
		return exciseAmount;
	}

	public void setExciseAmount(Double exciseAmount) {
		this.exciseAmount = exciseAmount;
	}

	public Double getEduCessAmount() {
		return eduCessAmount;
	}

	public void setEduCessAmount(Double eduCessAmount) {
		this.eduCessAmount = eduCessAmount;
	}

	public Double gethEduCessAmount() {
		return hEduCessAmount;
	}

	public void sethEduCessAmount(Double hEduCessAmount) {
		this.hEduCessAmount = hEduCessAmount;
	}

	public Integer getApprovedFlag() {
		return approvedFlag;
	}

	public void setApprovedFlag(Integer approvedFlag) {
		this.approvedFlag = approvedFlag;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
