package com.advanz.erp.masters.storage.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.erp.masters.entity.jpa.BatchEntity;
import com.advanz.erp.masters.entity.jpa.BranchEntity;
import com.advanz.erp.masters.storage.IStorageBatchDAO;

@Component
public class StorageBatchDAO extends JpaDaoSupport implements IStorageBatchDAO{

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(BatchEntity entity) {
		
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public BatchEntity read(Integer uid) {
        if (uid == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
     //   return getJpaTemplate().find(BatchEntity.class, uid);
return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public BatchEntity update(BatchEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Cannot update a null entity");
        }
        
     //   System.out.println("BATCH UPDATE METHOD IS CALLING NNNNNNNNNNNNNNNNNNNN" + entity.getItem().getItemId() + "SNO IS ::::::::::::::::::" + entity.getSno());
        entity.setDeletedFlag(false);
        return getJpaTemplate().merge(entity);
	}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(BatchEntity entity) {
		  if (entity == null) {
	            throw new IllegalArgumentException("Cannot update a null entity");
	        }
		//  System.out.println("BATCH DELETED IS CALLING :::::::::::::::::::::::::");
			//entity=getJpaTemplate().find(BatchEntity.class, entity.getSno());
		    entity.setDeletedFlag(true);
	       getJpaTemplate().merge(entity);
	}
	@Override
    @Transactional(readOnly = true)
	public List<BatchEntity> load() {		
        return getJpaTemplate().find("FROM BatchEntity e where e.deletedFlag=?1 and activeStatus=1 ORDER BY e.batchNo",false);
	//	return getJpaTemplate().find("FROM BatchEntity e where e.deletedFlag=?1 ORDER BY e.modifiedDate DESC",false);

	}

	@Override
	@Transactional(readOnly = true)
	public<E> List<BatchEntity> findByBatchNo(String batchNo) {	
	//	logger.info("Find by batch no="+batchNo);
		return getJpaTemplate().find("FROM BatchEntity e WHERE e.batchNo like ?1 and e.deletedFlag=?2 ORDER BY e.batchNo",batchNo+"%",false);
	}

	@Override
	public List<BatchEntity> getLastByItemId(Integer itemId) {
				//return getJpaTemplate().find("FROM BatchEntity e WHERE e.item.uid = ?1 and e.deletedFlag=?2 ORDER BY e.modifiedDate DESC LIMIT 1",itemId,false);
		return getJpaTemplate().find("FROM BatchEntity e WHERE e.item.itemId = ?1 and e.deletedFlag=?2 ORDER BY e.modifiedDate DESC",itemId,false);
	}
	@Override
	public List<BatchEntity> findBySno(Integer sno) {
				//return getJpaTemplate().find("FROM BatchEntity e WHERE e.item.uid = ?1 and e.deletedFlag=?2 ORDER BY e.modifiedDate DESC LIMIT 1",itemId,false);
		return getJpaTemplate().find("FROM BatchEntity e WHERE e.sno = ?1 and e.deletedFlag=?2 ORDER BY e.modifiedDate DESC",sno,false);
	}
	@Override
	@Transactional(readOnly = true)
	public List<BatchEntity> search(String batchNo, String itemName, String itemCategory) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM BatchEntity e");		
		hql.append(" WHERE e.deletedFlag=:flag");
	    params.put("flag",false);
		
		if (StringUtils.hasText(batchNo)) {
		    hql.append(" and ");
		    hql.append("e.batchNo like :batch");
		    params.put("batch", "%"+batchNo.trim()+"%");
		  
		}
	//	logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		if (StringUtils.hasText(itemName)) {
		    hql.append(" and ");
		    hql.append("e.item.itemName like :iname");
		    params.put("iname", "%"+itemName.trim()+"%");
		  	}		
	//	logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		
		if (StringUtils.hasText(itemCategory)) {
		    hql.append(" and ");
		    hql.append("e.item.itemCategoryEntity.itemCategoryName like :icat");
		    params.put("icat", "%"+itemCategory.trim()+"%");
		  	}		
//		logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		
		hql.append(" ORDER BY item.itemName,batchNo");
	//	logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	public boolean isInUsed(String batchNo) {
	//	logger.info(" BatchNo="+ batchNo);
		List<Integer> list=getJpaTemplate().find("select batchNo from FinishedGoodsDetailEntity e where e.deletedFlag=0 and e.batchNo=?1",batchNo);
	//	logger.info("List:"+list);
		if(list!=null && list.size()>0){
			return true;
		}
		list=getJpaTemplate().find("Select batchNo FROM BillDetailEntity e WHERE e.deletedFlag=0 and e. batchNo =?1",batchNo);
	//	logger.info("List:"+list);
		if(list!=null && list.size()>0){
			return true;
		}
		list=getJpaTemplate().find("Select batchNo FROM DispatchDetailEntity e WHERE e.deletedFlag=0 and e.batchNo=?1",batchNo);
	//	logger.info("List:"+list);
		if(list!=null && list.size()>0){
			return true;
		}
		list=getJpaTemplate().find("Select batchNo FROM StockLedgerEntity e WHERE e.deletedFlag=0 and e.batchNo=?1",batchNo);
	//	logger.info("List:"+list);
		if(list!=null && list.size()>0){
			return true;
		}
		return false;
	}

	@Override
	public <E> List<BatchEntity> findById(E id) {
		return getJpaTemplate().find("FROM BatchEntity e WHERE e.batchNo= ?1 and e.deletedFlag=?2 ",id,false);
	}

	
}
