package com.advanz.erp.masters.entity.jpa;

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
@Table(name="t_finished_goods")
public class FinishedGoodsMasterEntity extends BaseEntity {
	@Column(name="transaction_series")
	private String transactionSeries;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="finish_auto_id")
		private Integer finishedGoodsAutoId;
	
	@Column(name="finyr")
	private String finYear;	
	
	
	@Column(name="finished_goods_number",unique=true)
	private String finishedGoodsNumber;
	
	@Column(name="finish_good_id")
	private Integer finishGoodId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="finish_good_date")
	private Date finishGoodDate;
	
	@ManyToOne
	@JoinColumn(name="branch_id")
	private BranchEntity branchEntity;
	
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="finish_auto_id")
	private List<FinishedGoodsDetailEntity>finishedGoodsDetailEntities;

	
	@Column(name="approval_flag")
	private Integer approvalFlag;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="aproved_date")
	private Date aprovedDate;
	  
	@Column(name="run_no")
	private String runNo;

	
	@ManyToOne
	@JoinColumn(name="shift_id")
	private MastersEntity mastersEntity;
	
	public List<FinishedGoodsDetailEntity> getFinishedGoodsDetailEntities() {
		return finishedGoodsDetailEntities;
	}


	public void setFinishedGoodsDetailEntities(
			List<FinishedGoodsDetailEntity> finishedGoodsDetailEntities) {
		this.finishedGoodsDetailEntities = finishedGoodsDetailEntities;
	}


	

	public Integer getFinishedGoodsAutoId() {
		return finishedGoodsAutoId;
	}


	public void setFinishedGoodsAutoId(Integer finishedGoodsAutoId) {
		this.finishedGoodsAutoId = finishedGoodsAutoId;
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


	


	public String getFinishedGoodsNumber() {
		return finishedGoodsNumber;
	}


	public void setFinishedGoodsNumber(String finishedGoodsNumber) {
		this.finishedGoodsNumber = finishedGoodsNumber;
	}


	public Integer getFinishGoodId() {
		return finishGoodId;
	}


	public void setFinishGoodId(Integer finishGoodId) {
		this.finishGoodId = finishGoodId;
	}


	public Date getFinishGoodDate() {
		return finishGoodDate;
	}


	public void setFinishGoodDate(Date finishGoodDate) {
		this.finishGoodDate = finishGoodDate;
	}


	public BranchEntity getBranchEntity() {
		return branchEntity;
	}


	public void setBranchEntity(BranchEntity branchEntity) {
		this.branchEntity = branchEntity;
	}


	public Integer getApprovalFlag() {
		return approvalFlag;
	}


	public void setApprovalFlag(Integer approvalFlag) {
		this.approvalFlag = approvalFlag;
	}


	public Date getAprovedDate() {
		return aprovedDate;
	}


	public void setAprovedDate(Date aprovedDate) {
		this.aprovedDate = aprovedDate;
	}


	public String getRunNo() {
		return runNo;
	}


	public void setRunNo(String runNo) {
		this.runNo = runNo;
	}


	public MastersEntity getMastersEntity() {
		return mastersEntity;
	}


	public void setMastersEntity(MastersEntity mastersEntity) {
		this.mastersEntity = mastersEntity;
	}


	@Override
	public String toString() {
		return "FinishedGoodsMasterEntity [transactionSeries="
				+ transactionSeries + ", finishedGoodsAutoId="
				+ finishedGoodsAutoId + ", finYear=" + finYear
				+ ", finishedGoodsNumber=" + finishedGoodsNumber
				+ ", finishGoodId=" + finishGoodId + ", finishGoodDate="
				+ finishGoodDate + ", branchEntity=" + branchEntity
				+ ", finishedGoodsDetailEntities="
				+ finishedGoodsDetailEntities + "]";
	}


	

	


	
	
	
	
	

}
