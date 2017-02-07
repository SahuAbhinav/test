package com.advanz.erp.masters.storage.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.erp.masters.entity.jpa.StoreLocationEntity;
import com.advanz.erp.masters.storage.IStorageStoreLocationDAO;

@Component
public class StorageStoreLocationDAO extends JpaDaoSupport implements IStorageStoreLocationDAO{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(StoreLocationEntity entity) {
		
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        getJpaTemplate().persist(entity);
        
	}

	@Override
	@Transactional(readOnly = true)
	public StoreLocationEntity read(Integer uid) {
        if (uid == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
        return getJpaTemplate().find(StoreLocationEntity.class, uid);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public StoreLocationEntity update(StoreLocationEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Cannot update a null entity");
        }
        return getJpaTemplate().merge(entity);
	}


	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(StoreLocationEntity entity1) {
		 if (entity1 == null) {
	            throw new IllegalArgumentException("Cannot delete a null entity");
	     }
		 StoreLocationEntity entity=getJpaTemplate().find(StoreLocationEntity.class, entity1.getStoreLocationId());
		 entity.setDeletedFlag(true);
		 entity.setModifiedUserId(entity1.getModifiedUserId());
	     getJpaTemplate().merge(entity);		
	}

	@Override
	@Transactional(readOnly = true)
	public List<StoreLocationEntity> findById(Integer uid) {		
		return getJpaTemplate().find("FROM StoreLocationEntity e WHERE e.storeLocationId = ?1 and e.deletedFlag = ?2 ORDER BY e.storeLocation", uid, false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<StoreLocationEntity> findByName(String storeLocation) {
		
		return getJpaTemplate().find("FROM StoreLocationEntity e WHERE e.storeLocation = ?1 and deletedFlag=?2 ORDER BY e.storeLocation", storeLocation,false);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<StoreLocationEntity> findByCode(String locationCode) {
		
		return getJpaTemplate().find("FROM StoreLocationEntity e WHERE e.locationCode=?1 and deletedFlag=?2 ORDER BY e.storeLocation", locationCode,false);
		
	}
	
	
	
	@Override
    @Transactional(readOnly = true)
	public List<StoreLocationEntity> load() {	
		return getJpaTemplate().find("FROM StoreLocationEntity e WHERE e.deletedFlag = ?1 ORDER BY e.storeLocation",false);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<StoreLocationEntity> search(String storeLocation, String locationCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM StoreLocationEntity e");
		boolean first = true;
		if (StringUtils.hasText(storeLocation)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.storeLocation like :storeLocation");
		    params.put("storeLocation", "%"+storeLocation.trim()+"%");
		    first=false;
		}
		if (StringUtils.hasText(locationCode)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.locationCode like :locationCode");
		    params.put("locationCode", "%"+locationCode.trim()+"%");
		    first=false;
		}		
		hql.append(first ? " where " : " and ");
		hql.append("e.deletedFlag = :flag");
		params.put("flag", false);
		
		hql.append(" ORDER BY e.storeLocation");
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	public boolean isInUsed(Integer id) {
		List<Integer> list=getJpaTemplate().find("Select sno FROM FinishedGoodsDetailEntity e WHERE e.deletedFlag=0 and e.storeLocationId ="+id);
		if(list!=null && list.size()>0){
			return true;
		}
		list=getJpaTemplate().find("Select sno FROM IssueDetailMasterEntity e WHERE e.deletedFlag=0 and e.storeLocationId ="+id);
		if(list!=null && list.size()>0){
			return true;
		}
		list=getJpaTemplate().find("Select itemId FROM ItemEntity e WHERE e.deletedFlag=0 and e.storeLocationEntity.storeLocationId ="+id);
		if(list!=null && list.size()>0){
			return true;
		}
		return false;
	}	
}
