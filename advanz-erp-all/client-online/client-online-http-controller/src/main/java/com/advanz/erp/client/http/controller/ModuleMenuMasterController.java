package com.advanz.erp.client.http.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.InvoiceForm;
import com.advanz.erp.client.http.controller.form.PartyForm;
import com.advanz.erp.masters.model.ModuleMenuMasterDTO;
import com.advanz.erp.masters.model.msg.ModuleMenuMasterInputMessage;
import com.advanz.erp.masters.model.msg.ModuleMenuMasterOutMessage;
import com.advanz.erp.masters.service.business.IModuleMenuMasterService;

@Controller
public class ModuleMenuMasterController extends BaseController{
	
	private static final Logger logger = LoggerFactory
	.getLogger(ModuleMenuMasterController.class);
	
	
	@Autowired 
	public IModuleMenuMasterService menuMasterService;
	
	@RequestMapping(value = "/show_moduleMenu", method = RequestMethod.GET)
	public ModelAndView display(
			@ModelAttribute("invoice") InvoiceForm invoiceForm,
			@RequestParam String operation, HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
	@RequestMapping(value = "/save_moduleMenu", method = RequestMethod.POST)
	public ModelAndView submit(
			@ModelAttribute("invoice") InvoiceForm invoiceForm,
			@RequestParam String operation, HttpSession session) {

		
		
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
	
	@RequestMapping(value = "/getModuleList", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse addUser(@RequestParam String moduleName, HttpSession session) {
		
		JsonResponse res = new JsonResponse();
		/*ModuleMenuMasterInputMessage inputMessage = new ModuleMenuMasterInputMessage();
		ModuleMenuMasterDTO dto = new ModuleMenuMasterDTO();
		dto.setModuleName("Masters");
		inputMessage.setModuleMenuMasterDTO(dto);
		ModuleMenuMasterOutMessage masterOutMessage= menuMasterService.findModuleMenuMasterByModuleName(inputMessage);
		//ModuleMenuMasterOutMessage masterOutMessage= menuMasterService.findAllModuleMenuMaster();
		List<ModuleMenuMasterDTO> moduleList = masterOutMessage.getModuleMenuMasterDTOList();
		System.out.println(" LLLLLLLLLSSSSSSIZE::::::: " + moduleList.size());
		
		session.setAttribute("moduleList", moduleList);
		res.setResult(moduleList);*/
		
		return res;
	   }
  }
