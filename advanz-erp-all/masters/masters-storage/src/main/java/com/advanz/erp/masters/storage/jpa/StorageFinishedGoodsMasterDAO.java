package com.advanz.erp.masters.storage.jpa;

import java.sql.Timestamp;
import java.text.MessageFormat;
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

import com.advanz.common.DataUtility;
import com.advanz.erp.masters.entity.jpa.FinishedGoodsDetailEntity;
import com.advanz.erp.masters.entity.jpa.FinishedGoodsMasterEntity;
import com.advanz.erp.masters.storage.IStorageFinishedGoodsMasterDAO;

@Component
public class StorageFinishedGoodsMasterDAO extends JpaDaoSupport implements
		IStorageFinishedGoodsMasterDAO {

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(FinishedGoodsMasterEntity entity) {

		if (entity == null) {
			throw new IllegalArgumentException("Cannot create a null entity");
		}
		getJpaTemplate().merge(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public FinishedGoodsMasterEntity read(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("uid must not be null");
		}
		return getJpaTemplate().find(FinishedGoodsMasterEntity.class, id);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public FinishedGoodsMasterEntity update(FinishedGoodsMasterEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot update a null entity");
		}
         List<FinishedGoodsDetailEntity> detailEntityList=entity.getFinishedGoodsDetailEntities();
		 List<FinishedGoodsDetailEntity>  l=getJpaTemplate().find("FROM FinishedGoodsDetailEntity e where e.deletedFlag=?1  AND e.finishedGoodsNumber='"+entity.getFinishedGoodsNumber()+"'",false);
		 for(int i=0;i<l.size();i++){
			 FinishedGoodsDetailEntity detailEntity=	l.get(i);
				for(int j=0;j<detailEntityList.size();j++){
					FinishedGoodsDetailEntity detailEntity2=detailEntityList.get(j);
					if(detailEntity2.getFinishAutoId()!=detailEntity.getFinishAutoId() ){
						detailEntity.setFinishAutoId(null);
						detailEntity.setDeletedFlag(true);
						entity.getFinishedGoodsDetailEntities().add(detailEntity);
					}}}
		 entity.setDeletedFlag(false);
		return getJpaTemplate().merge(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(FinishedGoodsMasterEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot remove a null entity");
		}
		entity = getJpaTemplate().find(FinishedGoodsMasterEntity.class,
				entity.getFinishedGoodsAutoId());
		entity.setDeletedFlag(true);
		List<FinishedGoodsDetailEntity> detailEntities = entity
				.getFinishedGoodsDetailEntities();
		for (FinishedGoodsDetailEntity e : detailEntities) {
			e.setDeletedFlag(true);
		}
		getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<FinishedGoodsMasterEntity> load() {
		return getJpaTemplate()
				.find("FROM FinishedGoodsMasterEntity e where e.deletedFlag=?1  ORDER BY e.finishedGoodsAutoId DESC",
						false);
	}

	@Override
	public List<FinishedGoodsMasterEntity> FindFinishedGoodPagination(Integer next) {
	EntityManager entityManager=entityManagerFactory.createEntityManager();
	Query query=entityManager.createQuery("FROM FinishedGoodsMasterEntity e WHERE e.deletedFlag=0 ORDER BY e.finishedGoodsAutoId DESC");
	query.setFirstResult(next);
	query.setMaxResults(15);
	List<FinishedGoodsMasterEntity> list = query.getResultList();
	entityManager.close();
	return list;
	}
	
	@Override
	@Transactional(readOnly = true)
	public <E> List<FinishedGoodsMasterEntity> findById(E id) {
		logger.info("a service  >>>>>>>>>>>> : " + id);
		return getJpaTemplate()
				.find("FROM FinishedGoodsMasterEntity e WHERE e.finishedGoodsAutoId=?1 and e.deletedFlag=?2",
						id, false);
	}

	@Override
	@Transactional(readOnly = true)
	public <E> List<FinishedGoodsMasterEntity> findByFinishGoodNumber(E id,Date date) {
		logger.info("a service  >>>>>>>>>>>> : " + id);
		String toDate1 = null;
		if (date != null) {
			toDate1 = DataUtility.getDate(date);
		}
		return getJpaTemplate()
				.find("FROM FinishedGoodsMasterEntity e WHERE (e.finishedGoodsNumber=?1 OR cast(e.finishGoodDate as date)='"+toDate1+"') and e.deletedFlag=?2",id, false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<FinishedGoodsMasterEntity> search(String finishedGoodsNumber,
			Date fromDate, Date toDate, String itemName) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM FinishedGoodsMasterEntity e");
		boolean first = true;

		if (StringUtils.hasText(finishedGoodsNumber)) {
			hql.append(first ? " where " : " and ");
			hql.append("finishedGoodsNumber like :finishedGoodsNumber");
			params.put("finishedGoodsNumber", "%" + finishedGoodsNumber.trim()
					+ "%");
			first = false;
		}

		if (fromDate != null || toDate != null) {
			hql.append(first ? " where " : " and ");
			String fromDate1 = "";
			if (fromDate != null) {
				fromDate1 = DataUtility.getDate(fromDate);
			}
			String toDate1 = null;
			if (toDate != null) {
				toDate1 = DataUtility.getDate(toDate);
			}

			hql.append("cast (finishGoodDate as date) >= '" + fromDate1
					+ "' AND cast (finishGoodDate as date) <= '" + toDate1 + "'");
			first = false;
		}
		
		/*
		 * logger.info("HQL >>>>>>>>>>>> : "+ hql.toString()); if
		 * (fromDate!=null) {
		 * System.out.println(" FROM DATE IS ::::::::::::::: " + fromDate);
		 * hql.append(first ? " where " : " and ");
		 * hql.append("finishGoodDate >= :finishGoodDate");
		 * params.put("finishGoodDate", fromDate); first=false; }
		 * 
		 * if (toDate!=null) { System.out.println(" TO DATE IS ::::::::::::::: "
		 * + toDate); hql.append(first ? " where " : " and ");
		 * hql.append("finishGoodDate <= :finishGoodDate");
		 * params.put("finishGoodDate", toDate); first=false; }
		 */
		if (itemName != null && itemName.trim().length() > 0) {
			hql.append(first ? " where " : " and ");
			hql.append("e.finishedGoodsNumber IN (SELECT DISTINCT fge.finishedGoodsNumber FROM FinishedGoodsDetailEntity fge, ItemEntity ie WHERE fge.itemEntity.itemId=ie.itemId AND ie.itemName LIKE "
					+ "'%" + itemName + "%'" + " AND ie.deletedFlag=0)");
			// params.put("invoiceNumber",
			// "%"+"IN (SELECT DISTINCT invoice_number FROM t_bill_detail, m_item WHERE t_bill_detail.item_id=m_item.item_id AND m_item.item_name LIKE 'Copp%' AND m_item.deleted_flag=0)"+"%");
			first = false;
		}
		logger.info("HQL >>>>>>>>>>>> : " + hql.toString());
		hql.append(first ? " where " : " and ");
		hql.append(" e.deletedFlag=0  ORDER BY e.finishedGoodsAutoId DESC");
		first = false;
		logger.info("HQL >>>>>>>>>>>> : " + hql.toString());
		System.out.println("HQL >>>>>>>>>>>> : " + hql.toString());
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	public List getNewSeriesNo(String finYear) {
		return getJpaTemplate()
				.find("SELECT max(e.finishGoodId),max(e.finishGoodDate) FROM FinishedGoodsMasterEntity e WHERE e.finYear=?1 and e.deletedFlag=0",
						finYear);
	}
	

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<FinishedGoodsDetailEntity> findByItemId(Integer itemId) {
		return getJpaTemplate()
				.find("FROM FinishedGoodsDetailEntity e WHERE e.itemEntity.itemId=?1 and e.deletedFlag=?2",
						itemId, false);

	}

	@Override
	public List getFinishGoodInfoToEmail(String date) {
		
		List list = new ArrayList();
		/*comment for active status on 21-03-15
		 * "SELECT * FROM (SELECT COUNT(f.finished_goods_number) AS total_finish_goods_entry_made_on_today FROM  t_finished_goods AS f WHERE CAST(f.finish_good_date AS DATE) = CURRENT_DATE AND f.deleted_flag=0) AS a,(SELECT SUM((IFNULL(i.opening_stock,0) + IFNULL(b.ledgerqty,0)) * IFNULL(i.sales_rate,0)) AS total_finish_goods_stock_amount FROM m_item_group_flag gf, m_item_group g, m_item_category c, m_item i LEFT JOIN (SELECT l.item_id,SUM(IFNULL(l.quantity,0)) AS ledgerqty FROM t_stock_ledger l WHERE l.deleted_flag=0 GROUP BY l.item_id) AS b ON b.item_id=i.item_id WHERE gf.ITEM_GROUP_FLAG_Name = 'FINISH GOODS' AND gf.ITEM_GROUP_FLAG_ID = g.ITEM_GROUP_FLAG_ID AND g.ITEM_GROUP_ID = c.ITEM_GROUP_ID AND c.ITEM_CATEGORY_ID = i.item_category_id AND g.DELETED_FLAG = 0 AND c.DELETED_FLAG = 0 AND i.deleted_flag = 0) AS b ,(SELECT SUM(booked_qty_amount) AS booked_qty_amount FROM (SELECT SUM(IFNULL(d.pending_qty,0)) AS pending_quantity,IFNULL(i.sales_rate,0) AS item_rate,IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0) AS closing_stock,CASE WHEN (IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0))>SUM(IFNULL(d.pending_qty,0)) THEN SUM(IFNULL(d.pending_qty,0)) WHEN (IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0))>0 THEN (IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0)) ELSE 0 END AS booked_qty,CASE WHEN (IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0))>SUM(IFNULL(d.pending_qty,0)) THEN SUM(IFNULL(d.pending_qty,0))*IFNULL(i.sales_rate,0) WHEN (IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0))>0 THEN (IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0))*IFNULL(i.sales_rate,0) ELSE 0 END AS booked_qty_amount FROM m_item i, t_sales_order_detail d LEFT JOIN (SELECT l.item_id,SUM(l.quantity) AS quantity FROM t_stock_ledger l WHERE l.deleted_flag=0 GROUP BY l.item_id) AS l ON d.item_id=l.item_id WHERE d.deleted_flag=0 AND i.deleted_flag=0 AND d.item_id=i.item_id AND d.pending_qty>0 GROUP BY i.item_id HAVING booked_qty>0) AS c) AS c,(SELECT SUM(pending_qty_amount) AS pending_production_amount FROM (SELECT SUM(IFNULL(d.pending_qty,0))-(IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0)) AS pending_stock_qty ,IFNULL(i.sales_rate,0)*(SUM(IFNULL(d.pending_qty,0))-(IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0))) AS pending_qty_amount FROM m_item i, t_sales_order_detail d LEFT JOIN (SELECT l.item_id,SUM(l.quantity) AS quantity FROM t_stock_ledger l WHERE l.deleted_flag=0 GROUP BY l.item_id) AS l ON d.item_id=l.item_id WHERE d.deleted_flag=0 AND i.deleted_flag=0 AND d.item_id=i.item_id AND d.pending_qty>0 GROUP BY i.item_id HAVING pending_stock_qty>0) AS d) AS d")
		 */
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		Object object = (Object) entityManager
				.createNativeQuery(
					"SELECT * FROM (SELECT COUNT(f.finished_goods_number) AS total_finish_goods_entry_made_on_today FROM  t_finished_goods AS f WHERE CAST(f.finish_good_date AS DATE) = CURRENT_DATE AND f.deleted_flag=0) AS a,(SELECT SUM((IFNULL(i.opening_stock,0) + IFNULL(b.ledgerqty,0)) * IFNULL(i.sales_rate,0)) AS total_finish_goods_stock_amount FROM m_item_group_flag gf, m_item_group g, m_item_category c, m_item i LEFT JOIN (SELECT l.item_id,SUM(IFNULL(l.quantity,0)) AS ledgerqty FROM t_stock_ledger l WHERE l.deleted_flag=0 GROUP BY l.item_id) AS b ON b.item_id=i.item_id WHERE gf.ITEM_GROUP_FLAG_Name = 'FINISH GOODS' AND gf.ITEM_GROUP_FLAG_ID = g.ITEM_GROUP_FLAG_ID AND g.ITEM_GROUP_ID = c.ITEM_GROUP_ID AND c.ITEM_CATEGORY_ID = i.item_category_id AND g.DELETED_FLAG = 0 AND c.DELETED_FLAG = 0 AND i.deleted_flag = 0) AS b ,(SELECT SUM(booked_qty_amount) AS booked_qty_amount FROM (SELECT SUM(IFNULL(d.pending_qty,0)) AS pending_quantity,IFNULL(i.sales_rate,0) AS item_rate,IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0) AS closing_stock,CASE WHEN (IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0))>SUM(IFNULL(d.pending_qty,0)) THEN SUM(IFNULL(d.pending_qty,0)) WHEN (IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0))>0 THEN (IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0)) ELSE 0 END AS booked_qty,CASE WHEN (IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0))>SUM(IFNULL(d.pending_qty,0)) THEN SUM(IFNULL(d.pending_qty,0))*IFNULL(i.sales_rate,0) WHEN (IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0))>0 THEN (IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0))*IFNULL(i.sales_rate,0) ELSE 0 END AS booked_qty_amount FROM m_item i, t_sales_order_detail d LEFT JOIN (SELECT l.item_id,SUM(l.quantity) AS quantity FROM t_stock_ledger l WHERE l.deleted_flag=0 GROUP BY l.item_id) AS l ON d.item_id=l.item_id WHERE d.deleted_flag=0 AND i.deleted_flag=0 AND d.item_id=i.item_id AND d.pending_qty>0 AND d.active_status=1 GROUP BY i.item_id HAVING booked_qty>0) AS c) AS c,(SELECT SUM(pending_qty_amount) AS pending_production_amount FROM (SELECT SUM(IFNULL(d.pending_qty,0))-(IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0)) AS pending_stock_qty ,IFNULL(i.sales_rate,0)*(SUM(IFNULL(d.pending_qty,0))-(IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0))) AS pending_qty_amount FROM m_item i, t_sales_order_detail d LEFT JOIN (SELECT l.item_id,SUM(l.quantity) AS quantity FROM t_stock_ledger l WHERE l.deleted_flag=0 GROUP BY l.item_id) AS l ON d.item_id=l.item_id WHERE d.deleted_flag=0 AND i.deleted_flag=0 AND d.item_id=i.item_id AND d.pending_qty>0 AND d.active_status=1 GROUP BY i.item_id HAVING pending_stock_qty>0) AS d) AS d")
				.getSingleResult();
		
		
	/*"SELECT * FROM (SELECT COUNT(f.finished_goods_number) AS total_finish_goods_entry_made_on_today FROM  t_finished_goods AS f WHERE CAST(f.finish_good_date AS DATE) = CURRENT_DATE AND f.deleted_flag=0) AS a,(SELECT SUM((IFNULL(i.opening_stock,0) + IFNULL(b.ledgerqty,0)) * IFNULL(i.sales_rate,0)) AS total_finish_goods_stock_amount FROM m_item_group_flag gf, m_item_group g, m_item_category c, m_item i LEFT JOIN (SELECT l.item_id,SUM(IFNULL(l.quantity,0)) AS ledgerqty FROM t_stock_ledger l WHERE l.deleted_flag=0 GROUP BY l.item_id) AS b ON b.item_id=i.item_id WHERE gf.ITEM_GROUP_FLAG_Name = 'FINISH GOODS' AND gf.ITEM_GROUP_FLAG_ID = g.ITEM_GROUP_FLAG_ID AND g.ITEM_GROUP_ID = c.ITEM_GROUP_ID AND c.ITEM_CATEGORY_ID = i.item_category_id AND g.DELETED_FLAG = 0 AND c.DELETED_FLAG = 0 AND i.deleted_flag = 0) AS b ,(SELECT SUM(booked_qty_amount) AS booked_qty_amount FROM (SELECT SUM(IFNULL(d.pending_qty,0)) AS pending_quantity,IFNULL(i.sales_rate,0) AS item_rate,IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0) AS closing_stock,CASE WHEN (IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0))>SUM(IFNULL(d.pending_qty,0)) THEN SUM(IFNULL(d.pending_qty,0)) WHEN (IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0))>0 THEN (IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0)) ELSE 0 END AS booked_qty,CASE WHEN (IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0))>SUM(IFNULL(d.pending_qty,0)) THEN SUM(IFNULL(d.pending_qty,0))*IFNULL(i.sales_rate,0) WHEN (IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0))>0 THEN (IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0))*IFNULL(i.sales_rate,0) ELSE 0 END AS booked_qty_amount FROM m_item i, t_sales_order_detail d LEFT JOIN (SELECT l.item_id,SUM(l.quantity) AS quantity FROM t_stock_ledger l WHERE l.deleted_flag=0 GROUP BY l.item_id) AS l ON d.item_id=l.item_id WHERE d.deleted_flag=0 AND i.deleted_flag=0 AND d.item_id=i.item_id AND d.pending_qty>0 GROUP BY i.item_id HAVING booked_qty>0) AS c) AS c,(SELECT SUM(pending_qty_amount) AS pending_production_amount FROM (SELECT SUM(IFNULL(d.pending_qty,0))-(IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0)) AS pending_stock_qty ,IFNULL(i.sales_rate,0)*(SUM(IFNULL(d.pending_qty,0))-(IFNULL(i.opening_stock,0)+IFNULL(l.quantity,0))) AS pending_qty_amount FROM m_item i, t_sales_order_detail d LEFT JOIN (SELECT l.item_id,SUM(l.quantity) AS quantity FROM t_stock_ledger l WHERE l.deleted_flag=0 GROUP BY l.item_id) AS l ON d.item_id=l.item_id WHERE d.deleted_flag=0 AND i.deleted_flag=0 AND d.item_id=i.item_id AND d.pending_qty>0 GROUP BY i.item_id HAVING pending_stock_qty>0) AS d) AS d")*/
		
		
		
		
		
		list.add(object);
		entityManager.close();
		return list;
	}

	@Override
	public Integer findLastFinishedGoodDetail(Integer finishGoodId,
			Integer itemId) {
		Integer id=0;
		String s="SELECT max(sno) FROM FinishedGoodsDetailEntity e WHERE e.itemEntity.itemId="+itemId+" and e.deletedFlag="+false+" and e.finishedGoodsNumber like '%"+finishGoodId+"'";
		List list= getJpaTemplate()
		.find(s);
		if (list != null && list.size() > 0) {
			Number n = (Number) list.get(0);
			if (n != null)
				id = n.intValue();
		}
		return id;
	}
}