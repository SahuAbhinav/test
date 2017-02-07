package com.advanz.erp.masters.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class EmployeeDTO extends BaseDTO {

	private Integer employeeId;
	private String update;
	private Double openingBalanceLeave;
	private Double closingBalanceLeave;
	private Double availedLeave;
	private Double workingDayInMonth;

	private List<EmployeeLeavesDTO> employeeLeavesDTOList;

	private List<EmployeeSalaryDetDTO> employeeSalaryDetDTOList;

	private List<EmployeeSalaryDetDTO> employeeSalaryDetDTOListDe;

	private List<Integer> leavesIdList = new ArrayList<Integer>();

	private List<Integer> leavesDaysList = new ArrayList<Integer>();

	private Double eamount;

	private Double damount;

	private Double totalAmount;

	private String employeeCode;

	private String employeeAddress;

	private String employeeName;

	private String permanentAddress;

	private CityDTO employeeCity;

	private Integer employeeLocalZipcode;

	private String nationality;

	private String phoneOffice;

	private String phoneResi;

	private String contactMobile;

	private Integer permanentCityId;

	private Integer permanentZipcode;

	private String permanentPhone;

	private String emailId;

	private Double empSalary;

	private Date birthDate;

	private Date annivDate;

	private Date joinDate;

	private Date separationDate;

	private String birthDateString;

	private String annivDateString;

	private String joinDateString;

	private String separationDateString;

	private String employeeFullName;
	private String designationName;
	private Date incrementDate;
	              
	private Double pfEmployerBasicContriAmt;
	private Double pfEmployerSharePer;
	private Double pfEmployeeBasicContriAmt;
	private Double pfEmployeeSharePer;
	private Double esiEmployerSharePer;
	private Double esiEmployeeSharePer;
	private Double esiEmployeeGrossCutoffAmt;
	private Double esiEmployerCutoffAmt;
	private String separationReason;

	private Integer qualificationId;

	private String experience;

	private Integer designation;

	private String gender;

	private Boolean maritalStatus;

	private String bloodGroup;

	private Integer masterEntityGrade;

	private Integer masterEntityDepartment;

	private Integer masterSubEntityDepartment;

	private Integer masterEntitShift;

	private Integer masterEntitCast;

	private Integer masterEntitReligion;

	private Integer masterEntitLanguage;

	private String specialSkills;

	private String empIdProofEntity;

	private String idProofNumber;
	//
	// @Column(name="EMPLOYEE_IMAGE")
	// private String employeeImage;

	private String weekOff1;

	private String weekOff2;

	private Integer pfFlag;

	private Integer esiFlag;

	private String panNumber;

	private String pfAcNumber;

	private String bankAcNumber1;

	private String bankName1;

	private String branchName;

	private String bankIfscCode1;

	private String bankAcNumber2;

	private String branchName2;

	private String bankName2;

	private String bankIfscCode2;

	private String esiAcNumber;

	private String referencesName;

	private String refPhone;

	private Integer employeeType;

	private String spouseName;

	private String childName1;

	private String childName2;

	private Integer activeStatus;

	private String employeeLastName;

	private Integer pmFlag;

	private Integer overtimeFlag;

	private Double overtimeRate;
	private Double openingClBalance;

	private String weakOff;
	
	private Double workingDays;
	private Double hollyWeekOff;
	private Double presentDays;
	private Double absentDays;
	private Double lossOfPay;
	private Double extraWorkDays;
	private Double payableDays;
	private Double monthlySalary;
	private Double netPayable;
	
	private Double clAdvanceAdj;
	private String advanceType;
	private MastersDTO mastersDTO;
	
	private List earningHeadList = new ArrayList();
	private List dedecationHeadList = new ArrayList();
	private List leaveTypeList = new ArrayList();

	private List earningheadTypeList = new ArrayList();
	private List deductionheadTypeList = new ArrayList();
	

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public List<EmployeeSalaryDetDTO> getEmployeeSalaryDetDTOListDe() {
		return employeeSalaryDetDTOListDe;
	}

	public void setEmployeeSalaryDetDTOListDe(
			List<EmployeeSalaryDetDTO> employeeSalaryDetDTOListDe) {
		this.employeeSalaryDetDTOListDe = employeeSalaryDetDTOListDe;
	}

		
	

	public Double getWorkingDayInMonth() {
		return workingDayInMonth;
	}

	public void setWorkingDayInMonth(Double workingDayInMonth) {
		this.workingDayInMonth = workingDayInMonth;
	}

	public Double getOpeningBalanceLeave() {
		return openingBalanceLeave;
	}

	public Double getOpeningClBalance() {
		return openingClBalance;
	}

	public void setOpeningClBalance(Double openingClBalance) {
		this.openingClBalance = openingClBalance;
	}

	public void setOpeningBalanceLeave(Double openingBalanceLeave) {
		this.openingBalanceLeave = openingBalanceLeave;
	}

	public Double getClosingBalanceLeave() {
		return closingBalanceLeave;
	}

	public void setClosingBalanceLeave(Double closingBalanceLeave) {
		this.closingBalanceLeave = closingBalanceLeave;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	public String getEmployeeNameCode() {

		return employeeName + "-" + employeeCode;

	}

	public Double getEamount() {
		return eamount;
	}

	public void setEamount(Double eamount) {
		this.eamount = eamount;
	}

	public Double getDamount() {
		return damount;
	}

	public void setDamount(Double damount) {
		this.damount = damount;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<Integer> getLeavesIdList() {
		return leavesIdList;
	}

	public void setLeavesIdList(List<Integer> leavesIdList) {
		this.leavesIdList = leavesIdList;
	}

	public List<Integer> getLeavesDaysList() {
		return leavesDaysList;
	}

	public void setLeavesDaysList(List<Integer> leavesDaysList) {
		this.leavesDaysList = leavesDaysList;
	}

	public Double getAvailedLeave() {
		return availedLeave;
	}

	public void setAvailedLeave(Double availedLeave) {
		this.availedLeave = availedLeave;
	}

	public List getEarningheadTypeList() {
		return earningheadTypeList;
	}

	public void setEarningheadTypeList(List earningheadTypeList) {
		this.earningheadTypeList = earningheadTypeList;
	}

	public List getDeductionheadTypeList() {
		return deductionheadTypeList;
	}

	public void setDeductionheadTypeList(List deductionheadTypeList) {
		this.deductionheadTypeList = deductionheadTypeList;
	}

	public List getEarningHeadList() {
		return earningHeadList;
	}

	public void setEarningHeadList(List earningHeadList) {
		this.earningHeadList = earningHeadList;
	}

	public List getDedecationHeadList() {
		return dedecationHeadList;
	}

	public void setDedecationHeadList(List dedecationHeadList) {
		this.dedecationHeadList = dedecationHeadList;
	}

	public List getLeaveTypeList() {
		return leaveTypeList;
	}

	public void setLeaveTypeList(List leaveTypeList) {
		this.leaveTypeList = leaveTypeList;
	}

	public String getEmployeeFullName() {
		if (this.employeeName != null && this.employeeLastName != null) {
			return this.employeeName + " " + this.employeeLastName;
		}
		return "";

	}

	public void setEmployeeFullName(String employeeFullName) {
		this.employeeFullName = employeeFullName;
	}

	public String getBirthDateString() {
		if (birthDate != null) {
			SimpleDateFormat obj = new SimpleDateFormat("MM/dd/yyyy");
			return obj.format(birthDate);
		}
		return birthDateString;
	}

	public void setBirthDateString(String birthDateString) {
		this.birthDateString = birthDateString;
	}

	public String getAnnivDateString() {
		if (annivDate != null) {
			SimpleDateFormat obj = new SimpleDateFormat("MM/dd/yyyy");
			return obj.format(annivDate);
		}
		return annivDateString;
	}

	public void setAnnivDateString(String annivDateString) {
		this.annivDateString = annivDateString;
	}

	public String getJoinDateString() {
		if (joinDate != null) {
			SimpleDateFormat obj = new SimpleDateFormat("MM/dd/yyyy");
			return obj.format(joinDate);
		}
		return joinDateString;
	}

	public void setJoinDateString(String joinDateString) {
		this.joinDateString = joinDateString;
	}

	public String getSeparationDateString() {

		if (separationDate != null) {
			SimpleDateFormat obj = new SimpleDateFormat("MM/dd/yyyy");
			return obj.format(separationDate);
		}
		return separationDateString;
	}

	public void setSeparationDateString(String separationDateString) {
		this.separationDateString = separationDateString;
	}

	public Date getIncrementDate() {
		return incrementDate;
	}

	public void setIncrementDate(Date incrementDate) {
		this.incrementDate = incrementDate;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public List<EmployeeLeavesDTO> getEmployeeLeavesDTOList() {
		return employeeLeavesDTOList;
	}

	public void setEmployeeLeavesDTOList(
			List<EmployeeLeavesDTO> employeeLeavesDTOList) {
		this.employeeLeavesDTOList = employeeLeavesDTOList;
	}

	public List<EmployeeSalaryDetDTO> getEmployeeSalaryDetDTOList() {
		return employeeSalaryDetDTOList;
	}

	public void setEmployeeSalaryDetDTOList(
			List<EmployeeSalaryDetDTO> employeeSalaryDetDTOList) {
		this.employeeSalaryDetDTOList = employeeSalaryDetDTOList;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public CityDTO getEmployeeCity() {
		return employeeCity;
	}

	public void setEmployeeCity(CityDTO employeeCity) {
		this.employeeCity = employeeCity;
	}

	public Integer getEmployeeLocalZipcode() {
		return employeeLocalZipcode;
	}

	public void setEmployeeLocalZipcode(Integer employeeLocalZipcode) {
		this.employeeLocalZipcode = employeeLocalZipcode;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPhoneOffice() {
		return phoneOffice;
	}

	public void setPhoneOffice(String phoneOffice) {
		this.phoneOffice = phoneOffice;
	}

	public String getPhoneResi() {
		return phoneResi;
	}

	public void setPhoneResi(String phoneResi) {
		this.phoneResi = phoneResi;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public Integer getPermanentCityId() {
		return permanentCityId;
	}

	public void setPermanentCityId(Integer permanentCityId) {
		this.permanentCityId = permanentCityId;
	}

	public Integer getPermanentZipcode() {
		return permanentZipcode;
	}

	public void setPermanentZipcode(Integer permanentZipcode) {
		this.permanentZipcode = permanentZipcode;
	}

	public String getPermanentPhone() {
		return permanentPhone;
	}

	public void setPermanentPhone(String permanentPhone) {
		this.permanentPhone = permanentPhone;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Double getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(Double empSalary) {
		this.empSalary = empSalary;
	}

	public Date getBirthDate() {
		try {
			if (birthDateString != null && !birthDateString.trim().equals("")) {
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				birthDate = format.parse(birthDateString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getAnnivDate() {
		try {
			if (annivDateString != null && !annivDateString.trim().equals("")) {
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				annivDate = format.parse(annivDateString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return annivDate;
	}

	public void setAnnivDate(Date annivDate) {
		this.annivDate = annivDate;
	}

	public Date getJoinDate() {
		// try{
		// if(joinDateString!=null && !joinDateString.trim().equals("")){
		// SimpleDateFormat format=new SimpleDateFormat("MM/dd/yyyy");
		// joinDate=format.parse(joinDateString);
		// }
		// }catch(Exception e){
		// e.printStackTrace();
		// }
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getSeparationDate() {
		try {
			if (separationDateString != null
					&& !separationDateString.trim().equals("")) {
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				separationDate = format.parse(separationDateString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return separationDate;
	}

	public void setSeparationDate(Date separationDate) {
		this.separationDate = separationDate;
	}

	public String getSeparationReason() {
		return separationReason;
	}

	public void setSeparationReason(String separationReason) {
		this.separationReason = separationReason;
	}

	public Integer getQualificationId() {
		return qualificationId;
	}

	public void setQualificationId(Integer qualificationId) {
		this.qualificationId = qualificationId;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public Integer getDesignation() {
		return designation;
	}

	public void setDesignation(Integer designation) {
		this.designation = designation;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Boolean getMaritalStatus() {
		if (maritalStatus == null)
			maritalStatus = false;
		return maritalStatus;
	}

	public void setMaritalStatus(Boolean maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Integer getMasterEntityGrade() {
		return masterEntityGrade;
	}

	public void setMasterEntityGrade(Integer masterEntityGrade) {
		this.masterEntityGrade = masterEntityGrade;
	}

	public Integer getMasterEntityDepartment() {
		return masterEntityDepartment;
	}

	public void setMasterEntityDepartment(Integer masterEntityDepartment) {
		this.masterEntityDepartment = masterEntityDepartment;
	}

	public Integer getMasterSubEntityDepartment() {
		return masterSubEntityDepartment;
	}

	public void setMasterSubEntityDepartment(Integer masterSubEntityDepartment) {
		this.masterSubEntityDepartment = masterSubEntityDepartment;
	}

	public Integer getMasterEntitShift() {
		return masterEntitShift;
	}

	public void setMasterEntitShift(Integer masterEntitShift) {
		this.masterEntitShift = masterEntitShift;
	}

	public Integer getMasterEntitCast() {
		return masterEntitCast;
	}

	public void setMasterEntitCast(Integer masterEntitCast) {
		this.masterEntitCast = masterEntitCast;
	}

	public Integer getMasterEntitReligion() {
		return masterEntitReligion;
	}

	public void setMasterEntitReligion(Integer masterEntitReligion) {
		this.masterEntitReligion = masterEntitReligion;
	}

	public Integer getMasterEntitLanguage() {
		return masterEntitLanguage;
	}

	public void setMasterEntitLanguage(Integer masterEntitLanguage) {
		this.masterEntitLanguage = masterEntitLanguage;
	}

	public String getSpecialSkills() {
		return specialSkills;
	}

	public void setSpecialSkills(String specialSkills) {
		this.specialSkills = specialSkills;
	}

	public String getEmpIdProofEntity() {
		return empIdProofEntity;
	}

	public void setEmpIdProofEntity(String empIdProofEntity) {
		this.empIdProofEntity = empIdProofEntity;
	}

	public String getIdProofNumber() {
		return idProofNumber;
	}

	public void setIdProofNumber(String idProofNumber) {
		this.idProofNumber = idProofNumber;
	}

	public String getWeekOff1() {
		return weekOff1;
	}

	public void setWeekOff1(String weekOff1) {
		this.weekOff1 = weekOff1;
	}

	public String getWeekOff2() {
		return weekOff2;
	}

	public void setWeekOff2(String weekOff2) {
		this.weekOff2 = weekOff2;
	}

	/*
	 * public Boolean getPfFlag() { if(pfFlag==null) pfFlag=false; return
	 * pfFlag; }
	 * 
	 * public void setPfFlag(Boolean pfFlag) { this.pfFlag = pfFlag; }
	 * 
	 * public Boolean getEsiFlag() { if(esiFlag==null) esiFlag=false; return
	 * esiFlag; }
	 * 
	 * public void setEsiFlag(Boolean esiFlag) { this.esiFlag = esiFlag; }
	 */

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getPfAcNumber() {
		return pfAcNumber;
	}

	public void setPfAcNumber(String pfAcNumber) {
		this.pfAcNumber = pfAcNumber;
	}

	public String getBankAcNumber1() {
		return bankAcNumber1;
	}

	public void setBankAcNumber1(String bankAcNumber1) {
		this.bankAcNumber1 = bankAcNumber1;
	}

	public String getBankName1() {
		return bankName1;
	}

	public void setBankName1(String bankName1) {
		this.bankName1 = bankName1;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBankIfscCode1() {
		return bankIfscCode1;
	}

	public void setBankIfscCode1(String bankIfscCode1) {
		this.bankIfscCode1 = bankIfscCode1;
	}

	public String getBankAcNumber2() {
		return bankAcNumber2;
	}

	public void setBankAcNumber2(String bankAcNumber2) {
		this.bankAcNumber2 = bankAcNumber2;
	}

	public String getBranchName2() {
		return branchName2;
	}

	public void setBranchName2(String branchName2) {
		this.branchName2 = branchName2;
	}

	public String getBankName2() {
		return bankName2;
	}

	public void setBankName2(String bankName2) {
		this.bankName2 = bankName2;
	}

	public String getBankIfscCode2() {
		return bankIfscCode2;
	}

	public void setBankIfscCode2(String bankIfscCode2) {
		this.bankIfscCode2 = bankIfscCode2;
	}

	public String getEsiAcNumber() {
		return esiAcNumber;
	}

	public void setEsiAcNumber(String esiAcNumber) {
		this.esiAcNumber = esiAcNumber;
	}

	public String getReferencesName() {
		return referencesName;
	}

	public void setReferencesName(String referencesName) {
		this.referencesName = referencesName;
	}

	public String getRefPhone() {
		return refPhone;
	}

	public void setRefPhone(String refPhone) {
		this.refPhone = refPhone;
	}

	public Integer getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(Integer employeeType) {
		this.employeeType = employeeType;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getChildName1() {
		return childName1;
	}

	public void setChildName1(String childName1) {
		this.childName1 = childName1;
	}

	public String getChildName2() {
		return childName2;
	}

	public void setChildName2(String childName2) {
		this.childName2 = childName2;
	}

	/*
	 * public Boolean getActiveStatus() { if(activeStatus==null)
	 * activeStatus=true; return activeStatus; }
	 * 
	 * public void setActiveStatus(Boolean activeStatus) { this.activeStatus =
	 * activeStatus; }
	 */
	public String getEmployeeLastName() {
		return employeeLastName;
	}

	public Integer getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Integer activeStatus) {
		this.activeStatus = activeStatus;
	}

	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}

	public Integer getPmFlag() {
		return pmFlag;
	}

	public void setPmFlag(Integer pmFlag) {
		this.pmFlag = pmFlag;
	}

	/*
	 * public Boolean getOvertimeFlag() { if(overtimeFlag==null)
	 * overtimeFlag=false; return overtimeFlag; }
	 * 
	 * public void setOvertimeFlag(Boolean overtimeFlag) { this.overtimeFlag =
	 * overtimeFlag; }
	 */
	public Integer getPfFlag() {
		return pfFlag;
	}

	public void setPfFlag(Integer pfFlag) {
		this.pfFlag = pfFlag;
	}

	public Integer getEsiFlag() {
		return esiFlag;
	}

	public void setEsiFlag(Integer esiFlag) {
		this.esiFlag = esiFlag;
	}

	public Double getOvertimeRate() {
		return overtimeRate;
	}

	public void setOvertimeRate(Double overtimeRate) {
		this.overtimeRate = overtimeRate;
	}

	public Integer getOvertimeFlag() {
		return overtimeFlag;
	}

	public void setOvertimeFlag(Integer overtimeFlag) {
		this.overtimeFlag = overtimeFlag;
	}

	public String getWeakOff() {
		return weakOff;
	}

	public void setWeakOff(String weakOff) {
		this.weakOff = weakOff;
	}

	public Double getPfEmployerBasicContriAmt() {
		return pfEmployerBasicContriAmt;
	}

	public void setPfEmployerBasicContriAmt(Double pfEmployerBasicContriAmt) {
		this.pfEmployerBasicContriAmt = pfEmployerBasicContriAmt;
	}

	public Double getPfEmployerSharePer() {
		return pfEmployerSharePer;
	}

	public void setPfEmployerSharePer(Double pfEmployerSharePer) {
		this.pfEmployerSharePer = pfEmployerSharePer;
	}

	public Double getPfEmployeeBasicContriAmt() {
		return pfEmployeeBasicContriAmt;
	}

	public void setPfEmployeeBasicContriAmt(Double pfEmployeeBasicContriAmt) {
		this.pfEmployeeBasicContriAmt = pfEmployeeBasicContriAmt;
	}

	public Double getPfEmployeeSharePer() {
		return pfEmployeeSharePer;
	}

	public void setPfEmployeeSharePer(Double pfEmployeeSharePer) {
		this.pfEmployeeSharePer = pfEmployeeSharePer;
	}

	public Double getEsiEmployerSharePer() {
		return esiEmployerSharePer;
	}

	public void setEsiEmployerSharePer(Double esiEmployerSharePer) {
		this.esiEmployerSharePer = esiEmployerSharePer;
	}

	public Double getEsiEmployeeSharePer() {
		return esiEmployeeSharePer;
	}

	public void setEsiEmployeeSharePer(Double esiEmployeeSharePer) {
		this.esiEmployeeSharePer = esiEmployeeSharePer;
	}

	public Double getEsiEmployeeGrossCutoffAmt() {
		return esiEmployeeGrossCutoffAmt;
	}

	public void setEsiEmployeeGrossCutoffAmt(Double esiEmployeeGrossCutoffAmt) {
		this.esiEmployeeGrossCutoffAmt = esiEmployeeGrossCutoffAmt;
	}

	public Double getEsiEmployerCutoffAmt() {
		return esiEmployerCutoffAmt;
	}

	public void setEsiEmployerCutoffAmt(Double esiEmployerCutoffAmt) {
		this.esiEmployerCutoffAmt = esiEmployerCutoffAmt;
	}

	public Double getWorkingDays() {
		return workingDays;
	}

	public void setWorkingDays(Double workingDays) {
		this.workingDays = workingDays;
	}

	public Double getHollyWeekOff() {
		return hollyWeekOff;
	}

	public void setHollyWeekOff(Double hollyWeekOff) {
		this.hollyWeekOff = hollyWeekOff;
	}

	public Double getPresentDays() {
		return presentDays;
	}

	public void setPresentDays(Double presentDays) {
		this.presentDays = presentDays;
	}



	public Double getAbsentDays() {
		return absentDays;
	}

	public void setAbsentDays(Double absentDays) {
		this.absentDays = absentDays;
	}

	public Double getLossOfPay() {
		return lossOfPay;
	}

	public void setLossOfPay(Double lossOfPay) {
		this.lossOfPay = lossOfPay;
	}

	public Double getExtraWorkDays() {
		return extraWorkDays;
	}

	public void setExtraWorkDays(Double extraWorkDays) {
		this.extraWorkDays = extraWorkDays;
	}

	public Double getPayableDays() {
		return payableDays;
	}

	public void setPayableDays(Double payableDays) {
		this.payableDays = payableDays;
	}

	public Double getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(Double monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	public Double getNetPayable() {
		return netPayable;
	}

	public void setNetPayable(Double netPayable) {
		this.netPayable = netPayable;
	}

	public MastersDTO getMastersDTO() {
		return mastersDTO;
	}

	public void setMastersDTO(MastersDTO mastersDTO) {
		this.mastersDTO = mastersDTO;
	}



	public Double getClAdvanceAdj() {
		return clAdvanceAdj;
	}

	public void setClAdvanceAdj(Double clAdvanceAdj) {
		this.clAdvanceAdj = clAdvanceAdj;
	}

	public String getAdvanceType() {
		return advanceType;
	}

	public void setAdvanceType(String advanceType) {
		this.advanceType = advanceType;
	}

	@Override
	public String toString() {
		return "EmployeeDTO [employeeId=" + employeeId + ", update=" + update
				+ ", openingBalanceLeave=" + openingBalanceLeave
				+ ", closingBalanceLeave=" + closingBalanceLeave
				+ ", availedLeave=" + availedLeave + ", workingDayInMonth="
				+ workingDayInMonth + ", employeeLeavesDTOList="
				+ employeeLeavesDTOList + ", employeeSalaryDetDTOList="
				+ employeeSalaryDetDTOList + ", employeeSalaryDetDTOListDe="
				+ employeeSalaryDetDTOListDe + ", leavesIdList=" + leavesIdList
				+ ", leavesDaysList=" + leavesDaysList + ", eamount=" + eamount
				+ ", damount=" + damount + ", totalAmount=" + totalAmount
				+ ", employeeCode=" + employeeCode + ", employeeAddress="
				+ employeeAddress + ", employeeName=" + employeeName
				+ ", permanentAddress=" + permanentAddress + ", employeeCity="
				+ employeeCity + ", employeeLocalZipcode="
				+ employeeLocalZipcode + ", nationality=" + nationality
				+ ", phoneOffice=" + phoneOffice + ", phoneResi=" + phoneResi
				+ ", contactMobile=" + contactMobile + ", permanentCityId="
				+ permanentCityId + ", permanentZipcode=" + permanentZipcode
				+ ", permanentPhone=" + permanentPhone + ", emailId=" + emailId
				+ ", empSalary=" + empSalary + ", birthDate=" + birthDate
				+ ", annivDate=" + annivDate + ", joinDate=" + joinDate
				+ ", separationDate=" + separationDate + ", birthDateString="
				+ birthDateString + ", annivDateString=" + annivDateString
				+ ", joinDateString=" + joinDateString
				+ ", separationDateString=" + separationDateString
				+ ", employeeFullName=" + employeeFullName
				+ ", designationName=" + designationName + ", incrementDate="
				+ incrementDate + ", pfEmployerBasicContriAmt="
				+ pfEmployerBasicContriAmt + ", pfEmployerSharePer="
				+ pfEmployerSharePer + ", pfEmployeeBasicContriAmt="
				+ pfEmployeeBasicContriAmt + ", pfEmployeeSharePer="
				+ pfEmployeeSharePer + ", esiEmployerSharePer="
				+ esiEmployerSharePer + ", esiEmployeeSharePer="
				+ esiEmployeeSharePer + ", esiEmployeeGrossCutoffAmt="
				+ esiEmployeeGrossCutoffAmt + ", esiEmployerCutoffAmt="
				+ esiEmployerCutoffAmt + ", separationReason="
				+ separationReason + ", qualificationId=" + qualificationId
				+ ", experience=" + experience + ", designation=" + designation
				+ ", gender=" + gender + ", maritalStatus=" + maritalStatus
				+ ", bloodGroup=" + bloodGroup + ", masterEntityGrade="
				+ masterEntityGrade + ", masterEntityDepartment="
				+ masterEntityDepartment + ", masterSubEntityDepartment="
				+ masterSubEntityDepartment + ", masterEntitShift="
				+ masterEntitShift + ", masterEntitCast=" + masterEntitCast
				+ ", masterEntitReligion=" + masterEntitReligion
				+ ", masterEntitLanguage=" + masterEntitLanguage
				+ ", specialSkills=" + specialSkills + ", empIdProofEntity="
				+ empIdProofEntity + ", idProofNumber=" + idProofNumber
				+ ", weekOff1=" + weekOff1 + ", weekOff2=" + weekOff2
				+ ", pfFlag=" + pfFlag + ", esiFlag=" + esiFlag
				+ ", panNumber=" + panNumber + ", pfAcNumber=" + pfAcNumber
				+ ", bankAcNumber1=" + bankAcNumber1 + ", bankName1="
				+ bankName1 + ", branchName=" + branchName + ", bankIfscCode1="
				+ bankIfscCode1 + ", bankAcNumber2=" + bankAcNumber2
				+ ", branchName2=" + branchName2 + ", bankName2=" + bankName2
				+ ", bankIfscCode2=" + bankIfscCode2 + ", esiAcNumber="
				+ esiAcNumber + ", referencesName=" + referencesName
				+ ", refPhone=" + refPhone + ", employeeType=" + employeeType
				+ ", spouseName=" + spouseName + ", childName1=" + childName1
				+ ", childName2=" + childName2 + ", activeStatus="
				+ activeStatus + ", employeeLastName=" + employeeLastName
				+ ", pmFlag=" + pmFlag + ", overtimeFlag=" + overtimeFlag
				+ ", overtimeRate=" + overtimeRate + ", openingClBalance="
				+ openingClBalance + ", weakOff=" + weakOff + ", workingDays="
				+ workingDays + ", hollyWeekOff=" + hollyWeekOff
				+ ", presentDays=" + presentDays + ", absentDays=" + absentDays
				+ ", lossOfPay=" + lossOfPay + ", extraWorkDays="
				+ extraWorkDays + ", payableDays=" + payableDays
				+ ", monthlySalary=" + monthlySalary + ", netPayable="
				+ netPayable + ", clAdvanceAdj=" + clAdvanceAdj
				+ ", advanceType=" + advanceType + ", mastersDTO=" + mastersDTO
				+ ", earningHeadList=" + earningHeadList
				+ ", dedecationHeadList=" + dedecationHeadList
				+ ", leaveTypeList=" + leaveTypeList + ", earningheadTypeList="
				+ earningheadTypeList + ", deductionheadTypeList="
				+ deductionheadTypeList + "]";
	}
	
	/*
	 * public Double getOvertimeRate() { return overtimeRate; }
	 * 
	 * public void setOvertimeRate(Double overtimeRate) { this.overtimeRate =
	 * overtimeRate; }
	 */

	
}
