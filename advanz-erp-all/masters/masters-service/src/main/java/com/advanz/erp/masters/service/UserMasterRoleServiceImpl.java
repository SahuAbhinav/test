package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.RoleMasterEntity;
import com.advanz.erp.masters.entity.jpa.UserMasterRoleEntity;
import com.advanz.erp.masters.model.RoleMasterDTO;
import com.advanz.erp.masters.model.UserMasterRoleDTO;
import com.advanz.erp.masters.model.msg.UserMasterRoleInputMassage;
import com.advanz.erp.masters.model.msg.UserMasterRoleOutputMessage;
import com.advanz.erp.masters.service.business.IUserMasterRoleService;
import com.advanz.erp.masters.storage.IStorageUserMasterRoleDAO;

public class UserMasterRoleServiceImpl implements IUserMasterRoleService 
{
	private final String FIND_ALL_USER_MASTER="findAllUserMaster";	
	private final String CREATE_USER_MASTER_ROLE="addNewUserMaster";
	private final String SEARCH_USER_MASTER_ROLE="searchNewUserMaster";
	private final String FIND_USER_MAX_ID="findUserMaxId";
	private final String UPDATE_USER_MASTER_ROLE="updateUserMasterRole";
	private final String FIND_USER_MASTER_ROLE_BY_ID="findUserMasterRoleById";
	private final String DELETE_USER_MASTER_ROLE="deleteUserMasterRole";
	private final String GET_USER_INFO="getUserInfo";
	String flowId="";
    
    private IAdvanzErpServiceTemplate advanzErpServiceTemplate=new AdvanzErpServiceTemplate();
    
    @Autowired
    public IStorageUserMasterRoleDAO userMasterRoleDAO;
    
    public UserMasterRoleOutputMessage userMasterRoleOutputMessage;
    public UserMasterRoleInputMassage userMasterRoleInputMassage;
    
    @Override
	public UserMasterRoleOutputMessage findAllUserMaster() {
    	flowId=FIND_ALL_USER_MASTER;
    	advanzErpServiceTemplate.execute(this);
		return userMasterRoleOutputMessage;
	}
	

    @Override
    public UserMasterRoleOutputMessage addUserMasterForm(UserMasterRoleInputMassage userMasterRoleInputMassage) {
        flowId=CREATE_USER_MASTER_ROLE;
        this.userMasterRoleInputMassage=userMasterRoleInputMassage;
        advanzErpServiceTemplate.execute(this);
    	return userMasterRoleOutputMessage;
    }    
    
    
    @Override
    public UserMasterRoleOutputMessage searchUserMasterForm(UserMasterRoleInputMassage userMasterRoleInputMassage) {
    	flowId=SEARCH_USER_MASTER_ROLE;
    	this.userMasterRoleInputMassage=userMasterRoleInputMassage;
    	advanzErpServiceTemplate.execute(this);
    	return userMasterRoleOutputMessage;
    }


    @Override
    public UserMasterRoleOutputMessage findUserRoleId() {
    	flowId=FIND_USER_MAX_ID;
        advanzErpServiceTemplate.execute(this);
        return userMasterRoleOutputMessage;
    }
    
    

    @Override
    public UserMasterRoleOutputMessage updateUserMasterRole(UserMasterRoleInputMassage userMasterRoleInputMassage) {
    	 flowId=UPDATE_USER_MASTER_ROLE;
    	 this.userMasterRoleInputMassage=userMasterRoleInputMassage;
    	 advanzErpServiceTemplate.execute(this);
    	 return userMasterRoleOutputMessage;
    }
    

    @Override
    public UserMasterRoleOutputMessage findUserMasterRoleById(UserMasterRoleInputMassage userMasterRoleInputMassage) {
        flowId=FIND_USER_MASTER_ROLE_BY_ID;
        this.userMasterRoleInputMassage=userMasterRoleInputMassage;
        advanzErpServiceTemplate.execute(this);
    	return userMasterRoleOutputMessage;
    }
    
    

    @Override
    public UserMasterRoleOutputMessage deleteUserMasterRole(UserMasterRoleInputMassage userMasterRoleInputMassage) {
    
    	flowId=DELETE_USER_MASTER_ROLE;
       this.userMasterRoleInputMassage=userMasterRoleInputMassage;
        advanzErpServiceTemplate.execute(this);
    	return userMasterRoleOutputMessage;
    }
    @Override
    public UserMasterRoleOutputMessage getUserInfo(UserMasterRoleInputMassage userMasterRoleInputMassage) {
    
    	flowId=GET_USER_INFO;
       this.userMasterRoleInputMassage=userMasterRoleInputMassage;
        advanzErpServiceTemplate.execute(this);
    	return userMasterRoleOutputMessage;
    }
    
	@Override
	public void performBusinessLogic() {
				
	  if(FIND_ALL_USER_MASTER.equals(flowId))
		{
		 List<UserMasterRoleEntity> list=userMasterRoleDAO.load();
		 userMasterRoleOutputMessage=new UserMasterRoleOutputMessage();
	     List<UserMasterRoleDTO> userDTOList=convertUserEntityToUserDTO(list);
	     userMasterRoleOutputMessage.setUserMasterRoleDTOList(userDTOList);
	    }
	  
	  
	  else if(CREATE_USER_MASTER_ROLE.equals(flowId))
	   {
	   UserMasterRoleEntity userMasterRoleEntities=new UserMasterRoleEntity();
	   if(userMasterRoleInputMassage!=null)
		{
	   UserMasterRoleDTO userMasterRoleDTO=userMasterRoleInputMassage.getUserMasterRoleDTO();
	   try{
		 BeanUtils.copyProperties(userMasterRoleDTO, userMasterRoleEntities);
	   }catch (Exception e) {
			  e.printStackTrace();
	   }
		 RoleMasterEntity roleMasterEntity=new RoleMasterEntity();
		 List<UserMasterRoleEntity > urEntity=userMasterRoleDAO.checkUserDuplicateEntry(userMasterRoleEntities.getUserLoginId());
	   if( urEntity!=null && urEntity.size()>0 )
		  {
			 ErrorDTO errorDTO=new ErrorDTO("1","Sorry, Record already exist, Duplicate entries are not allowed.");
			   ErrorListDTO errorListDTO=new ErrorListDTO();
			   errorListDTO.addError(errorDTO);
			   userMasterRoleOutputMessage=new UserMasterRoleOutputMessage();
			   userMasterRoleOutputMessage.setErrorListDTO(errorListDTO);
		  }
	    else
		  {
		 if(userMasterRoleEntities.getRoleMasterEntity()!=null)
		  {
			 roleMasterEntity.setRoleId(userMasterRoleDTO.getRoleMasterDTO().getRoleId());
		   userMasterRoleEntities.setRoleMasterEntity(roleMasterEntity);
		  }
		  userMasterRoleOutputMessage=new UserMasterRoleOutputMessage();
		  userMasterRoleOutputMessage.setErrorListDTO(null);
		  userMasterRoleDAO.create(userMasterRoleEntities);
	    }
	   }
	  }
	  
	 else if(SEARCH_USER_MASTER_ROLE.equals(flowId))
	  { 
		userMasterRoleOutputMessage=new UserMasterRoleOutputMessage();
		UserMasterRoleEntity userMasterRoleEntities=new UserMasterRoleEntity();
		RoleMasterEntity roleMasterEntity=new RoleMasterEntity();
		if(userMasterRoleInputMassage!=null)
		{
		 UserMasterRoleDTO userMasterRoleDTO=userMasterRoleInputMassage.getUserMasterRoleDTO();
		 try{
		 BeanUtils.copyProperties(userMasterRoleDTO, userMasterRoleEntities);
		 }
		 catch (Exception e) {
			e.printStackTrace();
		}
		 if(userMasterRoleDTO.getRoleMasterDTO()!=null)
		 {
			 roleMasterEntity.setRoleName(userMasterRoleDTO.getRoleMasterDTO().getRoleName()); 
		  userMasterRoleEntities.setRoleMasterEntity(roleMasterEntity);
		 }
	    List<UserMasterRoleEntity> searchList=userMasterRoleDAO.searchUserMaster(userMasterRoleDTO.getUserFullName(),userMasterRoleDTO.getRoleMasterDTO().getRoleName());
	    
	    List<UserMasterRoleDTO> userMasterRoleDTOList=convertUserEntityToUserDTO(searchList);
	    userMasterRoleOutputMessage.setUserMasterRoleDTOList(userMasterRoleDTOList);
	   }
	 }
	  
	 else if(FIND_USER_MAX_ID.equals(flowId))
	 {
	   List<UserMasterRoleEntity> roleEntities=userMasterRoleDAO.findLastUserMasterId();
	   userMasterRoleOutputMessage=new UserMasterRoleOutputMessage();
	   List<UserMasterRoleDTO> roleDTOList=convertUserEntityToUserDTO(roleEntities);
	   userMasterRoleOutputMessage.setUserMasterRoleDTOList(roleDTOList);
     }
	 else if(GET_USER_INFO.equals(flowId))
	 {
		 
	  if(userMasterRoleInputMassage!=null){
		  UserMasterRoleDTO masterRoleDTO=userMasterRoleInputMassage.getUserMasterRoleDTO();
		  List<UserMasterRoleEntity> roleEntities=userMasterRoleDAO.getUserInfo(masterRoleDTO.getUserLoginId());
		  userMasterRoleOutputMessage=new UserMasterRoleOutputMessage();
		   List<UserMasterRoleDTO> roleDTOList=convertUserEntityToUserDTO(roleEntities);
		   userMasterRoleOutputMessage.setUserMasterRoleDTOList(roleDTOList);
	  }
     }
	
	
	 else if(UPDATE_USER_MASTER_ROLE.equals(flowId))
     {
      UserMasterRoleEntity userMasterRoleEntities=new UserMasterRoleEntity();
   	  if(userMasterRoleInputMassage!=null)
   	   {
   	   UserMasterRoleDTO userMasterRoleDTO=userMasterRoleInputMassage.getUserMasterRoleDTO();
   	   try{
   		BeanUtils.copyProperties(userMasterRoleDTO, userMasterRoleEntities);
   		RoleMasterEntity roleMasterEntity=new RoleMasterEntity();
   		if(userMasterRoleEntities.getRoleMasterEntity()!=null)
   		{
   			
   		 roleMasterEntity.setRoleId(userMasterRoleDTO.getRoleMasterDTO().getRoleId());
   		 userMasterRoleEntities.setRoleMasterEntity(roleMasterEntity);
   		}
   		userMasterRoleOutputMessage=new UserMasterRoleOutputMessage();
   		userMasterRoleOutputMessage.setErrorListDTO(null);
   		userMasterRoleDAO.update(userMasterRoleEntities);
   	   }catch (Exception e) {
   	    e.printStackTrace();
   	   }}
   	  }
	  
	  
	else if(FIND_USER_MASTER_ROLE_BY_ID.equals(flowId))
	 {
	 UserMasterRoleEntity userMasterRoleEntity=new UserMasterRoleEntity();	
	 UserMasterRoleDTO userMasterRoleDTO =new UserMasterRoleDTO();
	
	 if(userMasterRoleInputMassage!=null)
	  {
		userMasterRoleDTO=userMasterRoleInputMassage.getUserMasterRoleDTO();
		BeanUtils.copyProperties(userMasterRoleDTO, userMasterRoleEntity);
	  }
	  List<UserMasterRoleEntity> list=userMasterRoleDAO.getUserMasterRoleById(userMasterRoleEntity.getUserId());
	 
	  if(list!=null)
	   {
	   List<UserMasterRoleDTO> userMasterRoleDTOList= convertUserEntityToUserDTO(list);
	   userMasterRoleOutputMessage=new UserMasterRoleOutputMessage();
	   userMasterRoleOutputMessage.setUserMasterRoleDTOList(userMasterRoleDTOList);
	   }
	  }
	  
	else if(DELETE_USER_MASTER_ROLE.equals(flowId))
	 {UserMasterRoleDTO userMasterRoleDTO =new UserMasterRoleDTO();
	
	  UserMasterRoleEntity userMasterRoleEntity=new UserMasterRoleEntity();	
	  
      if (userMasterRoleInputMassage!=null)
      {
       userMasterRoleDTO=userMasterRoleInputMassage.getUserMasterRoleDTO();
       BeanUtils.copyProperties(userMasterRoleDTO, userMasterRoleEntity);
       userMasterRoleDAO.delete(userMasterRoleEntity);	
	  }
     }
    }
	
	
	@Override
	public void formatOutput() {
		if(FIND_ALL_USER_MASTER.equals(flowId))
		{}
		if(CREATE_USER_MASTER_ROLE.equals(flowId))
		{}
		if(SEARCH_USER_MASTER_ROLE.equals(flowId))
		{}
		if(FIND_USER_MAX_ID.equals(flowId))
		{}
		if(UPDATE_USER_MASTER_ROLE.equals(flowId))
		{}
		if(FIND_USER_MASTER_ROLE_BY_ID.equals(flowId))
		{}
		if(DELETE_USER_MASTER_ROLE.equals(flowId))
		{}
	}

	@Override
	public boolean validateInput() {
		if(FIND_ALL_USER_MASTER.equals(flowId))
		{
			return true;
		}
		else if(CREATE_USER_MASTER_ROLE.equals(flowId))
		{
			return true;
		}
		else if(SEARCH_USER_MASTER_ROLE.equals(flowId))
		{
			return true;
		}
		else if(FIND_USER_MAX_ID.equals(flowId))
		{
			return true;
		}
		else if(UPDATE_USER_MASTER_ROLE.equals(flowId))
		{
			return true;
		}
		else if(FIND_USER_MASTER_ROLE_BY_ID.equals(flowId))
		{
			return true;
		}
		else if(DELETE_USER_MASTER_ROLE.equals(flowId))
		{
			return true;
		}else if(GET_USER_INFO.equals(flowId))
		{
			return true;
		}
		
		return false;
	}

  private List<UserMasterRoleDTO> convertUserEntityToUserDTO(List<UserMasterRoleEntity> list)
  {
	List<UserMasterRoleDTO> resultList=new ArrayList<UserMasterRoleDTO>();
	UserMasterRoleDTO userMasterRoleDTO;
	for(UserMasterRoleEntity entity:list)
	 {
	 userMasterRoleDTO=new UserMasterRoleDTO();
	 userMasterRoleDTO.setRoleMasterDTO(new RoleMasterDTO());
	 try{
	   BeanUtils.copyProperties(entity,userMasterRoleDTO );  
	   if(entity.getRoleMasterEntity()!=null)
	   {
		 BeanUtils.copyProperties(entity.getRoleMasterEntity(), userMasterRoleDTO.getRoleMasterDTO());
	   }
	   resultList.add(userMasterRoleDTO); 
	  }
	  catch (Exception e) {
		  e.printStackTrace();
	 }}
	  return resultList;
  }


}
