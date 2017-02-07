package com.advanz.erp.masters.storage.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.erp.masters.entity.jpa.ModuleMenuMasterEntity;
import com.advanz.erp.masters.storage.IStorageModuleMenuMasterDAO;

public class StorageModuleMenuMasterDAO extends JpaDaoSupport implements IStorageModuleMenuMasterDAO{

	
	
	@Override
    @Transactional(readOnly = true)
	public List<ModuleMenuMasterEntity> load() {
		// TODO Auto-generated method stub
		return getJpaTemplate().find("FROM ModuleMenuMasterEntity e ");
	}
	
	

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(ModuleMenuMasterEntity entity) {
		if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
		
	    getJpaTemplate().persist(entity);
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ModuleMenuMasterEntity update(ModuleMenuMasterEntity entity) {
		 if (entity == null) {
	            throw new IllegalArgumentException("Cannot update a null entity");
	        }
		 
	        return getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(ModuleMenuMasterEntity entity) {
		if (entity == null) {
            throw new IllegalArgumentException("Cannot remove a null entity");
        }
      getJpaTemplate().merge(entity);
	}

	
	@Override
	public List<ModuleMenuMasterEntity> findModuleMasterByName(String moduleName ,String loginUserName) {
 
		//return getJpaTemplate().find("FROM ModuleMenuMasterEntity e where e.activeStatus=1 AND e.moduleName="+"'"+moduleName+"' ORDER BY e.menuId");
		
		//return getJpaTemplate().find("FROM ModuleMenuMasterEntity e where e.activeStatus=1 AND e.moduleName="+"'"+moduleName+"' ORDER BY e.menuId"); 
		                                                                                                                                                                                
		List list= getJpaTemplate().find("SELECT e FROM ModuleMenuMasterEntity e, UserMasterRoleEntity ue , UserRoleEntity re, UserRoleAndRightsEntity rre WHERE ue.userId=re.userId AND ue.userLoginId="+"'"+loginUserName+"' AND re.roleId=rre.roleId AND e.menuId=rre.menuId AND rre.visibleFlag=1 AND e.moduleName="+"'"+moduleName+"' AND e.activeStatus=1 AND ue.deletedFlag=0 ORDER BY e.menuSeqNo");
		return list;
		//return getJpaTemplate().find("FROM ModuleMenuMasterEntity e where e.activeStatus=1 AND e.moduleName="+"'"+moduleName+"' ORDER BY e.menuId");
	    }

	@Override
	public List<ModuleMenuMasterEntity> findModuleMasterBySubMenuName(
			String moduleName, String loginUserName) {
		List list= getJpaTemplate().find("SELECT e FROM ModuleMenuMasterEntity e, UserMasterRoleEntity ue , UserRoleEntity re, UserRoleAndRightsEntity rre WHERE ue.userId=re.userId AND ue.userLoginId="+"'"+loginUserName+"' AND re.roleId=rre.roleId AND e.menuId=rre.menuId AND rre.visibleFlag=1 AND e.moduleName="+"'"+moduleName+"' AND e.activeStatus=1 AND ue.deletedFlag=0 ORDER BY e.menuSeqNo");
		return list;
	}
	
	@Override
	public ModuleMenuMasterEntity read(Integer id) {
		
		if (id == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
        return getJpaTemplate().find(ModuleMenuMasterEntity.class, id);
	}



	@Override
	public List<ModuleMenuMasterEntity> getReportLinkList(String loginUserName) {
		
		List list= getJpaTemplate().find("SELECT e FROM ModuleMenuMasterEntity e, UserMasterRoleEntity ue , UserRoleEntity re, UserRoleAndRightsEntity rre WHERE ue.userId=re.userId AND ue.userLoginId="+"'"+loginUserName+"' AND re.roleId=rre.roleId AND e.menuId=rre.menuId AND rre.visibleFlag=1 AND e.moduleName='Reports' AND e.activeStatus=1 AND ue.deletedFlag=0 ORDER BY e.menuSeqNo DESC");
		return list;
	}



	@Override
	public List<ModuleMenuMasterEntity> search(String loginUserName,
			String menuName, String subMenuName, String description) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("SELECT e FROM ModuleMenuMasterEntity e, UserMasterRoleEntity ue , UserRoleEntity re, UserRoleAndRightsEntity rre ");		
		boolean first = true;
		
		if (menuName!=null) {
			
			hql.append(first ? " where " : " and ");
		    hql.append("e.menuName like :menuName");
		    params.put("menuName", "%"+menuName+"%");
		    first = false;
		}
if (subMenuName!=null) {
			
			hql.append(first ? " where " : " and ");
		    hql.append("e.subMenuName like :subMenuName");
		    params.put("subMenuName", "%"+subMenuName+"%");
		    first = false;
		}if (description!=null) {
			
			hql.append(first ? " where " : " and ");
		    hql.append("e.description like :description");
		    params.put("description", "%"+description+"%");
		    first = false;
		}
		
logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
hql.append(first ? " where " : " and ");
hql.append(" ue.userId=re.userId AND ue.userLoginId="+"'"+loginUserName+"' AND re.roleId=rre.roleId AND e.menuId=rre.menuId AND rre.visibleFlag=1 AND e.moduleName='Reports' AND e.activeStatus=1 AND ue.deletedFlag=0 ORDER BY e.menuSeqNo DESC");
first = false;
logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
List l= getJpaTemplate().findByNamedParams(hql.toString(), params);
System.out.println("LIST SIZE IN STORAGE ::::::::::::" +l.size());
return l;
	}
	
}
