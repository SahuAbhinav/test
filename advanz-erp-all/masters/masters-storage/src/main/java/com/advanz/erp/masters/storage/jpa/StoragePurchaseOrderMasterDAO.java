package com.advanz.erp.masters.storage.jpa;

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
import com.advanz.erp.masters.entity.jpa.PurchaseOrderDetailEntity;
import com.advanz.erp.masters.entity.jpa.PurchaseOrderMasterEntity;
import com.advanz.erp.masters.storage.IStorageIndentDAO;
import com.advanz.erp.masters.storage.IStoragePurchaseOrderMasterDAO;

@Component
public class StoragePurchaseOrderMasterDAO extends JpaDaoSupport implements
		IStoragePurchaseOrderMasterDAO {
	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Autowired
	public IStorageIndentDAO indentDAO;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(PurchaseOrderMasterEntity entity) {

		if (entity == null) {
			throw new IllegalArgumentException("Cannot create a null entity");
		}
		getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public PurchaseOrderMasterEntity read(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("uid must not be null");
		}
		return getJpaTemplate().find(PurchaseOrderMasterEntity.class, id);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public PurchaseOrderMasterEntity update(PurchaseOrderMasterEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot update a null entity");
		}
		List<PurchaseOrderDetailEntity> detailEntityList = entity
				.getPurchaseOrderDetailEntity();

		List<PurchaseOrderDetailEntity> l = getJpaTemplate()
				.find("FROM PurchaseOrderDetailEntity e where e.deletedFlag=?1  AND e.purchaseOrderNumber='"
						+ entity.getPurchaseOrderNumber() + "'", false);
		for (int i = 0; i < l.size(); i++) {
			PurchaseOrderDetailEntity detailEntity = l.get(i);
			for (int j = 0; j < detailEntityList.size(); j++) {
				PurchaseOrderDetailEntity detailEntity2 = detailEntityList
						.get(j);
				if (detailEntity2.getPoAutoId() != detailEntity.getPoAutoId()
						&& detailEntity2 != null) {
					detailEntity.setPoAutoId(null);
					detailEntity.setDeletedFlag(true);
					entity.getPurchaseOrderDetailEntity().add(detailEntity);
				}
			}
		}

		entity.setDeletedFlag(false);
		return getJpaTemplate().merge(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(PurchaseOrderMasterEntity entity1) {
		if (entity1 == null) {
			throw new IllegalArgumentException("Cannot remove a null entity");
		}
		PurchaseOrderMasterEntity entity = getJpaTemplate().find(
				PurchaseOrderMasterEntity.class, entity1.getPoAutoId());
		entity.setDeletedFlag(true);

		List<PurchaseOrderDetailEntity> detailEntities = entity
				.getPurchaseOrderDetailEntity();
		for (PurchaseOrderDetailEntity e : detailEntities) {
			e.setDeletedFlag(true);
			e.setModifiedUserId(entity1.getModifiedUserId());

			// To update pending quantity in indent detail........
			/*
			 * try{
			 * indentDAO.updatePendingQtyInIndentDetail(e.getItemEntity().getItemId
			 * (), e.getIndentNumber(), e.getItemQuantity(),"delete");
			 * }catch(Exception ex){ ex.printStackTrace(); }
			 */

		}
		entity.setModifiedUserId(entity1.getModifiedUserId());
		getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PurchaseOrderMasterEntity> load() {
		return getJpaTemplate()
				.find("FROM PurchaseOrderMasterEntity e where e.deletedFlag=?1  ORDER BY e.poAutoId DESC",
						false);
	}

	@Override
	public List<PurchaseOrderMasterEntity> FindPurchaseOrderPagination(
			Integer next) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		Query query = entityManager
				.createQuery("FROM PurchaseOrderMasterEntity e WHERE e.deletedFlag=0 ORDER BY e.poAutoId DESC");
		query.setFirstResult(next);
		query.setMaxResults(15);
		List<PurchaseOrderMasterEntity> list = query.getResultList();
		entityManager.close();
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public <E> List<PurchaseOrderMasterEntity> findById(E id) {
		List<PurchaseOrderMasterEntity> list = getJpaTemplate()
				.find("FROM PurchaseOrderMasterEntity e WHERE e.poAutoId =?1 and e.deletedFlag=?2",
						id, false);
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public <E> List<PurchaseOrderMasterEntity> findByPurchaseOrderNumber(E id) {
		return getJpaTemplate()
				.find("FROM PurchaseOrderMasterEntity e WHERE e.purchaseOrderNumber =?1 and e.deletedFlag=?2",
						id, false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PurchaseOrderMasterEntity> search(String purchaseOrderNumber,
			Date fromDate, Date toDate, String supplierName, String itemName) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM PurchaseOrderMasterEntity e");
		boolean first = true;

		if (StringUtils.hasText(purchaseOrderNumber)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.purchaseOrderNumber like :purchaseOrderNumber");
			params.put("purchaseOrderNumber", "%" + purchaseOrderNumber.trim()
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

			hql.append("cast(e.purchaseOrderDate as date) >= '" + fromDate1
					+ "' AND cast(e.purchaseOrderDate as date) <= '" + toDate1
					+ "'");
			first = false;
		}

		// logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		if (StringUtils.hasText(supplierName)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.partyEntity.partyName like :partyName");
			params.put("partyName", "%" + supplierName.trim() + "%");
			first = false;
		}

		if (itemName != null && itemName.trim().length() > 0) {

			hql.append(first ? " where " : " and ");
			hql.append("e.purchaseOrderNumber IN (SELECT DISTINCT pe.purchaseOrderNumber FROM PurchaseOrderDetailEntity pe, ItemEntity ie WHERE pe.itemEntity.itemId=ie.itemId AND ie.itemName LIKE "
					+ "'%" + itemName + "%'" + " AND ie.deletedFlag=0)");
			// params.put("invoiceNumber",
			// "%"+"IN (SELECT DISTINCT invoice_number FROM t_bill_detail, m_item WHERE t_bill_detail.item_id=m_item.item_id AND m_item.item_name LIKE 'Copp%' AND m_item.deleted_flag=0)"+"%");
			first = false;
		}

		// logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		hql.append(first ? " where " : " and ");
		hql.append(" e.deletedFlag=0 ORDER BY e.poAutoId DESC");

		first = false;

		// logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	public List getNewSeriesNo(String finYear) {

		return getJpaTemplate()
				.find("SELECT max(e.purchaseOrderId),max(purchaseOrderDate) FROM PurchaseOrderMasterEntity e WHERE e.finYear=?1 and e.deletedFlag=0",
						finYear);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<PurchaseOrderDetailEntity> findByItemId(Integer itemId) {
		return getJpaTemplate()
				.find("FROM PurchaseOrderDetailEntity e WHERE e.itemEntity.itemId=?1 and e.deletedFlag=?2",
						itemId, false);

	}

	@Override
	@Transactional(readOnly = true)
	public List<PurchaseOrderMasterEntity> loadPoAccordingGrn() {
		return getJpaTemplate()
				.find("FROM PurchaseOrderMasterEntity pm,GrnDetailEntity gd where gd.  e.deletedFlag=?1  ORDER BY e.poAutoId DESC",
						false);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<PurchaseOrderDetailEntity> findByItemIdAndPo(Integer itemId,
			Integer poId) {
		return getJpaTemplate()
				.find("FROM PurchaseOrderDetailEntity e WHERE e.itemEntity.itemId=?1 and e.poAutoId=?2 and e.deletedFlag=?3",
						itemId, poId, false);

	}

	@Override
	public <E> List<PurchaseOrderMasterEntity> findBySupplierId(E id) {
		// TODO Auto-generated method stub
		return getJpaTemplate()
				.find("FROM PurchaseOrderMasterEntity e WHERE e.partyEntity.partyId =?1 and e.deletedFlag=?2 ORDER BY e.purchaseOrderNumber DESC",
						id, false);
	}

	@Override
	public List<PurchaseOrderDetailEntity> findByItemIdAndPoNumber(
			Integer itemId, String poNumber) {
		// TODO Auto-generated method stub
		return getJpaTemplate()
				.find("FROM PurchaseOrderDetailEntity e WHERE e.itemEntity.itemId=?1 and e.purchaseOrderNumber=?2 and e.deletedFlag=?3",
						itemId, poNumber, false);
	}

	@Override
	public List findByPurchaseOrderByIndentNumber(String indentNumber) {
		return getJpaTemplate()
				.find("FROM PurchaseOrderMasterEntity e WHERE e.indentNumber =?1 and e.deletedFlag=?2",
						indentNumber, false);
	}

	@Override
	public List<Double> findPurchaseQtyByItemIdAndIndentNumber(Integer itemId,
			String indentNumber) {
		List<Double> l = getJpaTemplate()
				.find("SELECT SUM(e.itemQuantity) FROM PurchaseOrderDetailEntity e WHERE e.itemEntity.itemId=?1 and e.indentNumber=?2 and e.deletedFlag=?3",
						itemId, indentNumber, false);
		return l;
	}

	@Override
	public List findByPurchaseOrderDetailByIndentNumberAndItemId(
			String indentNumber, Integer itemId) {
		return getJpaTemplate()
				.find("FROM PurchaseOrderDetailEntity e WHERE e.itemEntity.itemId=?1 and e.indentNumber =?2 and e.deletedFlag=?3",
						itemId, indentNumber, false);
	}

}
