package com.advanz.erp.masters.storage.jpa;

import java.util.List;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.advanz.erp.masters.entity.jpa.ExciseLedgerEntity;
import com.advanz.erp.masters.entity.jpa.UserMasterRoleEntity;
import com.advanz.erp.masters.storage.IStorageExciseLedgerDAO;

public class StorageExciseLedgerDAO extends JpaDaoSupport implements IStorageExciseLedgerDAO
{

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void create(ExciseLedgerEntity entity) {
		
		if(entity==null)
		{
			 throw new IllegalArgumentException("Cannot Create a null entity");
		}
		getJpaTemplate().persist(entity);
	}

	@Override
	public ExciseLedgerEntity read(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public ExciseLedgerEntity update(ExciseLedgerEntity entity) {
		if(entity==null)
		{
			 throw new IllegalArgumentException("Cannot Update a null entity");
		}
		return getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(ExciseLedgerEntity entity) {
		if(entity==null)
		{
			 throw new IllegalArgumentException("Cannot delete a null entity");
		}
		  entity=getJpaTemplate().find(ExciseLedgerEntity.class, entity.getSno());	
		  entity.setDeletedFlag(true);
	      getJpaTemplate().merge(entity);				
	}

	@Override
	@Transactional(readOnly=true)
	public List<ExciseLedgerEntity> load() {		
		return null;
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public List<ExciseLedgerEntity> findLastUserMasterId() {	
		List<ExciseLedgerEntity> list=getJpaTemplate().find("FROM ExciseLedgerEntity e where e.deletedFlag=0 ORDER BY e.sno DESC LIMIT 1");
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ExciseLedgerEntity> findSnoByGrnNumber(String grnNumber)
	{
	List<ExciseLedgerEntity> list=getJpaTemplate().find("FROM ExciseLedgerEntity  e WHERE e.grnNumber=?1 and e.deletedFlag=?2", grnNumber,false);
	return list;
	}

	@Override
	public List<ExciseLedgerEntity> findSnoByIssueNumber(String issueNumber) {
		List<ExciseLedgerEntity> list=getJpaTemplate().find("FROM ExciseLedgerEntity  e WHERE e.issueNumber=?1 and e.deletedFlag=?2", issueNumber,false);
		return list;	
	}

	@Override
	public List<ExciseLedgerEntity> findSnoByInvoiceNumber(String invoiceNumber) {
		List<ExciseLedgerEntity> list=getJpaTemplate().find("FROM ExciseLedgerEntity  e WHERE e.invoiceNumber=?1 and e.deletedFlag=?2", invoiceNumber,false);
		return list;
	}
	
	@Override
	public List<ExciseLedgerEntity> findExciseByItemId(Integer itemId)
	{
		return getJpaTemplate().find("From ExciseLedgerEntity e WHERE e.itemId=?1 and deletedFlag=?2",itemId,false);
	}
	
}
