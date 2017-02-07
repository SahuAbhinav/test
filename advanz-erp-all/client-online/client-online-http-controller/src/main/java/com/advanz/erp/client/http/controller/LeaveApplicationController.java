package com.advanz.erp.client.http.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.advanz.erp.client.http.controller.form.LeaveApplicationForm;
import com.advanz.erp.masters.model.EmployeeDTO;
import com.advanz.erp.masters.model.LeaveApplicationDTO;
import com.advanz.erp.masters.model.LeaveTypeMastDTO;
import com.advanz.erp.masters.model.msg.EmployeeOutputMessage;
import com.advanz.erp.masters.model.msg.LeaveApplicationInputMessage;
import com.advanz.erp.masters.model.msg.LeaveApplicationOutputMessage;
import com.advanz.erp.masters.model.msg.LeaveTypeMastOutputMessage;
import com.advanz.erp.masters.service.business.IEmployeeService;
import com.advanz.erp.masters.service.business.ILeaveApplicationService;
import com.advanz.erp.masters.service.business.ILeaveTypeMastService;

@Controller
public class LeaveApplicationController extends BaseController{
	
	@Autowired
	public ILeaveApplicationService applicationService;
	@Autowired
	public IEmployeeService employeeService;
	
	@Autowired
	public ILeaveTypeMastService leaveTypeMasterService;
	
	@RequestMapping(value="/show_leave_application")
	public ModelAndView doDisplay(@ModelAttribute("leaveApplicationForm") LeaveApplicationForm leaveApplicationForm,@RequestParam (value="operation",required=false)String operation,@RequestParam(value="sno",required=false) Integer sno, HttpSession session){
    ModelAndView mav = preloadedData();
	if("view".equalsIgnoreCase(operation) || "update".equalsIgnoreCase(operation) ||"Delete".equalsIgnoreCase(operation)){
		LeaveApplicationDTO applicationDTO = new LeaveApplicationDTO();
		applicationDTO.setSno(sno);
		LeaveApplicationInputMessage applicationInputMessage = new LeaveApplicationInputMessage();
		applicationInputMessage.setLeaveApplicationDTO(applicationDTO);
		LeaveApplicationOutputMessage applicationOutputMessage= applicationService.findLeaveApplicationById(applicationInputMessage);
		List<LeaveApplicationDTO> list= applicationOutputMessage.getLeaveApplicationDTOList();
		if(list!=null && list.size()>0){
			applicationDTO=list.get(0);
			leaveApplicationForm.setApplicationDTO(applicationDTO);
		}
		mav.addObject("operation", operation);
		mav.addObject("leaveApplicationForm", leaveApplicationForm);
		mav.setViewName("leave_application_entry");
		 return mav;
	}	
	
	
	mav.addObject("operation", operation);
	mav.addObject("leaveApplicationForm", leaveApplicationForm);
	mav.setViewName("leave_application_entry");
	 return mav;
	}
	
	@RequestMapping(value="/show_leave_application_list")
	public ModelAndView doDisplayList(@ModelAttribute("leaveApplicationForm") LeaveApplicationForm leaveApplicationForm,@RequestParam(value="operation",required=false) String operation,@RequestParam(value="menuId",required=false) String menuId,@RequestParam(value="sno",required=false) Integer sno, HttpSession session){
		 if(menuId!=null)
	   	  {
	  		session.setAttribute("menuId", menuId);
	  	  }
		 if("Delete".equalsIgnoreCase(operation)){
			 LeaveApplicationDTO applicationDTO = new LeaveApplicationDTO();
			 applicationDTO.setSno(sno);
			 LeaveApplicationInputMessage applicationInputMessage=new LeaveApplicationInputMessage();
			 applicationInputMessage.setLeaveApplicationDTO(applicationDTO);
			 applicationService.deleteLeaveApplication(applicationInputMessage);
		 }
		ModelAndView mav = new ModelAndView("leave_application_list");
	    LeaveApplicationOutputMessage applicationOutputMessage=applicationService.findAllLeaveApplication();
	    List<LeaveApplicationDTO> leaveList=  applicationOutputMessage.getLeaveApplicationDTOList();
	    
	    mav.addObject("leaveList", leaveList);
		mav.addObject("leaveApplicationForm", leaveApplicationForm);
	 return mav;
	}
	
	
	@RequestMapping(value="/save_leave_application", method = RequestMethod.POST)
	public ModelAndView doSubmit(@ModelAttribute("leaveApplicationForm") LeaveApplicationForm leaveApplicationForm,@RequestParam(value="operation",required=false) String operation, HttpSession session){
		
	LeaveApplicationDTO applicationDTO=	leaveApplicationForm.getApplicationDTO();
	LeaveApplicationInputMessage applicationInputMessage = new LeaveApplicationInputMessage();
	if(applicationDTO.getSno()!=null && applicationDTO.getSno()>0){
		applicationInputMessage.setLeaveApplicationDTO(applicationDTO);
		applicationDTO.setModifiedUserId(createdUserId);
		applicationService.updateLeaveApplication(applicationInputMessage);
	}
	applicationDTO.setCreatedUserId(createdUserId);
	applicationInputMessage.setLeaveApplicationDTO(applicationDTO);
	applicationService.createLeaveApplication(applicationInputMessage);
	 
	
	ModelAndView mav = new ModelAndView(new RedirectView("show_leave_application_list?operation=show"));
	 return mav;
	}
	protected ModelAndView preloadedData() {
		ModelAndView mav = new ModelAndView();
	//	EmployeeOutputMessage employeeOutputMessage = employeeService.findAllEmployee();
		EmployeeOutputMessage employeeOutputMessage = employeeService.preLoad();
		List<EmployeeDTO> employeeList = (ArrayList<EmployeeDTO>) employeeOutputMessage.getEmployeeDTOList();
		LeaveTypeMastOutputMessage leaveTypeMastOutputMessage= leaveTypeMasterService.findAllLeaveTypeMasts();
		List<LeaveTypeMastDTO> eaveTypeList = (ArrayList<LeaveTypeMastDTO>)leaveTypeMastOutputMessage.getLeaveTypeMastDTOList();
		
		mav.addObject("employeeList", employeeList);
		mav.addObject("eaveTypeList", eaveTypeList);
	return mav;
	}
}
