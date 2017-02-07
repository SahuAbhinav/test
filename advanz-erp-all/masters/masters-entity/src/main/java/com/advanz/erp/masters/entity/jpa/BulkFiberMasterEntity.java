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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name="t_bulk_fiber_mster")
public class BulkFiberMasterEntity extends BaseEntity{	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bulk_fiber_id")
	private Integer bulkFiberId;
	
	@Temporal(value=TemporalType.DATE)
	@Column(name="bulk_fiber_date")
	private Date bulkFiberDate;
	
	@Column(name="run_no")
	private String runNo;
	
	@ManyToOne
	@JoinColumn(name="shift_id ")
	private MastersEntity shiftMasterEntity;
	
	@ManyToOne
	@JoinColumn(name="grade_id ")
	private MastersEntity gradeMasterEntity;
	
	@Column(name="shift_eng")
	private String shiftEng;
	
	
	
	@Column(name="production_planning")
	private String productionPlanning;
	
	@Column(name="batch_no")
	private String batchNo;
	
	@Column(name="total_bag")
	private String totalBag;
	
	@Column(name="total_bulk")
	private String totalBulk;
	
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="bulk_fiber_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<BulkFiberDetailEntity> bulkFiberDetailEntityList;


	public Integer getBulkFiberId() {
		return bulkFiberId;
	}


	public void setBulkFiberId(Integer bulkFiberId) {
		this.bulkFiberId = bulkFiberId;
	}


	public Date getBulkFiberDate() {
		return bulkFiberDate;
	}


	public void setBulkFiberDate(Date bulkFiberDate) {
		this.bulkFiberDate = bulkFiberDate;
	}


	public String getRunNo() {
		return runNo;
	}


	public void setRunNo(String runNo) {
		this.runNo = runNo;
	}


	public MastersEntity getShiftMasterEntity() {
		return shiftMasterEntity;
	}


	public void setShiftMasterEntity(MastersEntity shiftMasterEntity) {
		this.shiftMasterEntity = shiftMasterEntity;
	}


	public MastersEntity getGradeMasterEntity() {
		return gradeMasterEntity;
	}


	public void setGradeMasterEntity(MastersEntity gradeMasterEntity) {
		this.gradeMasterEntity = gradeMasterEntity;
	}


	public String getShiftEng() {
		return shiftEng;
	}


	public void setShiftEng(String shiftEng) {
		this.shiftEng = shiftEng;
	}



	public String getProductionPlanning() {
		return productionPlanning;
	}


	public void setProductionPlanning(String productionPlanning) {
		this.productionPlanning = productionPlanning;
	}


	public String getBatchNo() {
		return batchNo;
	}


	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}


	public String getTotalBag() {
		return totalBag;
	}


	public void setTotalBag(String totalBag) {
		this.totalBag = totalBag;
	}


	public String getTotalBulk() {
		return totalBulk;
	}


	public void setTotalBulk(String totalBulk) {
		this.totalBulk = totalBulk;
	}


	public List<BulkFiberDetailEntity> getBulkFiberDetailEntityList() {
		return bulkFiberDetailEntityList;
	}


	public void setBulkFiberDetailEntityList(
			List<BulkFiberDetailEntity> bulkFiberDetailEntityList) {
		this.bulkFiberDetailEntityList = bulkFiberDetailEntityList;
	}

	
	

}
