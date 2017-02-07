package com.advanz.erp.masters.storage.jpa;

import java.util.List;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.advanz.erp.masters.entity.jpa.UserRoleEntity;
import com.advanz.erp.masters.storage.IStorageUserRoleDAO;

public class StorageUserRoleDAO extends JpaDaoSupport implements IStorageUserRoleDAO
{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(UserRoleEntity entity) {
		
		getJpaTemplate().persist(entity);
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserRoleEntity read(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public UserRoleEntity update(UserRoleEntity entity) {
		return getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(UserRoleEntity entity) {
		/*
		UserRoleEntity entity1=getJpaTemplate().find(UserRoleEntity.class,entity.getUserId());
		*/
		List l= getJpaTemplate().find("FROM UserRoleEntity e where userId="+entity.getUserId());
		if(l!=null && l.size()>0){
			entity=(UserRoleEntity)l.get(0);
		}
		entity.setDeletedFlag(true);
	    getJpaTemplate().merge(entity);
		
		// TODO Auto-generated method stub
		
	}


	@Override
    @Transactional(readOnly = true)
	public List<UserRoleEntity> load() {
		// TODO Auto-generated method stub
		return getJpaTemplate().find("FROM UserRoleEntity e",false);
	}

	@Override
    @Transactional(readOnly = true)
	public List<UserRoleEntity> findUserRoleByRoleID(Integer roleId) {
	
		 List<UserRoleEntity> entities= getJpaTemplate().find("FROM UserRoleEntity e where roleId="+roleId);
	
		 return entities;
	}
 }
