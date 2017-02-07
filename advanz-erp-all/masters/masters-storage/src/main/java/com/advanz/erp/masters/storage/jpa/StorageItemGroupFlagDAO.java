package com.advanz.erp.masters.storage.jpa;

import java.util.List;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.erp.masters.entity.jpa.BatchEntity;
import com.advanz.erp.masters.entity.jpa.ItemGroupFlagEntity;
import com.advanz.erp.masters.storage.IStorageItemGroupFlagDAO;

public class StorageItemGroupFlagDAO extends JpaDaoSupport implements IStorageItemGroupFlagDAO{
 
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(ItemGroupFlagEntity entity) {
		
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public ItemGroupFlagEntity read(Integer uid) {
        if (uid == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
        return getJpaTemplate().find(ItemGroupFlagEntity.class, uid);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ItemGroupFlagEntity update(ItemGroupFlagEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Cannot update a null entity");
        }
        return getJpaTemplate().merge(entity);
	}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(ItemGroupFlagEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
    @Transactional(readOnly = true)
	public List<ItemGroupFlagEntity> load() {
	    return getJpaTemplate().find("FROM ItemGroupFlagEntity e ORDER BY e.itemGroupFlagName");
	}


	@Override
    @Transactional(readOnly = true)
	public List<ItemGroupFlagEntity> findIdByName(String itemGroupFlagName) {
	    return getJpaTemplate().find("FROM ItemGroupFlagEntity e where e.itemGroupFlagName=? ORDER BY e.itemGroupFlagName ",itemGroupFlagName);
    }

	
	@Override
    @Transactional(readOnly = true)
	public List<ItemGroupFlagEntity> findGroupFlagByItemId(Integer itemId){
		itemId=1;
	   List<ItemGroupFlagEntity> iList =getJpaTemplate().find("FROM ItemGroupFlagEntity e where e.itemGroupEntity.itemCategoryEntity.itemId=? ORDER BY e.itemGroupFlagName ",itemId);
	   return iList;
	}
		// TODO: handle exception
	

}
