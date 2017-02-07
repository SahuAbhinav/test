package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;

import com.advanz.erp.masters.entity.jpa.RoleMasterEntity;
import com.advanz.erp.masters.entity.jpa.UserRoleAndRightsEntity;
import com.advanz.erp.masters.model.UserRoleAndRightsDTO;
import com.advanz.erp.masters.model.msg.UserRoleAndRightsInputMessage;
import com.advanz.erp.masters.model.msg.UserRoleAndRightsOutputMessage;
import com.advanz.erp.masters.service.business.IUserRoleAndRightsService;
import com.advanz.erp.masters.storage.IStorageRoleAndRightsDAO;
@Service
public class UserRoleAndRightsServiceImpl implements IUserRoleAndRightsService
{
	public static final String ADD_ROLE_AND_RIGHT = "CreateRole";
	public static final String UPDATE_ROLE_AND_RIGHT = "UpdateRole";
	public static final String DELETE_ROLE_AND_RIGHT = "DeleteRole";
	public static final String FIND_ROLE_AND_RIGHT_BY_ID = "FindRoleById";
	public static final String FIND_ALL_ROLE_AND_RIGHT = "FindAllRoles";
	private static final String FIND_ROLE_AND_RIGHT_BY_CRITERIA = "findRoleByCriteria";
	private static final String FIND_MODULE_MENU_DATA = "findAllModuleMenuData";
	private static final String FIND_ROLE_AND_RIGHT_BY_ROLE_ID = "findRoleAndRightByRoleId";
	private static final String FIND_ROLE_FLAG_BY_USER_ID = "findRoleFlagByUserId";
	
	private static final String FIND_SNO_ROLE_AND_RIGHT_BY_ROLE_ID = "findSnoRoleAndRightByRoleId";
	
	private static final Logger logger = LoggerFactory.getLogger(UserRoleAndRightsServiceImpl.class);
	public String flowId = "";

	//@Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO do autowiring 
	
	@Autowired
	public IStorageRoleAndRightsDAO storageRoleAndRightsDAO;
	
	public UserRoleAndRightsOutputMessage userRoleAndRightsOutputMessage=new UserRoleAndRightsOutputMessage();

	public UserRoleAndRightsInputMessage userRoleAndRightsInputMessage;

	@Override
	public UserRoleAndRightsOutputMessage createUserRoleAndRight(UserRoleAndRightsInputMessage userRoleAndRightsInputMessage)
	{

		flowId = ADD_ROLE_AND_RIGHT;
		// assign the message to the class level variable.
		this.userRoleAndRightsInputMessage = userRoleAndRightsInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return userRoleAndRightsOutputMessage;
	}

	@Override
	public UserRoleAndRightsOutputMessage updateUserRoleAndRight(UserRoleAndRightsInputMessage userRoleAndRightsInputMessage)
	{
	flowId = UPDATE_ROLE_AND_RIGHT;
		// assign the message to the class level variable.
	this.userRoleAndRightsInputMessage = userRoleAndRightsInputMessage;
	// call the template method
	advanzErpServiceTemplate.execute(this);

	return userRoleAndRightsOutputMessage;
	}

	@Override
	 public UserRoleAndRightsOutputMessage deleteUserRoleAndRight(UserRoleAndRightsInputMessage userRoleAndRightsInputMessage) 
		{
		flowId = DELETE_ROLE_AND_RIGHT;
		// assign the message to the class level variable.
		this.userRoleAndRightsInputMessage = userRoleAndRightsInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return userRoleAndRightsOutputMessage;
		}

	@Override
	public UserRoleAndRightsOutputMessage findUserRoleAndRightById(UserRoleAndRightsInputMessage userRoleAndRightsInputMessage) {

		flowId = FIND_ROLE_AND_RIGHT_BY_ID;
		// assign the message to the class level variable.
		this.userRoleAndRightsInputMessage = userRoleAndRightsInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		
		return userRoleAndRightsOutputMessage;
		}

	@Override
	public UserRoleAndRightsOutputMessage findAllUserRoleAndRight()
	{
		flowId = FIND_ALL_ROLE_AND_RIGHT;
		advanzErpServiceTemplate.execute(this);
		return userRoleAndRightsOutputMessage;
	}

	@Override
	public UserRoleAndRightsOutputMessage search(UserRoleAndRightsInputMessage userRoleAndRightsInputMessage)
	{
		flowId = FIND_ROLE_AND_RIGHT_BY_CRITERIA;
		// assign the message to the class level variable.
		this.userRoleAndRightsInputMessage = userRoleAndRightsInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return userRoleAndRightsOutputMessage;
	}

	@Override
	public UserRoleAndRightsOutputMessage findRoleAndRightByRoleId(UserRoleAndRightsInputMessage userRoleAndRightsInputMessage) {
		flowId = FIND_ROLE_AND_RIGHT_BY_ROLE_ID;
		// assign the message to the class level variable.
		this.userRoleAndRightsInputMessage = userRoleAndRightsInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return userRoleAndRightsOutputMessage;
	}
	
	@Override
	public UserRoleAndRightsOutputMessage findSnoFromRoleAndRightByRoleId(UserRoleAndRightsInputMessage userRoleAndRightsInputMessage) {
		flowId = FIND_SNO_ROLE_AND_RIGHT_BY_ROLE_ID;
		// assign the message to the class level variable.
		this.userRoleAndRightsInputMessage = userRoleAndRightsInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return userRoleAndRightsOutputMessage;
	}
	@Override
	public UserRoleAndRightsOutputMessage findRoleAndRightsByUserName(UserRoleAndRightsInputMessage userRoleAndRightsInputMessage) {
		flowId = FIND_ROLE_FLAG_BY_USER_ID;
		// assign the message to the class level variable.
		this.userRoleAndRightsInputMessage = userRoleAndRightsInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return userRoleAndRightsOutputMessage;
	}


	
	@Override
	public boolean validateInput() {
		List<ErrorDTO> errorList = new ArrayList<ErrorDTO>();
		ErrorListDTO errorListDTO =  new ErrorListDTO();
		errorListDTO.setErrorList(errorList);
		if (ADD_ROLE_AND_RIGHT.equals(flowId)) { 
			return true;
		}
		else if (UPDATE_ROLE_AND_RIGHT.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_ROLE_AND_RIGHT.equals(flowId)) {
			
			return true;
		} else if (FIND_ROLE_AND_RIGHT_BY_ID.equals(flowId)) {
			 
			return true;
		} else if (FIND_ALL_ROLE_AND_RIGHT.equals(flowId)) {
			
			return true;
		}else if (FIND_ROLE_AND_RIGHT_BY_CRITERIA.equals(flowId)) {
			
			return true;
		}else if (FIND_MODULE_MENU_DATA.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (FIND_ROLE_AND_RIGHT_BY_ROLE_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (FIND_SNO_ROLE_AND_RIGHT_BY_ROLE_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (FIND_ROLE_FLAG_BY_USER_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		return false;
	}

	@Override
	public void performBusinessLogic() {

	if (ADD_ROLE_AND_RIGHT.equals(flowId)) {
		UserRoleAndRightsEntity userRoleAndRightsEntity  = new UserRoleAndRightsEntity();
		{
		if(userRoleAndRightsInputMessage!=null)
		{
	    UserRoleAndRightsDTO userRoleAndRightsDTO =userRoleAndRightsInputMessage.getUserRoleAndRightsDTO();
	    BeanUtils.copyProperties(userRoleAndRightsDTO, userRoleAndRightsEntity);
	     userRoleAndRightsOutputMessage	=new UserRoleAndRightsOutputMessage();
	     userRoleAndRightsOutputMessage.setErrorListDTO(null);
	     storageRoleAndRightsDAO.create(userRoleAndRightsEntity);
		 } 
	   }
	} 
	
	else if (FIND_ALL_ROLE_AND_RIGHT.equals(flowId)) {
		List<UserRoleAndRightsEntity> list = storageRoleAndRightsDAO.findAllRoleAndRights();
		List<UserRoleAndRightsDTO> dtoList = convertRoleEntityToRoleDTO(list);	
		userRoleAndRightsOutputMessage.setUserRoleAndRightsDTOList(dtoList);
		}
	
	
	 else if (FIND_ROLE_AND_RIGHT_BY_ID.equals(flowId)) {
		 
		 Integer roleId  = userRoleAndRightsInputMessage.getUserRoleAndRightsDTO().getRoleId();
		 List<Integer> list = storageRoleAndRightsDAO.findRoleAndRightsById(roleId);
		// System.out.println("Entity List  ---------------------------------------"+list);
		   userRoleAndRightsOutputMessage=new UserRoleAndRightsOutputMessage();
			userRoleAndRightsOutputMessage.setMenuIdList(list);

			// TODO business logic
		}


	 else if (FIND_ROLE_FLAG_BY_USER_ID.equals(flowId)) {
		 
		 String userName  = userRoleAndRightsInputMessage.getUserRoleAndRightsDTO().getLoginUserName();
		
		 List<UserRoleAndRightsEntity> list = storageRoleAndRightsDAO.findRoleAndRightsByUserName(userName);
		
		   userRoleAndRightsOutputMessage=new UserRoleAndRightsOutputMessage();
			List<UserRoleAndRightsDTO> dtoList = convertRoleEntityToRoleDTO(list);
			userRoleAndRightsOutputMessage.setUserRoleAndRightsDTOList(dtoList);

			// TODO business logic
		}

	 else if (FIND_SNO_ROLE_AND_RIGHT_BY_ROLE_ID.equals(flowId)) {
		 
		 Integer roleId  = userRoleAndRightsInputMessage.getUserRoleAndRightsDTO().getRoleId();
		 List<Integer> list = storageRoleAndRightsDAO.findSnoFromRoleAndRightsByRoleId(roleId);
		// System.out.println("Entity List  ---------------------------------------"+list);
		   userRoleAndRightsOutputMessage=new UserRoleAndRightsOutputMessage();
			userRoleAndRightsOutputMessage.setMenuIdList(list);

			// TODO business logic
		}

	 else if (FIND_ROLE_AND_RIGHT_BY_ROLE_ID.equals(flowId)) {
		 
		   UserRoleAndRightsDTO userRoleAndRightsDTO=new UserRoleAndRightsDTO();
		   userRoleAndRightsDTO=userRoleAndRightsInputMessage.getUserRoleAndRightsDTO();
		   Integer rid=userRoleAndRightsDTO.getRoleId();
		   List<UserRoleAndRightsEntity> list = storageRoleAndRightsDAO.findRoleAndRightsByRoleId(rid);
		 //  System.out.println("BY ROLE ID LIST SIZE ::::::---------------------------------------"+list.size());
		   List<UserRoleAndRightsDTO> dtoList = convertRoleEntityToRoleDTO(list);	
		   userRoleAndRightsOutputMessage = new UserRoleAndRightsOutputMessage();
		   userRoleAndRightsOutputMessage.setUserRoleAndRightsDTOList(dtoList);
		} 
	
	
	 else if (DELETE_ROLE_AND_RIGHT.equals(flowId)) 
	 {  UserRoleAndRightsEntity roleEntity = storageRoleAndRightsDAO.read(userRoleAndRightsInputMessage.getUserRoleAndRightsDTO().getSno());
		roleEntity.setDeletedFlag(true);
		storageRoleAndRightsDAO.delete(roleEntity);
		 }
		
		else if (UPDATE_ROLE_AND_RIGHT.equals(flowId)) {
			 UserRoleAndRightsDTO andRightsDTO=userRoleAndRightsInputMessage.getUserRoleAndRightsDTO();
			 UserRoleAndRightsEntity rightsEntity=new UserRoleAndRightsEntity();
			 try
			 {
				 BeanUtils.copyProperties(andRightsDTO, rightsEntity);	 
			 }
			 catch (Exception e) {
				 e.printStackTrace();
			}
			 storageRoleAndRightsDAO.update(rightsEntity);
		  }
			
			
		} 
	
	
	private List<UserRoleAndRightsDTO> convertRoleEntityToRoleDTO(List<UserRoleAndRightsEntity> list)
	  {
		List<UserRoleAndRightsDTO> resultList=new ArrayList<UserRoleAndRightsDTO>();
		UserRoleAndRightsDTO roleDTO;
		for(UserRoleAndRightsEntity entity:list)
		 {
		 roleDTO=new UserRoleAndRightsDTO();
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
