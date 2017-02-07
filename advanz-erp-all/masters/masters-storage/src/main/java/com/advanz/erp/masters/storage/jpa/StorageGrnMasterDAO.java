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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.common.DataUtility;
import com.advanz.erp.masters.entity.jpa.GrnDetailEntity;
import com.advanz.erp.masters.entity.jpa.GrnMasterEntity;
import com.advanz.erp.masters.storage.IStorageGrnMasterDAO;

@Component
public class StorageGrnMasterDAO extends JpaDaoSupport implements
		IStorageGrnMasterDAO {
	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(GrnMasterEntity entity) {

		if (entity == null) {
			throw new IllegalArgumentException("Cannot create a null entity");
		}
		List<GrnDetailEntity> detailEntityList = entity.getGrnDetailEntity();
		for (int j = 0; j < detailEntityList.size(); j++) {
			GrnDetailEntity detailEntity2 = detailEntityList.get(j);
			if (detailEntity2.getGrnApprovalFlag() != null
					&& detailEntity2.getGrnApprovalFlag() > 0) {
				entity.setAproved(detailEntity2.getGrnApprovalFlag());
				detailEntity2.setAprovedDate(new Date());
			}
		}
		getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public GrnMasterEntity read(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("uid must not be null");
		}
		return getJpaTemplate().find(GrnMasterEntity.class, id);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public GrnMasterEntity update(GrnMasterEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot update a null entity");
		}

		List<GrnDetailEntity> detailEntityList = entity.getGrnDetailEntity();
		List<GrnDetailEntity> l = getJpaTemplate().find(
				"FROM GrnDetailEntity e where e.deletedFlag=?1  AND e.grnNumber='"
						+ entity.getGrnNumber() + "'", false);
		for (int i = 0; i < l.size(); i++) {
			GrnDetailEntity detailEntity = l.get(i);
			for (int j = 0; j < detailEntityList.size(); j++) {
				GrnDetailEntity detailEntity2 = detailEntityList.get(j);
				if (detailEntity2.getGrnApprovalFlag() != null
						&& detailEntity2.getGrnApprovalFlag() > 0) {
					entity.setAproved(detailEntity2.getGrnApprovalFlag());
					entity.setModifiedUserId(entity.getModifiedUserId());
					detailEntity2.setAprovedDate(new Date());
				}

				if (detailEntity2.getGrnAutoId() != detailEntity.getGrnAutoId()) {
					detailEntity.setGrnAutoId(null);
					detailEntity.setDeletedFlag(true);
					entity.getGrnDetailEntity().add(detailEntity);
					entity.setModifiedUserId(entity.getModifiedUserId());
				}
			}
		}

		entity.setDeletedFlag(false);
		return getJpaTemplate().merge(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(GrnMasterEntity entity1) {
		if (entity1 == null) {
			throw new IllegalArgumentException("Cannot remove a null entity");
		}
		GrnMasterEntity entity = getJpaTemplate().find(GrnMasterEntity.class,
				entity1.getGrnAutoId());
		entity.setDeletedFlag(true);
		List<GrnDetailEntity> detailEntities = entity.getGrnDetailEntity();
		for (GrnDetailEntity e : detailEntities) {
			e.setDeletedFlag(true);
			e.setModifiedUserId(entity1.getModifiedUserId());
		}
		entity.setModifiedUserId(entity1.getModifiedUserId());
		getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GrnMasterEntity> load() {
		return getJpaTemplate()
				.find("FROM GrnMasterEntity e where e.deletedFlag=?1  ORDER BY e.grnAutoId DESC",
						false);
	}

	@Override
	@Transactional(readOnly = true)
	public <E> List<GrnMasterEntity> findById(E id) {
		return getJpaTemplate()
				.find("FROM GrnMasterEntity e WHERE e.grnAutoId =?1 and e.deletedFlag=?2",
						id, false);
	}

	@Override
	@Transactional(readOnly = true)
	public <E> List<GrnMasterEntity> findByGrnNumber(E id) {
		return getJpaTemplate()
				.find("FROM GrnMasterEntity e WHERE e.grnNumber =?1 and e.deletedFlag=?2",
						id, false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GrnMasterEntity> search(String grnNumber, Date fromDate,
			Date toDate, String supplierName, String itemName,
			String supplierBillNo) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM GrnMasterEntity e");
		boolean first = true;

		if (StringUtils.hasText(grnNumber)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.grnNumber like :grnNumber");
			params.put("grnNumber", "%" + grnNumber.trim() + "%");
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

			hql.append("cast(e.grnDate as date) >= '" + fromDate1
					+ "' AND cast(e.grnDate as date) <= '" + toDate1 + "'");
			first = false;
		}

		// logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		if (StringUtils.hasText(supplierName)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.partyEntity.partyName like :partyName");
			params.put("partyName", "%" + supplierName.trim() + "%");
			first = false;
		}

		if (StringUtils.hasText(supplierBillNo)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.supplierBillNo like :supplierBillNo");
			params.put("supplierBillNo", "%" + supplierBillNo.trim() + "%");
			first = false;
		}

		if (itemName != null && itemName.trim().length() > 0) {
			hql.append(first ? " where " : " and ");
			hql.append("e.grnNumber IN (SELECT DISTINCT ge.grnNumber FROM GrnDetailEntity ge, ItemEntity ie WHERE ge.itemEntity.itemId=ie.itemId AND ie.itemName LIKE "
					+ "'%" + itemName + "%'" + " AND ie.deletedFlag=0)");
			// params.put("invoiceNumber",
			// "%"+"IN (SELECT DISTINCT invoice_number FROM t_bill_detail, m_item WHERE t_bill_detail.item_id=m_item.item_id AND m_item.item_name LIKE 'Copp%' AND m_item.deleted_flag=0)"+"%");
			first = false;
		}

		// logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());

		hql.append(first ? " where " : " and ");
		hql.append(" e.deletedFlag=0 ORDER BY e.grnAutoId DESC");
		first = false;

		// logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	public List getNewSeriesNo(String finYear) {

		return getJpaTemplate()
				.find("SELECT max(e.grnId),max(grnDate) FROM GrnMasterEntity e WHERE e.finYear=?1 and e.deletedFlag=0 ",
						finYear);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<GrnDetailEntity> findByItemId(Integer itemId) {
		return getJpaTemplate()
				.find("FROM GrnDetailEntity e WHERE e.itemEntity.itemId=?1 and e.deletedFlag=?2",
						itemId, false);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<GrnMasterEntity> findByPoId(Integer poId) {
		return getJpaTemplate()
				.find("FROM GrnMasterEntity e WHERE e.purchaseOrderEntity.poAutoId=?1 and e.deletedFlag=?2",
						poId, false);
	}

	@Override
	public Date getMaxDate() {
		Date date = null;
		List list = getJpaTemplate()
				.find("SELECT max(e.grnDate) FROM GrnMasterEntity e WHERE  e.deletedFlag=?1",
						false);
		if (list != null && list.size() > 0) {
			date = (Date) list.get(0);
		}
		return date;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<GrnMasterEntity> findForGrnNumber(Integer grnAutoId) {
		return getJpaTemplate()
				.find("FROM GrnMasterEntity e WHERE e.grnAutoId=?1 and e.deletedFlag=?2",
						grnAutoId, false);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<GrnMasterEntity> findGrnByPoId(Integer poId) {
		List<GrnMasterEntity> list2 = getJpaTemplate()
				.find("FROM GrnMasterEntity e WHERE e.purchaseOrderEntity.poAutoId=?1 and e.deletedFlag=?2",
						poId, false);
		return list2;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<GrnMasterEntity> findAllPoNo() {
		List<GrnMasterEntity> list2 = getJpaTemplate()
				.find("FROM GrnMasterEntity e WHERE e.purchaseOrderEntity.poAutoId>0 and e.deletedFlag=?1",
						false);
		return list2;
	}

	@Override
	public List getEmailAlertData(String date) {
		List list = new ArrayList();
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		/*
		 * Object object = (Object)entityManager.createNativeQuery(
		 * "SELECT a.q AS grn_made_on_today, b.q AS grn_pending_till_date, c.q AS pending_grn_having_more_than_four_days FROM (SELECT COUNT(m.grn_number) AS q FROM t_grn_mast m WHERE CAST(m.created_date AS DATE) = '"
		 * +date+
		 * "' AND m.deleted_flag=0) AS a,(SELECT COUNT(DISTINCT m.grn_number) AS q FROM t_grn_mast m, t_grn_detail d WHERE m.grn_number=d.grn_number AND m.deleted_flag=0 AND d.deleted_flag=0 AND (d.grn_approval_flag IS NULL OR d.grn_approval_flag =0)) AS b,(SELECT COUNT(DISTINCT m.grn_number) AS q FROM t_grn_mast m, t_grn_detail d WHERE m.grn_number=d.grn_number AND m.deleted_flag=0 AND d.deleted_flag=0 AND (d.grn_approval_flag IS NULL OR d.grn_approval_flag =0) AND m.created_date < DATE_SUB('"
		 * +date+"', INTERVAL 4 DAY)) AS c").getSingleResult();
		 */
		Object object = (Object) entityManager
				.createNativeQuery(
						"SELECT a.q AS grn_made_on_today, b.q AS grn_pending_till_date, c.q AS pending_grn_having_more_than_four_days FROM (SELECT COUNT(m.grn_number) AS q FROM t_grn_mast m WHERE CAST(m.created_date AS DATE) = CURRENT_DATE AND m.deleted_flag=0) AS a,(SELECT COUNT(DISTINCT m.grn_number) AS q FROM t_grn_mast m, t_grn_detail d WHERE m.grn_number=d.grn_number AND m.deleted_flag=0 AND d.deleted_flag=0 AND (d.grn_approval_flag IS NULL OR d.grn_approval_flag =0)) AS b,(SELECT COUNT(DISTINCT m.grn_number) AS q FROM t_grn_mast m, t_grn_detail d WHERE m.grn_number=d.grn_number AND m.deleted_flag=0 AND d.deleted_flag=0 AND (d.grn_approval_flag IS NULL OR d.grn_approval_flag =0) AND m.created_date < DATE_SUB(CURRENT_DATE, INTERVAL 4 DAY)) AS c")
				.getSingleResult();
		list.add(object);
		entityManager.close();
		return list;
	}

	@Override
	public List<GrnMasterEntity> FindGrnMasterPagination(Integer next) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		Query query = entityManager
				.createQuery("FROM GrnMasterEntity e WHERE e.deletedFlag=0 ORDER BY e.grnAutoId DESC");
		query.setFirstResult(next);
		query.setMaxResults(15);
		List<GrnMasterEntity> list = query.getResultList();
		entityManager.close();
		return list;
	}

	@Override
	public Double getPuchaseRateByIssueItemId(Integer itemId) {
		List list = getJpaTemplate()
				.find("SELECT  MAX(e.sno) FROM GrnDetailEntity e WHERE e.itemEntity.itemId='"
						+ itemId
						+ "' AND e.grnApprovalFlag=1 AND e.deletedFlag=?1 ORDER BY e.sno DESC",
						false);
		Integer sno = 0;
		if (list != null && list.size() > 0) {
			sno = (Integer) list.get(0);
		}
		Double purchseRate = 0.0;
		if (sno != null && sno > 0) {
			List list1 = getJpaTemplate().find(
					"SELECT e.purchaseRate FROM GrnDetailEntity e WHERE e.sno='"
							+ sno
							+ "' AND e.grnApprovalFlag=1 AND e.deletedFlag=?1",
					false);
			if (list1 != null && list1.size() > 0) {
				purchseRate = (Double) list1.get(0);
			}
		}

		return purchseRate;
	}
	
	public <E> List<GrnMasterEntity> findByDateItemId(Date toDate ,int itemId) {
		return getJpaTemplate()
				.find("FROM GrnMasterEntity e WHERE e.grnNumber =?1 and e.deletedFlag=?2",
						itemId, false);
	}

	@Override
	public List getLastGrnRateForWeightedAvg(Date date, Integer itemId) {
		return getJpaTemplate()
				.find("SELECT e.purchaseRate FROM GrnDetailEntity e WHERE e.creationDate<=?1 e.itemEntity.itemId =?2 and e.deletedFlag=?3",
						date,itemId, false);
	}

}
