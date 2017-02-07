package com.advanz.erp.masters.storage.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.erp.masters.entity.jpa.ItemEntity;
import com.advanz.erp.masters.entity.jpa.StateEntity;
import com.advanz.erp.masters.storage.IStorageStateDAO;


@Component
public class StorageStateDAO extends JpaDaoSupport implements IStorageStateDAO{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(StateEntity entity) {
		
		if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        entity.setDeletedFlag(false);
        getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public StateEntity read(Integer uid) {
        if (uid == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
        return getJpaTemplate().find(StateEntity.class, uid);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public StateEntity update(StateEntity entity) {
		 if (entity == null) {
	            throw new IllegalArgumentException("Cannot update a null entity");
	        }
	        entity.setDeletedFlag(false);
	        return getJpaTemplate().merge(entity);
	}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(StateEntity entity1) {
		// TODO Auto-generated method stub
		if (entity1 == null) {
            throw new IllegalArgumentException("Cannot remove a null entity");
        }
		StateEntity entity=getJpaTemplate().find(StateEntity.class, entity1.getStateId());	
	  entity.setDeletedFlag(true);
	  entity.setModifiedUserId(entity1.getModifiedUserId());
      getJpaTemplate().merge(entity);
		
	}

	@Override
    @Transactional(readOnly = true)
	public List<StateEntity> load() {
		
        return getJpaTemplate().find("FROM StateEntity e where e.deletedFlag=0 ORDER BY e.stateName");
	}

	@Override
	@Transactional(readOnly = true)
	public<E> List<StateEntity> findById(E uid) {		
		return getJpaTemplate().find("FROM StateEntity e WHERE e.stateId = ?1 ORDER BY e.modifiedDate", uid);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<StateEntity> search(String stateName,String stateCode,String zoneName) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM StateEntity e");
		boolean first = true;
		
		if (StringUtils.hasText(stateName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.stateName like :stateName");
		    params.put("stateName", "%"+stateName.trim()+"%");
		    first=false;
		}
		if (StringUtils.hasText(stateCode)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.stateCode like :stateCode");
		    params.put("stateCode", "%"+stateCode.trim()+"%");
		    first=false;
		}
		if (StringUtils.hasText(zoneName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.zoneEntity.zoneName like :zoneName");
		    params.put("zoneName", "%"+zoneName.trim()+"%");
		    first=false;
		}
		hql.append(first ? " where " : " and ");
	    hql.append(" e.deletedFlag=0 ORDER BY e.stateName");
	    first=false;
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<StateEntity> findStateByZoneId(Integer zoneId) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM StateEntity e");
		boolean first = true;
		
	    hql.append(first ? " where " : " and ");
	    hql.append("e.zoneEntity.zoneId = :zoneId");
	    params.put("zoneId", zoneId);
	    first=false;
		
		hql.append(first ? " where " : " and ");
	    hql.append(" e.deletedFlag=0 ORDER BY e.stateName");
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

}
