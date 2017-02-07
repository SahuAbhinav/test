package com.advanz.erp.masters.storage.jpa;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.erp.masters.entity.jpa.SalesOrderDetailEntity;
import com.advanz.erp.masters.entity.jpa.SalesOrderMasterEntity;
import com.advanz.erp.masters.storage.IStorageSalesOrderMasterDAO;

@Component
public class StorageSalesOrderMasterDAO extends JpaDaoSupport implements IStorageSalesOrderMasterDAO{

	@Autowired
	EntityManagerFactory entityManagerFactory;
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(SalesOrderMasterEntity entity) {		
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        Integer id=getNewSeriesNo(entity.getFinYear());
        if(!id.equals(entity.getSalesOrderId())){
           	entity.setSalesOrderId(id);        	
        	entity.setSalesOrderNumber(entity.getSalesOrderNumber().substring(0,8)+id);          	
        }
        getJpaTemplate().persist(entity);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String save(SalesOrderMasterEntity entity) {		
		String son=null;
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        Integer id=getNewSeriesNo(entity.getFinYear());
        if(!id.equals(entity.getSalesOrderId())){
           	entity.setSalesOrderId(id);        	
        	entity.setSalesOrderNumber(entity.getSalesOrderNumber().substring(0,8)+id); 
        	son=entity.getSalesOrderNumber();
        }
        getJpaTemplate().persist(entity);
        return son;
	}
	@Override
	@Transactional(readOnly = true)
	public SalesOrderMasterEntity read(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
        return getJpaTemplate().find(SalesOrderMasterEntity.class, id);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public SalesOrderMasterEntity update(SalesOrderMasterEntity entity) {
		 if (entity == null) {
	            throw new IllegalArgumentException("Cannot update a null entity");
	        }
	       // entity.setDeletedFlag(false);
		 List<SalesOrderDetailEntity> detailDeletedList= getJpaTemplate().find("FROM SalesOrderDetailEntity e where e.deletedFlag=?1 and e.salesOrderAutoId=?2",true,entity.getSalesOrderAutoId());
		 if(detailDeletedList!=null){
			 for(SalesOrderDetailEntity e:detailDeletedList){
				 entity.getSalesOrderDetailEntities().add(e);
			 }
		 }
	        return getJpaTemplate().merge(entity);
		}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(SalesOrderMasterEntity entity1) {
		if (entity1 == null) {
            throw new IllegalArgumentException("Cannot remove a null entity");
        }
		SalesOrderMasterEntity  entity=getJpaTemplate().find(SalesOrderMasterEntity.class, entity1.getSalesOrderAutoId());	
	  entity.setDeletedFlag(true);
	  List<SalesOrderDetailEntity> detailEntities=entity.getSalesOrderDetailEntities();
      for(SalesOrderDetailEntity e:detailEntities){
    	  e.setDeletedFlag(true);
    	  e.setModifiedUserId(entity1.getModifiedUserId());
      }
      entity.setModifiedUserId(entity1.getModifiedUserId());
      getJpaTemplate().merge(entity);	
      
	}

	@Override
    @Transactional(readOnly = true)
	public List<SalesOrderMasterEntity> load() {		
    //    return getJpaTemplate().find("FROM SalesOrderMasterEntity e where e.deletedFlag=?1  ORDER BY e.modifiedDate DESC",false);
		  return getJpaTemplate().find("FROM SalesOrderMasterEntity e where e.deletedFlag=?1  ORDER BY e.salesOrderAutoId DESC",false);
	}

	@Override
	@Transactional(readOnly = true)
	public<E> List<SalesOrderMasterEntity> findById(E id) {		
		return getJpaTemplate().find("FROM SalesOrderMasterEntity e WHERE e.salesOrderAutoId =?1 and e.deletedFlag=?2", id,false);
	}
	
	@Override
	@Transactional(readOnly = true)
	public<E> List<SalesOrderMasterEntity> findBySalesOrderNo(E id) {		
		return getJpaTemplate().find("FROM SalesOrderMasterEntity e WHERE e.salesOrderNumber =?1 and e.deletedFlag=?2", id,false);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public<E> List<SalesOrderMasterEntity> findBySalesOrderNumber(E id) {		
		return getJpaTemplate().find("FROM SalesOrderMasterEntity e WHERE e.salesOrderNumber =?1 and e.deletedFlag=?2", id,false);
	}

	
	@Override
	@Transactional(readOnly = true)
	public List<SalesOrderMasterEntity> search(String salesOrderNumber,Date fromDate,Date toDate,String partyName,String itemName){
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM SalesOrderMasterEntity e");
		boolean first = true;
		if (StringUtils.hasText(salesOrderNumber)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.salesOrderNumber like :salesOrderNumber");
		    params.put("salesOrderNumber", "%"+salesOrderNumber.trim()+"%");
		    first=false;
		}
		if (fromDate!=null || toDate!=null) {
		    hql.append(first ? " where " : " and ");
		    String fromDate1="";
		    if (fromDate!=null){
		     fromDate1=getDate(fromDate);
		    }
		    String toDate1=null;
		    if (toDate!=null) {
		     toDate1=getDate(toDate);
		    } 
		   hql.append("e.salesOrderDate >= '"+ fromDate1 +"' AND e.salesOrderDate <= '"+ toDate1+"'");
		   first=false;
		}
		
		
		//logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		if (StringUtils.hasText(partyName)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.partyEntity.partyName like :partyName");
		    params.put("partyName", "%"+partyName.trim()+"%");
		    first=false;
		}
		if (itemName!=null && itemName.trim().length()>0) {
			hql.append(first ? " where " : " and ");
			hql.append("e.salesOrderNumber IN (SELECT DISTINCT sde.salesOrderNumber FROM SalesOrderDetailEntity sde, ItemEntity ie WHERE sde.itemEntity.itemId=ie.itemId AND ie.itemName LIKE "+"'%"+itemName+"%'"+ " AND ie.deletedFlag=0)");
			//params.put("invoiceNumber", "%"+"IN (SELECT DISTINCT invoice_number FROM t_bill_detail, m_item WHERE t_bill_detail.item_id=m_item.item_id AND m_item.item_name LIKE 'Copp%' AND m_item.deleted_flag=0)"+"%");
		    first = false;
		  	}
		//logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		hql.append(first ? " where " : " and ");
	    //hql.append(" e.deletedFlag=0 ORDER BY e.modifiedDate DESC");
		  hql.append(" e.deletedFlag=0 ORDER BY e.salesOrderAutoId DESC");
	    first=false;
		
		//logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}
	@Override
	@Transactional(readOnly = true)
	public Integer getNewSeriesNo(String finYear) {
		 int id=0;
	      //  List list=getJpaTemplate().find("SELECT max(e.salesOrderId) FROM SalesOrderMasterEntity e WHERE e.finYear=?1",finYear);
	        List list=getJpaTemplate().find("SELECT max(e.salesOrderId) FROM SalesOrderMasterEntity e WHERE e.finYear=?1 and e.deletedFlag=0",finYear);
		     
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
	public Timestamp getNewSeriesDate(String finYear) {
		Timestamp date = new Timestamp(new Date().getTime());
	        List list=getJpaTemplate().find("SELECT max(e.salesOrderDate) FROM SalesOrderMasterEntity e WHERE e.finYear=?1 and e.deletedFlag=0",finYear);
		     
	        if(list!=null && list.size()>0){
	        	Timestamp n=(Timestamp)list.get(0);
	        	if(n!=null)
	        		date=n;
	        }
	       
		return date;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<SalesOrderMasterEntity> findBySalesOrderNo(String salesOrderNo) {
		return getJpaTemplate().find("FROM SalesOrderMasterEntity e WHERE e.salesOrderNumber = "+ "'"+salesOrderNo+"' and e.deletedFlag=?1", false);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public boolean isInUsed(Integer id) {	
		List<String> list=getJpaTemplate().find("Select salesOrderNumber FROM SalesOrderMasterEntity e WHERE e.salesOrderAutoId =?1",id);
		if(list!=null && list.size()>0){
			List<Integer> invoiceList=getJpaTemplate().find("Select invoiceAutoId FROM BillEntity e WHERE e.deletedFlag=0 and e.salesOrderNumber =?1",list.get(0));
			if(invoiceList!=null && invoiceList.size()>0){
				return true;
			}
		}
		return false;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<SalesOrderDetailEntity> findByItemId(Integer itemId) {
		return getJpaTemplate().find("FROM SalesOrderDetailEntity e WHERE e.itemEntity.itemId=?1 and e.deletedFlag=?2", itemId,false);

	}

	
private String getDate(Date date){
		
		String s2="";
		if(date!=null){
    	try{
    		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	    	String s = df.format(date);
        	Date d = (new SimpleDateFormat("yyyy-MM-dd")).parse(s);
        	 s2 = (new SimpleDateFormat("yyyy-MM-dd")).format(d);
        	} catch(Exception e) {
        	  e.printStackTrace();
        	}
		}
		return s2;
	}
@Override
public List getSalesDetailToEmail(String date) {
	List list =new ArrayList();
	EntityManager entityManager=entityManagerFactory.createEntityManager();
	/*Object object = (Object)entityManager.createNativeQuery("SELECT * FROM (SELECT COUNT(a.item_id) AS items_pending_till_date, SUM(a.pending_amount)AS total_amount_of_pending_items FROM (SELECT d.item_id,SUM(d.sales_rate * IFNULL(d.pending_qty,0)) AS pending_amount FROM t_sales_order_mast AS s, t_sales_order_detail AS d WHERE s.sales_order_number = d.sales_order_number AND s.deleted_flag = 0 AND d.deleted_flag = 0 AND d.pending_qty > 0 AND s.sales_order_date <= '"+date+"' GROUP BY d.item_id) a) AS a ,(SELECT SUM(d.sales_rate * IFNULL(d.pending_qty,0)) AS total_amount_of_pending_items_having_more_than_15_days FROM t_sales_order_mast AS s, t_sales_order_detail AS d WHERE s.sales_order_number = d.sales_order_number AND s.deleted_flag = 0 AND d.deleted_flag = 0 AND d.pending_qty > 0 AND s.sales_order_date < (DATE_SUB('"+date+"', INTERVAL 15 DAY))) AS b").getSingleResult();*/
	/*Comment on 21-03-15 for active status
	 * Object object = (Object)entityManager.createNativeQuery("SELECT * FROM (SELECT COUNT(a.item_id) AS items_pending_till_date, SUM(a.pending_amount)AS total_amount_of_pending_items FROM (SELECT d.item_id,SUM(d.sales_rate * IFNULL(d.pending_qty,0)) AS pending_amount FROM t_sales_order_mast AS s, t_sales_order_detail AS d WHERE s.sales_order_number = d.sales_order_number AND s.deleted_flag = 0 AND d.deleted_flag = 0 AND d.pending_qty > 0 AND s.sales_order_date <= CURRENT_DATE GROUP BY d.item_id) a) AS a ,(SELECT SUM(d.sales_rate * IFNULL(d.pending_qty,0)) AS total_amount_of_pending_items_having_more_than_15_days FROM t_sales_order_mast AS s, t_sales_order_detail AS d WHERE s.sales_order_number = d.sales_order_number AND s.deleted_flag = 0 AND d.deleted_flag = 0 AND d.pending_qty > 0 AND s.sales_order_date < (DATE_SUB(CURRENT_DATE, INTERVAL 15 DAY))) AS b").getSingleResult();
	 */
	Object object = (Object)entityManager.createNativeQuery("SELECT * FROM (SELECT COUNT(a.item_id) AS items_pending_till_date, SUM(a.pending_amount)AS total_amount_of_pending_items FROM (SELECT d.item_id,SUM(d.sales_rate * IFNULL(d.pending_qty,0)) AS pending_amount FROM t_sales_order_mast AS s, t_sales_order_detail AS d WHERE s.sales_order_number = d.sales_order_number AND s.deleted_flag = 0 AND d.deleted_flag = 0 AND d.pending_qty > 0 AND d.active_status=1 AND s.sales_order_date <= CURRENT_DATE GROUP BY d.item_id) a) AS a ,(SELECT SUM(d.sales_rate * IFNULL(d.pending_qty,0)) AS total_amount_of_pending_items_having_more_than_15_days FROM t_sales_order_mast AS s, t_sales_order_detail AS d WHERE s.sales_order_number = d.sales_order_number AND s.deleted_flag = 0 AND d.deleted_flag = 0 AND d.pending_qty > 0 AND d.active_status=1 AND s.sales_order_date < (DATE_SUB(CURRENT_DATE, INTERVAL 15 DAY))) AS b").getSingleResult();
	list.add(object);
	entityManager.close();
	//Double d= Double.parseDouble(cust_name.toString()) ;
return list;
}
@Override
public List<SalesOrderMasterEntity> findSalesOrderMasterPagination(Integer next) {
	EntityManager entityManager=entityManagerFactory.createEntityManager();
	Query query=entityManager.createQuery("FROM SalesOrderMasterEntity e WHERE e.deletedFlag=0 ORDER BY e.salesOrderAutoId DESC");
	query.setFirstResult(next);
	query.setMaxResults(15);
	List<SalesOrderMasterEntity> list = query.getResultList();
	entityManager.close();
	return list;
}
@Override
public List getBookedSalesDetailToEmail(String date) {
	List list =new ArrayList();
	EntityManager entityManager=entityManagerFactory.createEntityManager();
	/*Object object = (Object)entityManager.createNativeQuery("SELECT * FROM (SELECT COUNT(1) AS today_sales_order_count FROM t_sales_order_mast WHERE t_sales_order_mast.deleted_flag=0 AND CAST(t_sales_order_mast.sales_order_date AS DATE)='"+date+"') AS sales_order_count ,(SELECT TRIM(GROUP_CONCAT(' ',so_amount)) AS so_amount FROM (SELECT CONCAT(m_party.party_name,' (Order=',COUNT(1) ,', Ass. value = Rs. ',ROUND(IFNULL(SUM(t_sales_order_mast.item_value),0)-IFNULL(SUM(t_sales_order_mast.discount_amount),0)+IFNULL(SUM(t_sales_order_mast.packing_forwarding),0),2),'/- )') AS so_amount FROM t_sales_order_mast,m_party WHERE t_sales_order_mast.deleted_flag=0 AND m_party.deleted_flag=0 AND t_sales_order_mast.party_id=m_party.party_id AND CAST(t_sales_order_mast.sales_order_date AS DATE)='"+date+"' GROUP BY m_party.party_id) AS a) AS assessable_value").getSingleResult();*/
	Object object = (Object)entityManager.createNativeQuery("SELECT * FROM (SELECT COUNT(1) AS today_sales_order_count FROM t_sales_order_mast WHERE t_sales_order_mast.deleted_flag=0 AND CAST(t_sales_order_mast.sales_order_date AS DATE)=CURRENT_DATE) AS sales_order_count ,(SELECT TRIM(GROUP_CONCAT(' ',so_amount)) AS so_amount FROM (SELECT CONCAT(m_party.party_name,' (Order=',COUNT(1) ,', Ass. value = Rs. ',ROUND(IFNULL(SUM(t_sales_order_mast.item_value),0)-IFNULL(SUM(t_sales_order_mast.discount_amount),0)+IFNULL(SUM(t_sales_order_mast.packing_forwarding),0),2),'/- )') AS so_amount FROM t_sales_order_mast,m_party WHERE t_sales_order_mast.deleted_flag=0 AND m_party.deleted_flag=0 AND t_sales_order_mast.party_id=m_party.party_id AND CAST(t_sales_order_mast.sales_order_date AS DATE)=CURRENT_DATE GROUP BY m_party.party_id) AS a) AS assessable_value").getSingleResult();
	list.add(object);
	entityManager.close();
	//Double d= Double.parseDouble(cust_name.toString()) ;
return list;
}
@Override
public Date getMinimumPendingDate() {
	EntityManager entityManager=entityManagerFactory.createEntityManager();
	Object object = (Object)entityManager.createNativeQuery("SELECT MIN(b.`sales_order_date`) FROM `t_sales_order_mast` AS b, `t_sales_order_detail` AS a WHERE b.`sales_order_number`=a.`sales_order_number` AND a.`pending_qty`>0 AND b.`deleted_flag`=0 AND a.`deleted_flag`=0").getSingleResult();
	 Timestamp issueDate=(Timestamp)object;
	 java.sql.Date date = new java.sql.Date(issueDate.getTime()); 
return date;
} 
}
