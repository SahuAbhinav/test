package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.UserRoleEntity;
import com.advanz.erp.masters.model.RoleMasterDTO;
import com.advanz.erp.masters.model.UserRoleDTO;
import com.advanz.erp.masters.model.msg.RoleMasterInputMessage;
import com.advanz.erp.masters.model.msg.RoleMasterOutputMessage;
import com.advanz.erp.masters.model.msg.UserRoleInputMessage;
import com.advanz.erp.masters.model.msg.UserRoleOutputMessage;
import com.advanz.erp.masters.service.business.IUserRoleService;
import com.advanz.erp.masters.storage.IStorageRoleMasterDAO;
import com.advanz.erp.masters.storage.IStorageUserRoleDAO;
@Service
public class UserRoleServiceImpl implements IUserRoleService{

	private final String FIND_ALL_USER_ROLE="findAllUserRole";	
	private final String CREATE_USER__ROLE="addNewUserRole";
	private final String UPDATE_USER__ROLE="UpdateUserRole";
	private final String DELETE_USER_ROLE="deleteUserRole";
	private final String FIND_USER_ROLE_BY_ROLE_ID="findUserRoleByRoleId";
	
    String flowId="";
    
    private IAdvanzErpServiceTemplate advanzErpServiceTemplate=new AdvanzErpServiceTemplate();
    
    @Autowired
    public IStorageUserRoleDAO iStorageUserRoleDAO;
    
    public UserRoleOutputMessage userRoleOutputMessage;
    public UserRoleInputMessage userRoleInputMessage;
   
    @Override
	public UserRoleOutputMessage findAllRecord() {
      flowId=FIND_ALL_USER_ROLE;
      advanzErpServiceTemplate.execute(this);
      return userRoleOutputMessage;
	}

	@Override
	public UserRoleOutputMessage addNewUserRecord(UserRoleInputMessage userRoleInputMessage) {
	   flowId=CREATE_USER__ROLE;
	   this.userRoleInputMessage=userRoleInputMessage;
	   advanzErpServiceTemplate.execute(this);
	   return userRoleOutputMessage;
	}

	@Override
	public UserRoleOutputMessage updateUserRecord(UserRoleInputMessage userRoleInputMessage) {
		
	    flowId=UPDATE_USER__ROLE;
	    this.userRoleInputMessage=userRoleInputMessage;
	    advanzErpServiceTemplate.execute(this);
		return userRoleOutputMessage;
	}
	
	
	@Override
	public UserRoleOutputMessage deleteUserRecord(UserRoleInputMessage userRoleInputMessage) {
					 
		flowId=DELETE_USER_ROLE;
		this.userRoleInputMessage=userRoleInputMessage;
		advanzErpServiceTemplate.execute(this);
		return userRoleOutputMessage;
	}

	@Override
	public UserRoleOutputMessage findUerRoleByRoleId(UserRoleInputMessage userRoleInputMessage) {
		flowId=FIND_USER_ROLE_BY_ROLE_ID;
		this.userRoleInputMessage=userRoleInputMessage;
		advanzErpServiceTemplate.execute(this);
		return userRoleOutputMessage;
		}

	
	
	@Override
	public void performBusinessLogic() {
	
		 if(FIND_ALL_USER_ROLE.equals(flowId))
			{
			 List<UserRoleEntity> list=iStorageUserRoleDAO.load();
			 userRoleOutputMessage=new UserRoleOutputMessage();
		     List<UserRoleDTO> userDTOList=convertUserEntityToUserDTO(list);
		     userRoleOutputMessage.setUserRoleDTOList(userDTOList);
		    }
		 
		 
		 
	 else if(CREATE_USER__ROLE.equals(flowId))
	   {
	    UserRoleEntity userRoleEntity=new UserRoleEntity();
		if(userRoleInputMessage!=null)
		 {
		  UserRoleDTO userRoleDTO=userRoleInputMessage.getUserRoleDTO();
		  try{
		  BeanUtils.copyProperties(userRoleDTO, userRoleEntity);
		  iStorageUserRoleDAO.create(userRoleEntity);
		 }catch(Exception e) {
		 e.printStackTrace();
		}}
		}
		 
	 else if(UPDATE_USER__ROLE.equals(flowId))
	 {
	   UserRoleEntity userRoleEntity=new UserRoleEntity();
		if(userRoleInputMessage!=null)
		 {
		 UserRoleDTO userRoleDTO=userRoleInputMessage.getUserRoleDTO();
		  try{
		  BeanUtils.copyProperties(userRoleDTO, userRoleEntity);
		 
		  iStorageUserRoleDAO.update(userRoleEntity);
		 }catch(Exception e) {
		 e.printStackTrace();
	   }}	 
	 }
		 
		 
	 else if(DELETE_USER_ROLE.equals(flowId))
	 {
	   UserRoleEntity userRoleEntity=new UserRoleEntity();
		if(userRoleInputMessage!=null)
		 {
		 UserRoleDTO userRoleDTO=userRoleInputMessage.getUserRoleDTO();
		  try{
			  BeanUtils.copyProperties(userRoleDTO, userRoleEntity);
			  iStorageUserRoleDAO.delete(userRoleEntity);
			 }catch(Exception e) {
			 e.printStackTrace();
		   }}	 
	 }
	
	 else if(FIND_USER_ROLE_BY_ROLE_ID.equals(flowId))
	 {
	   UserRoleEntity userRoleEntity=new UserRoleEntity();
		if(userRoleInputMessage!=null)
		 {
		 UserRoleDTO userRoleDTO=userRoleInputMessage.getUserRoleDTO();
		  try{
		     BeanUtils.copyProperties(userRoleDTO, userRoleEntity);
		   }catch(Exception e) {
		 e.printStackTrace();
	   }
		 List<UserRoleEntity> list=iStorageUserRoleDAO.findUserRoleByRoleID(userRoleEntity.getRoleId());
		 System.out.println("logger---------------------------"+userRoleEntity.getRoleId());
	     List<UserRoleDTO> userDTOList=convertUserEntityToUserDTO(list);
	     userRoleOutputMessage= new UserRoleOutputMessage();
	     userRoleOutputMessage.setUserRoleDTOList(userDTOList);
	  }
		
	 }

	}
	
	
	@Override
	public boolean validateInput() {
		if(FIND_ALL_USER_ROLE.equals(flowId))
		{
			return true;
		}
		else if(CREATE_USER__ROLE.equals(flowId))
		{
			return true;
		}
		else if(UPDATE_USER__ROLE.equals(flowId))
		{
			return true;
		}
		else if(DELETE_USER_ROLE.equals(flowId))
		{
			return true;
		}
		else if(FIND_USER_ROLE_BY_ROLE_ID.equals(flowId))
		{
			return true;
		}
		return false;
	}

	@Override
	public void formatOutput() {
		if(FIND_ALL_USER_ROLE.equals(flowId))
		{}
		else if(CREATE_USER__ROLE.equals(flowId))
		{}
		else if(UPDATE_USER__ROLE.equals(flowId))
		{}
		else if(DELETE_USER_ROLE.equals(flowId))
		{}
	}

	
	private List<UserRoleDTO> convertUserEntityToUserDTO(List<UserRoleEntity> list)
	  {
		List<UserRoleDTO> resultList=new ArrayList<UserRoleDTO>();
		UserRoleDTO userRoleDTO;
		for(UserRoleEntity entity:list)
		 {
			userRoleDTO=new UserRoleDTO();
	  try{
		   BeanUtils.copyProperties(entity,userRoleDTO );  
		   resultList.add(userRoleDTO); 	 
		 }  
		  catch (Exception e) {
			  e.printStackTrace();
		 }}
		  return resultList;
	  }

	
	
	
}
