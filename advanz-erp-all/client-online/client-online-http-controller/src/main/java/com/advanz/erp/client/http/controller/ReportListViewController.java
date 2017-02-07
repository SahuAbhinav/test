package com.advanz.erp.client.http.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.authentication.AdvanzUserServiceImpl;
import com.advanz.erp.masters.model.ModuleMenuMasterDTO;
import com.advanz.erp.masters.model.msg.ModuleMenuMasterInputMessage;
import com.advanz.erp.masters.model.msg.ModuleMenuMasterOutMessage;
import com.advanz.erp.masters.service.business.IModuleMenuMasterService;
import com.advanz.erp.masters.service.business.IUserRoleAndRightsService;

@Controller
public class ReportListViewController extends BaseController{

	@Autowired
	AdvanzUserServiceImpl advanzUserServiceImpl;
	
	@Autowired 
	public IModuleMenuMasterService menuMasterService;
	
	@Autowired
	public IUserRoleAndRightsService iUserRoleAndRightsService;
	

	
	@RequestMapping(value = "/getReportLinkList", method = RequestMethod.GET)
	public ModelAndView submit(HttpSession session,@ModelAttribute("masterDTO") ModuleMenuMasterDTO masterDTO,@RequestParam(value="operation",required=false)String operation) {
		
		String loginUserName=(String)session.getAttribute("loginUserName");
		ModelAndView	modelAndView = new ModelAndView("report_link_list");
		ModuleMenuMasterInputMessage inputMessage = new ModuleMenuMasterInputMessage();
		ModuleMenuMasterDTO dto = new ModuleMenuMasterDTO();
		List<ModuleMenuMasterDTO> reportList=null;
		ModuleMenuMasterOutMessage masterOutMessage= new ModuleMenuMasterOutMessage();
		System.out.println("Menu Name :"+ masterDTO.getMenuName()+"Sub Menu NAme  :"+masterDTO.getSubMenuName());
		if("Search".equalsIgnoreCase(operation)){
			System.out.println("INSIDE SEARCH OPERATION ::::::::::::::");
			masterDTO.setModuleName("Reports");
			masterDTO.setLoginUserName(loginUserName);
			
			inputMessage.setModuleMenuMasterDTO(masterDTO);
			masterOutMessage= menuMasterService.search(inputMessage);
			reportList = masterOutMessage.getModuleMenuMasterDTOList();
			modelAndView.addObject("reportList",reportList);
			modelAndView.addObject("masterDTO",masterDTO);
			return modelAndView;
		}else{
			System.out.println("ELSE CONDITION:::::::::::::::");
		dto.setModuleName("Reports");
		dto.setLoginUserName(loginUserName);
		inputMessage.setModuleMenuMasterDTO(dto);
		masterOutMessage= menuMasterService.getReportLinkList(inputMessage);
		reportList = masterOutMessage.getModuleMenuMasterDTOList();
		}
		
		
		
		modelAndView.addObject("reportList",reportList);
		modelAndView.addObject("masterDTO",masterDTO);
		return modelAndView;
	}
	
	
	
}
