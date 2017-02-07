package com.advanz.erp.client.http.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.MastersForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.criteria.MastersSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.service.business.IMastersService;

@Controller
public class MastersController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(MastersController.class);

	@Autowired
	public IMastersService mastersService;

	@RequestMapping(value = "/get_masters_list")
	public ModelAndView searchMasters(@ModelAttribute("mastersSearchCriteria") MastersSearchCriteriaDTO searchCriteria,@ModelAttribute("mastersForm") MastersForm mastersForm,Model model,@RequestParam(value="menuId",required=false) String menuId,HttpSession session) {
		if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
		List<MastersDTO> list = new ArrayList<MastersDTO>();
		String succ="";
		MastersOutputMessage mastersOutputMessage = null;
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		mastersInputMessage.setSearchCriteria(searchCriteria);
		mastersOutputMessage = mastersService.searchMasters(mastersInputMessage);
		list = (ArrayList<MastersDTO>) mastersOutputMessage.getMastersDTOList();
		ModelAndView mav = new ModelAndView("masters-list");
		mav.addObject("mastersList", list);
		mav.addObject("mastersSearchCriteria", searchCriteria);
		
		// Employee Type
		mastersInputMessage.setFormId(5);
		mastersOutputMessage = mastersService.findMapForIdName(mastersInputMessage);
		mav.addObject("empTypes", mastersOutputMessage.getMastersIdNameMap());
		succ="Blk";
		logger.info("Loading new MasterForm..................."+list);
		
		if(list.equals(null) || list.size()==0)
		{
		  model.addAttribute("succ", succ);
		}
		mav.addObject("mastersForm",mastersForm);
		return mav;
	}

	@RequestMapping(value = "/show_new_masters_form", method = RequestMethod.GET)
	public ModelAndView showNewBatchForm(@ModelAttribute("mastersForm") MastersForm mastersForm) {
		logger.info("Loading new MasterForm...................");
		ModelAndView mav = new ModelAndView("masters-detail");
		if (mastersForm == null)
			mastersForm = new MastersForm();
		mav.addObject("mastersForm", mastersForm);
		
		return mav;
	}

	@RequestMapping(value = "/save_masters", method = RequestMethod.POST)
	public String saveMasters(@ModelAttribute("mastersForm") MastersForm mastersForm,
			@ModelAttribute("tabName") String tabName, Model model) {
		logger.info("Tab Name = " + tabName);
		logger.info("mastersForm = " + mastersForm);
		logger.info(mastersForm.getMastersDTO().toString());
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		MastersDTO mastersDTO= mastersForm.getMastersDTO();
		mastersDTO.setCreatedUserId(getCreatedUserId());
		mastersInputMessage.setMastersDTO(mastersDTO);
		MastersOutputMessage mastersOutputMessage = mastersService.createMasters(mastersInputMessage);
		ErrorListDTO errorListDTO = mastersOutputMessage.getErrorListDTO();

		if (errorListDTO != null && errorListDTO.hasErrors()) {
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errors",errorDTO);
			return "masters-detail";
		}
		String succ="Ad";
		model.addAttribute("succ", succ);
		return "redirect:/get_masters_list";
	}

	@RequestMapping(value = "/get_masters", method = RequestMethod.GET)
	public ModelAndView getMastersData(@RequestParam("mid") String mid,	@RequestParam("fid") String fid, @RequestParam("opr") String opr,@ModelAttribute("tv")String tabVal) {
		logger.info("Opr : " + opr);
		logger.info("Mid : " + mid);
		logger.info("Fid : " + fid);
		logger.info("Tab : " + tabVal);
		Integer id = null;
		Integer formId = null;
		try {
			id = Integer.parseInt(mid);
			formId = Integer.parseInt(fid);
		} catch (Exception ex) {
			logger.debug("Error " + ex);
		}
		MastersForm mastersForm = null;
		MastersOutputMessage mastersOutputMessage = null;
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		MastersDTO mastersDTO = new MastersDTO();
		mastersDTO.setMastersId(id);		
	//	mastersDTO.setId(id);
	//	mastersDTO.setFormId(formId);
		mastersInputMessage.setMastersDTO(mastersDTO);
		if("R".equals(opr)){
			mastersOutputMessage = mastersService.checkBeforeRemove(mastersInputMessage);
			if(mastersOutputMessage!=null){
				ErrorListDTO errorListDTO=mastersOutputMessage.getErrorListDTO();
				if (errorListDTO != null && errorListDTO.hasErrors()) {
					ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
					ModelAndView mav = new ModelAndView("forward:get_masters_list");
					mav.addObject("errors", errorDTO);
					return mav;
				}
			}
		}
		mastersOutputMessage = mastersService.findMastersById(mastersInputMessage);
		ArrayList<MastersDTO> list = (ArrayList<MastersDTO>) mastersOutputMessage.getMastersDTOList();
		if (list.size() > 0) {
			mastersForm = new MastersForm();
			mastersForm.setMastersDTO(list.get(0));
		}

		ModelAndView mav = new ModelAndView("masters-edit");
		mav.addObject("mastersForm", mastersForm);
		mav.addObject("opr", opr);
	//	logger.info(mastersForm.getMastersDTO().toString());
		return mav;
	}

	@RequestMapping(value = "/remove_masters", method = RequestMethod.GET)
	public String removeMasters(@RequestParam("mid") String mid,@RequestParam("fid") String fid) 
	{
		logger.info("Removing..........mastersNo = [" + mid + "," + fid + "]");

		Integer id = null;
		Integer formId = null;
		try {
			id = Integer.parseInt(mid);
	//		formId = Integer.parseInt(fid);
		} catch (Exception ex) {
			logger.debug("Error " + ex);
		}
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		MastersDTO mastersDTO = new MastersDTO();
		mastersDTO.setMastersId(id);
		mastersDTO.setModifiedUserId(getCreatedUserId());
	//	mastersDTO.setId(id);
//		mastersDTO.setFormId(formId);
		mastersInputMessage.setMastersDTO(mastersDTO);
		MastersOutputMessage mastersOutputMessage = mastersService.deleteMasters(mastersInputMessage);
		return "redirect:/get_masters_list";
	}

	@RequestMapping(value = "/update_masters", method = RequestMethod.POST)
	public String updateMasters(
			@ModelAttribute("mastersForm") MastersForm mastersForm,@RequestParam("tv")String tab, Model model) {
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		MastersDTO mastersDTO= mastersForm.getMastersDTO();
		mastersDTO.setModifiedUserId(getCreatedUserId());
		mastersInputMessage.setMastersDTO(mastersDTO);
		MastersOutputMessage mastersOutputMessage = mastersService.updateMasters(mastersInputMessage);
		ErrorListDTO errorListDTO = mastersOutputMessage.getErrorListDTO();

		if (errorListDTO != null && errorListDTO.hasErrors()) {
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errorList",	errorDTO);
			model.addAttribute("opr", "E");
			model.addAttribute("tv", tab);
			return "masters-edit";
		}
		String succ="";
		succ="Up";
		 model.addAttribute("succ",succ);
		return "redirect:/get_masters_list";
	}

	
	@RequestMapping(value = "/get_shift_time_masterId", method = RequestMethod.GET)
	public @ResponseBody MastersDTO findShiftTimeByMasterId(@RequestParam("id") Integer masterId)
	{
		
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		mastersInputMessage.setFormId(11);
		MastersDTO dto=new MastersDTO();
		dto.setMastersId(masterId);
		mastersInputMessage.setMastersDTO(dto);
		MastersOutputMessage mastersOutputMessage = mastersService.findMastersByIdAndFormId(mastersInputMessage);
		MastersDTO mastersDTO=mastersOutputMessage.getMastersDTOList().get(0);
		return mastersDTO;
	}
	
	
	@ModelAttribute("empTypeList")
	public List<MastersDTO> getEmployeeTypeList() {
		MastersOutputMessage outMsg = null;
		MastersInputMessage inMsg = new MastersInputMessage();
		// Employee Type
		inMsg.setFormId(5);
		outMsg = mastersService.findFormById(inMsg);
		List<MastersDTO> empTypeList = outMsg.getMastersDTOList();

		return empTypeList;
	}
	@ModelAttribute("deptTypeList")
	public List<MastersDTO> getDeptTypeList() {		
		MastersOutputMessage outMsg = null;
		MastersInputMessage inMsg = new MastersInputMessage();
		inMsg.setFormId(8);
		outMsg = mastersService.findFormById(inMsg);
		List<MastersDTO> deptTypeList = outMsg.getMastersDTOList();
		return deptTypeList;
	}
}
