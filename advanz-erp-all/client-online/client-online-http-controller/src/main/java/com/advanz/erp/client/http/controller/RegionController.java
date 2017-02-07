package com.advanz.erp.client.http.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.RegionForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.RegionDTO;
import com.advanz.erp.masters.model.StateDTO;
import com.advanz.erp.masters.model.criteria.RegionSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.RegionInputMessage;
import com.advanz.erp.masters.model.msg.RegionOutputMessage;
import com.advanz.erp.masters.model.msg.StateOutputMessage;
import com.advanz.erp.masters.service.business.IRegionService;
import com.advanz.erp.masters.service.business.IStateService;

@Controller
public class RegionController extends BaseController{
private static final Logger logger = LoggerFactory.getLogger(RegionController.class);
    
    @Autowired
    public IRegionService regionService;
    @Autowired
    public IStateService stateService;
    
    @RequestMapping(value = "/get_region_list")
	public  ModelAndView searchRegion(@ModelAttribute("regionSearchCriteria")RegionSearchCriteriaDTO searchCriteria,Model model,@ModelAttribute("regionForm") RegionForm regionForm,@RequestParam(value="menuId",required=false) String menuId,HttpSession session) {
    	if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
	
		List<RegionDTO> list = new ArrayList<RegionDTO>();		
			RegionOutputMessage regionOutputMessage = null;
			RegionInputMessage regionInputMessage = new RegionInputMessage();
			 regionInputMessage.setSearchCriteria(searchCriteria);
			 regionOutputMessage = regionService.search(regionInputMessage);
			 list = (ArrayList<RegionDTO>) regionOutputMessage.getRegionDTOList();	
			ModelAndView mav=new ModelAndView("region-list");
			mav.addObject("regionList", list);
			mav.addObject("regionSearchCriteria",searchCriteria);
			
			if(list.equals(null) || list.size()==0)
			{
			String succ="Blk";
			 model.addAttribute("succ", succ);
			}		
			mav.addObject("regionForm", regionForm);
		return mav;
	}
    
   
    @RequestMapping(value = "/show_new_region_form", method = RequestMethod.GET)
    public ModelAndView showForm(@ModelAttribute("regionForm") RegionForm regionForm) {    	
    	ModelAndView mav = new ModelAndView("region-detail");
	if (regionForm == null) {
		regionForm = new RegionForm();
	}
	mav.addObject("regionForm", regionForm);
	return mav;
    }
    
    
    @RequestMapping(value = "/save_region", method = RequestMethod.POST)
	public String saveRegion(@ModelAttribute("regionForm") RegionForm regionForm,Model model) {
		logger.info("regionForm = " + regionForm);
		logger.info(regionForm.getRegionDTO().toString());
		RegionInputMessage regionInputMessage = new RegionInputMessage();
		RegionDTO regionDTO=regionForm.getRegionDTO();
		regionDTO.setCreatedUserId(getCreatedUserId());
		regionInputMessage.setRegionDTO(regionDTO);
		RegionOutputMessage regionOutputMessage=regionService.createRegion(regionInputMessage);
		ErrorListDTO errorListDTO=regionOutputMessage.getErrorListDTO();
		
		if(errorListDTO!=null && errorListDTO.hasErrors()){
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error "+errorDTO.getErrorMsg());
			model.addAttribute("errors",errorDTO);
			return "region-detail";
		}
		String succ="Ad";
		model.addAttribute("succ", succ);
		return "redirect:/get_region_list";
	}

    @RequestMapping(value = "/get_region", method = RequestMethod.GET)
	public 	ModelAndView getRegionData(@RequestParam("regionId") String regionId,@RequestParam("opr")String opr) {
		logger.info("Get Region : " + regionId);
		logger.info("Opr : " + opr);
		RegionForm regionForm=null;
		RegionOutputMessage regionOutputMessage = null;
		if (StringUtils.hasText(regionId)) {
			int id=NumberUtils.parseNumber(regionId, Integer.class);
			RegionInputMessage regionInputMessage = new RegionInputMessage();
			RegionDTO regionDTO = new RegionDTO();
			regionDTO.setRegionId(id);
			regionInputMessage.setRegionDTO(regionDTO);
			if("R".equals(opr)){				
				regionOutputMessage = regionService.checkBeforeRemove(regionInputMessage);
				if(regionOutputMessage!=null){
					ErrorListDTO errorListDTO=regionOutputMessage.getErrorListDTO();
					if (errorListDTO != null && errorListDTO.hasErrors()) {
						ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
						ModelAndView mav = new ModelAndView("forward:get_region_list");
						mav.addObject("errorList", errorDTO);
						return mav;
					}
				}
			}
			regionOutputMessage = regionService.findRegionById(regionInputMessage);
			ArrayList<RegionDTO> list = (ArrayList<RegionDTO>) regionOutputMessage
					.getRegionDTOList();
			if (list.size()>0) {
				regionForm = new RegionForm();
				regionForm.setRegionDTO(list.get(0));
			}
		}
		ModelAndView mav = new ModelAndView("region-edit");
		mav.addObject("regionForm", regionForm);
		mav.addObject("opr",opr);
		logger.info(regionForm.getRegionDTO().toString());
		return mav;
	}
    
    @RequestMapping(value = "/update_region", method = RequestMethod.POST)
	public String updateRegion(@ModelAttribute("regionForm") RegionForm regionForm,Model model) {
		logger.info("regionForm = " + regionForm);
		logger.info(regionForm.getRegionDTO().toString());
		RegionInputMessage regionInputMessage = new RegionInputMessage();
		RegionDTO regionDTO=regionForm.getRegionDTO();
		regionDTO.setModifiedUserId(getCreatedUserId());
		regionInputMessage.setRegionDTO(regionDTO);
		RegionOutputMessage regionOutputMessage=	regionService.updateRegion(regionInputMessage);
		ErrorListDTO errorListDTO=regionOutputMessage.getErrorListDTO();
		
		if(errorListDTO!=null && errorListDTO.hasErrors()){
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errorList", errorDTO);
			model.addAttribute("opr","E");
			return "region-edit";
		}
		String succ="Up";
		model.addAttribute("succ", succ);
		return "redirect:/get_region_list";
	}
    
    @RequestMapping(value = "/remove_region", method = RequestMethod.GET)
	public String removeRegion(@RequestParam("regionId")String regionId,@ModelAttribute("regionForm") RegionForm regionForm,Model model) {
    	if (StringUtils.hasText(regionId)) {
			int id=NumberUtils.parseNumber(regionId, Integer.class);
		RegionInputMessage regionInputMessage = new RegionInputMessage();
		RegionDTO regionDTO=new RegionDTO();
		regionDTO.setRegionId(id);
		regionDTO.setModifiedUserId(getCreatedUserId());
		regionInputMessage.setRegionDTO(regionDTO);
		RegionOutputMessage regionOutputMessage=regionService.deleteRegion(regionInputMessage);
    	}
    	String succ="Dl";
    	model.addAttribute("succ", succ);
		return "redirect:/get_region_list";
	}
    
    @ModelAttribute("states")
    public List<StateDTO> getStates(@ModelAttribute("rows") ArrayList<StateDTO> list) {
    	StateOutputMessage stateOutputMessage = stateService.findAllStates();
    	list = (ArrayList<StateDTO>)stateOutputMessage.getStateDTOList();
    	logger.debug("State list -- :-"+list);
    	return list;
    	
    }

}
