package com.advanz.erp.client.http.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.ControllerUtil;
import com.advanz.erp.client.http.controller.form.EmployeeForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.masters.model.CityDTO;
import com.advanz.erp.masters.model.EmailDetailDTO;
import com.advanz.erp.masters.model.EmployeeDTO;
import com.advanz.erp.masters.model.EmployeeLeavesDTO;
import com.advanz.erp.masters.model.EmployeeSalaryDetDTO;
import com.advanz.erp.masters.model.LeaveTypeMastDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.ProfessionalTaxDTO;
import com.advanz.erp.masters.model.SalaryHeadDTO;
import com.advanz.erp.masters.model.criteria.SalaryHeadSearchCriteriaDTO;
import com.advanz.erp.masters.model.msg.CityInputMessage;
import com.advanz.erp.masters.model.msg.CityOutputMessage;
import com.advanz.erp.masters.model.msg.EmployeeInputMessage;
import com.advanz.erp.masters.model.msg.EmployeeOutputMessage;
import com.advanz.erp.masters.model.msg.LeaveTypeMastOutputMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.model.msg.ProfessionalTaxOutputMessage;
import com.advanz.erp.masters.model.msg.SalaryHeadInputMessage;
import com.advanz.erp.masters.model.msg.SalaryHeadOutputMessage;
import com.advanz.erp.masters.service.business.ICityService;
import com.advanz.erp.masters.service.business.IEmployeeService;
import com.advanz.erp.masters.service.business.ILeaveTypeMastService;
import com.advanz.erp.masters.service.business.IMastersService;
import com.advanz.erp.masters.service.business.IProfessionalTaxService;
import com.advanz.erp.masters.service.business.ISalaryHeadService;

@Controller
@SessionAttributes({ "employeeDTOA", "employeeDTOU" })
public class EmployeeController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	public IEmployeeService employeeService;

	@Autowired
	public IMastersService mastersService;

	@Autowired
	public ICityService cityService;

	@Autowired
	public ILeaveTypeMastService leaveTypeMastService;

	@Autowired
	public ISalaryHeadService salaryHeadService;
	@Autowired
	public IProfessionalTaxService professionalTaxService;
		
	@RequestMapping(value = "/show_Employee_list", method = RequestMethod.GET)
	public ModelAndView showList(@ModelAttribute("employeeForm") EmployeeForm employeeForm,@RequestParam(value="menuId",required=false) String menuId,HttpSession session) {
		if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
		ModelAndView mav = new ModelAndView("employee_show");
		if (employeeForm == null) {
			employeeForm = new EmployeeForm();
		}
		EmployeeOutputMessage employeeOutputMessage = employeeService.findAllEmployee();
		ArrayList<EmployeeDTO> list = (ArrayList<EmployeeDTO>) employeeOutputMessage.getEmployeeDTOList();
		mav.addObject("employeeForm", employeeForm);
		mav.addObject("employeeList", list);
		return mav;
	}

	@RequestMapping(value = "/checkEmployeeCode", method = RequestMethod.POST)
	public @ResponseBody
	String checkEmployee(@ModelAttribute(value = "code") String employeeCode,BindingResult result) {
		String returnText = "";

		EmployeeOutputMessage employeeOutputMessage = null;
		EmployeeInputMessage employeeInputMessage = new EmployeeInputMessage();
		ArrayList<EmployeeDTO> list;
		EmployeeDTO employeeDTO = new EmployeeDTO();
		if (StringUtils.hasText(employeeCode)) {
			employeeDTO.setEmployeeCity(new CityDTO());
			employeeDTO.setEmployeeCode(employeeCode);
			employeeInputMessage.setEmployeeDTO(employeeDTO);
			employeeOutputMessage = employeeService.findEmployee(employeeInputMessage);
			list = (ArrayList<EmployeeDTO>) employeeOutputMessage.getEmployeeDTOList();
			if (list != null && list.size() > 0) {
				returnText = "Employee Code already exits";
			}
		}
		return returnText;
	}

	@RequestMapping(value = "/show_Employee_form", method = RequestMethod.GET)
	public ModelAndView showForm(@ModelAttribute("employeeForm") EmployeeForm employeeForm,ModelMap model) {
		ModelAndView mav = new ModelAndView("employee_add");
		if (employeeForm == null) {
			employeeForm = new EmployeeForm();
		}
		EmployeeDTO employeeDTO = new EmployeeDTO();
		model.put("employeeDTOA", employeeDTO);
		return mav;
	}

	@RequestMapping(value = "/get_employee_data", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView getEmployeeData(@ModelAttribute(value = "employeeForm") EmployeeForm employeeForm) {
		 
		String employeeCode=employeeForm.getEmployeeCode();
		String employeeName=employeeForm.getEmployeeName();
		String empliyeeCity=employeeForm.getEmpliyeeCity();
		
		
		ModelAndView mav = new ModelAndView("employee_show");
		ArrayList<EmployeeDTO> list = null;
		EmployeeOutputMessage employeeOutputMessage = null;
		EmployeeInputMessage employeeInputMessage = new EmployeeInputMessage();
		ErrorDTO errorDTO=new ErrorDTO();
		if (StringUtils.hasText(employeeCode) || StringUtils.hasText(employeeName) || StringUtils.hasText(empliyeeCity)) 
		{
			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setEmployeeCity(new CityDTO());
			employeeDTO.setEmployeeName(employeeName);
			employeeDTO.getEmployeeCity().setCityName(empliyeeCity);
			employeeDTO.setEmployeeCode(employeeCode);
			employeeInputMessage.setEmployeeDTO(employeeDTO);
			employeeOutputMessage = employeeService.findEmployee(employeeInputMessage);
			list = (ArrayList<EmployeeDTO>) employeeOutputMessage.getEmployeeDTOList();
			if(list.equals(null) || list.size()==0)
			{
			 errorDTO.setErrorMsg("Record Not Found !!!");
			}
			} else {
			employeeOutputMessage = employeeService.findAllEmployee();
			list = (ArrayList<EmployeeDTO>) employeeOutputMessage.getEmployeeDTOList();
		}
		
		
		mav.addObject("employeeList", list);
		mav.addObject("error", errorDTO);
		return mav;
	}

	@RequestMapping(value = "/get_employee", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView getEmployeeData(@ModelAttribute("employeeForm") EmployeeForm employeeForm,@ModelAttribute("employeeId") String employeeId,@ModelAttribute("opr") String opr, ModelMap model) {
		logger.info("Get employeeId : " + employeeId);
		logger.info("Get opr : " + opr);
		EmployeeOutputMessage employeeOutputMessage = null;
		ModelAndView mav = null;

		if (StringUtils.hasText(employeeId) && opr.equals("R")) {
			int id = NumberUtils.parseNumber(employeeId, Integer.class);
			EmployeeInputMessage employeeInputMessage = new EmployeeInputMessage();
			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setEmployeeId(id);
			employeeDTO.setModifiedUserId(getCreatedUserId());
			employeeInputMessage.setEmployeeDTO(employeeDTO);
			employeeInputMessage.setDeletedFlag(false);
			employeeOutputMessage = employeeService.deleteEmployee(employeeInputMessage);
			EmployeeForm employeeForm1 = null;
			if (employeeOutputMessage.getErrorListDTO() != null	&& employeeOutputMessage.getErrorListDTO().hasErrors()) {
				employeeForm1 = new EmployeeForm();
				mav = new ModelAndView("employee_show");
				ErrorDTO errorDTO=employeeOutputMessage.getErrorListDTO().getErrorList().get(0);
				mav.addObject("error",errorDTO);
				employeeOutputMessage = employeeService.findAllEmployee();
				ArrayList<EmployeeDTO> list = (ArrayList<EmployeeDTO>) employeeOutputMessage.getEmployeeDTOList();
				
				
				mav.addObject("employeeList", list);
				mav.addObject("employeeForm", employeeForm1);
				return mav;
			}
		}
		if (opr.equals("M") || opr.equals("V")) {
			mav = new ModelAndView("employee_edit");
		} else {
			mav = new ModelAndView("employee_remove");
		}
		if (StringUtils.hasText(employeeId)) {
			int id = NumberUtils.parseNumber(employeeId, Integer.class);
			EmployeeInputMessage employeeInputMessage = new EmployeeInputMessage();
			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setEmployeeId(id);
			employeeInputMessage.setEmployeeDTO(employeeDTO);
			employeeOutputMessage = employeeService.findEmployeeById(employeeInputMessage);
			ArrayList<EmployeeDTO> list = (ArrayList<EmployeeDTO>) employeeOutputMessage.getEmployeeDTOList();
			if (list.size() == 1) {
				employeeForm = new EmployeeForm();
				employeeForm.setEmployeeDTO(list.get(0));
				MastersInputMessage mastersInputMessage = new MastersInputMessage();
				MastersDTO mastersDto = new MastersDTO();
				mastersDto.setMastersId(list.get(0).getMasterSubEntityDepartment());
				mastersInputMessage.setMastersDTO(mastersDto);
				MastersOutputMessage obj2 = mastersService.findMastersById(mastersInputMessage);
				mav.addObject("subDepartment", obj2.getMastersDTOList());

				LeaveTypeMastOutputMessage leaveTypeMastOutputMessage = leaveTypeMastService.findAllLeaveTypeMasts();

				List<LeaveTypeMastDTO> list3 = leaveTypeMastOutputMessage.getLeaveTypeMastDTOList();
				List<EmployeeLeavesDTO> list1 = list.get(0).getEmployeeLeavesDTOList();
				List<EmployeeLeavesDTO> list4 = new ArrayList<EmployeeLeavesDTO>();

				if (list1 != null) {
					for (LeaveTypeMastDTO e : list3) {
						boolean flag = false;
						int id1 = e.getLeaveId();
						for (EmployeeLeavesDTO e1 : list1) {
							if (id1 == e1.getLeaveId()) {
								System.out.println("enter == ");
								list4.add(e1);
								flag = true;
								break;
							}
						}
						if (!flag) {
							list4.add(new EmployeeLeavesDTO());
						}
					}
				}
				list.get(0).setEmployeeLeavesDTOList(list4);
				List<EmployeeSalaryDetDTO> employeeSalaryDetDTOTemp = list.get(
						0).getEmployeeSalaryDetDTOList();
				if (employeeSalaryDetDTOTemp != null && employeeSalaryDetDTOTemp.size() > 0) {
					SalaryHeadSearchCriteriaDTO searchCriteria = new SalaryHeadSearchCriteriaDTO();
					searchCriteria.setHeadType("E");
					SalaryHeadOutputMessage salaryHeadOutputMessage = null;
					SalaryHeadInputMessage salaryHeadInputMessage = new SalaryHeadInputMessage();
					salaryHeadInputMessage.setSearchCriteria(searchCriteria);
					salaryHeadOutputMessage = salaryHeadService
							.search(salaryHeadInputMessage);
					List<SalaryHeadDTO> listE = (ArrayList<SalaryHeadDTO>) salaryHeadOutputMessage
							.getSalaryHeadDTOList();
					salaryHeadOutputMessage = null;
					searchCriteria.setHeadType("D");
					salaryHeadInputMessage.setSearchCriteria(searchCriteria);
					salaryHeadOutputMessage = salaryHeadService
							.search(salaryHeadInputMessage);
					List<SalaryHeadDTO> listD = (ArrayList<SalaryHeadDTO>) salaryHeadOutputMessage
							.getSalaryHeadDTOList();
					List<EmployeeSalaryDetDTO> employeeSalaryDetDTOE = new ArrayList<EmployeeSalaryDetDTO>();
					List<EmployeeSalaryDetDTO> employeeSalaryDetDTOTDe = new ArrayList<EmployeeSalaryDetDTO>();
					
					mav.addObject("salaryHeadListE", listE);
					mav.addObject("salaryHeadListD", listD);
					
					
					double eamount = 0.0;
					double damount = 0.0;
					try{
					if (listE != null) {
						for (SalaryHeadDTO obj : listE) {
							boolean flag = true;
							for (EmployeeSalaryDetDTO objTemp : employeeSalaryDetDTOTemp) {
								if (objTemp.getSalaryId().equals(
										obj.getSalaryHeadId().toString())) {
									employeeSalaryDetDTOE.add(objTemp);
									eamount = eamount + objTemp.getAmount();
									flag = false;
									break;
								}
							}
							if (flag) {
								employeeSalaryDetDTOE
										.add(new EmployeeSalaryDetDTO());
							}

						}
					}}
					catch (Exception e) {
						// TODO: handle exception
					}
					if (listD != null) {
						for (SalaryHeadDTO obj : listD) {
							boolean flag = true;
							for (EmployeeSalaryDetDTO objTemp : employeeSalaryDetDTOTemp) {
								if (objTemp.getSalaryId().equals(
										obj.getSalaryHeadId().toString())) {
									employeeSalaryDetDTOTDe.add(objTemp);
									damount = damount + objTemp.getAmount();
									flag = false;
									break;
								}
							}
							if (flag) {
								employeeSalaryDetDTOTDe.add(new EmployeeSalaryDetDTO());
							}

						}
					}
					list.get(0).setEmployeeSalaryDetDTOList(employeeSalaryDetDTOE);
					list.get(0).setEmployeeSalaryDetDTOListDe(employeeSalaryDetDTOTDe);
					list.get(0).setEamount(eamount);
					list.get(0).setDamount(damount);
					list.get(0).setTotalAmount(eamount - damount);
				}
				EmployeeDTO employeeDTOU = list.get(0);
				model.put("employeeDTOU", employeeDTOU);
				
			}

		}
		
		
		mav.addObject("employeeForm", employeeForm);
		// logger.info(employeeForm.getEmploDTO().toString());
		return mav;
	}

	@RequestMapping(value = "/employee_remove", method = RequestMethod.GET)
	public ModelAndView removeEemployee(@RequestParam("employeeId") String employeeId) {
		EmployeeOutputMessage employeeOutputMessage = null;
		Integer id = null;
		if (StringUtils.hasText(employeeId)) {
			id = NumberUtils.parseNumber(employeeId, Integer.class);
			EmployeeInputMessage employeeInputMessage = new EmployeeInputMessage();
			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setEmployeeId(id);
			employeeDTO.setModifiedUserId(getCreatedUserId());
			employeeInputMessage.setEmployeeDTO(employeeDTO);
			employeeInputMessage.setDeletedFlag(true);
			employeeOutputMessage = employeeService
					.deleteEmployee(employeeInputMessage);
		}
		ModelAndView mav = null;
		EmployeeForm employeeForm = null;
		if (employeeOutputMessage.getErrorListDTO() != null
				&& employeeOutputMessage.getErrorListDTO().hasErrors()) {
			employeeForm = new EmployeeForm();
			mav = new ModelAndView("employee_remove");
			ErrorDTO errorDTO=employeeOutputMessage.getErrorListDTO().getErrorList().get(0);
			mav.addObject("error",errorDTO);
			EmployeeInputMessage employeeInputMessage = new EmployeeInputMessage();
			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setEmployeeId(id);
			employeeInputMessage.setEmployeeDTO(employeeDTO);
			employeeOutputMessage = employeeService
					.findEmployeeById(employeeInputMessage);
			ArrayList<EmployeeDTO> list2 = (ArrayList<EmployeeDTO>) employeeOutputMessage
					.getEmployeeDTOList();
			if (list2 != null && list2.size() > 0) {
				employeeForm.setEmployeeDTO(list2.get(0));
			}
			mav.addObject("employeeForm", employeeForm);
		} else {
			mav = new ModelAndView("employee_show");
			if (employeeForm == null) {
				employeeForm = new EmployeeForm();
			}
			employeeOutputMessage = employeeService.findAllEmployee();
			ArrayList<EmployeeDTO> list = (ArrayList<EmployeeDTO>) employeeOutputMessage
					.getEmployeeDTOList();
			mav.addObject("employeeForm", employeeForm);
			mav.addObject("employeeList", list);
		}
		ErrorDTO errorDTO=new ErrorDTO();
		errorDTO.setErrorMsg("Successfully Record Deleted !!!");
		mav.addObject("error", errorDTO);
		return mav;

	}

	@RequestMapping(value = "/show_Employee_form2", method = RequestMethod.POST)
	public ModelAndView addEmployeeForm(@ModelAttribute("employeeForm") EmployeeForm employee,ModelMap model,
			@ModelAttribute("employeeDTOA") EmployeeDTO employeeDTO) {
		ModelAndView mav = null;
		if (employee == null) {
			employee = new EmployeeForm();
		}
		employeeDTO = new EmployeeDTO();
		try {
			new ControllerUtil().copyObjectWithoutNull(employee.getEmployeeDTO(), employeeDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (employeeDTO.getUpdate() != null
				&& employeeDTO.getUpdate().equals("update")) {
			mav = new ModelAndView("employee_edit");
		} else {
			mav = new ModelAndView("employee_add");
		}
		logger.info("form value" + employeeDTO.getEmployeeName());
		logger.info("form value" + employeeDTO.getEmployeeAddress());
		// logger.info("form value"+employee.getLeavesIdList().size());
		employee.setEmployeeDTO(employeeDTO);
		mav.addObject("employeeForm", employee);
		model.addAttribute("step2", "2");
		model.put("employeeDTOA", employeeDTO);

		return mav;
	}

	@RequestMapping(value = "/edit_Employee_form2", method = RequestMethod.POST)
	public ModelAndView editEmployeeForm(@ModelAttribute("employeeForm") EmployeeForm employee,ModelMap model,
			@ModelAttribute("employeeDTOU") EmployeeDTO employeeDTOU) {
		ModelAndView mav = null;
		if (employee == null) {
			employee = new EmployeeForm();
		}
		
		try {
			new ControllerUtil().copyObjectWithoutNull(employee.getEmployeeDTO(), employeeDTOU);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mav = new ModelAndView("employee_edit");
		logger.info("form value" + employeeDTOU.getEmployeeName());
		logger.info("form value" + employeeDTOU.getEmployeeAddress());
		// logger.info("form value"+employee.getLeavesIdList().size());
		employee.setEmployeeDTO(employeeDTOU);
		mav.addObject("employeeForm", employee);
		model.addAttribute("step2", "2");
		model.put("employeeDTOA", employeeDTOU);
		model.put("employeeDTOU", employeeDTOU);
		return mav;
	}

	@RequestMapping(value = "/show_Employee_form3", method = RequestMethod.POST)
	public ModelAndView addEmployeeForm3(@ModelAttribute("employeeForm") EmployeeForm employee,
			ModelMap model,@ModelAttribute("employeeDTOA") EmployeeDTO employeeDTO) {
		ModelAndView mav = null;
		if (employee == null) {
			employee = new EmployeeForm();
		}
		try {
			new ControllerUtil().copyObjectWithoutNull(employee.getEmployeeDTO(), employeeDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (employeeDTO.getUpdate() != null
				&& employeeDTO.getUpdate().equals("update")) {
			mav = new ModelAndView("employee_edit");
		} else {
			mav = new ModelAndView("employee_add");
			
			LeaveTypeMastOutputMessage leaveTypeMastOutputMessage = leaveTypeMastService.findAllLeaveTypeMasts();
			List<LeaveTypeMastDTO> employeeLeavesDTOList=leaveTypeMastOutputMessage.getLeaveTypeMastDTOList();
			List<EmployeeLeavesDTO> empList=new ArrayList<EmployeeLeavesDTO>();
			for(int i=0;i<employeeLeavesDTOList.size();i++){
				EmployeeLeavesDTO employeeLeavesDTO=new EmployeeLeavesDTO();
				LeaveTypeMastDTO leaveTypeMastDTO=employeeLeavesDTOList.get(i);
				employeeLeavesDTO.setAllowDays(leaveTypeMastDTO.getAllowDays());
				empList.add(employeeLeavesDTO);
			}
			
			employeeDTO.setEmployeeLeavesDTOList(empList);
			//employeeDTO.setLeavesDaysList(leaveTypeMastOutputMessage.getLeaveTypeMastDTOList());
		}
		logger.info("form value" + employeeDTO.getEmployeeName());
		logger.info("form value" + employeeDTO.getEmployeeAddress());
		int leaveListSize= 	employeeDTO.getEmployeeLeavesDTOList().size();
		employee.setEmployeeDTO(employeeDTO);
		mav.addObject("employeeForm", employee);
		model.addAttribute("step2", "2");
		model.addAttribute("step3", "3");
		model.addAttribute("step4", "4");
		model.put("employeeDTOA", employeeDTO);
		model.put("leaveListSize", leaveListSize);
		
		return mav;
	}

	@RequestMapping(value = "/edit_Employee_form3", method = RequestMethod.POST)
	public ModelAndView editEmployeeForm3(
			@ModelAttribute("employeeForm") EmployeeForm employee,
			ModelMap model,
			@ModelAttribute("employeeDTOU") EmployeeDTO employeeDTOU) {
		
		ModelAndView mav = null;
		if (employee == null) {
			employee = new EmployeeForm();
		}
		try {
			new ControllerUtil().copyObjectWithoutNull(employee.getEmployeeDTO(), employeeDTOU);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mav = new ModelAndView("employee_edit");
	    int leaveListSize= 	employeeDTOU.getEmployeeLeavesDTOList().size();
		logger.info("form value" + employeeDTOU.getEmployeeName());
		logger.info("form value" + employeeDTOU.getEmployeeAddress());
		employee.setEmployeeDTO(employeeDTOU);
		mav.addObject("employeeForm", employee);
		model.addAttribute("step2", "2");
		model.addAttribute("step3", "3");
		model.addAttribute("step4", "4");
		model.put("employeeDTOU", employeeDTOU);
		model.put("leaveListSize", leaveListSize);
		
		return mav;
	}

	@RequestMapping(value = "/show_Employee_form4", method = RequestMethod.POST)
	public ModelAndView addEmployeeForm4(@ModelAttribute("employeeForm") EmployeeForm employee,	ModelMap model,
			@ModelAttribute("employeeDTOA") EmployeeDTO employeeDTO) {
		ModelAndView mav = null;
		if (employee == null) {
			employee = new EmployeeForm();
		}
		try {
			new ControllerUtil().copyObjectWithoutNull(employee.getEmployeeDTO(), employeeDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*Double pfCal=0.0;
		if(employeeDTO.getPfFlag()!=null && employeeDTO.getPfFlag()==1)
		{
		 if(employeeDTO.getPfEmployeeBasicContriAmt()!=null)
		 {   Double emplyeeBsc=employeeDTO.getPfEmployeeBasicContriAmt()*employeeDTO.getPfEmployeeSharePer();
		 	 Double emplyrBsc=employeeDTO.getPfEmployerBasicContriAmt()*employeeDTO.getPfEmployerSharePer();
			 pfCal=(emplyeeBsc+emplyrBsc)/100;
			EmployeeSalaryDetDTO detDTO=new EmployeeSalaryDetDTO();
			//detDTO.setAmount(2.2);
			List<EmployeeSalaryDetDTO> list=new ArrayList<EmployeeSalaryDetDTO>();
			list.add(0,new EmployeeSalaryDetDTO());
			detDTO.setAmount(pfCal);
			list.add(1,detDTO);
			 employeeDTO.setEmployeeSalaryDetDTOListDe(list);
		 }
		}
		Double esiCal=0.0;
		if(employeeDTO.getEsiFlag()!=null && employeeDTO.getEsiFlag()==1)
		 {
		  if(employeeDTO.getEamount()!=null)
		  {
				Double esiEmployeeShare =employeeDTO.getEsiEmployeeSharePer();
				Double esiEmployerShare =employeeDTO.getEsiEmployerSharePer();
				esiCal=((esiEmployeeShare+esiEmployerShare)*employeeDTO.getEamount())/100;
				employeeDTO.getEmployeeSalaryDetDTOListDe().get(0).setAmount(esiCal);
		  }
		 }
		Double deduAmt=pfCal+esiCal;
		
		if(employeeDTO.getEamount()!=null){
			employeeDTO.setTotalAmount(employeeDTO.getEamount()-deduAmt);
			employeeDTO.setDamount(deduAmt);
		}
		else{
			employeeDTO.setTotalAmount(deduAmt);
			employeeDTO.setDamount(deduAmt);
		}*/
		mav = new ModelAndView("employee_add");

		SalaryHeadSearchCriteriaDTO searchCriteria = new SalaryHeadSearchCriteriaDTO();
		searchCriteria.setHeadType("E");
		SalaryHeadOutputMessage salaryHeadOutputMessage = null;
		SalaryHeadInputMessage salaryHeadInputMessage = new SalaryHeadInputMessage();
		salaryHeadInputMessage.setSearchCriteria(searchCriteria);
		salaryHeadOutputMessage = salaryHeadService.search(salaryHeadInputMessage);
		List<SalaryHeadDTO> list = (ArrayList<SalaryHeadDTO>) salaryHeadOutputMessage.getSalaryHeadDTOList();
		logger.debug(" list------- :-" + list);
		// logger.debug("----salary head name list------- :-"+list.get(0).getBaseHeads().get(0).getSalaryHeadName());
		mav.addObject("salaryHeadListE", list);

		SalaryHeadSearchCriteriaDTO searchCriteria1 = new SalaryHeadSearchCriteriaDTO();
		SalaryHeadOutputMessage salaryHeadOutputMessage1 = null;
		searchCriteria1.setHeadType("D");
		SalaryHeadInputMessage salaryHeadInputMessage1 = new SalaryHeadInputMessage();
		salaryHeadInputMessage1.setSearchCriteria(searchCriteria1);
		salaryHeadOutputMessage1 = salaryHeadService.search(salaryHeadInputMessage1);
		List<SalaryHeadDTO> list1 = (ArrayList<SalaryHeadDTO>) salaryHeadOutputMessage1.getSalaryHeadDTOList();
		logger.debug(" list------- :-" + list1);
		// logger.debug("----salary head name list------- :-"+list.get(0).getBaseHeads().get(0).getSalaryHeadName());
		mav.addObject("salaryHeadListD", list1);

		logger.info("form value" + employeeDTO.getEmployeeName());
		logger.info("form value" + employeeDTO.getEmployeeAddress());
		if (employeeDTO.getEmployeeLeavesDTOList() != null) {
			logger.info("form value "+ employeeDTO.getEmployeeLeavesDTOList().size());
		}
		
		employee.setEmployeeDTO(employeeDTO);
		mav.addObject("employeeForm", employee);
		model.addAttribute("step2", "2");
		model.addAttribute("step3", "3");
		model.addAttribute("step4", "4");
		
		model.put("employeeDTOA", employeeDTO);
		return mav;
	}

	@RequestMapping(value = "/edit_Employee_form4", method = RequestMethod.POST)
	 public  ModelAndView editEmployeeForm4(@ModelAttribute("employeeForm") EmployeeForm employee,ModelMap model,
			@ModelAttribute("employeeDTOU") EmployeeDTO employeeDTOU) {
		ModelAndView mav = null;
		if (employee == null) {
			employee = new EmployeeForm();
		}
		try {
			new ControllerUtil().copyObjectWithoutNull(employee.getEmployeeDTO(), employeeDTOU);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		mav = new ModelAndView("employee_edit");
		SalaryHeadSearchCriteriaDTO searchCriteria = new SalaryHeadSearchCriteriaDTO();
		searchCriteria.setHeadType("E");
		SalaryHeadOutputMessage salaryHeadOutputMessage = null;
		SalaryHeadInputMessage salaryHeadInputMessage = new SalaryHeadInputMessage();
		salaryHeadInputMessage.setSearchCriteria(searchCriteria);
		salaryHeadOutputMessage = salaryHeadService.search(salaryHeadInputMessage);
		List<SalaryHeadDTO> list = (ArrayList<SalaryHeadDTO>) salaryHeadOutputMessage.getSalaryHeadDTOList();
		logger.debug(" list------- :-" + list);
		// logger.debug("----salary head name list------- :-"+list.get(0).getBaseHeads().get(0).getSalaryHeadName());
		mav.addObject("salaryHeadListE", list);

		SalaryHeadSearchCriteriaDTO searchCriteria1 = new SalaryHeadSearchCriteriaDTO();
		SalaryHeadOutputMessage salaryHeadOutputMessage1 = null;
		searchCriteria1.setHeadType("D");
		SalaryHeadInputMessage salaryHeadInputMessage1 = new SalaryHeadInputMessage();
		salaryHeadInputMessage1.setSearchCriteria(searchCriteria1);
		salaryHeadOutputMessage1 = salaryHeadService.search(salaryHeadInputMessage1);
		List<SalaryHeadDTO> list1 = (ArrayList<SalaryHeadDTO>) salaryHeadOutputMessage1.getSalaryHeadDTOList();
		for (SalaryHeadDTO salaryHeadDTO : list1) {
			
		}
		logger.debug(" list------- :-" + list1);
		mav.addObject("salaryHeadListD", list1);

		logger.info("form value" + employeeDTOU.getEmployeeName());
		logger.info("form value" + employeeDTOU.getEmployeeAddress());
		if (employeeDTOU.getEmployeeLeavesDTOList() != null) {
		logger.info("form value "+ employeeDTOU.getEmployeeLeavesDTOList().size());
		
		}
		
		employee.setEmployeeDTO(employeeDTOU);
		mav.addObject("employeeForm", employee);
		model.addAttribute("step2", "2");
		model.addAttribute("step3", "3");
		model.addAttribute("step4", "4");
		model.put("employeeDTOU", employeeDTOU);
		return mav;
	}
	
	@RequestMapping(value = "/ajax_Employee_cal", method = RequestMethod.POST)
	public @ResponseBody Map refresh(@ModelAttribute("employeeForm") EmployeeForm employee,
			ModelMap model,	@ModelAttribute("employeeDTOA") EmployeeDTO employeeDTO) {
		Map mav = new HashMap();
		if (employee == null) {
			employee = new EmployeeForm();
		}
		try {
			new ControllerUtil().copyObjectWithoutNull(employee.getEmployeeDTO(), employeeDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
			List<EmployeeSalaryDetDTO> salaryDetDTOE = employeeDTO.getEmployeeSalaryDetDTOList();
			List<EmployeeSalaryDetDTO> salaryDetDTOD = employeeDTO.getEmployeeSalaryDetDTOListDe();
			if (salaryDetDTOE != null && salaryDetDTOE.size() > 0) {
				int i = 0;
				for (EmployeeSalaryDetDTO obj : salaryDetDTOE) {
					String id = obj.getSalaryId();
					Double amount = baseCalculation(id, salaryDetDTOE,salaryDetDTOD);
					employee.getEmployeeDTO().getEmployeeSalaryDetDTOList().get(i).setAmount(amount);
					i++;
				}
			}

			int indexNo=0;
			int pfNo=0;
			int esiNo=0;
			int pfFlag=0;
			if (salaryDetDTOD != null && salaryDetDTOD.size() > 0) {
				int i = 0;
				for (EmployeeSalaryDetDTO obj : salaryDetDTOD) {
					String id = obj.getSalaryId();
					
					Double amount = baseCalculation(id, salaryDetDTOE,salaryDetDTOD);
					
					//To chech professional tax head and get index no for employee list
					SalaryHeadInputMessage salaryHeadInputMessage=new SalaryHeadInputMessage();
					SalaryHeadDTO salaryHeadDTO=new SalaryHeadDTO();
					salaryHeadDTO.setSalaryHeadId(Integer.parseInt(id));
					
					salaryHeadInputMessage.setSalaryHeadDTO(salaryHeadDTO);
					SalaryHeadOutputMessage salaryHeadOutputMessage = salaryHeadService.findSalaryHeadById(salaryHeadInputMessage);
					List<SalaryHeadDTO> headList= salaryHeadOutputMessage.getSalaryHeadDTOList();
					if(headList!=null && headList.size()>0){
						SalaryHeadDTO shd=headList.get(0);
						if(shd.getProfessionalTaxFlag()!=null && shd.getProfessionalTaxFlag()==1){
							indexNo=i;
						}
						if(shd.getPfEmployeeFlag()!=null && shd.getPfEmployeeFlag()==1){
							pfNo=i;
						}
						if(shd.getEsiEmployeeFlag()!=null && shd.getEsiEmployeeFlag()==1){
							esiNo=i;
						}
						if(shd.getSalaryHeadCode().equalsIgnoreCase("PF"))
						 {
						  	pfFlag=i;
						 }
					}
					
				if(employeeDTO.getPfFlag() !=null && employeeDTO.getPfFlag()==1 && pfFlag==i)
			    	{
				   if(salaryDetDTOE.get(0).getAmount()!=null && salaryDetDTOE.get(0).getAmount()>15000)
					  {
					    double bsic=(15000*24)/100;
					   employee.getEmployeeDTO().getEmployeeSalaryDetDTOListDe().get(i).setAmount(bsic);
					  }
				    else
				  	 {
						employee.getEmployeeDTO().getEmployeeSalaryDetDTOListDe().get(i).setAmount(amount);
				  	 }	
				  }
			   else if(employeeDTO.getPfFlag() !=null && employeeDTO.getPfFlag()==0 && pfFlag==i )
				  {
					 employee.getEmployeeDTO().getEmployeeSalaryDetDTOListDe().get(i).setAmount(0.0);
				  }
			   else
			      {
					employee.getEmployeeDTO().getEmployeeSalaryDetDTOListDe().get(i).setAmount(amount);
				  }
					i++;
				}
			}
			// earning total
			List<EmployeeSalaryDetDTO> salaryDetDTOE1 = employeeDTO.getEmployeeSalaryDetDTOList();
			List<EmployeeSalaryDetDTO> salaryDetDTOD1 = employeeDTO.getEmployeeSalaryDetDTOListDe();
			double eamount = 0.0;
			double damount = 0.0;
			if (salaryDetDTOE1 != null)
				for (EmployeeSalaryDetDTO obj : salaryDetDTOE1) {
					eamount = eamount + obj.getAmount();
				}
			if(eamount >6500 && eamount <15000)
			{
				double esiAmt=(eamount*6.5)/100;
				employee.getEmployeeDTO().getEmployeeSalaryDetDTOListDe().get(1).setAmount(esiAmt);
			}else{employee.getEmployeeDTO().getEmployeeSalaryDetDTOListDe().get(1).setAmount(0.0);}
			//Get Professional Tax amount from Professional tax form........
			Double netamnt=(eamount - damount)*12;
			/*ProfessionalTaxOutputMessage taxOutputMessage=professionalTaxService.findAllProfessionalTax();
			List<ProfessionalTaxDTO> pList=taxOutputMessage.getProfessionalTaxDTOList();
			Double dedctAmnt=0.0;
			
			for(int i=0;i<pList.size();i++){
				ProfessionalTaxDTO pDTO=pList.get(i);
				if(netamnt>=pDTO.getFromAmount() && netamnt<=pDTO.getToAmount()){
					dedctAmnt=Double.parseDouble(pDTO.getDeductAmount());
				}}	
			employee.getEmployeeDTO().getEmployeeSalaryDetDTOListDe().get(indexNo).setAmount(dedctAmnt);*/
//End......
			
			if (salaryDetDTOD1 != null)
				for (EmployeeSalaryDetDTO obj : salaryDetDTOD1) {
					damount = damount + Math.abs(obj.getAmount());
				}
			
			employeeDTO.setEamount(eamount);
			
			employeeDTO.setDamount(damount);
			employeeDTO.setTotalAmount(eamount - damount);
			SalaryHeadSearchCriteriaDTO searchCriteria = new SalaryHeadSearchCriteriaDTO();
			searchCriteria.setHeadType("E");
			SalaryHeadOutputMessage salaryHeadOutputMessage = null;
			SalaryHeadInputMessage salaryHeadInputMessage = new SalaryHeadInputMessage();
			salaryHeadInputMessage.setSearchCriteria(searchCriteria);
			salaryHeadOutputMessage = salaryHeadService.search(salaryHeadInputMessage);
			List<SalaryHeadDTO> list = (ArrayList<SalaryHeadDTO>) salaryHeadOutputMessage.getSalaryHeadDTOList();
			logger.debug(" list------- :-" + list);
			mav.put("salaryHeadListE", list);

			salaryHeadOutputMessage = null;
			searchCriteria.setHeadType("D");
			salaryHeadInputMessage.setSearchCriteria(searchCriteria);
			salaryHeadOutputMessage = salaryHeadService.search(salaryHeadInputMessage);
			List<SalaryHeadDTO> list1 = (ArrayList<SalaryHeadDTO>) salaryHeadOutputMessage.getSalaryHeadDTOList();
			logger.debug(" list------- :-" + list1);
			employee.setScript("");
			employee.setEmployeeDTO(employeeDTO);
			mav.put("salaryHeadListD", list1);
			mav.put("employeeForm", employee);
			model.addAttribute("step2", "2");
			model.addAttribute("step3", "3");
			model.addAttribute("step4", "4");
			model.put("employeeDTOA", employeeDTO);
		
		return mav;
	}
	@RequestMapping(value = "/ajax_Employee_form", method = RequestMethod.POST)
	public @ResponseBody ModelAndView saveEmployee(@ModelAttribute("employeeForm") EmployeeForm employee,
			ModelMap model,	@ModelAttribute("employeeDTOA") EmployeeDTO employeeDTO) {
		ModelAndView mav = null;
		if (employee == null) {
			employee = new EmployeeForm();
		}
		try {
			new ControllerUtil().copyObjectWithoutNull(employee.getEmployeeDTO(), employeeDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<EmployeeSalaryDetDTO> salaryDetDTOE = employeeDTO.getEmployeeSalaryDetDTOList();
		List<EmployeeSalaryDetDTO> salaryDetDTOD = employeeDTO.getEmployeeSalaryDetDTOListDe();
		if (salaryDetDTOE != null && salaryDetDTOE.size() > 0) {
			int i = 0;
			for (EmployeeSalaryDetDTO obj : salaryDetDTOE) {
				String id = obj.getSalaryId();
				Double amount = baseCalculation(id, salaryDetDTOE,salaryDetDTOD);
				employee.getEmployeeDTO().getEmployeeSalaryDetDTOList().get(i).setAmount(amount);
				i++;
			}
		}

		int indexNo=0;
		int pfNo=0;
		int esiNo=0;
		int pfFlag=0;
		if (salaryDetDTOD != null && salaryDetDTOD.size() > 0) {
			int i = 0;
			for (EmployeeSalaryDetDTO obj : salaryDetDTOD) {
				String id = obj.getSalaryId();
				
				Double amount = baseCalculation(id, salaryDetDTOE,salaryDetDTOD);
				
				//To chech professional tax head and get index no for employee list
				SalaryHeadInputMessage salaryHeadInputMessage=new SalaryHeadInputMessage();
				SalaryHeadDTO salaryHeadDTO=new SalaryHeadDTO();
				salaryHeadDTO.setSalaryHeadId(Integer.parseInt(id));
				
				salaryHeadInputMessage.setSalaryHeadDTO(salaryHeadDTO);
				SalaryHeadOutputMessage salaryHeadOutputMessage = salaryHeadService.findSalaryHeadById(salaryHeadInputMessage);
				List<SalaryHeadDTO> headList= salaryHeadOutputMessage.getSalaryHeadDTOList();
				if(headList!=null && headList.size()>0){
					SalaryHeadDTO shd=headList.get(0);
					if(shd.getProfessionalTaxFlag()!=null && shd.getProfessionalTaxFlag()==1){
						indexNo=i;
					}
					if(shd.getPfEmployeeFlag()!=null && shd.getPfEmployeeFlag()==1){
						pfNo=i;
					}
					if(shd.getEsiEmployeeFlag()!=null && shd.getEsiEmployeeFlag()==1){
						esiNo=i;
					}
					if(shd.getSalaryHeadCode().equalsIgnoreCase("PF"))
					 {
					  	pfFlag=i;
					 }
				}
				
			if(employeeDTO.getPfFlag() !=null && employeeDTO.getPfFlag()==1 && pfFlag==i)
		    	{
			   if(salaryDetDTOE.get(0).getAmount()!=null && salaryDetDTOE.get(0).getAmount()>15000)
				  {
				    double bsic=(15000*24)/100;
				   employee.getEmployeeDTO().getEmployeeSalaryDetDTOListDe().get(i).setAmount(bsic);
				  }
			    else
			  	 {
					employee.getEmployeeDTO().getEmployeeSalaryDetDTOListDe().get(i).setAmount(amount);
			  	 }	
			  }
		   else if(employeeDTO.getPfFlag() !=null && employeeDTO.getPfFlag()==0 && pfFlag==i )
			  {
				 employee.getEmployeeDTO().getEmployeeSalaryDetDTOListDe().get(i).setAmount(0.0);
			  }
		   else
		      {
				employee.getEmployeeDTO().getEmployeeSalaryDetDTOListDe().get(i).setAmount(amount);
			  }
				i++;
			}
		}
		// earning total
		List<EmployeeSalaryDetDTO> salaryDetDTOE1 = employeeDTO.getEmployeeSalaryDetDTOList();
		List<EmployeeSalaryDetDTO> salaryDetDTOD1 = employeeDTO.getEmployeeSalaryDetDTOListDe();
		double eamount = 0.0;
		double damount = 0.0;
		if (salaryDetDTOE1 != null)
			for (EmployeeSalaryDetDTO obj : salaryDetDTOE1) {
				eamount = eamount + obj.getAmount();
			}
		if(eamount >6500 && eamount <15000)
		{
			double esiAmt=(eamount*6.5)/100;
			employee.getEmployeeDTO().getEmployeeSalaryDetDTOListDe().get(1).setAmount(esiAmt);
		}else{employee.getEmployeeDTO().getEmployeeSalaryDetDTOListDe().get(1).setAmount(0.0);}
		//Get Professional Tax amount from Professional tax form........
		Double netamnt=(eamount - damount)*12;
				
		if (salaryDetDTOD1 != null)
			for (EmployeeSalaryDetDTO obj : salaryDetDTOD1) {
				damount = damount + Math.abs(obj.getAmount());
			}
		
		employeeDTO.setEamount(eamount);
		
		employeeDTO.setDamount(damount);
		employeeDTO.setTotalAmount(eamount - damount);
		
			EmployeeInputMessage employeeInputMessage = new EmployeeInputMessage();
			employeeDTO.setCreatedUserId(getCreatedUserId());
			employeeInputMessage.setEmployeeDTO(employeeDTO);
			employeeService.createEmployee(employeeInputMessage);
			EmployeeOutputMessage employeeOutputMessage = employeeService.findAllEmployee();
			ArrayList<EmployeeDTO> list = (ArrayList<EmployeeDTO>) employeeOutputMessage.getEmployeeDTOList();
			mav = new ModelAndView("employee_show");
			ErrorDTO errorDTO=new ErrorDTO();
			//errorDTO.setErrorMsg("Successfully Record Saved !!!");
			String succ="Ad";
			model.addAttribute("succ",succ);
			employee.setSucc(succ);
			//errorDTO.setErrorMsg("Successfully Record Updated !!!");
			mav.addObject("employeeForm",employee);
			mav.addObject("error", errorDTO);
			mav.addObject("employeeList", list);
		
		return mav;
	}

	
	@RequestMapping(value = "/ajax_editEmployee_form", method = RequestMethod.POST)
	public ModelAndView updateEmployee(
			@ModelAttribute("employeeForm") EmployeeForm employee,
			ModelMap model,
			@ModelAttribute("employeeDTOU") EmployeeDTO employeeDTOU) {
		ModelAndView mav = null;
		if (employee == null) {
			employee = new EmployeeForm();
		}
		try {
			new ControllerUtil().copyObjectWithoutNull(employee.getEmployeeDTO(), employeeDTOU);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<EmployeeSalaryDetDTO> salaryDetDTOE = employeeDTOU.getEmployeeSalaryDetDTOList();
		List<EmployeeSalaryDetDTO> salaryDetDTOD = employeeDTOU.getEmployeeSalaryDetDTOListDe();
		if (salaryDetDTOE != null && salaryDetDTOE.size() > 0) {
			int i = 0;
			for (EmployeeSalaryDetDTO obj : salaryDetDTOE) {
				String id = obj.getSalaryId();
				Double amount = baseCalculation(id, salaryDetDTOE,salaryDetDTOD);
				employee.getEmployeeDTO().getEmployeeSalaryDetDTOList().get(i).setAmount(amount);
				i++;
			}
		}
		int indexNo=0;
		int pfNo=0;
		int esiNo=0;
		int pfFlag=0;
		if (salaryDetDTOD != null && salaryDetDTOD.size() > 0) {
			int i = 0;
			for (EmployeeSalaryDetDTO obj : salaryDetDTOD) {
				String id = obj.getSalaryId();
				Double amount = baseCalculation(id, salaryDetDTOE,salaryDetDTOD);
				System.out.println("HEAD ID......................."+id);
				//To chech professional tax head and get index no for employee list
				SalaryHeadInputMessage salaryHeadInputMessage=new SalaryHeadInputMessage();
				SalaryHeadDTO salaryHeadDTO=new SalaryHeadDTO();
				salaryHeadDTO.setSalaryHeadId(Integer.parseInt(id));
				salaryHeadInputMessage.setSalaryHeadDTO(salaryHeadDTO);
				SalaryHeadOutputMessage salaryHeadOutputMessage = salaryHeadService.findSalaryHeadById(salaryHeadInputMessage);
				List<SalaryHeadDTO> headList= salaryHeadOutputMessage.getSalaryHeadDTOList();
				if(headList!=null && headList.size()>0){
					SalaryHeadDTO shd=headList.get(0);
					if(shd.getProfessionalTaxFlag()!=null && shd.getProfessionalTaxFlag()==1){
						indexNo=i;
					}
					if(shd.getPfEmployeeFlag()!=null && shd.getPfEmployeeFlag()==1){
						pfNo=i;
					}
					if(shd.getEsiEmployeeFlag()!=null && shd.getEsiEmployeeFlag()==1){
						esiNo=i;
					}
					if(shd.getSalaryHeadCode().equalsIgnoreCase("PF"))
					 {
					  	pfFlag=i;
					 }
				}
	    if(employeeDTOU.getPfFlag() !=null && employeeDTOU.getPfFlag()==1 && pfFlag==i)
	       {
		   if(salaryDetDTOE.get(0).getAmount()!=null && salaryDetDTOE.get(0).getAmount()>15000)
			 {
			  double bsic=(15000*24)/100;
			  employee.getEmployeeDTO().getEmployeeSalaryDetDTOListDe().get(i).setAmount(bsic);
			  }
		   else{employee.getEmployeeDTO().getEmployeeSalaryDetDTOListDe().get(i).setAmount(amount);}	
		  }
	   else if(employeeDTOU.getPfFlag() !=null && employeeDTOU.getPfFlag()==0 && pfFlag==i)
		  { employee.getEmployeeDTO().getEmployeeSalaryDetDTOListDe().get(i).setAmount(0.0);}
	   else{employee.getEmployeeDTO().getEmployeeSalaryDetDTOListDe().get(i).setAmount(amount);}
		  i++;
		 }
	   }
		// earning total
		List<EmployeeSalaryDetDTO> salaryDetDTOE1 = employeeDTOU.getEmployeeSalaryDetDTOList();
		List<EmployeeSalaryDetDTO> salaryDetDTOD1 = employeeDTOU.getEmployeeSalaryDetDTOListDe();
		double eamount = 0.0;
		double damount = 0.0;
		for (EmployeeSalaryDetDTO obj : salaryDetDTOE1) {
			eamount = eamount + obj.getAmount();
		}
		if(eamount >6500 && eamount <15000)
		{
			double esiAmt=(eamount*6.5)/100;
			employee.getEmployeeDTO().getEmployeeSalaryDetDTOListDe().get(1).setAmount(esiAmt);
		}else{employee.getEmployeeDTO().getEmployeeSalaryDetDTOListDe().get(1).setAmount(0.0);}
		
		for (EmployeeSalaryDetDTO obj : salaryDetDTOD1) {
			damount = damount + Math.abs(obj.getAmount());
		}
		
		employeeDTOU.setEamount(eamount);
		employeeDTOU.setDamount(damount);
		
		employeeDTOU.setTotalAmount(eamount - damount);
		
			EmployeeInputMessage employeeInputMessage = new EmployeeInputMessage();
			employeeDTOU.setModifiedUserId(getCreatedUserId());
			employeeInputMessage.setEmployeeDTO(employeeDTOU);
			employeeService.updateEmployee(employeeInputMessage);
			EmployeeOutputMessage employeeOutputMessage = employeeService.findAllEmployee();
			ArrayList<EmployeeDTO> list = (ArrayList<EmployeeDTO>) employeeOutputMessage.getEmployeeDTOList();
			mav = new ModelAndView("employee_show");
			mav.addObject("employeeList", list);
		
		ErrorDTO errorDTO=new ErrorDTO();
		String succ="Up";
		model.addAttribute("succ",succ);
		employee.setSucc(succ);
		//errorDTO.setErrorMsg("Successfully Record Updated !!!");
		mav.addObject("employeeForm",employee);
		mav.addObject("error", errorDTO);
		return mav;
	}
	@RequestMapping(value = "/ajax_editEmployee_cal", method = RequestMethod.POST)
	public @ResponseBody Map refreshEdit(@ModelAttribute("employeeForm") EmployeeForm employee,
			ModelMap model,@ModelAttribute("employeeDTOU") EmployeeDTO employeeDTOU) {
		Map mav = new HashMap();
		if (employee == null) {
			employee = new EmployeeForm();
		}
		try {
			new ControllerUtil().copyObjectWithoutNull(employee.getEmployeeDTO(), employeeDTOU);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			//mav = new ModelAndView("employee_edit");

			List<EmployeeSalaryDetDTO> salaryDetDTOE = employeeDTOU.getEmployeeSalaryDetDTOList();
			List<EmployeeSalaryDetDTO> salaryDetDTOD = employeeDTOU.getEmployeeSalaryDetDTOListDe();
			if (salaryDetDTOE != null && salaryDetDTOE.size() > 0) {
				int i = 0;
				for (EmployeeSalaryDetDTO obj : salaryDetDTOE) {
					String id = obj.getSalaryId();
					Double amount = baseCalculation(id, salaryDetDTOE,salaryDetDTOD);
					employee.getEmployeeDTO().getEmployeeSalaryDetDTOList().get(i).setAmount(amount);
					i++;
				}
			}
			int indexNo=0;
			int pfNo=0;
			int esiNo=0;
			int pfFlag=0;
			if (salaryDetDTOD != null && salaryDetDTOD.size() > 0) {
				int i = 0;
				for (EmployeeSalaryDetDTO obj : salaryDetDTOD) {
					String id = obj.getSalaryId();
					Double amount = baseCalculation(id, salaryDetDTOE,salaryDetDTOD);
					System.out.println("HEAD ID......................."+id);
					//To chech professional tax head and get index no for employee list
					SalaryHeadInputMessage salaryHeadInputMessage=new SalaryHeadInputMessage();
					SalaryHeadDTO salaryHeadDTO=new SalaryHeadDTO();
					salaryHeadDTO.setSalaryHeadId(Integer.parseInt(id));
					salaryHeadInputMessage.setSalaryHeadDTO(salaryHeadDTO);
					SalaryHeadOutputMessage salaryHeadOutputMessage = salaryHeadService.findSalaryHeadById(salaryHeadInputMessage);
					List<SalaryHeadDTO> headList= salaryHeadOutputMessage.getSalaryHeadDTOList();
					if(headList!=null && headList.size()>0){
						SalaryHeadDTO shd=headList.get(0);
						if(shd.getProfessionalTaxFlag()!=null && shd.getProfessionalTaxFlag()==1){
							indexNo=i;
						}
						if(shd.getPfEmployeeFlag()!=null && shd.getPfEmployeeFlag()==1){
							pfNo=i;
						}
						if(shd.getEsiEmployeeFlag()!=null && shd.getEsiEmployeeFlag()==1){
							esiNo=i;
						}
						if(shd.getSalaryHeadCode().equalsIgnoreCase("PF"))
						 {
						  	pfFlag=i;
						 }
					}
		    if(employeeDTOU.getPfFlag() !=null && employeeDTOU.getPfFlag()==1 && pfFlag==i)
		       {
			   if(salaryDetDTOE.get(0).getAmount()!=null && salaryDetDTOE.get(0).getAmount()>15000)
				 {
				  double bsic=(15000*24)/100;
				  employee.getEmployeeDTO().getEmployeeSalaryDetDTOListDe().get(i).setAmount(bsic);
				  }
			   else{employee.getEmployeeDTO().getEmployeeSalaryDetDTOListDe().get(i).setAmount(amount);}	
			  }
		   else if(employeeDTOU.getPfFlag() !=null && employeeDTOU.getPfFlag()==0 && pfFlag==i)
			  { employee.getEmployeeDTO().getEmployeeSalaryDetDTOListDe().get(i).setAmount(0.0);}
		   else{employee.getEmployeeDTO().getEmployeeSalaryDetDTOListDe().get(i).setAmount(amount);}
			  i++;
			 }
		   }
			// earning total
			List<EmployeeSalaryDetDTO> salaryDetDTOE1 = employeeDTOU.getEmployeeSalaryDetDTOList();
			List<EmployeeSalaryDetDTO> salaryDetDTOD1 = employeeDTOU.getEmployeeSalaryDetDTOListDe();
			double eamount = 0.0;
			double damount = 0.0;
			for (EmployeeSalaryDetDTO obj : salaryDetDTOE1) {
				eamount = eamount + obj.getAmount();
			}
			if(eamount >6500 && eamount <15000)
			{
				double esiAmt=(eamount*6.5)/100;
				employee.getEmployeeDTO().getEmployeeSalaryDetDTOListDe().get(1).setAmount(esiAmt);
			}else{employee.getEmployeeDTO().getEmployeeSalaryDetDTOListDe().get(1).setAmount(0.0);}
			
			//Get Professional Tax amount from Professional tax form........
			/*Double netamnt=(eamount - damount)*12;
			ProfessionalTaxOutputMessage taxOutputMessage=professionalTaxService.findAllProfessionalTax();
			List<ProfessionalTaxDTO> pList=taxOutputMessage.getProfessionalTaxDTOList();
			Double dedctAmnt=0.0;
			
			for(int i=0;i<pList.size();i++){
				ProfessionalTaxDTO pDTO=pList.get(i);
				if(netamnt>=pDTO.getFromAmount() && netamnt<=pDTO.getToAmount()){
					dedctAmnt=Double.parseDouble(pDTO.getDeductAmount());
				}}	
			employee.getEmployeeDTO().getEmployeeSalaryDetDTOListDe().get(indexNo).setAmount(dedctAmnt);
		*/	//End......
			
		
				
			for (EmployeeSalaryDetDTO obj : salaryDetDTOD1) {
				damount = damount + Math.abs(obj.getAmount());
			}
			
			employeeDTOU.setEamount(eamount);
			employeeDTOU.setDamount(damount);
			
			employeeDTOU.setTotalAmount(eamount - damount);
			SalaryHeadSearchCriteriaDTO searchCriteria = new SalaryHeadSearchCriteriaDTO();
			searchCriteria.setHeadType("E");
			SalaryHeadOutputMessage salaryHeadOutputMessage = null;
			SalaryHeadInputMessage salaryHeadInputMessage = new SalaryHeadInputMessage();
			salaryHeadInputMessage.setSearchCriteria(searchCriteria);
			salaryHeadOutputMessage = salaryHeadService.search(salaryHeadInputMessage);
			List<SalaryHeadDTO> list = (ArrayList<SalaryHeadDTO>) salaryHeadOutputMessage
					.getSalaryHeadDTOList();
			logger.debug(" list------- :-" + list);
			// logger.debug("----salary head name list------- :-"+list.get(0).getBaseHeads().get(0).getSalaryHeadName());
			mav.put("salaryHeadListE", list);

			salaryHeadOutputMessage = null;
			searchCriteria.setHeadType("D");
			salaryHeadInputMessage.setSearchCriteria(searchCriteria);
			salaryHeadOutputMessage = salaryHeadService.search(salaryHeadInputMessage);
			List<SalaryHeadDTO> list1 = (ArrayList<SalaryHeadDTO>) salaryHeadOutputMessage.getSalaryHeadDTOList();
			logger.debug(" list------- :-" + list1);
			// logger.debug("----salary head name list------- :-"+list.get(0).getBaseHeads().get(0).getSalaryHeadName());
			employee.setScript("");
			employee.setEmployeeDTO(employeeDTOU);
			mav.put("salaryHeadListD", list1);
			mav.put("employeeForm", employee);
			model.addAttribute("step2", "2");
			model.addAttribute("step3", "3");
			model.addAttribute("step4", "4");
			model.put("employeeDTOA", employeeDTOU);
			model.put("employeeDTOU", employeeDTOU);
		
		ErrorDTO errorDTO=new ErrorDTO();
		//errorDTO.setErrorMsg("Successfully Record Updated !!!");
		mav.put("error", errorDTO);
		return mav;
	}

	@RequestMapping(value = "/getEmpCityBy_id", method = RequestMethod.GET)
	public @ResponseBody
	CityDTO getCityById(@RequestParam("id") Integer CityId) {
		logger.info("Party Id=" + CityId);
		CityInputMessage cityInputMessage = new CityInputMessage();
		CityDTO cityDTO = new CityDTO();
		cityDTO.setCityId(CityId);
		cityInputMessage.setCityDTO(cityDTO);
		CityOutputMessage cityOutputMessage = cityService
				.findCityById(cityInputMessage);

		ArrayList<CityDTO> list = (ArrayList<CityDTO>) cityOutputMessage
				.getCityDTOList();

		if (list.size() == 1) {
			cityDTO = list.get(0);
			// employeeForm.getEmployeeDTO().setCity(partyDTO);
		}
		logger.info("cityDTO :" + cityDTO);
		logger.info("cityDTO :"
				+ cityDTO.getAreaDTO().getRegionDTO().getStateDTO()
						.getStateName());
		return cityDTO;

	}

	private Double baseCalculation(String id,List<EmployeeSalaryDetDTO> salaryDetDTOE,List<EmployeeSalaryDetDTO> salaryDetDTOD) {
		Double amount = 0.0;
		SalaryHeadDTO salaryHeadDTO = new SalaryHeadDTO();
		salaryHeadDTO.setSalaryHeadId(Integer.parseInt(id));
		SalaryHeadOutputMessage salaryHeadOutputMessage = null;
		SalaryHeadInputMessage salaryHeadInputMessage = new SalaryHeadInputMessage();
		salaryHeadInputMessage.setSalaryHeadDTO(salaryHeadDTO);
		salaryHeadOutputMessage = salaryHeadService.findSalaryHeadById(salaryHeadInputMessage);
		if (salaryHeadOutputMessage != null && salaryHeadOutputMessage.getSalaryHeadDTOList() != null && salaryHeadOutputMessage.getSalaryHeadDTOList().size() > 0) {
			salaryHeadDTO = salaryHeadOutputMessage.getSalaryHeadDTOList().get(0);
			
			if (salaryHeadDTO.getBaseHeads() != null && salaryHeadDTO.getBaseHeads().size() > 0) {
				for (SalaryHeadDTO obj : salaryHeadDTO.getBaseHeads()) {
					try{
					amount = amount+ (salaryHeadDTO.getBaseHeadPer() * baseCalculation(Integer.toString(obj.getSalaryHeadId()),salaryDetDTOE, salaryDetDTOD)) / 100;
					}catch (Exception e) {
						e.printStackTrace();
					}
					}
			} else {try{
				boolean flag = false;
				for (EmployeeSalaryDetDTO obj : salaryDetDTOE) {
					if (obj.getSalaryId().equals(id)) {
						double value = obj.getAmount();
						amount = value;
						flag = true;
						break;
					}
				}
				if (!flag) {
					for (EmployeeSalaryDetDTO obj : salaryDetDTOD) {
						if (obj.getSalaryId().equals(id)) {
							double value = obj.getAmount();
							amount = value;
							break;
						}
					}
				}
			}catch(Exception e)
			{e.printStackTrace();
			}
			}
		}
		System.out.println("AMOUNCT......................"+amount);
		return amount;
	}

	@ModelAttribute("qualificationes")
	public List<MastersDTO> qualificationesList() {
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		mastersInputMessage.setFormId(4);
		MastersOutputMessage mastersOutputMessage = mastersService
				.findFormById(mastersInputMessage);
		return mastersOutputMessage.getMastersDTOList();
	}

	@ModelAttribute("employeeType")
	public List<MastersDTO> employeeTypeList() {
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		mastersInputMessage.setFormId(5);
		MastersOutputMessage mastersOutputMessage = mastersService
				.findFormById(mastersInputMessage);
		return mastersOutputMessage.getMastersDTOList();
	}

	@ModelAttribute("Departmentes")
	public List<MastersDTO> departmentList() {
		List<MastersDTO> items = new ArrayList<MastersDTO>();
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		mastersInputMessage.setFormId(8);
		MastersOutputMessage mastersOutputMessage = mastersService
				.findFormById(mastersInputMessage);
		items.add(new MastersDTO());
		for (MastersDTO obj : mastersOutputMessage.getMastersDTOList()) {
			items.add(obj);
		}
		return items;
	}


	@RequestMapping(value = "/getSubdepartment", method = RequestMethod.POST)
	public @ResponseBody
	List<MastersDTO> getSubdepartment(
			@ModelAttribute(value = "code") Integer departmentCode, Model model) {
		System.out.println("call = " + departmentCode);
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		MastersDTO mastersDTO = new MastersDTO();
		mastersDTO.setMastersId(departmentCode);
		mastersInputMessage.setMastersDTO(mastersDTO);
		MastersOutputMessage mastersOutputMessage = mastersService
				.findMastersById(mastersInputMessage);
		System.out.println("id =="
				+ mastersOutputMessage.getMastersDTOList().get(0)
						.getMastersId());
		mastersDTO = new MastersDTO();
		mastersDTO.setDeptId(mastersOutputMessage.getMastersDTOList().get(0)
				.getMastersId());
		mastersDTO.setFormId(9);
		mastersInputMessage.setMastersDTO(mastersDTO);

		mastersOutputMessage = mastersService
				.findSubdepartment(mastersInputMessage);
		System.out.println("sai ram "
				+ mastersOutputMessage.getMastersDTOList().size());
		model.addAttribute("subDepartment",
				mastersOutputMessage.getMastersDTOList());
		return mastersOutputMessage.getMastersDTOList();

	}

	@ModelAttribute("designation")
	public List<MastersDTO> DesignationList() {
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		mastersInputMessage.setFormId(7);
		MastersOutputMessage mastersOutputMessage = mastersService
				.findFormById(mastersInputMessage);
		return mastersOutputMessage.getMastersDTOList();
	}

	@ModelAttribute("shift")
	public List<MastersDTO> shiftList() {
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		mastersInputMessage.setFormId(11);
		MastersOutputMessage mastersOutputMessage = mastersService
				.findFormById(mastersInputMessage);
		return mastersOutputMessage.getMastersDTOList();
	}

	@ModelAttribute("grade")
	public List<MastersDTO> gradeList() {
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		mastersInputMessage.setFormId(6);
		MastersOutputMessage mastersOutputMessage = mastersService
				.findFormById(mastersInputMessage);
		return mastersOutputMessage.getMastersDTOList();
	}

	@ModelAttribute("cast")
	public List<MastersDTO> castList() {
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		mastersInputMessage.setFormId(1);
		MastersOutputMessage mastersOutputMessage = mastersService
				.findFormById(mastersInputMessage);
		return mastersOutputMessage.getMastersDTOList();
	}

	@ModelAttribute("religion")
	public List<MastersDTO> religionList() {
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		mastersInputMessage.setFormId(2);
		MastersOutputMessage mastersOutputMessage = mastersService
				.findFormById(mastersInputMessage);
		return mastersOutputMessage.getMastersDTOList();
	}

	@ModelAttribute("Language")
	public List<MastersDTO> languageList() {
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		mastersInputMessage.setFormId(3);
		MastersOutputMessage mastersOutputMessage = mastersService
				.findFormById(mastersInputMessage);
		return mastersOutputMessage.getMastersDTOList();
	}

	@ModelAttribute("cityList")
	public List<CityDTO> cityList() {

		CityOutputMessage cityOutputMessage = cityService.findAllCityes();
		return cityOutputMessage.getCityDTOList();
	}

	@ModelAttribute("leaveList")
	public List<LeaveTypeMastDTO> leaveList() {

		LeaveTypeMastOutputMessage leaveTypeMastOutputMessage = leaveTypeMastService.findAllLeaveTypeMasts();
		return leaveTypeMastOutputMessage.getLeaveTypeMastDTOList();
	}

}
