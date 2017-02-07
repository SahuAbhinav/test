package com.advanz.erp.masters.storage.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.erp.masters.entity.jpa.AreaEntity;
import com.advanz.erp.masters.entity.jpa.CountryEntity;
import com.advanz.erp.masters.storage.IStorageCountryDAO;
import org.springframework.util.StringUtils;

@Component
public class StorageCountryDAO extends JpaDaoSupport implements IStorageCountryDAO{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(CountryEntity entity) {
		
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }      
       
        getJpaTemplate().persist(entity);
        
        

	}

	@Override
	@Transactional(readOnly = true)
	public CountryEntity read(Integer uid) {
        if (uid == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
        return getJpaTemplate().find(CountryEntity.class, uid);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public CountryEntity update(CountryEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Cannot update a null entity");
        }
        
        return getJpaTemplate().merge(entity);
	}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(CountryEntity entity1) {
		// TODO Auto-generated method stub
		if (entity1 == null) {
            throw new IllegalArgumentException("Cannot update a null entity");
        }
		CountryEntity  entity=getJpaTemplate().find(CountryEntity.class, entity1.getCountryId())	;    
	  entity.setDeletedFlag(true);
	  entity.setModifiedUserId(entity1.getModifiedUserId());
      getJpaTemplate().merge(entity);
		
	}

	@Override
    @Transactional(readOnly = true)
	public List<CountryEntity> load() {
		
        return getJpaTemplate().find("FROM CountryEntity e ORDER BY e.countryName");
	}

	@Override
	@Transactional(readOnly = true)
	public<E> List<CountryEntity> findById(E uid) {	
		System.out.print("DAO countryID"+uid);
		return getJpaTemplate().find("FROM CountryEntity e WHERE e.countryId like ?1 and e.deletedFlag=?2  ORDER BY e.countryName", uid,false);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<CountryEntity> findAllCountry() {
		System.out.println("storeCountryDAO :---------------------");
		return getJpaTemplate().find("FROM CountryEntity e WHERE e.deletedFlag = ?1  ORDER BY e.countryName ",false);
}

	@Override
	@Transactional(readOnly = true)
	public List<CountryEntity> search(String countryName,String countryCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM CountryEntity e");
		boolean first = true;
		
		if (StringUtils.hasText(countryName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.countryName like :countryName");
		    params.put("countryName", "%"+countryName.trim()+"%");
		    first=false;
		}
		if (StringUtils.hasText(countryCode)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.countryCode like :countryCode");
		    params.put("countryCode", "%"+countryCode.trim()+"%");
		    first=false;
		}
			
		hql.append(first ? " where " : " and ");
	    hql.append(" e.deletedFlag=0 ORDER BY e.countryName");
	   
	    first=false;
		
		
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}
	@Override
	@Transactional(readOnly = true)
	public List<CountryEntity> findByNameAndCode(String countryName,String countryCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM CountryEntity e");
		boolean first = true;
		
		if (StringUtils.hasText(countryName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.countryName = :countryName");
		    params.put("countryName", countryName.trim());
		    first=false;
		}
		if (StringUtils.hasText(countryCode)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.countryCode = :countryCode");
		    params.put("countryCode", countryCode.trim());
		    first=false;
		}
		
		hql.append(first ? " where " : " and ");
	    hql.append(" e.deletedFlag=0 ORDER BY e.countryName");
	   
	    first=false;
		
		
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}
	
	@Override
	public boolean isInUsed(Integer id) {	
		List<String> list=getJpaTemplate().find("Select zoneId FROM ZoneEntity e WHERE e.countryEntity.countryId =?1 and e.deletedFlag=0",id);
		if(list!=null && list.size()>0){			
				return true;			
		}
		return false;
	}
	
}