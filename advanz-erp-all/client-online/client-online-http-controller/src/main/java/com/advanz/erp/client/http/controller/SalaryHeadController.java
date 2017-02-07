package com.advanz.erp.client.http.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import com.advanz.erp.client.http.controller.form.SalaryHeadForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.SalaryHeadDTO;
import com.advanz.erp.masters.model.criteria.SalaryHeadSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.SalaryHeadInputMessage;
import com.advanz.erp.masters.model.msg.SalaryHeadOutputMessage;
import com.advanz.erp.masters.service.business.ISalaryHeadService;
import com.advanz.erp.masters.service.business.IRegionService;

@Controller
public class SalaryHeadController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(SalaryHeadController.class);

	@Autowired
	public ISalaryHeadService salaryHeadService;

	@Autowired
	public IRegionService regionService;

	@RequestMapping(value = "/get_salaryHead_list")
	public ModelAndView searchSalaryHead(@ModelAttribute("salaryHeadSearchCriteria") SalaryHeadSearchCriteriaDTO searchCriteria,@ModelAttribute("salaryHeadForm") SalaryHeadForm salaryHeadForm,Model model,@RequestParam(value="menuId",required=false) String menuId,HttpSession session) {
		if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
		List<SalaryHeadDTO> list = new ArrayList<SalaryHeadDTO>();
		SalaryHeadOutputMessage salaryHeadOutputMessage = null;
		SalaryHeadInputMessage salaryHeadInputMessage = new SalaryHeadInputMessage();
		salaryHeadInputMessage.setSearchCriteria(searchCriteria);
		salaryHeadOutputMessage = salaryHeadService.search(salaryHeadInputMessage);
		list = (ArrayList<SalaryHeadDTO>) salaryHeadOutputMessage.getSalaryHeadDTOList();
		logger.debug(" ff -----list------- :-" + list);
		String succ="Blk";
		if(list.equals(null) || list.size()==0)
		{
		  model.addAttribute("succ", succ);
		}
		// logger.debug("----salary head name list------- :-"+list.get(0).getBaseHeads());
		ModelAndView mav = new ModelAndView("salaryHead-list");
		mav.addObject("salaryHeadList", list);
		
		mav.addObject("salaryHeadSearchCriteria", searchCriteria);
		mav.addObject("salaryHeadForm", salaryHeadForm);
		
		return mav;
	}

	@RequestMapping(value = "/show_new_salary_Head_form", method = RequestMethod.GET)
	public ModelAndView showForm(
			@ModelAttribute("salaryHeadForm") SalaryHeadForm salaryHeadForm) {
		ModelAndView mav = new ModelAndView("salaryHead-detail");
		if (salaryHeadForm == null) {
			salaryHeadForm = new SalaryHeadForm();
		}
		SalaryHeadSearchCriteriaDTO searchCriteria = new SalaryHeadSearchCriteriaDTO();
		SalaryHeadOutputMessage salaryHeadOutputMessage = null;
		SalaryHeadInputMessage salaryHeadInputMessage = new SalaryHeadInputMessage();
		salaryHeadInputMessage.setSearchCriteria(searchCriteria);
		salaryHeadOutputMessage = salaryHeadService.search(salaryHeadInputMessage);
		List<SalaryHeadDTO> list = (ArrayList<SalaryHeadDTO>) salaryHeadOutputMessage.getSalaryHeadDTOList();
		logger.debug(" list------- :-" + list);
		// logger.debug("----salary head name list------- :-"+list.get(0).getBaseHeads().get(0).getSalaryHeadName());
		mav.addObject("salaryHeadList", list);
		mav.addObject("salaryHeadForm", salaryHeadForm);
		return mav;
	}

	@RequestMapping(value = "/save_salary_Head", method = RequestMethod.POST)
	public String saveSalaryHead(@ModelAttribute("salaryHeadForm") SalaryHeadForm salaryHeadForm,
			Model model) {
		logger.info("salaryHeadForm = ddd " + salaryHeadForm.getSalaryHeadDTO());
		logger.info("Array -->"	+ salaryHeadForm.getSalaryHeadDTO().getBaseHeadIds());
		List<Integer> baseHeadsIs = salaryHeadForm.getSalaryHeadDTO().getBaseHeadIds();
		if (baseHeadsIs != null) {
			List<SalaryHeadDTO> baseHeads = new ArrayList<SalaryHeadDTO>();
			for (Integer s : baseHeadsIs) {
				SalaryHeadDTO bh = new SalaryHeadDTO();
				bh.setSalaryHeadId(s);
				baseHeads.add(bh);
			}
			salaryHeadForm.getSalaryHeadDTO().setBaseHeads(baseHeads);
		}
		SalaryHeadInputMessage salaryHeadInputMessage = new SalaryHeadInputMessage();
		SalaryHeadDTO salaryHeadDTO=salaryHeadForm.getSalaryHeadDTO();
		salaryHeadDTO.setCreatedUserId(getCreatedUserId());
		salaryHeadInputMessage.setSalaryHeadDTO(salaryHeadDTO);
		logger.debug("salaryHeadInputMessage controolluh---"+ salaryHeadInputMessage.getSalaryHeadDTO());
		SalaryHeadOutputMessage salaryHeadOutputMessage = salaryHeadService	.createSalaryHead(salaryHeadInputMessage);
		ErrorListDTO errorListDTO = salaryHeadOutputMessage.getErrorListDTO();

		if (errorListDTO != null && errorListDTO.hasErrors()) {
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errors",errorDTO);

			List<SalaryHeadDTO> list = new ArrayList<SalaryHeadDTO>();
			SalaryHeadSearchCriteriaDTO searchCriteria = new SalaryHeadSearchCriteriaDTO();
			SalaryHeadOutputMessage salaryHeadOutputMessage1 = null;
			SalaryHeadInputMessage salaryHeadInputMessage1 = new SalaryHeadInputMessage();
			salaryHeadInputMessage1.setSearchCriteria(searchCriteria);
			salaryHeadOutputMessage1 = salaryHeadService.findAllSalaryHeads();
			list = (ArrayList<SalaryHeadDTO>) salaryHeadOutputMessage1.getSalaryHeadDTOList();
			logger.debug("---jjjj list------- :-" + list);
			model.addAttribute("salaryHeadList", list);
			model.addAttribute("salaryHeadForm", salaryHeadForm);
			return "salaryHead-detail";
		}
		String succ="Ad";
		model.addAttribute("succ", succ);
		return "redirect:/get_salaryHead_list";

	}

	// for edit and remove
	@RequestMapping(value = "/get_salaryHead", method = RequestMethod.GET)
	public ModelAndView getSalaryHeadData(@RequestParam("salaryHeadId") String salaryHeadId,@RequestParam("opr") String opr, Model model) {
		logger.info("Get SalaryHead : " + salaryHeadId);
		logger.info("Opr : " + opr);
		SalaryHeadForm salaryHeadForm = null;
		ArrayList<SalaryHeadDTO> list = null;
		ModelAndView mav = new ModelAndView("salaryHead-edit");
		SalaryHeadOutputMessage salaryHeadOutputMessage = null;
		if (StringUtils.hasText(salaryHeadId)) {
			int id = NumberUtils.parseNumber(salaryHeadId, Integer.class);
			SalaryHeadInputMessage salaryHeadInputMessage = new SalaryHeadInputMessage();
			SalaryHeadDTO salaryHeadDTO = new SalaryHeadDTO();
			salaryHeadDTO.setSalaryHeadId(id);
			salaryHeadInputMessage.setSalaryHeadDTO(salaryHeadDTO);
			salaryHeadOutputMessage = salaryHeadService.findSalaryHeadById(salaryHeadInputMessage);
			list = (ArrayList<SalaryHeadDTO>) salaryHeadOutputMessage.getSalaryHeadDTOList();
			SalaryHeadDTO salaryHeadDTONew = null;
			List<SalaryHeadDTO> list3 = null;
			List<SalaryHeadDTO> list4 = new ArrayList<SalaryHeadDTO>();
			if (list.size() > 0) {
				salaryHeadForm = new SalaryHeadForm();
				salaryHeadDTONew = list.get(0);
				list3 = salaryHeadDTONew.getBaseHeads();

			}
			SalaryHeadSearchCriteriaDTO searchCriteria = new SalaryHeadSearchCriteriaDTO();
			searchCriteria.setCurrentSalaryHeadId(id);
			SalaryHeadOutputMessage salaryHeadOutputMessage1 = null;
			SalaryHeadInputMessage salaryHeadInputMessage1 = new SalaryHeadInputMessage();
			salaryHeadInputMessage1.setSearchCriteria(searchCriteria);
			salaryHeadOutputMessage1 = salaryHeadService.search(salaryHeadInputMessage1);
			ArrayList<SalaryHeadDTO> list1 = (ArrayList<SalaryHeadDTO>) salaryHeadOutputMessage1.getSalaryHeadDTOList();
			if (list1 != null) {
				for (SalaryHeadDTO e : list1) {
					boolean flag = false;
					int id1 = e.getSalaryHeadId();
					for (SalaryHeadDTO e1 : list3) {
						if (id1 == e1.getSalaryHeadId()) {
							System.out.println("enter == "+ id1);
							list4.add(e1);
							flag = true;
							break;
						}
					}
					if (!flag) {
						list4.add(new SalaryHeadDTO());
					}
				}
			}
			salaryHeadDTONew.setBaseHeads(list4);
			salaryHeadForm.setSalaryHeadDTO(salaryHeadDTONew);
			mav.addObject("salaryHeadList", list1);
		}
		mav.addObject("salaryHeadForm", salaryHeadForm);
		mav.addObject("opr", opr);
		// logger.info(salaryHeadForm.getSalaryHeadDTO().toString());
		return mav;
	}

	@RequestMapping(value = "/update_salaryHead", method = RequestMethod.POST)
	public String updateSalaryHead(@ModelAttribute("salaryHeadForm") SalaryHeadForm salaryHeadForm,Model model) {
		logger.info("salaryHeadForm = " + salaryHeadForm);
		logger.info("Array -->"	+ salaryHeadForm.getSalaryHeadDTO().getBaseHeadIds());
		List<SalaryHeadDTO> baseHeadsIs = salaryHeadForm.getSalaryHeadDTO().getBaseHeads();
		if (baseHeadsIs != null) {
			List<SalaryHeadDTO> baseHeads = new ArrayList<SalaryHeadDTO>();
			for (SalaryHeadDTO s : baseHeadsIs) {
				System.out.println("base Head ID = " + s.getSalaryHeadId());
				if (s.getSalaryHeadId() != null) {
					baseHeads.add(s);
				}
			}
			salaryHeadForm.getSalaryHeadDTO().setBaseHeads(baseHeads);
		}
		logger.info(salaryHeadForm.getSalaryHeadDTO().toString());
		SalaryHeadInputMessage salaryHeadInputMessage = new SalaryHeadInputMessage();
		SalaryHeadDTO salaryHeadDTO=salaryHeadForm.getSalaryHeadDTO();
		salaryHeadDTO.setModifiedUserId(getCreatedUserId());
		salaryHeadInputMessage.setSalaryHeadDTO(salaryHeadDTO);
		SalaryHeadOutputMessage salaryHeadOutputMessage = salaryHeadService.updateSalaryHead(salaryHeadInputMessage);
		ErrorListDTO errorListDTO = salaryHeadOutputMessage.getErrorListDTO();

		if (errorListDTO != null && errorListDTO.hasErrors()) {
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errors",errorDTO);
			model.addAttribute("opr", "E");
			return "salaryHead-edit";
		}
		String succ="Up";
		model.addAttribute("succ", succ);
		return "redirect:/get_salaryHead_list";

	}

	@RequestMapping(value = "/remove_salaryHead", method = RequestMethod.GET)
	public String removeSalaryHead(@RequestParam("salaryHeadId") String salaryHeadId,Model model,@ModelAttribute("salaryHeadForm") SalaryHeadForm salaryHeadForm) {
		logger.info("==================salaryHeadId = " + salaryHeadId);
		if (StringUtils.hasText(salaryHeadId)) {
			int id = NumberUtils.parseNumber(salaryHeadId, Integer.class);
			SalaryHeadInputMessage salaryHeadInputMessage = new SalaryHeadInputMessage();
			SalaryHeadDTO salaryHeadDTO = new SalaryHeadDTO();
			salaryHeadDTO.setSalaryHeadId(id);
			salaryHeadDTO.setModifiedUserId(getCreatedUserId());
			salaryHeadInputMessage.setSalaryHeadDTO(salaryHeadDTO);
			salaryHeadService.deleteSalaryHead(salaryHeadInputMessage);
		}
		String succ="Dl";
		model.addAttribute("succ", succ);
		return "redirect:/get_salaryHead_list";

	}

	@ModelAttribute("regiones")
	public @ResponseBody List<SalaryHeadDTO> getStates(@ModelAttribute("rows") ArrayList<SalaryHeadDTO> list) {
		return list;

	}

	
	@RequestMapping(value = "/getHeadSequenceNo", method = RequestMethod.POST)
	public @ResponseBody JsonResponse addUser( @RequestParam("headSequence") String headType) {
		JsonResponse res = new JsonResponse();
		SalaryHeadInputMessage salaryHeadInputMessage = new SalaryHeadInputMessage();
		Integer i = salaryHeadService.findSalaryHeadByType(headType);
		
		res.setResult(i);
		res.setStatus(null);
		return res;
	}
	
	@RequestMapping(value = "/checkFlagIsAvlaible", method = RequestMethod.POST)
	public @ResponseBody JsonResponse checkFlagIsAvlaible( @RequestParam("flagTYpe") String flagTYpe) {
		JsonResponse res = new JsonResponse();
		SalaryHeadOutputMessage outputMessage = salaryHeadService.findAllSalaryHeads();
	   List<SalaryHeadDTO> list=outputMessage.getSalaryHeadDTOList();
	Boolean flag=true;
	if(flagTYpe.equalsIgnoreCase("PfEmployeeFlag")){
	   for(int i=0;i<list.size();i++){
		SalaryHeadDTO dto=list.get(i);
			if(dto.getPfEmployeeFlag()!=null && dto.getPfEmployeeFlag()==1){
				flag=false;
			}}
	}else if(flagTYpe.equalsIgnoreCase("BasicSalaryFlag")){
		   for(int i=0;i<list.size();i++){
				SalaryHeadDTO dto=list.get(i);
					if(dto.getBasicSalaryFlag()!=null && dto.getBasicSalaryFlag()==1){
						flag=false;
					}}
			}else if(flagTYpe.equalsIgnoreCase("ProfessionalTaxFlag")){
				   for(int i=0;i<list.size();i++){
						SalaryHeadDTO dto=list.get(i);
							if(dto.getProfessionalTaxFlag()!=null && dto.getProfessionalTaxFlag()==1){
								flag=false;
							}}
					}
			else if(flagTYpe.equalsIgnoreCase("EsiEmployeeFlag")){
				   for(int i=0;i<list.size();i++){
						SalaryHeadDTO dto=list.get(i);
							if(dto.getEsiEmployeeFlag()!=null && dto.getEsiEmployeeFlag()==1){
								flag=false;
							}}
					}
	
	
	
	   res.setResult(flag);
		//res.setStatus(flag);
		return res;
	    }
	
	
}
