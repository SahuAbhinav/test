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

import com.advanz.erp.client.http.controller.form.LeaveTypeMastForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.LeaveTypeMastDTO;
import com.advanz.erp.masters.model.SalaryHeadDTO;
import com.advanz.erp.masters.model.criteria.LeaveTypeMastSearchCriteriaDTO;
import com.advanz.erp.masters.model.criteria.SalaryHeadSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.LeaveTypeMastInputMessage;
import com.advanz.erp.masters.model.msg.LeaveTypeMastOutputMessage;
import com.advanz.erp.masters.model.msg.SalaryHeadInputMessage;
import com.advanz.erp.masters.model.msg.SalaryHeadOutputMessage;
import com.advanz.erp.masters.service.business.ILeaveTypeMastService;
import com.advanz.erp.masters.service.business.ISalaryHeadService;

@Controller
public class LeaveTypeMastController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(LeaveTypeMastController.class);

	@Autowired
	public ILeaveTypeMastService leaveTypeMastService;

	@Autowired
	public ISalaryHeadService salaryHeadService;

	@RequestMapping(value = "/get_leave_type_list")
	public ModelAndView searchLKeaveType(@ModelAttribute("leaveTypeMastSearchCriteriaDTO") LeaveTypeMastSearchCriteriaDTO searchCriteria,@ModelAttribute("leaveTypeMastForm") LeaveTypeMastForm leaveTypeMastForm,Model model,@RequestParam(value="menuId",required=false) String menuId,HttpSession session) {
		if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
	
		List<LeaveTypeMastDTO> list = new ArrayList<LeaveTypeMastDTO>();
		LeaveTypeMastOutputMessage leaveTypeMastOutputMessage = null;
		LeaveTypeMastInputMessage leaveTypeMastInputMessage = new LeaveTypeMastInputMessage();
		leaveTypeMastInputMessage.setSearchCriteria(searchCriteria);
		leaveTypeMastOutputMessage = leaveTypeMastService.search(leaveTypeMastInputMessage);
		list = (ArrayList<LeaveTypeMastDTO>) leaveTypeMastOutputMessage.getLeaveTypeMastDTOList();
		String succ="Blk";
		if(list.equals(null) || list.size()==0)
		{
		  model.addAttribute("succ", succ);
		}
		logger.debug(" list------- :-" + list);
		ModelAndView mav = new ModelAndView("leaveType-list");
		mav.addObject("leaveTypeList", list);
		mav.addObject("leaveTypeMastSearchCriteriaDTO", searchCriteria);
		mav.addObject("leaveTypeMastForm", leaveTypeMastForm);
		return mav;
	}

	@RequestMapping(value = "/show_new_leave_type_form", method = RequestMethod.GET)
	public ModelAndView showForm(@ModelAttribute("leaveTypeMastForm") LeaveTypeMastForm leaveTypeMastForm) {
		ModelAndView mav = new ModelAndView("leaveType-detail");
		if (leaveTypeMastForm == null) {
			leaveTypeMastForm = new LeaveTypeMastForm();
		}
		List<LeaveTypeMastDTO> list = new ArrayList<LeaveTypeMastDTO>();
		LeaveTypeMastOutputMessage leaveTypeMastOutputMessage = null;
		LeaveTypeMastInputMessage leaveTypeMastInputMessage = new LeaveTypeMastInputMessage();
		LeaveTypeMastSearchCriteriaDTO searchCriteria = new LeaveTypeMastSearchCriteriaDTO();
		leaveTypeMastInputMessage.setSearchCriteria(searchCriteria);
		leaveTypeMastOutputMessage = leaveTypeMastService.search(leaveTypeMastInputMessage);
		list = (ArrayList<LeaveTypeMastDTO>) leaveTypeMastOutputMessage.getLeaveTypeMastDTOList();
		logger.debug(" list------- :-" + list);
		mav.addObject("leaveTypeList", list);
		LeaveTypeMastDTO leaveTypeMastDTO=new LeaveTypeMastDTO();
		leaveTypeMastDTO.setEncashmentFlag(0);
		leaveTypeMastDTO.setLeaveCarryForwardFlag(0);
		leaveTypeMastForm.setLeaveTypeMastDTO(leaveTypeMastDTO);
		mav.addObject("leaveTypeMastForm", leaveTypeMastForm);
		return mav;
	}

	@RequestMapping(value = "/save_leave_Type", method = RequestMethod.POST)
	public String saveLeaveTypeMast(@ModelAttribute("leaveTypeMastForm") LeaveTypeMastForm leaveTypeMastForm,Model model) {
		logger.info("leaveTypeMastForm = ddd "+ leaveTypeMastForm.getLeaveTypeMastDTO().getEncashments());
		
		LeaveTypeMastInputMessage leaveTypeMastInputMessage = new LeaveTypeMastInputMessage();
		LeaveTypeMastDTO leaveTypeMastDTO=leaveTypeMastForm.getLeaveTypeMastDTO();
		leaveTypeMastDTO.setCreatedUserId(getCreatedUserId());
		leaveTypeMastInputMessage.setLeaveTypeMastDTO(leaveTypeMastDTO);
		logger.debug("leaveTypeMastInputMessage controolluh---"+ leaveTypeMastInputMessage.getLeaveTypeMastDTO().getEncashmentFlag());
		LeaveTypeMastOutputMessage leaveTypeMastOutputMessage = leaveTypeMastService.createLeaveTypeMast(leaveTypeMastInputMessage);
		ErrorListDTO errorListDTO = leaveTypeMastOutputMessage.getErrorListDTO();

		if (errorListDTO != null && errorListDTO.hasErrors()) {
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errors",errorDTO);
			return "leaveType-detail";
		}
		String succ="Ad";
		model.addAttribute("succ", succ);
		return "redirect:/get_leave_type_list";
	}

	// for edit and remove
	@RequestMapping(value = "/get_leaveType", method = RequestMethod.GET)
	public ModelAndView getLeavbeTypeData(@RequestParam("leaveId") String leaveId,@RequestParam("opr") String opr,Model model) {
		logger.info("Get leaveId : " + leaveId);
		logger.info("Opr : " + opr);
		LeaveTypeMastForm leaveTypeMastForm = null;
		LeaveTypeMastOutputMessage leaveTypeMastOutputMessage = null;
		List<SalaryHeadDTO> list3=null;
		List<SalaryHeadDTO> list4=new ArrayList<SalaryHeadDTO>();
		LeaveTypeMastDTO leaveTypeMastDTO =null;
		if (StringUtils.hasText(leaveId)) {
			int id = NumberUtils.parseNumber(leaveId, Integer.class);
			LeaveTypeMastInputMessage leaveTypeMastInputMessage = new LeaveTypeMastInputMessage();
			LeaveTypeMastDTO LeaveTypeMastDTO = new LeaveTypeMastDTO();
			LeaveTypeMastDTO.setLeaveId(id);
			leaveTypeMastInputMessage.setLeaveTypeMastDTO(LeaveTypeMastDTO);
			
			leaveTypeMastOutputMessage = leaveTypeMastService.findLeaveTypeMastById(leaveTypeMastInputMessage);
			ArrayList<LeaveTypeMastDTO> list = (ArrayList<LeaveTypeMastDTO>) leaveTypeMastOutputMessage.getLeaveTypeMastDTOList();
			
			if (list.size() > 0) {
				leaveTypeMastForm = new LeaveTypeMastForm();
				leaveTypeMastDTO=list.get(0);
//				leaveTypeMastForm.setLeaveTypeMastDTO(list.get(0));
				 list3 = leaveTypeMastDTO.getEncashments();
			}
		}

		SalaryHeadOutputMessage salaryHeadOutputMessage = salaryHeadService.findAllSalaryHeads();
		ArrayList<SalaryHeadDTO> list1 = (ArrayList<SalaryHeadDTO>) salaryHeadOutputMessage.getSalaryHeadDTOList();
		if (list1 != null) {
			for (SalaryHeadDTO e : list1) {
				boolean flag = false;
				int id1 = e.getSalaryHeadId();
				for (SalaryHeadDTO e1 : list3) {
					if (id1 == e1.getSalaryHeadId()) {
						System.out.println("enter == " + id1);
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
		leaveTypeMastDTO.setEncashments(list4);
		leaveTypeMastForm.setLeaveTypeMastDTO(leaveTypeMastDTO);

		ModelAndView mav = new ModelAndView("leaveType-edit");
		mav.addObject("leaveTypeMastForm", leaveTypeMastForm);
		mav.addObject("opr", opr);
		logger.info(leaveTypeMastForm.getLeaveTypeMastDTO().toString());
		return mav;
	}

	@RequestMapping(value = "/update_leaveType", method = RequestMethod.POST)
	public String updateLeaveTypeHead(@ModelAttribute("leaveTypeMastForm") LeaveTypeMastForm leaveTypeMastForm,Model model) {
		logger.info("leaveTypeMastForm = " + leaveTypeMastForm);
		logger.info(leaveTypeMastForm.getLeaveTypeMastDTO().toString());
		LeaveTypeMastInputMessage leaveTypeMastInputMessage = new LeaveTypeMastInputMessage();
		LeaveTypeMastDTO leaveTypeMastDTO=leaveTypeMastForm.getLeaveTypeMastDTO();
		leaveTypeMastDTO.setModifiedUserId(getCreatedUserId());
		leaveTypeMastInputMessage.setLeaveTypeMastDTO(leaveTypeMastDTO);
		LeaveTypeMastOutputMessage leaveTypeMastOutputMessage = leaveTypeMastService.updateLeaveTypeMast(leaveTypeMastInputMessage);
		ErrorListDTO errorListDTO = leaveTypeMastOutputMessage.getErrorListDTO();

		if (errorListDTO != null && errorListDTO.hasErrors()) {
			ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
			logger.info(" adding Error ");
			model.addAttribute("errors",errorDTO);
			model.addAttribute("opr", "E");
			return "leaveType-edit";
		}
		String succ="Up";
		model.addAttribute("succ", succ);
		return "redirect:/get_leave_type_list";

	}

	@RequestMapping(value = "/remove_leaveType", method = RequestMethod.GET)
	public String removeLeaveType(@RequestParam("leaveId") String leaveId,@ModelAttribute("leaveTypeMastForm") LeaveTypeMastForm leaveTypeMastForm,Model model) {
		if (StringUtils.hasText(leaveId)) {
			int id = NumberUtils.parseNumber(leaveId, Integer.class);
			LeaveTypeMastInputMessage leaveTypeMastInputMessage = new LeaveTypeMastInputMessage();
			LeaveTypeMastDTO leaveTypeMastDTO = new LeaveTypeMastDTO();
			leaveTypeMastDTO.setLeaveId(id);
			leaveTypeMastDTO.setModifiedUserId(getCreatedUserId());
			leaveTypeMastInputMessage.setLeaveTypeMastDTO(leaveTypeMastDTO);
			leaveTypeMastService.deleteLeaveTypeMast(leaveTypeMastInputMessage);
		}
		String succ="Dl";
		model.addAttribute("succ", succ);
		return "redirect:/get_leave_type_list";
	}

	@ModelAttribute("salaryHeadList")
	public @ResponseBody
	List<SalaryHeadDTO> getStates(@ModelAttribute("rows") ArrayList<SalaryHeadDTO> list) {
		SalaryHeadOutputMessage salaryHeadOutputMessage = salaryHeadService.findAllSalaryHeads();
		list = (ArrayList<SalaryHeadDTO>) salaryHeadOutputMessage.getSalaryHeadDTOList();
		logger.debug("getSalaryHeadDTOList ------" + list);
		return list;

	}

}
