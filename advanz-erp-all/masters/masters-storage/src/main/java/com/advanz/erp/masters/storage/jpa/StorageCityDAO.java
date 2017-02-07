package com.advanz.erp.masters.storage.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.erp.masters.entity.jpa.CityEntity;
import com.advanz.erp.masters.entity.jpa.AreaEntity;
import com.advanz.erp.masters.storage.IStorageCityDAO;

@Component
public class StorageCityDAO extends JpaDaoSupport implements IStorageCityDAO{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(CityEntity entity) {
		
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public CityEntity read(Integer uid) {
        if (uid == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
        return getJpaTemplate().find(CityEntity.class, uid);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public CityEntity update(CityEntity entity) {
		 if (entity == null) {
	            throw new IllegalArgumentException("Cannot update a null entity");
	        }
	       // entity.setDeletedFlag(false);
	        return getJpaTemplate().merge(entity);
		}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(CityEntity entity1) {
		if (entity1 == null) {
            throw new IllegalArgumentException("Cannot remove a null entity");
        }
		CityEntity entity=getJpaTemplate().find(CityEntity.class, entity1.getCityId());	
	  entity.setDeletedFlag(true);
	  entity.setModifiedUserId(entity1.getModifiedUserId());
      getJpaTemplate().merge(entity);				
	}

	@Override
    @Transactional(readOnly = true)
	public List<CityEntity> load() {
		
        return getJpaTemplate().find("FROM CityEntity e where e.deletedFlag=?1  ORDER BY e.cityName ",false);
	}

	@Override
	@Transactional(readOnly = true)
	public<E> List<CityEntity> findById(E uid) {		
		return getJpaTemplate().find("FROM CityEntity e WHERE e.cityId =?1 and e.deletedFlag=?2 ORDER BY e.cityName", uid,false);
	}
	

	@Override
	@Transactional(readOnly = true)
	public List<CityEntity> search(String cityName,String cityCode,String areaName) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM CityEntity e");
		boolean first = true;
		
		if (StringUtils.hasText(cityName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.cityName like :cityName");
		    params.put("cityName", "%"+cityName.trim()+"%");
		    first=false;
		}
		if (StringUtils.hasText(cityCode)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.cityCode like :cityCode");
		    params.put("cityCode", "%"+cityCode.trim()+"%");
		    first=false;
		}
		if (StringUtils.hasText(areaName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.areaEntity.areaName like :areaName");
		    params.put("areaName", "%"+areaName.trim()+"%");
		    first=false;
		}
			
		hql.append(first ? " where " : " and ");
	    hql.append(" e.deletedFlag=0  ORDER BY e.cityName");
	   
	    first=false;
		
		
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public List<CityEntity> findByNameAndCode(String cityName,String cityCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM CityEntity e");
		boolean first = true;
		
		if (StringUtils.hasText(cityName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.cityName = :cityName");
		    params.put("cityName", cityName.trim());
		    first=false;
		}
		if (StringUtils.hasText(cityCode)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.cityCode = :cityCode");
		    params.put("cityCode", cityCode.trim());
		    first=false;
		}
		
			
		hql.append(first ? " where " : " and ");
	    hql.append(" e.deletedFlag=0 ");
	    hql.append(" ORDER BY e.cityName ");
	   
	    first=false;
		
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}
	@Override
	public boolean isInUsed(Integer id) {	
		List<String> list=getJpaTemplate().find("Select partyId FROM PartyEntity e WHERE e.billingCityId =?1 and e.deletedFlag=0",id);
		if(list!=null && list.size()>0){
			//logger.info("so number :" +list.get(0));			
				return true;			
		}
		List<String> list1=getJpaTemplate().find("Select transporterId FROM TransporterEntity e WHERE e.cityId =?1 and e.deletedFlag=0",id);
		if(list1!=null && list1.size()>0){
			//logger.info("City in TransporterEntity  :" +list1.get(0));			
				return true;			
		}
		List<String> list11=getJpaTemplate().find("Select employeeId FROM EmployeeEntity e WHERE e.employeeCityEntity.cityId =?1 and e.deletedFlag=0",id);
		if(list11!=null && list11.size()>0){
			//logger.info("City in EmployeeEntity  :" +list11.get(0));			
				return true;			
		}
		return false;
	}

}
