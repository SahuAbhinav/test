package com.advanz.erp.masters.storage.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.erp.masters.entity.jpa.TransactionTypeEntity;
import com.advanz.erp.masters.storage.IStorageTransactionTypeDAO;

@Component
public class StorageTransactionTypeDAO extends JpaDaoSupport implements IStorageTransactionTypeDAO {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(TransactionTypeEntity entity) {
		
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public TransactionTypeEntity read(Integer uid) {
        if (uid == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
        return getJpaTemplate().find(TransactionTypeEntity.class, uid);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public TransactionTypeEntity update(TransactionTypeEntity entity) {
		 if (entity == null) {
	            throw new IllegalArgumentException("Cannot update a null entity");
	        }
	       // entity.setDeletedFlag(false);
	        return getJpaTemplate().merge(entity);
		}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(TransactionTypeEntity entity) {
				
	}

	@Override
	public <E> List<TransactionTypeEntity> findById(E id) {
		return getJpaTemplate().find("FROM TransactionTypeEntity e WHERE e.sno =?1",id);
	}

	@Override
	public List<TransactionTypeEntity> findByName(String name) {		
		return getJpaTemplate().find("FROM TransactionTypeEntity e WHERE e.name =?1",name);
	}

	@Override
	public List<TransactionTypeEntity> findBySeries(String series) {
		return getJpaTemplate().find("FROM TransactionTypeEntity e WHERE e.series =?1",series);
	}

	

}
