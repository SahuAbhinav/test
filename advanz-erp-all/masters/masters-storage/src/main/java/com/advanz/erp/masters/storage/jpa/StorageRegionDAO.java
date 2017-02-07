package com.advanz.erp.masters.storage.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.erp.masters.entity.jpa.RegionEntity;
import com.advanz.erp.masters.entity.jpa.ZoneEntity;
import com.advanz.erp.masters.storage.IStorageRegionDAO;

@Component
public class StorageRegionDAO extends JpaDaoSupport implements IStorageRegionDAO{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(RegionEntity entity) {
		
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public RegionEntity read(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
        return getJpaTemplate().find(RegionEntity.class, id);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public RegionEntity update(RegionEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Cannot update a null entity");
        }
        entity.setDeletedFlag(false);
        return getJpaTemplate().merge(entity);
	}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(RegionEntity entity1) {
		if (entity1 == null) {
            throw new IllegalArgumentException("Cannot remove a null entity");
        }
		RegionEntity  entity=getJpaTemplate().find(RegionEntity.class, entity1.getRegionId());	
	  entity.setDeletedFlag(true);
	  entity.setModifiedUserId(entity1.getModifiedUserId());
      getJpaTemplate().merge(entity);		
	}

	@Override
    @Transactional(readOnly = true)
	public List<RegionEntity> load() {		
        return getJpaTemplate().find("FROM RegionEntity e where e.deletedFlag=?1 ORDER BY e.regionName",false);
	}

	@Override
	@Transactional(readOnly = true)
	public<E> List<RegionEntity> findById(E id) {		
		return getJpaTemplate().find("FROM RegionEntity e WHERE e.regionId =?1 and e.deletedFlag=?2", id,false);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public List<RegionEntity> search(String regionName,String regionCode,String stateName) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM RegionEntity e");
		boolean first = true;
		
		if (StringUtils.hasText(regionName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.regionName like :regionName");
		    params.put("regionName", "%"+regionName.trim()+"%");
		    first=false;
		}
		if (StringUtils.hasText(regionCode)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.regionCode like :regionCode");
		    params.put("regionCode", "%"+regionCode.trim()+"%");
		    first=false;
		}
		if (StringUtils.hasText(stateName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.stateEntity.stateName like :stateName");
		    params.put("stateName", "%"+stateName.trim()+"%");
		    first=false;
		}
			
		hql.append(first ? " where " : " and ");
	    hql.append(" e.deletedFlag=0 ORDER BY e.regionName");
	   
	    first=false;
		
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public List<RegionEntity> findByNameAndCode(String regionName,String regionCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM RegionEntity e");
		boolean first = true;
		
		if (StringUtils.hasText(regionName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.regionName = :regionName");
		    params.put("regionName", regionName.trim());
		    first=false;
		}
		if (StringUtils.hasText(regionCode)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.regionCode = :regionCode");
		    params.put("regionCode", regionCode.trim());
		    first=false;
		}
		
			
		hql.append(first ? " where " : " and ");
	    hql.append(" e.deletedFlag=0 ");
	   
	    first=false;
		
		
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}
	
	/**
	 * The method is used to get all the regions that belong to a state. 
	 */
	@Override
	@Transactional(readOnly = true)
	public List<RegionEntity> findByStateId(Integer stateId) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM RegionEntity e");
		boolean first = true;
		
	    hql.append(first ? " where " : " and ");
	    hql.append("e.stateEntity.stateId = :stateId");
	    params.put("stateId", stateId);
	    first=false;
		
		
		hql.append(first ? " where " : " and ");
	    hql.append(" e.deletedFlag=0 ");
	   
	    first=false;
		
		
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	public boolean isInUsed(Integer id) {
		List<Integer> list=getJpaTemplate().find("Select areaId FROM AreaEntity e WHERE e.deletedFlag=0 and e.regionEntity.regionId =?1",id);
		if(list!=null && list.size()>0){
			return true;
		}
		return false;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<RegionEntity> findReginByStateId(Integer stateId) {
		return getJpaTemplate().find("FROM RegionEntity e WHERE e.stateEntity.stateId = ?1 and  e.deletedFlag = 0", stateId);
	}
}
