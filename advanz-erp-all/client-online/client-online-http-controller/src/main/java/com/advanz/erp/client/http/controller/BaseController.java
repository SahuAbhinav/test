/**
 * 
 */
package com.advanz.erp.client.http.controller;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.advanz.erp.client.http.authentication.util.UserRoleUtil;
import com.advanz.erp.common.model.msg.AdvanzErpBaseOutputMessage;
import com.advanz.erp.util.beans.propertyeditors.CustomTimeEditor;

/**
 * @author ct20268
 *
 */
@Controller
public class BaseController {
//	private static final String dateFormat = "dd/MM/yyyy";
	
//	@InitBinder
//	public void allowEmptyDateBinding( WebDataBinder binder )
//	{
//	    // Allow for null values in date fields.
//	    binder.registerCustomEditor( Date.class, new CustomDateEditor( new SimpleDateFormat( dateFormat), true ));
//	    // tell spring to set empty values as null instead of empty string.
//	    binder.registerCustomEditor( String.class, new StringTrimmerEditor( true ));
//	}
	
	private static final String dateFormat = "dd-MMM-yyyy";
	private static final String timeFormat = "HH:mm";
	public Integer createdUserId;
	@InitBinder
	public void allowEmptyDateBinding(WebDataBinder binder,HttpSession session) {
		
     Integer userId=(Integer)session.getAttribute("userId");
     if(userId==null){
    	 logout();
     }
     
     setCreatedUserId(userId);
		// Allow for null values in date fields.
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat(dateFormat), true));
		binder.registerCustomEditor(Time.class, new CustomTimeEditor(
				new SimpleDateFormat(timeFormat), true));
		// tell spring to set empty values as null instead of empty string.
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

	}
	private ModelAndView logout(){
		ModelAndView mv=new ModelAndView(new RedirectView("/j_spring_security_logout"));
		return mv;
	}
	public Integer getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(Integer createdUserId) {
		this.createdUserId = createdUserId;
	}

	protected String getFinYear() {
		String finYear = null;
		Calendar c = Calendar.getInstance();
		int y = c.get(Calendar.YEAR) % 100;
		int m = c.get(Calendar.MONTH);
		if (m <= Calendar.MARCH)
			finYear = (y - 1) + "-" + y;
		else
			finYear = y + "-" + (y + 1);
		return finYear;
	}
	
	protected String getLoggedInUserName() {
		return UserRoleUtil.getLoggedInUserName();
	}
	
	protected List<String> getLoggedInUserRoles() {
		return UserRoleUtil.getLoggedInUserRoles();
	}
	
	public boolean isErrorPresent(AdvanzErpBaseOutputMessage advanzErpBaseOutputMessage){
		if( advanzErpBaseOutputMessage != null ) {
			if( advanzErpBaseOutputMessage.getErrorListDTO() != null && advanzErpBaseOutputMessage.getErrorListDTO().hasErrors()) {
				return true;
			}
		}
		return false;
	}
}
