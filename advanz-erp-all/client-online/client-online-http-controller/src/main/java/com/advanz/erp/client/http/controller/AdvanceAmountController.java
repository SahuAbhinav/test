package com.advanz.erp.client.http.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.AdvanceAmountForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.AdvanceAmountDTO;
import com.advanz.erp.masters.model.EmployeeDTO;
import com.advanz.erp.masters.model.ItemDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.msg.AdvanceAmountInputMessage;
import com.advanz.erp.masters.model.msg.AdvanceAmountOutputMessage;
import com.advanz.erp.masters.model.msg.EmployeeInputMessage;
import com.advanz.erp.masters.model.msg.EmployeeOutputMessage;
import com.advanz.erp.masters.model.msg.ItemInputMessage;
import com.advanz.erp.masters.model.msg.ItemOutMessage;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.service.business.IAdvanceAmountService;
import com.advanz.erp.masters.service.business.IEmployeeService;
import com.advanz.erp.masters.service.business.IMastersService;
@Controller
public class AdvanceAmountController extends BaseController
{
 @Autowired
 IAdvanceAmountService advanceAmountService;	
 @Autowired
 IEmployeeService employeeService;
 
 @Autowired
 IMastersService mastersService;
 @RequestMapping(value="/advanceAmountList",method=RequestMethod.GET)
 public ModelAndView findList(@ModelAttribute("advanceAmountForm")AdvanceAmountForm advanceAmountForm,@RequestParam(value="menuId",required=false) String menuId,HttpSession session)
 {
	 if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
	 System.out.println("Advance.......................");
	 ModelAndView mav=new ModelAndView();
	 mav.setViewName("advanceAmount_list");
	 AdvanceAmountOutputMessage outputMessage=advanceAmountService.loadAllList();
	 List<AdvanceAmountDTO> list=outputMessage.getAdvanceAmountDTOList();
	 mav.addObject("advanceAmountForm",advanceAmountForm);
	 mav.addObject("advanceAmountList", list); 
	 return mav;
 }
 
 @RequestMapping(value="/advanceAmountSearch",method=RequestMethod.POST)
 public ModelAndView search(@ModelAttribute("advanceAmountForm") AdvanceAmountForm advanceAmountForm)
 {
	 Date toDate=advanceAmountForm.getToDate();
	 Date fromDate=advanceAmountForm.getFromDate();
	 String empName=advanceAmountForm.getEmployeeName();
	 String designation=advanceAmountForm.getDesignation();
	 String trType=advanceAmountForm.getTransactionType();
	 AdvanceAmountDTO advanceAmountDTO=new AdvanceAmountDTO();
	 advanceAmountDTO.setToDate(toDate);
	 advanceAmountDTO.setFromDate(fromDate);
	 advanceAmountDTO.setEmployeeName(empName);
	 advanceAmountDTO.setEmployeeDesignation(designation);
	 advanceAmountDTO.setTransactionType(trType);
	 
	 AdvanceAmountInputMessage message=new AdvanceAmountInputMessage();
	 message.setAdvanceAmountDTO(advanceAmountDTO);
	 ModelAndView mav=new ModelAndView();
	 mav.setViewName("advanceAmount_list");
	 AdvanceAmountOutputMessage outputMessage=advanceAmountService.search(message);
	 List<AdvanceAmountDTO> list=outputMessage.getAdvanceAmountDTOList();
	 mav.addObject("advanceAmountForm",advanceAmountForm);
	 mav.addObject("advanceAmountList", list);
	 return mav;
 }
 

 @RequestMapping(value="/new_advance",method=RequestMethod.GET)
 public ModelAndView newAdvanceAmount(@ModelAttribute("advanceAmountForm") AdvanceAmountForm advanceAmountForm)
 {
	if(advanceAmountForm==null)
	 {
	  advanceAmountForm=new AdvanceAmountForm();
	 }
	AdvanceAmountDTO amountDTO=new AdvanceAmountDTO();
	AdvanceAmountOutputMessage outputMessage=advanceAmountService.getTransactionId();
	Integer transactionId=outputMessage.getTransactionId();
	amountDTO.setSno(transactionId);
	advanceAmountForm.setAdvanceAmountDTO(amountDTO);
	ModelAndView mav=new ModelAndView();
	mav.setViewName("advanceAmount_Add");
	mav.addObject("advanceAmountForm", advanceAmountForm);
	return mav;
 }
 
 @RequestMapping(value = "/advance_save", method = RequestMethod.POST)
 public String save(@ModelAttribute("advanceAmountForm") AdvanceAmountForm advanceAmountForm, Model model)
 {	AdvanceAmountInputMessage inputMessage=new AdvanceAmountInputMessage();
 	AdvanceAmountDTO advDto=advanceAmountForm.getAdvanceAmountDTO();
 	String succ=advanceAmountForm.getSucc();
 	
	 if(succ!=null && succ.equalsIgnoreCase("update"))
	 {
		 inputMessage.setAdvanceAmountDTO(advDto);
		 advanceAmountService.update(inputMessage);
	 }
	 else
	 {
		 inputMessage.setAdvanceAmountDTO(advDto);
		 AdvanceAmountOutputMessage outputMessage=advanceAmountService.findbyEmployeeIdAndCurrntMonth(inputMessage);
	     Integer trNo=outputMessage.getTransactionId();
	     if(trNo!=null && trNo==0)
	     {	 advDto.setSno(null);
	    	 inputMessage.setAdvanceAmountDTO(advDto);
		     advanceAmountService.save(inputMessage);
	     }else{
	    	    			
	    	 ErrorDTO errorDTO=new ErrorDTO("1","This month one record already inserted.");
	    	 errorDTO.setErrorMsg("This month one record already inserted.");
	    	 model.addAttribute("errorList",errorDTO);
	    	 return "advanceAmount_Add";
	     }
	   
	 }
	 return "redirect:/advanceAmountList";
 }
 
 @RequestMapping(value="/findBy_Id",method=RequestMethod.GET)
 public ModelAndView findById(@ModelAttribute("advanceAmountForm") AdvanceAmountForm advanceAmountForm,@RequestParam("sno") Integer sno,@RequestParam("opr")String opr)
 {
	 AdvanceAmountDTO dto=new AdvanceAmountDTO();
	 dto.setSno(sno);
	 AdvanceAmountInputMessage message=new AdvanceAmountInputMessage();
	 message.setAdvanceAmountDTO(dto);
	 ModelAndView mav=new ModelAndView();
	 mav.setViewName("advanceAmount_Add");
	 AdvanceAmountOutputMessage outputMessage=advanceAmountService.findById(message);
	 List<AdvanceAmountDTO> list=outputMessage.getAdvanceAmountDTOList();
	 if(list.size()>0)
	 {
		 advanceAmountForm.setAdvanceAmountDTO(list.get(0));
	 }
	 mav.addObject("advanceAmountForm",advanceAmountForm);
	 mav.addObject("opr",opr);
	 return mav;
 }
 
 @RequestMapping(value="/delete_advance",method=RequestMethod.GET)
 public String delete(@ModelAttribute("advanceAmountForm") AdvanceAmountForm advanceAmountForm,@RequestParam("sno") Integer sno)
 {
	 AdvanceAmountDTO dto=new AdvanceAmountDTO();
	 dto.setSno(sno);
	 AdvanceAmountInputMessage message=new AdvanceAmountInputMessage();
	 message.setAdvanceAmountDTO(dto);
	 advanceAmountService.delete(message);
	 return "redirect:/advanceAmountList";
 }
 
 @RequestMapping(value = "/employee_detail", method = RequestMethod.POST)
 @ResponseBody EmployeeDTO employeeDetl(@RequestParam("employeeId") Integer employeeId)
 {
	 System.out.println("Employee Id................"+employeeId);
	 EmployeeInputMessage message=new EmployeeInputMessage();
	 EmployeeDTO empDto=new EmployeeDTO();
	 empDto.setEmployeeId(employeeId);
	 message.setEmployeeDTO(empDto);
	 EmployeeOutputMessage outputMessage= employeeService.findEmployeeById(message);
	 JsonResponse jsonResponse=new JsonResponse();
 	 jsonResponse.setStatus("SUCCESS");
 	 jsonResponse.setResult(outputMessage.getEmployeeDTOList());
     return outputMessage.getEmployeeDTOList().get(0);
 }
 
 @RequestMapping(value = "/find_by_empId", method = RequestMethod.POST)
 @ResponseBody JsonResponse advanceAmt(@RequestParam("employeeId") Integer employeeId)
 {
	 System.out.println("Employee Id................"+employeeId);
	 AdvanceAmountDTO dto=new AdvanceAmountDTO();
	 EmployeeDTO employeeDTO=new EmployeeDTO();
	 employeeDTO.setEmployeeId(employeeId);
	 dto.setEmployeeDTO(employeeDTO);
	 AdvanceAmountInputMessage message=new AdvanceAmountInputMessage();
	 message.setAdvanceAmountDTO(dto);
	 AdvanceAmountOutputMessage outputMessage=advanceAmountService.findByEmployeeId(message);
	 List<AdvanceAmountDTO> list=outputMessage.getAdvanceAmountDTOList();
	 JsonResponse jsonResponse=new JsonResponse();
 	 if(list!=null && list.size()>0)
	 {
		 jsonResponse.setResult(list.get(0));
		 jsonResponse.setStatus("SUCCESS");
		 return jsonResponse;
	 }
 	jsonResponse.setStatus("UNSUCCESS");
	return jsonResponse; 
	}

 
 @ModelAttribute("designation")
	public List<MastersDTO> shiftList() {
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		mastersInputMessage.setFormId(7);
		MastersOutputMessage mastersOutputMessage = mastersService.findFormByIdForMelterLog(mastersInputMessage);
		return mastersOutputMessage.getMastersDTOList();
	}
 
 @ModelAttribute("employeeList")
 public List<EmployeeDTO> loadEmployee()
 {
	 EmployeeOutputMessage outputMessage=employeeService.findAllEmployee();
	 return outputMessage.getEmployeeDTOList();
 }

}
