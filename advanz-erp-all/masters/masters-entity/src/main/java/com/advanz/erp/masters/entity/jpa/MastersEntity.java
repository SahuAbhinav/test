package com.advanz.erp.masters.entity.jpa;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.advanz.erp.common.entity.jpa.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name="M_MASTERS")
public class MastersEntity extends BaseEntity {
	

	@Column(name="id")
	private Integer id;	

	@Column(name="m_form_id")
	private Integer formId;
	
	@Column(name="m_form_name")
	private String formName;
	
	@Id
	@Column(name="masters_id")
	private Integer mastersId;
	
	@Column(name="code")
	private String code;
	
	@Column(name="name")
	private String name;	
	
	@Column(name="grade_print_seq_no")
	private Integer gradePrintSeqNo;
	
	@Column(name="emp_type_id")
	private Integer empTypeId;
	
	@Column(name="holiday_from_date")
	private Date holidayFromDate;
	
	@Column(name="holiday_to_date")
	private Date holidayToDate;
	
	@Column(name="shift_from_time")
	private Time shiftFromTime;
	
	@Column(name="shift_to_time")
	private Time shiftToTime;
	
	@Column(name="working_hour")
	private Time workingHour;
	
	@Column(name="late_permit")
	private Time latePermit;
	
	@Column(name="dept_id")
	private Integer deptId;	
	
	@Column(name="description")
	private String description;
	
	

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the formId
	 */
	public Integer getFormId() {
		return formId;
	}

	/**
	 * @param formId the formId to set
	 */
	public void setFormId(Integer formId) {
		this.formId = formId;
	}

	/**
	 * @return the formName
	 */
	public String getFormName() {
		return formName;
	}

	/**
	 * @param formName the formName to set
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the gradePrintSeqNo
	 */
	public Integer getGradePrintSeqNo() {
		return gradePrintSeqNo;
	}

	/**
	 * @param gradePrintSeqNo the gradePrintSeqNo to set
	 */
	public void setGradePrintSeqNo(Integer gradePrintSeqNo) {
		this.gradePrintSeqNo = gradePrintSeqNo;
	}

	/**
	 * @return the empTypeId
	 */
	public Integer getEmpTypeId() {
		return empTypeId;
	}

	/**
	 * @param empTypeId the empTypeId to set
	 */
	public void setEmpTypeId(Integer empTypeId) {
		this.empTypeId = empTypeId;
	}

	/**
	 * @return the holidayFromDate
	 */
	public Date getHolidayFromDate() {
		return holidayFromDate;
	}

	/**
	 * @param holidayFromDate the holidayFromDate to set
	 */
	public void setHolidayFromDate(Date holidayFromDate) {
		this.holidayFromDate = holidayFromDate;
	}

	/**
	 * @return the holidayToDate
	 */
	public Date getHolidayToDate() {
		return holidayToDate;
	}

	/**
	 * @param holidayToDate the holidayToDate to set
	 */
	public void setHolidayToDate(Date holidayToDate) {
		this.holidayToDate = holidayToDate;
	}

	
	/**
	 * @return the shiftFromTime
	 */
	public Time getShiftFromTime() {
		return shiftFromTime;
	}

	/**
	 * @param shiftFromTime the shiftFromTime to set
	 */
	public void setShiftFromTime(Time shiftFromTime) {
		this.shiftFromTime = shiftFromTime;
	}

	/**
	 * @return the shiftToTime
	 */
	public Time getShiftToTime() {
		return shiftToTime;
	}

	/**
	 * @param shiftToTime the shiftToTime to set
	 */
	public void setShiftToTime(Time shiftToTime) {
		this.shiftToTime = shiftToTime;
	}

	

	/**
	 * @return the workingHour
	 */
	public Time getWorkingHour() {
		return workingHour;
	}

	/**
	 * @param workingHour the workingHour to set
	 */
	public void setWorkingHour(Time workingHour) {
		this.workingHour = workingHour;
	}

	/**
	 * @return the latePermit
	 */
	public Time getLatePermit() {
		return latePermit;
	}

	/**
	 * @param latePermit the latePermit to set
	 */
	public void setLatePermit(Time latePermit) {
		this.latePermit = latePermit;
	}

	/**
	 * @return the deptId
	 */
	public Integer getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	
	
	public Integer getMastersId() {
		return mastersId;
	}

	public void setMastersId(Integer mastersId) {
		this.mastersId = mastersId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MastersEntity [id=" + id + ", formId=" + formId + ", formName="
				+ formName + ", code=" + code + ", name=" + name
				+ ", gradePrintSeqNo=" + gradePrintSeqNo + ", empTypeId="
				+ empTypeId + ", holidayFromDate=" + holidayFromDate
				+ ", holidayToDate=" + holidayToDate + ", shiftFromTime="
				+ shiftFromTime + ", shiftToTime=" + shiftToTime
				+ ", workingHour=" + workingHour + ", latePermit=" + latePermit
				+ "]";
	}

	
	


}
