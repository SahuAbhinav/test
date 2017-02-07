package com.advanz.erp.masters.storage.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.erp.masters.entity.jpa.ProfessionalTaxEntity;
import com.advanz.erp.masters.storage.IStorageProfessionalTaxDAO;

public class StorageProfessionalTaxDAO extends JpaDaoSupport implements  IStorageProfessionalTaxDAO{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(ProfessionalTaxEntity entity) {
		
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        getJpaTemplate().persist(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public ProfessionalTaxEntity read(Integer uid) {
        if (uid == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
     //   return getJpaTemplate().find(BatchEntity.class, uid);
       return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ProfessionalTaxEntity update(ProfessionalTaxEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Cannot update a null entity");
        }
        return getJpaTemplate().merge(entity);
	}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(ProfessionalTaxEntity entity1) {
		  
		  if (entity1 == null) {
	            throw new IllegalArgumentException("Cannot update a null entity");
	        }
		  ProfessionalTaxEntity entity=getJpaTemplate().find(ProfessionalTaxEntity.class, entity1.getPtaxId()); 
		  entity.setDeletedFlag(true);
		  entity.setModifiedUserId(entity1.getModifiedUserId());
		  getJpaTemplate().merge(entity);
	}

	@Override
    @Transactional(readOnly = true)
	public List<ProfessionalTaxEntity> load() {
		logger.info("Loading ProfessionalTax data ");
        return getJpaTemplate().find("FROM ProfessionalTaxEntity e where e.deletedFlag=?1 ORDER BY e.modifiedDate DESC",false);
	}
	
	@Override
  	@Transactional(readOnly = true)
  	public List<ProfessionalTaxEntity> findByNameAndCode(String slabName,String ptaxCode) {
 		return getJpaTemplate().find("FROM ProfessionalTaxEntity e WHERE e.slabName = ?1 and e.ptaxCode=?2 and deletedFlag=?3 ORDER BY e.slabName", slabName.trim(),ptaxCode.trim(),false);
	}
	
	@Override
  	@Transactional(readOnly = true)
  	public List<ProfessionalTaxEntity> findByCode(String ptaxCode) {
 		return getJpaTemplate().find("FROM ProfessionalTaxEntity e WHERE e.ptaxCode=?1 and deletedFlag=?2 ORDER BY e.slabName", ptaxCode.trim(),false);
	}
	
	@Override
  	@Transactional(readOnly = true)
  	public List<ProfessionalTaxEntity> findBySlabName(String slabName) {
 		return getJpaTemplate().find("FROM ProfessionalTaxEntity e WHERE e.slabName=?1 and deletedFlag=?2 ORDER BY e.slabName", slabName.trim(),false);
	}
	@Override
	@Transactional(readOnly = true)
	public List<ProfessionalTaxEntity> search(String slabName, String deductAmount) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		
	    StringBuffer hql = new StringBuffer("FROM ProfessionalTaxEntity e");
		boolean first = true;
		
		if (StringUtils.hasText(slabName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.slabName like :slabName");
		    params.put("slabName", "%"+slabName.trim()+"%");
		    first=false;
		}	
		if (StringUtils.hasText(deductAmount)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.deductAmount like :deductAmount");
		    params.put("deductAmount", "%"+deductAmount.trim()+"%");
		    first=false;
		}
		hql.append(first ? " where " : " and ");
		hql.append("e.deletedFlag=:flag");
		params.put("flag", false);
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProfessionalTaxEntity> findById(Integer ptaxId) {		
		//return getJpaTemplate().find("FROM BatchEntity e WHERE e.batchNo=?1 and e.deletedFlag=?2 ORDER BY e.batchNo",uid,false);
		return getJpaTemplate().find("FROM ProfessionalTaxEntity e WHERE e.ptaxId=?1 ORDER BY e.ptaxId",ptaxId);
	}
	

}
