/*package com.advanz.erp.client.http.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.GetPassForm;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.service.business.IMastersService;

@Controller
public class BulkFiberMasterListController {
    @Autowired
    private IMastersService mastersService;
    
	@RequestMapping(value = "/get_bulkFiber_list", method = RequestMethod.GET)
	public ModelAndView display(
			@ModelAttribute("getPass") GetPassForm getPassForm,
			@RequestParam(value = "operation", required = false) String operation,
			HttpSession session,@RequestParam(value = "menuId", required = false) String menuId) {
		
		
		
		
		
		
		
		ModelAndView mav=new ModelAndView("bulkFiber-list");
		mav.addObject("gradeList", getGradeMastersList());
		mav.addObject("getPassForm", getPassForm);
		return mav;
	}
	
	 private List<MastersDTO> getGradeMastersList(){
	    	MastersInputMessage mastersInputMessage =new MastersInputMessage();
	    	//formid=16   --> Item Grade
	    	mastersInputMessage.setFormId(16);
	    	MastersOutputMessage mastersOutputMessage=	mastersService.findFormById(mastersInputMessage);
	    	return mastersOutputMessage.getMastersDTOList();
	    }
	    
}
*/