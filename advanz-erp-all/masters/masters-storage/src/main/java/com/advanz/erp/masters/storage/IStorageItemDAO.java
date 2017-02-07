package com.advanz.erp.masters.storage;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.ItemEntity;

public interface IStorageItemDAO extends IStorageDAO<ItemEntity>{
    public List<ItemEntity> load();
    public List<ItemEntity> findById(Integer id);
    public List<ItemEntity> search(String itemName,String invoiceName,String itemCode,String itemCategory,String itemGroup,Integer groupId,Integer activeStatus,Double excisePerc,Integer gradeId);

    public List<ItemEntity> loadFinishedGoodItems(String invoiceName,String itemCode);
    public List<ItemEntity> loadItemsForBatch();
    
    public List<ItemEntity> finishGoodItemList();
    public List<ItemEntity> searchByItemGroupNameList(String invoiceName,String itemCode,Integer itemGroupFlageId,Integer activeStatus);

    public List<ItemEntity> findItemByGroupName(String groupName);
    public List<ItemEntity> getItemIdAndItemNameList();
    public List<ItemEntity> loadItemPagination(Integer index);
    public List<ItemEntity> finishGoodItemListWithPagination(Integer next, Integer itemGroupFlagId);
    
    public List<ItemEntity> getListByItemGroupName(Integer itemGroupFlageId,Integer activeStatus,Integer index);
    
    public List<ItemEntity> getItemByCode(String itemCode);
	public List<ItemEntity> getItemByName(String itemName);
	
	public List<ItemEntity> getListByItemGroupFlagId(Integer itemGroupFlageId);
	public Integer itemGroupFlagId(String itemCode) ;
	
	public List<ItemEntity> finishGoodItemListWithPaginationAndExcisePerc(Integer next,Double excisePerc);
	public List<ItemEntity> getListByItemCategory(Integer itemCategory);
	public List getItemIdAndDencity(Integer gradeId,Double lenght,Double width,Double thickness,Double weight);
	public List<ItemEntity> getItemNameAndId(Integer itemId,String operation);
}
