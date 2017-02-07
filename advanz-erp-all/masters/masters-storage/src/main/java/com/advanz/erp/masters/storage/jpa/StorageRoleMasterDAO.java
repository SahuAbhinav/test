package com.advanz.erp.masters.storage.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//import com.advanz.erp.masters.entity.jpa.RoleEntity;
import com.advanz.erp.masters.entity.jpa.RoleMasterEntity;
import com.advanz.erp.masters.entity.jpa.UserMasterRoleEntity;
import com.advanz.erp.masters.storage.IStorageRoleMasterDAO;
@Component
public class StorageRoleMasterDAO extends JpaDaoSupport implements IStorageRoleMasterDAO
 {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(RoleMasterEntity entity) {
		
		getJpaTemplate().persist(entity);		
	}

	@Override
	@Transactional(readOnly = true)
	public RoleMasterEntity read(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
        return getJpaTemplate().find(RoleMasterEntity.class, id);

	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public RoleMasterEntity update(RoleMasterEntity entity) {
		return getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(RoleMasterEntity entity) {
		 if (entity == null) {
	            throw new IllegalArgumentException("Cannot update a null entity");
	        }
	        entity.setDeletedFlag(true);
	        getJpaTemplate().merge(entity);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<RoleMasterEntity> findByRoleNames(String roleName) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM RoleMasterEntity e");
		boolean first = true;
		
	    hql.append(first ? " where " : " and ");
	    hql.append("e.roleName like :roleName");
	    params.put("roleName", "%"+roleName+"%" );
	    first=false;
	    
		hql.append(first ? " where " : " and ");
	    hql.append(" e.deletedFlag=0 ");
	   
	    first=false;
		
		
		//logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}



	@Override
	@Transactional(readOnly = true)
	public List<RoleMasterEntity> load() {
		List<RoleMasterEntity> entities= getJpaTemplate().find("FROM RoleMasterEntity e WHERE e.deletedFlag=0");	
	  return entities;
	}

	@Override
	public List<RoleMasterEntity> searchByName(String name) {
		List<RoleMasterEntity> list=getJpaTemplate().find("FROM RoleMasterEntity e WHERE e.roleName=?1 ORDER BY e.roleName",name);
		return list;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<RoleMasterEntity> findByLastId() {
		List<RoleMasterEntity> list=getJpaTemplate().find("FROM RoleMasterEntity e where e.deletedFlag=0 ORDER BY e.roleId DESC LIMIT 1");
	
		return list;
	}

	
 }
