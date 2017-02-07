package com.advanz.erp.client.http.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.advanz.erp.masters.service.business.ICompanyService;
import com.advanz.erp.masters.service.business.IStockLedgerService;

@Controller
public class SendEmailController extends BaseController{
@Autowired
public ICompanyService companyService;
@Autowired
public IStockLedgerService service;
	@RequestMapping(value = "/show_email" , method=RequestMethod.GET)
	public ModelAndView showEmailForm(){
		ModelAndView mav = new ModelAndView("sendEmail");
		
		return mav;
	}
	
	@RequestMapping(value = "/send_email", method=RequestMethod.GET)
	public ModelAndView sendEmail(HttpSession session,@RequestParam("date") Date date){
		ModelAndView mav = new ModelAndView("sendEmail");
		Integer userId=(Integer)session.getAttribute("userId");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateString= sdf.format(date);
	String body=service.getEmailByDate(dateString);
		if(body!=null ){
			companyService.sendEmail(userId,date);
		}else{
			mav.addObject("info", "Email not genrated for this date");
		}
		
		return mav;
	}
	
}
