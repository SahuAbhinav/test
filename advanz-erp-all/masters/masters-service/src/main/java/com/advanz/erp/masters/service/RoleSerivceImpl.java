package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.RoleMasterEntity;
import com.advanz.erp.masters.model.RoleMasterDTO;
import com.advanz.erp.masters.model.UserRoleDTO;
import com.advanz.erp.masters.model.msg.RoleInputMessage;
import com.advanz.erp.masters.model.msg.RoleOutMessage;
import com.advanz.erp.masters.model.msg.UserRoleInputMessage;
import com.advanz.erp.masters.model.msg.UserRoleOutputMessage;
import com.advanz.erp.masters.service.business.IRoleService;
import com.advanz.erp.masters.service.business.IUserRoleService;
import com.advanz.erp.masters.storage.IStorageRoleMasterDAO;

@Service
public class RoleSerivceImpl implements IRoleService{

	public static final String ADD_ROLE = "CreateRole";
	public static final String UPDATE_ROLE = "UpdateRole";
	public static final String DELETE_ROLE = "DeleteRole";
	public static final String FIND_ROLE_BY_ID = "FindRoleById";
	public static final String FIND_ALL_ROLES = "FindAllRoles";
	private static final String FIND_ROLES_BY_CRITERIA = "findRoleByCriteria";
	private static final String FIND_MODULE_MENU_DATA = "findAllModuleMenuData";
	private static final String FIND_ROLE_BY_LAST_ID = "findRoleByLastId";

	public String flowId = "";

	//@Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO do autowiring 
	
	@Autowired
	public IStorageRoleMasterDAO storageRoleDAO;
	
	public RoleInputMessage roleInputMessage ;

	public RoleOutMessage roleOutMessage = new RoleOutMessage();

	
	
	@Override
	public RoleOutMessage addRole(RoleInputMessage roleInputMessage) {

		flowId = ADD_ROLE;
		// assign the message to the class level variable.
		this.roleInputMessage = roleInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return roleOutMessage;
	}

	@Override
	public RoleOutMessage updateRole(RoleInputMessage roleInputMessage) {

		flowId = UPDATE_ROLE;
		// assign the message to the class level variable.
		this.roleInputMessage = roleInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return roleOutMessage;
	}

	@Override
	public RoleOutMessage deleteRole(RoleInputMessage roleInputMessage) {

		flowId = DELETE_ROLE;
		// assign the message to the class level variable.
		this.roleInputMessage = roleInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return roleOutMessage;
	}

	@Override
	public RoleOutMessage findRoleById(RoleInputMessage roleInputMessage) {

		flowId = FIND_ROLE_BY_ID;
		// assign the message to the class level variable.
		this.roleInputMessage = roleInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return roleOutMessage;
	}

	@Override
	public RoleOutMessage findAllRoles() {

		flowId = FIND_ALL_ROLES;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return roleOutMessage;
	}

	@Override
	public RoleOutMessage findRoleLastId() {
		flowId = FIND_ROLE_BY_LAST_ID;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return roleOutMessage;
	}

	
	@Override
	public RoleOutMessage findRoleBySearchCriteria(
			RoleInputMessage roleInputMessage) {

		flowId = FIND_ROLES_BY_CRITERIA;
		// assign the message to the class level variable.
		this.roleInputMessage = roleInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return roleOutMessage;
	}

	@Override
	public RoleOutMessage findAllModuleMenuData() {
		flowId = FIND_MODULE_MENU_DATA;
		
		advanzErpServiceTemplate.execute(this);

		return roleOutMessage;
	}
	
	@Override
	public boolean validateInput() {
		List<ErrorDTO> errorList = new ArrayList<ErrorDTO>();
		ErrorListDTO errorListDTO =  new ErrorListDTO();
		errorListDTO.setErrorList(errorList);
		if (ADD_ROLE.equals(flowId)) { 
			// find if the role name is already there
			if(roleInputMessage != null && roleInputMessage.getRole() !=null && !"".equals(roleInputMessage.getRole().getRoleName())) {
				if(! CollectionUtils.isEmpty(storageRoleDAO.findByRoleNames(roleInputMessage.getRole().getRoleName())) ) {
					
					ErrorDTO error = new ErrorDTO();
					error.setErrorMsg("Sorry, Record already exist, Duplicate entries are not allowed.");
				//	System.out.println("errrrrrrrrrrrrrrrrrr"+error);
					errorList.add(error);
					roleOutMessage.setErrorListDTO(errorListDTO);
					return false;
				}
				
			}else {
				ErrorDTO error = new ErrorDTO();
				error.setErrorMsg("Role cannot be null");
				errorList.add(error);
				roleOutMessage.setErrorListDTO(errorListDTO);
				return false;
			}
			return true;
		}
		else if (UPDATE_ROLE.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (FIND_ROLE_BY_LAST_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (DELETE_ROLE.equals(flowId)) {
			
			return true;
		} else if (FIND_ROLE_BY_ID.equals(flowId)) {
			 
			return true;
		} else if (FIND_ALL_ROLES.equals(flowId)) {
			
			return true;
		}else if (FIND_ROLES_BY_CRITERIA.equals(flowId)) {
			
			return true;
		}else if (FIND_MODULE_MENU_DATA.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}

		return false;
	}

	@Override
	public void performBusinessLogic() {

	if (ADD_ROLE.equals(flowId)) {
		RoleMasterEntity roleEntity  = new RoleMasterEntity();
		{
		if(roleInputMessage!=null)
		{
	    RoleMasterDTO roleDTO =roleInputMessage.getRole();
	    BeanUtils.copyProperties(roleDTO, roleEntity);
		 roleOutMessage=new RoleOutMessage();
		 roleOutMessage.setErrorListDTO(null);
		 storageRoleDAO.create(roleEntity);
		 } 
	   }
	} 
	else if (FIND_ALL_ROLES.equals(flowId)) {
		List<RoleMasterEntity> list = storageRoleDAO.load();
		List<RoleMasterDTO> dtoList = convertRoleEntityToRoleDTO(list);
		 roleOutMessage=new RoleOutMessage();
		roleOutMessage.setRoles(dtoList);
	}
	
	else if (FIND_ROLE_BY_ID.equals(flowId)) {
		// TODO business logic
		Integer roleId  = roleInputMessage.getRole().getRoleId();
		RoleMasterEntity roleEntity = storageRoleDAO.read(roleId);
		List<RoleMasterEntity> list=new ArrayList<RoleMasterEntity>();
		list.add(roleEntity);
		List<RoleMasterDTO> roleDto = convertRoleEntityToRoleDTO(list);
		RoleMasterDTO dto=roleDto.get(0);
		roleOutMessage=new RoleOutMessage();
		roleOutMessage.setRole(dto);
		

	 } 

	else if (FIND_ROLE_BY_LAST_ID.equals(flowId)) {
		// TODO business logic
		List<RoleMasterEntity> roleEntity = storageRoleDAO.findByLastId();
		List<RoleMasterDTO> roleDto = convertRoleEntityToRoleDTO(roleEntity);
		 roleOutMessage=new RoleOutMessage();
	//	System.out.println("Logger-------------------------------------------"+roleDto.get(0));
		roleOutMessage.setRoles(roleDto);
	 } 

	
	else if (UPDATE_ROLE.equals(flowId)) {
		RoleMasterEntity roleEntity  = new RoleMasterEntity();
			roleEntity.setRoleName(roleInputMessage.getRole().getRoleName());
			roleEntity.setRoleId(roleInputMessage.getRole().getRoleId());
			roleEntity.setHotKeyActive(roleInputMessage.getRole().getHotKeyActive());
			 roleOutMessage=new RoleOutMessage();
			storageRoleDAO.update(roleEntity);
			
	}
	else if (DELETE_ROLE.equals(flowId))
	{
		
		RoleMasterEntity roleEntity = storageRoleDAO.read(roleInputMessage.getRole().getRoleId());
			roleEntity.setDeletedFlag(true);
			storageRoleDAO.delete(roleEntity);
		
	}
	else if (FIND_ROLES_BY_CRITERIA.equals(flowId)) {
		List<RoleMasterEntity> list = storageRoleDAO.findByRoleNames(roleInputMessage.getRole().getRoleName());
		List<RoleMasterDTO> dtoList = convertRoleEntityToRoleDTO(list);
		 roleOutMessage=new RoleOutMessage();
		roleOutMessage.setRoles(dtoList);
	}
	
	}
	
	
	 private List<RoleMasterDTO> convertRoleEntityToRoleDTO(List<RoleMasterEntity> list)
	  {
		List<RoleMasterDTO> resultList=new ArrayList<RoleMasterDTO>();
		RoleMasterDTO roleDTO;
		for(RoleMasterEntity entity:list)
		 {
		 roleDTO=new RoleMasterDTO();
		 try{
		   BeanUtils.copyProperties(entity,roleDTO );  
		    resultList.add(roleDTO); 
		  }
		  catch (Exception e) {
			  e.printStackTrace();
		 }}
		  return resultList;
	  }

	@Override
	public void formatOutput() {
		// TODO Auto-generated method stub
		
	}

	
	
}
