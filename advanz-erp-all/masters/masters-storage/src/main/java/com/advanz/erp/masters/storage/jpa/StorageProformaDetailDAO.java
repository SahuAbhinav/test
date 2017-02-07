package com.advanz.erp.masters.storage.jpa;

import java.util.List;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.erp.masters.entity.jpa.ProformaDetailEntity;
import com.advanz.erp.masters.storage.IStorageProformaDetailDAO;

public class StorageProformaDetailDAO extends JpaDaoSupport implements IStorageProformaDetailDAO{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(ProformaDetailEntity entity) {
		if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
		entity.setDeletedFlag(false);
	    getJpaTemplate().persist(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public ProformaDetailEntity read(Integer sno) {
		if (sno == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
        return getJpaTemplate().find(ProformaDetailEntity.class, sno);
	   }

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ProformaDetailEntity update(ProformaDetailEntity entity) {
		 if (entity == null) {
	            throw new IllegalArgumentException("Cannot update a null entity");
	        }
		
		    //entity=getJpaTemplate().find(ProformaDetailEntity.class, entity.getSno());	
	        //entity.setDeletedFlag(false);
	        return getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(ProformaDetailEntity entity) {
		if (entity == null) {
            throw new IllegalArgumentException("Cannot remove a null entity");
        }
	 // entity=getJpaTemplate().find(ProformaDetailEntity.class, entity.getSno());	
	  entity.setDeletedFlag(true);
      getJpaTemplate().merge(entity);
    	}

	@Override
    @Transactional(readOnly = true)
	public List<ProformaDetailEntity> load() {
		// TODO Auto-generated method stub
		return getJpaTemplate().find("FROM ProformaDetailEntity e where e.deletedFlag=0 ORDER BY e.modifiedDate DESC");
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ProformaDetailEntity> findById(String invoiceNumber) {
		// TODO Auto-generated method stub
		return getJpaTemplate().find("FROM ProformaDetailEntity e WHERE e.invoiceNumber = "+"'"+ invoiceNumber+"'"+ " AND e.deletedFlag=0 ORDER BY e.sno");
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ProformaDetailEntity> findByItemId(Integer itemId) {
		return getJpaTemplate().find("FROM ProformaDetailEntity e WHERE e.itemId = "+ itemId+ " AND e.deletedFlag=0 ");
		//return getJpaTemplate().find("Select invoiceNumber FROM ProformaDetailEntity e WHERE e.itemId = "+ itemId+ " AND e.deletedFlag=0");
	}
	
	
	
}
