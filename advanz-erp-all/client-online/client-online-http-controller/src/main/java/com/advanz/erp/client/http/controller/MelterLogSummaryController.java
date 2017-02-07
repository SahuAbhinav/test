package com.advanz.erp.client.http.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.MelterLogSummaryForm;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.MelterLogSummaryDTO;
import com.advanz.erp.masters.model.msg.MelterLogSummaryInputMessage;
import com.advanz.erp.masters.model.msg.MelterLogSummaryOutputMessage;
import com.advanz.erp.masters.service.business.IMelterLogSummaryService;

@Controller
public class MelterLogSummaryController extends BaseController {
  @Autowired
  IMelterLogSummaryService iMelterLogSummaryService=null;
  
  private static final Logger logger = LoggerFactory.getLogger(MelterLogSummaryController.class);
  
  /*for showing melter list form*/
  @RequestMapping(value="/show_melter_summary_form",method=RequestMethod.GET)
  public ModelAndView showMelterForm(@ModelAttribute("melterSummaryForm") MelterLogSummaryForm melterLogSummaryForm,@RequestParam(value="menuId",required=false) String menuId,HttpSession session)
  {
	  if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
    ModelAndView mav=new ModelAndView("melter_log_summary_list");
    MelterLogSummaryOutputMessage melterLogSummaryOutputMessage=iMelterLogSummaryService.findAllMelterSummary();
    ArrayList<MelterLogSummaryDTO> list=(ArrayList<MelterLogSummaryDTO>) melterLogSummaryOutputMessage.getMelterLogSummaryDTOList();
 
    mav.addObject("melterLogSummaryList",list);
    logger.info("melter Log summary list", list);
	return mav;  
  }
  
  
  
 /* for searching data on database based on the input field
  */ 
  @RequestMapping(value="/get_melter_summary_form",method=RequestMethod.POST)
  public ModelAndView getMelterSummaryForm(@ModelAttribute("melterSummaryForm") MelterLogSummaryForm melterLogSummaryForm,Model model)
  {
	
    Date logDate=melterLogSummaryForm.getLogDate();
    Date fromDate=melterLogSummaryForm.getFromDate();
    Date toDate=melterLogSummaryForm.getToDate();
    
   
	MelterLogSummaryDTO melterLogSummaryDTO=new MelterLogSummaryDTO();
	MelterLogSummaryInputMessage melterLogSummaryInputMessage=new MelterLogSummaryInputMessage();
	MelterLogSummaryOutputMessage melterLogSummaryOutputMessage=new MelterLogSummaryOutputMessage();
	
	if(fromDate!=null || toDate!=null)
	{
		 melterLogSummaryDTO.setFromDate(fromDate);
		 melterLogSummaryDTO.setToDate(toDate);
	
	 melterLogSummaryDTO.setLogDate(logDate);
     melterLogSummaryInputMessage.setMelterLogSummaryDTO(melterLogSummaryDTO);
	 melterLogSummaryOutputMessage=iMelterLogSummaryService.searchMelterSummaryByDate(melterLogSummaryInputMessage);
	}
	else
	{
	 melterLogSummaryOutputMessage=iMelterLogSummaryService.findAllMelterSummary();
	}
	 ArrayList< MelterLogSummaryDTO> list=(ArrayList<MelterLogSummaryDTO>) melterLogSummaryOutputMessage.getMelterLogSummaryDTOList();	
	 ModelAndView mav=new ModelAndView("melter_log_summary_list");
     mav.addObject("melterLogSummaryList",list);
    logger.info("melter Log summary list", list);
    String succ="Blk";
	if(list.equals(null) || list.size()==0)
	{
	  model.addAttribute("succ", succ);
	}
	return mav;
  }
  
  /*it getting request from list for adding new record simple check form object and send it to melter_log_summary_detail jsp*/
  @RequestMapping(value="show_new_melter_summary",method=RequestMethod.GET)
  public ModelAndView showMelterAddFor(@ModelAttribute("melterSummaryForm") MelterLogSummaryForm melterLogSummaryForm)
  {
	ModelAndView mav=new ModelAndView("melter_log_summary_detail");
	MelterLogSummaryOutputMessage melterLogSummaryOutputMessage=null;
	MelterLogSummaryInputMessage melterLogSummaryInputMessage=new  MelterLogSummaryInputMessage();
	if(melterLogSummaryForm!=null)
	{
		melterLogSummaryForm=new MelterLogSummaryForm();
	}
	melterLogSummaryOutputMessage=iMelterLogSummaryService.getLastMelterLogSummaryDate(melterLogSummaryInputMessage);
	Timestamp timestamp= melterLogSummaryOutputMessage.getLastMelterLogSummaryDate();
	//System.out.println("Last Finished good time"+new Date(timestamp.getTime()));
	SimpleDateFormat ft =new SimpleDateFormat ("yyyy,MM,dd");
	melterLogSummaryForm.setLastMelterLogDate(ft.format(new Date(timestamp.getTime())));
	mav.addObject("melterSummaryForm",melterLogSummaryForm);
	return mav;
  }
  
 /*for saving data in database call service from here*/  
  @RequestMapping(value="/save_melter_summary_form",method=RequestMethod.POST)
  public String saveMelterSummaryForm(@ModelAttribute("melterSummaryForm") MelterLogSummaryForm melterLogSummaryForm,Model model)
  {
	  String succ="";
    MelterLogSummaryInputMessage melterLogSummaryInputMessage=new  MelterLogSummaryInputMessage();
    MelterLogSummaryOutputMessage melterLogSummaryOutputMessage=null;
    MelterLogSummaryDTO melterLogSummaryDTO=  melterLogSummaryForm.getMelterLogSummaryDTO();
    melterLogSummaryInputMessage.setMelterLogSummaryDTO(melterLogSummaryDTO);
       if(melterLogSummaryForm.getMelterLogSummaryDTO().getSno()==null || melterLogSummaryForm.getMelterLogSummaryDTO().getSno().equals(0))
      {
    	   melterLogSummaryDTO.setCreatedUserId(getCreatedUserId());
       melterLogSummaryOutputMessage=iMelterLogSummaryService.addNewMelterLogSummary(melterLogSummaryInputMessage);
       succ="Ad";
      }
   else
    {
	   melterLogSummaryDTO.setModifiedUserId(getCreatedUserId());
	   succ="Up";
	  melterLogSummaryOutputMessage=iMelterLogSummaryService.updateMelterLogSummary(melterLogSummaryInputMessage);	
    }
     ErrorListDTO errorListDTO=melterLogSummaryOutputMessage.getErrorListDTO();
     if(errorListDTO!=null)
     {
    	 logger.info("Error in record creation");
    	 ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
    	 model.addAttribute("errorList",errorDTO);
    	 return "melter_log_summary_detail";
     } 
     model.addAttribute("succ", succ);
     return "redirect:/show_melter_summary_form";
  }
  
  @RequestMapping(value="/get_melter_log_summary",method=RequestMethod.GET)
  public ModelAndView getMelterLogSummary(@ModelAttribute("melterSummaryForm") MelterLogSummaryForm melterLogSummaryForm,@RequestParam("sno") Integer sno,@RequestParam("opr")String opr,ModelMap modle)
  {
	
	  logger.info("Get Melter Log Summary"+sno);
	  logger.info("opr"+opr);
	  MelterLogSummaryDTO melterLogSummaryDTO=new MelterLogSummaryDTO();
	  MelterLogSummaryOutputMessage melterLogSummaryOutputMessage=new MelterLogSummaryOutputMessage();
	  if(sno!=null || !equals(sno))
	  {
		 MelterLogSummaryInputMessage melterLogSummaryInputMessage=new MelterLogSummaryInputMessage();
		 melterLogSummaryDTO.setSno(sno);
		 melterLogSummaryInputMessage.setMelterLogSummaryDTO(melterLogSummaryDTO);
		 melterLogSummaryOutputMessage=iMelterLogSummaryService.findById(melterLogSummaryInputMessage);
		 ArrayList<MelterLogSummaryDTO> list=(ArrayList<MelterLogSummaryDTO>) melterLogSummaryOutputMessage.getMelterLogSummaryDTOList();
		
		 if(list!=null && list.size()>0)
	     {
	    	melterLogSummaryForm.setMelterLogSummaryDTO(list.get(0));
	     }
	  }
	  modle.put("opr",opr);
	  modle.put("sno",sno);
	  modle.put("melterSummaryForm", melterLogSummaryForm);
	  ModelAndView mav=new ModelAndView("melter_log_summary_detail");
	  return mav;
  }
  
   @RequestMapping(value="/remove_melter_log_summary",method=RequestMethod.GET)
   public String removeMelterLogSummary(@RequestParam("sno") Integer sno,@ModelAttribute("melterSummaryForm") MelterLogSummaryForm melterLogSummaryForm,Model model)
   {
	
	 MelterLogSummaryOutputMessage melterLogSummaryOutputMessage=new MelterLogSummaryOutputMessage();
	 MelterLogSummaryInputMessage melterLogSummaryInputMessage=new MelterLogSummaryInputMessage();
	 MelterLogSummaryDTO melterLogSummaryDTO=new MelterLogSummaryDTO();
	 melterLogSummaryDTO.setSno(sno);
	 melterLogSummaryDTO.setModifiedUserId(getCreatedUserId());
	 melterLogSummaryInputMessage.setMelterLogSummaryDTO(melterLogSummaryDTO);
	 melterLogSummaryOutputMessage=iMelterLogSummaryService.deleteMelterLogSummary(melterLogSummaryInputMessage);
	 String succ="Dl";
		model.addAttribute("succ", succ);
	 return "redirect:/show_melter_summary_form";
   }
}
