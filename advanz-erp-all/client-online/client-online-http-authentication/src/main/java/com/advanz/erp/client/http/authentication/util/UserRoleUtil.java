package com.advanz.erp.client.http.authentication.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserRoleUtil {
	/**
	 * The method returns the logged in user name.
	 * @return
	 */
	public static String getLoggedInUserName(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
		 username = ((UserDetails)principal).getUsername();
		} else {
		 username = principal.toString();
		}
		return username;
		
	}
	 
	/**
	 * The method returns the roles of the user that is logged-in.For now only one role can be assigned to the user. 
	 * @return
	 */
	public static List<String> getLoggedInUserRoles() {
		Collection<? extends GrantedAuthority> list = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		List<String> listStr = new ArrayList<String>();
		for (GrantedAuthority ga : list) {
			listStr.add(ga.getAuthority());
		}
		return listStr;
	}
	
	
}
