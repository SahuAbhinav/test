package com.advanz.erp.masters.storage.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.erp.masters.entity.jpa.EmailDetailEntity;
import com.advanz.erp.masters.entity.jpa.StockLedgerEntity;
import com.advanz.erp.masters.storage.IStorageStockLedgerDAO;

public class StorageStockLedgerDAO extends JpaDaoSupport implements
		IStorageStockLedgerDAO {
	@Autowired
	EntityManagerFactory entityManagerFactory;
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(StockLedgerEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot create a null entity");
		}
		entity.setDeletedFlag(false);
		getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public StockLedgerEntity read(Integer itemId) {
		if (itemId == null) {
			throw new IllegalArgumentException("uid must not be null");
		}
		return getJpaTemplate().find(StockLedgerEntity.class, itemId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public StockLedgerEntity update(StockLedgerEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot update a null entity");
		}
		entity.setDeletedFlag(false);
		return getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(StockLedgerEntity entity) {

		if (entity == null) {
			throw new IllegalArgumentException("Cannot remove a null entity");
		}
		entity=getJpaTemplate().find(StockLedgerEntity.class,entity.getSno());
		entity.setDeletedFlag(true);
		getJpaTemplate().merge(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public List<StockLedgerEntity> load() {
		// TODO Auto-generated method stub
		return getJpaTemplate()
				.find("FROM StockLedgerEntity e where e.deletedFlag=0 ORDER BY e.modifiedDate DESC");
	}

	@Override
	@Transactional(readOnly = true)
	public List<StockLedgerEntity> findById(Integer itemId) {
		// TODO Auto-generated method stub
		return getJpaTemplate().find(
				"FROM StockLedgerEntity e WHERE e.deletedFlag=0 and e.itemId = "
						+ itemId);
	}

	@Override
	public List countStockByItemId(Integer itemId) {
		List l = getJpaTemplate()
				.find("SELECT SUM(quantity) FROM StockLedgerEntity e WHERE e.deletedFlag=0 and e.itemId = "+ itemId);
		return l;
	}

	@Override
	public List<Double> countStockByBatchNo(String batchNo) {
		List<Double> l = getJpaTemplate()
				.find("SELECT SUM(quantity) FROM StockLedgerEntity e WHERE e.deletedFlag=0 and e.batchNo =?1",
						batchNo);

		return l;
	}

	@Override
	public List findStockByTransactionId(String transactionNumber) {
		return getJpaTemplate().find(
				"FROM StockLedgerEntity e WHERE e.deletedFlag=0 and transactionNumber = "
						+ "'" + transactionNumber + "'");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<StockLedgerEntity> findByItemId(Integer itemId) {
		return getJpaTemplate()
				.find("FROM StockLedgerEntity e WHERE e.itemId=?1 and e.deletedFlag=?2",
						itemId, false);

	}

	// added by kamal on 8-2-13 for remove finishgoods

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<StockLedgerEntity> deleteByTransactionNumber(
			String transactionNumber) {
		return getJpaTemplate()
				.find("FROM StockLedgerEntity e WHERE e.transactionNumber=?1 and e.deletedFlag=?2",
						transactionNumber, false);

	}

	@Override
	public List<Double> countStockForItemByItemId(Integer itemId) {
		List<Double> l = getJpaTemplate()
				.find("SELECT SUM(quantity) FROM StockLedgerEntity e WHERE e.deletedFlag=0 and e.itemId = "
						+ itemId);

		return l;
	}

	@Override
	public List ItemCountDateWise(String date) {
		List list =new ArrayList();
	EntityManager entityManager=entityManagerFactory.createEntityManager();
	/*Object object = (Object)entityManager.createNativeQuery("SELECT COUNT(DISTINCT(a.Item_id)) AS mil_pending, COUNT(DISTINCT(p.item_id)) AS po_pending FROM (SELECT item.Item_id, IFNULL(item.min_stock,0), (IFNULL(s.qty,0) + IFNULL(item.opening_stock,0)) AS Stock FROM  M_ITEM AS item LEFT JOIN (SELECT SUM(IFNULL(s.QUANTITY,0)) AS qty, item_id FROM t_stock_ledger AS s WHERE s.DELETED_FLAG=0 GROUP BY s.ITEM_ID) AS s ON item.item_id = s.item_id WHERE item.deleted_flag = 0 AND item.active_status='1' AND (IFNULL(s.qty,0) + IFNULL(item.opening_stock,0)) < IFNULL(item.min_stock,0) GROUP BY item.Item_id)AS a LEFT JOIN (SELECT d.item_id, SUM(d.quantity) AS pur_qty FROM t_purchase_order_mast AS p, t_purchase_order_detail AS d WHERE p.deleted_flag = 0 AND d.deleted_flag = 0 AND p.po_number = d.po_number AND IFNULL(d.pending_quantity,0) > 0 AND p.po_date <= '"+date+"' GROUP BY d.item_id) AS p ON a.Item_id = p.item_id").getSingleResult();*/
	Object object = (Object)entityManager.createNativeQuery("SELECT COUNT(DISTINCT(a.Item_id)) AS mil_pending, COUNT(DISTINCT(p.item_id)) AS po_pending FROM (SELECT item.Item_id, IFNULL(item.min_stock,0), (IFNULL(s.qty,0) + IFNULL(item.opening_stock,0)) AS Stock FROM  M_ITEM AS item LEFT JOIN (SELECT SUM(IFNULL(s.QUANTITY,0)) AS qty, item_id FROM t_stock_ledger AS s WHERE s.DELETED_FLAG=0 GROUP BY s.ITEM_ID) AS s ON item.item_id = s.item_id WHERE item.deleted_flag = 0 AND item.active_status='1' AND (IFNULL(s.qty,0) + IFNULL(item.opening_stock,0)) < IFNULL(item.min_stock,0) GROUP BY item.Item_id)AS a LEFT JOIN (SELECT d.item_id, SUM(d.quantity) AS pur_qty FROM t_purchase_order_mast AS p, t_purchase_order_detail AS d WHERE p.deleted_flag = 0 AND d.deleted_flag = 0 AND p.po_number = d.po_number AND IFNULL(d.pending_quantity,0) > 0 AND p.po_date <= CURRENT_DATE GROUP BY d.item_id) AS p ON a.Item_id = p.item_id").getSingleResult();
	list.add(object);
	entityManager.close();
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void createEmailDetal(String emailDetail, Date date,Integer userId) {
		EmailDetailEntity entity = new EmailDetailEntity();
		entity.setEmailDetail(emailDetail);
		entity.setEmailDate(date);
		
		try{
		entity.setSenderId(userId.toString());
		}catch (Exception e) {
		}
		getJpaTemplate().merge(entity);
		
	}

	@Override
	@Transactional(readOnly = true)
	public String getEmailByDate(String date) {
		String emailBody=null;
		List list = getJpaTemplate()
		.find("SELECT e.emailDetail from EmailDetailEntity e WHERE CAST(e.emailDate as date)='"+date+"'");
		if(list!=null && list.size()>0){
		emailBody=(String)list.get(0);
		}
		return emailBody;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateEmailDetal(String date, String status) {
		EmailDetailEntity entity = new EmailDetailEntity();
		String sm="";
		if(status.equalsIgnoreCase("FALSE")){
			sm=" or senderId is null or senderId like '' ";
		}
		if(status.equalsIgnoreCase("NATIVE")){
			sm=" or senderId  like 'FALSE' ";
		}
		if(status.equalsIgnoreCase("UPDATE")){
			sm=" or senderId  like 'FALSE' or senderId is null or senderId like '' ";
		}
		System.out.println("from EmailDetailEntity e WHERE CAST(e.emailDate as date)='"+date+"' and (senderId like '"+status+"' "+ sm+")  order by sno DESC");
		List list = getJpaTemplate().find(" from EmailDetailEntity e WHERE CAST(e.emailDate as date)='"+date+"' and (senderId like '"+status+"' "+ sm+") order by sno DESC");
				if(list!=null && list.size()>0){
					entity=(EmailDetailEntity)list.get(0);
					System.out.println(entity);
				}
				//System.out.println("list.get(0)"+list.get(0));
		try{
			if(status.equalsIgnoreCase("NATIVE") || status.equalsIgnoreCase("UPDATE")){
				entity.setSenderId("TRUE");
			}else{
			entity.setSenderId(status);
			}
		}catch (Exception e) {
		}
		getJpaTemplate().merge(entity);
		
	}

	@Override
	public List getEmailDetal(String date, String status) {
		String sm="";
		if(status.equalsIgnoreCase("FALSE")){
			sm=" or senderId is null or senderId like '' ";
		}
		System.out.println("from EmailDetailEntity e WHERE CAST(e.emailDate as date)='"+date+"' and (senderId like '"+status+"' "+ sm+")  order by sno DESC");
		List list = getJpaTemplate().find(" from EmailDetailEntity e WHERE CAST(e.emailDate as date)='"+date+"' and (senderId like '"+status+"' "+ sm+")  order by sno DESC");
		return list;
	}
	public void updateWeightedRateInStock(){
		
		String sql1="DELETE FROM ExciseLedgerEntity e WHERE e.invoiceNumber ";
		String sql2="DELETE FROM StockLedgerEntity e WHERE e.transactionNumber ";
		String sql3="DELETE FROM BillEntity e WHERE e.invoiceNumber ";
		
		try {
			EntityManager entityManager=entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.createQuery(sql1).executeUpdate();
			entityManager.getTransaction().commit();
			entityManager.getTransaction().begin();
			entityManager.createQuery(sql2).executeUpdate();
			entityManager.getTransaction().commit();
			entityManager.getTransaction().begin();
			entityManager.createQuery(sql3).executeUpdate();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List getLastSalesRate(Integer itemId, Date transactionDate,
			String transactionCodeRequired) {
		
		return getJpaTemplate()
				.find("FROM StockLedgerEntity e WHERE e.transactionSeries like ?1 and e.transactionDate<=?2 and e.itemId=?3 and e.deletedFlag=?4 ORDER BY e.transactionDate DESC ",
						transactionCodeRequired,transactionDate,itemId, false);
	}

	
}