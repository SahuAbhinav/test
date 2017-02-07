package com.advanz.erp.masters.storage.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.erp.masters.entity.jpa.ItemEntity;
import com.advanz.erp.masters.entity.jpa.MastersEntity;
import com.advanz.erp.masters.storage.IStorageItemDAO;

@Component
public class StorageItemDAO extends JpaDaoSupport implements IStorageItemDAO{

	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(ItemEntity entity) {
		
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        entity.setDeletedFlag(false);
        getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public ItemEntity read(Integer itemId) {
        if (itemId == null) {
            throw new IllegalArgumentException("itemId must not be null");
        }
        return getJpaTemplate().find(ItemEntity.class, itemId);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ItemEntity update(ItemEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Cannot update a null entity");
        }
        entity.setDeletedFlag(false);
        return getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(ItemEntity entity1) {
		if (entity1 == null) {
            throw new IllegalArgumentException("Cannot remove a null entity");
        }
		ItemEntity entity=getJpaTemplate().find(ItemEntity.class, entity1.getItemId());	
	  entity.setDeletedFlag(true);
	  entity.setModifiedUserId(entity1.getModifiedUserId());
      getJpaTemplate().merge(entity);
		
	}
	
	@Override
    @Transactional(readOnly = true)
	public List<ItemEntity> load() {
    return getJpaTemplate().find("FROM ItemEntity e where e.deletedFlag=0 ORDER BY e.itemName ");
	
	}

	@Override
	@Transactional(readOnly = true)
	public List<ItemEntity> findById(Integer itemId) {
		
	 return getJpaTemplate().find("FROM ItemEntity e WHERE e.itemId = "+ itemId +" and e.deletedFlag=0 ORDER BY e.itemName");
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ItemEntity> search(String itemName,String invoiceName,String itemCode,String category,String itemGroup , Integer groupId,Integer activeStatus,Double excisePerc,Integer gradeId) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM ItemEntity e");
		boolean first = true;
		if(activeStatus==null){
			activeStatus=1;
		}
		if(activeStatus==2){
			activeStatus=null;
		}/*else{
			activeStatus=null;
		}*/
		if (StringUtils.hasText(itemName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.itemName like :itemName");
		    params.put("itemName", "%"+ itemName.trim()+"%");
		    first=false;
		}
		if (StringUtils.hasText(invoiceName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.invoiceName like :invoiceName");
		    params.put("invoiceName", "%"+invoiceName.trim()+"%");
		    first=false;
		}
		
		if (StringUtils.hasText(itemCode)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.itemCode like :itemCode");
		    params.put("itemCode", "%"+itemCode.trim()+"%");
		    first=false;
		}
		if (StringUtils.hasText(category)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.itemCategoryEntity.itemCategoryName like :category");
		    params.put("category", "%"+category.trim()+"%");
		    first=false;
		}
		if (StringUtils.hasText(itemGroup)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.itemCategoryEntity.itemGroupEntity.itemGroupName like :itemGroup");
		    params.put("itemGroup", "%"+itemGroup.trim()+"%");
		    first=false;
		}
		
		if (groupId!=null && groupId.intValue()!=0) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.itemCategoryEntity.itemGroupEntity.itemGroupFlagId = "+groupId.intValue());
		    first=false;
		}
		if (gradeId!=null && gradeId.intValue()!=0) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.masterGradeEntity.mastersId = "+gradeId.intValue());
		    first=false;
		}
		
		if (activeStatus!=null) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.activeStatus = "+activeStatus);
		    first=false;
		}
		if (excisePerc!=null && excisePerc >0) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.excisePerc = "+excisePerc);
		    first=false;
		}
		
		hql.append(first ? " where " : " and ");
	    hql.append(" e.deletedFlag=0 ORDER BY e.itemName");
	   
	    first=false;
		
		
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}


	@Override
    @Transactional(readOnly = true)
	public List<ItemEntity> loadFinishedGoodItems(String invoiceName,
			String itemCode) {
		
		//        return getJpaTemplate().find("FROM ItemEntity e where e.itemCategoryEntity.itemGroupEntity.itemGroupFlagId=3 and e.deletedFlag=0 ORDER BY e.modifiedDate DESC");
    	Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM ItemEntity e where e.itemCategoryEntity.itemGroupEntity.itemGroupFlagId=3 and e.deletedFlag=0 and e.activeStatus=1");
	
		if (StringUtils.hasText(invoiceName)) {
		    hql.append(" and ");
		    hql.append("e.itemName like :itemName");
		    params.put("itemName", "%"+invoiceName.trim()+"%");
		   
		}
		
		if (StringUtils.hasText(itemCode)) {
		    hql.append(" and ");
		    hql.append("e.itemCode like :itemCode");
		    params.put("itemCode", "%"+itemCode.trim()+"%");
		    
		}
		
		hql.append(" ORDER BY e.itemName");
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	public List<ItemEntity> finishGoodItemList() {
		StringBuffer hql = new StringBuffer("FROM  ItemEntity e WHERE e.itemCategoryEntity.itemGroupEntity.itemGroupFlagId=3 AND e.deletedFlag=0 and e.activeStatus=1 ORDER BY e.itemName");
		return getJpaTemplate().find(hql.toString());
	}
	
	@Override
	public List<ItemEntity> finishGoodItemListWithPagination(Integer next, Integer itemGroupFlagId) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		if(itemGroupFlagId==null )
			itemGroupFlagId=3;
		Query query=entityManager.createQuery("FROM  ItemEntity e WHERE e.itemCategoryEntity.itemGroupEntity.itemGroupFlagId="+itemGroupFlagId+" AND e.deletedFlag=0 and e.activeStatus=1 ");
		query.setFirstResult(next);
		query.setMaxResults(13);
		List<ItemEntity> list = query.getResultList();
		entityManager.close();
		return list;
	}

	@Override
	public List<ItemEntity> finishGoodItemListWithPaginationAndExcisePerc(
			Integer next, Double excisePerc) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		Query query=entityManager.createQuery("FROM  ItemEntity e WHERE e.itemCategoryEntity.itemGroupEntity.itemGroupFlagId=3 AND e.excisePerc ='"+excisePerc+"' AND e.deletedFlag=0 and e.activeStatus=1 ");
		query.setFirstResult(next);
		query.setMaxResults(13);
		List<ItemEntity> list = query.getResultList();
		entityManager.close();
		return list;
	}
	
	
	
	@Override
	public List<ItemEntity> loadItemsForBatch() {
//		return getJpaTemplate().find("FROM ItemEntity e where e.deletedFlag=0 and e.batchFlag=1 and e.actioveStatus=1 ORDER BY e.itemName ");
		List<ItemEntity> il= getJpaTemplate().find("SELECT new ItemEntity(e.itemId,e.itemName) FROM ItemEntity e where e.deletedFlag=0 and e.batchFlag=1 and e.activeStatus=1 ORDER BY e.itemName ");
		
		return il;

		
	}
	
			@Override
			@Transactional(readOnly = true)
			public List<ItemEntity> searchByItemGroupNameList(String invoiceName,String itemCode, Integer groupId,Integer activeStatus) {
				Map<String, Object> params = new HashMap<String, Object>();
				StringBuffer hql = new StringBuffer("FROM ItemEntity e");
				boolean first = true;
				
			
				if (StringUtils.hasText(invoiceName)) {
				    hql.append(first ? " where " : " and ");
				    hql.append("e.itemName like :itemName");
				    params.put("itemName", "%"+invoiceName.trim()+"%");
				    first=false;
				}
				
				if (StringUtils.hasText(itemCode)) {
				    hql.append(first ? " where " : " and ");
				    hql.append("e.itemCode like :itemCode");
				    params.put("itemCode", "%"+itemCode.trim()+"%");
				    first=false;
				}
				

				if (groupId!=null && groupId.intValue()!=0) {
				    hql.append(first ? " where " : " and ");
				    hql.append("e.itemCategoryEntity.itemGroupEntity.itemGroupFlagId = "+groupId.intValue());
				    first=false;
				}
				
				if (activeStatus!=null) {
				    hql.append(first ? " where " : " and ");
				    hql.append("e.activeStatus = "+activeStatus);
				    first=false;
				}
				
				hql.append(first ? " where " : " and ");
			    hql.append(" e.deletedFlag=0 and e.activeStatus=1 ORDER BY e.itemName");
			   
			    first=false;
				
				
				return getJpaTemplate().findByNamedParams(hql.toString(), params);
			}

			@Override
			public List<ItemEntity> findItemByGroupName(String groupName) {
				StringBuffer hql = new StringBuffer("SELECT new ItemEntity(e.itemId,e.itemName)  FROM  ItemEntity e WHERE e.itemCategoryEntity.itemGroupEntity.itemGroupName='"+groupName+"' and e.activeStatus=1 AND e.deletedFlag=0 ORDER BY e.itemName");
				return getJpaTemplate().find(hql.toString());
			}

			@Override
			public List<ItemEntity> getItemIdAndItemNameList() {
				List<ItemEntity> il= getJpaTemplate().find("SELECT new ItemEntity(e.itemId,e.itemName) FROM ItemEntity e where e.deletedFlag=0  and e.activeStatus=1 ORDER BY e.itemName ");
				
				return il;
			}
		

			@Override
		    @Transactional(readOnly = true)
			public List<ItemEntity> loadItemPagination(Integer index) {
				EntityManager entityManager=entityManagerFactory.createEntityManager();
				Query query=entityManager.createQuery("FROM ItemEntity e where e.deletedFlag=0 ORDER BY e.itemName");
				query.setFirstResult(index);
				query.setMaxResults(15);
				
				List<ItemEntity> list = query.getResultList();
				entityManager.close();
				return list;
				
			}
			
			@Override
		    @Transactional(readOnly = true)
			public List<ItemEntity> getListByItemGroupName(Integer itemGroupFlageId,Integer activeStatus,Integer index){
				EntityManager entityManager=entityManagerFactory.createEntityManager();
				Query query=entityManager.createQuery("FROM ItemEntity e where e.deletedFlag=0 and e.activeStatus=1 and e.itemCategoryEntity.itemGroupEntity.itemGroupFlagId="+itemGroupFlageId+" ORDER BY e.itemName");
				query.setFirstResult(index);
				query.setMaxResults(13);
				
				List<ItemEntity> list = query.getResultList();
				entityManager.close();
				return list;
			}
			
			
			@Override
			@Transactional(readOnly = true)
			public List<ItemEntity> getItemByName(String itemName) {
				
			 return getJpaTemplate().find("FROM ItemEntity e WHERE e.deletedFlag=0  and e.itemName = '"+ itemName+"'");
			}
			@Override
			@Transactional(readOnly = true)
			public List<ItemEntity> getItemByCode(String itemCode) {
				
			 return getJpaTemplate().find("FROM ItemEntity e WHERE e.deletedFlag=0  and e.itemCode=?1", itemCode);
			}
			
			
			@Override
		    @Transactional(readOnly = true)
			public List<ItemEntity> getListByItemGroupFlagId(Integer itemGroupFlageId){
				EntityManager entityManager=entityManagerFactory.createEntityManager();
				Query query=entityManager.createQuery("FROM ItemEntity e where e.deletedFlag=0 and e.activeStatus=1 and e.itemCategoryEntity.itemGroupEntity.itemGroupFlagId="+itemGroupFlageId+" ORDER BY e.itemName");
				List<ItemEntity> list = query.getResultList();
				entityManager.close();
				return list;
			}
			
	
			@Override
			@Transactional(readOnly = true)
			public Integer itemGroupFlagId(String itemCode) {
			List list= getJpaTemplate().find("SELECT e.itemCategoryEntity.itemGroupEntity.itemGroupFlagId FROM ItemEntity e WHERE e.deletedFlag=0 and e.activeStatus=1 and e.itemCode=?1",itemCode);
			Integer flagName=(Integer) list.get(0);
			return flagName;
		}

			@Override
			public List<ItemEntity> getListByItemCategory(Integer itemCategory) {
				EntityManager entityManager=entityManagerFactory.createEntityManager();
				Query query=entityManager.createQuery("FROM ItemEntity e where e.deletedFlag=0 and e.activeStatus=1 and e.itemCategoryEntity.itemCategoryId="+itemCategory+" ORDER BY e.itemName");
				List<ItemEntity> list = query.getResultList();
				entityManager.close();
				return list;
			}
			@Override
			//@Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT)
			@Transactional(readOnly = true)
			public List getItemIdAndDencity(Integer gradeId,
					Double lenght, Double width, Double thickness, Double weight) {
				EntityManager entityManager=entityManagerFactory.createEntityManager();
				/*EntityManager entityManager=entityManagerFactory.createEntityManager();
				entityManager.getTransaction().begin();
				Query query=entityManager.createQuery("SELECT item_id,item_density FROM m_item WHERE item_grade_id='"+gradeId+"' AND item_length='"+lenght+"' AND minimum_weight<='"+weight+"' AND maximum_weight>='"+weight+"' AND item_thikness='"+thickness+"' AND item_width='"+width+"'  AND deleted_flag='0' AND active_status='1'");
				List list = query.getResultList();
				entityManager.getTransaction().commit();
				entityManager.close();
				return list;*/
				Query query=entityManager.createQuery("SELECT e.itemId,e.itemDensity,e.itemName FROM ItemEntity e WHERE e.deletedFlag=0 and e.activeStatus=1 and e.masterGradeEntity.mastersId="+gradeId+" and e.itemLength="+lenght+" and e.itemWidth="+width+" and e.itemHeight="+thickness+" and e.minimumWeight<="+weight+" and e.maximumWeight>="+weight+" ");
				List<ItemEntity> list = query.getResultList();
				entityManager.close();
				
				/*List list= getJpaTemplate().find("SELECT e.itemId,e.itemDensity FROM ItemEntity e WHERE e.deletedFlag=0 and e.activeStatus=1 and e.masterUnitEntity.mastersId='"+gradeId+"' and e.itemLength='"+lenght+"' and e.itemWidth='"+width+"' and e.itemHeight='"+thickness+"' and e.minimumWeight<='"+weight+"' and e.maximumWeight>='"+weight+"'");
				System.out.println("LIST SIZE IN ITEM DOA :::::::::" +list.size());*/
				return list;
			 }

			@Override
			public List<ItemEntity> getItemNameAndId(Integer itemId,
					String operation) {
				List<ItemEntity> il=null;
				if("FindByGroup".equals(operation)){
					   il= getJpaTemplate().find("SELECT new ItemEntity(e.itemId,e.itemName,e.itemCode) FROM ItemEntity e where e.deletedFlag=0 and e.itemCategoryEntity.itemGroupEntity.itemGroupFlagId=3  and e.activeStatus=1 ORDER BY e.itemName ");
				}else {
                       il= getJpaTemplate().find("SELECT new ItemEntity(e.itemId,e.itemName,e.itemCode) FROM ItemEntity e where e.deletedFlag=0 and e.itemId='"+itemId+"'  and e.activeStatus=1 ORDER BY e.itemName ");
				}	return il;
			}
			
	}
