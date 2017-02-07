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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.AreaForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.AreaDTO;
import com.advanz.erp.masters.model.RegionDTO;
import com.advanz.erp.masters.model.criteria.AreaSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.AreaInputMessage;
import com.advanz.erp.masters.model.msg.AreaOutputMessage;
import com.advanz.erp.masters.model.msg.RegionOutputMessage;
import com.advanz.erp.masters.service.business.IAreaService;
import com.advanz.erp.masters.service.business.IRegionService;

@Controller
public class AreaController extends BaseController{
	
private static final Logger logger = LoggerFactory.getLogger(AreaController.class);
    
    @Autowired
    public IAreaService areaService;
    
    @Autowired
    public IRegionService regionService;

   
    @RequestMapping(value = "/get_area_list")
	public  ModelAndView searchArea(@ModelAttribute("areaSearchCriteria")AreaSearchCriteriaDTO searchCriteria,@ModelAttribute("areaForm") AreaForm areaForm,@RequestParam(value="menuId", required = false) String menuId,Model model,HttpSession session) {
    	String succ="Blk";
    	if(menuId!=null)
    	{
    		session.setAttribute("menuId", menuId);
    	}
    	List<AreaDTO> list = new ArrayList<AreaDTO>();		
			AreaOutputMessage areaOutputMessage = null;
			AreaInputMessage areaInputMessage = new AreaInputMessage();
			 areaInputMessage.setSearchCriteria(searchCriteria);
			 areaOutputMessage = areaService.search(areaInputMessage);
			 list = (ArrayList<AreaDTO>) areaOutputMessage.getAreaDTOList();	
			ModelAndView mav=new ModelAndView("area-list");
			mav.addObject("menuId", menuId);
			mav.addObject("areaList", list);
			mav.addObject("areaSearchCriteria",searchCriteria);
			if(list.equals(null) || list.size()==0)
			{
			  model.addAttribute("succ", succ);
			}	
			
			mav.addObject("areaForm", areaForm);
		return mav;
	}
    
 
    @RequestMapping(value = "/show_new_area_form", method = RequestMethod.GET)
    public ModelAndView showForm(@ModelAttribute("areaForm") AreaForm areaForm) {    	
    	ModelAndView mav = new ModelAndView("area-detail");
	if (areaForm == null) {
		areaForm = new AreaForm();
	}
	mav.addObject("areaForm", areaForm);
	return mav;
    }
    
    
    
    @RequestMapping(value = "/save_area", method = RequestMethod.POST)
	public String saveArea(@ModelAttribute("areaForm") AreaForm areaForm,Model model) {
		//logger.info("areaForm = " + areaForm);
	//	logger.info(areaForm.getAreaDTO().toString());
		AreaInputMessage areaInputMessage = new AreaInputMessage();
		AreaDTO areaDTO= areaForm.getAreaDTO();
		areaDTO.setCreatedUserId(getCreatedUserId());
		areaInputMessage.setAreaDTO(areaDTO);
		AreaOutputMessage areaOutputMessage=	areaService.createArea(areaInputMessage);
		ErrorListDTO errorListDTO=areaOutputMessage.getErrorListDTO();		
		if(errorListDTO!=null && errorListDTO.hasErrors()){
			ErrorDTO  errorDTO=errorListDTO.getErrorList().get(0);
			//logger.info(" adding Error ");
			model.addAttribute("errors", errorDTO);
			return "area-detail";
		}	
		String succ="Ad";
		model.addAttribute("succ", succ);
		return "redirect:/get_area_list";

	}

    // for edit and remove
    @RequestMapping(value = "/get_area", method = RequestMethod.GET)
	public 	ModelAndView getAreaData(@RequestParam("areaId") String areaId,@RequestParam("opr")String opr) {
		//logger.info("Get Area : " + areaId);
		//logger.info("Opr : " + opr);
		AreaForm areaForm=null;
		AreaOutputMessage areaOutputMessage = null;
		if (StringUtils.hasText(areaId)) {
			int id=NumberUtils.parseNumber(areaId, Integer.class);
			AreaInputMessage areaInputMessage = new AreaInputMessage();
			AreaDTO areaDTO = new AreaDTO();
			areaDTO.setAreaId(id);
			areaInputMessage.setAreaDTO(areaDTO);
			if("R".equals(opr)){				
				areaOutputMessage = areaService.checkBeforeRemove(areaInputMessage);
				if(areaOutputMessage!=null){
					ErrorListDTO errorListDTO=areaOutputMessage.getErrorListDTO();
					if (errorListDTO != null && errorListDTO.hasErrors()) {	
						ErrorDTO  errorDTO=errorListDTO.getErrorList().get(0);
						ModelAndView mav = new ModelAndView("forward:get_area_list");
						mav.addObject("errors", errorDTO);
						return mav;
					}
				}
			}
			areaOutputMessage = areaService.findAreaById(areaInputMessage);
			ArrayList<AreaDTO> list = (ArrayList<AreaDTO>) areaOutputMessage.getAreaDTOList();
			if (list.size()>0) {
				areaForm = new AreaForm();
				areaForm.setAreaDTO(list.get(0));
			}
		}

		ModelAndView mav = new ModelAndView("area-edit");
		mav.addObject("areaForm", areaForm);
		mav.addObject("opr",opr);
		//logger.info(areaForm.getAreaDTO().toString());
		return mav;
	}
    
    @RequestMapping(value = "/update_area", method = RequestMethod.POST)
	public String updateArea(@ModelAttribute("areaForm") AreaForm areaForm,Model model) {
		//logger.info("areaForm = " + areaForm);
		//logger.info(areaForm.getAreaDTO().toString());
		AreaInputMessage areaInputMessage = new AreaInputMessage();
		AreaDTO areaDTO= areaForm.getAreaDTO();
		areaDTO.setModifiedUserId(getCreatedUserId());
		areaInputMessage.setAreaDTO(areaDTO);
		AreaOutputMessage areaOutputMessage=	areaService.updateArea(areaInputMessage);
		ErrorListDTO errorListDTO=areaOutputMessage.getErrorListDTO();
		
		if(errorListDTO!=null && errorListDTO.hasErrors()){
			ErrorDTO  errorDTO=errorListDTO.getErrorList().get(0);
		//	logger.info(" adding Error ");
			model.addAttribute("errors",errorDTO);
			model.addAttribute("opr","E");
			return "area-edit";
		}
		String succ="Up";
		model.addAttribute("succ", succ);
		return "redirect:/get_area_list";
	}
    
    @RequestMapping(value = "/remove_area", method = RequestMethod.GET)
	public String removeArea(@ModelAttribute("areaForm") AreaForm areaForm,@RequestParam("areaId")String areaId,Model model) {
    	if (StringUtils.hasText(areaId)) {
			int id=NumberUtils.parseNumber(areaId, Integer.class);
		AreaInputMessage areaInputMessage = new AreaInputMessage();
		AreaDTO areaDTO=new AreaDTO();
		areaDTO.setAreaId(id);
		areaDTO.setModifiedUserId(getCreatedUserId());
		areaInputMessage.setAreaDTO(areaDTO);
		areaService.deleteArea(areaInputMessage);
    	}
    	String succ="Dl";
		model.addAttribute("succ", succ);
		return "redirect:/get_area_list";

	}
    

    @ModelAttribute("regiones")
    public @ResponseBody List<RegionDTO> getStates(@ModelAttribute("rows") ArrayList<RegionDTO> list) {

    	RegionOutputMessage regionOutputMessage = regionService.findAllRegions();
    	list = (ArrayList<RegionDTO>)regionOutputMessage.getRegionDTOList();
    	//logger.debug("regions list -- :-"+list);
    	return list;
    	
    }

}
