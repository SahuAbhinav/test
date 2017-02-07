package com.advanz.erp.client.http.controller.form;

import java.util.List;

import com.advanz.erp.masters.model.ModuleMenuMasterDTO;
import com.advanz.erp.masters.model.RoleDTO;
import com.advanz.erp.masters.model.RoleMasterDTO;
import com.advanz.erp.masters.model.UserMasterRoleDTO;
import com.advanz.erp.masters.model.UserRoleAndRightsDTO;

public class RoleForm {

	private RoleMasterDTO role;
	
	private List<RoleMasterDTO> roles;

	
	private UserRoleAndRightsDTO userRoleAndRightsDTO;
	
	private List<UserRoleAndRightsDTO> userRoleAndRightsDTOList;
	
	private ModuleMenuMasterDTO moduleMenuMasterDTO;
	
	private List<ModuleMenuMasterDTO> moduleMenuMasterDTOList;

    private String succ;
    
	
	public String getSucc() {
		return succ;
	}

	public void setSucc(String succ) {
		this.succ = succ;
	}

	public UserRoleAndRightsDTO getUserRoleAndRightsDTO() {
		return userRoleAndRightsDTO;
	}

	public void setUserRoleAndRightsDTO(UserRoleAndRightsDTO userRoleAndRightsDTO) {
		this.userRoleAndRightsDTO = userRoleAndRightsDTO;
	}

	public List<UserRoleAndRightsDTO> getUserRoleAndRightsDTOList() {
		return userRoleAndRightsDTOList;
	}

	public void setUserRoleAndRightsDTOList(
			List<UserRoleAndRightsDTO> userRoleAndRightsDTOList) {
		this.userRoleAndRightsDTOList = userRoleAndRightsDTOList;
	}

	public ModuleMenuMasterDTO getModuleMenuMasterDTO() {
		return moduleMenuMasterDTO;
	}

	public void setModuleMenuMasterDTO(ModuleMenuMasterDTO moduleMenuMasterDTO) {
		this.moduleMenuMasterDTO = moduleMenuMasterDTO;
	}

	public List<ModuleMenuMasterDTO> getModuleMenuMasterDTOList() {
		return moduleMenuMasterDTOList;
	}

	public void setModuleMenuMasterDTOList(
			List<ModuleMenuMasterDTO> moduleMenuMasterDTOList) {
		this.moduleMenuMasterDTOList = moduleMenuMasterDTOList;
	}

	public RoleMasterDTO getRole() {
		return role;
	}

	public void setRole(RoleMasterDTO role) {
		this.role = role;
	}

	public List<RoleMasterDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleMasterDTO> roles) {
		this.roles = roles;
	}
	
}
