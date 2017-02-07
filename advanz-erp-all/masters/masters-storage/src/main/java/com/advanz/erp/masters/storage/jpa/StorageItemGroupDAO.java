package com.advanz.erp.masters.storage.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.erp.masters.entity.jpa.ItemGroupEntity;
import com.advanz.erp.masters.storage.IStorageItemGroupDAO;

@Component
public class StorageItemGroupDAO extends JpaDaoSupport implements IStorageItemGroupDAO{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(ItemGroupEntity entity) {
		
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public ItemGroupEntity read(Integer uid) {
        if (uid == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
        return getJpaTemplate().find(ItemGroupEntity.class, uid);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ItemGroupEntity update(ItemGroupEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Cannot update a null entity");
        }
        entity.setDeletedFlag(false);
        return getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(ItemGroupEntity itemGroupEntity1) {
		
		if (itemGroupEntity1 == null) {
            throw new IllegalArgumentException("Cannot remove a null entity");
        }
		ItemGroupEntity itemGroupEntity=getJpaTemplate().find(ItemGroupEntity.class, itemGroupEntity1.getItemGroupId());	
		itemGroupEntity.setDeletedFlag(true);
		itemGroupEntity.setModifiedUserId(itemGroupEntity1.getModifiedUserId());
      getJpaTemplate().merge(itemGroupEntity);
//		    getJpaTemplate().remove(itemGroupEntity);	
		
	}
	@Override
    @Transactional(readOnly = true)
	public List<ItemGroupEntity> load() {
		
//        return getJpaTemplate().find("FROM ItemGroupEntity e ORDER BY e.modifiedDate DESC");
        return getJpaTemplate().find("FROM ItemGroupEntity e where e.deletedFlag=0 ORDER BY e.itemGroupName");
	}

	
	@Override
    @Transactional(readOnly = true)
	public List<ItemGroupEntity> loadNameIdForItemCategory() {
		
//        return getJpaTemplate().find("FROM ItemGroupEntity e ORDER BY e.modifiedDate DESC");
        return getJpaTemplate().find("Select itemGroupId,itemGroupName FROM ItemGroupEntity e where e.deletedFlag=0 ORDER BY e.itemGroupName");
	}

	
	
	@Override
	@Transactional(readOnly = true)
	public List<ItemGroupEntity> findById(Integer uid) {
		
		return getJpaTemplate().find("FROM ItemGroupEntity e WHERE e.itemGroupId = ?1 ORDER BY e.itemGroupName", uid);
		
//		return getJpaTemplate().find("FROM ItemEntity e WHERE e.uid = "+ uid +"ORDER BY e.itemName");
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ItemGroupEntity> search(String itemGroupName, String code) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM ItemGroupEntity e");
		boolean first = true;
		
		if (StringUtils.hasText(itemGroupName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.itemGroupName like :itemGroupName");
		    params.put("itemGroupName", "%"+itemGroupName.trim()+"%");
		    first=false;
		}
	//	logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		if (StringUtils.hasText(code)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.itemGroupCode like :code");
		    params.put("code", "%"+code.trim()+"%");
		    first=false;
		}
		
		hql.append(first ? " where " : " and ");
	    hql.append(" e.deletedFlag=0 ");
		
	//	logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	
	
	@Override
	@Transactional(readOnly = true)
	public List<ItemGroupEntity> findByItemGroupFlagId(Integer itemGroupFlagId) {
		
		return getJpaTemplate().find("FROM ItemGroupEntity e WHERE e.itemGroupFlagId = ?1 and e.deletedFlag=0 ORDER BY e.itemGroupName", itemGroupFlagId);

	}
}
