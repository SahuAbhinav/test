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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.client.http.controller.form.MelterLogBookFrom;
import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.MelterLogBookDTO;
import com.advanz.erp.masters.model.msg.MastersInputMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.model.msg.MelterLogBookInputMessage;
import com.advanz.erp.masters.model.msg.MelterLogBookOutputMessage;
import com.advanz.erp.masters.service.business.IMastersService;
import com.advanz.erp.masters.service.business.IMelterLogBookService;

@Controller
public class MelterLogBookController extends BaseController{
	@Autowired
	public IMelterLogBookService iMelterLogBookService;
	
	@Autowired
	public IMastersService mastersService;

	private static final Logger logger = LoggerFactory
			.getLogger(MelterLogBookController.class);

	@RequestMapping(value = "/show_melter_log_book", method = RequestMethod.GET)
	public ModelAndView showForm(@ModelAttribute("melterForm") MelterLogBookFrom melterLogBookFrom,@RequestParam(value="menuId",required=false) String menuId,HttpSession session) {
		if(menuId!=null)
		{
		session.setAttribute("menuId", menuId);
		}
	
		ModelAndView mav = new ModelAndView("melter_log_book_list");
		MelterLogBookOutputMessage bookOutputMessage = iMelterLogBookService.findAllMelterLogBook();
		ArrayList<MelterLogBookDTO> list = (ArrayList<MelterLogBookDTO>) bookOutputMessage.getMelterLogBookDTOList();
		mav.addObject("melterLogBookList", list);
		logger.info("melterLogBook List" + list);
		return mav;
	}

	@RequestMapping(value = "/get_melter_log_book", method = RequestMethod.POST)
	public ModelAndView getMelterLogBookData(@ModelAttribute("melterForm") MelterLogBookFrom melterLogBookFrom,Model model) {
		MelterLogBookOutputMessage melterLogBookOutputMessage = new MelterLogBookOutputMessage();
		Date logDate = melterLogBookFrom.getLogDate();
		Date fromDate = melterLogBookFrom.getFromDate();
		Date toDate = melterLogBookFrom.getToDate();
		String runNo = melterLogBookFrom.getRunNo();
		String operatorName = melterLogBookFrom.getOperatorName();
		MelterLogBookInputMessage melterLogBookInputMessage = new MelterLogBookInputMessage();
		MelterLogBookDTO melterLogBookDTO = new MelterLogBookDTO();
	
		if (logDate != null || StringUtils.hasText(runNo)|| StringUtils.hasText(operatorName)||fromDate != null ||toDate != null) {
			melterLogBookDTO.setLogDate(logDate);
			melterLogBookDTO.setRunNo(runNo);
			melterLogBookDTO.setFromDate(fromDate);
			melterLogBookDTO.setToDate(toDate);
			melterLogBookDTO.setOperatorName(operatorName);
			melterLogBookInputMessage.setMelterLogBookDTO(melterLogBookDTO);
			melterLogBookOutputMessage = iMelterLogBookService.findForSearchRecord(melterLogBookInputMessage);
		} else {
			melterLogBookOutputMessage = iMelterLogBookService.findAllMelterLogBook();
		}

		ModelAndView mav = new ModelAndView("melter_log_book_list");
		ArrayList<MelterLogBookDTO> list = (ArrayList<MelterLogBookDTO>) melterLogBookOutputMessage.getMelterLogBookDTOList();
		mav.addObject("melterLogBookList", list);
		logger.info("melterLogBook List" + list);
		String succ="Blk";
		if(list.equals(null) || list.size()==0)
		{
		  model.addAttribute("succ", succ);
		}
		return mav;
	}
	
	@RequestMapping(value="/save_melter_log_book")
	public String saveMelterLogBookData(@ModelAttribute("melterForm") MelterLogBookFrom melterLogBookFrom,Model model) 
	{
		//MelterLogBookDTO bookDTO=new MelterLogBookDTO();
     String succ="";
	  MelterLogBookInputMessage melterLogBookInputMessage=new MelterLogBookInputMessage();
	  MelterLogBookDTO bookDTO=  melterLogBookFrom.getMelterLogBookDTO();
	  melterLogBookInputMessage.setMelterLogBookDTO(bookDTO);
	  
	  MelterLogBookOutputMessage melterLogBookOutputMessage=new MelterLogBookOutputMessage();
	   if(melterLogBookFrom.getMelterLogBookDTO().getSno()==null || melterLogBookFrom.getMelterLogBookDTO().getSno().equals(0))
	   {bookDTO.setCreatedUserId(getCreatedUserId());
	    melterLogBookOutputMessage=iMelterLogBookService.createMelterLogBook(melterLogBookInputMessage);
	    succ="Ad";
	   }
	   else
	   {
		bookDTO.setModifiedUserId(getCreatedUserId());
		melterLogBookOutputMessage=iMelterLogBookService.updateMelterRecord(melterLogBookInputMessage);
		succ="Up";
	   }
	  ErrorListDTO errorListDTO=melterLogBookOutputMessage.getErrorListDTO();
	  if(errorListDTO!=null && errorListDTO.hasErrors()){
		  ErrorDTO errorDTO=errorListDTO.getErrorList().get(0);
		  
			logger.info(" adding Error ");
			model.addAttribute("errorList", errorDTO);
			return "melter_log_book_detail";
		}
	  model.addAttribute("succ", succ);
	 return "redirect:/show_melter_log_book";
	}

	@RequestMapping(value = "/show_new_melter_form", method = RequestMethod.GET)
	public ModelAndView showNewMelterForm(@ModelAttribute("melterForm") MelterLogBookFrom melterLogBookFrom) {
		ModelAndView mav = new ModelAndView("melter_log_book_detail");
		MelterLogBookInputMessage melterLogBookInputMessage= null;//new MelterLogBookInputMessage();
		MelterLogBookOutputMessage bookOutputMessage = iMelterLogBookService.getLastRecordDate(melterLogBookInputMessage);
		Timestamp timestamp=  bookOutputMessage.getLastRecordDate();
		System.out.println("timestamp"+timestamp);
		SimpleDateFormat ft =new SimpleDateFormat ("yyyy,MM,dd");
		
		if (melterLogBookFrom == null) {
			melterLogBookFrom = new MelterLogBookFrom();
		}
		System.out.println("melterLogBookFrom"+melterLogBookFrom);
		melterLogBookFrom.setLastRecordDate(ft.format(new Date(timestamp.getTime())));
		mav.addObject("melterLogBookForm", melterLogBookFrom);
		return mav;
	}
	
	
   @RequestMapping(value="/get_melter_log_book",method=RequestMethod.GET)
   public ModelAndView getMelterLogBook(@ModelAttribute("melterForm") MelterLogBookFrom melterLogBookFrom,@RequestParam("opr") String opr,@RequestParam("sno") Integer sno ,ModelMap model)
   {	 
	   logger.info("Get Melter Log Book:"+sno);
	   logger.info("opr:"+opr);
	   MelterLogBookDTO melterLogBookDTO=new MelterLogBookDTO();
	   MelterLogBookOutputMessage melterLogBookOutputMessage=new MelterLogBookOutputMessage();
	   if(sno!=null || !equals(sno))
	   {
		MelterLogBookInputMessage melterLogBookInputMessage=new MelterLogBookInputMessage();
		melterLogBookDTO.setSno(sno);
		melterLogBookInputMessage.setMelterLogBookDTO(melterLogBookDTO);
		melterLogBookOutputMessage=iMelterLogBookService.findMelterById(melterLogBookInputMessage);
		ArrayList<MelterLogBookDTO> list=(ArrayList<MelterLogBookDTO>) melterLogBookOutputMessage.getMelterLogBookDTOList();   
	   
		if(list!=null && list.size()>0)
	    {
	     melterLogBookFrom.setMelterLogBookDTO(list.get(0));
	    }
	   }
	   model.put("opr",opr);
	   model.put("sno",sno);
	   model.put("melterForm", melterLogBookFrom);
	   ModelAndView mav=new ModelAndView("melter_log_book_detail");
	   return mav;
   }

   
   @RequestMapping(value="/remove_melter_log",method=RequestMethod.GET) 
   public String removeMelterLog(@RequestParam("sno") Integer sno,Model model)
    {
	   MelterLogBookOutputMessage melterLogBookOutputMessage=new MelterLogBookOutputMessage();
	   MelterLogBookInputMessage melterLogBookInputMessage=new MelterLogBookInputMessage();
	   MelterLogBookDTO melterLogBookDTO=new MelterLogBookDTO();
	   melterLogBookDTO.setSno(sno);
	   melterLogBookDTO.setModifiedUserId(getCreatedUserId());
	   System.out.println();
	   melterLogBookInputMessage.setMelterLogBookDTO(melterLogBookDTO);
	   melterLogBookOutputMessage=iMelterLogBookService.deleteMelterRecord(melterLogBookInputMessage); 
	   String succ="Dl";
		model.addAttribute("succ", succ);
	  return "redirect:/show_melter_log_book";   
   }
	
	
	@ModelAttribute("shift")
	public List<MastersDTO> shiftList() {
		MastersInputMessage mastersInputMessage = new MastersInputMessage();
		mastersInputMessage.setFormId(11);
		MastersOutputMessage mastersOutputMessage = mastersService.findFormByIdForMelterLog(mastersInputMessage);
		return mastersOutputMessage.getMastersDTOList();
	}


}
