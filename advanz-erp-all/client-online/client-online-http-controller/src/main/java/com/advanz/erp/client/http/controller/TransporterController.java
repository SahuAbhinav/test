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

import com.advanz.erp.client.http.controller.form.TransporterForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.CityDTO;
import com.advanz.erp.masters.model.TransporterDTO;
import com.advanz.erp.masters.model.criteria.TransporterSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.CityOutputMessage;
import com.advanz.erp.masters.model.msg.TransporterInputMessage;
import com.advanz.erp.masters.model.msg.TransporterOutMessage;
import com.advanz.erp.masters.service.business.ICityService;
import com.advanz.erp.masters.service.business.ITransporterService;

@Controller
public class TransporterController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(TransporterController.class);

	@Autowired
	public ITransporterService transporterService;
	@Autowired
    public ICityService cityService;
	

	@RequestMapping(value = "/show_transporter_form", method = RequestMethod.GET)
	public ModelAndView showForm(@ModelAttribute("transporterForm")TransporterForm transporterForm,@RequestParam(value="menuId",required=false) String menuId,HttpSession session) {
		if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
	
		ModelAndView mav = new ModelAndView("transporter_list");
		//TransporterOutMessage transporterOutMessage = transporterService.findAllTransporters();
		TransporterInputMessage transporterInputMessage = new TransporterInputMessage();
		TransporterDTO transporterDto = new TransporterDTO();		
		transporterInputMessage.setTransporterDTO(transporterDto);
		TransporterOutMessage transporterOutMessage =transporterService.findTransporter(transporterInputMessage);
		ArrayList<TransporterDTO> list = (ArrayList<TransporterDTO>) transporterOutMessage.getTransporterDTOList();
		mav.addObject("transporterList", list);
		TransporterSearchCriteriaDTO seacrhCriteria=new TransporterSearchCriteriaDTO();
		mav.addObject("searchCriteria",seacrhCriteria);
		logger.info("transporter List : " + list);
		return mav;
	}

	@RequestMapping(value = "/show_new_transporter_form", method = RequestMethod.GET)
	public ModelAndView showNewTransporterForm(@ModelAttribute("transporterForm") TransporterForm transporterForm) {
		ModelAndView mav = new ModelAndView("transporter_detail");
		if (transporterForm == null) {
			transporterForm = new TransporterForm();
		}
		mav.addObject("transporterForm", transporterForm);
		return mav;
	}

	@RequestMapping(value = "/save_transporter", method = RequestMethod.POST)
	public String saveTransporter(@ModelAttribute("transporterForm") final TransporterForm transporterForm,Model model,@ModelAttribute("opr")String opr) {
		
		TransporterInputMessage transporterInputMessage = new TransporterInputMessage();
		TransporterDTO transporterDTO=	transporterForm.getTransporterDTO();
		
		transporterInputMessage.setTransporterDTO(transporterDTO);
		Integer transporterId=transporterForm.getTransporterDTO().getTransporterId();
		logger.info("------Tr ID="+transporterForm.getTransporterDTO().getTransporterId());
		logger.info("------opr="+opr);
		 String succ="";
		TransporterOutMessage transporterOutMessage;
		if(transporterId!=null && transporterId.intValue()!=0){
			transporterDTO.setModifiedUserId(getCreatedUserId());
			 transporterOutMessage = transporterService.updateTransporter(transporterInputMessage);		
			 succ="Up";
		}else{	
			transporterDTO.setCreatedUserId(getCreatedUserId());
			 transporterOutMessage = transporterService.createTransporter(transporterInputMessage);
			succ="Ad";
		}		

		ErrorListDTO errorListDTO = transporterOutMessage.getErrorListDTO();

		if (errorListDTO != null && errorListDTO.hasErrors()) {
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errors",errorDTO);
			model.addAttribute("opr",opr);
			return "transporter_detail";
		}
		model.addAttribute("succ", succ);
		return "redirect:/show_transporter_form";
	}

	@RequestMapping(value = "/get_transporter_data", method = RequestMethod.POST)
	public ModelAndView getTransporterData(@ModelAttribute("searchCriteria") TransporterSearchCriteriaDTO searchCriteria,Model model,@ModelAttribute("transporterForm") TransporterForm transporterForm) {
		TransporterOutMessage transporterOutMessage;
			TransporterInputMessage transporterInputMessage = new TransporterInputMessage();
			TransporterDTO transporterDto = new TransporterDTO();
			transporterDto.setTransName(searchCriteria.getName());
			transporterDto.setTransCode(searchCriteria.getCode());
			transporterDto.setTransAddress(searchCriteria.getAddress());
			transporterInputMessage.setTransporterDTO(transporterDto);
			transporterOutMessage = transporterService.findTransporter(transporterInputMessage);
		
		ArrayList<TransporterDTO> list = (ArrayList<TransporterDTO>) transporterOutMessage.getTransporterDTOList();
		ModelAndView mav = new ModelAndView("transporter_list");
		mav.addObject("transporterList", list);
		logger.info("transporter List : " + list);
		String succ="Blk";
		if(list.equals(null) || list.size()==0)
		{
		  model.addAttribute("succ", succ);
		}
		return mav;
	}

	@RequestMapping(value = "/remove_transporter", method = RequestMethod.GET)
	public String deleteTransporter(@RequestParam("id") String transporterId,Model model) {
		if (StringUtils.hasText(transporterId)) {
			int id = NumberUtils.parseNumber(transporterId, Integer.class);
			TransporterInputMessage transporterInputMessage = new TransporterInputMessage();
			TransporterDTO transporterDto = new TransporterDTO();
			transporterDto.setTransporterId(id);
			transporterDto.setModifiedUserId(getCreatedUserId());
			transporterInputMessage.setTransporterDTO(transporterDto);
			transporterService.deleteTransporter(transporterInputMessage);
		}
		String succ="Dl";
		model.addAttribute("succ", succ);
		return "redirect:/show_transporter_form";
	}

	@RequestMapping(value = "/get_transporter", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getTransporter(@RequestParam("transporterId") String transporterId,@RequestParam("opr") String opr) {
		logger.info("Get Transporter : " + transporterId);
		logger.info("Opr : " + opr);
		
	//	ModelAndView mav = new ModelAndView("transporter_detail_edit");
		ModelAndView mav = new ModelAndView("transporter_detail");
		TransporterForm transporterForm = new TransporterForm();		
		if (StringUtils.hasText(transporterId)) {
			int id = NumberUtils.parseNumber(transporterId, Integer.class);			
			TransporterInputMessage transporterInputMessage = new TransporterInputMessage();
			TransporterDTO transporterDto = new TransporterDTO();
			transporterDto.setTransporterId(id);
			transporterInputMessage.setTransporterDTO(transporterDto);
			
			if("R".equals(opr)){
				logger.info("RRRRRRRRRRRRRRR");
				TransporterOutMessage transporterOutMessage = transporterService.checkBeforeRemove(transporterInputMessage);
				if(transporterOutMessage!=null){
				ErrorListDTO errorListDTO=transporterOutMessage.getErrorListDTO();
				if (errorListDTO != null && errorListDTO.hasErrors()) {		
					ErrorDTO  errorDTO=errorListDTO.getErrorList().get(0);
					ModelAndView mvw = new ModelAndView("forward:show_transporter_form");
					mvw.addObject("errors", errorDTO);					
					return mvw;
				}
				}
			}
			
			TransporterOutMessage transporterOutMessage = transporterService.findTransporterById(transporterInputMessage);
			ArrayList<TransporterDTO> list = (ArrayList<TransporterDTO>) transporterOutMessage.getTransporterDTOList();		
			if (list.size() >0) {
				transporterForm.setTransporterDTO(list.get(0));				
			}
		}		
		mav.addObject("transporterForm", transporterForm);
		mav.addObject("opr",opr);
		return mav;
	}
	
	@ModelAttribute("cityList")
	public List<CityDTO> cityList() {
		List<CityDTO> items = new ArrayList<CityDTO>();
		CityOutputMessage cityOutMessage = cityService.findAllCityes();
	
		return cityOutMessage.getCityDTOList();
	}

}
