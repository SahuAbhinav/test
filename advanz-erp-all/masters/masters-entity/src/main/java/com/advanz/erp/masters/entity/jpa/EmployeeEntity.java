package com.advanz.erp.masters.entity.jpa;

import java.util.Date;
import java.util.List;

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

@SuppressWarnings("serial")
@Entity 
@Table(name = "m_employee")
public class EmployeeEntity extends BaseEntity {
	@Id
	@GeneratedValue(generator = "system-incr")
	@GenericGenerator(name = "system-incr", strategy = "increment")
	@Column(name = "EMPLOYEE_ID")
	private Integer employeeId;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "employee" )
	private List<EmployeeLeavesEntity> employeeLeavesEntityList;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "employee" )
	private List<EmployeeSalaryDetEntity> employeeSalaryDetEntityList;

//	@ManyToMany(fetch=FetchType.EAGER) 
//	@JoinTable(name="m_employee_salary_det", joinColumns = {@JoinColumn(name="employee_id")}, inverseJoinColumns={@JoinColumn(name="employee_id")})  
//	private List<EmployeeLeavesEntity> employeeLeavesEntityList;	
	// @ManyToOne
	// @JoinColumn(name="item_category_id")
	// private ItemCategoryEntity itemCategoryEntity;

	@Column(name = "employee_code")
	private String employeeCode;

	@Column(name = "employee_local_address")
	private String employeeAddress;
	
	@Column(name = "employee_first_name")
	private String employeeName;

	@Column(name = "PERMANENT_ADDRESS")
	private String permanentAddress;

//	@Column(name = "employee_local_city_id")
//	private Integer employeeCity;
	
	@ManyToOne
	@JoinColumn(name="employee_local_city_id")
	private CityEntity employeeCityEntity;

	@Column(name = "employee_local_zipcode")
	private Integer employeeLocalZipcode;

	@Column(name = "nationality")
	private String nationality;

	@Column(name = "employee_local_phone_o")
	private String phoneOffice;

	@Column(name = "employee_local_phone_r")
	private String phoneResi;

	@Column(name = "contact_local_mobile")
	private String contactMobile;

	@Column(name = "permanent_city_id")
	private Integer permanentCityId;

	@Column(name = "permanent_zipcode")
	private Integer permanentZipcode;

	@Column(name = "permanent_phone_r")
	private String permanentPhone;

	@Column(name = "EMPLOYEE_MAIL_ID")
	private String emailId;

	@Column(name = "EMPLOYEE_SALARY")
	private Double empSalary;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "BIRTH_DATE")
	private Date birthDate;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "ANNIV_DATE")
	private Date annivDate;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "JOIN_DATE")
	private Date joinDate;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "INCREMENT_DATE")
	private Date incrementDate;
	
	@Temporal(value = TemporalType.DATE)
	@Column(name = "separation_date")
	private Date separationDate;

	@Column(name = "separation_reason")
	private String separationReason;

    @Column(name = "Qualification_id")
	private Integer qualificationId;

	@Column(name = "Experience")
	private String experience;

	@Column(name = "Designation_id")
	private Integer designation;
	@ManyToOne
	@JoinColumn(name = "Designation_id" ,insertable=false,updatable=false)
	private MastersEntity mastersEntity;
	

	@Column(name = "Gender")
	private String gender;

	@Column(name = "Marital_Status")
	private Boolean maritalStatus;

	@Column(name = "Blood_Group")
	private String bloodGroup;

	@Column(name = "Grade_id")
	private Integer masterEntityGrade;

	@Column(name = "department_id")
	private Integer masterEntityDepartment;

	@Column(name = "subdepartment_id")
	private Integer masterSubEntityDepartment;

	@Column(name = "Shift_id")
	private Integer masterEntitShift;

	@Column(name = "Cast_id")
	private Integer masterEntitCast;

	@Column(name = "Religion_id")
	private Integer masterEntitReligion;

	@Column(name = "Language_id")
	private Integer masterEntitLanguage;

	@Column(name = "SPECIAL_SKILLS")
	private String specialSkills;

	@Column(name = "ID_PROOF_TYPE")
	private String empIdProofEntity;

	@Column(name = "ID_PROOF_NUMBER")
	private String idProofNumber;
	//
	// @Column(name="EMPLOYEE_IMAGE")
	// private String employeeImage;

	@Column(name = "week_off_1")
	private String weekOff1;

	@Column(name = "week_off_2")
	private String weekOff2;

	@Column(name = "P_F_Flag")
	private Integer pfFlag;

	@Column(name = "E_S_I_Flag")
	private Integer esiFlag;

	@Column(name = "PAN_NUMBER")
	private String panNumber;

	@Column(name = "PF_AC_NUMBER")
	private String pfAcNumber;

	@Column(name = "BANK_AC_NUMBER_1")
	private String bankAcNumber1;

	@Column(name = "bank_name_1")
	private String bankName1;

	@Column(name = "branch_name_1")
	private String branchName;

	@Column(name = "BANK_IFSC_CODE_1")
	private String bankIfscCode1;

	@Column(name = "BANK_AC_NUMBER_2")
	private String bankAcNumber2;

	@Column(name = "branch_name_2")
	private String branchName2;

	@Column(name = "bank_name_2")
	private String bankName2;

	@Column(name = " BANK_IFSC_CODE_2")
	private String bankIfscCode2;

	@Column(name = "ESI_AC_NUMBER")
	private String esiAcNumber;

	@Column(name = "References_Name")
	private String referencesName;

	@Column(name = "Ref_Phone")
	private String refPhone;

	@Column(name = "Employee_Type_id")
	private Integer employeeType;

	@Column(name = "Spouse_Name")
	private String spouseName;

	@Column(name = "Child_Name_1")
	private String childName1;

	@Column(name = "Child_Name_2")
	private String childName2;

	@Column(name = "ACTIVE_STATUS")
	private Integer activeStatus;

	@Column(name = "employee_last_name")
	private String employeeLastName;

	@Column(name = "pm_flag")
	private Integer pmFlag;

	@Column(name = "overtime_flag")
	private Integer overtimeFlag;

	@Column(name = "overtime_rate")
	private Double overtimeRate;

	
	@Column(name = "pf_employer_basic_contri_amt")
	private Double pfEmployerBasicContriAmt;
	
	@Column(name = "pf_employer_share_per")
	private Double pfEmployerSharePer;
	
	@Column(name = "pf_employee_basic_contri_amt")
	private Double pfEmployeeBasicContriAmt;
	
	@Column(name = "pf_employee_share_per")
	private Double pfEmployeeSharePer;

	@Column(name = "esi_employer_share_per")
	private Double esiEmployerSharePer;
	
	@Column(name = "esi_employee_share_per")
	private Double esiEmployeeSharePer;
	
	@Column(name = "esi_employee_gross_cutoff_amt")
	private Double esiEmployeeGrossCutoffAmt;

	@Column(name = "esi_employer_cutoff_amt")
	private Double esiEmployerCutoffAmt;
	
	@Column(name = "opening_cl_balance")
	private Double openingClBalance;
	
	@Column(name = "monthly_salary")
	private Double totalAmount;
	
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
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

	public CityEntity getEmployeeCityEntity() {
		return employeeCityEntity;
	}

	public void setEmployeeCityEntity(CityEntity employeeCityEntity) {
		this.employeeCityEntity = employeeCityEntity;
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
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getAnnivDate() {
		return annivDate;
	}

	public void setAnnivDate(Date annivDate) {
		this.annivDate = annivDate;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getSeparationDate() {
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

	/*public Boolean getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Boolean activeStatus) {
		this.activeStatus = activeStatus;
	}*/

	public Date getIncrementDate() {
		return incrementDate;
	}

	public void setIncrementDate(Date incrementDate) {
		this.incrementDate = incrementDate;
	}

	public Double getOpeningClBalance() {
		return openingClBalance;
	}

	public void setOpeningClBalance(Double openingClBalance) {
		this.openingClBalance = openingClBalance;
	}

	public String getEmployeeLastName() {
		return employeeLastName;
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

	

	public Double getOvertimeRate() {
		return overtimeRate;
	}

	public void setOvertimeRate(Double overtimeRate) {
		this.overtimeRate = overtimeRate;
	}
	
	
	public List<EmployeeLeavesEntity> getEmployeeLeavesEntityList() {
		return employeeLeavesEntityList;
	}

	public void setEmployeeLeavesEntityList(
			List<EmployeeLeavesEntity> employeeLeavesEntity) {
		this.employeeLeavesEntityList = employeeLeavesEntity;
	}

	public List<EmployeeSalaryDetEntity> getEmployeeSalaryDetEntityList() {
		return employeeSalaryDetEntityList;
	}

	public void setEmployeeSalaryDetEntityList(
			List<EmployeeSalaryDetEntity> employeeSalaryDetEntity) {
		this.employeeSalaryDetEntityList = employeeSalaryDetEntity;
	}

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

	public Integer getOvertimeFlag() {
		return overtimeFlag;
	}

	public void setOvertimeFlag(Integer overtimeFlag) {
		this.overtimeFlag = overtimeFlag;
	}

	public Integer getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Integer activeStatus) {
		this.activeStatus = activeStatus;
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

	public MastersEntity getMastersEntity() {
		return mastersEntity;
	}

	public void setMastersEntity(MastersEntity mastersEntity) {
		this.mastersEntity = mastersEntity;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	
}
