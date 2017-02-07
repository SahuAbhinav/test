package com.advanz.erp.masters.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.common.DataUtility;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.EmployeeEntity;
import com.advanz.erp.masters.entity.jpa.LeaveTypeMastEntity;
import com.advanz.erp.masters.entity.jpa.SalaryAttendanceEntity;
import com.advanz.erp.masters.entity.jpa.SalaryDetailEntity;
import com.advanz.erp.masters.entity.jpa.SalaryLeaveEntity;
import com.advanz.erp.masters.entity.jpa.SalaryMasterEntity;
import com.advanz.erp.masters.model.AdvanceAmountDTO;
import com.advanz.erp.masters.model.EmployeeDTO;
import com.advanz.erp.masters.model.EmployeeSalaryDetDTO;
import com.advanz.erp.masters.model.LeaveTypeMastDTO;
import com.advanz.erp.masters.model.SalaryAttandanceDTO;
import com.advanz.erp.masters.model.SalaryDetailDTO;
import com.advanz.erp.masters.model.SalaryHeadDTO;
import com.advanz.erp.masters.model.SalaryLeaveDTO;
import com.advanz.erp.masters.model.SalaryMasterDTO;
import com.advanz.erp.masters.model.SalaryTempDTO;
import com.advanz.erp.masters.model.msg.AdvanceAmountInputMessage;
import com.advanz.erp.masters.model.msg.AdvanceAmountOutputMessage;
import com.advanz.erp.masters.model.msg.EmployeeInputMessage;
import com.advanz.erp.masters.model.msg.EmployeeOutputMessage;
import com.advanz.erp.masters.model.msg.LeaveTypeMastOutputMessage;
import com.advanz.erp.masters.model.msg.SalaryHeadInputMessage;
import com.advanz.erp.masters.model.msg.SalaryHeadOutputMessage;
import com.advanz.erp.masters.model.msg.SalaryMasterInputMessage;
import com.advanz.erp.masters.model.msg.SalaryMasterOutputMessage;
import com.advanz.erp.masters.service.business.IAdvanceAmountService;
import com.advanz.erp.masters.service.business.IAttandanceMasterService;
import com.advanz.erp.masters.service.business.IEmployeeService;
import com.advanz.erp.masters.service.business.ILeaveTypeMastService;
import com.advanz.erp.masters.service.business.IMastersService;
import com.advanz.erp.masters.service.business.ISalaryHeadService;
import com.advanz.erp.masters.service.business.ISalaryMasterService;
import com.advanz.erp.masters.storage.IStorageSalaryDAO;

public class SalaryMasterServiceImpl implements ISalaryMasterService {

	public static final String CREATE_SALARY_MASTER = "CreateSalaryMaster";
	public static final String UPDATE_SALARY_MASTER = "UpdateSalaryMaster";
	public static final String DELETE_SALARY_MASTER = "DeleteSalaryMaster";
	public static final String FIND_SALARY_MASTER_BY_ID = "FindSalaryMasterById";
	public static final String FIND_ALL_SALARY_MASTER = "FindAllSalaryMasters";
	public static final String SEARCH_SALARY_MASTER = "SearchSalaryMasters";
	
	public String flowId = "";
	private static final Logger logger = LoggerFactory.getLogger(SalaryMasterServiceImpl.class);
	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do

	@Autowired
	public IStorageSalaryDAO storageSalaryDAO;

	public SalaryMasterInputMessage salaryMasterInputMessage;

	public SalaryMasterOutputMessage salaryMasterOutputMessage;

	@Autowired
	public ISalaryHeadService headService;
	
	@Autowired
	public IAdvanceAmountService advanceAmountService;
		
	@Autowired
	public IEmployeeService employeeService;
	
	@Autowired
	public ILeaveTypeMastService leaveTypeMasterService;
	@Autowired
	public IAttandanceMasterService sttandanceMasterService;
	
	@Autowired
	public IMastersService masterService;
	@Override
	public SalaryMasterOutputMessage createSalaryMaster(SalaryMasterInputMessage salaryMasterInputMessage) {

		flowId = CREATE_SALARY_MASTER;
		// assign the message to the class level variable.
		this.salaryMasterInputMessage = salaryMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return salaryMasterOutputMessage;
	}

	@Override
	public SalaryMasterOutputMessage updateSalaryMaster(SalaryMasterInputMessage salaryMasterInputMessage) {

		flowId = UPDATE_SALARY_MASTER;
		// assign the message to the class level variable.
		this.salaryMasterInputMessage = salaryMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return salaryMasterOutputMessage;
	}

	@Override
	public SalaryMasterOutputMessage deleteSalaryMaster(SalaryMasterInputMessage salaryMasterInputMessage) {
		flowId = DELETE_SALARY_MASTER;
		// assign the message to the class level variable.
		this.salaryMasterInputMessage = salaryMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return salaryMasterOutputMessage;

	}

	@Override
	public SalaryMasterOutputMessage findSalaryMasterById(SalaryMasterInputMessage salaryMasterInputMessage) {
		flowId = FIND_SALARY_MASTER_BY_ID;
		// assign the message to the class level variable.
		this.salaryMasterInputMessage = salaryMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return salaryMasterOutputMessage;

	}

	@Override
	public SalaryMasterOutputMessage findAllSalaryMasters() {
		flowId = FIND_ALL_SALARY_MASTER;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return salaryMasterOutputMessage;
	}
	
	@Override
	public SalaryMasterOutputMessage search(SalaryMasterInputMessage salaryMasterInputMessage) {
		flowId = SEARCH_SALARY_MASTER;
		this.salaryMasterInputMessage = salaryMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return salaryMasterOutputMessage;

	}
	
	
	
	
	@Override
	public boolean validateInput() {

		if (CREATE_SALARY_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_SALARY_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_SALARY_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_SALARY_MASTER_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_SALARY_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (SEARCH_SALARY_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		return false;
	}

	@Override
	public void performBusinessLogic() {

		SalaryMasterEntity salaryMasterEntity = new SalaryMasterEntity();
		
		List<SalaryDetailDTO> salaryDetailDTOList=null;
		List<SalaryLeaveDTO> salaryLeaveDTOList=null;
		List<SalaryAttandanceDTO> salaryAttandanceDTOList=null;
		SalaryMasterDTO salaryMasterDTO =null;
		if (salaryMasterInputMessage != null) {
			salaryMasterDTO = salaryMasterInputMessage.getSalaryMasterDTO();
			
			if (salaryMasterDTO != null) {
				BeanUtils.copyProperties(salaryMasterDTO, salaryMasterEntity);
			//	PartyDTO partyDTO=issueMasterDTO.getPartyDTO();
				salaryDetailDTOList=salaryMasterDTO.getSalaryDetailDTOList();
				if(salaryDetailDTOList!=null && salaryDetailDTOList.size()>0){
					List<SalaryDetailEntity>salaryDetailEntity=convertSalaryDetailDtoTOSalaryDetailEntity(salaryDetailDTOList,salaryMasterDTO);	
					salaryMasterEntity.setSalaryDetailEntity(salaryDetailEntity);
				}
				salaryLeaveDTOList=salaryMasterDTO.getSalaryLeaveDTOList();
				if(salaryLeaveDTOList!=null && salaryLeaveDTOList.size()>0){
					List<SalaryLeaveEntity> salaryLeaveEntityList=convertSalaryLeaveEtoToEntity(salaryLeaveDTOList);
					salaryMasterEntity.setSalaryLeaveEntity(salaryLeaveEntityList);
				}
				salaryAttandanceDTOList=salaryMasterDTO.getSalaryAttandanceDTOList();
				if(salaryAttandanceDTOList!=null && salaryAttandanceDTOList.size()>0){
					List<SalaryAttendanceEntity> salaryAttendanceEntity=convertSalaryAttendanceDtoToEntity(salaryAttandanceDTOList);
					salaryMasterEntity.setSalaryAttendanceEntity(salaryAttendanceEntity);
				}
		 }
		}

		if (CREATE_SALARY_MASTER.equals(flowId)) {
			// check duplicate finishedGoodsMaster name
			List<SalaryMasterEntity> list = null;
			System.out.println("CREATE SALARY MASTER........................");
		//	issueOutputMessage = new IssueOutputMessage();
			//if (list != null && list.size() > 0) {
				List departList= salaryMasterDTO.getDepartmentIdList();
				List emplIdList= salaryMasterDTO.getEmployeeIdList();
				//List balAdvanceList= salaryMasterDTO.getBalAdvanceList();
				//List dedectAdvanceAmntList= salaryMasterDTO.getDedectAdvanceAmntList();
				List netSalaryList= salaryMasterDTO.getNetAmntList();
				List<SalaryTempDTO> earningSalaryHeadList =salaryMasterDTO.getTemp();
				List<SalaryTempDTO> earningSalaryHeadPayableList =salaryMasterDTO.getHeadPayableList();
				List<SalaryTempDTO> deductingSalaryHeadList =salaryMasterDTO.getTempDedect();
				List<SalaryTempDTO> deductingSalaryPayableHeadList =salaryMasterDTO.getHeadDeductPayableList();
								
				salaryDetailDTOList=new ArrayList<SalaryDetailDTO>();
				for(int i=0;i<departList.size();i++){
					
					EmployeeDTO employeeDTO = new EmployeeDTO();
					
					int empId=Integer.parseInt(emplIdList.get(i).toString());
					 employeeDTO.setEmployeeId(empId);
					Double netSalary=null;
					try {
						netSalary = Double.parseDouble(netSalaryList.get(i).toString());
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
							
					//Get Employee
					EmployeeInputMessage employeeInputMessage=new EmployeeInputMessage();
					employeeInputMessage.setEmployeeDTO(employeeDTO);
					EmployeeOutputMessage employeeOutputMessage= employeeService.findEmployeeById(employeeInputMessage);
					List<EmployeeDTO> employeeList=employeeOutputMessage.getEmployeeDTOList();
					EmployeeDTO employeeDTO1=null;
					if(employeeList!=null){
					employeeDTO1=employeeList.get(0);
					}
			//Combine earning and deduction Head bise Amount 		
				List<EmployeeSalaryDetDTO> empsl= employeeDTO1.getEmployeeSalaryDetDTOList();
				SalaryTempDTO eraningAmt=	earningSalaryHeadList.get(i);
				SalaryTempDTO earningPayableAmt=earningSalaryHeadPayableList.get(i);
				
				List<Double> tempList1 =	eraningAmt.getTempList();
				List<Double> tempList2 =	earningPayableAmt.getSalaryHeadPayableList();
				SalaryTempDTO dedectingAmt=  deductingSalaryHeadList.get(i);
				SalaryTempDTO dedectingPayableAmt=  deductingSalaryPayableHeadList.get(i);
				
				List<Double> l= dedectingAmt.getTempDedectList();
				eraningAmt.setTempDedectList(l);
				
				for(int e=0;e<l.size();e++){
					tempList1.add(l.get(e)); 
				}
				List<Double> l2= dedectingPayableAmt.getSalaryDeductPayableList();
				earningPayableAmt.setSalaryDeductPayableList(l2);
				for(int e=0;e<l2.size();e++){
					tempList2.add(l2.get(e)); 
				}
				
				try {
					for(int j=0;j<empsl.size();j++){
						SalaryDetailDTO detailDTO =new SalaryDetailDTO();
						detailDTO.setNetSalary(netSalary);
							  EmployeeSalaryDetDTO detDTO= empsl.get(j);
							
						  detailDTO.setHeadType(detDTO.getHeadType());
							List<Double> tempList =	eraningAmt.getTempList();
							List<Double> payableList=earningPayableAmt.getSalaryHeadPayableList();
							//for(int q=0;q<tempList.size();q++){
							Double as=tempList.get(j);
							
							detailDTO.setHeadAmount(as);
							Double payable=payableList.get(j);
							detailDTO.setHeadAmountPayable(payable);
							  
							  detailDTO.setHeadId(Integer.parseInt(detDTO.getSalaryId()));
							    employeeDTO.setEmployeeId(empId);
								detailDTO.setEmployeeDTO(employeeDTO);
								detailDTO.setSalaryTranAutoNo(salaryMasterDTO.getSalaryTranAutoNo());
							   salaryDetailDTOList.add(detailDTO);
						  }
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				}
				salaryMasterDTO.setSalaryDetailDTOList(salaryDetailDTOList);
				List<SalaryLeaveEntity> leaveEntities=convertSalaryLeaveEtoToEntity(salaryMasterDTO.getSalaryLeaveDTOList());
				List<SalaryDetailEntity>salaryDetailEntity=convertSalaryDetailDtoTOSalaryDetailEntity(salaryDetailDTOList,salaryMasterDTO);	
				salaryMasterEntity.setSalaryLeaveEntity(leaveEntities);
				salaryMasterEntity.setSalaryDetailEntity(salaryDetailEntity);
				
				storageSalaryDAO.create(salaryMasterEntity);
				//ErrorDTO errorDTO = new ErrorDTO("1", indentMasterEntity.getIndentNumber()+" Indent Number is already exist,it can't be duplicate ");
			    ErrorListDTO errorListDTO = new ErrorListDTO();
				//errorListDTO.addError(errorDTO);
				salaryMasterOutputMessage = new SalaryMasterOutputMessage();
				salaryMasterOutputMessage.setErrorListDTO(errorListDTO);
			  
			/*}else{
				 storageSalaryDAO.create(salaryMasterEntity);
			}*/
			
			
		} else if (UPDATE_SALARY_MASTER.equals(flowId)) {
			
			storageSalaryDAO.update(salaryMasterEntity);
	
		} 
		else if (DELETE_SALARY_MASTER.equals(flowId)) {
			storageSalaryDAO.delete(salaryMasterEntity);
		}
		
		else if (FIND_SALARY_MASTER_BY_ID.equals(flowId)) {
			salaryMasterDTO = salaryMasterInputMessage.getSalaryMasterDTO();
		List<SalaryMasterEntity>list=	storageSalaryDAO.findById(salaryMasterDTO.getSalaryTranAutoNo());
		//popUpList(list);
		
		List<SalaryMasterDTO> l= convertSalaryEntityToSalaryDTO(list);
		
		if(l!=null && l.size()>0){
			
			///// To add Employee detail In header Leave Type And earning and deduction Head(Or created Header)
			 List<String> header =new ArrayList<String>();
			 	header.add("Month");
	    		header.add("Day in Month");
	    		header.add("EmployeeName");
	    	    header.add("EmployeeCode");
	    	    header.add("Desination");
	    	    header.add("Date Of Joining");
	    	    header.add("CL Opening Balance");
	    	    header.add("CL Alloted In Month");
		 	   LeaveTypeMastOutputMessage leaveTypeMastOutputMessage= leaveTypeMasterService.findAllLeaveTypeMasts();
		 	 List<LeaveTypeMastDTO> leaveTypeList=leaveTypeMastOutputMessage.getLeaveTypeMastDTOList();
		 	 for(int i=0;i<leaveTypeList.size();i++){
		 		 LeaveTypeMastDTO leaveTypeMastDTO=leaveTypeList.get(i);
		 		// header.add(leaveTypeMastDTO.getLeaveName());
		 	 }
		 	 header.add("Working Days");
	    	 header.add("Holiday+WeekOff");
	    	 header.add("Present");
	    	 header.add("ABSENT");
	    	 header.add("CL Availed");
	    	 header.add("Loss Of Pay");
	    	 header.add("Extra");
	 	     header.add("Payable Days");
	 	     header.add("CL Closing Balance");
		 	
		 SalaryHeadOutputMessage headOutputMessage=headService.findAllSalaryHeads();
		 List<SalaryHeadDTO> salaryHeadList=headOutputMessage.getSalaryHeadDTOList();
		 for(int i=0;i<salaryHeadList.size();i++){
				SalaryHeadDTO headDTO=salaryHeadList.get(i);
			if(headDTO.getHeadType().equalsIgnoreCase("E")){
				header.add(headDTO.getSalaryHeadName());
				header.add(headDTO.getSalaryHeadName()+"Payable");
			  }
			}	
		 	header.add("Monthly Salary");
		    header.add("Other Allowance");
		    header.add("Net Amount");
		    header.add("PF Flag");
		    header.add("Base Sal For PF");
		 for(int i=0;i<salaryHeadList.size();i++){
				SalaryHeadDTO headDTO=salaryHeadList.get(i);
			if(headDTO.getHeadType().equalsIgnoreCase("D")){
				header.add(headDTO.getSalaryHeadName());
			  }
			}	
		 	header.add("Employer Contr.");
		    header.add("Employee Contr.");
		    header.add("Total Dedc"); 
		    header.add("Adv.Adjustment");
		   // header.add("Bal. Advance");
		    //header.add("Ded.adv./Other");
		    header.add("Net Salary");
		 
			
			// TO get advance amount dedection	
			SalaryMasterDTO masterDTO=l.get(0);
			
			List detailList=storageSalaryDAO.getAdvanceAmount(masterDTO.getSalaryTranAutoNo());
			List netSalaryList=storageSalaryDAO.getNetSalary(masterDTO.getSalaryTranAutoNo());
			List<EmployeeDTO> emplList=new ArrayList<EmployeeDTO>();
			// List balAdvanceList=new ArrayList();
			// List dedectAdvanceAmntList=new ArrayList();
			 List netSalaryL=new ArrayList();
			for(int i=0;i<detailList.size();i++){
			Object[] detailEntity=(Object[])detailList.get(i);
			//Double advanceDedectAmount=(Double)(detailEntity[0]);
			//Double advanceAmount=(Double)(detailEntity[1]);
			Integer employeeId=Integer.parseInt(detailEntity[2].toString());
			Date minDate=masterDTO.getMinDate();
		    Date maxDate=masterDTO.getMaxDate();
		    Calendar cal1=Calendar.getInstance();
		    cal1.setTime(minDate);
		   
		    
		   	Double netSalary=(Double)netSalaryList.get(i);
			EmployeeDTO empDTO=new EmployeeDTO();
			empDTO.setEmployeeId(employeeId);
			EmployeeInputMessage empInputMessage=new EmployeeInputMessage();
			empInputMessage.setEmployeeDTO(empDTO);
			EmployeeOutputMessage employeeOutputMessage=employeeService.findEmployeeById(empInputMessage);
			List<EmployeeDTO> employeeList=employeeOutputMessage.getEmployeeDTOList();
			int salaryMonth=0;
			try {
				for(int k=0;k<employeeList.size();k++){
					EmployeeDTO employeeDTO= employeeList.get(k);
					List<EmployeeSalaryDetDTO> empsl= employeeDTO.getEmployeeSalaryDetDTOList();
					List<EmployeeSalaryDetDTO> empsdl= employeeDTO.getEmployeeSalaryDetDTOListDe();
					salaryMonth= getMonthNumber(masterDTO.getSalaryMonth());
					Map map= getFirstDateAndLastDate(salaryMonth);
					Date firstDate=(Date)map.get("firstDate");
					Date lastDate=(Date)map.get("lastDate");
					 int crntMonth = getMonthNumber(masterDTO.getSalaryMonth())+1;
					 
				    Calendar cal=Calendar.getInstance();
				    cal.setTime(firstDate);
				    
				     Calendar calss = Calendar.getInstance();
					calss.add(Calendar.MONTH, -1);
					calss.setTime(employeeDTO.getJoinDate());
					int joinMonth = calss.get(Calendar.MONTH)+1;
					int joinDay = calss.get(Calendar.DAY_OF_MONTH);
					int joinYear = calss.get(Calendar.YEAR);
				    
					int month= getMonthNumber(masterDTO.getSalaryMonth());
					int monthMaxDays= getDayOfMonth(month);
					
				Double num= 0.0;
			
				 for(int j=0;j<leaveTypeList.size();j++){
					LeaveTypeMastDTO leaveTypeMastDTO=leaveTypeList.get(j);
					Double nu= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), leaveTypeMastDTO.getLeaveName(), firstDate, lastDate);
					num =num+nu;
					employeeDTO.getLeaveTypeList().add(nu);
				 } 			 
				  
				 try {
					  String designationName= masterService.getMasterNameById(employeeDTO.getDesignation());
					  employeeDTO.setDesignationName(designationName);
					} catch (Exception e) {
					  e.printStackTrace();}
				 
			  String previousMonthName=null;
			  int crntYear=0;
				
				if(crntMonth==1)
					 {previousMonthName=DataUtility.getMonthName(11);
					 }
				 else
				    {previousMonthName=DataUtility.getMonthName(crntMonth-2);
				    }
				// System.out.println("PREVIOUS Month Name..........."+previousMonthName);
				 crntYear=cal1.get(Calendar.YEAR);
				  AdvanceAmountInputMessage inputMessage=new AdvanceAmountInputMessage();
				    AdvanceAmountDTO amountDTO=new AdvanceAmountDTO();
				    EmployeeDTO advanceEmp=new EmployeeDTO();
				    advanceEmp.setEmployeeId(employeeDTO.getEmployeeId());
				    amountDTO.setEmployeeDTO(advanceEmp);
				    amountDTO.setFromDate(minDate);
				    amountDTO.setToDate(maxDate);
				    inputMessage.setAdvanceAmountDTO(amountDTO);
				    
				    AdvanceAmountOutputMessage outputMessage=advanceAmountService.getByLastTransactionDate(inputMessage);
				    if(outputMessage!=null)
				    {
				    	List<AdvanceAmountDTO> dtos=outputMessage.getAdvanceAmountDTOList();
				    	if(dtos!=null && dtos.size()>0)
				    	{
				    		if(dtos.get(0).getTransactionType().equalsIgnoreCase("Deduction"))
					    	   {employeeDTO.setClAdvanceAdj(-dtos.get(0).getAmount());}
					    	else{ employeeDTO.setClAdvanceAdj(dtos.get(0).getAmount());}
				    	}
				    	else{employeeDTO.setClAdvanceAdj(0.0);}
				    }
				    else{employeeDTO.setClAdvanceAdj(0.0);
				    }
				 
				  Double opnLeaveBal= 0.0;
				  Double allowLeaveBal= 0.0;
				  Double availLeavBal=0.0;
				  List<SalaryLeaveEntity> entLeaveEntities=storageSalaryDAO.findByMonthEmpIdLeaveId(previousMonthName, employeeDTO.getEmployeeId(), employeeDTO.getEmployeeLeavesDTOList().get(0).getLeaveId());
				  if(entLeaveEntities!=null && entLeaveEntities.size()>0)
					 {
					 List<SalaryLeaveDTO> leaveDTOList=convertSalaryEntitytoDto(entLeaveEntities);
				    for(SalaryLeaveDTO salaryLeaveDTO:leaveDTOList)
					  {				     	 
				     	 opnLeaveBal=salaryLeaveDTO.getClosingLeaveBalance();
				     	 employeeDTO.setOpeningBalanceLeave(opnLeaveBal);
				     }   }
					 else
					 {
						 opnLeaveBal=employeeDTO.getOpeningClBalance();
						 employeeDTO.setOpeningBalanceLeave(opnLeaveBal);
				 	 }
				   try {
					   
					String designationName= masterService.getMasterNameById(employeeDTO.getDesignation());
					   employeeDTO.setDesignationName(designationName);
				} catch (Exception e) {
					e.printStackTrace();
				}
				   
				 
				  	
				 double availedLeave=0.0;
					double absHalf=0.0;
					double hollyDay=0.0;
					double workingDays=0.0;
					double extraHalf=0.0;
					double extraFull=0.0;
					double lossOfPay=0.0;
			   
					 extraHalf= sttandanceMasterService.countByHalfDay(employeeDTO.getEmployeeId(), "EXTRA", minDate, maxDate);
					 extraFull= sttandanceMasterService.countByFullDay(employeeDTO.getEmployeeId(), "EXTRA", minDate, maxDate);
					 hollyDay= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), "Holiday", minDate, maxDate);
					 double hollyWk= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), "weakOff", minDate, maxDate);
					 extraHalf=extraHalf*2;
					
					 hollyWk=hollyWk+hollyDay+extraHalf+extraFull;
					 
					 workingDays=monthMaxDays-hollyWk;
					 if(crntMonth==joinMonth && joinYear==crntYear && joinDay>1)
					 {
						 employeeDTO.getLeaveTypeList().add(workingDays-(joinDay-1));
						 employeeDTO.setWorkingDays(workingDays-(joinDay-1));
					 }
					 else
					 {
						 employeeDTO.getLeaveTypeList().add(workingDays);
						 employeeDTO.setWorkingDays(workingDays);
					 }
					 
					employeeDTO.getLeaveTypeList().add(hollyWk);
					employeeDTO.setHollyWeekOff(hollyWk);
					double num4= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), "PRESENT", minDate, maxDate);
					employeeDTO.getLeaveTypeList().add(num4);
					employeeDTO.setPresentDays(num4);
					double num3= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), "ABSENT", minDate, maxDate);
					 absHalf= sttandanceMasterService.countByHalfDay(employeeDTO.getEmployeeId(), "PRESENT", minDate, maxDate);
					 double extraWork= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), "EXTRA", minDate, maxDate);
					 Double pabayelDay=(hollyWk+num4)+extraWork+num+availedLeave;
					 //absHalf=(absHalf/2);
					  /*if(crntMonth==joinMonth && joinYear==crntYear && joinDay>10)
						 {
							 employeeDTO.getEmployeeLeavesDTOList().get(0).setAllowDays(allowLeaveBal);
						 }
						 else
						 {
							 allowLeaveBal=employeeDTO.getEmployeeLeavesDTOList().get(0).getAllowDays();
						 }*/
					 if(pabayelDay<20)
					 {
						 employeeDTO.getEmployeeLeavesDTOList().get(0).setAllowDays(allowLeaveBal);
					 }
					 else{ allowLeaveBal=employeeDTO.getEmployeeLeavesDTOList().get(0).getAllowDays();}
					 availLeavBal=allowLeaveBal+opnLeaveBal;
					 if(employeeDTO.getAvailedLeave()!=null)
					 {
						 availLeavBal=availLeavBal+employeeDTO.getAvailedLeave();
					 }
					
					 
					num3=num3+absHalf;		
					employeeDTO.getLeaveTypeList().add(num3);
					employeeDTO.setAbsentDays(num3);
					if(availLeavBal>num3)
					{
						
						employeeDTO.setClosingBalanceLeave(availLeavBal-num3);
						if(num3>0.0){
							availedLeave=num3;}
						 	employeeDTO.setAvailedLeave(availedLeave);//for hidden field store in availed leav
						 	employeeDTO.getLeaveTypeList().add(availedLeave);
						 	}
					else
					  {
							employeeDTO.setClosingBalanceLeave(0.0);
							availedLeave=availLeavBal;
							lossOfPay=num3-availLeavBal;
							employeeDTO.setAvailedLeave(availedLeave);//for hidden field store in availed leav
							employeeDTO.getLeaveTypeList().add(availedLeave);
						}
					employeeDTO.getLeaveTypeList().add(lossOfPay);
					employeeDTO.setLossOfPay(lossOfPay);
					
					employeeDTO.getLeaveTypeList().add(extraWork);
					employeeDTO.setExtraWorkDays(extraWork);
					//Double pabayelDay=(monthMaxDays-num3)+num1;
					
					pabayelDay=pabayelDay+availedLeave;
									
					employeeDTO.getLeaveTypeList().add(pabayelDay);
					employeeDTO.setPayableDays(pabayelDay);
					
					 for(int j=0;j<empsl.size();j++){
						  EmployeeSalaryDetDTO detDTO= empsl.get(j);
						  if(detDTO.getHeadType().equalsIgnoreCase("E")){
							//To check earning Head Type is(constant,fixed or variable)...........
							  SalaryHeadDTO headDTO= getSalaryHeadDTO(Integer.parseInt(detDTO.getSalaryId()));
							  employeeDTO.getEarningheadTypeList().add(headDTO.getType());
							//TO get Earning Head Amount from salary detail Table
							  Double earnAmount=0.0;try{
							   earnAmount= storageSalaryDAO.getHeadAmount(masterDTO.getSalaryTranAutoNo(), Integer.parseInt(detDTO.getSalaryId()), employeeId);
							  }catch (Exception e) {
							} if(earnAmount==null){
								earnAmount=0.0;
							  }
							   employeeDTO.getEarningHeadList().add(earnAmount);
							   						  
						  }if(detDTO.getHeadType().equalsIgnoreCase("D")){
							//To check deduction Head Type is(constant,fixed or variable)...........
							  SalaryHeadDTO headDTO= getSalaryHeadDTO(Integer.parseInt(detDTO.getSalaryId()));     
							  employeeDTO.getDeductionheadTypeList().add(headDTO.getType());
							 //TO get Deduction Head Amount from salary detail Table
							  Double dedectAmount=0.0;try{
								  dedectAmount= storageSalaryDAO.getHeadAmount(masterDTO.getSalaryTranAutoNo(), Integer.parseInt(detDTO.getSalaryId()), employeeId);
								  }catch (Exception e) {}
								  if(dedectAmount==null){
									  dedectAmount=0.0;
								  }
							 employeeDTO.getDedecationHeadList().add(dedectAmount);
						  
						  }}
					
					// balAdvanceList.add(advanceAmount);
					// dedectAdvanceAmntList.add(advanceDedectAmount);
					 netSalaryL.add(netSalary);
					 emplList.add(employeeDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			SalaryMasterDTO salaryMasterDTO2 = new SalaryMasterDTO();
			salaryMasterDTO2.setMinDate(masterDTO.getMinDate());
			salaryMasterDTO2.setMaxDate(masterDTO.getMaxDate());
			salaryMasterDTO2.setApprovedFlag(masterDTO.getApprovedFlag());
			salaryMasterDTO2.setSalaryMonth(masterDTO.getSalaryMonth());
			salaryMasterDTO2.setSalaryYear(masterDTO.getSalaryYear());
			salaryMasterDTO2.setSalaryYearInt(cal1.get(Calendar.YEAR));
			//salaryMasterDTO2.setBalAdvanceList(balAdvanceList);
			//salaryMasterDTO2.setDedectAdvanceAmntList(dedectAdvanceAmntList);
			salaryMasterDTO2.setNetAmntList(netSalaryL);
			
			int mn= getMonthNumber(masterDTO.getSalaryMonth());
			int totalDaysInMonth= getDayOfMonth(mn);
			salaryMasterDTO2.setTotalDaysInMonth(totalDaysInMonth);
			   
			Map map=new HashMap();
			map.put("salaryMasterDTO2", salaryMasterDTO2);
			map.put("header", header);
			map.put("employeeList", emplList);
			map.put("deptId", masterDTO.getDepartmentId());
			
			salaryMasterOutputMessage = new SalaryMasterOutputMessage();
			salaryMasterOutputMessage.setMap(map);
			}
		   }
		} else if (FIND_ALL_SALARY_MASTER.equals(flowId)) {
			List<SalaryMasterEntity> list = storageSalaryDAO.load();
			popUpList(list);			
		}
		else if (SEARCH_SALARY_MASTER.equals(flowId)) {
			//IndentMasterDTO inentDTO=indentInputMessage.getIndentMasterDTO();
			//List<IndentMasterEntity> list = storageIndentDAO.search(inentDTO.getIndentNumber(),inentDTO.getFromDate(),inentDTO.getToDate(),inentDTO.getItemName());
			//popUpList(list);			
		}
		
		
	}
	void popUpList(List<SalaryMasterEntity> list) {
		salaryMasterOutputMessage = new SalaryMasterOutputMessage();
		// set the data to the output message.
		if (list != null) {
			List<SalaryMasterDTO> resultList = new ArrayList<SalaryMasterDTO>();
			SalaryMasterDTO salaryMasterDTO;
			for (SalaryMasterEntity entity : list) {
				salaryMasterDTO = new SalaryMasterDTO();
				// Spring				
				BeanUtils.copyProperties(entity, salaryMasterDTO);
				List<SalaryDetailEntity> salaryDetailEntityList=entity.getSalaryDetailEntity();
				List<SalaryDetailDTO> salaryDetailDTOList=new ArrayList<SalaryDetailDTO>();
				if(salaryDetailEntityList!=null && salaryDetailEntityList.size()>0){
					for(SalaryDetailEntity salaryDetailEntity:salaryDetailEntityList){
						SalaryDetailDTO salaryDetailDTO=new SalaryDetailDTO();
						copyObject(salaryDetailEntity,salaryDetailDTO);	
						salaryDetailDTOList.add(salaryDetailDTO);
					}
				}
				salaryMasterDTO.setSalaryDetailDTOList(salaryDetailDTOList);				
				resultList.add(salaryMasterDTO);
			}
			salaryMasterOutputMessage.setSalaryMasterDTOList(resultList);
		   }}
	public List<SalaryMasterDTO> convertSalaryEntityToSalaryDTO(List<SalaryMasterEntity> list) {
		salaryMasterOutputMessage = new SalaryMasterOutputMessage();
		// set the data to the output message.
		List<SalaryMasterDTO> resultList = new ArrayList<SalaryMasterDTO>();
		if (list != null) {
			SalaryMasterDTO salaryMasterDTO;
			for (SalaryMasterEntity entity : list) {
				salaryMasterDTO = new SalaryMasterDTO();
				// Spring				
				BeanUtils.copyProperties(entity, salaryMasterDTO);
				List<SalaryDetailEntity> salaryDetailEntityList=entity.getSalaryDetailEntity();
				
				List<SalaryDetailDTO> salaryDetailDTOList=new ArrayList<SalaryDetailDTO>();
				if(salaryDetailEntityList!=null && salaryDetailEntityList.size()>0){
					for(SalaryDetailEntity salaryDetailEntity:salaryDetailEntityList){
						SalaryDetailDTO salaryDetailDTO=new SalaryDetailDTO();
						copyObject(salaryDetailEntity,salaryDetailDTO);	
						salaryDetailDTOList.add(salaryDetailDTO);
					}
				}
				List<SalaryLeaveEntity> leaveEntityList=entity.getSalaryLeaveEntity();
				List<SalaryLeaveDTO> salaryLeaveDTOList=new ArrayList<SalaryLeaveDTO>();
				if(leaveEntityList!=null && leaveEntityList.size()>0){
					for(SalaryLeaveEntity salaryLeaveEntity:leaveEntityList){
						SalaryLeaveDTO salaryLeaveDTO=new SalaryLeaveDTO();
						copyObject(salaryLeaveEntity,salaryLeaveDTO);	
						salaryLeaveDTOList.add(salaryLeaveDTO);
					}
				}
				List<SalaryAttendanceEntity> salaryAttendanceEntityList=entity.getSalaryAttendanceEntity();
				List<SalaryAttandanceDTO> salaryAttandanceDtoList=new ArrayList<SalaryAttandanceDTO>();
				if(salaryAttendanceEntityList!=null && salaryAttendanceEntityList.size()>0){
					for(SalaryAttendanceEntity salaryAttendanceEntity:salaryAttendanceEntityList){
						SalaryAttandanceDTO salaryAttandanceDTO=new SalaryAttandanceDTO();
						copyObject(salaryAttendanceEntity,salaryAttandanceDTO);	
						salaryAttandanceDtoList.add(salaryAttandanceDTO);
					}
				}
				salaryMasterDTO.setSalaryLeaveDTOList(salaryLeaveDTOList);
				salaryMasterDTO.setSalaryDetailDTOList(salaryDetailDTOList);
				salaryMasterDTO.setSalaryAttandanceDTOList(salaryAttandanceDtoList);
				
				resultList.add(salaryMasterDTO);
			}
			salaryMasterOutputMessage.setSalaryMasterDTOList(resultList);
		   }
		return resultList;
	}

	@Override
	public void formatOutput() {

		
	}
	
	private List<SalaryDetailEntity> convertSalaryDetailDtoTOSalaryDetailEntity(List<SalaryDetailDTO>dtoList,SalaryMasterDTO salaryMasterDTO){
		logger.info("convertSalaryDetailDtoTOSalaryDetailMasterEntity       Deatil dtoList  = "+ dtoList);	
		List<SalaryDetailEntity> entityList=new ArrayList<SalaryDetailEntity>();
		for(SalaryDetailDTO dto:dtoList){
			SalaryDetailEntity entity=new SalaryDetailEntity();
			copyObject(dto, entity);
			
			if(salaryMasterDTO.getCreatedUserId()!=null){
				entity.setCreatedUserId(salaryMasterDTO.getCreatedUserId());
			}if(salaryMasterDTO.getModifiedUserId()!=null){
				entity.setModifiedUserId(salaryMasterDTO.getModifiedUserId());
			}
			if(dto.getEmployeeDTO()!=null){
				EmployeeEntity employeeEntity=new EmployeeEntity();
				copyObject(dto.getEmployeeDTO(), employeeEntity);
				entity.setEmployeeEntity(employeeEntity); 
			}
			
			
			entityList.add(entity);
		}
		return entityList;
		
	}
	
	private List<SalaryLeaveEntity> convertSalaryLeaveEtoToEntity(List<SalaryLeaveDTO> leaveDTOList){
		logger.info("convertSalaryLeaveEtoToEntity leaveDTOList dtoList  = "+ leaveDTOList);	
		List<SalaryLeaveEntity> entityList=new ArrayList<SalaryLeaveEntity>();
		for(SalaryLeaveDTO dto:leaveDTOList){
			SalaryLeaveEntity entity=new SalaryLeaveEntity();
			copyObject(dto, entity);
			entityList.add(entity);
		}
		return entityList;
	}
	
	private List<SalaryAttendanceEntity> convertSalaryAttendanceDtoToEntity(List<SalaryAttandanceDTO> salaryAttandanceDtoList){
		logger.info("convertSalaryAttendanceDtoToEntity salaryAttandanceDtoList   = "+ salaryAttandanceDtoList);	
		List<SalaryAttendanceEntity> entityList=new ArrayList<SalaryAttendanceEntity>();
		for(SalaryAttandanceDTO dto:salaryAttandanceDtoList){
			SalaryAttendanceEntity entity=new SalaryAttendanceEntity();
			copyObject(dto, entity);
			entityList.add(entity);
		}
		return entityList;
	}
	
	private List<SalaryLeaveDTO> convertSalaryEntitytoDto(List<SalaryLeaveEntity> entityList){
		List<SalaryLeaveDTO> salaryLeaveDTOList=new ArrayList<SalaryLeaveDTO>();
		for(SalaryLeaveEntity entity:entityList){
			SalaryLeaveDTO salaryLeaveDTO=new SalaryLeaveDTO();
			copyObject(entity, salaryLeaveDTO);
			salaryLeaveDTOList.add(salaryLeaveDTO);
		}
		return salaryLeaveDTOList;
	}
	private List<SalaryDetailDTO> convertSalaryDetailEntityListTOSalaryDetailDtoList(
			List<SalaryDetailEntity> list) {

		salaryMasterOutputMessage = new SalaryMasterOutputMessage();
		List<SalaryDetailDTO> resultList = null;
		// set the data to the outputput message.
		if (list != null) {
			SalaryDetailDTO salaryDetailDTO;
			resultList = new ArrayList<SalaryDetailDTO>();
			for (SalaryDetailEntity salaryDetailEntity : list) {
				salaryDetailDTO = new SalaryDetailDTO();
				if (salaryDetailEntity != null) {
					BeanUtils.copyProperties(salaryDetailEntity,salaryDetailDTO);
					resultList.add(salaryDetailDTO);
				}
			}
		}

		return resultList;
	}
	
	private void copyObject(Object source,Object target){
		
		try {
			BeanUtils.copyProperties(source, target);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    @Override
	public Map getEmployeeList(){
    	//Map map=getEmployeesList(null, null,"allEmployee");
    	List<String> header =new ArrayList<String>();
    		header.add("Month");
    		header.add("Day in Month");
    		header.add("EmployeeName");
    	    header.add("EmployeeCode");
    	    header.add("Desination");
    	    header.add("Date Of Joining");
    	    header.add("CL Opening Balance");
    	    header.add("CL Alloted In Month");
    	   LeaveTypeMastOutputMessage leaveTypeMastOutputMessage= leaveTypeMasterService.findAllLeaveTypeMasts();
    	 List<LeaveTypeMastDTO> leaveTypeList=leaveTypeMastOutputMessage.getLeaveTypeMastDTOList();
    	 for(int i=0;i<leaveTypeList.size();i++){
    		 LeaveTypeMastDTO leaveTypeMastDTO=leaveTypeList.get(i);
    		 //header.add(leaveTypeMastDTO.getLeaveName());
    	 }
    	 header.add("Working Days");
    	 header.add("Holiday+WeekOff");
    	 header.add("Present");
    	 header.add("ABSENT");
    	 header.add("CL Availed");
    	 header.add("Loss Of Pay");
    	 header.add("Extra");
 	     header.add("Payable Days");
 	     header.add("CL Closing Balance");
    SalaryHeadOutputMessage headOutputMessage=headService.findAllSalaryHeads();
    List<SalaryHeadDTO> salaryHeadList=headOutputMessage.getSalaryHeadDTOList();
    for(int i=0;i<salaryHeadList.size();i++){
		SalaryHeadDTO headDTO=salaryHeadList.get(i);
	if(headDTO.getHeadType().equalsIgnoreCase("E")){
		header.add(headDTO.getSalaryHeadName());
		header.add(headDTO.getSalaryHeadName()+"Payable");
	  }
	}
    header.add("Monthly Salary");
    header.add("Other Allowance");
    header.add("Net Amount");
    header.add("PF Flag");
    header.add("Base Sal For PF");
    for(int i=0;i<salaryHeadList.size();i++){
		SalaryHeadDTO headDTO=salaryHeadList.get(i);
	if(headDTO.getHeadType().equalsIgnoreCase("D")){
		header.add(headDTO.getSalaryHeadName());
	  }
	}	
    header.add("Employer Contr.");
    header.add("Employee Contr.");
    header.add("Total Dedc");
    //header.add("Bal. Advance");
    header.add("Adv.Adjustment");
    header.add("Net Salary");
    
    
   EmployeeOutputMessage employeeOutputMessage=employeeService.findAllActivatedEmployee();
   List<EmployeeDTO> employeeList=employeeOutputMessage.getEmployeeDTOList();
   Calendar calss = Calendar.getInstance();
	calss.add(Calendar.MONTH, -1);
	SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");  
	Date previousMonth = calss.getTime();
	String monthName=dateFormat.format(previousMonth);
   
	 Calendar calendr = Calendar.getInstance();
	 calendr.setTime(new Date());
	 int crntMonth = calendr.get(Calendar.MONTH)+1;
	 int crntYear = calendr.get(Calendar.YEAR);
	// int daysInMonth=getDayOfMonth(crntMonth);
	 
    List advanceAmtList=new ArrayList();
	List dedectAdvanceAmntList =new ArrayList();
	List earnigList =new ArrayList();
	
   for(int i=0;i<employeeList.size();i++){
	EmployeeDTO employeeDTO= employeeList.get(i);
	List<EmployeeSalaryDetDTO> empsl= employeeDTO.getEmployeeSalaryDetDTOList();
	List<EmployeeSalaryDetDTO> empsdl= employeeDTO.getEmployeeSalaryDetDTOListDe();
	
	calss.setTime(employeeDTO.getJoinDate());
	int joinMonth = calss.get(Calendar.MONTH)+1;
	int joinDay = calss.get(Calendar.DAY_OF_MONTH);
	int joinYear = calss.get(Calendar.YEAR);
	 
	Map map= getFirstDateAndLastDate(calendr.get(Calendar.MONTH));
	Date firstDate=(Date)map.get("firstDate");
    Date lastDate=(Date)map.get("lastDate");
    Double num=0.0;
    
    AdvanceAmountInputMessage inputMessage=new AdvanceAmountInputMessage();
    AdvanceAmountDTO amountDTO=new AdvanceAmountDTO();
    EmployeeDTO advanceEmp=new EmployeeDTO();
    advanceEmp.setEmployeeId(employeeDTO.getEmployeeId());
    amountDTO.setEmployeeDTO(advanceEmp);
    amountDTO.setFromDate(firstDate);
    amountDTO.setToDate(lastDate);
    inputMessage.setAdvanceAmountDTO(amountDTO);
    
    AdvanceAmountOutputMessage outputMessage=advanceAmountService.getByLastTransactionDate(inputMessage);
    if(outputMessage!=null)
    {
     List<AdvanceAmountDTO> dtos=outputMessage.getAdvanceAmountDTOList();
      if(dtos!=null && dtos.size()>0)
    	{
    		if(dtos.get(0).getTransactionType().equalsIgnoreCase("Deduction"))
	    	   {employeeDTO.setClAdvanceAdj(-dtos.get(0).getAmount());}
	    	   else
	    	   {employeeDTO.setClAdvanceAdj(dtos.get(0).getAmount());}
    	}
    	else{employeeDTO.setClAdvanceAdj(0.0);}
      }
    else{employeeDTO.setClAdvanceAdj(0.0);
 	}
    
    for(int j=0;j<leaveTypeList.size();j++){
		 LeaveTypeMastDTO leaveTypeMastDTO=leaveTypeList.get(j);
		 Double nu= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), leaveTypeMastDTO.getLeaveName(), firstDate, lastDate);
		 num =num+nu;
		
	 employeeDTO.getLeaveTypeList().add(nu);
    }
    
    /**Fetch previous month for pass in salary master and find balance leave detail  
	  * */
	 
    Double opnLeaveBal= 0.0;
    Double allowLeaveBal= 0.0;
    Double availLeavBal=0.0;
    Double lossOfPay=0.0;
       
	System.out.println("employeeDTO:-"+employeeDTO);
  List<SalaryLeaveEntity> entLeaveEntities=storageSalaryDAO.findByMonthEmpIdLeaveId(monthName, employeeDTO.getEmployeeId(), employeeDTO.getEmployeeLeavesDTOList().get(0).getLeaveId());
  if(entLeaveEntities!=null && entLeaveEntities.size()>0)
	 {
	 List<SalaryLeaveDTO> leaveDTOList=convertSalaryEntitytoDto(entLeaveEntities);
    for(SalaryLeaveDTO salaryLeaveDTO:leaveDTOList)
	  {
     	 if(salaryLeaveDTO.getClosingLeaveBalance()!=null)
     	 opnLeaveBal=salaryLeaveDTO.getClosingLeaveBalance();
     	 employeeList.get(i).setOpeningBalanceLeave(opnLeaveBal);
     }   }
	 else
	 {
		 opnLeaveBal=employeeDTO.getOpeningClBalance();
		 employeeList.get(i).setOpeningBalanceLeave(opnLeaveBal);
 	 }
   try {
	   
	String designationName= masterService.getMasterNameById(employeeDTO.getDesignation());
	   employeeDTO.setDesignationName(designationName);
} catch (Exception e) {
	e.printStackTrace();
}

//Calendar cal = Calendar.getInstance();
//cal.setTime(new Date());
//int year = cal.get(Calendar.YEAR);
int month = calendr.get(Calendar.MONTH);

	int monthMaxDays= getDayOfMonth(month);  
	double availedLeave=0.0;
	double absHalf=0.0;
	double hollyDay=0.0;
	double workingDays=0.0;
	double extraHalf=0.0;
	double extraFull=0.0;
    
	 extraHalf= sttandanceMasterService.countByHalfDay(employeeDTO.getEmployeeId(), "EXTRA", firstDate, lastDate);
	 extraFull= sttandanceMasterService.countByFullDay(employeeDTO.getEmployeeId(), "EXTRA", firstDate, lastDate);
	 hollyDay= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), "Holiday", firstDate, lastDate);
	 double hollyWk= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), "weakOff", firstDate, lastDate);
	 extraHalf=extraHalf*2;
	
	 hollyWk=hollyWk+hollyDay+extraHalf+extraFull;
	 workingDays=monthMaxDays-hollyWk;
	 if(crntMonth==joinMonth && joinYear==crntYear && joinDay>1)
	 {
		 employeeDTO.getLeaveTypeList().add(workingDays-(joinDay-1));
		 employeeDTO.setWorkingDays(workingDays-(joinDay-1));
	 }
	 else
	 {
		 employeeDTO.getLeaveTypeList().add(workingDays);
		 employeeDTO.setWorkingDays(workingDays);
	 }
    
	employeeDTO.getLeaveTypeList().add(hollyWk);
	employeeDTO.setHollyWeekOff(hollyWk);
	double extraWork= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), "EXTRA", firstDate, lastDate);
	double present= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), "PRESENT", firstDate, lastDate);
	double absent= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), "ABSENT", firstDate, lastDate);
	 absHalf= sttandanceMasterService.countByHalfDay(employeeDTO.getEmployeeId(), "PRESENT", firstDate, lastDate);

	employeeDTO.getLeaveTypeList().add(present);
	employeeDTO.setPresentDays(present);
	 //absHalf=(absHalf/2);
	Double payableDays=(hollyWk+present)+extraWork+num+availedLeave;
	 if(payableDays<20)
	   {
		 employeeDTO.getEmployeeLeavesDTOList().get(0).setAllowDays(allowLeaveBal);
	   }
	 else
	  {
		 if(employeeDTO.getEmployeeLeavesDTOList().get(0).getAllowDays()!=null)
		 allowLeaveBal=employeeDTO.getEmployeeLeavesDTOList().get(0).getAllowDays();
	  }
	 
	 availLeavBal=allowLeaveBal+opnLeaveBal;
	 
	 if(employeeDTO.getAvailedLeave()!=null)
	 {
		 availLeavBal=availLeavBal+employeeDTO.getAvailedLeave();
	 }
	 
	 absent=absent+absHalf;		
	employeeDTO.getLeaveTypeList().add(absent);
	employeeDTO.setAbsentDays(absent);
	if(availLeavBal>absent)
	{
		employeeList.get(i).setClosingBalanceLeave(availLeavBal-absent);
		if(absent>0.0){
			availedLeave=absent;}
		 	employeeDTO.setAvailedLeave(availedLeave);//for hidden field store in availed leav
		 	employeeDTO.getLeaveTypeList().add(availedLeave);
			}
	else
	  {
			employeeList.get(i).setClosingBalanceLeave(0.0);
			availedLeave=availLeavBal;
			lossOfPay=absent-availLeavBal;
			employeeDTO.setAvailedLeave(availedLeave);//for hidden field store in availed leav
			employeeDTO.getLeaveTypeList().add(availedLeave);
		}
	employeeDTO.getLeaveTypeList().add(lossOfPay);
	employeeDTO.setLossOfPay(lossOfPay);
	
	employeeDTO.getLeaveTypeList().add(extraWork);
	employeeDTO.setExtraWorkDays(extraWork);
	//Double pabayelDay=(monthMaxDays-num3)+num1;
	payableDays=payableDays+availedLeave;
	//pabayelDay=pabayelDay-lossOfPay;
	
	employeeDTO.setPayableDays(payableDays);
	employeeDTO.getLeaveTypeList().add(payableDays);
	
	 List emplistleave=employeeDTO.getEmployeeLeavesDTOList();
	   
	try{
	 for(int j=0;j<empsl.size();j++){
		  EmployeeSalaryDetDTO detDTO= empsl.get(j);
		  if(detDTO.getHeadType().equalsIgnoreCase("E")){
			//To check earning Head Type is(constant,fixed or variable)...........
			  SalaryHeadDTO headDTO= getSalaryHeadDTO(Integer.parseInt(detDTO.getSalaryId()));
			  employeeDTO.getEarningheadTypeList().add(headDTO.getType());
		     employeeDTO.getEarningHeadList().add(detDTO.getAmount());
		    
		  } if(detDTO.getHeadType().equalsIgnoreCase("D")){
			  
			//To check deduction Head Type is(constant,fixed or variable)...........
			  SalaryHeadDTO headDTO= getSalaryHeadDTO(Integer.parseInt(detDTO.getSalaryId()));     
			  employeeDTO.getDeductionheadTypeList().add(headDTO.getType());
			  employeeDTO.getDedecationHeadList().add(detDTO.getAmount());
			  
		  }
		  }
	 
	}catch (Exception e) {
		// TODO: handle exception
	}
	    advanceAmtList.add(0);
		dedectAdvanceAmntList.add(0);
		earnigList.add(0);
	   }
   
     //Calendar calendr = Calendar.getInstance();
	 // calendr.setTime(new Date());
     // int crntMonth = calendr.get(Calendar.MONTH)+1;
	 // int crntYear = calendr.get(Calendar.YEAR);
	 // int daysInMonth=getDayOfMonth(crntMonth);
	 
	Map monthDate= getFirstDateAndLastDate(calendr.get(Calendar.MONTH));
	Date firstDate=(Date)monthDate.get("firstDate");
    Date lastDate=(Date)monthDate.get("lastDate");
  
   SalaryMasterDTO salaryMasterDTO=new SalaryMasterDTO();
   salaryMasterDTO.setBalAdvanceList(advanceAmtList);
   salaryMasterDTO.setDedectAdvanceAmntList(dedectAdvanceAmntList);
   String monthNameString= DataUtility.getMonthName(calendr.get(Calendar.MONTH));
   int mn= getMonthNumber(monthNameString);
   int totalDaysInMonth= getDayOfMonth(mn); 
   salaryMasterDTO.setSalaryYearInt(crntYear);
   salaryMasterDTO.setTotalDaysInMonth(totalDaysInMonth);
   salaryMasterDTO.setSalaryMonth(monthNameString);
   salaryMasterDTO.setNetAmntList(earnigList);
   salaryMasterDTO.setMinDate(firstDate);
   salaryMasterDTO.setMaxDate(lastDate);
   
  Map map=new HashMap();
  map.put("salaryMasterDTO", salaryMasterDTO);
  map.put("header", header);
  map.put("employeeList", employeeList);
  
  return map;
 }
    
    
    @Override
   	public Map getEmployeeListByMonthName(String salaryMonth,Integer year){
       	//Map map=getEmployeesList(null, null,"allEmployee");
       	List<String> header =new ArrayList<String>();
       		header.add("Month");
       		header.add("Day in Month");
       		header.add("EmployeeName");
       	    header.add("EmployeeCode");
       	    header.add("Desination");
       	    header.add("Date Of Joining");
       	    header.add("CL Opening Balance");
       	    header.add("CL Alloted In Month");
       	 LeaveTypeMastOutputMessage leaveTypeMastOutputMessage= leaveTypeMasterService.findAllLeaveTypeMasts();
       	 List<LeaveTypeMastDTO> leaveTypeList=leaveTypeMastOutputMessage.getLeaveTypeMastDTOList();
       	 for(int i=0;i<leaveTypeList.size();i++){
       		 LeaveTypeMastDTO leaveTypeMastDTO=leaveTypeList.get(i);
       		 //header.add(leaveTypeMastDTO.getLeaveName());
       	 }
       	 header.add("Working Days");
       	 header.add("Holiday+WeekOff");
       	 header.add("Present");
       	 header.add("ABSENT");
       	 header.add("CL Availed");
       	 header.add("Loss Of Pay");
       	 header.add("Extra");
    	 header.add("Payable Days");
    	 header.add("CL Closing Balance");
       SalaryHeadOutputMessage headOutputMessage=headService.findAllSalaryHeads();
       List<SalaryHeadDTO> salaryHeadList=headOutputMessage.getSalaryHeadDTOList();
    for(int i=0;i<salaryHeadList.size();i++){
   		SalaryHeadDTO headDTO=salaryHeadList.get(i);
   	if(headDTO.getHeadType().equalsIgnoreCase("E")){
   		header.add(headDTO.getSalaryHeadName());
   		header.add(headDTO.getSalaryHeadName()+"Payable");
   	  }
   	}
       header.add("Monthly Salary");
       header.add("Other Allowance");
       header.add("Net Amount");
       header.add("PF Flag");
       header.add("Base Sal For PF");
   for(int i=0;i<salaryHeadList.size();i++){
   		SalaryHeadDTO headDTO=salaryHeadList.get(i);
   	if(headDTO.getHeadType().equalsIgnoreCase("D")){
   		header.add(headDTO.getSalaryHeadName());
   	  }
   	}	
       header.add("Employer Contr.");
       header.add("Employee Contr.");
       header.add("Total Dedc");
       //header.add("Bal. Advance");
       header.add("Adv.Adjustment");
       header.add("Net Salary");
       
       int monthInt=getMonthNumber(salaryMonth);
       Calendar calendr = Calendar.getInstance();
        calendr.set(Calendar.MONTH, monthInt);
        calendr.set(Calendar.YEAR, year);
   	 	int crntMonth = calendr.get(Calendar.MONTH)+1;
   	 	int crntYear = calendr.get(Calendar.YEAR);
   	 	int curntJavaMonth=calendr.get(Calendar.MONTH);
   	 	calendr.add(Calendar.MONTH,-1);
   	 	SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");  
    	Date date = calendr.getTime();
    	String previousJavaMonth=dateFormat.format(date);
    	int monthMaxDays= getDaysInMonth(curntJavaMonth,crntYear);  
       
     /*  	 Calendar calendr = Calendar.getInstance();
     	 calendr.setTime(new Date());
     	 int crntMonth = calendr.get(Calendar.MONTH)+1;
     	 int crntYear = calendr.get(Calendar.YEAR);
       
     	 Calendar calss = Calendar.getInstance();
      	  calss.add(Calendar.MONTH, -1);
      	  SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM");  
      	  Date previousMonth = calss.getTime();
      	  String monthName=dateFormat.format(previousMonth);
      	  
      	 Calendar cal = Calendar.getInstance();
         cal.setTime(new Date());
         //int year = cal.get(Calendar.YEAR);
         int month = cal.get(Calendar.MONTH);
*/
       
      EmployeeOutputMessage employeeOutputMessage=employeeService.findAllActivatedEmployee();
      List<EmployeeDTO> employeeList=employeeOutputMessage.getEmployeeDTOList();
      
      List advanceAmtList=new ArrayList();
      List dedectAdvanceAmntList =new ArrayList();
   	  List earnigList =new ArrayList();
   	
      for(int i=0;i<employeeList.size();i++){
   	   EmployeeDTO employeeDTO= employeeList.get(i);
   	   List<EmployeeSalaryDetDTO> empsl= employeeDTO.getEmployeeSalaryDetDTOList();
   	   List<EmployeeSalaryDetDTO> empsdl= employeeDTO.getEmployeeSalaryDetDTOListDe();
   	
   	calendr.setTime(employeeDTO.getJoinDate());
   	int joinMonth = calendr.get(Calendar.MONTH)+1;
   	int joinDay = calendr.get(Calendar.DAY_OF_MONTH);
   	int joinYear = calendr.get(Calendar.YEAR);

   	
   	// int daysInMonth=getDayOfMonth(crntMonth);
   	 
   	Map map= getFirstLastDateByMonthAndYear(monthInt,year);
   	Date firstDate=(Date)map.get("firstDate");
       Date lastDate=(Date)map.get("lastDate");
       Double num=0.0;
       
       AdvanceAmountInputMessage inputMessage=new AdvanceAmountInputMessage();
       AdvanceAmountDTO amountDTO=new AdvanceAmountDTO();
       EmployeeDTO advanceEmp=new EmployeeDTO();
       advanceEmp.setEmployeeId(employeeDTO.getEmployeeId());
       amountDTO.setEmployeeDTO(advanceEmp);
       amountDTO.setFromDate(firstDate);
       amountDTO.setToDate(lastDate);
       inputMessage.setAdvanceAmountDTO(amountDTO);
       
       AdvanceAmountOutputMessage outputMessage=advanceAmountService.getByLastTransactionDate(inputMessage);
       if(outputMessage!=null)
       {
        List<AdvanceAmountDTO> dtos=outputMessage.getAdvanceAmountDTOList();
         if(dtos!=null && dtos.size()>0)
       	{
       		if(dtos.get(0).getTransactionType().equalsIgnoreCase("Deduction"))
   	    	   {employeeDTO.setClAdvanceAdj(-dtos.get(0).getAmount());}
   	    	   else
   	    	   {employeeDTO.setClAdvanceAdj(dtos.get(0).getAmount());}
       	}
       	else{employeeDTO.setClAdvanceAdj(0.0);}
         }
       else{employeeDTO.setClAdvanceAdj(0.0);
    	}
       
       for(int j=0;j<leaveTypeList.size();j++){
   		 LeaveTypeMastDTO leaveTypeMastDTO=leaveTypeList.get(j);
   		 Double nu= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), leaveTypeMastDTO.getLeaveName(), firstDate, lastDate);
   		 num =num+nu;
   		
   	 employeeDTO.getLeaveTypeList().add(nu);
       }
       
       /**Fetch previous month for pass in salary master and find balance leave detail  
   	  * */
      
       Double opnLeaveBal= 0.0;
       Double allowLeaveBal= 0.0;
       Double availLeavBal=0.0;
       Double lossOfPay=0.0;
       
   	  double availedLeave=0.0;
   	  double absHalf=0.0;
   	  double hollyDay=0.0;
   	  double workingDays=0.0;
   	  double extraHalf=0.0;
   	  double extraFull=0.0;
       
       	/* if(crntMonth==joinMonth && joinYear==crntYear && joinDay>10)
   		 {
   			 employeeDTO.getEmployeeLeavesDTOList().get(0).setAllowDays(allowLeaveBal);
   		 }
   		 else
   		 {
   			 if(employeeDTO.getEmployeeLeavesDTOList().get(0).getAllowDays()!=null)
   			 allowLeaveBal=employeeDTO.getEmployeeLeavesDTOList().get(0).getAllowDays();
   		 }*/
   	
     List<SalaryLeaveEntity> entLeaveEntities=storageSalaryDAO.findByMonthEmpIdLeaveId(previousJavaMonth, employeeDTO.getEmployeeId(), employeeDTO.getEmployeeLeavesDTOList().get(0).getLeaveId());
     if(entLeaveEntities!=null && entLeaveEntities.size()>0)
   	 {
   	 List<SalaryLeaveDTO> leaveDTOList=convertSalaryEntitytoDto(entLeaveEntities);
       for(SalaryLeaveDTO salaryLeaveDTO:leaveDTOList)
   	  {
        	 if(salaryLeaveDTO.getClosingLeaveBalance()!=null)
        	 opnLeaveBal=salaryLeaveDTO.getClosingLeaveBalance();
        	 employeeList.get(i).setOpeningBalanceLeave(opnLeaveBal);
        }   }
   	 else
   	 {
   		 opnLeaveBal=employeeDTO.getOpeningClBalance();
   		 employeeList.get(i).setOpeningBalanceLeave(opnLeaveBal);
    	 }
      try {
   	   
   	String designationName= masterService.getMasterNameById(employeeDTO.getDesignation());
   	   employeeDTO.setDesignationName(designationName);
   } catch (Exception e) {
   	e.printStackTrace();
   }

       
   	extraHalf= sttandanceMasterService.countByHalfDay(employeeDTO.getEmployeeId(), "EXTRA", firstDate, lastDate);
	 extraFull= sttandanceMasterService.countByFullDay(employeeDTO.getEmployeeId(), "EXTRA", firstDate, lastDate);
	 hollyDay= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), "Holiday", firstDate, lastDate);
	 double hollyWk= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), "weakOff", firstDate, lastDate);
	 extraHalf=extraHalf*2;
	
	 hollyWk=hollyWk+hollyDay+extraHalf+extraFull;
	 workingDays=monthMaxDays-hollyWk;
	 if(crntMonth==joinMonth && joinYear==crntYear && joinDay>1)
	 {
		 employeeDTO.getLeaveTypeList().add(workingDays-(joinDay-1));
		 employeeDTO.setWorkingDays(workingDays-(joinDay-1));
	 }
	 else
	 {
		 employeeDTO.getLeaveTypeList().add(workingDays);
		 employeeDTO.setWorkingDays(workingDays);
	 }
   
	employeeDTO.getLeaveTypeList().add(hollyWk);
	employeeDTO.setHollyWeekOff(hollyWk);
	double extraWork= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), "EXTRA", firstDate, lastDate);
	double present= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), "PRESENT", firstDate, lastDate);
	double absent= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), "ABSENT", firstDate, lastDate);
	 absHalf= sttandanceMasterService.countByHalfDay(employeeDTO.getEmployeeId(), "PRESENT", firstDate, lastDate);

	employeeDTO.getLeaveTypeList().add(present);
	employeeDTO.setPresentDays(present);
	 //absHalf=(absHalf/2);
	Double payableDays=(hollyWk+present)+extraWork+num+availedLeave;
	 if(payableDays<20)
	   {
		 employeeDTO.getEmployeeLeavesDTOList().get(0).setAllowDays(allowLeaveBal);
	   }
	 else
	  {
		 if(employeeDTO.getEmployeeLeavesDTOList().get(0).getAllowDays()!=null)
		 allowLeaveBal=employeeDTO.getEmployeeLeavesDTOList().get(0).getAllowDays();
	  }
	 availLeavBal=allowLeaveBal+opnLeaveBal;
	 if(employeeDTO.getAvailedLeave()!=null)
	 {
		 availLeavBal=availLeavBal+employeeDTO.getAvailedLeave();
	 }
	 
	 absent=absent+absHalf;		
	employeeDTO.getLeaveTypeList().add(absent);
	employeeDTO.setAbsentDays(absent);
	if(availLeavBal>absent)
	{
		employeeList.get(i).setClosingBalanceLeave(availLeavBal-absent);
		if(absent>0.0){
			availedLeave=absent;}
		 	employeeDTO.setAvailedLeave(availedLeave);//for hidden field store in availed leav
		 	employeeDTO.getLeaveTypeList().add(availedLeave);
			}
	else
	  {
			employeeList.get(i).setClosingBalanceLeave(0.0);
			availedLeave=availLeavBal;
			lossOfPay=absent-availLeavBal;
			employeeDTO.setAvailedLeave(availedLeave);//for hidden field store in availed leav
			employeeDTO.getLeaveTypeList().add(availedLeave);
		}
	employeeDTO.getLeaveTypeList().add(lossOfPay);
	employeeDTO.setLossOfPay(lossOfPay);
	
	employeeDTO.getLeaveTypeList().add(extraWork);
	employeeDTO.setExtraWorkDays(extraWork);
	//Double pabayelDay=(monthMaxDays-num3)+num1;
	payableDays=payableDays+availedLeave;
	//pabayelDay=pabayelDay-lossOfPay;
	
	employeeDTO.setPayableDays(payableDays);
	employeeDTO.getLeaveTypeList().add(payableDays);
   	
   	 List emplistleave=employeeDTO.getEmployeeLeavesDTOList();
   	   
   	try{
   	 for(int j=0;j<empsl.size();j++){
   		  EmployeeSalaryDetDTO detDTO= empsl.get(j);
   		  if(detDTO.getHeadType().equalsIgnoreCase("E")){
   			//To check earning Head Type is(constant,fixed or variable)...........
   			  SalaryHeadDTO headDTO= getSalaryHeadDTO(Integer.parseInt(detDTO.getSalaryId()));
   			  employeeDTO.getEarningheadTypeList().add(headDTO.getType());
   		     employeeDTO.getEarningHeadList().add(detDTO.getAmount());
   		    
   		  } if(detDTO.getHeadType().equalsIgnoreCase("D")){
   			//To check deduction Head Type is(constant,fixed or variable)...........
   			  SalaryHeadDTO headDTO= getSalaryHeadDTO(Integer.parseInt(detDTO.getSalaryId()));     
   			  employeeDTO.getDeductionheadTypeList().add(headDTO.getType());
   			  employeeDTO.getDedecationHeadList().add(detDTO.getAmount());
   		  }}
   	   }catch (Exception e) {
   		// TODO: handle exception
     	}
   	    advanceAmtList.add(0);
   		dedectAdvanceAmntList.add(0);
   		earnigList.add(0);
   	   }
      
      
   	/* int crMonth = calendr.get(Calendar.MONTH)+1;
   	 int crYear = calendr.get(Calendar.YEAR);*/
   	// int daysInMonth=getDayOfMonth(crntMonth);
   	 
   	Map monthDate= getFirstDateAndLastDate(calendr.get(Calendar.MONTH));
   	Date firstDate=(Date)monthDate.get("firstDate");
     Date lastDate=(Date)monthDate.get("lastDate");
     
      SalaryMasterDTO salaryMasterDTO=new SalaryMasterDTO();
      salaryMasterDTO.setBalAdvanceList(advanceAmtList);
      salaryMasterDTO.setDedectAdvanceAmntList(dedectAdvanceAmntList);
      //String mthName= DataUtility.getMonthName(new Date().getMonth());
      //int mn= getMonthNumber(mthName);
      //int totalDaysInMonth= getDayOfMonth(mn);
      String finYear=getFinYear(monthInt, year);
      int totalDaysInMonth= getDaysInMonth(monthInt, year);
      salaryMasterDTO.setSalaryYear(finYear);
      salaryMasterDTO.setSalaryYearInt(year);
      salaryMasterDTO.setTotalDaysInMonth(totalDaysInMonth);
      salaryMasterDTO.setSalaryMonth(salaryMonth);
      salaryMasterDTO.setNetAmntList(earnigList);
      salaryMasterDTO.setMinDate(firstDate);
      salaryMasterDTO.setMaxDate(lastDate);
      
     Map map=new HashMap();
     map.put("salaryMasterDTO", salaryMasterDTO);
     map.put("header", header);
     map.put("employeeList", employeeList);
     
     return map;
    }
    
   
 //Method TO Get Employee By Department......................................
    
 @Override
	public Map getEmployeeListByDepartment(Integer deptId, String mothName) {
	 
	 //Map map= getEmployeesList(deptId,mothName,"employeeByDept");
	 
   List<String> header =new ArrayList<String>();
   	header.add("Month");
	header.add("Day in Month");
 	header.add("EmployeeName");
 	header.add("EmployeeCode");
 	header.add("Desination");
 	LeaveTypeMastOutputMessage leaveTypeMastOutputMessage= leaveTypeMasterService.findAllLeaveTypeMasts();
 	 List<LeaveTypeMastDTO> leaveTypeList=leaveTypeMastOutputMessage.getLeaveTypeMastDTOList();
 	 for(int i=0;i<leaveTypeList.size();i++){
 		 LeaveTypeMastDTO leaveTypeMastDTO=leaveTypeList.get(i);
 		 header.add(leaveTypeMastDTO.getLeaveName());
 	 }
    header.add("Extra");
	   header.add("WeekOff");
	   header.add("ABSENT");
	   header.add("Presant");
	   header.add("Payable Days");
 SalaryHeadOutputMessage headOutputMessage=headService.findAllSalaryHeads();
 List<SalaryHeadDTO> salaryHeadList=headOutputMessage.getSalaryHeadDTOList();
 for(int i=0;i<salaryHeadList.size();i++){
		SalaryHeadDTO headDTO=salaryHeadList.get(i);
	if(headDTO.getHeadType().equalsIgnoreCase("E")){
		header.add(headDTO.getSalaryHeadName());
	  }
	}	
 header.add("Total Earn");
 for(int i=0;i<salaryHeadList.size();i++){
		SalaryHeadDTO headDTO=salaryHeadList.get(i);
	if(headDTO.getHeadType().equalsIgnoreCase("D")){
		header.add(headDTO.getSalaryHeadName());
	  }
	}	
 header.add("Total Dedc");
 header.add("Bal. Advance");
 header.add("Ded.adv./Other");
 header.add("Net Salary");
 
 EmployeeDTO empDTO=new EmployeeDTO();
 empDTO.setDesignation(deptId);
 EmployeeInputMessage empInputMessage=new EmployeeInputMessage();
 empInputMessage.setEmployeeDTO(empDTO);
 EmployeeOutputMessage employeeOutputMessage=null;
 if(deptId==0){
  employeeOutputMessage=employeeService.findAllActivatedEmployee();
 }else{
 employeeOutputMessage=employeeService.findAllActivatedEmployeeByDeptId(empInputMessage);
 }
List<EmployeeDTO> employeeList=employeeOutputMessage.getEmployeeDTOList();
List advanceAmtList=new ArrayList();
List dedectAdvanceAmntList =new ArrayList();
List earningList=new ArrayList();
try {
	
	for(int i=0;i<employeeList.size();i++){
		EmployeeDTO employeeDTO= employeeList.get(i);
		List<EmployeeSalaryDetDTO> empsl= employeeDTO.getEmployeeSalaryDetDTOList();
		List<EmployeeSalaryDetDTO> empsdl= employeeDTO.getEmployeeSalaryDetDTOListDe();
		
		
		
		int m= getMonthNumber(mothName);
		Map map= getFirstDateAndLastDate(m);
		Date firstDate=(Date)map.get("firstDate");
	    Date lastDate=(Date)map.get("lastDate");
	 
	 
	 try {
			String designationName= masterService.getMasterNameById(employeeDTO.getDesignation());
			   employeeDTO.setDesignationName(designationName);
		} catch (Exception e) {
			e.printStackTrace();}
		Double num=0.0;
		
	 for(int j=0;j<leaveTypeList.size();j++){
			 LeaveTypeMastDTO leaveTypeMastDTO=leaveTypeList.get(j);
			 Double  nu= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), leaveTypeMastDTO.getLeaveName(), firstDate, lastDate);
		 num =num+nu;
		 employeeDTO.getLeaveTypeList().add(nu);
	 }
	 int month= getMonthNumber(mothName);
	 int monthMaxDays= getDayOfMonth(month);
	 
	 
	    double num1= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), "EXTRA", firstDate, lastDate);
		employeeDTO.getLeaveTypeList().add(num1);
		double num2= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), "weakOff", firstDate, lastDate);
		employeeDTO.getLeaveTypeList().add(num2);
		double num3= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), "ABSENT", firstDate, lastDate);
		employeeDTO.getLeaveTypeList().add(num3);
		double num4= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), "PRESENT", firstDate, lastDate);
		double extra=0.0;
		if(num1>0){
		Long l= Math.round(num1);
		double d=(double)l;
		extra=d;
		num4=num4+d;
		}
		employeeDTO.getLeaveTypeList().add(num4);
		
		
		//Double pabayelDay=(monthMaxDays-num3)+num1;
		Double pabayelDay=(num2+num4)+num1+num+extra;
		employeeDTO.getLeaveTypeList().add(pabayelDay);
		
		 for(int j=0;j<empsl.size();j++){
			  EmployeeSalaryDetDTO detDTO= empsl.get(j);
			  if(detDTO.getHeadType().equalsIgnoreCase("E")){
				  
		//To check earning Head Type is(constant,fixed or variable)...........
				  SalaryHeadDTO headDTO= getSalaryHeadDTO(Integer.parseInt(detDTO.getSalaryId()));     
				  employeeDTO.getEarningheadTypeList().add(headDTO.getType());
				  
				  System.out.println("HEAD TYPE:::::::::::::"+headDTO.getType()+"EARNING HEAD AMOUNT:::::::::::" +detDTO.getAmount());
			  employeeDTO.getEarningHeadList().add(detDTO.getAmount());
			  } if(detDTO.getHeadType().equalsIgnoreCase("D")){
				//To check deduction Head Type is(constant,fixed or variable)...........
				  SalaryHeadDTO headDTO= getSalaryHeadDTO(Integer.parseInt(detDTO.getSalaryId()));     
				  employeeDTO.getDeductionheadTypeList().add(headDTO.getType());
				  
				  
				employeeDTO.getDedecationHeadList().add(detDTO.getAmount());
			  }}

	}
} catch (Exception e) {
// TODO Auto-generated catch block
	e.printStackTrace();
}

//To get Advance amount of Previous Month
try{
int mn= getMonthNumber(mothName);
if(mn!=0){
	mn=mn-1;
}
String monthN= DataUtility.getMonthName(mn);
List adml= storageSalaryDAO.getAdvanceAmountByMonthNameAndDeartment(monthN, deptId);
for(int i=0;i<employeeList.size();i++){
	EmployeeDTO	employeeDTO=employeeList.get(i);
	if(adml!=null && adml.size()>0){
for(int l=0;l<adml.size();l++){
Object[] obj=(Object[])adml.get(l);
Double balAvAmnt=(Double) obj[0];
	Double deductAvAmnt=(Double)obj[1];
	Integer emId=(Integer)obj[2];
	
if(employeeDTO.getEmployeeId()==emId){
    advanceAmtList.add(balAvAmnt);
	dedectAdvanceAmntList.add(deductAvAmnt);
}else {
	advanceAmtList.add(0);
	dedectAdvanceAmntList.add(0);
}}
	}else{
		advanceAmtList.add(0);
		dedectAdvanceAmntList.add(0);
	}
	earningList.add(0);
}
}catch(Exception ex){ex.printStackTrace();}


int mn= getMonthNumber(mothName);
int totalDaysInMonth= getDayOfMonth(mn);


SalaryMasterDTO masterDTO=new SalaryMasterDTO();
masterDTO.setBalAdvanceList(advanceAmtList);
masterDTO.setDedectAdvanceAmntList(dedectAdvanceAmntList);
masterDTO.setDepartmentId(deptId);
masterDTO.setSalaryMonth(mothName);
masterDTO.setTotalDaysInMonth(totalDaysInMonth);
masterDTO.setNetAmntList(earningList);
Map map=new HashMap();
map.put("header", header);
map.put("employeeList", employeeList);
map.put("masterDTO", masterDTO);


return map;
    }
    
    
public Map getFirstDateAndLastDate(int month) {
	Map map=new HashMap();
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.MONTH, month);
	    calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
	    Date lastDate = calendar.getTime();
	    calendar.set(Calendar.DAY_OF_MONTH, 1);
	    Date firstDate = calendar.getTime();
	    map.put("firstDate", firstDate);
	    map.put("lastDate",lastDate);
	 return map;
}

public Map getFirstLastDateByMonthAndYear(int month,int year) {
	Map map=new HashMap();
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.YEAR, year);
	    calendar.set(Calendar.MONTH, month);
	    calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
	    Date lastDate = calendar.getTime();
	    calendar.set(Calendar.DAY_OF_MONTH, 1);
	    Date firstDate = calendar.getTime();
	    map.put("firstDate", firstDate);
	    map.put("lastDate",lastDate);
	 return map;
}

@Override
public List<SalaryMasterDTO> getSalaryList() {
	List<SalaryMasterDTO> salaryList = new ArrayList<SalaryMasterDTO>();
	
    List list=storageSalaryDAO.getSalaryList();
	for(int i=0;i<list.size();i++){
	SalaryMasterDTO salaryMasterDTO = new SalaryMasterDTO();
	Object[] object=(Object[])list.get(i);
	Integer salaryTranAutoNo=(Integer)object[0];
	String monthName=(String)object[1];
	Integer totalEmployee=Integer.parseInt(object[2].toString());
	Double totalSalary=Double.parseDouble(object[3].toString());
	String salaryYear=(String)object[4].toString();
	Boolean approvedFlag=false;
	int flag=0;
	try {
	approvedFlag = Boolean.parseBoolean(object[5].toString());
	flag=Integer.parseInt(object[5].toString());
	
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	salaryMasterDTO.setApprovedFlag(flag);
	String departmentName=(String)object[6];
	salaryMasterDTO.setTotalEmployee(totalEmployee);
	salaryMasterDTO.setTotalSalary(totalSalary);
	salaryMasterDTO.setSalaryMonth(monthName);
	salaryMasterDTO.setSalaryTranAutoNo(salaryTranAutoNo);
	salaryMasterDTO.setDepartmentName(departmentName);
	salaryMasterDTO.setSalaryYear(salaryYear);
	System.out.println("salaryTranAutoNo :"+salaryTranAutoNo+"monthName: "+monthName+"totalEmployee :"+totalEmployee+"totalSalary: "+totalSalary);
	salaryList.add(salaryMasterDTO);
	}
	return salaryList;
	}

@Override
public Boolean getSalaryByDepartmentIdAndMonth(Integer deptId, String month) {
	List list= storageSalaryDAO.getSalaryByDepartmentIdAndMonth(deptId, month);
	Boolean flag=false;
	if(list!=null && list.size()>0){
		flag=true;
	}
	
return flag;
}

public enum Month
{
	JANUARY, FEBRUARY, MARCH, APRIL,
	MAY, JUNE, JULY,AUGUST,SEPTEMBER,OCTOBER,NOVEMBER,DECEMBER;
}
public Integer getMonthNumber(String str){
	 Integer month=0;
	 
	  switch (Month.valueOf(str))
	  {
	      case JANUARY:  month=0;
	       break;
	       
	      case FEBRUARY: month=1;
	       break;
	       
	      case MARCH: month=2;
	       break;
	       
	      case APRIL: month=3;
	       break;
	       
	      case MAY: month=4;
	       break;
	       
	      case JUNE: month=5;
	       break;
	       
	      case JULY: month=6;
	       break;
	       
	      case AUGUST: month=7;
	       break;
	       
	      case SEPTEMBER: month=8;
		       break;
		       
	      case OCTOBER: month=9;
		       break;
		       
	      case NOVEMBER: month=10;
		       break;
		       
	      case DECEMBER: month=11;
		       break;
		
	  } 
	return month;
}

public static void main(String args[]) {
	SalaryMasterServiceImpl impl=new SalaryMasterServiceImpl();
//int i=	impl.getMonthNumber("AUGUST");

	int monthMaxDays= impl.getDayOfMonth(1);
	System.out.println("MaxDaysOfMonth::::::::"+monthMaxDays);
//System.out.println(i +"  "+monthMaxDays);
 }
public int  getDayOfMonth(int month){
	Calendar cal = Calendar.getInstance();
	cal.setTime(new Date());
	int year = cal.get(Calendar.YEAR);
	//int month = cal.get(Calendar.MONTH);
	int day = cal.get(Calendar.DAY_OF_MONTH);
	//Calendar cal = Calendar.getInstance();
	cal.set(Calendar.MONTH, month);
	cal.set(Calendar.YEAR, year);
	int monthMaxDays= cal.getActualMaximum(Calendar.DAY_OF_MONTH);	
    return monthMaxDays;
   
}
 
public Date getDateByMonth(int month)
{
	int year = 0;
	Calendar c = Calendar.getInstance();
	int y = c.get(Calendar.YEAR);
	int m = c.get(month);
	if (m <= Calendar.MARCH)
		year = y ;
	else
		year = y + 1;
  	c.set(Calendar.MONTH, month);
	c.set(Calendar.YEAR, year);
	System.out.println(c.getTime());
	return c.getTime();
}

protected String getFinYear(int month,int year) {
	String finYear = null;
	Calendar c = Calendar.getInstance();
	c.set(Calendar.MONTH, month);
	c.set(Calendar.YEAR, year);
	int y = c.get(Calendar.YEAR) % 100;
	int m = c.get(month);
	if (m <= Calendar.MARCH)
		finYear = (y - 1) + "-" + y;
	else
		finYear = y + "-" + (y + 1);
	System.out.println(finYear);
	return finYear;
}

public int getDaysInMonth(int month,int Year)
{
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);
        int monthMaxDays= cal.getActualMaximum(Calendar.DAY_OF_MONTH);        
//for leap year                
        if( (month==1) && (Year%4==0))
        {
            monthMaxDays= cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            monthMaxDays=monthMaxDays+1;
            return monthMaxDays;
        }
        
    return monthMaxDays;
}



public SalaryHeadDTO getSalaryHeadDTO(Integer salaryHeadId){
	 SalaryHeadInputMessage salaryHeadInputMessage = new SalaryHeadInputMessage();
	  SalaryHeadDTO headDTO=new SalaryHeadDTO();
	  headDTO.setSalaryHeadId(salaryHeadId);
	  salaryHeadInputMessage.setSalaryHeadDTO(headDTO);
	  SalaryHeadOutputMessage headOutputMessage2= headService.findSalaryHeadById(salaryHeadInputMessage);
	  headDTO=headOutputMessage2.getSalaryHeadDTOList().get(0);
	return headDTO;
}



public Map getEmployeesList(Integer deptId, String mothName,String operation){
	
	  List<String> header =new ArrayList<String>();
	  	header.add("Month");
		header.add("Day in Month");
	 	header.add("EmployeeName");
	 	header.add("EmployeeCode");
	 	header.add("Desination");
	 	LeaveTypeMastOutputMessage leaveTypeMastOutputMessage= leaveTypeMasterService.findAllLeaveTypeMasts();
	 	 List<LeaveTypeMastDTO> leaveTypeList=leaveTypeMastOutputMessage.getLeaveTypeMastDTOList();
	 	 for(int i=0;i<leaveTypeList.size();i++){
	 		 LeaveTypeMastDTO leaveTypeMastDTO=leaveTypeList.get(i);
	 		 header.add(leaveTypeMastDTO.getLeaveName());
	 	 }
	    header.add("Extra");
		   header.add("WeekOff");
		   header.add("ABSENT");
		   header.add("Presant");
		   header.add("Payable Days");
	 SalaryHeadOutputMessage headOutputMessage=headService.findAllSalaryHeads();
	 List<SalaryHeadDTO> salaryHeadList=headOutputMessage.getSalaryHeadDTOList();
	 for(int i=0;i<salaryHeadList.size();i++){
			SalaryHeadDTO headDTO=salaryHeadList.get(i);
		if(headDTO.getHeadType().equalsIgnoreCase("E")){
			header.add(headDTO.getSalaryHeadName());
		  }
		}	
	 header.add("Total Earn");
	 for(int i=0;i<salaryHeadList.size();i++){
			SalaryHeadDTO headDTO=salaryHeadList.get(i);
		if(headDTO.getHeadType().equalsIgnoreCase("D")){
			header.add(headDTO.getSalaryHeadName());
		  }
		}	
	 header.add("Total Dedc");
	 header.add("Bal. Advance");
	 header.add("Ded.adv./Other");
	 header.add("Net Salary");
	 
	 
	 EmployeeOutputMessage employeeOutputMessage=null;
	 List<EmployeeDTO> employeeList=null;
	 
	 List advanceAmtList=new ArrayList();
	 List dedectAdvanceAmntList =new ArrayList();
	 
if(operation.equalsIgnoreCase("employeeByDept")){
EmployeeDTO empDTO=new EmployeeDTO();
empDTO.setDesignation(deptId);
EmployeeInputMessage empInputMessage=new EmployeeInputMessage();
empInputMessage.setEmployeeDTO(empDTO);
employeeOutputMessage=employeeService.findAllActivatedEmployeeByDeptId(empInputMessage);
employeeList=employeeOutputMessage.getEmployeeDTOList();
}
if(operation.equalsIgnoreCase("allEmployee")){
employeeOutputMessage=employeeService.findAllActivatedEmployee();
employeeList=employeeOutputMessage.getEmployeeDTOList();

}	 
	 


try {

for(int i=0;i<employeeList.size();i++){
	EmployeeDTO employeeDTO= employeeList.get(i);
	List<EmployeeSalaryDetDTO> empsl= employeeDTO.getEmployeeSalaryDetDTOList();
	List<EmployeeSalaryDetDTO> empsdl= employeeDTO.getEmployeeSalaryDetDTOListDe();

	
	Map map=null;
	Date firstDate=null;
	Date lastDate=null;
	int month=0;
	int monthMaxDays=0;
	if(operation.equalsIgnoreCase("employeeByDept")){
	int m= getMonthNumber(mothName);
	map= getFirstDateAndLastDate(m);
	 firstDate=(Date)map.get("firstDate");
   lastDate=(Date)map.get("lastDate");
   
    month= getMonthNumber(mothName);
	  monthMaxDays= getDayOfMonth(month);
	}else{
		map= getFirstDateAndLastDate(new Date().getMonth());
		 firstDate=(Date)map.get("firstDate");
	     lastDate=(Date)map.get("lastDate");
	     
	     Calendar cal = Calendar.getInstance();
	     cal.setTime(new Date());
	     int year = cal.get(Calendar.YEAR);
	      month = cal.get(Calendar.MONTH);
	     //int month= getMonthNumber(mothName);
	      monthMaxDays= getDayOfMonth(month);  
	}
	
	 for(int j=0;j<leaveTypeList.size();j++){
		 LeaveTypeMastDTO leaveTypeMastDTO=leaveTypeList.get(j);
	double num= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), leaveTypeMastDTO.getLeaveName(), firstDate, lastDate);
	employeeDTO.getLeaveTypeList().add(num);
}
	 try {
			String designationName= masterService.getMasterNameById(employeeDTO.getDesignation());
			   employeeDTO.setDesignationName(designationName);
		} catch (Exception e) {
			e.printStackTrace();}
	 
	 
		double num1= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), "EXTRA", firstDate, lastDate);
		employeeDTO.getLeaveTypeList().add(num1);
		double num2= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), "weakOff", firstDate, lastDate);
		employeeDTO.getLeaveTypeList().add(num2);
		double num3= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), "ABSENT", firstDate, lastDate);
		employeeDTO.getLeaveTypeList().add(num3);
		double num4= sttandanceMasterService.coutLeaves(employeeDTO.getEmployeeId(), "PRESENT", firstDate, lastDate);
		employeeDTO.getLeaveTypeList().add(num4);
		
		//Double pabayelDay=(monthMaxDays-num3)+num1;
		Double pabayelDay=(num2+num4-num3)+num1;
		employeeDTO.getLeaveTypeList().add(pabayelDay);
  
		 for(int j=0;j<empsl.size();j++){
			  EmployeeSalaryDetDTO detDTO= empsl.get(j);
			  
			  
			  if(detDTO.getHeadType().equalsIgnoreCase("E")){
				  
				//To check earning Head Type is(constant,fixed or variable)...........
				  SalaryHeadDTO headDTO= getSalaryHeadDTO(Integer.parseInt(detDTO.getSalaryId()));
				  employeeDTO.getEarningheadTypeList().add(headDTO.getType());
			     employeeDTO.getEarningHeadList().add(detDTO.getAmount());
			  } if(detDTO.getHeadType().equalsIgnoreCase("D")){
				  
				//To check deduction Head Type is(constant,fixed or variable)...........
				  SalaryHeadDTO headDTO= getSalaryHeadDTO(Integer.parseInt(detDTO.getSalaryId()));     
				  employeeDTO.getDeductionheadTypeList().add(headDTO.getType());
				employeeDTO.getDedecationHeadList().add(detDTO.getAmount());
			  }
			  }if(operation.equalsIgnoreCase("allEmployee")){
		    advanceAmtList.add(0);
			dedectAdvanceAmntList.add(0);
		 }
		 
}} catch (Exception e) {
	// TODO Auto-generated catch block
		e.printStackTrace();
	}

//To get Advance amount of Previous Month
if(operation.equalsIgnoreCase("employeeByDept")){
	try{
	int mn= getMonthNumber(mothName);
	if(mn!=0){
		mn=mn-1;
	}
	String monthN= DataUtility.getMonthName(mn);
	List adml= storageSalaryDAO.getAdvanceAmountByMonthNameAndDeartment(monthN, deptId);
	for(int i=0;i<employeeList.size();i++){
		EmployeeDTO	employeeDTO=employeeList.get(i);
		if(adml!=null && adml.size()>0){
	for(int l=0;l<adml.size();l++){
	Object[] obj=(Object[])adml.get(l);
	Double balAvAmnt=(Double) obj[0];
		Double deductAvAmnt=(Double)obj[1];
		Integer emId=(Integer)obj[2];
		
	if(employeeDTO.getEmployeeId()==emId){
	    advanceAmtList.add(balAvAmnt);
		dedectAdvanceAmntList.add(deductAvAmnt);
	}/*else {
		advanceAmtList.add(0);
		dedectAdvanceAmntList.add(0);
	}*/}
		}else{
			advanceAmtList.add(0);
			dedectAdvanceAmntList.add(0);
		}

	}
	}catch(Exception ex){ex.printStackTrace();}
}
//To get Advance amount of Previous Month END.........
int mn= 0;
int totalDaysInMonth=0;
	if(operation.equalsIgnoreCase("employeeByDept")){
	
		 mn= getMonthNumber(mothName);
		 totalDaysInMonth= getDayOfMonth(mn);
	}else{
		mothName= DataUtility.getMonthName(new Date().getMonth());
	}

	SalaryMasterDTO masterDTO=new SalaryMasterDTO();
	masterDTO.setBalAdvanceList(advanceAmtList);
	masterDTO.setDedectAdvanceAmntList(dedectAdvanceAmntList);
	if(deptId!=null && deptId>0){
		masterDTO.setDepartmentId(deptId);
	}
	masterDTO.setSalaryMonth(mothName);
	masterDTO.setTotalDaysInMonth(totalDaysInMonth);
	Map map=new HashMap();
	map.put("header", header);
	map.put("employeeList", employeeList);
	map.put("masterDTO", masterDTO);
	return map;
}

@Override
public Integer getApprovedFlag(String monthName) {
	// TODO Auto-generated method stub
	return storageSalaryDAO.getApprovedFlag(monthName);
}


}
