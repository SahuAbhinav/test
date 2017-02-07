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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.PartyForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.CityDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.PartyDTO;
import com.advanz.erp.masters.model.PartyTypeDTO;
import com.advanz.erp.masters.model.TransporterDTO;
import com.advanz.erp.masters.model.criteria.PartySearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.CityOutputMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.model.msg.PartyInputMessage;
import com.advanz.erp.masters.model.msg.PartyOutMessage;
import com.advanz.erp.masters.model.msg.PartyTypeOutMessage;
import com.advanz.erp.masters.model.msg.TransporterOutMessage;
import com.advanz.erp.masters.service.business.ICityService;
import com.advanz.erp.masters.service.business.IMastersService;
import com.advanz.erp.masters.service.business.IPartyService;
import com.advanz.erp.masters.service.business.IPartyTypeService;
import com.advanz.erp.masters.service.business.ITransporterService;

@Controller
public class PartyController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(PartyController.class);

	@Autowired
	public IPartyService partyService;
	@Autowired
	public ITransporterService transporterService;
	@Autowired
	public ICityService cityService;
	@Autowired
	public IMastersService formService;
	@Autowired
	public IPartyTypeService partyTypeService;
	//private PartyDTO partyDTO = new PartyDTO();
	//private PartyDTO updatePartyDTO = new PartyDTO();

	@RequestMapping(value = "/show_party_form", method = RequestMethod.GET)
	public ModelAndView showForm(@ModelAttribute("partyForm")PartyForm partyForm,@RequestParam(value="menuId",required=false) String menuId,HttpSession session) 
		{
		if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
	
		PartySearchCriteriaDTO searchCriteria=new PartySearchCriteriaDTO();
		ModelAndView mav = new ModelAndView("party_list");
		mav.addObject("searchCriteria", searchCriteria);
		PartyOutMessage partyOutMessage = partyService.findAllPartys();
		PartyInputMessage partyInputMessage = new PartyInputMessage();
	//	PartyDTO partyDTO = new PartyDTO();
		//partyInputMessage.setPartyDTO(partyDTO);
		//PartyOutMessage partyOutMessage = partyService.findParty(partyInputMessage);
		ArrayList<PartyDTO> list = (ArrayList<PartyDTO>) partyOutMessage.getPartyDTOList();
		mav.addObject("partyList", list);
		logger.info("party List : " + list);
		return mav;
	}

	@RequestMapping(value = "/show_new_party_form", method = RequestMethod.GET)
	public ModelAndView showNewPartyForm() {
		PartyForm partyForm= new PartyForm();
		ModelAndView mav = new ModelAndView("party_detail");
		mav.addObject("partyForm", partyForm);
		mav.addObject("step", "1");
		return mav;
	}
	
	@RequestMapping(value="/process_party")
	public String processParty(@ModelAttribute("partyForm")PartyForm partyForm,BindingResult result, @RequestParam("nextBtn")String btn,Model model){
		if("tab1".equals(btn))
		{
			model.addAttribute("step", "2");
		//	return "redirect:/processs_party#tabs-2";
			return "party_detail";
		}else if("tab2".equals(btn))
		{
			model.addAttribute("step", "3");
		//	return "redirect:/processs_party#tabs-3";
			return "party_detail";
		}else{
			
			if(!validateParty(partyForm.getPartyDTO(), result, model))
			{
				return "party_detail";
			}
		return saveParty(partyForm, model);	
		}
	}
	
	@RequestMapping(value = "/save_party", method = RequestMethod.POST)
	public String saveParty(@ModelAttribute("partyForm") final PartyForm partyForm,Model model) {
		logger.info(">>>>>>>>>>>>>>>>>>>> before : "+partyForm.getPartyDTO());
		PartyDTO partyDTO=partyForm.getPartyDTO();
		PartyInputMessage partyInputMessage = new PartyInputMessage();
		partyDTO.setCreatedUserId(getCreatedUserId());
		partyInputMessage.setPartyDTO(partyDTO);
		PartyOutMessage partyOutMessage = partyService.createParty(partyInputMessage);

		ErrorListDTO errorListDTO = partyOutMessage.getErrorListDTO();

		if (errorListDTO != null && errorListDTO.hasErrors()) {
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errors",errorDTO);
			return "party_detail";
		}
		model.asMap().remove("partyForm");
		String succ="Ad";
		model.addAttribute("succ", succ);
		return "redirect:/show_party_form";
	}

	@RequestMapping(value = "/get_party_data", method = RequestMethod.POST)
	public ModelAndView getPartyData(@ModelAttribute("searchCriteria")PartySearchCriteriaDTO searchCriteria,Model model,@ModelAttribute("partyForm") final PartyForm partyForm) {
		PartyOutMessage partyOutMessage;
		
			PartyInputMessage partyInputMessage = new PartyInputMessage();
			PartyDTO partyDto = new PartyDTO();
			partyDto.setPartyName(searchCriteria.getPartyName());
			partyDto.setPartyTypeDTO(searchCriteria.getPartyTypeDTO());
			partyDto.setBalanceType(searchCriteria.getPartyTypeDTO().getPartyTypeFlag());
			partyInputMessage.setPartyDTO(partyDto);
			partyOutMessage = partyService.findParty(partyInputMessage);		
		ArrayList<PartyDTO> list = (ArrayList<PartyDTO>) partyOutMessage.getPartyDTOList();
		ModelAndView mav = new ModelAndView("party_list");
		mav.addObject("partyList", list);
	//	mav.addObject(attributeName, attributeValue)
		logger.info("party List : " + list);
		String succ="Blk";
		if(list.equals(null) || list.size()==0)
		{
		  model.addAttribute("succ", succ);
		}
		return mav;
	}

	@RequestMapping(value = "/delete_party", method = RequestMethod.GET)
	public String deleteParty(@RequestParam("partyId") String partyId,@ModelAttribute("partyForm") final PartyForm partyForm) {
		if (StringUtils.hasText(partyId)) {
			int id = NumberUtils.parseNumber(partyId, Integer.class);
			PartyInputMessage partyInputMessage = new PartyInputMessage();
			PartyDTO partyDto = new PartyDTO();
			partyDto.setPartyId(id);
			partyInputMessage.setPartyDTO(partyDto);
			partyService.deleteParty(partyInputMessage);
		}
		return "redirect:/show_party_form";
	}

	@RequestMapping(value = "/get_party", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getParty(@ModelAttribute("partyForm") PartyForm partyForm,@ModelAttribute("opr")String opr,
			@RequestParam("partyId") String partyId) {
		if (StringUtils.hasText(partyId)) {
			int id = NumberUtils.parseNumber(partyId, Integer.class);
			PartyInputMessage partyInputMessage = new PartyInputMessage();
			PartyDTO partyDto = new PartyDTO();
			partyDto.setPartyId(id);
			
			partyInputMessage.setPartyDTO(partyDto);
			PartyOutMessage partyOutMessage=null;
			if("R".equals(opr)){
				partyOutMessage = partyService.checkBeforeRemove(partyInputMessage);
				if(partyOutMessage!=null){
					ErrorListDTO errorListDTO=partyOutMessage.getErrorListDTO();
					if (errorListDTO != null && errorListDTO.hasErrors()) {	
						ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
						ModelAndView mav = new ModelAndView("forward:show_party_form");
						mav.addObject("errors", errorDTO);
						return mav;
					}
				}
			}
			partyOutMessage = partyService.findPartyById(partyInputMessage);
			ArrayList<PartyDTO> list = (ArrayList<PartyDTO>) partyOutMessage.getPartyDTOList();
			
			if (list.size() == 1) {
				partyForm = new PartyForm();
				partyForm.setPartyDTO(list.get(0));
				logger.info("-----------"+partyForm.getPartyDTO().getInvoiceType());
			}
		}
		ModelAndView mav = new ModelAndView("party_detail_edit");
		mav.addObject("partyForm", partyForm);
		mav.addObject("opr",opr);
		mav.addObject("step","1");
		return mav;
	}
	
	
	@RequestMapping(value = "/update_party", method = RequestMethod.POST)
	public ModelAndView updateParty(@ModelAttribute("partyForm") PartyForm partyForm,BindingResult result, @RequestParam(value="btn",required=false )String btn, Model model) {
		logger.info("Button="+btn);
		logger.info("-----------"+partyForm.getPartyDTO().getInvoiceType());
		ModelAndView mav = new ModelAndView("party_detail_edit");
		
		if("tab1".equals(btn)){			
			mav.addObject("step", "2");
		}else if("tab2".equals(btn)){		
			mav.addObject("step", "3");
		}else {					
			PartyDTO partyDTO=partyForm.getPartyDTO();			
			if(!validateParty(partyDTO, result, model))
			{
				return mav;
			}
			PartyInputMessage partyInputMessage = new PartyInputMessage();
			partyDTO.setModifiedUserId(getCreatedUserId());
			partyInputMessage.setPartyDTO(partyDTO);			
			PartyOutMessage partyOutMessage = partyService.updateParty(partyInputMessage);
			ErrorListDTO errorListDTO = partyOutMessage.getErrorListDTO();
			if (errorListDTO != null && errorListDTO.hasErrors()) {
				ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
				logger.info(" adding Error ");
				mav.addObject("errors",errorDTO);				
			}else{
				mav=new ModelAndView("redirect:/show_party_form");
			}
		}		
		String succ="Up";
		model.addAttribute("succ", succ);
		return mav;
	}
	
	

	@ModelAttribute("cityList")
	public List<CityDTO> cityList() {
		CityOutputMessage cityOutMessage = cityService.findAllCityes();
		return cityOutMessage.getCityDTOList();
	}

	@ModelAttribute("formList")
	public List<MastersDTO> formList() {
		MastersInputMessage mastersInputMessage	=new MastersInputMessage();
		mastersInputMessage.setFormId(12);
		MastersOutputMessage mastersOutputMessage = formService.findFormById(mastersInputMessage);
		return mastersOutputMessage.getMastersDTOList();
	}

	@ModelAttribute("transporterList")
	public List<TransporterDTO> transporterList() {
		TransporterOutMessage transporterOutMessage = transporterService.findAllTransporters();

		return transporterOutMessage.getTransporterDTOList();
	}

	@ModelAttribute("partyTypeList")
	public List<PartyTypeDTO> partyTypeListS() {
		PartyTypeOutMessage partyTypeOutMessage = partyTypeService.findAllPartyTypes();
		return partyTypeOutMessage.getPartyTypeDTOList();
	}
	
	private boolean validateParty(PartyDTO partyDTO,BindingResult result,Model model){
		if(partyDTO!=null){
			String name=partyDTO.getPartyName();
			if(!StringUtils.hasText(name)){
				result.reject("", "Name is required");
			}
			String code=partyDTO.getPartyCode();
			
			if(!StringUtils.hasText(code)){
				result.reject("", "Code is Required ");
			}			
			if(result.hasErrors()){
				model.addAttribute("step", "1");
				return false;
			}
		}
		return true;
	}
  }
