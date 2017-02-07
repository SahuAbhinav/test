package com.advanz.erp.masters.storage.jpa;

import java.util.List;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.erp.masters.entity.jpa.TdsMasterEntity;
import com.advanz.erp.masters.storage.IStorageTdsMasterDAO;

public class StorageTdsMasterDAO extends JpaDaoSupport implements  IStorageTdsMasterDAO{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(TdsMasterEntity entity) {
		
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        getJpaTemplate().persist(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public TdsMasterEntity read(Integer uid) {
        if (uid == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
     //   return getJpaTemplate().find(BatchEntity.class, uid);
       return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public TdsMasterEntity update(TdsMasterEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Cannot update a null entity");
        }
        return getJpaTemplate().merge(entity);
	}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(TdsMasterEntity entity1) {
		  
		  if (entity1 == null) {
	            throw new IllegalArgumentException("Cannot update a null entity");
	        }
		  TdsMasterEntity entity=getJpaTemplate().find(TdsMasterEntity.class, entity1.getSno()); 
		  entity.setDeletedFlag(true);
		  entity.setModifiedUserId(entity1.getModifiedUserId());
		  getJpaTemplate().merge(entity);
	}

	@Override
    @Transactional(readOnly = true)
	public List<TdsMasterEntity> load() {
		logger.info("Loading TdsMasters data ");
        return getJpaTemplate().find("FROM TdsMasterEntity e where e.deletedFlag=?1 ORDER BY e.modifiedDate DESC",false);
	}
	
	
	@Override
  	@Transactional(readOnly = true)
  	public List<TdsMasterEntity> findByCode(String tdsCode) {
 		return getJpaTemplate().find("FROM TdsMasterEntity e WHERE e.code=?1 and deletedFlag=?2 ", tdsCode.trim(),false);
	}
	
	@Override
  	@Transactional(readOnly = true)
  	public List<TdsMasterEntity> findBySlabName(String slabName) {
 		return getJpaTemplate().find("FROM TdsMasterEntity e WHERE e.slabName=?1 and deletedFlag=?2 ORDER BY e.slabName", slabName.trim(),false);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<TdsMasterEntity> findById(Integer sno) {		
		//return getJpaTemplate().find("FROM BatchEntity e WHERE e.batchNo=?1 and e.deletedFlag=?2 ORDER BY e.batchNo",uid,false);
		return getJpaTemplate().find("FROM TdsMasterEntity e WHERE e.sno=?1",sno);
	}

}
