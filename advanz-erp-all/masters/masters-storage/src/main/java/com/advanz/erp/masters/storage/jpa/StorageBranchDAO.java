package com.advanz.erp.masters.storage.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.erp.masters.entity.jpa.BranchEntity;
import com.advanz.erp.masters.entity.jpa.ItemCategoryEntity;
import com.advanz.erp.masters.storage.IStorageBranchDAO;

@Component
public class StorageBranchDAO extends JpaDaoSupport implements IStorageBranchDAO{
	private static final Logger logger = LoggerFactory
			.getLogger(StorageBranchDAO.class);
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(BranchEntity entity) {
		
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public BranchEntity read(Integer branchId) {
        if (branchId == null) {
            throw new IllegalArgumentException("branchId must not be null");
        }
        return getJpaTemplate().find(BranchEntity.class, branchId);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public BranchEntity update(BranchEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Cannot update a null entity");
        }
        return getJpaTemplate().merge(entity);
	}



	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(BranchEntity entity1) {
		 if (entity1 == null) {
	            throw new IllegalArgumentException("Cannot delete a null entity");
	     }
		 BranchEntity entity= getJpaTemplate().find(BranchEntity.class, entity1.getBranchId());
		 entity.setDeletedFlag(true);
		 entity.setModifiedUserId(entity1.getModifiedUserId());
	     getJpaTemplate().merge(entity);		
	}

	@Override
    @Transactional(readOnly = true)
	public List<BranchEntity> load() {
		
        return getJpaTemplate().find("FROM BranchEntity e WHERE e.deletedFlag = ?1 ORDER BY e.branch",false);
	}

	@Override
    @Transactional(readOnly = true)
	public List preloaded(){
        return getJpaTemplate().find("Select e.branchId,e.branch FROM BranchEntity e WHERE e.deletedFlag = ?1 ORDER BY e.branch",false);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<BranchEntity> findById(Integer uid) {
		System.out.println("uid"+uid);
		return getJpaTemplate().find("FROM BranchEntity e WHERE e.branchId = ?1 and e.deletedFlag = ?2 ORDER BY e.branch", uid,false);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<BranchEntity> findByName(String branch) {
		
		return getJpaTemplate().find("FROM BranchEntity e WHERE e.branch = ?1 and deletedFlag=?2 ORDER BY e.branch", branch.trim(),false);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<BranchEntity> findByCode(String invoiceCode) {
		
		return getJpaTemplate().find("FROM BranchEntity e WHERE e.invoiceCode=?1 and deletedFlag=?2 ORDER BY e.branch", invoiceCode.trim(),false);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<BranchEntity> search(String branchName, String invoice) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM BranchEntity e");
		boolean first = true;
		
		if (StringUtils.hasText(branchName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.branch like :branch");
		    params.put("branch", "%"+branchName.trim()+"%");
		    first=false;
		}	
		if (StringUtils.hasText(invoice)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.invoiceCode like :invoice");
		    params.put("invoice", "%"+invoice.trim()+"%");
		    first=false;
		}
		hql.append(first ? " where " : " and ");
		hql.append("e.deletedFlag=:flag");
		params.put("flag", false);
		
		
		hql.append(" ORDER BY e.branch");
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}
	@Override
	public boolean isInUsed(Integer id) {		
		List<Integer> list=getJpaTemplate().find("Select salesOrderAutoId FROM SalesOrderMasterEntity e WHERE e.deletedFlag=0 and e.branchEntity.branchId ="+id);
		if(list!=null && list.size()>0){
			return true;
		}	
		list=getJpaTemplate().find("Select quotationAutoId FROM QuotationMasterEntity e WHERE e.deletedFlag=0 and e.branchEntity.branchId ="+id);
		if(list!=null && list.size()>0){
			return true;
		}
		list=getJpaTemplate().find("Select invoiceAutoId FROM BillEntity e WHERE e.deletedFlag=0 and e.branchEntity.branchId ="+id);
		if(list!=null && list.size()>0){
			return true;
		}
		list=getJpaTemplate().find("Select dispatchAutoId FROM DispatchMasterEntity e WHERE e.deletedFlag=0 and e.branchId ="+id);
		if(list!=null && list.size()>0){
			return true;
		}
		list=getJpaTemplate().find("Select poAutoId FROM PurchaseOrderMasterEntity e WHERE e.deletedFlag=0 and e.branchEntity.branchId ="+id);
		if(list!=null && list.size()>0){
			return true;
		}
		list=getJpaTemplate().find("Select grnAutoId FROM GrnMasterEntity e WHERE e.deletedFlag=0 and e.branchEntity.branchId ="+id);
		if(list!=null && list.size()>0){
			return true;
		}
		return false;
	}
}
