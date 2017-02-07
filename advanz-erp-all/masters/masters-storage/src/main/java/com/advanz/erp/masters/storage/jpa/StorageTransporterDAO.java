package com.advanz.erp.masters.storage.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.erp.masters.entity.jpa.TransporterEntity;
import com.advanz.erp.masters.storage.IStorageTransporterDAO;

@Component
public class StorageTransporterDAO extends JpaDaoSupport implements IStorageTransporterDAO{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(TransporterEntity entity) {
		
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        getJpaTemplate().persist(entity);
        
	}

	@Override
	@Transactional(readOnly = true)
	public TransporterEntity read(Integer uid) {
        if (uid == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
        return getJpaTemplate().find(TransporterEntity.class, uid);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public TransporterEntity update(TransporterEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Cannot update a null entity");
        }
        return getJpaTemplate().merge(entity);
	}


	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(TransporterEntity entity) {
		 if (entity == null) {
	            throw new IllegalArgumentException("Cannot delete a null entity");
	     }
		 entity=getJpaTemplate().find(TransporterEntity.class, entity.getTransporterId());
		 entity.setDeletedFlag(true);
	     getJpaTemplate().merge(entity);		
	}

	@Override
	@Transactional(readOnly = true)
	public List<TransporterEntity> findById(Integer uid) {		
		return getJpaTemplate().find("FROM TransporterEntity e WHERE e.transporterId = ?1 and e.deletedFlag = ?2 ORDER BY e.transName", uid, false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TransporterEntity> findByName(String transporterName) {
		
		return getJpaTemplate().find("FROM TransporterEntity e WHERE e.transName = ?1 and deletedFlag=?2 ORDER BY e.transName", transporterName.trim(),false);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<TransporterEntity> findByCode(String transporterCode) {
		
		return getJpaTemplate().find("FROM TransporterEntity e WHERE e.transCode=?1 and deletedFlag=?2 ORDER BY e.transName", transporterCode.trim(),false);
		
	}
	
	@Override
    @Transactional(readOnly = true)
	public List<TransporterEntity> load() {	
	return getJpaTemplate().find("FROM TransporterEntity e WHERE e.deletedFlag = ?1 and activeStatus=1 ORDER BY e.transName",false);
	//	return getJpaTemplate().find("FROM TransporterEntity e WHERE e.deletedFlag = ?1 ORDER BY e.transName",false);

	}
	
	@Override
    @Transactional(readOnly = true)
	public List preload() {	
	return getJpaTemplate().find("Select e.transporterId,e.transName FROM TransporterEntity e WHERE e.deletedFlag = ?1 and activeStatus=1 ORDER BY e.transName",false);
	//	return getJpaTemplate().find("FROM TransporterEntity e WHERE e.deletedFlag = ?1 ORDER BY e.transName",false);

	}
	
	@Override
	@Transactional(readOnly = true)
	public List<TransporterEntity> search(String transName, String transAddress, String transCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM TransporterEntity e");
		boolean first = true;
		if (StringUtils.hasText(transName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.transName like :transName");
		    params.put("transName", "%"+transName.trim()+"%");
		    first=false;
		}
		//logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		if (StringUtils.hasText(transAddress)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.transAddress like :transAddress");
		    params.put("transAddress", "%"+transAddress.trim()+"%");
		    first=false;
		}
		//logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		if (StringUtils.hasText(transCode)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.transCode like :transCode");
		    params.put("transCode", "%"+transCode.trim()+"%");
		    first=false;
		}
	//	logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());		
		hql.append(first ? " where " : " and ");
		hql.append("e.deletedFlag = :flag");
		params.put("flag", false);
		
		hql.append(" ORDER BY e.transName");
	//	logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	public boolean isInUsed(Integer id) {
	
			List<Integer> list=getJpaTemplate().find("select partyId from PartyEntity e where e.deletedFlag=0 and e.transporterId=?1",id);
			//logger.info("List:"+list);
			if(list!=null && list.size()>0){
				return true;
			}
		return false;
	}	
}
