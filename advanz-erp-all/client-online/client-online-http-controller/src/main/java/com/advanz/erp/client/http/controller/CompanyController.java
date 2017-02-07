package com.advanz.erp.client.http.controller;

import java.util.ArrayList;
import java.util.Date;

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

import com.advanz.erp.client.http.controller.form.CompanyForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.CompanyDTO;
import com.advanz.erp.masters.model.criteria.CompanySearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.CompanyInputMessage;
import com.advanz.erp.masters.model.msg.CompanyOutMessage;
import com.advanz.erp.masters.service.business.ICompanyService;

@Controller
public class CompanyController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	public ICompanyService companyService;

	@RequestMapping(value = "/show_company_form", method = RequestMethod.GET)
	public ModelAndView showForm(@ModelAttribute("companyForm") CompanyForm companyForm,@RequestParam(value="menuId",required=false) String menuId,HttpSession session) {
		if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
		ModelAndView mav = new ModelAndView("company_list");
		CompanySearchCriteriaDTO searchCriteria = new CompanySearchCriteriaDTO();
		mav.addObject("searchCriteria", searchCriteria);
		CompanyOutMessage companyOutMessage = companyService.findAllCompanies();
		ArrayList<CompanyDTO> list = (ArrayList<CompanyDTO>) companyOutMessage
				.getCompanyDTOList();
		mav.addObject("companyList", list);
		logger.info("company List : " + list);
		
		return mav;
	}

	@RequestMapping(value = "/show_new_form", method = RequestMethod.GET)
	public ModelAndView showNewCompanyForm(
			@ModelAttribute("companyForm") CompanyForm companyForm) {
		ModelAndView mav = new ModelAndView("company_detail");
		if (companyForm == null) {
			companyForm = new CompanyForm();
		}
		mav.addObject("companyForm", companyForm);
		return mav;
	}

	@RequestMapping(value = "/save_company", method = RequestMethod.POST)
	public String saveCompany(
			@ModelAttribute("companyForm") final CompanyForm companyForm,
			Model model) {

		ErrorListDTO errorListDTO = null;
		CompanyDTO companyDTO = companyForm.getCompanyDto();
		Date finYearFrom = companyDTO.getFinancialYrBeg();
		Date finYearTo = companyDTO.getFinancialYrEnd();
		if (finYearFrom != null && finYearTo != null) {
			if (finYearTo.before(finYearFrom)) {
				errorListDTO = new ErrorListDTO();
				ErrorDTO error = new ErrorDTO("1",
						" Fin. Year From cannot be greater than  Fin. Year To");
				errorListDTO.addError(error);
			}
		}
		if (errorListDTO == null) {
			CompanyInputMessage companyInputMessage = new CompanyInputMessage();
			companyInputMessage.setCompanyDTO(companyDTO);
			CompanyOutMessage companyOutMessage = companyService
					.createCompany(companyInputMessage);

			if (companyOutMessage != null) {
				errorListDTO = companyOutMessage.getErrorListDTO();
			}
		}
		if (errorListDTO != null && errorListDTO.hasErrors()) {
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errors", errorDTO);
			return "company_detail";
		}
		String succ="Ad";
		model.addAttribute("succ", succ);
		return "redirect:/show_company_form";
	}

	@RequestMapping(value = "/get_company_data", method = RequestMethod.POST)
	public ModelAndView getCompanyData(
			@ModelAttribute("searchCriteria") CompanySearchCriteriaDTO searchCriteria,Model model) {

		CompanyOutMessage companyOutMessage = null;

		CompanyInputMessage companyInputMessage = new CompanyInputMessage();
		CompanyDTO companyDto = new CompanyDTO();
		companyDto.setCompanyName(searchCriteria.getName());
		companyDto.setCompanyCity(searchCriteria.getCity());
		companyDto.setCompanyCode(searchCriteria.getCode());
		companyInputMessage.setCompanyDTO(companyDto);
		companyOutMessage = companyService.findCompany(companyInputMessage);

		ArrayList<CompanyDTO> list = (ArrayList<CompanyDTO>) companyOutMessage
				.getCompanyDTOList();
		ModelAndView mav = new ModelAndView("company_list");
		mav.addObject("companyList", list);
		logger.info("company List : " + list);
		String succ="Blk";
		if(list.equals(null) || list.size()==0)
		{
		  model.addAttribute("succ", succ);
		}
		return mav;
	}

	@RequestMapping(value = "/get_company", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getCompany(
			@ModelAttribute("companyForm") CompanyForm companyForm,
			@RequestParam("companyId") String companyId,
			@RequestParam("opr") String opr) {
		logger.info("Get Company : " + companyId);
		logger.info("Opr : " + opr);
		if (StringUtils.hasText(companyId)) {
			int id = NumberUtils.parseNumber(companyId, Integer.class);
			CompanyInputMessage companyInputMessage = new CompanyInputMessage();
			CompanyDTO companyDto = new CompanyDTO();
			companyDto.setCompanyId(id);
			companyInputMessage.setCompanyDTO(companyDto);
			CompanyOutMessage companyOutMessage = companyService
					.findCompanyById(companyInputMessage);

			ArrayList<CompanyDTO> list = (ArrayList<CompanyDTO>) companyOutMessage
					.getCompanyDTOList();
			if (list.size() == 1) {
				logger.info("get data for companyId : " + id);
				companyForm = new CompanyForm();
				companyForm.setCompanyDto(list.get(0));
			}
		}
		ModelAndView mav = new ModelAndView("company_detail_edit");
		mav.addObject("companyForm", companyForm);
		mav.addObject("opr", opr);
		return mav;
	}

	@RequestMapping(value = "/update_company", method = RequestMethod.POST)
	public String updateCompany(
			@ModelAttribute("companyForm") CompanyForm companyForm, Model model) {

		ErrorListDTO errorListDTO = null;
		CompanyDTO companyDTO = companyForm.getCompanyDto();
		Date finYearFrom = companyDTO.getFinancialYrBeg();
		Date finYearTo = companyDTO.getFinancialYrEnd();
		if (finYearFrom != null && finYearTo != null) {
			if (finYearTo.before(finYearFrom)) {
				errorListDTO = new ErrorListDTO();
				ErrorDTO error = new ErrorDTO("1",
						" Fin. Year From cannot be greater than  Fin. Year To");
				errorListDTO.addError(error);
			}
		}
		logger.info("-------------From " + finYearFrom);
		logger.info("--------------End " + finYearTo);
		logger.info("Error " + errorListDTO);

		if (errorListDTO == null) {
			CompanyInputMessage companyInputMessage = new CompanyInputMessage();
			companyInputMessage.setCompanyDTO(companyDTO);
			CompanyOutMessage companyOutMessage = companyService
					.updateCompany(companyInputMessage);
			if (companyOutMessage != null)
				errorListDTO = companyOutMessage.getErrorListDTO();
		}

		if (errorListDTO != null && errorListDTO.hasErrors()) {
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errors", errorDTO);
			model.addAttribute("opr", "E");
			return "company_detail_edit";
		}
		String succ="Up";
		 model.addAttribute("succ",succ);
		return "redirect:/show_company_form";
	}

	@RequestMapping(value = "/delete_company", method = RequestMethod.GET)
	public String deleteCompany(@RequestParam("companyId") String companyId,Model model) {
		if (StringUtils.hasText(companyId)) {
			int id = NumberUtils.parseNumber(companyId, Integer.class);
			CompanyInputMessage companyInputMessage = new CompanyInputMessage();
			CompanyDTO companyDto = new CompanyDTO();
			companyDto.setCompanyId(id);
			companyInputMessage.setCompanyDTO(companyDto);
			companyService.deleteCompany(companyInputMessage);
		}
		String succ="Dl";
		model.addAttribute("succ", succ);
		return "redirect:/show_company_form";
	}
}
