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
import com.advanz.erp.masters.entity.jpa.ItemCategoryEntity;
import com.advanz.erp.masters.entity.jpa.ItemGroupEntity;
import com.advanz.erp.masters.model.ItemCategoryDTO;
import com.advanz.erp.masters.model.ItemGroupDTO;
import com.advanz.erp.masters.model.msg.ItemCategoryInputMessage;
import com.advanz.erp.masters.model.msg.ItemCategoryOutMessage;
import com.advanz.erp.masters.model.msg.TransporterInputMessage;
import com.advanz.erp.masters.model.msg.TransporterOutMessage;
import com.advanz.erp.masters.service.business.IItemCategoryService;
import com.advanz.erp.masters.storage.IStorageItemCategoryDAO;

@Service
public class ItemCategoryServiceImpl implements IItemCategoryService {

	
	public static final String UPDATE_ITEM_CATEGORY = "UpdateItemCategory";
	public static final String ADD_ITEM_CATEGORY = "AddItemCategory";
	public static final String DELETE_ITEM_CATEGORY = "DeleteItemCategory";
	public static final String FIND_ITEM_CATEGORY_BY_ID = "FindItemCategoryById";
	public static final String FIND_ALL_ITEM_CATEGORIES = "FindAllCompnies";
	public static final String FIND_CATEGORIES = "FindCategories";
	public static final String FIND_ITEM_CATEGORY_BY_NAME = "FindItemCategoryByName";
	public static final String PRE_REMOVE_CHECK="PreRemoveCheck";
	public static final String FIND_ITEM_CATEGORY_BY_ITEM_GROUP="FindItemCategoryByItemGroup";
	
	public String flowId = "";

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
	private static final Logger logger = LoggerFactory
	.getLogger(ItemCategoryServiceImpl.class);																						// autowiring

	@Autowired
	public IStorageItemCategoryDAO storageItemCategoryDAO;

	public ItemCategoryInputMessage itemCategoryInputMessage;

	public ItemCategoryOutMessage itemCategoryOutMessage;

	@Override
	public ItemCategoryOutMessage createItemCategory(ItemCategoryInputMessage itemCategoryInputMessage) {

		flowId = ADD_ITEM_CATEGORY;

		// assign the message to the class level variable.
		this.itemCategoryInputMessage = itemCategoryInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return itemCategoryOutMessage;
	}

	@Override
	public ItemCategoryOutMessage updateItemCategory(ItemCategoryInputMessage itemCategoryInputMessage) {

		flowId = UPDATE_ITEM_CATEGORY;
		this.itemCategoryInputMessage = itemCategoryInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return itemCategoryOutMessage;
	}

	@Override
	public ItemCategoryOutMessage deleteItemCategory(ItemCategoryInputMessage itemCategoryInputMessage) {
		flowId = DELETE_ITEM_CATEGORY;
		this.itemCategoryInputMessage = itemCategoryInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return itemCategoryOutMessage;

	}

	@Override
	public ItemCategoryOutMessage findItemCategoryByItemCategoryId(
			ItemCategoryInputMessage itemCategoryInputMessage) {
		flowId = FIND_ITEM_CATEGORY_BY_ID;
		this.itemCategoryInputMessage = itemCategoryInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemCategoryOutMessage;

	}
	@Override
	public ItemCategoryOutMessage findItemCategoryByItemCategoryName(
			ItemCategoryInputMessage itemCategoryInputMessage) {
		flowId = FIND_ITEM_CATEGORY_BY_NAME;
		this.itemCategoryInputMessage = itemCategoryInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemCategoryOutMessage;

	}

	@Override
	public ItemCategoryOutMessage findAllItemCategories() {
		flowId = FIND_ALL_ITEM_CATEGORIES;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemCategoryOutMessage;
	}
	@Override
	public ItemCategoryOutMessage findItemCategory(ItemCategoryInputMessage itemCategoryInputMessage) {
		flowId = FIND_CATEGORIES;
		this.itemCategoryInputMessage = itemCategoryInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemCategoryOutMessage;
	}
	
	@Override
	public ItemCategoryOutMessage checkBeforeRemove(ItemCategoryInputMessage itemCategoryInputMessage) {
		flowId = PRE_REMOVE_CHECK;
		this.itemCategoryInputMessage = itemCategoryInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemCategoryOutMessage;
	}
	
	
	@Override
	public ItemCategoryOutMessage findItemCategoryByItemGroup(
			ItemCategoryInputMessage itemCategoryInputMessage)
			{
			flowId = FIND_ITEM_CATEGORY_BY_ITEM_GROUP;
			this.itemCategoryInputMessage = itemCategoryInputMessage;
			// call the template method
			advanzErpServiceTemplate.execute(this);
			return itemCategoryOutMessage;
		
	}
	
	@Override
	public boolean validateInput() {

		if (ADD_ITEM_CATEGORY.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_ITEM_CATEGORY.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_ITEM_CATEGORY.equals(flowId)) {
			
			return true;
		} else if (FIND_ITEM_CATEGORY_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_ITEM_CATEGORIES.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (FIND_CATEGORIES.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (FIND_ITEM_CATEGORY_BY_NAME.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}else if (PRE_REMOVE_CHECK.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (FIND_ITEM_CATEGORY_BY_ITEM_GROUP.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		return false;
	}

	@Override
	public void performBusinessLogic() {

		ItemCategoryEntity itemCategoryEntity = new ItemCategoryEntity();
		if (itemCategoryInputMessage != null) {
			ItemCategoryDTO itemCategoryDTO = itemCategoryInputMessage.getItemCategoryDTO();
			if (itemCategoryDTO != null) {
				BeanUtils.copyProperties(itemCategoryDTO, itemCategoryEntity);
				ItemGroupEntity itemGroupEntity =new ItemGroupEntity();
				if(itemCategoryDTO.getItemGroupDTO()!=null)
				itemGroupEntity .setItemGroupId(itemCategoryDTO.getItemGroupDTO().getItemGroupId());
				itemCategoryEntity.setItemGroupEntity(itemGroupEntity);			
			}
		}
		// BeanUtils.copyProperties(itemCategoryInputMessage.getItemCategoryDTO(),
		// itemCategoryEntity);

		if (ADD_ITEM_CATEGORY.equals(flowId)) {
			// ItemCategoryDTO itemCategoryDTO = itemCategoryInputMessage.getItemCategoryDTO();
			// BeanUtils.copyProperties(itemCategoryDTO, itemCategoryEntity);
			List<ItemCategoryEntity> list1=storageItemCategoryDAO.findByNameAndCode(itemCategoryEntity.getItemCategoryName(),null);
			List<ItemCategoryEntity> list2=storageItemCategoryDAO.findByNameAndCode(null,itemCategoryEntity.getItemCategoryCode());
			
		
			itemCategoryOutMessage = new ItemCategoryOutMessage();
			boolean errors = false;
			if(list1!=null && list1.size()>0){				
				ErrorDTO errorDTO=new ErrorDTO("1","Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO=new ErrorListDTO();
				errorListDTO.addError(errorDTO);				
				itemCategoryOutMessage.setErrorListDTO(errorListDTO);
				errors = true;
			}
			if(list2!=null && list2.size()>0){				
				ErrorDTO errorDTO=new ErrorDTO("2","Sorry, Record already exist, Duplicate entries are not allowed.");
				if (!errors) {
					ErrorListDTO errorListDTO = new ErrorListDTO();
					errorListDTO.addError(errorDTO);
					itemCategoryOutMessage.setErrorListDTO(errorListDTO);
				} else {
					itemCategoryOutMessage.getErrorListDTO().addError(errorDTO);
				}				
				errors = true;
			}
			if(!errors)
			{
				itemCategoryOutMessage.setErrorListDTO(null);
				storageItemCategoryDAO.create(itemCategoryEntity);
			}
			
			
		} else if (UPDATE_ITEM_CATEGORY.equals(flowId)) {
			//logger.info("Updating Item category "+itemCategoryEntity);
			List<ItemCategoryEntity> list=storageItemCategoryDAO.findByNameAndCode(itemCategoryEntity.getItemCategoryName(),itemCategoryEntity.getItemCategoryCode());
			
		//	logger.info(flowId +": "+list);			
			itemCategoryOutMessage = new ItemCategoryOutMessage();
			if(list!=null && list.size()>0 && !list.get(0).getItemCategoryId().equals(itemCategoryEntity.getItemCategoryId())){
				
				ErrorDTO errorDTO=new ErrorDTO("1","Sorry, Record already exist, Duplicate entries are not allowed.");
				ErrorListDTO errorListDTO=new ErrorListDTO();
				errorListDTO.addError(errorDTO);				
				itemCategoryOutMessage.setErrorListDTO(errorListDTO);
			}else{
				logger.info("**Updating Item category for :"+itemCategoryEntity.getItemCategoryName()+" , "+itemCategoryEntity.getItemCategoryCode());
				itemCategoryOutMessage.setErrorListDTO(null);
				storageItemCategoryDAO.update(itemCategoryEntity);
			}
		} else if (DELETE_ITEM_CATEGORY.equals(flowId)) {
			storageItemCategoryDAO.delete(itemCategoryEntity);
		} else if (FIND_ITEM_CATEGORY_BY_ID.equals(flowId)) {
			//System.out.println("------------------------------------Find By Id : "+itemCategoryEntity.getItemCategoryId());
			List<ItemCategoryEntity> list = storageItemCategoryDAO.findById(itemCategoryEntity.getItemCategoryId());
			popUpList(list);
		} 
		else if (FIND_ITEM_CATEGORY_BY_ITEM_GROUP.equals(flowId)) {
		//	System.out.println("------------------------------------Find By Id : "+itemCategoryEntity.getItemCategoryId());
			List<ItemCategoryEntity> list = storageItemCategoryDAO.findByGroupId(itemCategoryEntity.getItemGroupEntity().getItemGroupId());
			popUpList(list);
		}
		
		else if (FIND_ALL_ITEM_CATEGORIES.equals(flowId)) {
			//System.out.println("------------------------------------Loading All");
			List<ItemCategoryEntity> list = storageItemCategoryDAO.load();
			popUpList(list);
		}else if (FIND_CATEGORIES.equals(flowId)) {

			ItemCategoryDTO itemCategoryDto = itemCategoryInputMessage.getItemCategoryDTO();
			System.out.println(itemCategoryDto.toString());
			List<ItemCategoryEntity> list = storageItemCategoryDAO.search(
					itemCategoryDto.getItemCategoryId(),
					itemCategoryDto.getItemCategoryCode(),
					itemCategoryDto.getItemCategoryName());
			itemCategoryOutMessage = new ItemCategoryOutMessage();
			
			
			popUpList(list);
		}else if(PRE_REMOVE_CHECK.equals(flowId)){
			if(storageItemCategoryDAO.isInUsed(itemCategoryInputMessage.getItemCategoryDTO().getItemCategoryId())){
				logger.info("TRUE");
				itemCategoryOutMessage=new ItemCategoryOutMessage();
				ErrorDTO errorDTO=new ErrorDTO("1","Item Category cannot be removed, It already used in Item Master");
				ErrorListDTO errorListDto=new ErrorListDTO();
				 errorListDto.addError(errorDTO);
				 itemCategoryOutMessage.setErrorListDTO(errorListDto);
			}else{
				itemCategoryOutMessage=null;
			}
		}
	}

	void popUpList(List<ItemCategoryEntity> list) {
		itemCategoryOutMessage = new ItemCategoryOutMessage();
		// set the data to the output message.
		if (list != null) {
			List<ItemCategoryDTO> resultList = new ArrayList<ItemCategoryDTO>();
			ItemCategoryDTO itemCategoryDTO;
			for (ItemCategoryEntity entity : list) {
				itemCategoryDTO = new ItemCategoryDTO();
				//Spring
				itemCategoryDTO.setItemGroupDTO(new ItemGroupDTO());
				BeanUtils.copyProperties(entity, itemCategoryDTO);
				if(entity.getItemGroupEntity()!=null){
				BeanUtils.copyProperties(entity.getItemGroupEntity(),itemCategoryDTO.getItemGroupDTO());
				}
				resultList.add(itemCategoryDTO);

			}
			itemCategoryOutMessage.setItemCategoryDTOList(resultList);
		}

	}

	@Override
	public void formatOutput() {

		if (ADD_ITEM_CATEGORY.equals(flowId)) {

		} else if (UPDATE_ITEM_CATEGORY.equals(flowId)) {

		} else if (DELETE_ITEM_CATEGORY.equals(flowId)) {

		} else if (FIND_ITEM_CATEGORY_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_ITEM_CATEGORIES.equals(flowId)) {
			
		}
		else if (FIND_ITEM_CATEGORY_BY_ITEM_GROUP.equals(flowId)) {

		}
	}

	

}
