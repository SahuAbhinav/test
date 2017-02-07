package com.advanz.erp.masters.storage.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.common.DataUtility;
import com.advanz.erp.masters.entity.jpa.AdvanceAmountEntity;
import com.advanz.erp.masters.entity.jpa.ProfessionalTaxEntity;
import com.advanz.erp.masters.storage.IStorageAdvanceAmountDAO;
import com.advanz.erp.masters.storage.IStorageProfessionalTaxDAO;

public class StorageAdvanceAmountDAO extends JpaDaoSupport implements  IStorageAdvanceAmountDAO{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(AdvanceAmountEntity entity) {
		
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        getJpaTemplate().persist(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public AdvanceAmountEntity read(Integer uid) {
        if (uid == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
     //   return getJpaTemplate().find(BatchEntity.class, uid);
       return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public AdvanceAmountEntity update(AdvanceAmountEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Cannot update a null entity");
        }
        return getJpaTemplate().merge(entity);
	}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(AdvanceAmountEntity entity1) {
		  
		  if (entity1 == null) {
	            throw new IllegalArgumentException("Cannot update a null entity");
	        }
		  AdvanceAmountEntity entity=getJpaTemplate().find(AdvanceAmountEntity.class, entity1.getSno()); 
		  entity.setDeletedFlag(true);
		  entity.setModifiedUserId(entity1.getModifiedUserId());
		  getJpaTemplate().merge(entity);
	}

	@Override
    @Transactional(readOnly = true)
	public List<AdvanceAmountEntity> load() {
		logger.info("Loading AdvanceAmountEntity data ");
          List<AdvanceAmountEntity> lsit= getJpaTemplate().find("FROM AdvanceAmountEntity e where e.deletedFlag=?1 ORDER BY e.sno DESC",false);
      	  return lsit;
	}

	@Override
	@Transactional(readOnly = true)
	public List<AdvanceAmountEntity> findById(Integer id) {
		// TODO Auto-generated method stub
		return getJpaTemplate().find("FROM AdvanceAmountEntity e WHERE e.sno="+id+" and deletedFlag=0");
	}

	@Override
	@Transactional(readOnly = true)
	public List<AdvanceAmountEntity> findByEmployeeId(Integer employee) {

		return getJpaTemplate().find("FROM AdvanceAmountEntity e WHERE e.sno=(select MAX(a.sno) from AdvanceAmountEntity a where a.employeeEntity.employeeId="+employee+") and deletedFlag=0");
	}

	@Override
	public List<AdvanceAmountEntity> search(Date toDate, Date fromDate,
			String empName, String designation, String transactionType) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		StringBuffer hql = new StringBuffer("FROM AdvanceAmountEntity e");
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
		    
		    hql.append("e.transactionDate >= '"+ fromDate1 +"' AND e.transactionDate <= '"+ toDate1 +"'");
		    first=false;
		}
		
		if (StringUtils.hasText(empName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.employeeName like :employeeName");
		    params.put("employeeName", '%'+empName.trim()+'%');
		    first=false;
		}
		
		if (StringUtils.hasText(designation)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.employeeDesignation like :employeeDesignation");
		    params.put("employeeDesignation", '%'+designation.trim()+'%');
		    first=false;
		}
		if (StringUtils.hasText(transactionType)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.transactionType like :transactionType");
		    params.put("transactionType", '%'+transactionType.trim()+'%');
		    first=false;
		}
		
		hql.append(first ? " where " : " and ");
		hql.append("e.deletedFlag=:flag");
		params.put("flag", false);
		hql.append(" ORDER BY e.sno DESC ");
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	public Integer getTransactionNo() {
		 int id=0;
	     List list=getJpaTemplate().find("SELECT max(e.sno) FROM AdvanceAmountEntity e WHERE e.deletedFlag=0 ");
	     if(list!=null && list.size()>0){
	     	Number n=(Number)list.get(0);
	     	if(n!=null)
	     	id=n.intValue();
	     }
	     id++;
		return id;
	}

	@Override
	public List<AdvanceAmountEntity> findLastDate(Date firstDate,Date lastDate, Integer empId) {
		List<AdvanceAmountEntity> adList=new ArrayList<AdvanceAmountEntity>();
		if (lastDate!=null && firstDate!=null) {
		    String fromDate="";
		    if (firstDate!=null){
		    	fromDate=DataUtility.getDate(firstDate);
		    }
		    String toDate="";
			if (lastDate!=null){
			   	toDate=DataUtility.getDate(lastDate);
			   }
		  adList= getJpaTemplate().find(" FROM AdvanceAmountEntity e WHERE e.transactionDate >= '"+ fromDate +"' and e.transactionDate <= '"+ toDate +"' and e.employeeEntity.employeeId="+empId+" and e.deletedFlag=0 ORDER BY e.sno DESC ");
		}

		return adList;
	}

	@Override
	public Integer findbyEmpIdAndTrnsMonth(Integer month, Integer year,Integer empId) {
		 int id=0;
	     List list=getJpaTemplate().find("FROM AdvanceAmountEntity e WHERE MONTH(e.transactionDate)="+month+" and YEAR(e.transactionDate)="+year+" and e.employeeEntity.employeeId= "+empId+" and e.deletedFlag=0 ");
	     if(list!=null && list.size()>0){
	     	id=1;
	     }
		return id;
	}	
	
}
