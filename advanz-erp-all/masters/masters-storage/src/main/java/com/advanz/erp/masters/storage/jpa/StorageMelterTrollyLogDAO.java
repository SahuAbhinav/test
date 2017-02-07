package com.advanz.erp.masters.storage.jpa;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.common.DataUtility;
import com.advanz.erp.masters.entity.jpa.MelterTrollyLogEntity;
import com.advanz.erp.masters.storage.IStorageMelterTrollyLogDAO;
import org.springframework.util.StringUtils;
public class StorageMelterTrollyLogDAO extends JpaDaoSupport implements IStorageMelterTrollyLogDAO
{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(MelterTrollyLogEntity entity) {
		 if(entity==null)
		  {
			  throw new IllegalArgumentException("can't create null entity");
		  }	
		getJpaTemplate().persist(entity);
		
	}

	@Override
	public MelterTrollyLogEntity read(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MelterTrollyLogEntity update(MelterTrollyLogEntity entity) {
		
		return	getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(MelterTrollyLogEntity entity1) {
		if(entity1==null)
		{
		throw new IllegalArgumentException("can't delete null entity");
		}
		MelterTrollyLogEntity entity=getJpaTemplate().find(MelterTrollyLogEntity.class,entity1.getSno());
		entity.setDeletedFlag(true);
		entity.setModifiedUserId(entity1.getModifiedUserId());
	    getJpaTemplate().merge(entity);
		
	}


	@Override
	public List<MelterTrollyLogEntity> load() {
	   logger.info("loding MelterTrollyLogEntity Data");
	   return getJpaTemplate().find("FROM MelterTrollyLogEntity e where e.deletedFlag=?1 ORDER BY e.modifiedDate DESC",false);
	}
	
	@Override
    @Transactional(readOnly = true)
	public List<MelterTrollyLogEntity> findBySno(Integer sno) {
	 if(sno==null)
	 {
	  throw new IllegalArgumentException("can't find record if sno is null");
	 }	
	 return  getJpaTemplate().find("FROM MelterTrollyLogEntity e where e.sno=?1 ORDER BY e.modifiedDate",sno);
	}
	
	
	
	@Override
	@Transactional(readOnly = true)
	public List<MelterTrollyLogEntity> checkDuplicateTrolly(Date logDate,	Time logTime, String trollyNo) {
		return getJpaTemplate().find("FROM MelterTrollyLogEntity e where e.logDate=?1 and e.logTime=?2 and e.trollyNumber=?3 and e.deletedFlag=?4",logDate,logTime,trollyNo.trim(),false);
	}

	@Override
	public List<MelterTrollyLogEntity> search(Date fromDate, Date toDate,String trollyNumber) 
	{
		Map<String, Object> params = new HashMap<String, Object>();
		 StringBuffer hql = new StringBuffer("FROM MelterTrollyLogEntity e");
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
			    
			    hql.append("e.logDate >= '"+ fromDate1 +"' AND e.logDate <= '"+ toDate1 +"'");
			    first=false;
			}
			
			
			if (StringUtils.hasText(trollyNumber)) {
			    hql.append(first ? " where " : " and ");
			    hql.append("e.trollyNumber like :trollyNumber");
			    params.put("trollyNumber", "%"+trollyNumber.trim()+"%");
			    first=false;
			}
			hql.append(first ? " where " : " and ");
			hql.append("e.deletedFlag=:flag");
			params.put("flag", false);
			return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	
}
