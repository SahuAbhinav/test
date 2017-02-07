package com.advanz.erp.masters.storage.jpa;

import java.util.List;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.erp.masters.entity.jpa.UserRoleAndRightsEntity;
import com.advanz.erp.masters.storage.IStorageRoleAndRightsDAO;

public class StrorageRoleAndRightsDAO extends JpaDaoSupport implements IStorageRoleAndRightsDAO
{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(UserRoleAndRightsEntity entity) {
		if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public UserRoleAndRightsEntity read(Integer id) {
		 if (id == null) {
	            throw new IllegalArgumentException("uid must not be null");
	        }
		    return getJpaTemplate().find(UserRoleAndRightsEntity.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public UserRoleAndRightsEntity update(UserRoleAndRightsEntity entity) {
		  if (entity == null) {
	            throw new IllegalArgumentException("Cannot update a null entity");
	        }
	        return getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(UserRoleAndRightsEntity entity) {
		  if (entity == null) {
	            throw new IllegalArgumentException("Cannot Delete a null entity");
	        }
		  entity=getJpaTemplate().find(UserRoleAndRightsEntity.class, entity.getSno()); 
		  entity.setDeletedFlag(true);
	        getJpaTemplate().merge(entity);
		}

	@Override
	public List<UserRoleAndRightsEntity> findByRoleName(String roleName) {
		
		return null;
	}

	@Override
    @Transactional(readOnly = true)
	public List<UserRoleAndRightsEntity> findAllRoleAndRights() {
		// TODO Auto-generated method stub
		return  getJpaTemplate().find("FROM UserRoleAndRightsEntity e where e.deletedFlag=0 ORDER BY e.modifiedDate DESC");
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Integer> findRoleAndRightsById(Integer id) {
		List<Integer> list= getJpaTemplate().find("Select e.menuId FROM  UserRoleAndRightsEntity e WHERE e.deletedFlag=0 and e.roleId=?1 ORDER BY e.roleId",id);
		//System.out.println("------------------>>>>>---------->>>-------"+list.toString());
	return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserRoleAndRightsEntity> findRoleAndRightsByRoleId(Integer id) {
	return getJpaTemplate().find("FROM UserRoleAndRightsEntity e WHERE e.deletedFlag=0 and e.roleId=?1 ORDER BY e.roleId",id);

	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Integer> findSnoFromRoleAndRightsByRoleId(Integer id) {
		List<Integer> list= getJpaTemplate().find("Select e.sno FROM  UserRoleAndRightsEntity e WHERE e.deletedFlag=0 and e.roleId=?1 ORDER BY e.roleId",id);
		//System.out.println("------------------>>>>>---------->>>-------"+list.toString());
	return list;
	}
	
	@Override
	public List<UserRoleAndRightsEntity> findRoleAndRightsByUserName(String userName) {                                                                                                                                                                            
		List<UserRoleAndRightsEntity> list= getJpaTemplate().find("SELECT e FROM UserRoleAndRightsEntity e, UserMasterRoleEntity ue , UserRoleEntity re WHERE ue.userId=re.userId AND ue.userLoginId="+"'"+userName+"' AND re.roleId=e.roleId AND e.deletedFlag=0 ORDER BY e.roleId");
	
		return list;
	  }
	
}
