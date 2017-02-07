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

import com.advanz.erp.client.http.controller.form.PartyTypeForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.PartyTypeDTO;
import com.advanz.erp.masters.model.msg.PartyTypeInputMessage;
import com.advanz.erp.masters.model.msg.PartyTypeOutMessage;
import com.advanz.erp.masters.service.business.IPartyTypeService;

@Controller
public class PartyTypeController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(PartyTypeController.class);

	@Autowired
	public IPartyTypeService partyTypeService;

	@RequestMapping(value = "/show_partytype_form", method = RequestMethod.GET)
	public ModelAndView showForm(@ModelAttribute("partyTypeForm") PartyTypeForm partyTypeForm,@RequestParam(value="menuId",required=false) String menuId,HttpSession session) {
		if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
		ModelAndView mav = new ModelAndView("partytype_list");
		PartyTypeOutMessage partyTypeOutMessage = partyTypeService.findAllPartyTypes();
		ArrayList<PartyTypeDTO> list = (ArrayList<PartyTypeDTO>) partyTypeOutMessage.getPartyTypeDTOList();
		mav.addObject("partyTypeList", list);
		PartyTypeDTO partyTypeDto=new PartyTypeDTO();
		mav.addObject("partyTypeDto",partyTypeDto);
		logger.info("partyType List : " + list);
		return mav;
	}

	@RequestMapping(value = "/show_new_partytype_form", method = RequestMethod.GET)
	public ModelAndView showNewPartyTypeForm(@ModelAttribute("partyTypeForm") PartyTypeForm partyTypeForm) {
		ModelAndView mav = new ModelAndView("partytype_detail");
		if (partyTypeForm == null) {
			partyTypeForm = new PartyTypeForm();
		}
		mav.addObject("partyTypeForm", partyTypeForm);
		return mav;
	}

	@RequestMapping(value = "/save_partytype", method = RequestMethod.POST)
	public String savePartyType(@ModelAttribute("partyTypeForm") final PartyTypeForm partyTypeForm, Model model) {
		System.out.println(partyTypeForm.getPartyTypeDTO());
		PartyTypeInputMessage partyTypeInputMessage = new PartyTypeInputMessage();
		PartyTypeDTO partyTypeDTO=partyTypeForm.getPartyTypeDTO();
		partyTypeDTO.setCreatedUserId(getCreatedUserId());
		partyTypeInputMessage.setPartyTypeDTO(partyTypeDTO);
		PartyTypeOutMessage partyTypeOutMessage = partyTypeService.createPartyType(partyTypeInputMessage);
		
		ErrorListDTO errorListDTO=partyTypeOutMessage.getErrorListDTO();
		
		if(errorListDTO!=null && errorListDTO.hasErrors()){
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errors", errorDTO);
			return "partytype_detail";
		}
		String succ="Ad";
		model.addAttribute("succ", succ);
		return "redirect:/show_partytype_form";
	}

	@RequestMapping(value = "/get_partytype_data", method = RequestMethod.POST)
	public ModelAndView getPartyTypeData(@ModelAttribute("partyTypeDto")PartyTypeDTO  partyTypeDto,Model model) {
		logger.info("code : "+partyTypeDto.getPartyTypeCode());
		PartyTypeOutMessage partyTypeOutMessage;
	
			PartyTypeInputMessage partyTypeInputMessage = new PartyTypeInputMessage();			
			partyTypeInputMessage.setPartyTypeDTO(partyTypeDto);
			partyTypeOutMessage = partyTypeService.findPartyType(partyTypeInputMessage);
		

		ArrayList<PartyTypeDTO> list = (ArrayList<PartyTypeDTO>) partyTypeOutMessage.getPartyTypeDTOList();
		ModelAndView mav = new ModelAndView("partytype_list");
		
		mav.addObject("partyTypeList", list);
		mav.addObject("partyTypeDto",partyTypeDto);
		String succ="Blk";
		if(list.equals(null) || list.size()==0)
		{
		  model.addAttribute("succ", succ);
		}
		return mav;
	}
	
	@RequestMapping(value = "/get_partytype_group")
	public @ResponseBody String getPartyTypeGroup(@RequestParam("id")Integer id) {
			String partyGroup="";
			PartyTypeOutMessage partyTypeOutMessage;	
			PartyTypeInputMessage partyTypeInputMessage = new PartyTypeInputMessage();	
			PartyTypeDTO partyTypeDto=new PartyTypeDTO();
			partyTypeDto.setPartyTypeId(id);
			partyTypeInputMessage.setPartyTypeDTO(partyTypeDto);
			partyTypeOutMessage = partyTypeService.findPartyTypeById(partyTypeInputMessage);
			if(partyTypeOutMessage!=null){
				List<PartyTypeDTO> list=partyTypeOutMessage.getPartyTypeDTOList();
				if(list!=null && list.size()>0){
					partyTypeDto=list.get(0);
					partyGroup=partyTypeDto.getPartyTypeFlag();
				}
			}
		return partyGroup;

		
	}

	@RequestMapping(value = "/delete_partytype", method = RequestMethod.GET)
	public String deletePartyType(@RequestParam("partyTypeId") String partyTypeId,Model model,@ModelAttribute("partyTypeForm") PartyTypeForm partyTypeForm) {
		if (StringUtils.hasText(partyTypeId)) {
			int id = NumberUtils.parseNumber(partyTypeId, Integer.class);
			PartyTypeInputMessage partyTypeInputMessage = new PartyTypeInputMessage();
			PartyTypeDTO partyTypeDto = new PartyTypeDTO();
			partyTypeDto.setPartyTypeId(id);
			partyTypeDto.setModifiedUserId(getCreatedUserId());
			partyTypeInputMessage.setPartyTypeDTO(partyTypeDto);
		//	partyTypeService.deletePartyType(partyTypeInputMessage);
			PartyTypeOutMessage partyTypeOutMessage=partyTypeService.deletePartyType(partyTypeInputMessage);
			if(partyTypeOutMessage!=null){
			ErrorListDTO errorListDTO=partyTypeOutMessage.getErrorListDTO();
			
			if(errorListDTO!=null && errorListDTO.hasErrors()){
				ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
				logger.info(" adding Error ");
				model.addAttribute("errors", errorDTO);
				return "forward:/show_partytype_form";
			}
			}}
		String succ="Dl";
		model.addAttribute("succ", succ);
		return "redirect:/show_partytype_form";
	}

	@RequestMapping(value = "/get_partytype", method = RequestMethod.GET)
	public String getPartyType(@ModelAttribute("partyTypeForm") PartyTypeForm partyTypeForm,
			@RequestParam("partyTypeId") String partyTypeId,@RequestParam("opr") String opr,Model model) {
		logger.info("Get Party Type : " + partyTypeId);
		logger.info("Opr : " + opr);
		if (StringUtils.hasText(partyTypeId)) {
			int id = NumberUtils.parseNumber(partyTypeId, Integer.class);		
			PartyTypeInputMessage partyTypeInputMessage = new PartyTypeInputMessage();
			PartyTypeDTO partyTypeDto = new PartyTypeDTO();
			partyTypeDto.setPartyTypeId(id);
			partyTypeInputMessage.setPartyTypeDTO(partyTypeDto);

			PartyTypeOutMessage partyTypeOutMessage = null;
			if("R".endsWith(opr)){
				partyTypeOutMessage=partyTypeService.getErrorIfUsed(partyTypeInputMessage);
			if(partyTypeOutMessage!=null){
				ErrorListDTO errorListDTO=partyTypeOutMessage.getErrorListDTO();

				if(errorListDTO!=null && errorListDTO.hasErrors()){
					ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
					logger.info(" adding Error ");
					model.addAttribute("errors",errorDTO);
					return "forward:/show_partytype_form";
				}
			}}
			partyTypeOutMessage=partyTypeService.findPartyTypeById(partyTypeInputMessage);

			ArrayList<PartyTypeDTO> list = (ArrayList<PartyTypeDTO>) partyTypeOutMessage.getPartyTypeDTOList();
			System.out.println("party list size : " + list.size());
			if (list.size() == 1) {
				partyTypeForm = new PartyTypeForm();
				partyTypeForm.setPartyTypeDTO(list.get(0));
			}
		}
//		ModelAndView mav = new ModelAndView("partytype_detail_edit");
//		mav.addObject("partyTypeForm", partyTypeForm);
//		mav.addObject("opr", opr);
		model.addAttribute("partyTypeForm", partyTypeForm);
		model.addAttribute("opr", opr);
		return "partytype_detail_edit";
	}

	@RequestMapping(value = "/update_partytype", method = RequestMethod.POST)
	public String updatePartyType(
			@ModelAttribute("partyTypeForm") PartyTypeForm partyTypeForm,
			@RequestParam("partyTypeDTO.partyTypeId") String partyTypeId, Model model) {
		if (StringUtils.hasText(partyTypeId)) {
			int id = NumberUtils.parseNumber(partyTypeId, Integer.class);
			logger.info("partyTypeForm = " + partyTypeForm);
			logger.info(partyTypeForm.getPartyTypeDTO().toString());
			PartyTypeInputMessage partyTypeInputMessage = new PartyTypeInputMessage();
			PartyTypeDTO partyTypeDTO =partyTypeForm
			.getPartyTypeDTO();
			partyTypeDTO.setModifiedUserId(getCreatedUserId());
//			partyTypeDTO.setPartyTypeId(id);
			partyTypeInputMessage.setPartyTypeDTO(partyTypeDTO);
			PartyTypeOutMessage partyTypeOutMessage = partyTypeService.updatePartyType(partyTypeInputMessage);
			
			ErrorListDTO errorListDTO=partyTypeOutMessage.getErrorListDTO();
			
			if(errorListDTO!=null && errorListDTO.hasErrors()){
				ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
				logger.info(" adding Error ");
				model.addAttribute("errors", errorDTO);
				model.addAttribute("opr","E");
				return "partytype_detail_edit";
			}
		}
		String succ="Up";
		model.addAttribute("succ", succ);
		model.addAttribute("partyTypeForm", partyTypeForm);
		return "redirect:/show_partytype_form";
	}

}
