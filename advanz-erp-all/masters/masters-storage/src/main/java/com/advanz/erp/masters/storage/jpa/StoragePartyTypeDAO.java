package com.advanz.erp.masters.storage.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.erp.masters.entity.jpa.PartyEntity;
import com.advanz.erp.masters.entity.jpa.PartyTypeEntity;
import com.advanz.erp.masters.storage.IStoragePartyTypeDAO;

@Component
public class StoragePartyTypeDAO extends JpaDaoSupport implements IStoragePartyTypeDAO{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(PartyTypeEntity entity) {
		
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        getJpaTemplate().persist(entity);
        
	}

	@Override
	@Transactional(readOnly = true)
	public PartyTypeEntity read(Integer uid) {
        if (uid == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
        return getJpaTemplate().find(PartyTypeEntity.class, uid);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public PartyTypeEntity update(PartyTypeEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Cannot update a null entity");
        }
        return getJpaTemplate().merge(entity);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(PartyTypeEntity entity1) {
		 if (entity1 == null) {
	            throw new IllegalArgumentException("Cannot delete a null entity");
	        }
		 
		 PartyTypeEntity entity=getJpaTemplate().find(PartyTypeEntity.class, entity1.getPartyTypeId());
		entity.setDeletedFlag(true);
		entity.setModifiedUserId(entity1.getModifiedUserId());
	     getJpaTemplate().merge(entity);		
	}

	@Override
	@Transactional(readOnly = true)
	public List<PartyTypeEntity> findById(Integer uid) {	
		
		return getJpaTemplate().find("FROM PartyTypeEntity e WHERE e.partyTypeId = ?1 and e.deletedFlag = ?2 ORDER BY e.partyTypeDesc", uid,false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PartyTypeEntity> findByName(String partyTypeDesc) {
		
		return getJpaTemplate().find("FROM PartyTypeEntity e WHERE e.partyTypeDesc = ?1 and deletedFlag=?2 ORDER BY e.partyTypeDesc", partyTypeDesc.trim(),false);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<PartyTypeEntity> findByCode(String partyTypeCode) {
		
		return getJpaTemplate().find("FROM PartyTypeEntity e WHERE e.partyTypeCode=?1 and deletedFlag=?2 ORDER BY e.partyTypeDesc", partyTypeCode.trim(),false);
		
	}
	
	@Override
    @Transactional(readOnly = true)
	public List<PartyTypeEntity> load() {	
		return getJpaTemplate().find("FROM PartyTypeEntity e WHERE e.deletedFlag = ?1 ORDER BY e.partyTypeDesc",false);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<PartyTypeEntity> search(String partyTypeDesc, String partyTypeCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM PartyTypeEntity e");
		boolean first = true;
		if (StringUtils.hasText(partyTypeDesc)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.partyTypeDesc like :partyTypeDesc");
		    params.put("partyTypeDesc", "%"+partyTypeDesc.trim()+"%");
		    first=false;
		}
		if (StringUtils.hasText(partyTypeCode)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.partyTypeCode like :partyTypeCode");
		    params.put("partyTypeCode", "%"+partyTypeCode.trim()+"%");
		    first=false;
		}
		hql.append(first ? " where " : " and ");
		hql.append("e.deletedFlag=:flag");
		params.put("flag", false);
		
		hql.append(" ORDER BY e.partyTypeDesc");
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	public boolean isInUsed(Integer id) {
		List<Integer> partyList=getJpaTemplate().find("Select partyId FROM PartyEntity e WHERE e.deletedFlag=0 and e.partyTypeEntity.partyTypeId = ?1",id);
		if(partyList!=null && partyList.size()>0){
			return true;
		}
		return false;
	}
}
