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

import com.advanz.erp.client.http.controller.form.StoreLocationForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.QuotationMasterDTO;
import com.advanz.erp.masters.model.StoreLocationDTO;
import com.advanz.erp.masters.model.criteria.StoreLocationSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.QuotationMasterInputMessage;
import com.advanz.erp.masters.model.msg.StoreLocationOutMessage;
import com.advanz.erp.masters.model.msg.StoreLocationInputMessage;
import com.advanz.erp.masters.model.msg.StoreLocationOutMessage;
import com.advanz.erp.masters.service.business.IStoreLocationService;

@Controller
public class StoreLocationController extends BaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(StoreLocationController.class);

	@Autowired
	public IStoreLocationService storeLocationService;

	@RequestMapping(value = "/show_storelocation_form", method = RequestMethod.GET)
	public ModelAndView showForm(@ModelAttribute("storeLocationForm") StoreLocationForm storeLocationForm,@RequestParam(value="menuId",required=false) String menuId,HttpSession session) {
		if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
		ModelAndView mav = new ModelAndView("store_location_list");
		StoreLocationOutMessage storeLocationOutMessage = storeLocationService.findAllStoreLocations();
		ArrayList<StoreLocationDTO> list = (ArrayList<StoreLocationDTO>) storeLocationOutMessage
				.getStoreLocationDTOList();
		mav.addObject("storeLocationList", list);
		StoreLocationSearchCriteriaDTO searchCritria=new StoreLocationSearchCriteriaDTO();
		mav.addObject("searchCriteria", searchCritria);
		logger.info("storeLocation List : " + list);
		return mav;
	}

	@RequestMapping(value = "/show_new_storelocation_form", method = RequestMethod.GET)
	public ModelAndView showNewStoreLocationForm(
			@ModelAttribute("storeLocationForm") StoreLocationForm storeLocationForm) {
		ModelAndView mav = new ModelAndView("store_location_detail");
		if (storeLocationForm == null) {
			storeLocationForm = new StoreLocationForm();
		}
		mav.addObject("storeLocationForm", storeLocationForm);
		return mav;
	}

	@RequestMapping(value = "/save_storelocation", method = RequestMethod.POST)
	public String saveStoreLocation(
			@ModelAttribute("storeLocationForm") final StoreLocationForm storeLocationForm,Model model,@ModelAttribute("opr")String opr) {
		String succ="";
		StoreLocationInputMessage storeLocationInputMessage = new StoreLocationInputMessage();
		StoreLocationDTO storeLocationDTO= storeLocationForm.getStoreLocationDTO();
		storeLocationInputMessage.setStoreLocationDTO(storeLocationDTO);
		Integer storeLocationId=storeLocationForm.getStoreLocationDTO().getStoreLocationId();
		StoreLocationOutMessage storeLocationOutMessage;
		if(storeLocationId!=null && storeLocationId.intValue()!=0){
			storeLocationDTO.setModifiedUserId(getCreatedUserId());
		storeLocationOutMessage = storeLocationService.updateStoreLocation(storeLocationInputMessage);
		 succ="Up";
		}
		else{
			storeLocationDTO.setCreatedUserId(getCreatedUserId());
			storeLocationOutMessage = storeLocationService.createStoreLocation(storeLocationInputMessage);
			succ="Ad";
		}
		ErrorListDTO errorListDTO=storeLocationOutMessage.getErrorListDTO();
		
		if(errorListDTO!=null && errorListDTO.hasErrors()){
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			succ="Du";
			logger.info(" adding Error ");
			model.addAttribute("errors", errorDTO);
			model.addAttribute("succ", succ);
			model.addAttribute("opr",opr);
			return "store_location_detail";
		}	
		
		model.addAttribute("succ",succ);
		return "redirect:/show_storelocation_form";
	}

	@RequestMapping(value = "/get_storelocation_data", method = RequestMethod.POST)
	public ModelAndView getStoreLocationData(@ModelAttribute("searchCriteria")StoreLocationSearchCriteriaDTO searchCriteria,Model model) {
		StoreLocationOutMessage storeLocationOutMessage = null;
			StoreLocationInputMessage storeLocationInputMessage = new StoreLocationInputMessage();
			StoreLocationDTO storeLocationDto = new StoreLocationDTO();
			storeLocationDto.setStoreLocation(searchCriteria.getName());
			storeLocationDto.setLocationCode(searchCriteria.getCode());
			storeLocationInputMessage.setStoreLocationDTO(storeLocationDto);
			storeLocationOutMessage = storeLocationService.findStoreLocation(storeLocationInputMessage);
		String succ="Blk";
		ArrayList<StoreLocationDTO> list = (ArrayList<StoreLocationDTO>) storeLocationOutMessage
				.getStoreLocationDTOList();
		ModelAndView mav = new ModelAndView("store_location_list");
		if(list.equals(null) || list.size()==0)
		{
			model.addAttribute("succ", succ);
		}
		mav.addObject("storeLocationList", list);
		logger.info("storeLocation List : " + list);
		return mav;
	}

	@RequestMapping(value = "/get_storelocation", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getStoreLocation(
			@ModelAttribute("storeLocationForm") StoreLocationForm storeLocationForm,
			@RequestParam("storeLocationId") String storeLocationId,
			@RequestParam("opr")String opr) {
		logger.info("Get StoreSLocation : " + storeLocationId);
		logger.info("Opr : " + opr);
		StoreLocationOutMessage storeLocationOutMessage = null;
		
			
		if (StringUtils.hasText(storeLocationId)) {
			int id = NumberUtils.parseNumber(storeLocationId, Integer.class);
			StoreLocationInputMessage storeLocationInputMessage = new StoreLocationInputMessage();
			StoreLocationDTO storeLocationDto = new StoreLocationDTO();
			storeLocationDto.setStoreLocationId(id);
			storeLocationInputMessage.setStoreLocationDTO(storeLocationDto);
			
			if("R".equals(opr)){
				logger.info("RRRRRRRRRRRRRRR");
				storeLocationOutMessage = storeLocationService.checkBeforeRemove(storeLocationInputMessage);
				if(storeLocationOutMessage!=null){
				ErrorListDTO errorListDTO=storeLocationOutMessage.getErrorListDTO();
				if (errorListDTO != null && errorListDTO.hasErrors()) {				
					ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
					ModelAndView mav = new ModelAndView("forward:show_storelocation_form");
					mav.addObject("errors", errorDTO);					
					return mav;
				}
				}
			}
			
			
			
			storeLocationOutMessage=storeLocationService.findStoreLocationById(storeLocationInputMessage);
            ArrayList<StoreLocationDTO> list = (ArrayList<StoreLocationDTO>) storeLocationOutMessage
					.getStoreLocationDTOList();
			if (list.size() == 1) {
				System.out.println("get data for storeLocationId : " + id);
				System.out.println(list.get(0));
				storeLocationForm = new StoreLocationForm();
				storeLocationForm.setStoreLocationDTO(list.get(0));
			}
			
		}
		ModelAndView mav = new ModelAndView("store_location_detail");
		mav.addObject("storeLocationForm", storeLocationForm);
		mav.addObject("opr", opr);
		return mav;
	}
	@RequestMapping(value = "/remove_storelocation", method = RequestMethod.GET)
	public String deleteStoreLocation(@RequestParam("id") String storeLocationId,Model model) {
		if (StringUtils.hasText(storeLocationId)) {
			int id = NumberUtils.parseNumber(storeLocationId, Integer.class);
			StoreLocationInputMessage storeLocationInputMessage = new StoreLocationInputMessage();
			StoreLocationDTO storeLocationDto = new StoreLocationDTO();
			storeLocationDto.setStoreLocationId(id);
			storeLocationDto.setModifiedUserId(getCreatedUserId());
			storeLocationInputMessage.setStoreLocationDTO(storeLocationDto);
			storeLocationService.deleteStoreLocation(storeLocationInputMessage);
		}		
		String succ="Dl";
		model.addAttribute("succ", succ);
		return "redirect:/show_storelocation_form";
	}
}
