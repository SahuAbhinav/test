package com.advanz.erp.masters.storage.jpa;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.common.DataUtility;
import com.advanz.erp.masters.entity.jpa.MelterLogBookEntity;
import com.advanz.erp.masters.storage.IStorageMelterLogBookDAO;

public class StorageMelterLogBookDAO extends JpaDaoSupport implements IStorageMelterLogBookDAO{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(MelterLogBookEntity entity) {
		if(entity!=null){
	    entity.setDeletedFlag(false);	
		getJpaTemplate().persist(entity);
	}}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MelterLogBookEntity read(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MelterLogBookEntity update(MelterLogBookEntity entity) {
		 if (entity == null) {
	            throw new IllegalArgumentException("Cannot update a null entity");
	        }
		entity.setDeletedFlag(false); 
		return getJpaTemplate().merge(entity);
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(MelterLogBookEntity entity1) {
		 if (entity1 == null) {
	            throw new IllegalArgumentException("Cannot delete a null entity");
	        }
		 MelterLogBookEntity entity=getJpaTemplate().find(MelterLogBookEntity.class, entity1.getSno()); 
		  entity.setDeletedFlag(true);
		  entity.setModifiedUserId(entity1.getModifiedUserId());
		  getJpaTemplate().merge(entity);
		// TODO Auto-generated method stub
		
	}

	@Override
    @Transactional(readOnly = true)
	public List<MelterLogBookEntity> load() {
		// TODO Auto-generated method stub	 
		return getJpaTemplate().find("from MelterLogBookEntity e where  e.deletedFlag=?1 ORDER BY e.modifiedDate DESC",false);
	}

	
	@Override
	@Transactional(readOnly = true)
	public List<MelterLogBookEntity> search(Date fromDate,Date toDate,String runNo,String operatorName) {
		Map<String, Object> params = new HashMap<String, Object>();
	
		StringBuffer hql = new StringBuffer("FROM MelterLogBookEntity e");
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
		
		if (StringUtils.hasText(runNo)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.runNo like :runNo");
		    params.put("runNo", "%"+runNo.trim()+"%");
		    first=false;
		}
		
		if (StringUtils.hasText(operatorName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.operatorName like :operatorName");
		    params.put("operatorName", "%"+operatorName.trim()+"%");
		    first=false;
		}
		
		hql.append(first ? " where " : " and ");
		hql.append("e.deletedFlag=:flag");
		params.put("flag", false);
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	
	@Override
    @Transactional(readOnly = true)
	public List<MelterLogBookEntity> findById(Integer sno) {
		return getJpaTemplate().find("FROM MelterLogBookEntity e WHERE e.sno=?1 ORDER BY e.sno", sno);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MelterLogBookEntity> findByDateRunNoShiftTime(Date logDate,	String runNo, Integer masterId, Time logTime) {
		return getJpaTemplate().find("FROM MelterLogBookEntity m WHERE m.logDate=?1 and m.runNo=?2  and m.mastersEntity.mastersId=?3 and m.logTime=?4 and m.deletedFlag=?5 ORDER BY m.modifiedDate DESC",logDate,runNo.trim(),masterId,logTime,false);
		
	}

	@Override
	public List getLastRecordDate() {
		return getJpaTemplate().find(" select max(logDate) FROM MelterLogBookEntity m WHERE m.deletedFlag=0");
	}

	
	
  
}
