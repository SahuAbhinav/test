package com.advanz.erp.masters.entity.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "t_blanket_production_mast_new")
public class BlanketProductionMasterNewEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "blanket_prod_id")
	private Integer blanketProductionId;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "blanket_prod_date")
	private Date blanketProductionDate;

	@Column(name = "run_no")
	private String runNo;

	@ManyToOne
	@JoinColumn(name = "shift_id ")
	private MastersEntity shiftMasterEntity;

	@ManyToOne
	@JoinColumn(name = "grade_id ")
	private MastersEntity gradeMasterEntity;

	@Column(name = "shift_engineer_name")
	private String shiftEngineerName;

	@Column(name = "blanket_prod_remark")
	private String remark;

	@Column(name = "production_planning")
	private String productionPlanning;

	@Column(name = "batch_number")
	private String batchNumber;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "blanket_prod_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<BlanketProductionDetailNewEntity> blanketProductionDetailNewEntityList;

	@Column(name = "roll_packed")
	private Double rollPacked;

	@Column(name = "carton_used")
	private Double cartonUsed;

	@Column(name = "short_lenght")
	private Double shortLenght;

	@Column(name = "edge_trim")
	private Double edgeTrim;

	@Column(name = "bulk_fiber")
	private Double bulkFiber;

	@Column(name = "approve_status")
	private Integer approveStatus;

	@Column(name = "approve_user_id")
	private String approveUserId;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "approve_date")
	private Date approveDate;

	@Column(name = "spliter_count")
	private Integer spliterCount;

	public Integer getSpliterCount() {
		return spliterCount;
	}

	public void setSpliterCount(Integer spliterCount) {
		this.spliterCount = spliterCount;
	}

	public Integer getBlanketProductionId() {
		return blanketProductionId;
	}

	public void setBlanketProductionId(Integer blanketProductionId) {
		this.blanketProductionId = blanketProductionId;
	}

	public Date getBlanketProductionDate() {
		return blanketProductionDate;
	}

	public void setBlanketProductionDate(Date blanketProductionDate) {
		this.blanketProductionDate = blanketProductionDate;
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

	public String getShiftEngineerName() {
		return shiftEngineerName;
	}

	public void setShiftEngineerName(String shiftEngineerName) {
		this.shiftEngineerName = shiftEngineerName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProductionPlanning() {
		return productionPlanning;
	}

	public void setProductionPlanning(String productionPlanning) {
		this.productionPlanning = productionPlanning;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public List<BlanketProductionDetailNewEntity> getBlanketProductionDetailNewEntityList() {
		return blanketProductionDetailNewEntityList;
	}

	public void setBlanketProductionDetailNewEntityList(
			List<BlanketProductionDetailNewEntity> blanketProductionDetailNewEntityList) {
		this.blanketProductionDetailNewEntityList = blanketProductionDetailNewEntityList;
	}

	public Double getRollPacked() {
		return rollPacked;
	}

	public void setRollPacked(Double rollPacked) {
		this.rollPacked = rollPacked;
	}

	public Double getCartonUsed() {
		return cartonUsed;
	}

	public void setCartonUsed(Double cartonUsed) {
		this.cartonUsed = cartonUsed;
	}

	public Double getShortLenght() {
		return shortLenght;
	}

	public void setShortLenght(Double shortLenght) {
		this.shortLenght = shortLenght;
	}

	public Double getEdgeTrim() {
		return edgeTrim;
	}

	public void setEdgeTrim(Double edgeTrim) {
		this.edgeTrim = edgeTrim;
	}

	public Double getBulkFiber() {
		return bulkFiber;
	}

	public void setBulkFiber(Double bulkFiber) {
		this.bulkFiber = bulkFiber;
	}

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getApproveUserId() {
		return approveUserId;
	}

	public void setApproveUserId(String approveUserId) {
		this.approveUserId = approveUserId;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	@Override
	public String toString() {
		return "BlanketProductionMasterNewEntity [blanketProductionId="
				+ blanketProductionId + ", blanketProductionDate="
				+ blanketProductionDate + ", runNo=" + runNo
				+ ", shiftMasterEntity=" + shiftMasterEntity
				+ ", gradeMasterEntity=" + gradeMasterEntity
				+ ", shiftEngineerName=" + shiftEngineerName + ", remark="
				+ remark + ", productionPlanning=" + productionPlanning
				+ ", batchNumber=" + batchNumber
				+ ", blanketProductionDetailNewEntityList="
				+ blanketProductionDetailNewEntityList + ", rollPacked="
				+ rollPacked + ", cartonUsed=" + cartonUsed + ", shortLenght="
				+ shortLenght + ", edgeTrim=" + edgeTrim + ", bulkFiber="
				+ bulkFiber + ", approveStatus=" + approveStatus
				+ ", approveUserId=" + approveUserId + ", approveDate="
				+ approveDate + ", spliterCount=" + spliterCount + "]";
	}
	

}
