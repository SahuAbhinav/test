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

import com.advanz.erp.client.http.controller.form.DebitDutyForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.masters.model.DebitDutyMasterDTO;
import com.advanz.erp.masters.model.msg.DebitDutyMasterInputMessage;
import com.advanz.erp.masters.model.msg.DebitDutyMasterOutPutMessage;
import com.advanz.erp.masters.service.business.IDebitDutyMasterService;

@Controller
public class DebitDutyListController extends BaseController{

	@Autowired
	public IDebitDutyMasterService debitDutyMasterService;

	@RequestMapping(value = "/show_debit_duty_list", method = RequestMethod.GET)
	public ModelAndView display(@ModelAttribute("debitDuty") DebitDutyForm debitDutyForm,@RequestParam(value="menuId",required=false) String menuId,
			@RequestParam(value="operation",required=false) String operation, HttpSession session) {
if(operation==null){
	operation="show";
}
		if(menuId!=null)
    	{
    		session.setAttribute("menuId", menuId);
    	}
		
		session.removeAttribute("debitDuty");
		
		
		DebitDutyMasterOutPutMessage debitDutyMasterOutPutMessage = new DebitDutyMasterOutPutMessage();
		debitDutyMasterOutPutMessage = debitDutyMasterService.findAllDebit();
		
		
		List l = debitDutyMasterOutPutMessage.getDebitDutyMasterDTOList();
		
		ErrorDTO error = new ErrorDTO();
		if(operation.equals("Save")){
		error.setErrorMsg("Debit Duty has been save successfully");
		}
		if(operation.equals("Delete")){
			error.setErrorMsg("Debit Duty has been remove successfully");
		}
		if(operation.equals("Update")){
			error.setErrorMsg("Debit Duty has been update successfully");
		}
		
		
		ModelAndView mav = new ModelAndView("debit_duty_list");
		mav.addObject("error", error);
		mav.addObject("debitDutyForm", debitDutyForm);
		mav.addObject("debitDutyList", l);
		return mav;
	    }

	@RequestMapping(value = "/submit_debit_duty_List", method = RequestMethod.GET)
	public ModelAndView submit(@ModelAttribute("debitDuty") DebitDutyForm debitDutyForm,
			@RequestParam String operation, HttpSession session) {
		ModelAndView mav = new ModelAndView("debit_duty_list");
		if (operation.equals("Add")) {
			
			session.removeAttribute("debitDuty");
			ModelAndView mv = new ModelAndView(new RedirectView("show_debit_duty?operation=show"));
			return mv;
		}
		if (operation.equals("Delete")) {
			DebitDutyMasterInputMessage debitDutyMasterInputMessage = new DebitDutyMasterInputMessage();
			
			DebitDutyMasterDTO masterDTO = new DebitDutyMasterDTO();
			masterDTO.setDebitDutyAutoId(debitDutyForm.getDebitDutyAutoId());
			masterDTO.setModifiedUserId(getCreatedUserId());
			debitDutyMasterInputMessage.setDebitDutyMasterDTO(masterDTO);
			debitDutyMasterService.deleteDebitDuty(debitDutyMasterInputMessage);

			DebitDutyMasterOutPutMessage debitDutyMasterOutMessage = new DebitDutyMasterOutPutMessage();
			debitDutyMasterOutMessage = debitDutyMasterService.findAllDebit();
			List l = debitDutyMasterOutMessage.getDebitDutyMasterDTOList();
			
			
			
			mav.addObject("debitDutyForm", debitDutyForm);
			mav.addObject("debitDutyList", l);
			ErrorDTO dto=new ErrorDTO();
			dto.setErrorMsg("Debit Duty has been remove successfully");
			mav.addObject("error", dto);
			return mav;
		}
		if (operation.equals("Search")) {
			DebitDutyMasterDTO masterDTO = debitDutyForm.getDebitDutyMasterDTO();
			if(debitDutyForm.getApproveStatus()!=null && debitDutyForm.getApproveStatus().length()>0){
			if(debitDutyForm.getApproveStatus().equalsIgnoreCase("Approved")){
					masterDTO.setApprovedFlag(1);
			}
			}else{
				masterDTO.setApprovedFlag(0);	
			}
			DebitDutyMasterInputMessage debitDutyMasterInputMessage = new DebitDutyMasterInputMessage();
			debitDutyMasterInputMessage.setDebitDutyMasterDTO(masterDTO);
			DebitDutyMasterOutPutMessage debitDutyMasterOutMessage = debitDutyMasterService.search(debitDutyMasterInputMessage);
			ArrayList<DebitDutyMasterDTO> list = (ArrayList<DebitDutyMasterDTO>) debitDutyMasterOutMessage.getDebitDutyMasterDTOList();
			
			
			ErrorDTO  errorDTO=new ErrorDTO();
			if(list.equals(null) || list.size()==0)
			{
				errorDTO.setErrorMsg("Record Not Found !!!");
			}	
			
			mav.addObject("debitDutyForm", debitDutyForm);
			mav.addObject("debitDutyList", list);
			mav.addObject("error123", errorDTO);
			return mav;
		}
		
		if (operation.equals("Cancel")) {
			ModelAndView mv = new ModelAndView(new RedirectView(
					"show_debit_duty_list?operation=show"));
			return mv;
			
			
		}
		if (operation.equals("Edite")) {
			ModelAndView mv = new ModelAndView(new RedirectView("show_debit_duty?operation=Edite"));
			mv.addObject("debitDutyForm", debitDutyForm);
			return mv;
		}
		if (operation.equals("V")) {
			ModelAndView mv = new ModelAndView(new RedirectView("show_debit_duty?operation=V"));
			mv.addObject("debitDutyForm", debitDutyForm);
			return mv;
		}
		mav.addObject("debitDutyForm", debitDutyForm);
		return mav;
	}
}
