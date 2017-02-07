package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.ItemCategoryEntity;
import com.advanz.erp.masters.entity.jpa.ItemGroupFlagEntity;
import com.advanz.erp.masters.model.ItemCategoryDTO;
import com.advanz.erp.masters.model.ItemGroupDTO;
import com.advanz.erp.masters.model.ItemGroupFlagDTO;
import com.advanz.erp.masters.model.msg.ItemGroupFlagInputMessage;
import com.advanz.erp.masters.model.msg.ItemGroupFlagOutMessage;
import com.advanz.erp.masters.service.business.IItemGroupFlagService;
import com.advanz.erp.masters.storage.IStorageItemGroupFlagDAO;

@Service
public class ItemGroupFlagServiceImpl implements IItemGroupFlagService {
	public static final String FIND_ALL_ITEM_GROUP_FLAG = "FindAllItemGroupFlag";
	public static final String FIND_ITEM_GROUP_FLAG_BY_NAME = "FindAllItemGroupByName";
	public static final String FIND_ITEM_GROUP_FLAG_BY_ID = "FindAllItemGroupById";

	public String flowId = "";

	 //@Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
	private static final Logger logger = LoggerFactory
	.getLogger(ItemGroupFlagServiceImpl.class);																						// autowiring

	@Autowired
	public IStorageItemGroupFlagDAO storageItemGroupFlagDAO;

	public ItemGroupFlagInputMessage itemGroupFlagInputMessage;

	public ItemGroupFlagOutMessage itemGroupFlagOutMessage;

	
	@Override
	public ItemGroupFlagOutMessage findItemGroupFlagByName(ItemGroupFlagInputMessage flagInputMessage) {
		flowId = FIND_ITEM_GROUP_FLAG_BY_NAME;
		this.itemGroupFlagInputMessage=flagInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemGroupFlagOutMessage;
	}
	
	

	@Override
	public ItemGroupFlagOutMessage findAllItemGroupFlag() {
		flowId = FIND_ALL_ITEM_GROUP_FLAG;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemGroupFlagOutMessage;
	}
	@Override
	public ItemGroupFlagOutMessage findItemGroupFlagById(
			ItemGroupFlagInputMessage flagInputMessage) {
		flowId = FIND_ITEM_GROUP_FLAG_BY_ID;
		this.itemGroupFlagInputMessage=flagInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemGroupFlagOutMessage;
	}
	@Override
	public void performBusinessLogic() {

	if (FIND_ALL_ITEM_GROUP_FLAG.equals(flowId)) {
			System.out.println("------------------------------------Loading All");
			List<ItemGroupFlagEntity> list = storageItemGroupFlagDAO.load();
			popUpList(list);
		}
	
	if (FIND_ITEM_GROUP_FLAG_BY_NAME.equals(flowId)) {
		if(itemGroupFlagInputMessage!=null)
		{
		ItemGroupFlagDTO flagDTOList=itemGroupFlagInputMessage.getItemGroupFlagDTO();
		List<ItemGroupFlagEntity> list = storageItemGroupFlagDAO.findIdByName(flagDTOList.getItemGroupFlagName());
	  	popUpList(list);
		}
      }if (FIND_ITEM_GROUP_FLAG_BY_ID.equals(flowId)) {
		if(itemGroupFlagInputMessage!=null)
		{
		ItemGroupFlagDTO flagDTOList=itemGroupFlagInputMessage.getItemGroupFlagDTO();
		ItemGroupFlagEntity entity = storageItemGroupFlagDAO.read(flagDTOList.getItemGroupFlagId());
		List<ItemGroupFlagEntity> list =new ArrayList<ItemGroupFlagEntity>();
		list.add(entity);
		popUpList(list);
		}
      }
	
	}

	void popUpList(List<ItemGroupFlagEntity> list) {
		itemGroupFlagOutMessage = new ItemGroupFlagOutMessage();
		// set the data to the output message.
		if (list != null) {
			List<ItemGroupFlagDTO> resultList = new ArrayList<ItemGroupFlagDTO>();
			ItemGroupFlagDTO itemGroupFlagDTO;
			for (ItemGroupFlagEntity entity : list) {
				itemGroupFlagDTO = new ItemGroupFlagDTO();
				//Spring
				
				BeanUtils.copyProperties(entity, itemGroupFlagDTO);
				
				resultList.add(itemGroupFlagDTO);

			}
			itemGroupFlagOutMessage.setItemGroupFlagDTOList(resultList);
		}

	}

	@Override
	public boolean validateInput() {
		if (FIND_ITEM_GROUP_FLAG_BY_NAME.equals(flowId)) {
			return true;
		}
		if (FIND_ALL_ITEM_GROUP_FLAG.equals(flowId)) {
			return true;
		}if (FIND_ITEM_GROUP_FLAG_BY_ID.equals(flowId)) {
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void formatOutput() {
		// TODO Auto-generated method stub
		
	}



	

}
