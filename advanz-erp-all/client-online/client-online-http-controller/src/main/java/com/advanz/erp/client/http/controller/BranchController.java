package com.advanz.erp.client.http.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.BranchForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.BranchDTO;
import com.advanz.erp.masters.model.criteria.BranchSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.BranchInputMessage;
import com.advanz.erp.masters.model.msg.BranchOutMessage;
import com.advanz.erp.masters.service.business.IBranchService;

@Controller
public class BranchController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(BranchController.class);

	@Autowired
	public IBranchService branchService;

	@RequestMapping(value = "/show_branch_form", method = RequestMethod.GET)
	public ModelAndView showForm(@ModelAttribute("branchForm") BranchForm branchForm,@RequestParam(value="menuId",required=false) String menuId,HttpSession session) {
		if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
	
		ModelAndView mav = new ModelAndView("branch_list");
		BranchSearchCriteriaDTO searchCriteria=new BranchSearchCriteriaDTO();
		mav.addObject("searchCriteria", searchCriteria);
		BranchOutMessage branchOutMessage = branchService.findAllBranches();
		ArrayList<BranchDTO> list = (ArrayList<BranchDTO>) branchOutMessage.getBranchDTOList();
		mav.addObject("branchList", list);
		logger.info("branch List : " + list);
		return mav;
	}

	@RequestMapping(value = "/show_new_branch_form", method = RequestMethod.GET)
	public ModelAndView showNewBranchForm(
			@ModelAttribute("branchForm") BranchForm branchForm) {
		ModelAndView mav = new ModelAndView("branch_detail");
		if (branchForm == null) {
			branchForm = new BranchForm();
		}
		mav.addObject("branchForm", branchForm);
		return mav;
	}

	@RequestMapping(value = "/save_branch", method = RequestMethod.POST)
	public String saveBranch(
			@ModelAttribute("branchForm") BranchForm branchForm, Model model) {
		
		logger.info(branchForm.getBranchDto().toString());
		BranchInputMessage branchInputMessage = new BranchInputMessage();
	 BranchDTO branchDTO=	branchForm.getBranchDto();
	if(branchDTO.getDutyVideNotification()!=null){
		branchDTO.setDutyVideNotification(1);
	}
	else{
		branchDTO.setDutyVideNotification(0);
	}
	branchDTO.setCreatedUserId(getCreatedUserId());
		branchInputMessage.setBranchDTO(branchDTO);
		BranchOutMessage branchOutMessage = branchService.createBranch(branchInputMessage);
		
		ErrorListDTO errorListDTO=branchOutMessage.getErrorListDTO();
		
		if(errorListDTO!=null && errorListDTO.hasErrors()){
			logger.info(" adding Error ");
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			model.addAttribute("errorList", errorDTO);
			return "branch_detail";
		}
		String succ="Ad";
		model.addAttribute("succ", succ);
		return "redirect:/show_branch_form";
	}

	@RequestMapping(value = "/get_branch_data", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView getBranchData(
			@ModelAttribute("searchCriteria")BranchSearchCriteriaDTO searchCriteria,Model model) {
				BranchOutMessage branchOutMessage;
		
			BranchInputMessage branchInputMessage = new BranchInputMessage();
			BranchDTO branchDto = new BranchDTO();
			branchDto.setBranch(searchCriteria.getBranchName());
			branchDto.setInvoiceCode(searchCriteria.getInvoiceCode());
			branchInputMessage.setBranchDTO(branchDto);
			branchOutMessage = branchService.findBranch(branchInputMessage);
		
		ArrayList<BranchDTO> list = (ArrayList<BranchDTO>) branchOutMessage.getBranchDTOList();
		ModelAndView mav = new ModelAndView("branch_list");
		mav.addObject("branchList", list);
		logger.info("branch List : " + list);
		String succ="Blk";
		if(list.equals(null) || list.size()==0)
		{
		  model.addAttribute("succ", succ);
		}
		return mav;
	  }

	@RequestMapping(value = "/get_branch", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getBranch(@ModelAttribute("branchForm") BranchForm branchForm,
			@RequestParam("branchId") String branchId,@RequestParam("opr")String opr) {
		logger.info("Get Branch : " + branchId);
		logger.info("Opr : " + opr);
		if (StringUtils.hasText(branchId)) {
			int id = NumberUtils.parseNumber(branchId, Integer.class);
			System.out.println("branchId : " + branchId);
			BranchInputMessage branchInputMessage = new BranchInputMessage();
			BranchDTO branchDto = new BranchDTO();
			branchDto.setBranchId(id);
			branchInputMessage.setBranchDTO(branchDto);
			BranchOutMessage branchOutMessage;
			if("R".equals(opr)){				
			  branchOutMessage = branchService.checkBeforeRemove(branchInputMessage);
			if(branchOutMessage!=null){
			  ErrorListDTO errorListDTO=branchOutMessage.getErrorListDTO();
			if (errorListDTO != null && errorListDTO.hasErrors()) {		
				ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			  ModelAndView mav = new ModelAndView("forward:show_branch_form");
			  mav.addObject("errors", errorDTO);
			  return mav;
			 }
			}
		   }

			branchOutMessage = branchService.findBranchById(branchInputMessage);

			ArrayList<BranchDTO> list = (ArrayList<BranchDTO>) branchOutMessage
			.getBranchDTOList();
			System.out.println("list : " + list.size());
			if (list.size() == 1) {
				System.out.println("get data for branchId : " + id);
				System.out.println(list.get(0));
				branchForm = new BranchForm();
				branchForm.setBranchDto(list.get(0));
			}
		}
		ModelAndView mav = new ModelAndView("branch_detail_edit");
		mav.addObject("branchForm", branchForm);
		mav.addObject("opr",opr);
		logger.info(branchForm.getBranchDto().toString());
		return mav;
	}

	@RequestMapping(value = "/update_branch", method = RequestMethod.POST)
	public String updateBranch(@ModelAttribute("branchForm") BranchForm branchForm,
			@RequestParam("branchDto.branchId") String branchId, Model model) {
		
		if (StringUtils.hasText(branchId)) {
			int id = NumberUtils.parseNumber(branchId, Integer.class);
			logger.info("branchForm = " + branchForm);
			logger.info(branchForm.getBranchDto().toString());
			BranchInputMessage branchInputMessage = new BranchInputMessage();
			BranchDTO branchDTO = branchForm.getBranchDto();
			
			if(branchDTO.getDutyVideNotification()!=null){
				branchDTO.setDutyVideNotification(1);
			}
			else{
				branchDTO.setDutyVideNotification(0);
			}
			branchDTO.setModifiedUserId(getCreatedUserId());
//			branchDTO.setBranchId(id);
			branchInputMessage.setBranchDTO(branchDTO);			
			BranchOutMessage branchOutMessage = branchService.updateBranch(branchInputMessage);
			
			ErrorListDTO errorListDTO=branchOutMessage.getErrorListDTO();
			
			if(errorListDTO!=null && errorListDTO.hasErrors()){
				logger.info(" adding Error ");
				ErrorDTO errorDTO=new ErrorDTO();
				errorDTO=errorListDTO.getErrorList().get(0);
				model.addAttribute("errors",errorDTO);
				model.addAttribute("opr","E");
				return "branch_detail_edit";
			}			
		}
		String succ="Up";
		 model.addAttribute("succ",succ);
		return "redirect:/show_branch_form";
	}

	@RequestMapping(value = "/delete_branch", method = RequestMethod.GET)
	public String deleteBranch(@RequestParam("branchId") String branchId,Model model) {
		if (StringUtils.hasText(branchId)) {
			int id = NumberUtils.parseNumber(branchId, Integer.class);
			BranchInputMessage branchInputMessage = new BranchInputMessage();
			BranchDTO branchDto = new BranchDTO();
			branchDto.setBranchId(id);
			branchDto.setModifiedUserId(getCreatedUserId());
			branchInputMessage.setBranchDTO(branchDto);
			branchService.deleteBranch(branchInputMessage);
		}
		String succ="Dl";
		model.addAttribute("succ", succ);
		return "redirect:/show_branch_form";
	}
}
