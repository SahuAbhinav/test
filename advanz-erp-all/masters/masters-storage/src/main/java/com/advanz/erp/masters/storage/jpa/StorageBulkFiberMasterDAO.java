package com.advanz.erp.masters.storage.jpa;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.common.DataUtility;
import com.advanz.erp.masters.entity.jpa.BillEntity;
import com.advanz.erp.masters.entity.jpa.BlanketProductionDetailLeftEntity;
import com.advanz.erp.masters.entity.jpa.BlanketProductionDetailRightEntity;
import com.advanz.erp.masters.entity.jpa.BlanketProductionMasterEntity;
import com.advanz.erp.masters.entity.jpa.BulkFiberDetailEntity;
import com.advanz.erp.masters.entity.jpa.BulkFiberMasterEntity;
import com.advanz.erp.masters.storage.IStorageBlanketProductionMasterDAO;
import com.advanz.erp.masters.storage.IStorageBulkFiberMasterDAO;

@Component
public class StorageBulkFiberMasterDAO extends JpaDaoSupport implements IStorageBulkFiberMasterDAO{
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(BulkFiberMasterEntity entity) {
		
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public BulkFiberMasterEntity read(Integer uid) {
        if (uid == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
        return getJpaTemplate().find(BulkFiberMasterEntity.class, uid);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public BulkFiberMasterEntity update(BulkFiberMasterEntity entity) {
		 if (entity == null) {
	            throw new IllegalArgumentException("Cannot update a null entity");
	        }
	       // entity.setDeletedFlag(false);
		 return getJpaTemplate().merge(entity);
		}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(BulkFiberMasterEntity entity1) {
		if (entity1 == null) {
            throw new IllegalArgumentException("Cannot remove a null entity");
        }
		BulkFiberMasterEntity  entity=getJpaTemplate().find(BulkFiberMasterEntity.class, entity1.getBulkFiberId());	
	  entity.setDeletedFlag(true);
	  entity.setModifiedUserId(entity1.getModifiedUserId());
	  if(entity.getBulkFiberDetailEntityList()!=null){
	  for(BulkFiberDetailEntity le:entity.getBulkFiberDetailEntityList()){
	le.setDeletedFlag(true);
	le.setModifiedUserId(entity1.getModifiedUserId());
	  }
	}
	
	if(entity.getBulkFiberDetailEntityList()!=null){
		  for(BulkFiberDetailEntity re:entity.getBulkFiberDetailEntityList()){
		re.setDeletedFlag(true);
		re.setModifiedUserId(entity1.getModifiedUserId());
		  }
		}
	
	   	getJpaTemplate().merge(entity);				
	}

	@Override
    @Transactional(readOnly = true)
	public List<BulkFiberMasterEntity> load() {
		
        //return getJpaTemplate().find("FROM BlanketProductionMasterEntity e where e.deletedFlag=?1  ORDER BY e.modifiedDate DESC",false);
		 return getJpaTemplate().find("FROM BulkFiberMasterEntity e where e.deletedFlag=?1  ORDER BY e.bulkFiberId DESC",false);
	}

	@Override
	@Transactional(readOnly = true)
	public<E> List<BulkFiberMasterEntity> findById(E uid) {		
		return getJpaTemplate().find("FROM BulkFiberMasterEntity e WHERE e.bulkFiberId =?1 and e.deletedFlag=?2", uid,false);
	}
	

	@Override
	@Transactional(readOnly = true)
	public List<BulkFiberMasterEntity> search(Date fromDate,Date toDate,String runNo,Integer gradeId){
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM BulkFiberMasterEntity e");
		boolean first = true;
		
		if (StringUtils.hasText(runNo)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.runNo like :runNo");
		    params.put("runNo", "%"+runNo.trim()+"%");
		    first=false;
		}
		if (gradeId!=null) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.gradeMasterEntity.mastersId=:gradeId");
		    params.put("gradeId", gradeId);
		    first=false;
		}
		if (fromDate!=null || toDate!=null) {
		    hql.append(first ? " where " : " and ");
		    String fromDate1="";
		    if (fromDate!=null){
		     fromDate1=DataUtility.getDate(fromDate);
		    }
		    String toDate1=null;
		    if (toDate!=null) {
		     toDate1=DataUtility.getDate(toDate);
		    } 
		    
		    hql.append("e.bulkFiberDate >= '"+ fromDate1 +"' AND e.bulkFiberDate <= '"+ toDate1 +"'");
		    first=false;
		}
		hql.append(first ? " where " : " and ");
	    hql.append(" e.deletedFlag=0 ORDER BY e.bulkFiberId DESC");
	    first=false;
		
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}
	@Override
	@Transactional(readOnly = true)
	public List<BulkFiberMasterEntity> findByDateAndRunNo(Date date,String runNo,Integer shiftId){
	return getJpaTemplate().find("FROM BulkFiberMasterEntity e where e.runNo=?1 and e.bulkFiberDate=?2 and e.shiftMasterEntity.mastersId=?3 and e.deletedFlag=0",runNo,date,shiftId);
	}

	@Override
	public List<BulkFiberMasterEntity> getListWithPagination(Integer index) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		Query query=entityManager.createQuery("FROM BulkFiberMasterEntity e  where e.deletedFlag=0 ORDER BY e.bulkFiberId DESC");
		query.setFirstResult(index);
		query.setMaxResults(15);
		List<BulkFiberMasterEntity> list = query.getResultList();
		entityManager.close();
		return list;
	}
	
}
