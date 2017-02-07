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
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.ZoneForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;

import com.advanz.erp.masters.model.CountryDTO;
import com.advanz.erp.masters.model.StateDTO;
import com.advanz.erp.masters.model.ZoneDTO;
import com.advanz.erp.masters.model.criteria.ZoneSearchCriteriaDTO;

import com.advanz.erp.masters.model.msg.CountryOutMessage;
import com.advanz.erp.masters.model.msg.ZoneInputMessage;
import com.advanz.erp.masters.model.msg.ZoneOutputMessage;
import com.advanz.erp.masters.service.business.ICountryService;
import com.advanz.erp.masters.service.business.IZoneService;

@Controller
public class ZoneController extends BaseController{
	
private static final Logger logger = LoggerFactory.getLogger(ZoneController.class);
    
    @Autowired
    public IZoneService zoneService;
    @Autowired
    public ICountryService countryService;
   
	 @RequestMapping(value = "/get_zone_list")		
		public ModelAndView searchZone(@ModelAttribute("zoneSearchCriteria") ZoneSearchCriteriaDTO searchCriteria,@ModelAttribute("zoneForm") ZoneForm zoneForm,Model model,@RequestParam(value="menuId",required=false) String menuId,HttpSession session) {
		 if(menuId!=null)
			{
			session.setAttribute("menuId", menuId);
			}
			
		String succ="Blk";
		 List<ZoneDTO> list = new ArrayList<ZoneDTO>();	
		 ZoneOutputMessage zoneOutputMessage = null;
		 ZoneInputMessage zoneInputMessage = new ZoneInputMessage();
		 zoneInputMessage.setSearchCriteria(searchCriteria);
		 zoneOutputMessage = zoneService.search(zoneInputMessage);
		 list = (ArrayList<ZoneDTO>) zoneOutputMessage.getZoneDTOList();	
		ModelAndView mav=new ModelAndView("zone-list");
		mav.addObject("zoneList", list);
		mav.addObject("zoneSearchCriteria",searchCriteria);
		if(list.equals(null) || list.size()==0)
		{
		  model.addAttribute("succ", succ);
		}		
		mav.addObject("zoneForm", zoneForm);
     return mav;
		
	}
    
    @RequestMapping(value = "/show_zone_form", method = RequestMethod.GET)
    public ModelAndView showCountries(@ModelAttribute("zoneForm") ZoneForm zoneForm){
    	ModelAndView mav = new ModelAndView("zone-list");

	if (zoneForm== null) {
		zoneForm= new ZoneForm();
	}
	mav.addObject("zoneForm", zoneForm);
	ZoneOutputMessage zoneOutputMessage = zoneService.findAllZones();
	List<ZoneDTO>list = (ArrayList<ZoneDTO>)zoneOutputMessage.getZoneDTOList();
	System.out.println("zone List :-"+list);
	mav.addObject("zoneList",list);
	
	return mav;
    } 

	@RequestMapping(value = "/save_zone", method = RequestMethod.POST)
	
	public String saveZone1(@ModelAttribute("zoneForm") ZoneForm zoneForm,Model model) {
		logger.info(zoneForm.getZoneDTO().toString());
		ZoneInputMessage zoneInputMessage = new ZoneInputMessage();
		ZoneDTO zoneDTO=zoneForm.getZoneDTO();
		zoneDTO.setCreatedUserId(getCreatedUserId());
		zoneInputMessage.setZoneDTO(zoneDTO);
		ZoneOutputMessage zoneOutputMessage=zoneService.createZone(zoneInputMessage);
		String succ="";
		ErrorListDTO errorListDTO=zoneOutputMessage.getErrorListDTO();
		if(errorListDTO!=null && errorListDTO.hasErrors()){
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errors", errorDTO);
			return "addZone";
		}		
		succ="Ad";
		model.addAttribute("succ", succ);
			return "redirect:/get_zone_list";
	}
	
	 @RequestMapping(value = "/update_zone", method = RequestMethod.POST)
		public String updateRegion(@ModelAttribute("zoneForm") ZoneForm zoneForm,Model model) {
			logger.info("zoneForm = " + zoneForm.toString());
			logger.info("zone DTO ------------------"+zoneForm.getZoneDTO().toString());
			logger.info("zone DTO getDescription------------------"+zoneForm.getZoneDTO().getDescription());
			ZoneInputMessage zoneInputMessage = new ZoneInputMessage();
			ZoneDTO aa=zoneForm.getZoneDTO();
			aa.setModifiedUserId(getCreatedUserId());
			aa.setDescription(zoneForm.getZoneDTO().getDescription());
			zoneInputMessage.setZoneDTO(aa);
			ZoneOutputMessage zoneOutputMessage=	zoneService.updateZone(zoneInputMessage);
			String succ="";
			ErrorListDTO errorListDTO=zoneOutputMessage.getErrorListDTO();
			
			if(errorListDTO!=null && errorListDTO.hasErrors()){
				ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
				logger.info(" adding Error ");
				model.addAttribute("errors", errorDTO);
				model.addAttribute("opr","E");
				return "zone-edit";
			}
			succ="Up";
			model.addAttribute("succ", succ);
				return "redirect:/get_zone_list";

		}
	 
	 @RequestMapping(value = "/remove_zone", method = RequestMethod.GET)
		
		public ModelAndView removeZone(@ModelAttribute("zoneId") String id,@ModelAttribute("zoneForm") ZoneForm zoneForm,ModelMap model){    	
		  zoneForm=null;
	    	logger.info("Removing..........zoneId = "+id);	
	    	ModelAndView mav =null;
	    	if(StringUtils.hasText(id)){
	    		Integer zoneId = Integer.parseInt(id);
	    		ZoneInputMessage zoneInputMessage=new ZoneInputMessage();
	    		ZoneDTO zoneDTO=new ZoneDTO();
	    		zoneDTO.setZoneId(zoneId);
	    		zoneDTO.setModifiedUserId(getCreatedUserId());
	    		zoneInputMessage.setZoneDTO(zoneDTO);	    		
	    		ZoneOutputMessage zoneOutputMessage=	zoneService.deleteZone(zoneInputMessage);
				
				ErrorListDTO errorListDTO=zoneOutputMessage.getErrorListDTO();
				
				if(errorListDTO!=null && errorListDTO.hasErrors()){
					ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
					mav = new ModelAndView("zone-edit");
					logger.info(" adding Error ");
					mav.addObject("errors",errorDTO);
					mav.addObject("opr","R");
					zoneOutputMessage = zoneService.findZoneById(zoneInputMessage);
					ArrayList<ZoneDTO> list=(ArrayList<ZoneDTO>)zoneOutputMessage.getZoneDTOList();
					if(list!=null){
						zoneForm=new ZoneForm();
						logger.info(" list list :- "+list.get(0));
						zoneForm.setZoneDTO(list.get(0));
					}
					mav.addObject("zoneForm", zoneForm);
					return mav;
				}else{
					 mav = new ModelAndView("zone-list");
					 ZoneSearchCriteriaDTO searchCriteria= new ZoneSearchCriteriaDTO();
					 zoneOutputMessage = zoneService.findAllZones();
						ArrayList<ZoneDTO> list = (ArrayList<ZoneDTO>) zoneOutputMessage.getZoneDTOList();
						mav.addObject("zoneList", list);
				        mav.addObject("zoneSearchCriteria", searchCriteria);
				}
	    	}
	    	
	    	String succ="Dl";
			model.addAttribute("succ", succ);
			mav.addObject("zoneForm", zoneForm);
	    	return mav;
		}
	
	@RequestMapping(value = "/add_zone_form", method = RequestMethod.GET)
	public ModelAndView addItemGroupForm(
			@ModelAttribute("zoneForm") ZoneForm zoneForm) {
		ModelAndView mav = new ModelAndView("addZone");
		if (zoneForm == null) {
			zoneForm = new ZoneForm();
		}
		mav.addObject("zoneForm", zoneForm);
		return mav;
	}
	
	
	
	@ModelAttribute("countryList")
	public List<CountryDTO> countryList() {
		List<CountryDTO> items = new ArrayList<CountryDTO>();
		CountryOutMessage countryOutMessage = countryService.findAllCountrys();
				
		return countryOutMessage.getCountryDTOList();
	}
	
	 @RequestMapping(value = "/get_zone", method = RequestMethod.GET)		
		ModelAndView getItemCategoryData(@ModelAttribute("zoneForm") ZoneForm zoneForm,@ModelAttribute("zoneId") String zoneId,@RequestParam("opr")String opr,Model model) {
			logger.info("Get Zone : " + zoneId);
			logger.info("Opr : " + opr);
			
			ZoneOutputMessage zoneOutputMessage = null;
			if (StringUtils.hasText(zoneId)) {
				int id = NumberUtils.parseNumber(zoneId, Integer.class);
				ZoneInputMessage zoneInputMessage = new ZoneInputMessage();
				ZoneDTO zoneDTO = new ZoneDTO();
				zoneDTO.setZoneId(id);
				zoneInputMessage.setZoneDTO(zoneDTO);
				
				
		if ("R".equals(opr)) {
			zoneOutputMessage = zoneService.checkBeforeRemove(zoneInputMessage);
		  if (zoneOutputMessage != null) {
			  ErrorListDTO errorListDTO = zoneOutputMessage.getErrorListDTO();
			if (errorListDTO != null && errorListDTO.hasErrors()) {
				ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
				model.addAttribute("errors",errorDTO);
				ModelAndView mav = new ModelAndView("forward:get_zone_list");
				return mav;
				}
		      }
			}
			zoneOutputMessage = zoneService.findZoneById(zoneInputMessage);
			//System.out.print("zoneOutputMessage -----"+zoneOutputMessage.getZoneDTOList());
			ArrayList<ZoneDTO> list = (ArrayList<ZoneDTO>) zoneOutputMessage.getZoneDTOList();
			System.out.print("sfsfsef  list llllll---size--"+list.size());
			for(int i=0;i<list.size();i++){
			ZoneDTO aa=list.get(i);
			if(aa.getZoneId().equals(id)){
	 			zoneForm = new ZoneForm();	
				zoneForm.setZoneDTO(list.get(i));
	    		}
		    	System.out.print("aa.getZoneId() -----"+aa.getZoneId());
		 	 }
			}
			ModelAndView mav = new ModelAndView("zone-edit");
			mav.addObject("zoneForm", zoneForm);
			mav.addObject("opr",opr);
			
			return mav;
		}
	}
