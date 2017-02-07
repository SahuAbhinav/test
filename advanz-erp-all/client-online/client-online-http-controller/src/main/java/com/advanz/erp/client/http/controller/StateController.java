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

import com.advanz.erp.client.http.controller.form.CityForm;
import com.advanz.erp.client.http.controller.form.CountryForm;
import com.advanz.erp.client.http.controller.form.ItemForm;
import com.advanz.erp.client.http.controller.form.StateForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.CityDTO;
import com.advanz.erp.masters.model.CountryDTO;
import com.advanz.erp.masters.model.ItemCategoryDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.ItemGroupDTO;
import com.advanz.erp.masters.model.StateDTO;
import com.advanz.erp.masters.model.ZoneDTO;
import com.advanz.erp.masters.model.msg.CityInputMessage;
import com.advanz.erp.masters.model.msg.CityOutputMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.StateInputMessage;
import com.advanz.erp.masters.model.msg.StateOutputMessage;
import com.advanz.erp.masters.model.msg.ZoneOutputMessage;
import com.advanz.erp.masters.service.business.IStateService;
import com.advanz.erp.masters.service.business.IZoneService;

@Controller
public class StateController extends BaseController{
private static final Logger logger = LoggerFactory.getLogger(StateController.class);
    
    @Autowired
    public IStateService stateService;
  
    @Autowired
    public IZoneService zoneService;

    @RequestMapping(value = "/show_new_state_form", method = RequestMethod.GET)
    public ModelAndView showForm(@ModelAttribute("stateForm") StateForm stateForm,@RequestParam(value="menuId",required=false) String menuId,HttpSession session) {
    	if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
   
    	ModelAndView mav = new ModelAndView("state-list");
	if (stateForm == null) {
		stateForm = new StateForm();
	}
	StateOutputMessage stateOutputMessage = stateService.findAllStates();
	ArrayList<StateDTO> list = (ArrayList<StateDTO>) stateOutputMessage.getStateDTOList();
	mav.addObject("stateList", list);
	mav.addObject("stateForm", stateForm);
	return mav;
    }
    
    @RequestMapping(value = "/add_state_form", method = RequestMethod.GET)
	public ModelAndView addStateForm(@ModelAttribute("stateForm") StateForm stateForm) {
		ModelAndView mav = new ModelAndView("state-form");
		if (stateForm == null) {
			stateForm = new StateForm();
		}
		mav.addObject("stateForm", stateForm);
		return mav;
	}
    
    @RequestMapping(value = "/get_state", method = RequestMethod.GET)
	public 	ModelAndView getAreaData(@RequestParam("stateId") String stateId,@RequestParam("opr")String opr) {
		logger.info("Get stateId: " + stateId);
		logger.info("Opr : " + opr);
		StateForm stateForm=null;
		StateOutputMessage stateOutputMessage = null;
		if (StringUtils.hasText(stateId)) {
			int id=NumberUtils.parseNumber(stateId, Integer.class);
			StateInputMessage stateInputMessage = new StateInputMessage();
			StateDTO stateDTO = new StateDTO();
			stateDTO.setStateId(id);
			stateInputMessage.setStateDTO(stateDTO);
			stateOutputMessage = stateService.findStateById(stateInputMessage);
			ArrayList<StateDTO> list = (ArrayList<StateDTO>) stateOutputMessage.getStateDTOList();
			if (list.size()>0) {
				stateForm = new StateForm();
				stateForm.setStateDTO(list.get(0));
			}
		}

		ModelAndView mav = new ModelAndView("state-edit");
		mav.addObject("stateForm", stateForm);
		mav.addObject("opr",opr);
		logger.info(stateForm.getStateDTO().toString());
		return mav;
	}
    
    @RequestMapping(value = "/save_state", method = RequestMethod.POST)
	public String saveItem(@ModelAttribute("stateForm") StateForm stateForm,Model model) {
		logger.info(stateForm.getStateDTO().toString());
		StateInputMessage stateInputMessage = new StateInputMessage();
		StateDTO stateDTO= stateForm.getStateDTO();
		stateDTO.setCreatedUserId(getCreatedUserId());
		stateInputMessage.setStateDTO(stateDTO);
		StateOutputMessage stateOutputMessage=stateService.createState(stateInputMessage);
		
		ErrorListDTO errorListDTO=stateOutputMessage.getErrorListDTO();
		if(errorListDTO!=null && errorListDTO.hasErrors()){
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errorList",errorDTO);
			return "state-form";
		}
		String succ="Ad";
		model.addAttribute("succ", succ);
		return "redirect:/show_new_state_form";
	}
    
    @RequestMapping(value = "/get_state_data", method = RequestMethod.POST)    
   public @ResponseBody
	ModelAndView getItemData(@ModelAttribute("stateName") String stateName,
			@ModelAttribute("stateCode") String stateCode,@ModelAttribute("zoneName") String zoneName,
			@ModelAttribute("stateForm") StateForm stateForm,Model model)
    	{
		ModelAndView mav=new ModelAndView("state-list");
		ArrayList<StateDTO>list = null;
		StateOutputMessage stateOutputMessage = null;
		if (StringUtils.hasText(stateName) || StringUtils.hasText(stateCode) || StringUtils.hasText(zoneName)) {
			StateInputMessage stateInputMessage = new StateInputMessage();
			StateDTO stateDTO = new StateDTO();
			stateDTO.setZoneDTO(new ZoneDTO());
			stateDTO.getZoneDTO().setZoneName(zoneName);
			stateDTO.setStateName(stateName);
			stateDTO.setStateCode(stateCode);
			stateInputMessage.setStateDTO(stateDTO);
			stateOutputMessage = stateService.findState(stateInputMessage);
			list = (ArrayList<StateDTO>) stateOutputMessage.getStateDTOList();
		} else {
			stateOutputMessage = stateService.findAllStates();
			list = (ArrayList<StateDTO>) stateOutputMessage.getStateDTOList();
		}
		mav.addObject("stateForm",stateForm);
		mav.addObject("stateList", list);
		String succ="Blk";
		if(list.equals(null) || list.size()==0)
		{
		  model.addAttribute("succ", succ);
		}
		return mav;

    }
    
    @RequestMapping(value = "/remove_state", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView removeState(@ModelAttribute("stateForm") StateForm stateForm,
			@ModelAttribute("stateId") Integer stateId,Model model) {
    	logger.info("Removing..........stateId = "+stateId);
    	StateOutputMessage stateOutputMessage=null;
    	ModelAndView mav =null;
    	
    		StateInputMessage stateInputMessage=new StateInputMessage();
    		StateDTO stateDTO=new StateDTO();
    		stateDTO.setStateId(stateId);
    		stateDTO.setModifiedUserId(getCreatedUserId());
    		stateInputMessage.setStateDTO(stateDTO);
    		stateOutputMessage= stateService.deleteState(stateInputMessage);
    		
		 if(stateOutputMessage.getErrorListDTO()!=null && stateOutputMessage.getErrorListDTO().hasErrors()){
			mav=new ModelAndView("state-edit");
			ErrorDTO errorDTO=stateOutputMessage.getErrorListDTO().getErrorList().get(0);
			mav.addObject("errorList",errorDTO);
			mav.addObject("opr","R");
			stateOutputMessage = stateService.findStateById(stateInputMessage);
			ArrayList<StateDTO> list = (ArrayList<StateDTO>) stateOutputMessage.getStateDTOList();
				
			if (list.size()>0) {
				stateForm = new StateForm();
				stateForm.setStateDTO(list.get(0));
			}
			mav.addObject("stateForm", stateForm);
    	}else{
    	 mav = new ModelAndView("state-list");
    	stateOutputMessage = stateService.findAllStates();
		ArrayList<StateDTO> list = (ArrayList<StateDTO>) stateOutputMessage.getStateDTOList();
		mav.addObject("stateList", list);
        mav.addObject("stateForm", stateForm);
	   
    	}
		String succ="Dl";
		model.addAttribute("succ", succ);
		return mav;
	}

 
    @RequestMapping(value = "/update_state", method = RequestMethod.POST)
    public String updateState(@ModelAttribute("stateForm") StateForm stateForm,Model model) {
    	logger.info("stateForm = "+stateForm);
		logger.info(stateForm.getStateDTO().toString());
		StateInputMessage stateInputMessage =new StateInputMessage();
	//	itemForm.getItemDTO().setd
		StateDTO stateDTO=stateForm.getStateDTO();
		stateDTO.setModifiedUserId(getCreatedUserId());
		stateInputMessage.setStateDTO(stateDTO);
		StateOutputMessage stateOutputMessage=stateService.updateState(stateInputMessage);
		
		ErrorListDTO errorListDTO=stateOutputMessage.getErrorListDTO();
		if(errorListDTO!=null && errorListDTO.hasErrors()){
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errorList",errorDTO);
			return "edit_state";
		}
		String succ="Up";
		 model.addAttribute("succ",succ);		
		return "redirect:/show_new_state_form";
	}
    
//  

    @ModelAttribute("zoneList")
	public List<ZoneDTO> zoneList() {
		List<ZoneDTO> itemGroupFlag = new ArrayList<ZoneDTO>();
		ZoneOutputMessage zoneOutputMessage = zoneService.findAllZones();
	    return zoneOutputMessage.getZoneDTOList();
	}
}
