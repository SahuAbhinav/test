package com.advanz.erp.client.http.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.advanz.erp.client.http.controller.form.RoleForm;

public class RoleValidator implements Validator  {

	public boolean supports(Class<?> clazz) {
		return RoleForm.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role.roleName",
				"roleForm.roleName.required");
		/*RoleForm roleForm = (RoleForm) target;
		if (roleForm.getRole().getRoleName() == null) {
			errors.rejectValue("role.roleName", "roleName.required");
		}*/
	}

}
