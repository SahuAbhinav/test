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
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.AreaDTO;
import com.advanz.erp.masters.model.CityDTO;
import com.advanz.erp.masters.model.CityStateCountryDTO;
import com.advanz.erp.masters.model.RegionDTO;
import com.advanz.erp.masters.model.StateDTO;
import com.advanz.erp.masters.model.ZoneDTO;
import com.advanz.erp.masters.model.criteria.CitySearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.AreaOutputMessage;
import com.advanz.erp.masters.model.msg.CityInputMessage;
import com.advanz.erp.masters.model.msg.CityOutputMessage;
import com.advanz.erp.masters.service.business.IAreaService;
import com.advanz.erp.masters.service.business.ICityService;

@Controller
public class CityController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(CityController.class);

	@Autowired
	public IAreaService areaService;

	@Autowired
	public ICityService cityService;

	@RequestMapping(value = "/get_city_list")
	public ModelAndView searchArea(@ModelAttribute("citySearchCriteria") CitySearchCriteriaDTO searchCriteria,@ModelAttribute("cityForm") CityForm cityForm,Model model,@RequestParam(value="menuId",required=false) String menuId,HttpSession session) {
		String succ="Blk";
		if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
		List<CityDTO> list = new ArrayList<CityDTO>();
		CityOutputMessage cityOutputMessage = null;
		CityInputMessage cityInputMessage = new CityInputMessage();
		cityInputMessage.setSearchCriteria(searchCriteria);
		cityOutputMessage = cityService.search(cityInputMessage);
		list = (ArrayList<CityDTO>) cityOutputMessage.getCityDTOList();
		ModelAndView mav = new ModelAndView("city-list");
		mav.addObject("cityList", list);
		mav.addObject("citySearchCriteria", searchCriteria);
		if(list.equals(null) || list.size()==0)
		{
		  model.addAttribute("succ", succ);
		}	
		
		mav.addObject("cityForm", cityForm);
		return mav;
	}

	@RequestMapping(value = "/save_city", method = RequestMethod.POST)
	public String saveCity(@ModelAttribute("cityForm") CityForm cityForm,Model model) {
		logger.info("cityForm = " + cityForm);
		logger.info(cityForm.getCityDTO().toString());
		CityInputMessage cityInputMessage = new CityInputMessage();
		CityDTO cityDTO=cityForm.getCityDTO();
		cityDTO.setCreatedUserId(getCreatedUserId());
		cityInputMessage.setCityDTO(cityDTO);
		CityOutputMessage cityOutputMessage = cityService.createCity(cityInputMessage);
		ErrorListDTO errorListDTO = cityOutputMessage.getErrorListDTO();

		if (errorListDTO != null && errorListDTO.hasErrors()) {
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errors",errorDTO);
			return "city-detail";
		}
		String succ="Ad";
		model.addAttribute("succ", succ);
		return "redirect:/get_city_list";
	}

	@RequestMapping(value = "/show_new_city_form", method = RequestMethod.GET)
	public ModelAndView showForm(@ModelAttribute("cityForm") CityForm cityForm) {
		ModelAndView mav = new ModelAndView("city-detail");
		if (cityForm == null) {
			cityForm = new CityForm();
		}
		mav.addObject("cityForm", cityForm);
		return mav;
	}

	// for edit and remove
	@RequestMapping(value = "/get_city", method = RequestMethod.GET)
	public ModelAndView getAreaData(@RequestParam("cityId") String cityId,
			@RequestParam("opr") String opr,Model model) {
		logger.info("Get city: " + cityId);
		logger.info("Opr : " + opr);
		CityForm cityForm = null;
		CityOutputMessage cityOutputMessage = null;
		if (StringUtils.hasText(cityId)) {
			int id = NumberUtils.parseNumber(cityId, Integer.class);
			CityInputMessage cityInputMessage = new CityInputMessage();
			CityDTO cityDTO = new CityDTO();
			cityDTO.setCityId(id);
			cityInputMessage.setCityDTO(cityDTO);
			
			if ("R".equals(opr)) {
				cityOutputMessage = cityService.checkBeforeRemove(cityInputMessage);
				if (cityOutputMessage != null) {
					ErrorListDTO errorListDTO = cityOutputMessage
							.getErrorListDTO();
					if (errorListDTO != null && errorListDTO.hasErrors()) {
						ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
						model.addAttribute("errors", errorDTO);
						ModelAndView mav = new ModelAndView("forward:get_city_list");
						return mav;
					}
				}
			}
			
			cityOutputMessage = cityService.findCityById(cityInputMessage);
			ArrayList<CityDTO> list = (ArrayList<CityDTO>) cityOutputMessage
					.getCityDTOList();
			if (list.size() > 0) {
				cityForm = new CityForm();
				cityForm.setCityDTO(list.get(0));
			}
		}

		ModelAndView mav = new ModelAndView("city-edit");
		mav.addObject("cityForm", cityForm);
		mav.addObject("opr", opr);
		logger.info(cityForm.getCityDTO().toString());
		return mav;
	}

	@RequestMapping(value = "/update_city", method = RequestMethod.POST)
	public String updateArea(@ModelAttribute("cityForm") CityForm cityForm,Model model) {
		logger.info("cityForm = " + cityForm);
		logger.info(cityForm.getCityDTO().toString());
		CityInputMessage cityInputMessage = new CityInputMessage();
		CityDTO cityDTO=cityForm.getCityDTO();
		cityDTO.setModifiedUserId(getCreatedUserId());
		cityInputMessage.setCityDTO(cityDTO);
		CityOutputMessage cityOutputMessage = cityService.updateCity(cityInputMessage);
		ErrorListDTO errorListDTO = cityOutputMessage.getErrorListDTO();

		if (errorListDTO != null && errorListDTO.hasErrors()) {
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errors", errorDTO);
			model.addAttribute("opr", "E");
			return "city-edit";
		}
		String succ="Up";
		model.addAttribute("succ", succ);
		return "redirect:/get_city_list";

	}

	@RequestMapping(value = "/remove_city", method = RequestMethod.GET)
	public String removeArea(@RequestParam("cityId") String cityId,Model model,@ModelAttribute("cityForm") CityForm cityForm) {
		if (StringUtils.hasText(cityId)) {
			int id = NumberUtils.parseNumber(cityId, Integer.class);
			CityInputMessage cityInputMessage = new CityInputMessage();
			CityDTO cityDTO = new CityDTO();
			cityDTO.setCityId(id);
			cityDTO.setModifiedUserId(getCreatedUserId());
			cityInputMessage.setCityDTO(cityDTO);
			//cityService.deleteCity(cityInputMessage);
			CityOutputMessage cityOutputMessage=new CityOutputMessage();		
			
			
			cityOutputMessage = cityService.checkBeforeRemove(cityInputMessage);
	if (cityOutputMessage != null) {
		ErrorListDTO errorListDTO = cityOutputMessage
				.getErrorListDTO();
		if (errorListDTO != null && errorListDTO.hasErrors()) {
			model.addAttribute("errors", errorListDTO);
			model.addAttribute("opr", "R");
			model.addAttribute("cityForm", cityForm);
			return "city-edit";
		}
	}
	cityOutputMessage = cityService.deleteCity(cityInputMessage);;

		}
		String succ="Dl";
		model.addAttribute("succ", succ);
		return "redirect:/get_city_list";

	}

	// for edit and remove
	@RequestMapping(value = "/get_csc_by_id", method = RequestMethod.GET)
	public @ResponseBody
	CityStateCountryDTO getCityStateCountryById(@RequestParam("id") String cityId) {
		logger.info("Get city: " + cityId);
		CityStateCountryDTO cityStateCountryDTO = null;
		CityDTO cityDTO = null;
		CityOutputMessage cityOutputMessage = null;
		if (StringUtils.hasText(cityId)) {
			logger.info("id=="+cityId);
			int id = NumberUtils.parseNumber(cityId, Integer.class);
			CityInputMessage cityInputMessage = new CityInputMessage();
			cityDTO = new CityDTO();
			cityDTO.setCityId(id);
			cityInputMessage.setCityDTO(cityDTO);
			cityOutputMessage = cityService.findCityStateCountryByCityId(cityInputMessage);
			cityStateCountryDTO=cityOutputMessage.getCityStateCountryDTO();		
			logger.info("City Info : "+cityStateCountryDTO);
		}
		logger.info("City Info : "+cityStateCountryDTO);
		return cityStateCountryDTO;
	}

	@ModelAttribute("areas")
	public @ResponseBody
	List<AreaDTO> getAreas(@ModelAttribute("rows") ArrayList<AreaDTO> list) {

		AreaOutputMessage areaOutputMessage = areaService.findAllAreas();
		list = (ArrayList<AreaDTO>) areaOutputMessage.getAreaDTOList();
		logger.debug("areas list -- :-" + list);
		return list;

	}

}
