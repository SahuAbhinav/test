package com.advanz.erp.masters.storage.jpa;

import java.util.List;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.erp.masters.entity.jpa.DispatchDetailEntity;
import com.advanz.erp.masters.entity.jpa.DispatchMasterEntity;
import com.advanz.erp.masters.storage.IStorageDispatchDetailDAO;

public class StorageDispatchDetailDAO extends JpaDaoSupport implements
		IStorageDispatchDetailDAO {

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(DispatchDetailEntity entity) {
		
		if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
		entity.setDeletedFlag(false);
	    getJpaTemplate().persist(entity);
	   }

	
	@Override
	@Transactional(readOnly = true)
	public DispatchDetailEntity read(Integer sno) {
		if (sno == null) {
            throw new IllegalArgumentException("sno must not be null");
        }
        return getJpaTemplate().find(DispatchDetailEntity.class, sno);
	  }

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public DispatchDetailEntity update(DispatchDetailEntity entity) {
		if (entity == null) {
            throw new IllegalArgumentException("Cannot update a null entity");
        }
        entity.setDeletedFlag(false);
        return getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(DispatchDetailEntity entity) {
		if (entity == null) {
            throw new IllegalArgumentException("Cannot remove a null entity");
        }
	 // entity=getJpaTemplate().find(DispatchDetailEntity.class, entity.getSno());	
	  entity.setDeletedFlag(true);
      getJpaTemplate().merge(entity);
     }


	@Override
    @Transactional(readOnly = true)
	public List<DispatchDetailEntity> load() {
		return getJpaTemplate().find("FROM DispatchDetailEntity e where e.deletedFlag=0 ORDER BY e.modifiedDate DESC");
	}

	
	@Override
	public <E> List<DispatchDetailEntity> findById(Integer sno) {
		// TODO Auto-generated method stub
		return getJpaTemplate().find("FROM DispatchDetailEntity e WHERE e.sno = "+"'"+ sno+"'"+ " ORDER BY e.modifiedDate DESC");
	}

	
	@Override
	public List<DispatchMasterEntity> getMaxId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DispatchDetailEntity> findByNameAndCode(String areaName,
			String areaCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DispatchDetailEntity> search(String areaName, String areaCode,
			String regionName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DispatchDetailEntity> findByDispatchId(String dispatchNumber) {
		return getJpaTemplate().find("FROM DispatchDetailEntity e WHERE e.dispatchNumber = "+"'"+ dispatchNumber+"'"+ " and e.deletedFlag=0 ORDER BY e.modifiedDate DESC");
	}
	
	@Override
	public List<DispatchDetailEntity> findByItemId(Integer dispatchNumber) {
		return getJpaTemplate().find("FROM DispatchDetailEntity e WHERE e.itemId = "+ dispatchNumber+ " and e.deletedFlag=0 ");
	}

}