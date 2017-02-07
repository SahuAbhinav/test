package com.advanz.erp.masters.storage.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.erp.masters.entity.jpa.UserMasterRoleEntity;
import com.advanz.erp.masters.storage.IStorageUserMasterRoleDAO;
@Component
public class StorageUserMasterRoleDAO extends JpaDaoSupport implements IStorageUserMasterRoleDAO {

	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(UserMasterRoleEntity entity) {
		
		getJpaTemplate().persist(entity);		
	}

	@Override
	public UserMasterRoleEntity read(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT)
	public UserMasterRoleEntity update(UserMasterRoleEntity entity) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.createQuery("DELETE FROM UserRoleEntity e WHERE e.userId = '"+entity.getUserId()+"'").executeUpdate();
		entityManager.getTransaction().commit();
		System.out.println("UUUUUUUUUUUUUIIIII"+entity.getUserId() +"PPPPPPP "+entity.getUserPassword());
		return getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(UserMasterRoleEntity entity1) {
		if(entity1==null)
		{
		throw new IllegalArgumentException("can't delete null entity");
		}
		UserMasterRoleEntity entity=getJpaTemplate().find(UserMasterRoleEntity.class,entity1.getUserId());
		entity.setDeletedFlag(true);
		entity.setModifiedUserId(entity1.getModifiedUserId());
	    getJpaTemplate().merge(entity);
	}
	
	
	
	@Override
    @Transactional(readOnly = true)
		public List<UserMasterRoleEntity> load() {
		List<UserMasterRoleEntity> lst=getJpaTemplate().find("FROM UserMasterRoleEntity e where e.deletedFlag=0");
		return lst;
		 //return getJpaTemplate().find("FROM UserMasterRoleEntity e where e.deletedFlag=?0 ORDER BY e.userFullName",false);
		}

	

	@Override
	@Transactional(readOnly = true)
		public List<UserMasterRoleEntity> searchUserMaster(String fullName,	String roleName) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	    StringBuffer hql = new StringBuffer("FROM UserMasterRoleEntity e");
		boolean first = true;
		
		if (StringUtils.hasText(fullName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.userFullName like :userFullName");
		    params.put("userFullName", "%"+fullName.trim()+"%");
		    first=false;
		}	
		//logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		if (StringUtils.hasText(roleName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.roleMasterEntity.roleName like :roleName");
		    params.put("roleName", "%"+roleName.trim()+"%");
		    first=false;
		}
		
		//logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		hql.append(first ? " where " : " and ");
		hql.append("e.deletedFlag=:flag");
		params.put("flag", false);
		//logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}
	@Override
	@Transactional(readOnly = true)
	public List<UserMasterRoleEntity> findLastUserMasterId() {
	
		List<UserMasterRoleEntity> list=getJpaTemplate().find("FROM UserMasterRoleEntity e where e.deletedFlag=0 ORDER BY e.userId DESC LIMIT 1");
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserMasterRoleEntity> getUserMasterRoleById(Integer userId) {
		List<UserMasterRoleEntity> entities= getJpaTemplate().find("FROM UserMasterRoleEntity e WHERE e.userId=?1 and deletedFlag=?2 ORDER BY e.userId", userId,false);
		
		return entities;
	}	

	@Override
	public List<UserMasterRoleEntity> checkUserDuplicateEntry(String userLoginId) {
		List<UserMasterRoleEntity> masterRoleEntities=getJpaTemplate().find("FROM UserMasterRoleEntity e WHERE e.userFullName=?1 and deletedFlag=?2 ORDER BY e.userLoginId", userLoginId,false);
		return masterRoleEntities;
	}
@Override
public List<UserMasterRoleEntity> getUserInfo(String userName){
	List<UserMasterRoleEntity> entities= getJpaTemplate().find("FROM UserMasterRoleEntity e WHERE e.userLoginId=?1 and deletedFlag=0 ORDER BY e.userId", userName);
	return entities;
}  

	 }
	