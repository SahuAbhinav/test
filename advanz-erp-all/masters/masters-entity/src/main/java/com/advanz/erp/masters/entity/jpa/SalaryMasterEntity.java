package com.advanz.erp.masters.entity.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@Entity
@Table(name = "t_salary_master")
public class SalaryMasterEntity extends BaseEntity{

	@Id 
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(name = "idGen", strategy = "increment")
	@Column(name="salary_tran_auto_no")
	private Integer salaryTranAutoNo;
	
	@Column(name="salary_month")
	private String salaryMonth;
	
	@Column(name="salary_year")
	private String salaryYear;
	
	@Column(name = "department_id")
	private Integer departmentId;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="salary_tran_auto_no")
	private List<SalaryDetailEntity> salaryDetailEntity;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="salary_tran_auto_no")
	private List<SalaryLeaveEntity> salaryLeaveEntity; 
	
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="salary_tran_auto_no")
	private List<SalaryAttendanceEntity> salaryAttendanceEntity;
	
	@Temporal(value = TemporalType.DATE)
	@Column(name = "min_date")
	private Date minDate;
	
	@Temporal(value = TemporalType.DATE)
	@Column(name = "max_date")
	private Date maxDate;
	
	@Column(name="approved_flag")
	private Integer approvedFlag;

	
	
	public Integer getSalaryTranAutoNo() {
		return salaryTranAutoNo;
	}

	public void setSalaryTranAutoNo(Integer salaryTranAutoNo) {
		this.salaryTranAutoNo = salaryTranAutoNo;
	}

	public String getSalaryMonth() {
		return salaryMonth;
	}

	public void setSalaryMonth(String salaryMonth) {
		this.salaryMonth = salaryMonth;
	}

	public String getSalaryYear() {
		return salaryYear;
	}

	public void setSalaryYear(String salaryYear) {
		this.salaryYear = salaryYear;
	}

	

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}

	public Integer getApprovedFlag() {
		return approvedFlag;
	}

	public void setApprovedFlag(Integer approvedFlag) {
		this.approvedFlag = approvedFlag;
	}

	public List<SalaryDetailEntity> getSalaryDetailEntity() {
		return salaryDetailEntity;
	}

	public void setSalaryDetailEntity(List<SalaryDetailEntity> salaryDetailEntity) {
		this.salaryDetailEntity = salaryDetailEntity;
	}

	public List<SalaryLeaveEntity> getSalaryLeaveEntity() {
		return salaryLeaveEntity;
	}

	public void setSalaryLeaveEntity(List<SalaryLeaveEntity> salaryLeaveEntity) {
		this.salaryLeaveEntity = salaryLeaveEntity;
	}

	public List<SalaryAttendanceEntity> getSalaryAttendanceEntity() {
		return salaryAttendanceEntity;
	}

	public void setSalaryAttendanceEntity(
			List<SalaryAttendanceEntity> salaryAttendanceEntity) {
		this.salaryAttendanceEntity = salaryAttendanceEntity;
	}
	
}
