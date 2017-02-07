package com.advanz.erp.client.http.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.advanz.erp.client.http.controller.form.CityForm;
import com.advanz.erp.client.http.controller.form.ProfessionalTaxForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;

import com.advanz.erp.masters.model.ProfessionalTaxDTO;
import com.advanz.erp.masters.model.ProfessionalTaxDeductTypeDTO;
import com.advanz.erp.masters.model.msg.ProfessionalTaxDeductTypeOutputMessage;
import com.advanz.erp.masters.model.msg.ProfessionalTaxInputMessage;
import com.advanz.erp.masters.model.msg.ProfessionalTaxOutputMessage;
import com.advanz.erp.masters.service.business.IProfessionalTaxDeductTypeService;
import com.advanz.erp.masters.service.business.IProfessionalTaxService;

@Controller
public class ProfessionalTaxController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ProfessionalTaxController.class);

	@Autowired
	public IProfessionalTaxService iProfessionalTaxService;
	@Autowired
	public IProfessionalTaxDeductTypeService iProfessionalTaxDeductTypeService;

	@RequestMapping(value = "/show_professionalTax_form", method = RequestMethod.GET)
	public ModelAndView showForm(@ModelAttribute("professionalTaxForm") ProfessionalTaxForm professionalTaxForm,@RequestParam(value="menuId",required=false) String menuId,HttpSession session) {
		if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
		ModelAndView mav = new ModelAndView("professionalTax_list");
		ProfessionalTaxOutputMessage professionalTaxOutputMessage = iProfessionalTaxService.findAllProfessionalTax();
		ArrayList<ProfessionalTaxDTO> list = (ArrayList<ProfessionalTaxDTO>) professionalTaxOutputMessage.getProfessionalTaxDTOList();
		mav.addObject("professionalTaxList", list);
		logger.info("professionalTax list : " + list);
		mav.addObject("professionalTaxForm", professionalTaxForm);
		return mav;
	}
	
	
	
	@RequestMapping(value = "/show_new_professionalTax_form", method = RequestMethod.GET)
	public ModelAndView showNewProfessionalTaxForm(@ModelAttribute("professionalTaxForm") ProfessionalTaxForm professionalTaxForm) {
		ModelAndView mav = new ModelAndView("professionalTax_detail");
		if (professionalTaxForm == null) {
			professionalTaxForm = new ProfessionalTaxForm();
		}
		mav.addObject("professionalTaxForm", professionalTaxForm);
		return mav;
	}
    
	@RequestMapping(value = "/save_professionalTax", method = RequestMethod.POST)
	public String saveProfessionalTax(@ModelAttribute("professionalTaxForm") ProfessionalTaxForm professionalTaxForm, Model model) {
		logger.info(professionalTaxForm.getProfessionalTaxDto().toString());
		ProfessionalTaxInputMessage professionalTaxInputMessage = new ProfessionalTaxInputMessage();
		ProfessionalTaxDTO professionalTaxDTO= professionalTaxForm.getProfessionalTaxDto();
		professionalTaxDTO.getCreatedUserId();
		professionalTaxDTO.setCreatedUserId(getCreatedUserId());
		professionalTaxInputMessage.setProfessionalTaxDTO(professionalTaxDTO);
		ProfessionalTaxOutputMessage professionalTaxOutputMessage = iProfessionalTaxService.createProfessionalTax(professionalTaxInputMessage);
		
		ErrorListDTO errorListDTO=professionalTaxOutputMessage.getErrorListDTO();
		
		if(errorListDTO!=null && errorListDTO.hasErrors()){
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errors",errorDTO);
			return "professionalTax_detail";
		}
		String succ="Ad";
		model.addAttribute("succ", succ);
		return "redirect:/show_professionalTax_form";
	}
	
	
	@RequestMapping(value = "/get_professionalTax", method = RequestMethod.GET)
	public ModelAndView getProfessionalTax(	@ModelAttribute("professionalTaxForm") ProfessionalTaxForm professionalTaxForm,@RequestParam("ptaxId") String ptaxId,@RequestParam("opr")String opr) {
		logger.info("Get ProfeesionalTax : " + ptaxId);
		logger.info("Opr : " + opr);
		if (StringUtils.hasText(ptaxId)) {
			 System.out.println("get_professionalTax   "+ptaxId);
			int id = NumberUtils.parseNumber(ptaxId, Integer.class);
			ProfessionalTaxInputMessage professionalTaxInputMessage = new ProfessionalTaxInputMessage();
			ProfessionalTaxDTO professionalTaxDto = new ProfessionalTaxDTO();
			professionalTaxDto.setPtaxId(id);
			professionalTaxInputMessage.setProfessionalTaxDTO(professionalTaxDto);
			ProfessionalTaxOutputMessage professionalTaxOutputMessage = iProfessionalTaxService.findProfessionalTaxById(professionalTaxInputMessage);

			ArrayList<ProfessionalTaxDTO> list = (ArrayList<ProfessionalTaxDTO>) professionalTaxOutputMessage.getProfessionalTaxDTOList();
			if (list.size() > 0) {
				System.out.println("get data for professionalTax Id :" + id);
				System.out.println("value=====  "+list.get(0).getPtaxId());
				professionalTaxForm = new ProfessionalTaxForm();
				professionalTaxForm.setProfessionalTaxDto(list.get(0));
			}
		}
		
		ModelAndView mav = new ModelAndView("professionalTax_detail_edit");
		mav.addObject("professionalTaxForm", professionalTaxForm);
		mav.addObject("opr",opr);
		return mav;
	}

	@RequestMapping(value = "/update_professionalTax", method = RequestMethod.POST)
	public String updateProfessionalTax(@ModelAttribute("professionalTaxForm") ProfessionalTaxForm professionalTaxForm, Model model) {
	  	logger.info("professionalTaxForm = " + professionalTaxForm.getProfessionalTaxDto().getPtaxId());
			logger.info(professionalTaxForm.getProfessionalTaxDto().toString());
			ProfessionalTaxInputMessage professionalTaxInputMessage = new ProfessionalTaxInputMessage();
			ProfessionalTaxDTO professionalTaxDTO = professionalTaxForm.getProfessionalTaxDto();
			//professionalTaxDTO.setPtaxId(professionalTaxForm.getProfessionalTaxDto().getPtaxId());
			professionalTaxDTO.setModifiedUserId(getCreatedUserId());
			professionalTaxInputMessage.setProfessionalTaxDTO(professionalTaxDTO);			
			ProfessionalTaxOutputMessage professionalTaxOutputMessage = iProfessionalTaxService.updateProfessionalTax(professionalTaxInputMessage);
			
			ErrorListDTO errorListDTO=professionalTaxOutputMessage.getErrorListDTO();
			
			if(errorListDTO!=null && errorListDTO.hasErrors()){
				ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
				logger.info(" updating Error ");
				model.addAttribute("errorList",errorDTO);
				model.addAttribute("opr","E");
				return "professionalTax_detail_edit";
			}			
		
			String succ="Up";
			model.addAttribute("succ",succ);
		return "redirect:/show_professionalTax_form";
	}
	
	
	@RequestMapping(value = "/delete_professionalTax", method = RequestMethod.GET)
	public String deleteProfessionalTax(@ModelAttribute("professionalTaxForm")ProfessionalTaxForm professionalTaxForm,@RequestParam("ptaxId") String ptaxId,Model model) 
	{
		if (StringUtils.hasText(ptaxId)) {
			int id = NumberUtils.parseNumber(ptaxId, Integer.class);
			ProfessionalTaxInputMessage professionalTaxInputMessage = new ProfessionalTaxInputMessage();
			ProfessionalTaxDTO professionalTaxDTO = new ProfessionalTaxDTO();
			professionalTaxDTO.setPtaxId(id);
			
			professionalTaxDTO.setModifiedUserId(getCreatedUserId());
			professionalTaxInputMessage.setProfessionalTaxDTO(professionalTaxDTO);
			iProfessionalTaxService.deleteProfessionalTax(professionalTaxInputMessage);
		}
		String succ="Dl";
		model.addAttribute("succ", succ);
		return "redirect:/show_professionalTax_form";
	}
	
	@RequestMapping(value = "/get_professionalTax_data", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView getProfessionalTaxData(@ModelAttribute("professionalTaxForm")ProfessionalTaxForm professionalTaxForm, Model model) {
		ProfessionalTaxOutputMessage professionalTaxOutputMessage;
		
	String slabName=professionalTaxForm.getSlabName();	
	String deductAmount=professionalTaxForm.getDeductAmount();	
		
	if (StringUtils.hasText(slabName) || StringUtils.hasText(deductAmount)) {
			ProfessionalTaxInputMessage professionalTaxInputMessage = new ProfessionalTaxInputMessage();
			ProfessionalTaxDTO professionalTaxDTO = new ProfessionalTaxDTO();
			professionalTaxDTO.setSlabName(slabName);
			professionalTaxDTO.setDeductAmount(deductAmount);
			professionalTaxInputMessage.setProfessionalTaxDTO(professionalTaxDTO);
			professionalTaxOutputMessage = iProfessionalTaxService.findProfessionalTax(professionalTaxInputMessage);
		}
		else
			professionalTaxOutputMessage = iProfessionalTaxService.findAllProfessionalTax();

		ArrayList<ProfessionalTaxDTO> list = (ArrayList<ProfessionalTaxDTO>) professionalTaxOutputMessage.getProfessionalTaxDTOList();
		ModelAndView mav = new ModelAndView("professionalTax_list");
		mav.addObject("professionalTaxList", list);
		logger.info("professionalTax List : " , list);
		String succ="Blk";
		if(list.equals(null) || list.size()==0)
		{
		  model.addAttribute("succ", succ);
		}
		return mav;
	}
	

	@ModelAttribute("ptaxDeductTypes")
	public List<ProfessionalTaxDeductTypeDTO> professionalTaxDeductTypeList() {			
			
		ProfessionalTaxDeductTypeOutputMessage  professionalTaxDeductTypeOutputMessage = iProfessionalTaxDeductTypeService.findAllDeductType();
		    return professionalTaxDeductTypeOutputMessage.getProfessionalTaxDeductTypeDTOList();
		}
	}


