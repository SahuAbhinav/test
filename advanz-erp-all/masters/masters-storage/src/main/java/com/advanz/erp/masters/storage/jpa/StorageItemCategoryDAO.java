package com.advanz.erp.masters.storage.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.erp.masters.entity.jpa.BranchEntity;
import com.advanz.erp.masters.entity.jpa.ItemCategoryEntity;
import com.advanz.erp.masters.storage.IStorageItemCategoryDAO;

@Component
public class StorageItemCategoryDAO extends JpaDaoSupport implements IStorageItemCategoryDAO{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(ItemCategoryEntity entity) {
		
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public ItemCategoryEntity read(Integer uid) {
        if (uid == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
        return getJpaTemplate().find(ItemCategoryEntity.class, uid);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ItemCategoryEntity update(ItemCategoryEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Cannot update a null entity");
        }
        return getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void  delete(ItemCategoryEntity entity1) {
		 if (entity1 == null) {
	            throw new IllegalArgumentException("Cannot update a null entity");
	        }
		 ItemCategoryEntity entity=getJpaTemplate().find(ItemCategoryEntity.class, entity1.getItemCategoryId());
		 	entity.setDeletedFlag(true);
		 	entity.setModifiedUserId(entity1.getModifiedUserId());
	       getJpaTemplate().merge(entity);
		
	}
	
	@Override
    @Transactional(readOnly = true)
	public List<ItemCategoryEntity> load() {
		
        return getJpaTemplate().find("FROM ItemCategoryEntity e where deletedFlag=?1 ORDER BY e.itemCategoryName",false);
	}



	@Override
	@Transactional(readOnly = true)
	public List<ItemCategoryEntity> findById(Integer uid) {
		
		return getJpaTemplate().find("FROM ItemCategoryEntity e WHERE e.itemCategoryId = ?1 and deletedFlag=?2 ORDER BY e.itemCategoryName", uid,false);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ItemCategoryEntity> findByNameAndCode(String name,String code) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM ItemCategoryEntity e");
		boolean first = true;
		
		if (StringUtils.hasText(name)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.itemCategoryName like :name");
		    params.put("name", "%"+name.trim()+"%");
		    first=false;
		}
		if (StringUtils.hasText(code)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.itemCategoryCode like :code");
		    params.put("code", "%"+code.trim()+"%");
		    first=false;
		}
		
		    hql.append(first ? " where " : " and ");
		    hql.append("e.deletedFlag=:flag");
		    params.put("flag", false);
		    first=false;
	
		
		return getJpaTemplate().findByNamedParams(hql.toString(), params);	
		
	}
	@Override
	@Transactional(readOnly = true)
	public List<ItemCategoryEntity> search(Integer id, String code, String name) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM ItemCategoryEntity e");
		boolean first = true;
		
		if (StringUtils.hasText(name)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.itemCategoryName like :name");
		    params.put("name", "%"+name.trim()+"%");
		    first=false;
		}
		if (StringUtils.hasText(code)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.itemCategoryCode like :code");
		    params.put("code", "%"+code.trim()+"%");
		    first=false;
		}
		if (id!=null && id!=0) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.itemCategoryId=:id");
		    params.put("id", "%"+id);
		    first=false;
		}
		
		    hql.append(first ? " where " : " and ");
		    hql.append("e.deletedFlag=:flag");
		    params.put("flag", false);
		    first=false;
	
		hql.append(" ORDER BY e.itemCategoryName");
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ItemCategoryEntity> findByGroupId(Integer uid) {
		
		return getJpaTemplate().find("FROM ItemCategoryEntity e WHERE e.itemGroupEntity.itemGroupId = ?1 and deletedFlag=?2 ORDER BY e.itemCategoryName", uid,false);
	}
	
	@Override
	public boolean isInUsed(Integer id) {
		logger.info("id="+id);
			List<Integer> list=getJpaTemplate().find("select itemId from ItemEntity e where e.deletedFlag=0 and e.itemCategoryEntity.itemCategoryId=?1",id);
			logger.info("List:"+list);
			if(list!=null && list.size()>0){
				return true;
			}
		return false;
	}	

}
