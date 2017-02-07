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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name="t_attandance_master")
public class AttandanceMasterEntity extends BaseEntity{	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="sno")
	private Integer sno;
	

			
	/*@Column(name="employee_name")
	private String employeeName;

	
	@Column(name="employee_code")
	private String employeeCode;*/
	@Column(name="employee_id")
	private Integer employeeId;
	@Column(name="attandance_flag")
	private String attandanceFlag;
	
	@Column(name="day_status")
	private String dayStatus;
	
	@Temporal(value=TemporalType.DATE)
	@Column(name="date")
	private Date date;
	
	@Column(name="day_of_date")
	private String dayOfDate;

	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	/*public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
*/
	public String getAttandanceFlag() {
		return attandanceFlag;
	}

	public void setAttandanceFlag(String attandanceFlag) {
		this.attandanceFlag = attandanceFlag;
	}

	public String getDayStatus() {
		return dayStatus;
	}

	public void setDayStatus(String dayStatus) {
		this.dayStatus = dayStatus;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDayOfDate() {
		return dayOfDate;
	}

	public void setDayOfDate(String dayOfDate) {
		this.dayOfDate = dayOfDate;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	
	

}
