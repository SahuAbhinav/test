package com.advanz.erp.masters.service;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
	  
	public class EmailCheckAndSenderJob extends QuartzJobBean
	{
	   
	 private EmailCheckAndSendService emailCheckAndSendService;
	
	 public void setEmailCheckAndSendService(
			EmailCheckAndSendService emailCheckAndSendService) {
		this.emailCheckAndSendService = emailCheckAndSendService;
	}

	@Override
	 protected void executeInternal(JobExecutionContext context) throws JobExecutionException
	 {
	  System.out.println("Mail has been snt  Sending.............  ");
	
	try
	   {
		System.out.println("Check Email Service");
		emailCheckAndSendService.execute(context);
	   }
	  
	   catch (Exception e)
	   {
	    e.printStackTrace();
	   }
	  //}
 }
	   
	
	}
