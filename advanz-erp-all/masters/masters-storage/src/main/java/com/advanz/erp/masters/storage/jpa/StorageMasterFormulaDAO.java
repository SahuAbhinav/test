package com.advanz.erp.masters.storage.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.erp.masters.entity.jpa.MasterFormulaDetailEntity;
import com.advanz.erp.masters.entity.jpa.MasterFormulaMasterEntity;
import com.advanz.erp.masters.storage.IStorageMasterFormulaDAO;
@Component
public class StorageMasterFormulaDAO extends JpaDaoSupport implements IStorageMasterFormulaDAO
{

	@Override
	
	public List<MasterFormulaMasterEntity> load() {
		 List<MasterFormulaMasterEntity> list = getJpaTemplate().find("From MasterFormulaMasterEntity e where e.deletedFlag=0 ORDER BY e.modifiedDate");
		 return list;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(MasterFormulaMasterEntity entity) {
		
		 if (entity == null) {
	            throw new IllegalArgumentException("Cannot create a null entity");
	        }
		getJpaTemplate().persist(entity);
	}

	@Override
	public MasterFormulaMasterEntity read(Integer id) {
		
		 if (id == null) {
	            throw new IllegalArgumentException("uid must not be null");
	        }
		 return getJpaTemplate().find(MasterFormulaMasterEntity.class, id);
		 
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public MasterFormulaMasterEntity update(MasterFormulaMasterEntity entity) {
		 if (entity == null) {
	            throw new IllegalArgumentException("Cannot create a null entity");
	        }
		 //entity=getJpaTemplate().find(MasterFormulaMasterEntity.class,entity.getMasterFormulaAutoId());
		 return getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(MasterFormulaMasterEntity entity1) {
		if (entity1 == null) {
			throw new IllegalArgumentException("Cannot create a null entity");
			}

			MasterFormulaMasterEntity entity=getJpaTemplate().find(MasterFormulaMasterEntity.class,entity1.getMasterFormulaAutoId());
			entity.setDeletedFlag(true);

			List<MasterFormulaDetailEntity> detailEntities=entity.getMasterFormulaDetailEntity();
			for(MasterFormulaDetailEntity e:detailEntities){
			e.setDeletedFlag(true);
			e.setModifiedUserId(entity1.getModifiedUserId());
			}

			entity.setModifiedUserId(entity1.getModifiedUserId());
			getJpaTemplate().merge(entity);

	}

	
	@Override
	@Transactional(readOnly = true)
	 public List<MasterFormulaMasterEntity> search(String itemName, String itemCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		
	    StringBuffer hql = new StringBuffer("FROM MasterFormulaMasterEntity e");
		boolean first = true;
		
		if (StringUtils.hasText(itemName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.itemEntity.itemName like :itemName");
		    params.put("itemName", "%"+itemName.trim()+"%");
		    first=false;
		}	
		//logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		if (StringUtils.hasText(itemCode)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.itemEntity.itemCode like :itemCode");
		    params.put("itemCode", "%"+itemCode.trim()+"%");
		    first=false;
		}
		
		/*
		if (modifiedDate!=null) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.modifiedDate =:modifiedDate");
		    params.put("modifiedDate",modifiedDate);
		    first=false;
		}
		 */		//logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		hql.append(first ? " where " : " and ");
		hql.append("e.deletedFlag=:flag");
		params.put("flag", false);
	//	logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<MasterFormulaMasterEntity> checkDuplicateEntry(Integer finishedItemId)
	{
		List<MasterFormulaMasterEntity> list=getJpaTemplate().find("FROM MasterFormulaMasterEntity e where  e.itemEntity.itemId='"+finishedItemId+"'and e.activeStatus=1 and e.deletedFlag=0 ORDER BY e.masterFormulaAutoId");
		return list;
	}

}
