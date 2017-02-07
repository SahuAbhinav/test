package com.advanz.erp.masters.storage.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.common.DataUtility;
import com.advanz.erp.masters.entity.jpa.LeaveApplicationEntity;
import com.advanz.erp.masters.storage.IStorageLeaveApplicationDAO;

@Component
public class StorageLeaveApplicationDAO extends JpaDaoSupport implements IStorageLeaveApplicationDAO{
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(LeaveApplicationEntity entity) {
		
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public LeaveApplicationEntity read(Integer uid) {
        if (uid == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
        return getJpaTemplate().find(LeaveApplicationEntity.class, uid);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public LeaveApplicationEntity update(LeaveApplicationEntity entity) {
		 if (entity == null) {
	            throw new IllegalArgumentException("Cannot update a null entity");
	        }
	       // entity.setDeletedFlag(false);
		 return getJpaTemplate().merge(entity);
		}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(LeaveApplicationEntity entity1) {
		if (entity1 == null) {
            throw new IllegalArgumentException("Cannot remove a null entity");
        }
		LeaveApplicationEntity  entity=getJpaTemplate().find(LeaveApplicationEntity.class, entity1.getSno());	
		entity.setDeletedFlag(true);
		  entity.setModifiedUserId(entity1.getModifiedUserId());
		getJpaTemplate().merge(entity);	
	   
	   
	}

	@Override
    @Transactional(readOnly = true)
	public List<LeaveApplicationEntity> load() {
	    //return getJpaTemplate().find("FROM BlanketProductionMasterEntity e where e.deletedFlag=?1  ORDER BY e.modifiedDate DESC",false);
		 return getJpaTemplate().find("FROM LeaveApplicationEntity e where e.deletedFlag=?1  ORDER BY e.sno DESC",false);
	}

	@Override
	@Transactional(readOnly = true)
	public<E> List<LeaveApplicationEntity> findById(E uid) {		
		return getJpaTemplate().find("FROM LeaveApplicationEntity e WHERE e.sno =?1 and e.deletedFlag=?2", uid,false);
	}
	@Override
    @Transactional(readOnly = true)
	public List<LeaveApplicationEntity> findLeaveByEmployeeIdAndDate(Integer employeeId,Date date) {
		String toDate1 = null;
		if (date != null) {
			toDate1 = DataUtility.getDate(date);
		}
        //return getJpaTemplate().find("FROM BlanketProductionMasterEntity e where e.deletedFlag=?1  ORDER BY e.modifiedDate DESC",false);
		 return getJpaTemplate().find("FROM LeaveApplicationEntity e where e.deletedFlag=0 and e.employeeEntity.employeeId='"+employeeId+"' and cast(e.fromDate as date)<='"+toDate1+"' and cast(e.toDate as date)>='"+toDate1+"' ORDER BY e.sno DESC");
	}
}
