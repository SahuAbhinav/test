package com.advanz.erp.client.http.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.DataUtility;
import com.advanz.erp.client.http.controller.form.IssueMasterForm;
import com.advanz.erp.client.http.controller.form.SalaryMasterForm;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.IndentMasterDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.SalaryLeaveDTO;
import com.advanz.erp.masters.model.SalaryMasterDTO;
import com.advanz.erp.masters.model.SalaryTempDTO;
import com.advanz.erp.masters.model.msg.IndentInputMessage;
import com.advanz.erp.masters.model.msg.IndentOutputMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.model.msg.SalaryMasterInputMessage;
import com.advanz.erp.masters.model.msg.SalaryMasterOutputMessage;
import com.advanz.erp.masters.service.business.ICompanyService;
import com.advanz.erp.masters.service.business.IItemService;
import com.advanz.erp.masters.service.business.IMastersService;
import com.advanz.erp.masters.service.business.ISalaryMasterService;

@Controller
@SessionAttributes({ "salaryMasterForm", "branchList", "itemsMap", "opr" })
public class SalaryController extends BaseController {

	private static final Logger logger = LoggerFactory
			.getLogger(SalaryController.class);

	@Autowired
	DataSource dataSource;
	
	@Autowired
	public IItemService itemService;
	
	@Autowired
	public ISalaryMasterService salaryMasterService;

	@Autowired
	public IMastersService mastersService;
	@Autowired
	public ICompanyService companyService;
	@RequestMapping(value = "/get_salary_list")
	public ModelAndView getSalaryList(
	/*@ModelAttribute("salaryMasterForm") SalaryMasterForm salaryMasterForm,*/
	Model model,@RequestParam(value = "menuId", required = false) String menuId,
	HttpSession session,@RequestParam(value="next",required=false) Integer next) {
	if (menuId != null) {
	session.setAttribute("menuId", menuId);
	}
	
	session.removeAttribute("advanceAmtList"); 
	session.removeAttribute("dedectAdvanceAmntList");
	session.removeAttribute("employeeList"); 
	
	
	
	SalaryMasterForm salaryMasterForm=new SalaryMasterForm();
	SalaryMasterDTO salaryDTO=new SalaryMasterDTO();
	logger.info("searchIssue : ");
	List<SalaryMasterDTO> list =salaryMasterService.getSalaryList();
	ModelAndView mav = new ModelAndView("salary-list");
	mav.addObject("salaryList", list);
	mav.addObject("salaryMasterForm", salaryMasterForm);
	return mav;
	}

	
	
	@RequestMapping(value = "/show_new_salary_form", method = RequestMethod.GET)
	public ModelAndView newSalaryMaterial(ModelMap model) {
		SalaryMasterForm salaryMasterForm = new SalaryMasterForm();
		SalaryMasterDTO salaryMasterDTO = new SalaryMasterDTO();
		
		salaryMasterForm.setSalaryMasterDTO(salaryMasterDTO);
		model.put("salaryMasterForm", salaryMasterForm);
		ModelAndView mav = new ModelAndView("salary-detail");
		return mav;
	}

	



	@RequestMapping(value = "/new_salary", method = RequestMethod.GET)
	public ModelAndView newSalary(ModelMap model,HttpSession session) {
	SalaryMasterForm salaryMasterForm = new SalaryMasterForm();
		Map map= salaryMasterService.getEmployeeList();
		SalaryMasterDTO salaryMasterDTO=(SalaryMasterDTO)map.get("salaryMasterDTO");
		try {
			salaryMasterDTO.setSalaryYear(getFinYear());
		} catch (Exception e) {	}
		salaryMasterForm.setSalaryMasterDTO(salaryMasterDTO);
		
		List empList=(List)map.get("header");
		List employeeList =(List)map.get("employeeList");
		session.setAttribute("employeeList",employeeList);
		session.setAttribute("salaryMaster",salaryMasterDTO);
        model.put("empList", empList);
        model.put("employeeList", employeeList);
        
		model.put("salaryMasterForm", salaryMasterForm);
		model.put("deptTypeList", getDeptTypeList());
		model.put("opr", "N");
		
		List advanceAmtList=	salaryMasterDTO.getBalAdvanceList();
		session.setAttribute("advanceAmtList",advanceAmtList); 
		List dedectAdvanceAmntList =salaryMasterDTO.getDedectAdvanceAmntList();
		session.setAttribute("dedectAdvanceAmntList",dedectAdvanceAmntList);
		
		ModelAndView mav = new ModelAndView("salary-detail");
		mav.addObject("salaryMasterForm", salaryMasterForm);
		return mav;
	}

	@RequestMapping(value = "/find_salary_by_date", method = RequestMethod.GET)
	public ModelAndView salaryCalByMonthAndYear(ModelMap model,HttpSession session,
			@RequestParam(value="mothName",required=false) String mothName,
			@RequestParam(value="salaryYearInt",required=false) Integer year) {
		SalaryMasterForm salaryMasterForm = new SalaryMasterForm();
	    Map map= salaryMasterService.getEmployeeListByMonthName(mothName,year);
		//Map map= salaryMasterService.getEmployeeList();
		SalaryMasterDTO salaryMasterDTO=(SalaryMasterDTO)map.get("salaryMasterDTO");
		/*try {
			salaryMasterDTO.setSalaryYear(getFinYear());
		} catch (Exception e) {	}*/
		salaryMasterForm.setSalaryMasterDTO(salaryMasterDTO);
		
		List empList=(List)map.get("header");
		List employeeList =(List)map.get("employeeList");
		session.setAttribute("employeeList",employeeList);
		session.setAttribute("salaryMaster",salaryMasterDTO);
        model.put("empList", empList);
        model.put("employeeList", employeeList);
        
		model.put("salaryMasterForm", salaryMasterForm);
		model.put("deptTypeList", getDeptTypeList());
		model.put("opr", "N");
		
		List advanceAmtList=	salaryMasterDTO.getBalAdvanceList();
		session.setAttribute("advanceAmtList",advanceAmtList); 
		List dedectAdvanceAmntList =salaryMasterDTO.getDedectAdvanceAmntList();
		session.setAttribute("dedectAdvanceAmntList",dedectAdvanceAmntList);
		
		ModelAndView mav = new ModelAndView("salary-detail");
		mav.addObject("salaryMasterForm", salaryMasterForm);
		return mav;
	}
	
	

	@RequestMapping(value = "/get_employee_by_deaprtment", method = RequestMethod.GET)
	public ModelAndView getEmployeeByDepartment(ModelMap model,@RequestParam(value="departmentId",required=false) Integer departmentId,@RequestParam(value="mothName",required=false) String mothName,HttpSession session) {

		SalaryMasterForm salaryMasterForm = new SalaryMasterForm();
		SalaryMasterDTO masterDTO=new SalaryMasterDTO();
		try {
			Map map= salaryMasterService.getEmployeeListByDepartment(departmentId,mothName);
			List empList=(List)map.get("header");
			List employeeList =(List)map.get("employeeList");
			session.setAttribute("employeeList",employeeList);  
			model.put("empList", empList);
			model.put("employeeList", employeeList);
			masterDTO=(SalaryMasterDTO)map.get("masterDTO");
			
			List advanceAmtList=	masterDTO.getBalAdvanceList();
			session.setAttribute("advanceAmtList",advanceAmtList); 
			List dedectAdvanceAmntList =masterDTO.getDedectAdvanceAmntList();
			session.setAttribute("dedectAdvanceAmntList",dedectAdvanceAmntList);

			session.setAttribute("salaryMaster",masterDTO);
			salaryMasterForm.setSalaryMasterDTO(masterDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.put("salaryMasterForm", salaryMasterForm);
		model.put("deptTypeList", getDeptTypeList());
		model.put("opr", "N");
		ModelAndView mav = new ModelAndView("salary-detail");
		mav.addObject("salaryMasterForm", salaryMasterForm);
		return mav;
	}
	
	
	@RequestMapping(value = "/backto_salary", method = RequestMethod.GET)
	public ModelAndView backToFinishGood(
			@ModelAttribute("issueMasterForm") IssueMasterForm IssueMasterForm) {
		// calcSalesOrder(salesOrderMasterForm.getSalesOrderMasterDTO());
		ModelAndView mav = new ModelAndView("salary-detail");
		return mav;
	}

	@RequestMapping(value = "/saveSalary", method = RequestMethod.POST)
	public String saveSalary(@ModelAttribute("salaryMasterForm") SalaryMasterForm salaryMasterForm,
			@RequestParam(value="opration",required=false) String opration,	ModelMap model,HttpSession session) {
		
		SalaryMasterDTO salaryMasterDTO = salaryMasterForm.getSalaryMasterDTO();
		List<SalaryTempDTO> l =salaryMasterDTO.getTemp();
	   
		//for(int i=0;i<l.size();i++){
		SalaryTempDTO d=l.get(0);
		List<Double> tempList =	d.getTempList();
		for(int j=0;j<tempList.size();j++){
			Double as=tempList.get(j);
		
		}
		
		List<SalaryTempDTO> stdList =salaryMasterDTO.getHeadPayableList();
	   
		//for(int i=0;i<l.size();i++){
		SalaryTempDTO std=	stdList.get(0);
		List<Double> headList =	std.getSalaryHeadPayableList();
		for(int j=0;j<headList.size();j++){
			Double as=headList.get(j);
		
		}
		//}
		
		
		if(salaryMasterDTO.getApprovedFlag()==null){
			salaryMasterDTO.setApprovedFlag(0);
		}
		
		salaryMasterDTO.setDepartmentId(salaryMasterForm.getSalaryMasterDTO().getDepartmentId());
		SalaryMasterInputMessage salaryMasterInputMessage = new SalaryMasterInputMessage();
		salaryMasterInputMessage.setSalaryMasterDTO(salaryMasterDTO);
		if(salaryMasterDTO.getSalaryTranAutoNo()!=null && salaryMasterDTO.getSalaryTranAutoNo()>0){
		salaryMasterService.deleteSalaryMaster(salaryMasterInputMessage);
		}
		salaryMasterDTO.setSalaryTranAutoNo(null);
		salaryMasterDTO.setMinDate(salaryMasterForm.getSalaryMasterDTO().getMinDate());
		salaryMasterDTO.setMaxDate(salaryMasterForm.getSalaryMasterDTO().getMaxDate());
		System.out.println("MinDate............"+salaryMasterForm.getSalaryMasterDTO().getMinDate());
		System.out.println("MaxDate............"+salaryMasterForm.getSalaryMasterDTO().getMaxDate());
		salaryMasterInputMessage.setSalaryMasterDTO(salaryMasterDTO);
		salaryMasterService.createSalaryMaster(salaryMasterInputMessage);
		
		model.addAttribute("salaryMasterForm", salaryMasterForm);
		return "redirect:/get_salary_list";
	    }

	@RequestMapping(value = "/get_salary", method = RequestMethod.GET)
	public ModelAndView getIndentData(@RequestParam("salaryTranAutoNo") Integer id,@RequestParam("opr") String opr,
			@ModelAttribute("salaryMasterForm")SalaryMasterForm salaryMasterForm,ModelMap model,HttpSession session) {
		//SalaryMasterForm salaryMasterForm = new SalaryMasterForm();

		SalaryMasterOutputMessage salaryOutputMessage = null;
		if (id != null && !id.equals(0)) {
			SalaryMasterInputMessage salaryMasterInputMessage = new SalaryMasterInputMessage();
			SalaryMasterDTO salaryMasterDTO = new SalaryMasterDTO();
			salaryMasterDTO.setSalaryTranAutoNo(id);
			salaryMasterInputMessage.setSalaryMasterDTO(salaryMasterDTO);
			salaryOutputMessage=salaryMasterService.findSalaryMasterById(salaryMasterInputMessage);
		}
		
		SalaryMasterDTO masterDTO=new SalaryMasterDTO();
		
		try {
			Map map= salaryOutputMessage.getMap();
			List empList=(List)map.get("header");
			List employeeList =(List)map.get("employeeList");
			session.setAttribute("employeeList",employeeList); 
			
			masterDTO=(SalaryMasterDTO)map.get("salaryMasterDTO2");
			masterDTO.setSalaryTranAutoNo(id);
			List advanceAmtList=	masterDTO.getBalAdvanceList();
			session.setAttribute("advanceAmtList",advanceAmtList); 
			List dedectAdvanceAmntList =masterDTO.getDedectAdvanceAmntList();
			session.setAttribute("dedectAdvanceAmntList",dedectAdvanceAmntList);
			
			model.put("empList", empList);
			model.put("employeeList", employeeList);
			model.put("advanceAmtList", advanceAmtList);
			
			Integer deptId=(Integer)map.get("deptId");
			masterDTO.setDepartmentId(deptId);
			salaryMasterForm.setSalaryMasterDTO(masterDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.setAttribute("salaryMaster",masterDTO);
		model.put("opr", opr);
		model.put("deptTypeList", getDeptTypeList());
		ModelAndView mav = new ModelAndView("salary-detail");
		
		mav.addObject("salaryMasterForm",salaryMasterForm);
		// mav.addObject("partyList",partyList());
		return mav;

	}

	@RequestMapping(value = "/remove_salary", method = RequestMethod.GET)
	public String removeSalary(@RequestParam("id") Integer id,
			ModelMap model) {
		logger.info("Removing..........salary = " + id);
		SalaryMasterOutputMessage salaryOutputMessage = null;
		if (id != null && !id.equals(0)) {
			SalaryMasterInputMessage salaryInputMessage = new SalaryMasterInputMessage();
			SalaryMasterDTO salaryMasterDTO = new SalaryMasterDTO();
			salaryMasterDTO.setSalaryTranAutoNo(id);
			salaryMasterDTO.setModifiedUserId(getCreatedUserId());
			salaryInputMessage.setSalaryMasterDTO(salaryMasterDTO);
			salaryMasterService.deleteSalaryMaster(salaryInputMessage);
		}
		String succ="Del";
		model.put("deptTypeList", getDeptTypeList());
		model.addAttribute("succ", succ);
		return "redirect:/get_salary_list";
	}

	public List<MastersDTO> getDeptTypeList() {
		MastersOutputMessage outMsg = null;
		MastersInputMessage inMsg = new MastersInputMessage();
		inMsg.setFormId(8);
		outMsg = mastersService.findFormById(inMsg);
		List<MastersDTO> deptTypeList = outMsg.getMastersDTOList();
		return deptTypeList;
	}

	@RequestMapping(value = "/checkEntryExiste", method = RequestMethod.POST)
	public @ResponseBody JsonResponse checkEntryExiste(@RequestParam("departmentId") Integer departmentId,@RequestParam("mothName") String mothName,ModelMap model) {
	JsonResponse res = new JsonResponse();
	Boolean flag=salaryMasterService.getSalaryByDepartmentIdAndMonth(departmentId, mothName);
		
	//To check previous month's salary is approved or not
	String approved="approved";
	Date salaryGenaratingDate= companyService.getSalaryGenaratingDate();
	String monthNameInCompany= null;
	try{
		monthNameInCompany=DataUtility.getMonthName(salaryGenaratingDate.getMonth());
	}catch (Exception e) {}
	
	//If month is first month to generate salary then no need to check it is approve or not
	if(monthNameInCompany.equalsIgnoreCase(mothName)){
		approved="approved";
	}else{
	int monthNumber= DataUtility.getMonthNumber(mothName);
		if(monthNumber==0){
		monthNumber=11;
		}else{
		monthNumber=monthNumber-1;
		}
	    String previousSalaryMonthName= DataUtility.getMonthName(monthNumber);
	   Integer i= salaryMasterService.getApprovedFlag(previousSalaryMonthName);
		if(i==0){
			approved="unApproved";
		}if(i==1){
			approved="approved";
		}}
	res.setStatus(approved);
	res.setResult(flag);
	return res;
	}
	
	/*@RequestMapping(value = "/checkSalaryApproved", method = RequestMethod.POST)
	public @ResponseBody JsonResponse checkSalaryApproved(@RequestParam("mothName") String mothName,ModelMap model) {
	JsonResponse res = new JsonResponse();
	String approved="approved";
	Date salaryGenaratingDate= companyService.getSalaryGenaratingDate();
	String monthNameInCompany= null;
	try{
		monthNameInCompany=DataUtility.getMonthName(salaryGenaratingDate.getMonth());
	}catch (Exception e) {}
	
	//If month is first month to generate salary then no need to check it is approve or not
	if(monthNameInCompany.equalsIgnoreCase(mothName)){
		approved="approved";
	}else{
	int monthNumber= DataUtility.getMonthNumber(mothName);
		if(monthNumber==0){
		monthNumber=11;
		}else{
		monthNumber=monthNumber-1;
		}
	    String previousSalaryMonthName= DataUtility.getMonthName(monthNumber);
	   Integer i= salaryMasterService.getApprovedFlag(previousSalaryMonthName);
		if(i==0){
			approved="unApproved";
		}if(i==1){
			approved="approved";
		}}
	res.setResult(approved);
	return res;
	}*/
}
