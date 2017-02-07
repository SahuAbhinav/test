package com.advanz.erp.client.http.controller;

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

import com.advanz.erp.client.http.controller.form.ReturnGetPassForm;
import com.advanz.erp.masters.model.ReturnGetPassMasterDTO;
import com.advanz.erp.masters.model.msg.ReturnGetPassMasterInputMessage;
import com.advanz.erp.masters.model.msg.ReturnGetPassMasterOutputMessage;
import com.advanz.erp.masters.service.business.IReturnGetPassMasterService;

@Controller
public class ReturnGetPassListController extends BaseController{
@Autowired
public IReturnGetPassMasterService returnGetPassMasterService;

@RequestMapping(value = "/show_return_get_pass_list", method = RequestMethod.GET)
public ModelAndView display(@ModelAttribute("returnGetPass") ReturnGetPassForm returnGetPassForm,@RequestParam(value="operation",required=false) String operation,HttpSession session,@RequestParam(value="next",required=false) Integer next,@RequestParam(value="menuId",required=false) String menuId) {
	
	//ReturnGetPassMasterOutputMessage getPassMasterOutputMessage= returnGetPassMasterService.findAllReturnGetPassMaster();
	ReturnGetPassMasterOutputMessage getPassMasterOutputMessage= null;
	ReturnGetPassMasterInputMessage returnGetPassMasterInputMessage=new ReturnGetPassMasterInputMessage();
	 if(next==null ||next<0)
		{
		next=0;
		returnGetPassMasterInputMessage.setNext(next);
		 getPassMasterOutputMessage= returnGetPassMasterService.findReturnGetPassPagination(returnGetPassMasterInputMessage);
		}
		else
		{
			returnGetPassMasterInputMessage.setNext(next);
			 getPassMasterOutputMessage= returnGetPassMasterService.findReturnGetPassPagination(returnGetPassMasterInputMessage);
		}
	 returnGetPassForm.setNext(next);
	 returnGetPassForm.setPrevious(next);
	    
	
	
	
	List<ReturnGetPassMasterDTO> returnGetPassList= getPassMasterOutputMessage.getReturnGetPassMasterDTOList();
	if(menuId!=null)
	{
	session.setAttribute("menuId", menuId);
	}
	session.removeAttribute("returnGetPass");
	returnGetPassForm.setMessage(operation);
	ModelAndView mav = new ModelAndView("return_get_pass_list");
	mav.addObject("returnGetPassList", returnGetPassList);
	mav.addObject("returnGetPassForm", returnGetPassForm);
	return	mav;
}
@RequestMapping(value = "/submitReturnGetPassList", method = RequestMethod.GET)
public ModelAndView submit(@ModelAttribute("returnGetPass")  ReturnGetPassForm returnGetPassForm,@RequestParam(value="next",required=false) Integer next,
		@RequestParam String operation, HttpSession session) {
	ModelAndView mav = new ModelAndView();
	if (operation.equals("Delete")) {
		Integer getPassAutoId= returnGetPassForm.getReturnGatePassAutoId();
		ReturnGetPassMasterDTO dto = new ReturnGetPassMasterDTO();
	dto.setReturnGatePassAutoId(getPassAutoId);
	ReturnGetPassMasterInputMessage getPassMasterInputMessage = new ReturnGetPassMasterInputMessage();
	getPassMasterInputMessage.setReturnGetPassMasterDTO(dto);
	dto.setModifiedUserId(getCreatedUserId());
	returnGetPassMasterService.deleteReturnGetPassMaster(getPassMasterInputMessage);
	
	//ReturnGetPassMasterOutputMessage getPassMasterOutputMessage= returnGetPassMasterService.findAllReturnGetPassMaster();
	ReturnGetPassMasterOutputMessage getPassMasterOutputMessage= null;
	ReturnGetPassMasterInputMessage returnGetPassMasterInputMessage=new ReturnGetPassMasterInputMessage();
	 if(next==null ||next<0)
		{
		next=0;
		returnGetPassMasterInputMessage.setNext(next);
		 getPassMasterOutputMessage= returnGetPassMasterService.findReturnGetPassPagination(returnGetPassMasterInputMessage);
		}
		else
		{
			returnGetPassMasterInputMessage.setNext(next);
			 getPassMasterOutputMessage= returnGetPassMasterService.findReturnGetPassPagination(returnGetPassMasterInputMessage);
		}
	 returnGetPassForm.setNext(next);
	 returnGetPassForm.setPrevious(next);
	
	
	
	List<ReturnGetPassMasterDTO> returnGetPassList= getPassMasterOutputMessage.getReturnGetPassMasterDTOList();
	mav = new ModelAndView("return_get_pass_list");
	mav.addObject("returnGetPassList", returnGetPassList);
	mav.addObject("returnGetPassForm", returnGetPassForm);
	session.removeAttribute("returnGetPass");
	return	mav;
	}
	if (operation.equals("Search")) {
		ReturnGetPassMasterDTO getPassMasterDTO= returnGetPassForm.getReturnGetPassMasterDTO();
		ReturnGetPassMasterInputMessage getPassMasterInputMessage = new ReturnGetPassMasterInputMessage();
		getPassMasterInputMessage.setReturnGetPassMasterDTO(getPassMasterDTO);
		ReturnGetPassMasterOutputMessage getPassMasterOutputMessage=returnGetPassMasterService.search(getPassMasterInputMessage);
	    List<ReturnGetPassMasterDTO>  returnGetPassList =getPassMasterOutputMessage.getReturnGetPassMasterDTOList();
	
	    mav = new ModelAndView("return_get_pass_list");
		mav.addObject("returnGetPassList", returnGetPassList);
		mav.addObject("returnGetPassForm", returnGetPassForm);
		return	mav;
	}
	if (operation.equals("Cancel")) {
		ModelAndView mv = new ModelAndView(new RedirectView(
				"show_return_get_pass_list?operation=Show"));
	
	return mv;
	}
	
	if (operation.equals("Add")) {
		session.removeAttribute("getPass");
		ModelAndView mv = new ModelAndView(new RedirectView(
				"show_return_get_pass?operation=Show"));
	return mv;
	}
	
	return mav;
   }

}
