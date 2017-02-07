package com.advanz.erp.masters.storage.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.erp.masters.entity.jpa.AreaEntity;
import com.advanz.erp.masters.entity.jpa.AreaEntity;
import com.advanz.erp.masters.storage.IStorageAreaDAO;

@Component
public class StorageAreaDAO extends JpaDaoSupport implements IStorageAreaDAO{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(AreaEntity entity) {
		
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public AreaEntity read(Integer uid) {
        if (uid == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
        return getJpaTemplate().find(AreaEntity.class, uid);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public AreaEntity update(AreaEntity entity) {
		 if (entity == null) {
	            throw new IllegalArgumentException("Cannot update a null entity");
	        }
	       // entity.setDeletedFlag(false);
	        return getJpaTemplate().merge(entity);
		}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(AreaEntity entity1) {
		if (entity1 == null) {
            throw new IllegalArgumentException("Cannot remove a null entity");
        }
		AreaEntity  entity=getJpaTemplate().find(AreaEntity.class, entity1.getAreaId());	
	  entity.setDeletedFlag(true);
	  entity.setModifiedUserId(entity1.getModifiedUserId());
      getJpaTemplate().merge(entity);				
	}

	@Override
    @Transactional(readOnly = true)
	public List<AreaEntity> load() {
		
        return getJpaTemplate().find("FROM AreaEntity e where e.deletedFlag=?1  ORDER BY e.areaName",false);
	}

	@Override
	@Transactional(readOnly = true)
	public<E> List<AreaEntity> findById(E uid) {		
		return getJpaTemplate().find("FROM AreaEntity e WHERE e.areaId =?1 and e.deletedFlag=?2  ORDER BY e.areaName", uid,false);
	}
	

	@Override
	@Transactional(readOnly = true)
	public List<AreaEntity> search(String areaName,String areaCode,String regionName) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM AreaEntity e");
		boolean first = true;
		
		if (StringUtils.hasText(areaName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.areaName like :areaName");
		    params.put("areaName", "%"+areaName.trim()+"%");
		    first=false;
		}
		if (StringUtils.hasText(areaCode)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.areaCode like :areaCode");
		    params.put("areaCode", "%"+areaCode.trim()+"%");
		    first=false;
		}
		if (StringUtils.hasText(regionName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.regionEntity.regionName like :regionName");
		    params.put("regionName", "%"+regionName.trim()+"%");
		    first=false;
		}
			
		hql.append(first ? " where " : " and ");
	    hql.append(" e.deletedFlag=0 ORDER BY e.areaName");
	   
	    first=false;
		
		
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public List<AreaEntity> findByNameAndCode(String areaName,String areaCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM AreaEntity e");
		boolean first = true;
		
		if (StringUtils.hasText(areaName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.areaName = :areaName");
		    params.put("areaName", areaName.trim());
		    first=false;
		}
		if (StringUtils.hasText(areaCode)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.areaCode = :areaCode");
		    params.put("areaCode", areaCode.trim());
		    first=false;
		}
		
			
		hql.append(first ? " where " : " and ");
	    hql.append(" e.deletedFlag=0 ORDER BY e.areaName ");
	   
	    first=false;
		
		
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	/**
	 * The method is used to get all the regions that belong to a state. 
	 */
	@Override
	@Transactional(readOnly = true)
	public List<AreaEntity> findByRegionId(Integer regionId) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM AreaEntity e");
		boolean first = true;
		
		
	    hql.append(first ? " where " : " and ");
	    hql.append("e.regionEntity.regionId = :regionId");
	    params.put("regionId", regionId);
	    first=false;
	    
		hql.append(first ? " where " : " and ");
	    hql.append(" e.deletedFlag=0 ");
	   
	    first=false;
		
		
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	public boolean isInUsed(Integer id) {
		List<Integer> list=getJpaTemplate().find("Select cityId FROM CityEntity e WHERE e.deletedFlag=0 and e.areaEntity.areaId =?1",id);
		if(list!=null && list.size()>0){
			return true;
		}
		return false;
	}
	

}
