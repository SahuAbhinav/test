package com.advanz.erp.client.http.controller;

import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.SalaryNoteForm;
import com.advanz.erp.masters.model.EmployeeDTO;
import com.advanz.erp.masters.model.SalaryNoteDTO;
import com.advanz.erp.masters.model.msg.EmployeeOutputMessage;
import com.advanz.erp.masters.model.msg.SalaryNoteInputMessage;
import com.advanz.erp.masters.model.msg.SalaryNoteOutputMessage;
import com.advanz.erp.masters.service.business.IEmployeeService;
import com.advanz.erp.masters.service.business.ISalaryNoteService;

@Controller
public class SalaryNoteController extends BaseController{

	@Autowired 
	private ISalaryNoteService iSalaryNoteService;
	@Autowired
	private IEmployeeService employeeService;
	
	@RequestMapping(value="/salaryNoteList",method=RequestMethod.GET)
	public ModelAndView salaryNoteList(@ModelAttribute("salaryNoteForm")SalaryNoteForm salaryNoteForm ,@RequestParam(value="menuId",required=false) String menuId,HttpSession session)
	{
		if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
		SalaryNoteOutputMessage outputMessage=iSalaryNoteService.loadSalaryNote();
		List<SalaryNoteDTO> noteDTOList=outputMessage.getSalaryNoteDTOList();
		ModelAndView mav=new ModelAndView("salary_note_list");
		String succ="Blk";
		if(noteDTOList.equals(null) || noteDTOList.size()==0)
		{
			mav.addObject("succ", succ);
		}
		mav.addObject("salaryNoteList", noteDTOList);
		mav.addObject("salaryNoteForm", salaryNoteForm);
		return mav;
	}
	
	@RequestMapping(value="/newSalaryNote",method=RequestMethod.GET)
	public ModelAndView salaryNewNote(@ModelAttribute("salaryNoteForm")SalaryNoteForm salaryNoteForm )
	{
	   if(salaryNoteForm==null)
	   {
		   salaryNoteForm=new SalaryNoteForm();
	   }
	   SalaryNoteOutputMessage noteOutputMessage=iSalaryNoteService.autoGeneratedNoteID();
	   SalaryNoteDTO salaryNoteDTO=noteOutputMessage.getSalaryNoteDTO();
	   salaryNoteForm.setSalaryNoteDTO(salaryNoteDTO);
	   ModelAndView mav=new ModelAndView("salary_note_detail");
		mav.addObject("employeeList", employeeList());
		mav.addObject("salaryNoteForm", salaryNoteForm);
		return mav;
	}
	
	@RequestMapping(value="/saveSalaryNote",method=RequestMethod.POST)
	public String saveSalaryNewNote(@ModelAttribute("salaryNoteForm")SalaryNoteForm salaryNoteForm,Model model)
	{
		String succ="";
		SalaryNoteInputMessage inputMessage=new SalaryNoteInputMessage();
		SalaryNoteDTO salaryNoteDTO=new SalaryNoteDTO();
		salaryNoteDTO=salaryNoteForm.getSalaryNoteDTO();
		if(salaryNoteDTO.getSno()!=null)
		{
		  List<Integer> checkList=salaryNoteForm.getSalaryNoteDTO().getEmpIdList();
			for( Integer itr: checkList)
			{
				  salaryNoteDTO.setAssignToEmp(itr);
				  inputMessage.setSalaryNoteDTO(salaryNoteDTO);
				  iSalaryNoteService.updateSalaryNote(inputMessage);
				  succ="Up";
			}
		}else{
		  List<Integer> checkList=salaryNoteForm.getSalaryNoteDTO().getEmpIdList();
		 for( Integer itr: checkList)
		   {
			  salaryNoteDTO.setAssignToEmp(itr);
			  inputMessage.setSalaryNoteDTO(salaryNoteDTO);
			  iSalaryNoteService.createSalaryNote(inputMessage);
			  succ="Ad";
		 }}
		model.addAttribute("succ", succ);
	 	return "redirect:/salaryNoteList";
	}
	

	@RequestMapping(value="/getSalaryNote",method=RequestMethod.GET)
	public ModelAndView getSalaryNote(@ModelAttribute("salaryNoteForm")SalaryNoteForm salaryNoteForm,
			@RequestParam("noteId") Integer noteId,@RequestParam("opr") String opr)
	{
	   SalaryNoteInputMessage message=new SalaryNoteInputMessage();
	   SalaryNoteDTO salaryNoteDTO=new SalaryNoteDTO();
	   salaryNoteDTO.setNoteId(noteId);
	   message.setSalaryNoteDTO(salaryNoteDTO);
	   SalaryNoteOutputMessage noteOutputMessage=iSalaryNoteService.findByNoteID(message);
	   List<SalaryNoteDTO> notDtoList=noteOutputMessage.getSalaryNoteDTOList();
	   SalaryNoteDTO noteDTO=notDtoList.get(0);
	   
	   salaryNoteForm.setSalaryNoteDTO(noteDTO);
	   ModelAndView mav=new ModelAndView("salary_note_detail");
	   List<Integer> empIdLst=new ArrayList<Integer>(); 
	   for(int j=0;j<notDtoList.size();j++)
	   {
		   empIdLst.add(notDtoList.get(j).getAssignToEmp());
	    }
	 
	   noteDTO.setEmpIdList(empIdLst);
	   salaryNoteForm.setSalaryNoteDTO(noteDTO);
	   mav.addObject("opr", opr);
	   mav.addObject("employeeList", employeeList());	   
	   mav.addObject("salaryNoteForm", salaryNoteForm);
	   return mav;
	}
	
	@RequestMapping(value="/removeSalaryNote",method=RequestMethod.GET)
	public String removeNote(@RequestParam("noteId") Integer noteId,Model model)
	{
		
	  SalaryNoteInputMessage message=new SalaryNoteInputMessage();
	   SalaryNoteDTO salaryNoteDTO=new SalaryNoteDTO();
	   salaryNoteDTO.setNoteId(noteId);
	   message.setSalaryNoteDTO(salaryNoteDTO);
	   iSalaryNoteService.removeNote(message);
	   String succ="Dl";
		model.addAttribute("succ", succ);
	   return "redirect:/salaryNoteList";
	}
	
	@RequestMapping(value="/searchSalaryNote",method=RequestMethod.POST)
	public ModelAndView searchNote(@ModelAttribute("salaryNoteForm")SalaryNoteForm salaryNoteForm)
	{
	  SalaryNoteDTO salaryNoteDTO=new SalaryNoteDTO();
		Date date= salaryNoteForm.getnDate();
		Integer status=salaryNoteForm.getnStatus();
		salaryNoteDTO.setNoteDate(date);
		salaryNoteDTO.setStatus(status);
		
	  SalaryNoteInputMessage message=new SalaryNoteInputMessage();
	   message.setSalaryNoteDTO(salaryNoteDTO);
	   SalaryNoteOutputMessage noteOutputMessage= iSalaryNoteService.searchNote(message);
	   List<SalaryNoteDTO> salaryList=noteOutputMessage.getSalaryNoteDTOList();
	   ModelAndView mav=new ModelAndView("salary_note_list");
	   String succ="Blk";
		if(salaryList.equals(null) || salaryList.size()==0)
		{mav.addObject("succ", succ);}
		 mav.addObject("salaryNoteList", salaryList);
	   return mav;
	}
	
	@RequestMapping(value="/updateSalaryNote",method=RequestMethod.POST)
	public String updateSalaryNote(@ModelAttribute("salaryNoteForm")SalaryNoteForm salaryNoteForm )
	{
		SalaryNoteInputMessage inputMessage=new SalaryNoteInputMessage();
		SalaryNoteDTO salaryNoteDTO=null;
		
		List<Integer> checkList=salaryNoteForm.getSalaryNoteDTO().getEmpIdList();
		for( Integer itr: checkList)
		{
			salaryNoteDTO=new SalaryNoteDTO();
			  salaryNoteDTO=salaryNoteForm.getSalaryNoteDTO();
			  salaryNoteDTO.setAssignToEmp(itr);
			  inputMessage.setSalaryNoteDTO(salaryNoteDTO);
			  iSalaryNoteService.updateSalaryNote(inputMessage);
		}
	 	return "redirect:/salaryNoteList";
	}
	
	public List<EmployeeDTO> employeeList()
	{
		EmployeeOutputMessage outputMessage=employeeService.findAllActivatedEmployee();
		return outputMessage.getEmployeeDTOList();
	}
	}
