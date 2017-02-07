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
import com.advanz.erp.masters.entity.jpa.IndentDetailEntity;
import com.advanz.erp.masters.entity.jpa.IndentMasterEntity;
import com.advanz.erp.masters.entity.jpa.MastersEntity;
import com.advanz.erp.masters.storage.IStorageIndentDAO;

@Component
public class StorageIndentMasterDAO extends JpaDaoSupport implements
		IStorageIndentDAO {
	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(IndentMasterEntity entity) {

		if (entity == null) {
			throw new IllegalArgumentException("Cannot create a null entity");
		}
		getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public IndentMasterEntity read(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("uid must not be null");
		}
		return getJpaTemplate().find(IndentMasterEntity.class, id);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public IndentMasterEntity update(IndentMasterEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot update a null entity");
		}
		// entity=getJpaTemplate().find(IssueMasterEntity.class,
		// entity.getIssueAutoId());
		List<IndentDetailEntity> detailEntityList = entity
				.getIndentDetailEntity();

		List<IndentDetailEntity> l = getJpaTemplate().find(
				"FROM IndentDetailEntity e where e.deletedFlag=?1  AND e.indentNumber='"
						+ entity.getIndentNumber() + "'", false);

		for (int i = 0; i < l.size(); i++) {
			IndentDetailEntity detailEntity = l.get(i);
			for (int j = 0; j < detailEntityList.size(); j++) {
				IndentDetailEntity detailEntity2 = detailEntityList.get(j);
				if (detailEntity2.getIndentAutoId() != detailEntity
						.getIndentAutoId()) {
					detailEntity.setIndentAutoId(null);
					detailEntity.setDeletedFlag(true);
					detailEntity.setModifiedUserId(entity.getModifiedUserId());
					entity.getIndentDetailEntity().add(detailEntity);
				}
			}
		}
		entity.setDeletedFlag(false);
		return getJpaTemplate().merge(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(IndentMasterEntity entity1) {
		if (entity1 == null) {
			throw new IllegalArgumentException("Cannot remove a null entity");
		}
		IndentMasterEntity entity = getJpaTemplate().find(
				IndentMasterEntity.class, entity1.getIndentAutoId());
		entity.setDeletedFlag(true);
		List<IndentDetailEntity> detailEntities = entity
				.getIndentDetailEntity();
		for (IndentDetailEntity e : detailEntities) {
			e.setDeletedFlag(true);
			e.setModifiedUserId(entity1.getModifiedUserId());
		}
		entity.setModifiedUserId(entity1.getModifiedUserId());
		getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<IndentMasterEntity> load() {
		return getJpaTemplate()
				.find("FROM IndentMasterEntity e where e.deletedFlag=?1  ORDER BY e.indentAutoId DESC",
						false);
	}

	@Override
	@Transactional(readOnly = true)
	public <E> List<IndentMasterEntity> findById(E id) {
		// logger.info("a service  >>>>>>>>>>>> : "+id);
		return getJpaTemplate()
				.find("FROM IndentMasterEntity e WHERE e.indentAutoId=?1 and e.deletedFlag=?2",
						id, false);
	}

	@Override
	@Transactional(readOnly = true)
	public <E> List<IndentMasterEntity> findByIndentNumber(E id) {
		// logger.info("a service  >>>>>>>>>>>> : "+id);
		return getJpaTemplate()
				.find("FROM IndentMasterEntity e WHERE e.indentNumber=?1 and e.deletedFlag=?2",
						id, false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<IndentMasterEntity> search(String issueNumber, Date fromDate,
			Date toDate, String itemName, String raisedBy,
			String departementName) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM IndentMasterEntity e");
		boolean first = true;

		if (StringUtils.hasText(issueNumber)) {
			hql.append(first ? " where " : " and ");
			hql.append("indentNumber like :indentNumber");
			params.put("indentNumber", "%" + issueNumber.trim() + "%");
			first = false;
		}
		if (StringUtils.hasText(raisedBy)) {
			hql.append(first ? " where " : " and ");
			hql.append("raisedBy like :raisedBy");
			params.put("raisedBy", "%" + raisedBy.trim() + "%");
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

			hql.append("cast(e.indentDate as date) >= '" + fromDate1
					+ "' AND cast(e.indentDate as date) <= '" + toDate1 + "'");
			first = false;
		}

		if (itemName != null && itemName.trim().length() > 0) {
			hql.append(first ? " where " : " and ");
			hql.append("e.indentNumber IN (SELECT DISTINCT ise.indentNumber FROM IndentDetailEntity ise, ItemEntity ie WHERE ise.itemEntity.itemId=ie.itemId AND ie.itemName LIKE "
					+ "'%" + itemName + "%'" + " AND ie.deletedFlag=0)");
			// params.put("invoiceNumber",
			// "%"+"IN (SELECT DISTINCT invoice_number FROM t_bill_detail, m_item WHERE t_bill_detail.item_id=m_item.item_id AND m_item.item_name LIKE 'Copp%' AND m_item.deleted_flag=0)"+"%");
			first = false;
		}
		System.out.println("DEPARTMENT NAME IS :::::::" + departementName);
		if (departementName != null && departementName.trim().length() > 0) {
			hql.append(first ? " where " : " and ");
			hql.append("e.indentNumber IN (SELECT DISTINCT ise.indentNumber FROM IndentDetailEntity ise, MastersEntity ie WHERE ise.departmentId=ie.mastersId AND ie.name LIKE "
					+ "'%" + departementName + "%'" + " AND ie.deletedFlag=0)");
			// params.put("invoiceNumber",
			// "%"+"IN (SELECT DISTINCT invoice_number FROM t_bill_detail, m_item WHERE t_bill_detail.item_id=m_item.item_id AND m_item.item_name LIKE 'Copp%' AND m_item.deleted_flag=0)"+"%");
			first = false;
		}

		hql.append(first ? " where " : " and ");
		hql.append(" e.deletedFlag=0 ORDER BY e.indentAutoId DESC");

		first = false;

		// logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	public List getNewSeriesNo(String finYear) {
		return getJpaTemplate()
				.find("SELECT max(e.indentId),max(e.indentDate) FROM IndentMasterEntity e WHERE e.finYear=?1 and e.deletedFlag=0 ",
						finYear);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<IndentMasterEntity> findIndentNumber(Integer issueAutoId) {
		return getJpaTemplate()
				.find("FROM IndentMasterEntity e WHERE e.indentAutoId=?1 and e.deletedFlag=?2",
						issueAutoId, false);

	}

	@Override
	public List<IndentMasterEntity> findIndentPagination(Integer next) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		Query query = entityManager
				.createQuery("FROM IndentMasterEntity e WHERE e.deletedFlag=0 ORDER BY e.indentAutoId DESC");
		query.setFirstResult(next);
		query.setMaxResults(15);
		List<IndentMasterEntity> list = query.getResultList();
		entityManager.close();
		return list;
	}

	// To Update pending quantity from Purchase Order
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<IndentDetailEntity> updatePendingQtyInIndentDetail(
			Integer itemId, String indentNumber, Double quantity,
			String operation) {

		List<IndentDetailEntity> l = getJpaTemplate().find(
				"FROM IndentDetailEntity e where e.deletedFlag=?1 AND e.itemEntity.itemId='"
						+ itemId + "'  AND e.indentNumber='" + indentNumber
						+ "'", false);
		IndentDetailEntity detailEntity = null;
		if (l != null && l.size() > 0) {
			detailEntity = l.get(0);
			if ("delete".equalsIgnoreCase(operation)) {
				detailEntity.setPendingQty(detailEntity.getPendingQty()
						+ quantity);
			} else {
				if ("update".equalsIgnoreCase(operation)) {
					detailEntity.setPendingQty(detailEntity.getIndentQty()
							- quantity);
				} else {
					detailEntity.setPendingQty(detailEntity.getPendingQty()
							- quantity);
				}
			}
		}
		getJpaTemplate().merge(detailEntity);
		return null;
	}
	//
}
