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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.common.DataUtility;
import com.advanz.erp.masters.entity.jpa.BillEntity;
import com.advanz.erp.masters.entity.jpa.DeletedRecordsLogEntity;
import com.advanz.erp.masters.storage.IStorageBillDAO;

public class StorageBillDAO extends JpaDaoSupport implements IStorageBillDAO{

	
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(BillEntity entity) {
		// TODO Auto-generated method stub
		   if (entity == null) {
	            throw new IllegalArgumentException("Cannot create a null entity");
	        }
	        getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public BillEntity read(Integer id) {
		 if (id == null) {
	            throw new IllegalArgumentException("uid must not be null");
	        }
	        return getJpaTemplate().find(BillEntity.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public BillEntity update(BillEntity entity) {
		// TODO Auto-generated method stub
		 if (entity == null) {
	            throw new IllegalArgumentException("Cannot update a null entity");
	        }
		 entity.setDeletedFlag(false);
	        return getJpaTemplate().merge(entity);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.DEFAULT)
	public void delete(BillEntity entity) {
		
		
	
		
		// TODO Auto-generated method stub
		 if (entity == null) {
	            throw new IllegalArgumentException("Cannot delete a null entity");
	     }
		 
		DeletedRecordsLogEntity deletedRecordsLogEntity = new DeletedRecordsLogEntity(); 
		deletedRecordsLogEntity.setTransactionSeries(entity.getTransactionSeries());
		deletedRecordsLogEntity.setTransactionNumber(entity.getInvoiceNumber());
		deletedRecordsLogEntity.setTransactionDate(entity.getInvoiceDate());
		deletedRecordsLogEntity.setDeleteOnDate(new Date());
		deletedRecordsLogEntity.setDeleteByUserId(entity.getCreatedUserId());
		getJpaTemplate().merge(deletedRecordsLogEntity);
		 entity.setDeletedFlag(true);

		
	   //  entityManager.createQuery("DELETE FROM t_excise_ledger where invoice_number='"+entity.getInvoiceNumber()+"'").executeUpdate();
	     //entityManager.createQuery("DELETE FROM t_stock_ledger where transaction_number='"+entity.getInvoiceNumber()+"'").executeUpdate();
		 //entityManager.createQuery("Delete FROM BillEntity e where e.invoiceNumber ='"+entity.getInvoiceNumber()+"'");
		
		 
		 
		try {
			EntityManager entityManager=entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.createQuery("DELETE FROM ExciseLedgerEntity e WHERE e.invoiceNumber = '"+entity.getInvoiceNumber()+"'").executeUpdate();
			entityManager.getTransaction().commit();
			entityManager.getTransaction().begin();
			entityManager.createQuery("DELETE FROM StockLedgerEntity e WHERE e.transactionNumber = '"+entity.getInvoiceNumber()+"'").executeUpdate();
			entityManager.getTransaction().commit();
			entityManager.getTransaction().begin();
			entityManager.createQuery("DELETE FROM BillEntity e WHERE e.invoiceNumber = '"+entity.getInvoiceNumber()+"'").executeUpdate();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    // getJpaTemplate().merge(entity);		
	     }

	@Override
    @Transactional(readOnly = true)
	public List<BillEntity> load() {
		// TODO Auto-generated method stub
		return getJpaTemplate().find("FROM BillEntity e  where e.deletedFlag=0 ORDER BY e.invoiceAutoId DESC");
		
	}

	@Override
	@Transactional(readOnly = true)
	public <E> List<BillEntity> findById(Integer invoiceAutoId) {
		// TODO Auto-generated method stub
		return getJpaTemplate().find("FROM BillEntity e WHERE e.invoiceAutoId = "+ invoiceAutoId +"ORDER BY e.modifiedDate DESC");
	}

	@Override
	@Transactional(readOnly = true)
	public List<BillEntity> getLastByInvoiceId() {
	// TODO Auto-generated method stub
		return getJpaTemplate().find("FROM BillEntity e   where e.deletedFlag=0 ORDER BY e.invoiceId DESC LIMIT 1");
		}
	@Override
	public <E> List<BillEntity> findByInvoiceSeries(String invoiceSeries) {
		return getJpaTemplate().find("FROM BillEntity e WHERE e.deletedFlag=0 and e.invoiceNumber = "+ "'"+invoiceSeries+"'");
	}

	@Override
	public List<BillEntity> search(Date fromDate,Date toDate, String partyName,String invoiceNumber,String itemName) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM BillEntity e");		
		boolean first = true;
	   
		
		if (fromDate!=null || toDate!=null) {
		    hql.append(first ? " where " : " and ");
		    String fromDate1="";
		    if (fromDate!=null){
		     fromDate1=DataUtility.getDate(fromDate);
		    }
		    String toDate1=null;
		    if (toDate!=null) {
		     toDate1=DataUtility.getDate(toDate);
		    } 
		    
		    hql.append("cast(e.invoiceDate as date) >= '"+ fromDate1 +"' AND cast(e.invoiceDate as date) <= '"+ toDate1 +"'");
		    first=false;
		   }
		
		
		if (invoiceNumber!=null) {
			System.out.println("INVOICE NO :::::::" + invoiceNumber);
			hql.append(first ? " where " : " and ");
		    hql.append("e.invoiceNumber like :invoiceNumber");
		    params.put("invoiceNumber", "%"+invoiceNumber+"%");
		    first = false;
		}
		
		//logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		
		if (partyName!=null && partyName.trim().length()>0) {
			hql.append(first ? " where " : " and ");
		    hql.append("e.partyEntity.partyName like :partyName");
		    params.put("partyName", "%"+partyName+"%");
		    first = false;
		  	}
		
		
		if (itemName!=null && itemName.trim().length()>0) {
			hql.append(first ? " where " : " and ");
			hql.append("e.invoiceNumber IN (SELECT DISTINCT be.invoiceNumber FROM BillDetailEntity be, ItemEntity ie WHERE be.itemId=ie.itemId AND ie.itemName LIKE "+"'%"+itemName+"%'"+ " AND ie.deletedFlag=0)");
			//params.put("invoiceNumber", "%"+"IN (SELECT DISTINCT invoice_number FROM t_bill_detail, m_item WHERE t_bill_detail.item_id=m_item.item_id AND m_item.item_name LIKE 'Copp%' AND m_item.deleted_flag=0)"+"%");
		    first = false;
		  	}
		
	//	logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		hql.append(first ? " where " : " and ");
		hql.append(" e.deletedFlag=0  ORDER BY e.modifiedDate DESC ");
		first = false;
	//	logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		List l= getJpaTemplate().findByNamedParams(hql.toString(), params);
		System.out.println("LIST SIZE :::::::" + l.size());
		return l;
		}
	
	@Override
	public List<BillEntity> findByEmployeeId(Integer partyId) {
		return getJpaTemplate().find("FROM BillEntity e WHERE e.deletedFlag=0 and e.partyEntity.partyId = "+partyId);
	}

	@Override
	public List<BillEntity> getMaxInvoiceId() {
		// TODO Auto-generated method stub
		return getJpaTemplate().find("FROM BillEntity e   where e.deletedFlag=0 ORDER BY e.invoiceAutoId DESC LIMIT 1");
	}
	
	@Override
	public List<BillEntity> getFinacialYear() {
		// TODO Auto-generated method stub
		 return getJpaTemplate().find("SELECT finyr FROM  BillEntity e  where e.deletedFlag=0 GROUP BY e.finyr");
	}

	@Override
	public Integer getNewSeriesNo(String finYear) {
		 int id=0;
	        List list=getJpaTemplate().find("SELECT max(e.invoiceId) FROM BillEntity e WHERE e.finyr=?1 and e.deletedFlag=0 ",finYear);
	        if(list!=null && list.size()>0){
	        	Number n=(Number)list.get(0);
	        	if(n!=null)
	        	id=n.intValue();
	        }
	        id++;
		return id;
		
	}
	
	@Override
    @Transactional(readOnly = true)
	public List<BillEntity> loadInvoicePagination(Integer index) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		Query query=entityManager.createQuery("FROM BillEntity e  where e.deletedFlag=0 ORDER BY e.invoiceAutoId DESC");
		query.setFirstResult(index);
		query.setMaxResults(15);
		List<BillEntity> list = query.getResultList();
		entityManager.close();
		return list;
		
	}

	@Override
	public List getCountInvoiceToEmail(String startDate, String endDate) {
		 List list=getJpaTemplate().find("SELECT count(distinct e.invoiceNumber),sum(e.billNetAmount) ,(select sum(b.billNetAmount) from BillEntity b where cast(b.invoiceDate as date) between '"+startDate+"' and '"+endDate+"' and b.deletedFlag=0) FROM BillEntity e WHERE cast(e.invoiceDate as date)='"+endDate+"' and e.deletedFlag=0 ");
		 
		   
		 List exciseList=getJpaTemplate().find("SELECT sum(e.exciseDutyAmount),sum(e.educationCessAmount),sum(e.highEducationCessAmount)  from BillEntity e where cast(e.invoiceDate as date) between '"+startDate+"' and '"+endDate+"' and e.deletedFlag=0");
		 
		 ArrayList<List> arrList=new ArrayList<List>();
		    arrList.add(list);
		    arrList.add(exciseList);
		    
		 return arrList;
	       }

	
	@Override
	public String checkDuplicateProformaNumber(String proformaNumber) {
		String proformaNum=null;
		List list=getJpaTemplate().find("SELECT e.proformaNumber FROM BillEntity e WHERE e.proformaNumber=?1 and e.deletedFlag=0 ",proformaNumber);
        if(list!=null && list.size()>0){
        	 proformaNum=(String)list.get(0);
        }
	return proformaNum;
	}
	    }