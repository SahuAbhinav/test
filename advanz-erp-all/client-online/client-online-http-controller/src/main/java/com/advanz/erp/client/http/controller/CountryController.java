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

import com.advanz.erp.client.http.controller.form.CountryForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.CountryDTO;
import com.advanz.erp.masters.model.criteria.CountrySearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.CountryInputMessage;
import com.advanz.erp.masters.model.msg.CountryOutMessage;
import com.advanz.erp.masters.service.business.ICountryService;

@Controller
public class CountryController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CountryController .class);
    
    @Autowired
    public ICountryService countryService;
    
    @RequestMapping(value = "/get_country_list")
	public  ModelAndView searchArea(@ModelAttribute("countrySearchCriteria")CountrySearchCriteriaDTO searchCriteria,Model model,@ModelAttribute("countryForm")CountryForm countryForm,@RequestParam(value="menuId",required=false) String menuId,HttpSession session) {
    	if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
			List<CountryDTO> list = new ArrayList<CountryDTO>();		
			CountryOutMessage countryOutputMessage = null;
			CountryInputMessage countryInputMessage = new CountryInputMessage();
			countryInputMessage.setSearchCriteria(searchCriteria);
			 countryOutputMessage = countryService.search(countryInputMessage);
			 list = (ArrayList<CountryDTO>) countryOutputMessage.getCountryDTOList();	
			ModelAndView mav=new ModelAndView("country-list");
			mav.addObject("list", list);
			mav.addObject("countrySearchCriteria",searchCriteria);			
			String succ="Blk";
			if(list.equals(null) || list.size()==0)
			{
			  model.addAttribute("succ", succ);
			}
			mav.addObject("countryForm", countryForm);
		return mav;
	}
    
    
    @RequestMapping(value = "/show_country", method = RequestMethod.GET)
    public ModelAndView showForm(@ModelAttribute("countryForm") CountryForm countryForm){
    	ModelAndView mav = new ModelAndView("country-form");
//    	ModelAndView mav = new ModelAndView("countryForm");
	if (countryForm== null) {
		countryForm= new CountryForm();
	}
	mav.addObject("countryForm", countryForm);
	return mav;
    }  
    
    @RequestMapping(value = "/save_country", method = RequestMethod.POST)
    public String saveCountry(@ModelAttribute("countryForm") CountryForm countryForm,Model model) {
    	logger.info("countryForm = "+countryForm);
		logger.info(countryForm.getCountryDTO().toString());
		CountryInputMessage countryInputMessage = new CountryInputMessage();
		CountryDTO countryDTO=	countryForm.getCountryDTO();
		countryDTO.setCreatedUserId(getCreatedUserId());
		countryInputMessage.setCountryDTO(countryDTO);
		CountryOutMessage countryOutputMessage=	countryService.createCountry(countryInputMessage);
		ErrorListDTO errorListDTO=countryOutputMessage.getErrorListDTO();
		
		if(errorListDTO!=null && errorListDTO.hasErrors()){
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errors",errorDTO);
			return "country-form";
		}
		String succ="Ad";
		model.addAttribute("succ",succ);
		return "redirect:/get_country_list";
	}


    // for edit and remove
    @RequestMapping(value = "/get_country", method = RequestMethod.GET)
	public 	ModelAndView getCountryData(@RequestParam("countryId") String countryId,@RequestParam("opr")String opr,Model model) {
		logger.info("Get Country : " + countryId);
		logger.info("Opr : " + opr);
		CountryForm countryForm=null;
		CountryOutMessage countryOutMessage = null;
		if (StringUtils.hasText(countryId)) {
		   int id=NumberUtils.parseNumber(countryId, Integer.class);
		   CountryInputMessage countryInputMessage = new CountryInputMessage();
		   CountryDTO countryDTO = new CountryDTO();
		   countryDTO.setCountryId(id);
		   countryInputMessage.setCountryDTO(countryDTO);
		  if ("R".equals(opr)) {
			countryOutMessage = countryService.checkBeforeRemove(countryInputMessage);
		    if (countryOutMessage != null) {
			  ErrorListDTO errorListDTO = countryOutMessage.getErrorListDTO();
			  if (errorListDTO != null && errorListDTO.hasErrors()) {
				ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
				model.addAttribute("errorList", errorDTO);
				ModelAndView mav = new ModelAndView("forward:get_country_list");
				return mav;
				}
			  }
			}
			countryOutMessage = countryService.findCountryById(countryInputMessage);
			ArrayList<CountryDTO> list = (ArrayList<CountryDTO>) countryOutMessage.getCountryDTOList();
			if (list.size()>0) {
				countryForm = new CountryForm();
				countryForm.setCountryDTO(list.get(0));
			}
		}

		ModelAndView mav = new ModelAndView("country-edit");
		mav.addObject("countryForm", countryForm);
		mav.addObject("opr",opr);
		logger.info(countryForm.getCountryDTO().toString());
		return mav;
	}
    

    
    @RequestMapping(value = "/update_country", method = RequestMethod.POST)
	public String updateArea(@ModelAttribute("countryForm") CountryForm countryForm,Model model) {
		logger.info("countryForm = " + countryForm);
		logger.info(countryForm.getCountryDTO().toString());
		CountryInputMessage countryInputMessage = new CountryInputMessage();
		CountryDTO countryDTO= countryForm.getCountryDTO();
		countryDTO.setModifiedUserId(getCreatedUserId());
		countryInputMessage.setCountryDTO(countryDTO);
		CountryOutMessage countryOutputMessage=countryService.updateCountry(countryInputMessage);
		ErrorListDTO errorListDTO=countryOutputMessage.getErrorListDTO();
		
		if(errorListDTO!=null && errorListDTO.hasErrors()){
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errorList", errorDTO);
			model.addAttribute("opr","E");
			return "country-edit";
		}	
		String succ="Up";
		model.addAttribute("succ", succ);
		return "redirect:/get_country_list";
	}
    
    
    @RequestMapping(value = "/remove_country", method = RequestMethod.GET)
	public ModelAndView removeArea(@RequestParam("countryId")String countryId,Model model,@ModelAttribute("countryForm")CountryForm countryForm,ModelMap modelMap) {
    	String succ="Dl";	
    	int id=NumberUtils.parseNumber(countryId, Integer.class);
			CountryInputMessage countryInputMessage = new CountryInputMessage();
			CountryDTO countryDTO=new CountryDTO();
			countryDTO.setCountryId(id);
			countryDTO.setModifiedUserId(getCreatedUserId());
			countryInputMessage.setCountryDTO(countryDTO);
			CountryOutMessage countryOutMessage= countryService.deleteCountry(countryInputMessage);
			ModelAndView mav =null;
			if(countryOutMessage.getErrorListDTO()!=null && countryOutMessage.getErrorListDTO().hasErrors()){
				mav=new ModelAndView("country-edit");
				ErrorDTO errorDTO=countryOutMessage.getErrorListDTO().getErrorList().get(0);
				mav.addObject("errorList",errorDTO);
				mav.addObject("opr","R");
				countryOutMessage = countryService.findCountryById(countryInputMessage);
				ArrayList<CountryDTO> list = (ArrayList<CountryDTO>) countryOutMessage.getCountryDTOList();
				 countryForm=new CountryForm();
				if (list.size()>0) {
					countryForm = new CountryForm();
					countryForm.setCountryDTO(list.get(0));
				}
			
				mav.addObject("countryForm", countryForm);
			}else{	
			    mav=new ModelAndView("forward:get_country_list");
			}
			modelMap.addAttribute("succ", succ);
			//model.addAttribute("succ",succ);
		    return mav;
	   }  
     }
