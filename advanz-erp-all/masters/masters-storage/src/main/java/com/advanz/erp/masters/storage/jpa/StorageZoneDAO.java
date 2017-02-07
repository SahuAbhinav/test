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
import com.advanz.erp.masters.entity.jpa.CountryEntity;
import com.advanz.erp.masters.entity.jpa.RegionEntity;
import com.advanz.erp.masters.entity.jpa.ZoneEntity;

import com.advanz.erp.masters.storage.IStorageZoneDAO;

public class StorageZoneDAO  extends JpaDaoSupport implements IStorageZoneDAO{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(ZoneEntity entity) {
		
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public ZoneEntity read(Integer uid) {
        if (uid == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
        return getJpaTemplate().find(ZoneEntity.class, uid);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ZoneEntity update(ZoneEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Cannot update a null entity");
        }
        System.out.print("ZoneEntity update");
        //entity=getJpaTemplate().find(ZoneEntity.class, entity.getZoneId())	;
        System.out.print("ZoneEntity updatev entity "+entity.getZoneCode()+", "+entity.getZoneName());
        //entity.setDeletedFlag(true);
        return getJpaTemplate().merge(entity);
	}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(ZoneEntity entity1) {
		
		// TODO Auto-generated method stub
		if (entity1 == null) {
            throw new IllegalArgumentException("Cannot remove a null entity");
        }
		ZoneEntity entity=getJpaTemplate().find(ZoneEntity.class, entity1.getZoneId());	
	  entity.setDeletedFlag(true);
	  entity.setModifiedUserId(entity1.getModifiedUserId());
      getJpaTemplate().merge(entity);	
		
	}

	@Override
    @Transactional(readOnly = true)
	public List<ZoneEntity> load() {
		System.out.print("ZoneEntity update load");
        return getJpaTemplate().find("FROM ZoneEntity e where e.deletedFlag=?1  ORDER BY  E.zoneName",false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ZoneEntity> findById(Integer uid) {	
		System.out.print("ZoneEntity uid------------"+uid);		
		return getJpaTemplate().find("FROM ZoneEntity e WHERE e.zoneId = ?1", uid);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ZoneEntity> findAllZones() {		
		System.out.println("storeCountryDAO :---------------------");
		return getJpaTemplate().find("FROM ZoneEntity e WHERE e.deletedFlag = ?1 ORDER BY  E.zoneName ",false);
}
	
	@Override
	@Transactional(readOnly = true)
	public List<ZoneEntity> search(String zoneName,String zoneCode,String countryName) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM ZoneEntity e");
		boolean first = true;
		
		if (StringUtils.hasText(zoneName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.zoneName like :zoneName");
		    params.put("zoneName", "%"+zoneName.trim()+"%");
		    first=false;
		}
		if (StringUtils.hasText(zoneCode)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.zoneCode like :zoneCode");
		    params.put("zoneCode", "%"+zoneCode.trim()+"%");
		    first=false;
		}
		if (StringUtils.hasText(countryName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.countryEntity.countryName like :countryName");
		    params.put("countryName", "%"+countryName.trim()+"%");
		    first=false;
		}
			
		hql.append(first ? " where " : " and ");
	    hql.append(" e.deletedFlag=0 ORDER BY  E.zoneName");
	   
	    first=false;
		
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ZoneEntity> findByNameAndCode(String zoneName,String zoneCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM ZoneEntity e");
		boolean first = true;
		
		if (StringUtils.hasText(zoneName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.zoneName = :zoneName");
		    params.put("zoneName", zoneName.trim());
		    first=false;
		}
		if (StringUtils.hasText(zoneCode)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.zoneCode = :zoneCode");
		    params.put("zoneCode", zoneCode.trim());
		    first=false;
		}
		
		hql.append(first ? " where " : " and ");
	    hql.append(" e.deletedFlag=0 ");
	   
	    first=false;
		
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ZoneEntity> findZoneByCountryId(Integer countryId) {
		return getJpaTemplate().find("FROM ZoneEntity e WHERE e.countryEntity.countryId = ?1 and  e.deletedFlag = 0", countryId);
	}
	
	@Override
	public boolean isInUsed(Integer id) {	
		List<String> list=getJpaTemplate().find("Select stateId FROM StateEntity e WHERE e.zoneEntity.zoneId =?1 and e.deletedFlag=0",id);
		if(list!=null && list.size()>0){			
				return true;			
		}
		return false;
	}
	
}
