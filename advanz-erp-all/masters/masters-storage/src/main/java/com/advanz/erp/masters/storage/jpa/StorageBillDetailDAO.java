package com.advanz.erp.masters.storage.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.erp.masters.entity.jpa.BillDetailEntity;
import com.advanz.erp.masters.storage.IStorageBillDetailDAO;

public class StorageBillDetailDAO extends JpaDaoSupport implements IStorageBillDetailDAO{
	@Autowired
	EntityManagerFactory entityManagerFactory;
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(BillDetailEntity entity) {
		if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
		entity.setDeletedFlag(false);
	    getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public BillDetailEntity read(Integer sno) {
		if (sno == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
        return getJpaTemplate().find(BillDetailEntity.class, sno);
	   }

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public BillDetailEntity update(BillDetailEntity entity) {
		 if (entity == null) {
	            throw new IllegalArgumentException("Cannot update a null entity");
	        }
		    //entity=getJpaTemplate().find(BillDetailEntity.class, entity.getSno());	
	        entity.setDeletedFlag(false);
	        return getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT)
	public void delete(BillDetailEntity entity) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		if (entity == null) {
            throw new IllegalArgumentException("Cannot remove a null entity");
        }
	 // entity=getJpaTemplate().find(BillDetailEntity.class, entity.getSno());	
	  entity.setDeletedFlag(true);
	  entityManager.getTransaction().begin();
	  entityManager.createQuery("Delete FROM BillDetailEntity e where e.invoiceNumber = '"+entity.getInvoiceNumber()+"'").executeUpdate();
	  entityManager.getTransaction().commit();
	  //getJpaTemplate().merge(entity);
    	}

	@Override
    @Transactional(readOnly = true)
	public List<BillDetailEntity> load() {
		// TODO Auto-generated method stub
		return getJpaTemplate().find("FROM BillDetailEntity e where e.deletedFlag=0 ORDER BY e.modifiedDate DESC");
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<BillDetailEntity> findById(String invoiceNumber) {
		// TODO Auto-generated method stub
		return getJpaTemplate().find("FROM BillDetailEntity e WHERE e.invoiceNumber = "+"'"+ invoiceNumber+"'"+ " AND e.deletedFlag=0 ORDER BY e.sno");
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<BillDetailEntity> findByItemId(Integer itemId) {
		return getJpaTemplate().find("FROM BillDetailEntity e WHERE e.itemId = "+ itemId+ " AND e.deletedFlag=0 ");
		//return getJpaTemplate().find("Select invoiceNumber FROM BillDetailEntity e WHERE e.itemId = "+ itemId+ " AND e.deletedFlag=0");
	}
	
	
	
}
