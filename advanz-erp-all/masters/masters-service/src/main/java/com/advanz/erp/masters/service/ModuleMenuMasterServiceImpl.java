package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.List;

import javassist.expr.NewArray;

import org.hibernate.mapping.Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.BillDetailEntity;
import com.advanz.erp.masters.entity.jpa.ModuleMenuMasterEntity;
import com.advanz.erp.masters.model.BillDetailDTO;
import com.advanz.erp.masters.model.ModuleMenuMasterDTO;
import com.advanz.erp.masters.model.msg.BillDetailOutMessage;
import com.advanz.erp.masters.model.msg.ModuleMenuMasterInputMessage;
import com.advanz.erp.masters.model.msg.ModuleMenuMasterOutMessage;
import com.advanz.erp.masters.service.business.IModuleMenuMasterService;
import com.advanz.erp.masters.storage.IStorageModuleMenuMasterDAO;

@Service
public class ModuleMenuMasterServiceImpl implements IModuleMenuMasterService {

	public static final String UPDATE_MODULE_MASTER = "UpdateModuleMaster";
	public static final String ADD_MODULE_MASTER = "AddModuleMaster";
	public static final String DELETE_MODULE_MASTER = "DeleteModuleMaster";
	public static final String FIND_ALL__MODULE_MASTER = "FindAllModuleMaster";
	public static final String FIND_MODULE_MASTER_BY_MODULE_NAME = "FindModuleMasterByModuleName";
	public static final String FIND_MODULE_MASTER_BY_ID = "FindModuleMasterById";
	public static final String FIND_MODULE_MASTER_BY_SUB_MODULE_NAME = "FindModuleMasterBySubModuleName";
	public static final String GET_REPORT_LINK_LIST = "GetReportLinkList";
	public static final String SEARCH = "Search";
	public String flowId = "";

	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO

	private static final Logger logger = LoggerFactory
			.getLogger(ModuleMenuMasterServiceImpl.class);

	@Autowired
	public IStorageModuleMenuMasterDAO storageModuleMenuMasterDAO;
	public ModuleMenuMasterInputMessage moduleMenuMasterInputMessage;
	public ModuleMenuMasterOutMessage moduleMenuMasterOutMessage;

	@Override
	public boolean validateInput() {
		if (UPDATE_MODULE_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (ADD_MODULE_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_MODULE_MASTER.equals(flowId)) {
			
			return true;
		} else if (FIND_ALL__MODULE_MASTER.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_MODULE_MASTER_BY_MODULE_NAME.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (FIND_MODULE_MASTER_BY_SUB_MODULE_NAME.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (FIND_MODULE_MASTER_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (GET_REPORT_LINK_LIST.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (SEARCH.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		
		return false;

	}

	@Override
	public void performBusinessLogic() {
		ModuleMenuMasterEntity moduleMasterEntity = new ModuleMenuMasterEntity();
		if(moduleMenuMasterInputMessage!=null){
			ModuleMenuMasterDTO moduleMasterDTO= moduleMenuMasterInputMessage.getModuleMenuMasterDTO();
			if(moduleMasterDTO!=null){
				
				BeanUtils.copyProperties(moduleMenuMasterInputMessage.getModuleMenuMasterDTO(),moduleMasterEntity);
				
			}
			
		}
		if (ADD_MODULE_MASTER.equals(flowId)) {
			storageModuleMenuMasterDAO.create(moduleMasterEntity);
			
		}
		else if (UPDATE_MODULE_MASTER.equals(flowId)) {
			try {
				BeanUtils.copyProperties(moduleMenuMasterInputMessage.getModuleMenuMasterDTO(),moduleMasterEntity);
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			storageModuleMenuMasterDAO.update(moduleMasterEntity);
		}
		 else if (DELETE_MODULE_MASTER.equals(flowId)) {
				try {
					BeanUtils.copyProperties(moduleMenuMasterInputMessage.getModuleMenuMasterDTO(),moduleMasterEntity);
				} catch (BeansException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				storageModuleMenuMasterDAO.delete(moduleMasterEntity);
			}
		 else if (FIND_ALL__MODULE_MASTER.equals(flowId)) {
				List<ModuleMenuMasterEntity> list = storageModuleMenuMasterDAO.load();
				moduleMenuMasterOutMessage = new ModuleMenuMasterOutMessage();
				// set the data to the output message.
				if (list != null) {
					List<ModuleMenuMasterDTO> resultList = convertModuleMasterEntityListTOModuleMasterDtoList(list);
					moduleMenuMasterOutMessage.setModuleMenuMasterDTOList(resultList);
				}
			}
		
		 else if (FIND_MODULE_MASTER_BY_MODULE_NAME.equals(flowId)) {
			 ModuleMenuMasterDTO menuMasterDTO = moduleMenuMasterInputMessage.getModuleMenuMasterDTO();
				List<ModuleMenuMasterEntity> list = storageModuleMenuMasterDAO.findModuleMasterByName(menuMasterDTO.getModuleName(),menuMasterDTO.getLoginUserName());
				moduleMenuMasterOutMessage = new ModuleMenuMasterOutMessage();
				if (list != null) {
					List<ModuleMenuMasterDTO> resultList = convertModuleMasterEntityListTOModuleMasterDtoList(list);
					moduleMenuMasterOutMessage.setModuleMenuMasterDTOList(resultList);
				}
			}
		 else if (FIND_MODULE_MASTER_BY_ID.equals(flowId)) {
			ModuleMenuMasterDTO menuMasterDTO = moduleMenuMasterInputMessage.getModuleMenuMasterDTO();
		//	System.out.println("MennuId in Module==========================-------------???????"+menuMasterDTO.getMenuId());
			ModuleMenuMasterEntity entity = storageModuleMenuMasterDAO.read(menuMasterDTO.getMenuId());
		//	System.out.println("MennuId in Module==========================-------------???????"+entity.getMenuId());
			moduleMenuMasterOutMessage = new ModuleMenuMasterOutMessage();
			List<ModuleMenuMasterEntity> entityList=new ArrayList<ModuleMenuMasterEntity>();
			entityList.add(entity);
			if (entityList != null) {
				List<ModuleMenuMasterDTO> resultList = convertModuleMasterEntityListTOModuleMasterDtoList(entityList);
				moduleMenuMasterOutMessage.setModuleMenuMasterDTOList(resultList);					
				}
			}
		 else if (FIND_MODULE_MASTER_BY_SUB_MODULE_NAME.equals(flowId)) {
			 ModuleMenuMasterDTO menuMasterDTO = moduleMenuMasterInputMessage.getModuleMenuMasterDTO();
				List<ModuleMenuMasterEntity> list = storageModuleMenuMasterDAO.findModuleMasterBySubMenuName(menuMasterDTO.getModuleName(),menuMasterDTO.getLoginUserName());
				moduleMenuMasterOutMessage = new ModuleMenuMasterOutMessage();
				if (list != null) {
					List<ModuleMenuMasterDTO> resultList = convertModuleMasterEntityListTOModuleMasterDtoList(list);
					moduleMenuMasterOutMessage.setModuleMenuMasterDTOList(resultList);
				}
			}
		 else if (GET_REPORT_LINK_LIST.equals(flowId)) {
			 ModuleMenuMasterDTO menuMasterDTO = moduleMenuMasterInputMessage.getModuleMenuMasterDTO();
				List<ModuleMenuMasterEntity> list = storageModuleMenuMasterDAO.getReportLinkList(menuMasterDTO.getLoginUserName());
				moduleMenuMasterOutMessage = new ModuleMenuMasterOutMessage();
				if (list != null) {
					List<ModuleMenuMasterDTO> resultList = convertModuleMasterEntityListTOModuleMasterDtoList(list);
					moduleMenuMasterOutMessage.setModuleMenuMasterDTOList(resultList);
				}
			} else if (SEARCH.equals(flowId)) {
				 ModuleMenuMasterDTO menuMasterDTO = moduleMenuMasterInputMessage.getModuleMenuMasterDTO();
				 
				 String loginUserName=null;
				 String menuName=null;
				 String subMenuName=null;
				 String description=null;
				 if(menuMasterDTO.getLoginUserName()!=null){
					 loginUserName= menuMasterDTO.getLoginUserName(); 
				 }
				 if(menuMasterDTO.getMenuName()!=null){
					 menuName= menuMasterDTO.getMenuName(); 
				 } if(menuMasterDTO.getSubMenuName()!=null){
					 subMenuName= menuMasterDTO.getSubMenuName(); 
				 }if(menuMasterDTO.getDescription()!=null){
					 description= menuMasterDTO.getDescription(); 
				 }
					List<ModuleMenuMasterEntity> list = storageModuleMenuMasterDAO.search(loginUserName, menuName, subMenuName, description);
					moduleMenuMasterOutMessage = new ModuleMenuMasterOutMessage();
					if (list != null) {
						List<ModuleMenuMasterDTO> resultList = convertModuleMasterEntityListTOModuleMasterDtoList(list);
						moduleMenuMasterOutMessage.setModuleMenuMasterDTOList(resultList);
					}
				}
		
		
         	}

	
	private List<ModuleMenuMasterDTO> convertModuleMasterEntityListTOModuleMasterDtoList(
			List<ModuleMenuMasterEntity> list) {

		moduleMenuMasterOutMessage = new ModuleMenuMasterOutMessage();
		List<ModuleMenuMasterDTO> resultList = null;
		// set the data to the outputput message.
		if (list != null) {
			ModuleMenuMasterDTO moduleMenuMasterDTO;
			resultList = new ArrayList<ModuleMenuMasterDTO>();
			for (ModuleMenuMasterEntity moduleMenuMasterEntity : list) {
				moduleMenuMasterDTO = new ModuleMenuMasterDTO();
				
				if (moduleMenuMasterEntity != null) {
					BeanUtils.copyProperties(moduleMenuMasterEntity, moduleMenuMasterDTO);
				resultList.add(moduleMenuMasterDTO);
				}
			}
		}

		return resultList;
	}

	@Override
	public void formatOutput() {
		// TODO Auto-generated method stub

	}

	@Override
	public ModuleMenuMasterOutMessage createModuleMenuMaster(
			ModuleMenuMasterInputMessage moduleMenuMasterInputMessage) {
		flowId = ADD_MODULE_MASTER;
		// assign the message to the class level variable.
		this.moduleMenuMasterInputMessage = moduleMenuMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return moduleMenuMasterOutMessage;
	}

	@Override
	public ModuleMenuMasterOutMessage updateModuleMenuMaster(
			ModuleMenuMasterInputMessage moduleMenuMasterInputMessage) {
		flowId = UPDATE_MODULE_MASTER;
		this.moduleMenuMasterInputMessage = moduleMenuMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return moduleMenuMasterOutMessage;
	}

	@Override
	public ModuleMenuMasterOutMessage deleteModuleMenuMaster(ModuleMenuMasterInputMessage moduleMenuMasterInputMessage) {
		flowId = DELETE_MODULE_MASTER;
		this.moduleMenuMasterInputMessage = moduleMenuMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return moduleMenuMasterOutMessage;
	}

	@Override
	public ModuleMenuMasterOutMessage findAllModuleMenuMaster() {
		flowId=FIND_ALL__MODULE_MASTER;
		this.moduleMenuMasterInputMessage = moduleMenuMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return moduleMenuMasterOutMessage;
	}

	@Override
	public ModuleMenuMasterOutMessage findModuleMenuMasterByModuleName(
			ModuleMenuMasterInputMessage moduleMenuMasterInputMessage) {
		flowId=FIND_MODULE_MASTER_BY_MODULE_NAME;
		this.moduleMenuMasterInputMessage = moduleMenuMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return moduleMenuMasterOutMessage;
	}

	@Override
	public ModuleMenuMasterOutMessage findModuleMenuMasterBySubModuleName(ModuleMenuMasterInputMessage moduleMenuMasterInputMessage) {
		flowId=FIND_MODULE_MASTER_BY_SUB_MODULE_NAME;
		this.moduleMenuMasterInputMessage = moduleMenuMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return moduleMenuMasterOutMessage;
	   }

	@Override
	public ModuleMenuMasterOutMessage findModuleMenuMasterByRoleId(ModuleMenuMasterInputMessage moduleMenuMasterInputMessage)
	{
		flowId=FIND_MODULE_MASTER_BY_ID;
		this.moduleMenuMasterInputMessage = moduleMenuMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return moduleMenuMasterOutMessage;

	}
	
	@Override
	public ModuleMenuMasterOutMessage getReportLinkList(
			ModuleMenuMasterInputMessage moduleMenuMasterInputMessage) {
		flowId=GET_REPORT_LINK_LIST;
		this.moduleMenuMasterInputMessage = moduleMenuMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return moduleMenuMasterOutMessage;
	}

	@Override
	public ModuleMenuMasterOutMessage search(
			ModuleMenuMasterInputMessage moduleMenuMasterInputMessage) {
		flowId=SEARCH;
		this.moduleMenuMasterInputMessage = moduleMenuMasterInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return moduleMenuMasterOutMessage;
	}
    }
