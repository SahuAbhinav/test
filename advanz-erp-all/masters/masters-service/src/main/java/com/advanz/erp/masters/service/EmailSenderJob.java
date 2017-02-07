package com.advanz.erp.masters.service;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
	  
	public class EmailSenderJob extends QuartzJobBean
	{
	   
	 private EmailService emailService;
	 public void setEmailService(EmailService emailService)
	 {
	  this.emailService = emailService;
	 }
	  
	   
	 @Override
	 protected void executeInternal(JobExecutionContext context) throws JobExecutionException
	 {
	  System.out.println("Mail has been snt  Sending.............  ");
	
	try
	   {
	    emailService.sendEmail();
	   }
	  
	   catch (Exception e)
	   {
	    e.printStackTrace();
	   }
	  //}
 }
	   
	
	}
