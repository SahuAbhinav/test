package com.advanz.erp.masters.storage.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.erp.masters.entity.jpa.IssueTypeMasterEntity;
import com.advanz.erp.masters.storage.IStorageIssueTypeMasterDAO;

@Component
public class StorageIssueTypeMasterDAO extends JpaDaoSupport implements IStorageIssueTypeMasterDAO {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(IssueTypeMasterEntity entity) {
		
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public IssueTypeMasterEntity read(Integer uid) {
        if (uid == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
        return getJpaTemplate().find(IssueTypeMasterEntity.class, uid);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public IssueTypeMasterEntity update(IssueTypeMasterEntity entity) {
		 if (entity == null) {
	            throw new IllegalArgumentException("Cannot update a null entity");
	        }
	       // entity.setDeletedFlag(false);
	        return getJpaTemplate().merge(entity);
		}

	@Override
	public List<IssueTypeMasterEntity> getIssueBySno(Integer sno) {
		
		return getJpaTemplate().find("FROM IssueTypeMasterEntity e WHERE e.sno = "+ sno);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(IssueTypeMasterEntity entity) {
				
	}

	@Override
	public List<IssueTypeMasterEntity> load() {
		   return getJpaTemplate().find("FROM IssueTypeMasterEntity e ORDER BY e.issueType");
	}

	
}
