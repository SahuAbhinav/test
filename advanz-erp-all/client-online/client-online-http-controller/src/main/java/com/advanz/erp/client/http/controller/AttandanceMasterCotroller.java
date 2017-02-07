package com.advanz.erp.client.http.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.advanz.DataUtility;
import com.advanz.erp.client.http.controller.form.AttandanceMasterForm;
import com.advanz.erp.masters.model.AttandanceMasterDTO;
import com.advanz.erp.masters.model.CompanyDTO;
import com.advanz.erp.masters.model.EmployeeDTO;
import com.advanz.erp.masters.model.EmployeeLeavesDTO;
import com.advanz.erp.masters.model.LeaveApplicationDTO;
import com.advanz.erp.masters.model.LeaveTypeMastDTO;
import com.advanz.erp.masters.model.msg.AttandanceMasterInputMessage;
import com.advanz.erp.masters.model.msg.AttandanceMasterOutputMessage;
import com.advanz.erp.masters.model.msg.CompanyOutMessage;
import com.advanz.erp.masters.model.msg.EmployeeInputMessage;
import com.advanz.erp.masters.model.msg.EmployeeOutputMessage;
import com.advanz.erp.masters.model.msg.LeaveApplicationInputMessage;
import com.advanz.erp.masters.model.msg.LeaveApplicationOutputMessage;
import com.advanz.erp.masters.model.msg.LeaveTypeMastInputMessage;
import com.advanz.erp.masters.model.msg.LeaveTypeMastOutputMessage;
import com.advanz.erp.masters.service.business.IAttandanceMasterService;
import com.advanz.erp.masters.service.business.ICompanyService;
import com.advanz.erp.masters.service.business.IEmployeeService;
import com.advanz.erp.masters.service.business.ILeaveApplicationService;
import com.advanz.erp.masters.service.business.ILeaveTypeMastService;
import com.advanz.erp.masters.service.business.IMastersService;
import com.advanz.erp.masters.service.business.ISalaryMasterService;

@Controller
public class AttandanceMasterCotroller extends BaseController {

	@Autowired
	public IAttandanceMasterService attandanceMasterService;

	@Autowired
	public IEmployeeService employeeService;

	@Autowired
	public ILeaveTypeMastService leaveTypeMastService;

	@Autowired
	public ILeaveApplicationService leaveApplicationService;
	
	@Autowired
	public IMastersService masterService;
	
	@Autowired
	public ISalaryMasterService salaryMasterService;
	
	@Autowired
	public ICompanyService companyService;
	@RequestMapping(value = "/show_attandance")
	public ModelAndView doDispaly(@ModelAttribute("attandanceMasterForm") AttandanceMasterForm attandanceMasterForm,
			@RequestParam(value = "operation", required = false) String operation,@RequestParam(value = "date", required = false) String sdate,HttpSession session) {
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date date=null;
		try{
		 date= sdf.parse(sdate);
		}catch (Exception e) {
		}
		ModelAndView mav = new ModelAndView("attandance_entry");
		AttandanceMasterDTO attandanceMasterDTO = new AttandanceMasterDTO();
		DateFormat format2 = new SimpleDateFormat("EEEE");
		DateFormat joinDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String finalDay =null;
		String joinDate=null;
		if(date!=null){
			finalDay = format2.format(date);
			joinDate=joinDateFormat.format(date);
		}else{
		 finalDay = format2.format(new Date());
		 joinDate=joinDateFormat.format(new Date());
		}
		
		attandanceMasterDTO.setDayOfDate(finalDay);
     EmployeeInputMessage inputMessage=new EmployeeInputMessage();
     EmployeeDTO empDto=new EmployeeDTO();
     empDto.setJoinDateString(joinDate);
     inputMessage.setEmployeeDTO(empDto);
		EmployeeOutputMessage employeeOutputMessage = employeeService.findAllActivatedEmployeeByJoinDate(inputMessage);
		List<EmployeeDTO> employeeList = employeeOutputMessage.getEmployeeDTOList();
		LeaveTypeMastOutputMessage leaveTypeMastOutputMessage = leaveTypeMastService.findAllLeaveTypeMasts();
		ArrayList<LeaveTypeMastDTO> leaveList = (ArrayList<LeaveTypeMastDTO>) leaveTypeMastOutputMessage.getLeaveTypeMastDTOList();
		List<AttandanceMasterDTO> attEmpList = new ArrayList<AttandanceMasterDTO>();

		if(employeeList!=null && employeeList.size()>0){
		for (int i = 0; i < employeeList.size(); i++) {
			EmployeeDTO employeeDTO = employeeList.get(i);
			AttandanceMasterDTO attandanceMasterDTO2 = new AttandanceMasterDTO();
			attandanceMasterDTO2.setEmployeeName(employeeDTO.getEmployeeFullName());
			attandanceMasterDTO2.setEmployeeCode(employeeDTO.getEmployeeCode());
			attandanceMasterDTO2.setEmployeeId(employeeDTO.getEmployeeId());
			attandanceMasterDTO2.setAttandanceFlag(null);
			attandanceMasterDTO2.setDayStatus(null);
			//To get leave name from Application leave form
			String leaveName=null;
			/*try {
				if(date!=null){
					leaveName= getRecommondateLeave(employeeDTO,date);
				}else{
				leaveName= getRecommondateLeave(employeeDTO,new Date());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		  Boolean flag=false;
		/*try {
			flag = checkLeaveAvailability(employeeDTO.getEmployeeId(),leaveName);
			System.out.println("FLAGE................"+flag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
			if(flag==true){
				attandanceMasterDTO2.setWeakOff(leaveName);
			}
			if(employeeDTO.getWeekOff1()!=null && finalDay.equalsIgnoreCase(employeeDTO.getWeekOff1())){
				attandanceMasterDTO2.setWeakOff("WeakOff");
		    }if(employeeDTO.getWeekOff2()!=null && finalDay.equalsIgnoreCase(employeeDTO.getWeekOff2())){
		    	attandanceMasterDTO2.setWeakOff("WeakOff");
		    }
		    List l=null;
		    if(date!=null){
            l= masterService.findByDate(date);
		    }else{
		    	  l= masterService.findByDate(new Date());	
		    }
			String as=null;
			try {
				as = (String)l.get(0);
			   if(as!=null){
					  attandanceMasterDTO2.setWeakOff("Holiday");
			     }
			   } catch (Exception e) {
			 }
			attEmpList.add(attandanceMasterDTO2);
		}
	}
		attandanceMasterForm.setAttandanceMasterList(attEmpList);
		attandanceMasterForm.setEmployeeDTOList(employeeList);
		
		if(date!=null){
			attandanceMasterDTO.setDate(date);
		}else{
			attandanceMasterDTO.setDate(new Date());
		}
		
		AttandanceMasterInputMessage attandanceMasterInputMessage =new AttandanceMasterInputMessage();
		attandanceMasterInputMessage.setAttandanceMasterDTO(attandanceMasterDTO);
	    AttandanceMasterOutputMessage attandanceMasterOutputMessage=	attandanceMasterService.search(attandanceMasterInputMessage);
	    List<AttandanceMasterDTO> attandanceList=  attandanceMasterOutputMessage.getAttandanceMasterDTOList();
	  
	    if(attandanceList!=null && attandanceList.size()>0){
	    	ArrayList<AttandanceMasterDTO> al= new ArrayList<AttandanceMasterDTO>();
			for(int i=0;i<attandanceList.size();i++){
				AttandanceMasterDTO dto = attandanceList.get(i);
				al.add(dto);
			}
		 if(al.size()<attEmpList.size()){
			for(int i=0;i<al.size();i++)
			 {
			 for(int j=0;j<attEmpList.size();j++){
				if(al.get(i).getEmployeeId().equals(attEmpList.get(j).getEmployeeId())){
					attEmpList.set(j, al.get(i));
				}
			  }
			 }
		  }
			attandanceMasterForm.setAttandanceMasterList(attEmpList);
			attandanceMasterForm.setAttandanceMasterDTO(attandanceMasterDTO);
			mav.addObject("attandanceMasterForm", attandanceMasterForm);
			//mav.addObject("employeeList", employeeList);
			mav.addObject("leaveList", leaveList);
			return mav;
		}
		attandanceMasterForm.setAttandanceMasterDTO(attandanceMasterDTO);
		mav.addObject("attandanceMasterForm", attandanceMasterForm);
		//mav.addObject("employeeList", employeeList);
		mav.addObject("leaveList", leaveList);
		return mav;
	    }

	
	@RequestMapping(value = "/save_attandance")
	public ModelAndView doSubmit(@ModelAttribute("attandanceMasterForm") AttandanceMasterForm attandanceMasterForm,
			@RequestParam(value = "operation", required = false) String operation,HttpSession session) {
		    ModelAndView mav = new ModelAndView("attandance_entry");
		    AttandanceMasterDTO attandanceMasterDTO = attandanceMasterForm.getAttandanceMasterDTO();
		
		    EmployeeInputMessage empInputMessage=new EmployeeInputMessage();
		    EmployeeDTO empDto=new EmployeeDTO();
		    DateFormat joinDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    String joinDate=joinDateFormat.format(attandanceMasterDTO.getDate());
		    empDto.setJoinDateString(joinDate);
		    empInputMessage.setEmployeeDTO(empDto);
		    EmployeeOutputMessage employeeOutputMessage = employeeService.findAllActivatedEmployeeByJoinDate(empInputMessage);
			List<EmployeeDTO> employeeList = employeeOutputMessage.getEmployeeDTOList();
		
			LeaveTypeMastOutputMessage leaveTypeMastOutputMessage = leaveTypeMastService.findAllLeaveTypeMasts();
			List<LeaveTypeMastDTO> leaveList = leaveTypeMastOutputMessage.getLeaveTypeMastDTOList();
			    
	if ("search".equalsIgnoreCase(operation)) {}
		if ("Save".equalsIgnoreCase(operation)) {
			//To delete records
			try {
				System.out.println("attandanceMasterDTO: "+attandanceMasterDTO);
				if(attandanceMasterDTO.getDate()!=null){
					AttandanceMasterInputMessage attandanceMasterInputMessage = new AttandanceMasterInputMessage();
					attandanceMasterInputMessage.setAttandanceMasterDTO(attandanceMasterDTO);
					attandanceMasterService.deleteAttandanceMaster(attandanceMasterInputMessage);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//end delete records
			List<AttandanceMasterDTO> list = attandanceMasterForm.getAttandanceMasterList();
			
			for (int i = 0; i < list.size(); i++) {
				AttandanceMasterDTO adto = list.get(i);
				 adto.setDate(attandanceMasterDTO.getDate());
				adto.setDayOfDate(attandanceMasterDTO.getDayOfDate());
				if(adto.getAttandanceFlag()==null){
					adto.setDayStatus(null);
				}
				AttandanceMasterInputMessage attandanceMasterInputMessage = new AttandanceMasterInputMessage();
				attandanceMasterInputMessage.setAttandanceMasterDTO(adto);
				attandanceMasterService.createAttandanceMaster(attandanceMasterInputMessage);
			}
			
		}else{
			DateFormat format2 = new SimpleDateFormat("EEEE");
			String finalDay = format2.format(attandanceMasterDTO.getDate());
			attandanceMasterDTO.setDayOfDate(finalDay);
			List l= masterService.findByDate(attandanceMasterDTO.getDate());
			String as=null;
			try {
				as = (String)l.get(0);
			  } catch (Exception e) {
			}
			
			AttandanceMasterInputMessage attandanceMasterInputMessage=new AttandanceMasterInputMessage();
		    attandanceMasterInputMessage.setAttandanceMasterDTO(attandanceMasterDTO);
		    AttandanceMasterOutputMessage attandanceMasterOutputMessage= attandanceMasterService.search(attandanceMasterInputMessage);
		    List<AttandanceMasterDTO> al= attandanceMasterOutputMessage.getAttandanceMasterDTOList();
		   if(al!=null && al.size()>0){
			   for(int i=0;i<al.size();i++){
				   AttandanceMasterDTO amdto=  al.get(i);
				   if(amdto.getAttandanceFlag()!=null ){
					   if(amdto.getAttandanceFlag().equalsIgnoreCase("Holiday")||amdto.getAttandanceFlag().equalsIgnoreCase("WeakOff")){
					   amdto.setWeakOff(amdto.getAttandanceFlag());
					   }
				   }else{
					   if(as!=null)
					    {
						   amdto.setWeakOff("Holiday");
					    }else{
					   amdto.setWeakOff(null);
					    }
				   }
			   }
			   
			if(al.size()<employeeList.size())
			  {
				List<AttandanceMasterDTO> attEmpList = new ArrayList<AttandanceMasterDTO>();

				for (int i = 0; i < employeeList.size(); i++) {
					EmployeeDTO employeeDTO = employeeList.get(i);
					AttandanceMasterDTO attandanceMasterDTO2 = new AttandanceMasterDTO();
					attandanceMasterDTO2.setEmployeeName(employeeDTO.getEmployeeFullName());
					attandanceMasterDTO2.setEmployeeCode(employeeDTO.getEmployeeCode());
					attandanceMasterDTO2.setEmployeeId(employeeDTO.getEmployeeId());
					attandanceMasterDTO2.setAttandanceFlag(null);
					attandanceMasterDTO2.setDayStatus(null);
					//To get leave name from Application leave form
					String leaveName=null;
					/*try {
						if(attandanceMasterDTO.getDate()!=null){
							leaveName= getRecommondateLeave(employeeDTO,attandanceMasterDTO.getDate());
						}else{
						leaveName= getRecommondateLeave(employeeDTO,new Date());
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
				  Boolean flag=false;
				/*try {
					flag = checkLeaveAvailability(employeeDTO.getEmployeeId(),leaveName);
					System.out.println("FLAGE................"+flag);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
					if(flag==true){
						attandanceMasterDTO2.setWeakOff(leaveName);
					}
					if(employeeDTO.getWeekOff1()!=null && finalDay.equalsIgnoreCase(employeeDTO.getWeekOff1())){
						attandanceMasterDTO2.setWeakOff("WeakOff");
				    }if(employeeDTO.getWeekOff2()!=null && finalDay.equalsIgnoreCase(employeeDTO.getWeekOff2())){
				    	attandanceMasterDTO2.setWeakOff("WeakOff");
				    }
				    List masterLst=null;
				    if(attandanceMasterDTO.getDate()!=null){
				    	masterLst= masterService.findByDate(attandanceMasterDTO.getDate());
				    }else{
				    	masterLst= masterService.findByDate(new Date());	
				    }
					String ad=null;
					try {
						ad = (String)l.get(0);
						if(ad!=null){
							  attandanceMasterDTO2.setWeakOff("Holiday");
					}
					  } catch (Exception e) {
					}
					attEmpList.add(attandanceMasterDTO2);
				}
				  
			   for(int i=0;i<al.size();i++)
				 {
				 for(int j=0;j<attEmpList.size();j++){
					if(al.get(i).getEmployeeId().equals(attEmpList.get(j).getEmployeeId())){
						attEmpList.set(j, al.get(i));
					}
				  }
				 }attandanceMasterForm.setAttandanceMasterList(attEmpList);}
			  else{attandanceMasterForm.setAttandanceMasterList(al);}
		   }else{
			  //If attandence is not available for selected date than forward to open new entry form 
			   SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			   String sdate= sdf.format(attandanceMasterDTO.getDate());
			   ModelAndView mv = new ModelAndView(new RedirectView("show_attandance?date="+sdate));
			   return mv;
		   }
		}
		attandanceMasterForm.setAttandanceMasterDTO(attandanceMasterDTO);
		attandanceMasterForm.setEmployeeDTOList(employeeList);
		mav.addObject("attandanceMasterForm", attandanceMasterForm);
			mav.addObject("attandenceFlag", "Attendence  Saved Successfully");
		//mav.addObject("employeeList", employeeList);
		mav.addObject("leaveList", leaveList);
		return mav;
	}

	@RequestMapping(value = "/getDay", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse getDay(@RequestParam(value = "date") String date,
			HttpSession session) {
		JsonResponse res = new JsonResponse();
		DateFormat readFormat = new SimpleDateFormat("dd/MM/yyyy");
		// String str_date="11-June-07";
		DateFormat formatter;
		Date date1 = null;
		formatter = new SimpleDateFormat("dd-MMM-yy");
		try {
			date1 = formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String s = readFormat.format(date1);
		DateFormat format2 = new SimpleDateFormat("EEEE");
		String finalDay = format2.format(date1);
		res.setResult(finalDay);
		return res;
	}

	public String getRecommondateLeave(EmployeeDTO employeeDTO,Date date){
		LeaveApplicationDTO leaveApplicationDTO = new LeaveApplicationDTO();
		LeaveApplicationInputMessage leaveApplicationInputMessage = new LeaveApplicationInputMessage();
		leaveApplicationDTO.setEmployeeDTO(employeeDTO);
		
		leaveApplicationDTO.setDate(date);
		leaveApplicationInputMessage.setLeaveApplicationDTO(leaveApplicationDTO);
	    LeaveApplicationOutputMessage leaveApplicationOutputMessage=leaveApplicationService.findLeaveByEmployeeIdAndDate(leaveApplicationInputMessage);
	    List<LeaveApplicationDTO> list=leaveApplicationOutputMessage.getLeaveApplicationDTOList();
		if(list!=null && list.size()>0){
		leaveApplicationDTO= list.get(0);
		}
	String leaveName=null;
	try {
		leaveName = leaveApplicationDTO.getLeaveTypeMastDTO().getLeaveName();
		System.out.println("leaveName.............................."+leaveName);
	} catch (Exception e) {
	  e.printStackTrace();
	}
	
	return leaveName;
	}
	
	@RequestMapping(value = "/chech_leave_avalability")
	public @ResponseBody
	String getItemById(@RequestParam("employeeId") Integer employeeId,@RequestParam("leaveType") String leaveType) {
		
		// Find Employee List
		EmployeeDTO employeeDTO=new EmployeeDTO();
		employeeDTO.setEmployeeId(employeeId);
		EmployeeInputMessage employeeInputMessage=new EmployeeInputMessage();
		employeeInputMessage.setEmployeeDTO(employeeDTO);
		EmployeeOutputMessage employeeOutputMessage= employeeService.findEmployeeById(employeeInputMessage);
		List<EmployeeDTO> employeeList= employeeOutputMessage.getEmployeeDTOList();
		List<EmployeeLeavesDTO> employeeLeaveList =null;
		if(employeeList!=null && employeeList.size()>0){
		employeeDTO=employeeList.get(0);
		employeeLeaveList = employeeList.get(0).getEmployeeLeavesDTOList();
		}
				
		//Get Leave from leave Type Master
		LeaveTypeMastDTO leaveTypeMastDTO=new LeaveTypeMastDTO();
		leaveTypeMastDTO.setLeaveName(leaveType);
		LeaveTypeMastInputMessage leaveTypeMastInputMessage = new LeaveTypeMastInputMessage();
		leaveTypeMastInputMessage.setLeaveTypeMastDTO(leaveTypeMastDTO);
	    LeaveTypeMastOutputMessage leaveTypeMastOutputMessage=leaveTypeMastService.findByLeaveName(leaveTypeMastInputMessage);
	    List<LeaveTypeMastDTO> leaveTypeList=leaveTypeMastOutputMessage.getLeaveTypeMastDTOList();
		if(leaveTypeList!=null && leaveTypeList.size()>0){
			leaveTypeMastDTO=leaveTypeList.get(0);
			//getDateAccordingToFinacialYear(companyDTO.getFinancialYrBeg(),leaveTypeMastDTO.getLeaveType());
		}
		Date joiningDateAfterAddAllowDays=null;
		try {
			joiningDateAfterAddAllowDays = DataUtility.addDayInDate(employeeDTO.getJoinDate(), leaveTypeMastDTO.getApplicableDays());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		Boolean flag=true;
		Calendar c = Calendar.getInstance();   // this takes current date
	    c.set(Calendar.DAY_OF_MONTH, 1);
	 // To get company info and start date and end date
		Map map=null;
		try {
			map = getFromDateAndToDate(employeeDTO.getJoinDate(), leaveTypeMastDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  Date fromDate=(Date)map.get("fromDate");
		  Date toDate=(Date)map.get("tDate");
	    
		Double d= attandanceMasterService.coutLeaves(employeeId, leaveType,fromDate, toDate);
		System.out.println("AVAILABLE LEAVES:::"+d+" ALLOW LEAVES............. ");
		if(joiningDateAfterAddAllowDays.getTime()<new Date().getTime()){
		if(d!=null){
			EmployeeLeavesDTO leavesDTO=null;
			if("CASUAL LEAVE".equalsIgnoreCase(leaveType)){
			 leavesDTO=employeeLeaveList.get(0);
			}if("EARNING LEAVE".equalsIgnoreCase(leaveType)){
			 leavesDTO=employeeLeaveList.get(1);
			}if("MEDICAL LEAVE".equalsIgnoreCase(leaveType)){
			 leavesDTO=employeeLeaveList.get(2);
			}
			
			if(d<leavesDTO.getAllowDays()){
				Double remainingLeave= leavesDTO.getAllowDays()-d;
				if(remainingLeave>0 && remainingLeave==.5){
					return	"FDNA";	
				}
				return "FDA";
			//flag=true;
			}else{
			//flag=false;
			return	"NA";
			}}
		}		
		return "FDA";
	   }
	
	
	
	public Boolean checkLeaveAvailability(Integer employeeId,String leaveType)throws Exception{
		
		
		
		EmployeeDTO employeeDTO=new EmployeeDTO();
		employeeDTO.setEmployeeId(employeeId);
		EmployeeInputMessage employeeInputMessage=new EmployeeInputMessage();
		employeeInputMessage.setEmployeeDTO(employeeDTO);
		EmployeeOutputMessage employeeOutputMessage= employeeService.findEmployeeById(employeeInputMessage);
		List<EmployeeDTO> employeeList= employeeOutputMessage.getEmployeeDTOList();
		List<EmployeeLeavesDTO> employeeLeaveList =null;
		if(employeeList!=null && employeeList.size()>0){
		employeeDTO=employeeList.get(0);
		employeeLeaveList = employeeList.get(0).getEmployeeLeavesDTOList();
		}
		
		//Get Leave from leave Type Master
		LeaveTypeMastDTO leaveTypeMastDTO=new LeaveTypeMastDTO();
		leaveTypeMastDTO.setLeaveName(leaveType);
		LeaveTypeMastInputMessage leaveTypeMastInputMessage = new LeaveTypeMastInputMessage();
		leaveTypeMastInputMessage.setLeaveTypeMastDTO(leaveTypeMastDTO);
	    LeaveTypeMastOutputMessage leaveTypeMastOutputMessage=leaveTypeMastService.findByLeaveName(leaveTypeMastInputMessage);
	    List<LeaveTypeMastDTO> leaveTypeList=leaveTypeMastOutputMessage.getLeaveTypeMastDTOList();
		if(leaveTypeList!=null && leaveTypeList.size()>0){
			leaveTypeMastDTO=leaveTypeList.get(0);
			//getDateAccordingToFinacialYear(companyDTO.getFinancialYrBeg(),leaveTypeMastDTO.getLeaveType());
		}
		Date joiningDateAfterAddAllowDays=null;
		try {
			joiningDateAfterAddAllowDays = DataUtility.addDayInDate(employeeDTO.getJoinDate(), leaveTypeMastDTO.getApplicableDays());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// To get company info and start date and end date
		Map map= getFromDateAndToDate(employeeDTO.getJoinDate(), leaveTypeMastDTO);
		  Date fromDate=(Date)map.get("fromDate");
		  Date toDate=(Date)map.get("tDate");
		
		Boolean flag=true;
		Calendar c = Calendar.getInstance();   // this takes current date
	    c.set(Calendar.DAY_OF_MONTH, 1);
	  
	    
		Double d= attandanceMasterService.coutLeaves(employeeId, leaveType, fromDate,toDate);
	   
		if(joiningDateAfterAddAllowDays.getTime()<new Date().getTime()){
		if(d!=null && d>0){
			EmployeeLeavesDTO leavesDTO=null;
			if("CASUAL LEAVE".equalsIgnoreCase(leaveType)){
			 leavesDTO=employeeLeaveList.get(0);
			}if("EARNING LEAVE".equalsIgnoreCase(leaveType)){
			 leavesDTO=employeeLeaveList.get(1);
			}if("MEDICAL LEAVE".equalsIgnoreCase(leaveType)){
			 leavesDTO=employeeLeaveList.get(2);
			}
			if(d<=leavesDTO.getAllowDays()){
			flag=true;
			}else{
			flag=false;
			}
		}}
		 System.out.println("Attandance Flag................................"+d);
		return flag;
	}
	public Map getFromDateAndToDate(Date employeeJoiningDate,LeaveTypeMastDTO leaveTypeMastDTO)throws Exception{
		Map map=null;
		//Get Company
		CompanyOutMessage companyOutMessage= companyService.findAllCompanies();
		List<CompanyDTO> companyList= companyOutMessage.getCompanyDTOList();
		if(companyList!=null && companyList.size()>0){
		CompanyDTO companyDTO=companyList.get(0);
		
	   map= getDateAccordingToFinacialYear(companyDTO.getFinancialYrBeg(),leaveTypeMastDTO.getLeaveType(),leaveTypeMastDTO.getApplicableDays(),employeeJoiningDate);
		Date fromDate=(Date)map.get("fromDate");
		Date toDate=(Date)map.get("tDate");
		  }
		return map;	
	}
public Map getDateAccordingToFinacialYear(Date finacialBigin,String leaevType,Integer applicableDays,Date joiningDate)throws Exception {
	Map map=new HashMap();
	Date toDate=null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd");
	 int day = Integer.parseInt(sdf.format(finacialBigin));
	if("yearly".equalsIgnoreCase(leaevType)){
		
	 map.put("fromDate", finacialBigin);
	 map.put("tDate",DataUtility.getLastDateOfMonth(DataUtility.addMonths(new Date(), 11)));
	}
	if("halfYearly".equalsIgnoreCase(leaevType)){
	
		int months=finacialBigin.getMonth()%100;
	    int currentDateMonth=new Date().getMonth()%100;
		if(currentDateMonth<=months+6){
		   finacialBigin =finacialBigin;
		   toDate=DataUtility.getLastDateOfMonth(DataUtility.addMonthInMonth(finacialBigin, 5));
		}
		else{
			finacialBigin =DataUtility.addMonthInMonth(finacialBigin, months+6);
			toDate =DataUtility.getLastDateOfMonth(DataUtility.addMonthInMonth(finacialBigin, 11));
	      }
		  map.put("fromDate", finacialBigin);
		  map.put("tDate",toDate);	
	}
   if("quarterly".equalsIgnoreCase(leaevType)){
	   int months=finacialBigin.getMonth()%100;
	   int currentDateMonth=new Date().getMonth()%100;
		if(currentDateMonth<=months+2){
		   finacialBigin =finacialBigin;
		   toDate=DataUtility.getLastDateOfMonth(DataUtility.addMonthInMonth(finacialBigin, 2));
		} if(months+3<=months+5){
			finacialBigin =DataUtility.addMonthInMonth(finacialBigin, months+3);
			toDate =DataUtility.getLastDateOfMonth(DataUtility.addMonthInMonth(finacialBigin, 5));
		}if(months+6<=months+8){
			finacialBigin =DataUtility.addMonthInMonth(finacialBigin, months+9);
			toDate =DataUtility.getLastDateOfMonth(DataUtility.addMonthInMonth(finacialBigin, 8));
		}
		if(months+9<=months+11){
			finacialBigin =DataUtility.addMonthInMonth(finacialBigin, months+12);
			toDate =DataUtility.getLastDateOfMonth(DataUtility.addMonthInMonth(finacialBigin, 11));
		}
		map.put("fromDate", finacialBigin);
		map.put("tDate",toDate);
	}if("monthly".equalsIgnoreCase(leaevType)){
	 Calendar c = Calendar.getInstance();   // this takes current date
	 c.set(Calendar.DAY_OF_MONTH, 1);
	 map.put("fromDate", c.getTime()); // this takes first date of month
	 map.put("tDate", new Date());
	}
	return map;
	}

//for show new attandance employee wise form 
	@RequestMapping(value = "/new_attandanceEmployee")
	public ModelAndView getAttendance(
			@ModelAttribute("attandanceMasterForm") AttandanceMasterForm attandanceMasterForm,
			@RequestParam(value = "operation", required = false) String operation,
			@RequestParam(value = "date", required = false) String sdate,
			HttpSession session) {
		ModelAndView mav = new ModelAndView("attandanceForm");
		AttandanceMasterDTO attandanceMasterDTO2 = new AttandanceMasterDTO();
		EmployeeOutputMessage employeeOutputMessage = employeeService
				.findAllActivatedEmployee();
		List<EmployeeDTO> employeeList = employeeOutputMessage
				.getEmployeeDTOList();
		List<AttandanceMasterDTO> alFormList = new ArrayList<AttandanceMasterDTO>();
		System.out.println("size of employee list" + employeeList.size());
		if(attandanceMasterForm==null)
		{
			attandanceMasterForm=new AttandanceMasterForm();
		}
		 Integer year=Calendar.getInstance().get(Calendar.YEAR);
	     attandanceMasterDTO2.setYear(year);
		 Integer Month= Calendar.getInstance().get(Calendar.MONTH);
		 String month=Month.toString();
		 attandanceMasterDTO2.setMonth(month);
		attandanceMasterForm.setAttandanceMasterDTO(attandanceMasterDTO2);
		mav.addObject("attandanceDateMasterForm",attandanceMasterForm);
		mav.addObject("employeeList", employeeList);
		return mav;
	}
	
     // for save date wise entry
	  @RequestMapping(value = "/save_attancedance_date")
     public ModelAndView saveAttendance(@ModelAttribute("attandanceMasterForm") AttandanceMasterForm attandanceMasterForm,
		@RequestParam(value = "operation", required = false) String operation,
		HttpSession session) 
         {
	   ModelAndView mav = new ModelAndView("attandanceForm");
	   AttandanceMasterDTO attandanceMasterDTO = attandanceMasterForm.getAttandanceMasterDTO();
	   System.out.println("EmployeeName.............."+attandanceMasterDTO.getEmployeeName());
	   System.out.println("EmployeeCode.............."+attandanceMasterDTO.getEmployeeCode());
	   EmployeeOutputMessage employeeOutputMessage = employeeService.findAllActivatedEmployee();
	   List<EmployeeDTO> employeeList = employeeOutputMessage.getEmployeeDTOList();
	   List<AttandanceMasterDTO> alFormList = new ArrayList<AttandanceMasterDTO>();
	   mav.addObject("employeeList",employeeList );
	   if("Save".equalsIgnoreCase(operation))
	          {
        AttandanceMasterInputMessage attandanceMasterInputMessage = new AttandanceMasterInputMessage();
	     attandanceMasterInputMessage.setAttandanceMasterDTO(attandanceMasterDTO);
	    List<AttandanceMasterDTO> list = attandanceMasterForm.getAttandanceMasterList();
	    for(int i=0;i<list.size();i++)
	    	{
	    attandanceMasterDTO.setDate(list.get(i).getDate());
     	AttandanceMasterInputMessage attandanceMasterInputMessage1 = new AttandanceMasterInputMessage();
	    attandanceMasterInputMessage1.setAttandanceMasterDTO(attandanceMasterDTO);
		attandanceMasterService.deleteAttandanceBYEmp(attandanceMasterInputMessage);
	    	}
	    	int empId=attandanceMasterDTO.getEmployeeId();
	    	String empCode=attandanceMasterDTO.getEmployeeCode();
	    	String empName=attandanceMasterDTO.getEmployeeName();
	    	for (int i = 0; i < list.size(); i++)
	    	{
	    	    AttandanceMasterDTO attMasterDTO = new AttandanceMasterDTO(); 
	    		attMasterDTO= list.get(i);
	    		attMasterDTO.setEmployeeId(empId);
	    		attMasterDTO.setEmployeeCode(empCode);
	    		attMasterDTO.setEmployeeName(empName);
	    		attandanceMasterInputMessage.setAttandanceMasterDTO(attMasterDTO);
	    		attandanceMasterService.createAttandanceMaster(attandanceMasterInputMessage);
	    	}
	     }
		return mav;
	     }

@RequestMapping(value = "/get_Day", method = RequestMethod.GET)
public ModelAndView getDayOfMonth(@RequestParam("month") Integer month,@RequestParam("Year") Integer Year,@RequestParam("employee")Integer employee,
@ModelAttribute("attandanceMasterForm") AttandanceMasterForm attandanceMasterForm) throws ParseException
	 {
	  int daysInMonth=getDaysInMonth(month,Year);
	  ModelAndView mav=new ModelAndView("attandanceForm");
 if(attandanceMasterForm==null)
  {
	   attandanceMasterForm= new AttandanceMasterForm();
  }
  EmployeeDTO employeeDTO =new EmployeeDTO();
  AttandanceMasterDTO attandanceMasterDTO=new AttandanceMasterDTO();
	   EmployeeOutputMessage employeeOutputMessage = employeeService.findAllActivatedEmployee();
   List<EmployeeDTO> employeeList = employeeOutputMessage.getEmployeeDTOList();
   List<AttandanceMasterDTO> alFormList = new ArrayList<AttandanceMasterDTO>();
	String employeeCode=null;
	String employeeName=null;
   for(int j=0;j<employeeList.size();j++)
	  {
      if(employee.equals(employeeList.get(j).getEmployeeId()))
	    {
	      employeeDTO=employeeList.get(j);
	      employeeCode=employeeDTO.getEmployeeCode();
	      employeeName=employeeDTO.getEmployeeFullName();
	      attandanceMasterDTO.setEmployeeCode(employeeCode);
         attandanceMasterDTO.setEmployeeName(employeeName);
	    }
    }
	    month=month+1;
	    String k;
	   for(int i=1;i<=daysInMonth;i++)
	   {
		   attandanceMasterDTO=new AttandanceMasterDTO();
	       k=i +"-"+month +"-"+ Year;
	 	   SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	       Date date = sdf.parse(k);
	       String sdate= sdf.format(date);
	       DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	       Date  date1 = (Date) df.parse(sdate);
	       List list=null;
		    if(date1!=null)
		    {
		    	list= masterService.findByDate(date1);
		    }
		    else
		    {
		    	list= masterService.findByDate(new Date());	
		    }
		     String as=null;
			as = (String)list.get(0);
			if(as!=null)
			 {
				attandanceMasterDTO.setWeakOff("Holiday");
				attandanceMasterDTO.setAttandanceFlag("Holiday");
               }
				attandanceMasterDTO.setDate(date1);
				attandanceMasterDTO.setEmployeeCode(employeeCode);
				attandanceMasterDTO.setEmployeeName(employeeName);
				
	           String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date1);
	           // to set weeak off
	           if(employeeDTO.getWeekOff1()!=null&& dayOfWeek.equalsIgnoreCase(employeeDTO.getWeekOff1()))
		        {
			     attandanceMasterDTO.setWeakOff("WeakOff");
			     attandanceMasterDTO.setAttandanceFlag("WeakOff");
			    }
	           // to set weeak off
		      if(employeeDTO.getWeekOff1()!=null&& dayOfWeek.equalsIgnoreCase(employeeDTO.getWeekOff2()))
		      {
			   attandanceMasterDTO.setWeakOff("WeakOff");
			   attandanceMasterDTO.setAttandanceFlag("WeakOff");
			  }
		 attandanceMasterDTO.setDayOfDate(dayOfWeek);
		 attandanceMasterDTO.setTempDate(sdate);
	 	 alFormList.add(attandanceMasterDTO);
	   }
	   attandanceMasterDTO.setYear(Year);
	   month=month-1;
	   String M=Integer.toString(month);
	   attandanceMasterDTO.setMonth(M);
	   attandanceMasterDTO.setEmployeeId(employee);
	   AttandanceMasterInputMessage attandanceMasterInputMessage=new AttandanceMasterInputMessage();
	   attandanceMasterInputMessage.setAttandanceMasterDTO(attandanceMasterDTO);
      AttandanceMasterOutputMessage attandanceMasterOutputMessage=attandanceMasterService.findAttandance(attandanceMasterInputMessage);
      List<AttandanceMasterDTO> attandanceDBList = attandanceMasterOutputMessage.getAttandanceMasterDTOList();
     // for check that attandance record present or not from db
      for(int j=0;j<attandanceDBList.size();j++)
       {
	   SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	   String sdate= sdf.format(attandanceDBList.get(j).getDate());
	   for(int i=0;i<alFormList.size();i++)
        {
		if(alFormList.get(i).getTempDate().equals(sdate))
        {
       attandanceMasterDTO=new AttandanceMasterDTO();
       // to show select value for drop-down month year or employee
       attandanceMasterDTO.setMonth(M);
       attandanceMasterDTO.setYear(Year);
       attandanceMasterDTO.setEmployeeId(employee);
       DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
       Date  date1 = (Date) df.parse(alFormList.get(i).getTempDate());
       attandanceMasterDTO.setDate(date1);
       String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date1);
		attandanceMasterDTO.setDayOfDate(dayOfWeek);
		
		attandanceMasterDTO.setEmployeeCode(employeeDTO.getEmployeeCode());
		attandanceMasterDTO.setEmployeeName(employeeDTO.getEmployeeFullName());
       
		attandanceMasterDTO.setDate(date1);
       attandanceMasterDTO.setTempDate(alFormList.get(i).getTempDate());
       attandanceMasterDTO.setAttandanceFlag(attandanceDBList.get(j).getAttandanceFlag());
       attandanceMasterDTO.setDayStatus(attandanceDBList.get(j).getDayStatus());
       
       alFormList.set(i,attandanceMasterDTO);  
       System.out.println(attandanceMasterDTO.getEmployeeCode()+"");
            }   		    	 		
         }
       }
      System.out.println("Year............."+attandanceMasterDTO.getYear());
    attandanceMasterForm.setAttandanceMasterList(alFormList);
    attandanceMasterForm.setAttandanceMasterDTO(attandanceMasterDTO);
    mav.addObject("attandanceDateMasterForm",attandanceMasterForm);
    mav.addObject("employeeList",employeeList);
    attandanceMasterForm.setSucc("show");
    return mav;
     }

// to calculate maximum number of days
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
}
