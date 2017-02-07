package com.advanz.erp.masters.service;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.advanz.erp.masters.service.business.ICompanyService;

public class EmailCheckAndSendService implements Job {
	private JavaMailSenderImpl mailSender = null;
	
	@Autowired
	public ICompanyService companyService;
	
	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		
		companyService.checkSendEmail(null, null);
		System.out.println("this is check every 10 second");
	}
	
	
	
	}