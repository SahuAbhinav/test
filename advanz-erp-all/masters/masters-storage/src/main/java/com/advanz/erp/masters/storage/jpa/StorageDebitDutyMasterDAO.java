package com.advanz.erp.masters.storage.jpa;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.common.DataUtility;
import com.advanz.erp.masters.entity.jpa.BillEntity;
import com.advanz.erp.masters.entity.jpa.DebitDutyMasterEntity;
import com.advanz.erp.masters.storage.IStorageDebitDutyMasterDAO;

public class StorageDebitDutyMasterDAO extends JpaDaoSupport implements IStorageDebitDutyMasterDAO{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(DebitDutyMasterEntity entity) {
		// TODO Auto-generated method stub
		   if (entity == null) {
	            throw new IllegalArgumentException("Cannot create a null entity");
	        }
	        getJpaTemplate().merge(entity);
		
	}

	@Override
	@Transactional(readOnly = true)
	public DebitDutyMasterEntity read(Integer id) {
		if (id == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
        return getJpaTemplate().find(DebitDutyMasterEntity.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public DebitDutyMasterEntity update(DebitDutyMasterEntity entity) {
			// TODO Auto-generated method stub
			 if (entity == null) {
		            throw new IllegalArgumentException("Cannot update a null entity");
		        }
			 entity.setDeletedFlag(false);
		        return getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(DebitDutyMasterEntity entity) {
		if (entity == null) {
            throw new IllegalArgumentException("Cannot delete a null entity");
     }
	 entity.setDeletedFlag(true);
     getJpaTemplate().merge(entity);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<DebitDutyMasterEntity> load() {
		// TODO Auto-generated method stub
		return getJpaTemplate().find("FROM DebitDutyMasterEntity e  where e.deletedFlag=0 ORDER BY e.debitDutyAutoId DESC");
	}

	@Override
	public <E> List<DebitDutyMasterEntity> findById(E id) {
		return getJpaTemplate().find("FROM DebitDutyMasterEntity e WHERE e.debitDutyAutoId = "+ id +"ORDER BY e.modifiedDate DESC");
	}
	@Override
	@Transactional(readOnly = true)
	public List<DebitDutyMasterEntity> getLastByDebitDutyId() {
	// TODO Auto-generated method stub
		return getJpaTemplate().find("FROM DebitDutyMasterEntity e   where e.deletedFlag=0 ORDER BY e.debitDutyAutoId DESC LIMIT 1");
		}
	
	@Override
	public List<DebitDutyMasterEntity> search(Date fromDate, Date toDate,
			Integer approvedStatus) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM DebitDutyMasterEntity e");		
		boolean first = true;
	   
		
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
		    
		    hql.append("cast( e.debitDutyDate as date) >= '"+ fromDate1 +"' AND cast(e.debitDutyDate as date) <= '"+ toDate1 +"'");
		    first=false;
		   }
		
		if (approvedStatus!=null && approvedStatus>0) {
			hql.append(first ? " where " : " and ");
		    hql.append("e.approvedFlag like :approvedFlag");
		    params.put("approvedFlag", approvedStatus);
		    first = false;
		  	}
		//logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		hql.append(first ? " where " : " and ");
		hql.append(" e.deletedFlag=0  ORDER BY e.modifiedDate DESC ");
		first = false;
	//	logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		List l= getJpaTemplate().findByNamedParams(hql.toString(), params);
		return l;
	    }
	@Override
	public List<DebitDutyMasterEntity> getMaxDebitId() {
		// TODO Auto-generated method stub
		return getJpaTemplate().find("FROM DebitDutyMasterEntity e   where e.deletedFlag=0 ORDER BY e.debitDutyAutoId DESC LIMIT 1");
	}
	
	@Override
	public List<DebitDutyMasterEntity> getFinacialYear() {
		// TODO Auto-generated method stub
		 return getJpaTemplate().find("SELECT finyr FROM  DebitDutyMasterEntity e  where e.deletedFlag=0 GROUP BY e.finyr");
	}

	@Override
	public List getNewSeriesNo(String finYear) {
		 
		return getJpaTemplate().find("SELECT max(e.debitDutyId),max(debitDutyDate) FROM DebitDutyMasterEntity e WHERE e.finyr=?1 and e.deletedFlag=0 ",finYear);
		
	}

	
}