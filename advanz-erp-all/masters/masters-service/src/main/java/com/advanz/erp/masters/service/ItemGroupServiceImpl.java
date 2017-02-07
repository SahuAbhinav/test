package com.advanz.erp.masters.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;

import com.advanz.erp.common.model.ErrorDTO;
import com.advanz.erp.common.model.ErrorListDTO;
import com.advanz.erp.common.service.template.AdvanzErpServiceTemplate;
import com.advanz.erp.common.service.template.IAdvanzErpServiceTemplate;
import com.advanz.erp.masters.entity.jpa.ItemCategoryEntity;
import com.advanz.erp.masters.entity.jpa.ItemGroupEntity;
import com.advanz.erp.masters.model.ItemGroupDTO;
import com.advanz.erp.masters.model.MastersDTO;
import com.advanz.erp.masters.model.msg.ItemGroupInputMessage;
import com.advanz.erp.masters.model.msg.ItemGroupOutMessage;
import com.advanz.erp.masters.model.msg.MastersOutputMessage;
import com.advanz.erp.masters.service.business.IItemGroupService;
import com.advanz.erp.masters.storage.IStorageItemCategoryDAO;
import com.advanz.erp.masters.storage.IStorageItemGroupDAO;

public class ItemGroupServiceImpl implements IItemGroupService {

	public static final String CREATE_ITEM_GROUP = "ItemGroup";
	public static final String UPDATE_ITEM_GROUP = "UpdateItemGroup";
	public static final String ADD_ITEM_GROUP = "AddItemGroup";
	public static final String DELETE_ITEM_GROUP = "DeleteItemGroup";
	public static final String FIND_ITEM_GROUP_BY_ID = "FindItemGroupById";
	public static final String FIND_ALL_ITEM_GROUPES = "FindAllItemGroupes";
	public static final String FIND_ITEM_GROUPES = "FindItemGroupes";
	public static final String FIND_ITEM_GROUPE_BY_FLAG_ID = "FindItemGroupeByFlagId";
	public static final String FIND_ITEM_GROUPES_FOR_ITEM_CATEGORY = "FindItemGroupForItemCategory";
	public static final String FIND_ITEM_COUNT = "FindItemCount";
	public String flowId = "";

	private static final Logger logger = LoggerFactory
			.getLogger(ItemGroupServiceImpl.class);

	public void ItemGroupServiceImpl() {
	}

	// @Autowired
	public IAdvanzErpServiceTemplate advanzErpServiceTemplate = new AdvanzErpServiceTemplate();// TODO
																								// do
																								// autowiring

	@Autowired
	public IStorageItemGroupDAO storageItemGroupDAO;

	@Autowired
	public IStorageItemCategoryDAO storageItemCategoryDAO;

	public ItemGroupInputMessage itemGroupInputMessage;

	public ItemGroupOutMessage itemGroupOutMessage;

	@Override
	public ItemGroupOutMessage createItemGroup(
			ItemGroupInputMessage itemGroupInputMessage) {

		flowId = ADD_ITEM_GROUP;
		// assign the message to the class level variable.
		this.itemGroupInputMessage = itemGroupInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return itemGroupOutMessage;
	}

	@Override
	public ItemGroupOutMessage updateItemGroup(
			ItemGroupInputMessage itemGroupInputMessage) {

		flowId = UPDATE_ITEM_GROUP;
		this.itemGroupInputMessage = itemGroupInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return itemGroupOutMessage;
	}

	@Override
	public ItemGroupOutMessage deleteItemGroup(
			ItemGroupInputMessage itemGroupInputMessage) {
		flowId = DELETE_ITEM_GROUP;
		this.itemGroupInputMessage = itemGroupInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);

		return itemGroupOutMessage;

	}

	@Override
	public ItemGroupOutMessage findItemGroupById(
			ItemGroupInputMessage itemGroupInputMessage) {
		flowId = FIND_ITEM_GROUP_BY_ID;
		this.itemGroupInputMessage = itemGroupInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemGroupOutMessage;

	}

	@Override
	public ItemGroupOutMessage findAllItemGroup() {
		flowId = FIND_ALL_ITEM_GROUPES;

		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemGroupOutMessage;
	}

	// @Override
	public ItemGroupOutMessage findItemGroup(
			ItemGroupInputMessage itemGroupInputMessage) {
		flowId = FIND_ITEM_GROUPES;
		this.itemGroupInputMessage = itemGroupInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemGroupOutMessage;
	}

	public ItemGroupOutMessage findItemGroupForItemCategory() {
		flowId = FIND_ITEM_GROUPES_FOR_ITEM_CATEGORY;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemGroupOutMessage;
	}

	@Override
	public ItemGroupOutMessage findItemGroupByFlagId(ItemGroupInputMessage itemGroupInputMessage) {
		flowId = FIND_ITEM_GROUPE_BY_FLAG_ID;
		this.itemGroupInputMessage = itemGroupInputMessage;
		// call the template method
		advanzErpServiceTemplate.execute(this);
		return itemGroupOutMessage;
	}
	
	
	@Override
	public boolean validateInput() {

		if (ADD_ITEM_GROUP.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (UPDATE_ITEM_GROUP.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (DELETE_ITEM_GROUP.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ITEM_GROUP_BY_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} else if (FIND_ALL_ITEM_GROUPES.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		} 
		else if (FIND_ITEM_GROUPES.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		else if (FIND_ITEM_GROUPES.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		
		else if (FIND_ITEM_GROUPES_FOR_ITEM_CATEGORY.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}

		else if (FIND_ITEM_GROUPE_BY_FLAG_ID.equals(flowId)) {
			// TODO add business validation on the input.
			return true;
		}
		return false;
	}

	@Override
	public void performBusinessLogic() {
		ItemGroupEntity itemGroupEntity = new ItemGroupEntity();
		itemGroupOutMessage = new ItemGroupOutMessage();
		// String ignoreProperties[]= {"branchId,servTaxDate,vatDate,cstDate"};
		if (itemGroupInputMessage != null) {
			ItemGroupDTO itemGroupDTO = itemGroupInputMessage.getItemGroupDTO();
			if (itemGroupDTO != null) {
				BeanUtils.copyProperties(itemGroupDTO, itemGroupEntity);
				ItemCategoryEntity itemCategoryEntity = new ItemCategoryEntity();
				// if(itemDTO.getItemCategoryDTO()!=null)
				// itemCategoryEntity
				// .setItemCategoryId(itemDTO.getItemCategoryDTO().getItemCategoryId());
				// itemEntity.setItemCategoryEntity(itemCategoryEntity);
			}
		}

		if (ADD_ITEM_GROUP.equals(flowId)) {
			try {
				List<ItemGroupEntity> list = storageItemGroupDAO.search(
						itemGroupEntity.getItemGroupName(), null);
				List<ItemGroupEntity> list1 = storageItemGroupDAO.search(null,
						itemGroupEntity.getItemGroupCode());
				logger.info(flowId + ": " + list);
				itemGroupOutMessage = new ItemGroupOutMessage();
				ItemGroupDTO itemDTO = itemGroupInputMessage.getItemGroupDTO();
				if ((list != null && list.size() > 0 && !list.get(0).getUid()
						.equals(itemGroupEntity.getUid()))
						|| (list1 != null && list1.size() > 0 && !list1.get(0)
								.getUid().equals(itemGroupEntity.getUid()))) {
					ErrorDTO errorDTO = new ErrorDTO("1",
							"Sorry, Record already exist, Duplicate entries are not allowed.");
					ErrorListDTO errorListDTO = new ErrorListDTO();
					errorListDTO.addError(errorDTO);
					itemGroupOutMessage.setErrorListDTO(errorListDTO);
				} else {
					itemGroupOutMessage.setErrorListDTO(null);
					itemGroupEntity.setDeletedFlag(false);
					storageItemGroupDAO.create(itemGroupEntity);
				}

			} catch (BeansException e) {
				e.printStackTrace();
			}
		} else if (UPDATE_ITEM_GROUP.equals(flowId)) {
			try {
				List<ItemGroupEntity> list = storageItemGroupDAO.search(
						itemGroupEntity.getItemGroupName(), null);
				List<ItemGroupEntity> list1 = storageItemGroupDAO.search(null,
						itemGroupEntity.getItemGroupCode());
				logger.info(flowId + ": " + list);
				itemGroupOutMessage = new ItemGroupOutMessage();
				if ((list != null && list.size() > 0 && !list.get(0).getUid()
						.equals(itemGroupEntity.getUid()))
						|| (list1 != null && list1.size() > 0 && !list1.get(0)
								.getUid().equals(itemGroupEntity.getUid()))) {
					ErrorDTO errorDTO = new ErrorDTO("1",
							"Sorry, Record already exist, Duplicate entries are not allowed.");
					ErrorListDTO errorListDTO = new ErrorListDTO();
					errorListDTO.addError(errorDTO);
					itemGroupOutMessage.setErrorListDTO(errorListDTO);
				} else {
					itemGroupOutMessage.setErrorListDTO(null);
					itemGroupEntity.setDeletedFlag(false);
					storageItemGroupDAO.update(itemGroupEntity);
				}
			} catch (BeansException e) {
				e.printStackTrace();
			}

		} else if (DELETE_ITEM_GROUP.equals(flowId)) {

			try {
				ItemGroupDTO itemGroupDTO = itemGroupInputMessage
						.getItemGroupDTO();
				List<ItemCategoryEntity> list = storageItemCategoryDAO
						.findByGroupId(itemGroupDTO.getItemGroupId());
				if ((list != null && list.size() > 0)) {
					ErrorDTO errorDTO = new ErrorDTO("1",
							"Item Group is already used in Item Category, can not be deleted");
					ErrorListDTO errorListDTO = new ErrorListDTO();
					errorListDTO.addError(errorDTO);
					itemGroupOutMessage.setErrorListDTO(errorListDTO);
				} else {
					if (itemGroupInputMessage.isDeleteFlag()) {
						try {
							BeanUtils.copyProperties(itemGroupDTO,
									itemGroupEntity);
						} catch (BeansException e) {
							e.printStackTrace();
						}
						itemGroupOutMessage.setErrorListDTO(null);
						storageItemGroupDAO.delete(itemGroupEntity);
					}
				}

			} catch (BeansException e) {
				e.printStackTrace();
			}

		} else if (FIND_ITEM_GROUP_BY_ID.equals(flowId)) {
			ItemGroupDTO itemGroupDTO = itemGroupInputMessage.getItemGroupDTO();
			List<ItemGroupEntity> list = storageItemGroupDAO
					.findById(itemGroupDTO.getItemGroupId());
			if (list != null) {
				List<ItemGroupDTO> resultList = convertItemGroupEntityListTOItemGroupDtoList(list);
				itemGroupOutMessage.setItemGroupDTOList(resultList);
			}
		}
		
		else if (FIND_ITEM_GROUPE_BY_FLAG_ID.equals(flowId)) {
			ItemGroupDTO itemGroupDTO = itemGroupInputMessage.getItemGroupDTO();
			List<ItemGroupEntity> list = storageItemGroupDAO.findByItemGroupFlagId(itemGroupDTO.getItemGroupFlagId());
			if (list != null) {
				List<ItemGroupDTO> resultList = convertItemGroupEntityListTOItemGroupDtoList(list);
				itemGroupOutMessage.setItemGroupDTOList(resultList);
			}
		}
		
		else if (FIND_ALL_ITEM_GROUPES.equals(flowId)) {
			List<ItemGroupEntity> list = storageItemGroupDAO.load();
			itemGroupOutMessage = new ItemGroupOutMessage();
			// set the data to the output message.
			if (list != null) {
				List<ItemGroupDTO> resultList = convertItemGroupEntityListTOItemGroupDtoList(list);
				itemGroupOutMessage.setItemGroupDTOList(resultList);
			}
		}
		
		else if (FIND_ITEM_GROUPES_FOR_ITEM_CATEGORY.equals(flowId)) {
			List list = storageItemGroupDAO.loadNameIdForItemCategory();
			itemGroupOutMessage = new ItemGroupOutMessage();
			
			List<ItemGroupDTO> resultList=new ArrayList<ItemGroupDTO>();	
			
			for(int i=0;i<list.size();i++)
			{
			ItemGroupDTO itemGroupDTO=new ItemGroupDTO();
			Object[] objects=(Object[])list.get(i);
			
			int id=(Integer)objects[0];
			String name=(String)objects[1];
			itemGroupDTO.setItemGroupId(id);
			itemGroupDTO.setItemGroupName(name);
			 
			resultList.add(itemGroupDTO);
			}
			itemGroupOutMessage=new ItemGroupOutMessage();
			itemGroupOutMessage.setItemGroupDTOList(resultList);
		}
		
		else if (FIND_ITEM_GROUPES.equals(flowId)) {
			ItemGroupDTO itemGroupDTO = itemGroupInputMessage.getItemGroupDTO();
			System.out.println(itemGroupDTO.toString());
			List<ItemGroupEntity> list = storageItemGroupDAO.search(
					itemGroupDTO.getItemGroupName(),
					itemGroupDTO.getItemGroupCode());
			itemGroupOutMessage = new ItemGroupOutMessage();
			if (list != null) {
				List<ItemGroupDTO> resultList = convertItemGroupEntityListTOItemGroupDtoList(list);
				itemGroupOutMessage.setItemGroupDTOList(resultList);
			}
		}
	}

	@Override
	public void formatOutput() {

		if (ADD_ITEM_GROUP.equals(flowId)) {

		} else if (UPDATE_ITEM_GROUP.equals(flowId)) {

		} else if (DELETE_ITEM_GROUP.equals(flowId)) {

		} else if (FIND_ITEM_GROUP_BY_ID.equals(flowId)) {

		} else if (FIND_ALL_ITEM_GROUPES.equals(flowId)) {
		}
		else if (FIND_ITEM_GROUPES_FOR_ITEM_CATEGORY.equals(flowId)) {
		}
		else if (FIND_ITEM_GROUPE_BY_FLAG_ID.equals(flowId)) {
		}
	}

	private List<ItemGroupDTO> convertItemGroupEntityListTOItemGroupDtoList(
			List<ItemGroupEntity> list) {

		List<ItemGroupDTO> resultList = new ArrayList<ItemGroupDTO>();
		ItemGroupDTO branchDto;
		for (ItemGroupEntity entity : list) {
			branchDto = new ItemGroupDTO();
			try {
				BeanUtils.copyProperties(entity, branchDto);
			} catch (BeansException e) {
				e.printStackTrace();
			}
			resultList.add(branchDto);
			/*
			 * try { BeanUtils.copyProperties(branchDto, entity);
			 * 
			 * 
			 * resultList.add(branchDto); } catch (IllegalAccessException e) {
			 * // TODO Auto-generated catch block e.printStackTrace(); } catch
			 * (InvocationTargetException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); }
			 */
		}
		return resultList;
	}

	

}
