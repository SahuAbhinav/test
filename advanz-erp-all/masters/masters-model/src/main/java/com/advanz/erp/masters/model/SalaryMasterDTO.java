package com.advanz.erp.masters.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.advanz.erp.common.model.BaseDTO;

@SuppressWarnings("serial")
public class SalaryMasterDTO extends BaseDTO {

	private Integer salaryTranAutoNo;
	private String salaryMonth;
	private String salaryYear;
	private Integer departmentId;
	private Date minDate;
	private Date maxDate;
	private Integer approvedFlag=0;
	private Integer salaryYearInt;
	private EmployeeDTO employeeDTO;
	
	private List employeeNameList=new ArrayList();
	private List employeeIdList=new ArrayList();
	private List departmentIdList=new ArrayList();
	private List earningTotalAmntList=new ArrayList();
	private List dedectionTotalAmntList=new ArrayList();
	private List balAdvanceList=new ArrayList();
	private List dedectAdvanceAmntList=new ArrayList();
	private List netAmntList=new ArrayList();
	
	
	private Integer totalEmployee;
	private Double totalSalary;
	
	private Integer totalDaysInMonth;
	
	private String departmentName;
	

	private List<SalaryTempDTO> temp=new ArrayList<SalaryTempDTO>();
	
	private List<SalaryTempDTO> tempDedect=new ArrayList<SalaryTempDTO>();

	private List<SalaryTempDTO> headPayableList=new ArrayList<SalaryTempDTO>();
	
	private List<SalaryTempDTO> headDeductPayableList=new ArrayList<SalaryTempDTO>();
	
	public List<SalaryTempDTO> getHeadPayableList() {
		return headPayableList;
	}
	public void setHeadPayableList(List<SalaryTempDTO> headPayableList) {
		this.headPayableList = headPayableList;
	}
	private List<SalaryLeaveDTO> salaryLeaveDTOList;
	private List<SalaryDetailDTO> salaryDetailDTOList;
	private List<SalaryAttandanceDTO> salaryAttandanceDTOList;
	
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public List getEmployeeIdList() {
		return employeeIdList;
	}
	public void setEmployeeIdList(List employeeIdList) {
		this.employeeIdList = employeeIdList;
	}
	public List getDepartmentIdList() {
		return departmentIdList;
	}
	public void setDepartmentIdList(List departmentIdList) {
		this.departmentIdList = departmentIdList;
	}
	public List getEarningTotalAmntList() {
		return earningTotalAmntList;
	}
	public void setEarningTotalAmntList(List earningTotalAmntList) {
		this.earningTotalAmntList = earningTotalAmntList;
	}
	public List getDedectionTotalAmntList() {
		return dedectionTotalAmntList;
	}
	public void setDedectionTotalAmntList(List dedectionTotalAmntList) {
		this.dedectionTotalAmntList = dedectionTotalAmntList;
	}
	public List getBalAdvanceList() {
		return balAdvanceList;
	}
	public void setBalAdvanceList(List balAdvanceList) {
		this.balAdvanceList = balAdvanceList;
	}
	public List getDedectAdvanceAmntList() {
		return dedectAdvanceAmntList;
	}
	public void setDedectAdvanceAmntList(List dedectAdvanceAmntList) {
		this.dedectAdvanceAmntList = dedectAdvanceAmntList;
	}
	public List getNetAmntList() {
		return netAmntList;
	}
	public void setNetAmntList(List netAmntList) {
		this.netAmntList = netAmntList;
	}
	
	
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
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public List<SalaryDetailDTO> getSalaryDetailDTOList() {
		return salaryDetailDTOList;
	}
	public void setSalaryDetailDTOList(List<SalaryDetailDTO> salaryDetailDTOList) {
		this.salaryDetailDTOList = salaryDetailDTOList;
	}
	public EmployeeDTO getEmployeeDTO() {
		return employeeDTO;
	}
	public void setEmployeeDTO(EmployeeDTO employeeDTO) {
		this.employeeDTO = employeeDTO;
	}
	public List getEmployeeNameList() {
		return employeeNameList;
	}
	public void setEmployeeNameList(List employeeNameList) {
		this.employeeNameList = employeeNameList;
	}
	public Integer getTotalEmployee() {
		return totalEmployee;
	}
	public void setTotalEmployee(Integer totalEmployee) {
		this.totalEmployee = totalEmployee;
	}
	public Double getTotalSalary() {
		return totalSalary;
	}
	public void setTotalSalary(Double totalSalary) {
		this.totalSalary = totalSalary;
	}
	public Integer getTotalDaysInMonth() {
		return totalDaysInMonth;
	}
	public void setTotalDaysInMonth(Integer totalDaysInMonth) {
		this.totalDaysInMonth = totalDaysInMonth;
	}
	
	public List<SalaryTempDTO> getTemp() {
		return temp;
	}
	public void setTemp(List<SalaryTempDTO> temp) {
		this.temp = temp;
	}
	public List<SalaryTempDTO> getTempDedect() {
		return tempDedect;
	}
	public void setTempDedect(List<SalaryTempDTO> tempDedect) {
		this.tempDedect = tempDedect;
	}
	public List<SalaryLeaveDTO> getSalaryLeaveDTOList() {
		return salaryLeaveDTOList;
	}
	public void setSalaryLeaveDTOList(List<SalaryLeaveDTO> salaryLeaveDTOList) {
		this.salaryLeaveDTOList = salaryLeaveDTOList;
	}
	public List<SalaryAttandanceDTO> getSalaryAttandanceDTOList() {
		return salaryAttandanceDTOList;
	}
	public void setSalaryAttandanceDTOList(
			List<SalaryAttandanceDTO> salaryAttandanceDTOList) {
		this.salaryAttandanceDTOList = salaryAttandanceDTOList;
	}
	public List<SalaryTempDTO> getHeadDeductPayableList() {
		return headDeductPayableList;
	}
	public void setHeadDeductPayableList(List<SalaryTempDTO> headDeductPayableList) {
		this.headDeductPayableList = headDeductPayableList;
	}
	public Integer getSalaryYearInt() {
		return salaryYearInt;
	}
	public void setSalaryYearInt(Integer salaryYearInt) {
		this.salaryYearInt = salaryYearInt;
	}
	
}
