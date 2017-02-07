package com.advanz.erp.client.http.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.advanz.erp.client.http.controller.form.MelterTrollyLogForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.MelterTrollyLogDTO;
import com.advanz.erp.masters.model.msg.MelterTrollyLogInputMessage;
import com.advanz.erp.masters.model.msg.MelterTrollyLogOutputMessage;
import com.advanz.erp.masters.service.business.IMelterTrollyLogService;


@Controller
public class MelterTrollyLogController extends BaseController 
 {
  private Logger logger=LoggerFactory.getLogger(MelterTrollyLogController.class);
  @Autowired
  public IMelterTrollyLogService iMelterTrollyLogService;
  
  @RequestMapping(value="/show_melter_trolly_form",method=RequestMethod.GET)
  public ModelAndView showMelterTrollyLogForm(@ModelAttribute("melterTrollyForm")MelterTrollyLogForm melterTrollyLogForm,@RequestParam(value="menuId",required=false) String menuId,HttpSession session)
  {
		if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
	
	ModelAndView mav=new ModelAndView("melter_trolly_list");
	MelterTrollyLogOutputMessage trollyLogOutputMessage=iMelterTrollyLogService.findAllMelterTrolly();
	List<MelterTrollyLogDTO> list=trollyLogOutputMessage.getMelterTrollyLogDTOList();
	logger.info("Melter Trolly Log Form", list);
	mav.addObject("melterLogTrollyList",list);
	return mav;
  }
   
  @RequestMapping(value="/get_melter_trolly_form",method=RequestMethod.POST)
  public ModelAndView getMelterTrollyForm(@ModelAttribute("melterTrollyForm") MelterTrollyLogForm melterTrollyLogForm,Model model)
  {
	MelterTrollyLogDTO melterTrollyLogDTO=new MelterTrollyLogDTO();
	MelterTrollyLogInputMessage melterTrollyLogInputMessage=new MelterTrollyLogInputMessage();
	MelterTrollyLogOutputMessage melterTrollyLogOutputMessage=null;
	String trollyNumber= melterTrollyLogForm.getTrollyNumber();
	Date logDate=melterTrollyLogForm.getLogDate();
	Date fromDate=melterTrollyLogForm.getFromDate();
	Date toDate=melterTrollyLogForm.getToDate();
	if(fromDate!=null||toDate!=null|| StringUtils.hasText(trollyNumber))
	{
	 melterTrollyLogDTO.setFromDate(fromDate);
	 melterTrollyLogDTO.setToDate(toDate);
	 melterTrollyLogDTO.setTrollyNumber(trollyNumber);
	 melterTrollyLogInputMessage.setMelterTrollyLogDTO(melterTrollyLogDTO);
	 melterTrollyLogOutputMessage=iMelterTrollyLogService.SearchByLogDateOrTrollyNo(melterTrollyLogInputMessage);
	}
	else
	{
	 melterTrollyLogOutputMessage=iMelterTrollyLogService.findAllMelterTrolly();	
	}
	ArrayList<MelterTrollyLogDTO> list=(ArrayList<MelterTrollyLogDTO>) melterTrollyLogOutputMessage.getMelterTrollyLogDTOList();
	ModelAndView mav=new ModelAndView("melter_trolly_list");  
	logger.info("Melter Trolly Log Form for Search"+list);
	mav.addObject("melterLogTrollyList",list);
	String succ="Blk";
	if(list.equals(null) || list.size()==0)
	{
	  model.addAttribute("succ", succ);
	}
	return mav;
  }
  
  
  @RequestMapping(value="/show_new_melter_trolly",method=RequestMethod.GET)
  public ModelAndView getNewMelterTrollyForm(@ModelAttribute("melterTrollyForm") MelterTrollyLogForm melterTrollyLogForm)
  {
   ModelAndView mav=new ModelAndView("melter_trolly_detail");
   if(melterTrollyLogForm!=null)
    {
	 melterTrollyLogForm=new MelterTrollyLogForm();   
    }
    mav.addObject("melterTrollyLogForm",melterTrollyLogForm);	
	return mav;
  }
  
  @RequestMapping(value="/save_melter_trolly_form",method=RequestMethod.POST)
  public String saveMelterLogTrollyForm(@ModelAttribute("melterTrollyForm") MelterTrollyLogForm melterTrollyLogForm,Model model,Map< String, String> map)
  {
	  String succ="";
	  MelterTrollyLogOutputMessage melterTrollyLogOutputMessage=new MelterTrollyLogOutputMessage();
    MelterTrollyLogInputMessage melterTrollyLogInputMessage=new MelterTrollyLogInputMessage();
    MelterTrollyLogDTO melterTrollyLogDTO= melterTrollyLogForm.getMelterTrollyLogDTO();
    melterTrollyLogInputMessage.setMelterTrollyLogDTO(melterTrollyLogDTO);
    if(melterTrollyLogForm.getMelterTrollyLogDTO().getSno()==null ||melterTrollyLogForm.getMelterTrollyLogDTO().getSno().equals(0) )
    { melterTrollyLogDTO.setCreatedUserId(getCreatedUserId());
    	succ="Ad";
     melterTrollyLogOutputMessage=iMelterTrollyLogService.saveMelterTrollyLog(melterTrollyLogInputMessage);
    }
    else
    {
    	melterTrollyLogDTO.setModifiedUserId(getCreatedUserId());
	 melterTrollyLogOutputMessage=iMelterTrollyLogService.updateMelterTrollyLog(melterTrollyLogInputMessage); 
	succ="Up";
	
    }
    ErrorListDTO errorListDTO=melterTrollyLogOutputMessage.getErrorListDTO();
    
    if(errorListDTO!=null)
    {
    	ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
  	 logger.info("Error in record creation");
  	 model.addAttribute("errorList",errorDTO);
  	 return "melter_trolly_detail";
    } 
   
      
    model.addAttribute("succ", succ);
    return "redirect:/show_melter_trolly_form";
   }
  
   @RequestMapping(value="/get_melter_trolly_log",method=RequestMethod.GET)
   public ModelAndView getMelterTrollyLogForm(@ModelAttribute("melterTrollyForm") MelterTrollyLogForm melterTrollyLogForm,@RequestParam("sno") Integer sno,@RequestParam("opr") String opr,ModelMap model)
   {
	  logger.info("Get Melter Trolly Log"+sno);
	  logger.info("opr"+opr);
	  MelterTrollyLogDTO melterTrollyLogDTO=new MelterTrollyLogDTO();
	  MelterTrollyLogInputMessage melterTrollyLogInputMessage=new MelterTrollyLogInputMessage();
	  MelterTrollyLogOutputMessage melterTrollyLogOutputMessage=new MelterTrollyLogOutputMessage();
	  
	  if(sno!=null || !equals(sno))
	  {
	    melterTrollyLogDTO.setSno(sno);
	    melterTrollyLogInputMessage.setMelterTrollyLogDTO(melterTrollyLogDTO);
	    
	    melterTrollyLogOutputMessage=iMelterTrollyLogService.findMelterTrollyById(melterTrollyLogInputMessage);   
	   
	    ArrayList<MelterTrollyLogDTO> list=(ArrayList<MelterTrollyLogDTO>) melterTrollyLogOutputMessage.getMelterTrollyLogDTOList();
	    if(list!=null && list.size()>0)
	    {
	     melterTrollyLogForm.setMelterTrollyLogDTO(list.get(0));
	    }
	  }
	    model.put("opr",opr);
	    model.put("sno",sno);
	    model.put("melterTrollyLogForm", melterTrollyLogForm);  
	    ModelAndView mav=new ModelAndView("melter_trolly_detail");
	   return mav;
    }
   
   
   @RequestMapping(value="/remove_melter_trolly_log", method=RequestMethod.GET)
   public String removeMelterTrollyLogForm(@RequestParam("sno") Integer sno,Model model)
    {
	 
     MelterTrollyLogDTO melterTrollyLogDTO=new MelterTrollyLogDTO();
     MelterTrollyLogInputMessage melterTrollyLogInputMessage=new MelterTrollyLogInputMessage();
     MelterTrollyLogOutputMessage melterTrollyLogOutputMessage=new MelterTrollyLogOutputMessage();
     melterTrollyLogDTO.setSno(sno);
     melterTrollyLogDTO.setModifiedUserId(getCreatedUserId());
	 melterTrollyLogInputMessage.setMelterTrollyLogDTO(melterTrollyLogDTO);
	 melterTrollyLogOutputMessage=iMelterTrollyLogService.deleteMelterTrolly(melterTrollyLogInputMessage);
	 String succ="Dl";
		model.addAttribute("succ", succ);
	 return	"redirect:/show_melter_trolly_form";
   }
   
   
 }




