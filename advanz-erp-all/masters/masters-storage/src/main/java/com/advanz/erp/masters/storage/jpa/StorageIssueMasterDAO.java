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
import com.advanz.erp.masters.entity.jpa.IssueDetailMasterEntity;
import com.advanz.erp.masters.entity.jpa.IssueMasterEntity;
import com.advanz.erp.masters.storage.IStorageIssueDAO;

@Component
public class StorageIssueMasterDAO extends JpaDaoSupport implements
		IStorageIssueDAO {
	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(IssueMasterEntity entity) {

		if (entity == null) {
			throw new IllegalArgumentException("Cannot create a null entity");
		}
		getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public IssueMasterEntity read(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("uid must not be null");
		}
		return getJpaTemplate().find(IssueMasterEntity.class, id);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public IssueMasterEntity update(IssueMasterEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot update a null entity");
		}
		// entity=getJpaTemplate().find(IssueMasterEntity.class,
		// entity.getIssueAutoId());
		List<IssueDetailMasterEntity> detailEntityList = entity
				.getIssueDetailMasterEntity();

		List<IssueDetailMasterEntity> l = getJpaTemplate().find(
				"FROM IssueDetailMasterEntity e where e.deletedFlag=?1  AND e.issueNumber='"
						+ entity.getIssueNumber() + "'", false);

		for (int i = 0; i < l.size(); i++) {
			IssueDetailMasterEntity detailEntity = l.get(i);
			for (int j = 0; j < detailEntityList.size(); j++) {
				IssueDetailMasterEntity detailEntity2 = detailEntityList.get(j);
				if (detailEntity2.getIssueAutoId() != detailEntity
						.getIssueAutoId()) {
					detailEntity.setIssueAutoId(null);
					detailEntity.setDeletedFlag(true);
					detailEntity.setModifiedUserId(entity.getModifiedUserId());
					entity.getIssueDetailMasterEntity().add(detailEntity);
				}
			}
		}
		entity.setDeletedFlag(false);
		return getJpaTemplate().merge(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(IssueMasterEntity entity1) {
		if (entity1 == null) {
			throw new IllegalArgumentException("Cannot remove a null entity");
		}
		IssueMasterEntity entity = getJpaTemplate().find(
				IssueMasterEntity.class, entity1.getIssueAutoId());
		entity.setDeletedFlag(true);
		List<IssueDetailMasterEntity> detailEntities = entity
				.getIssueDetailMasterEntity();
		for (IssueDetailMasterEntity e : detailEntities) {
			e.setDeletedFlag(true);
			e.setModifiedUserId(entity1.getModifiedUserId());
		}
		entity.setModifiedUserId(entity1.getModifiedUserId());
		getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<IssueMasterEntity> load() {
		return getJpaTemplate()
				.find("FROM IssueMasterEntity e where e.deletedFlag=?1  ORDER BY e.issueAutoId DESC",
						false);
	}

	@Override
	@Transactional(readOnly = true)
	public <E> List<IssueMasterEntity> findById(E id) {
		// logger.info("a service  >>>>>>>>>>>> : "+id);
		return getJpaTemplate()
				.find("FROM IssueMasterEntity e WHERE e.issueAutoId=?1 and e.deletedFlag=?2",
						id, false);
	}

	@Override
	@Transactional(readOnly = true)
	public <E> List<IssueMasterEntity> findByIssuesNumber(E id) {
		// logger.info("a service  >>>>>>>>>>>> : "+id);
		return getJpaTemplate()
				.find("FROM IssueMasterEntity e WHERE e.issueNumber=?1 and e.deletedFlag=?2",
						id, false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<IssueMasterEntity> search(String issueNumber, Date fromDate,
			Date toDate, String issueType, String itemName, String raisedBy) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM IssueMasterEntity e");
		boolean first = true;

		if (StringUtils.hasText(issueNumber)) {
			hql.append(first ? " where " : " and ");
			hql.append("issueNumber like :issueNumber");
			params.put("issueNumber", "%" + issueNumber.trim() + "%");
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

			hql.append("cast(e.issueDate as date) >= '" + fromDate1
					+ "' AND cast(e.issueDate as date) <= '" + toDate1 + "'");
			first = false;
		}
		if (itemName != null && itemName.trim().length() > 0) {
			hql.append(first ? " where " : " and ");
			hql.append("e.issueNumber IN (SELECT DISTINCT ise.issueNumber FROM IssueDetailMasterEntity ise, ItemEntity ie WHERE ise.itemEntity.itemId=ie.itemId AND ie.itemName LIKE "
					+ "'%" + itemName + "%'" + " AND ie.deletedFlag=0)");
			// params.put("invoiceNumber",
			// "%"+"IN (SELECT DISTINCT invoice_number FROM t_bill_detail, m_item WHERE t_bill_detail.item_id=m_item.item_id AND m_item.item_name LIKE 'Copp%' AND m_item.deleted_flag=0)"+"%");
			first = false;
		}

		if (issueType != null && issueType.trim().length() > 0) {
			hql.append(first ? " where " : " and ");
			hql.append("e.issueNumber IN (SELECT DISTINCT ise.issueNumber FROM IssueDetailMasterEntity ise WHERE ise.issueType='"
					+ issueType + "' AND ise.deletedFlag=0)");
			/*
			 * hql.append("issueType like :issueType"); params.put("issueType",
			 * "%"+issueType.trim()+"%");
			 */
			first = false;
		}
		if (StringUtils.hasText(raisedBy)) {
			hql.append(first ? " where " : " and ");
			hql.append("raisedBy like :raisedBy");
			params.put("raisedBy", "%" + raisedBy.trim() + "%");
			first = false;
		}
		hql.append(first ? " where " : " and ");
		hql.append(" e.deletedFlag=0 ORDER BY e.issueAutoId DESC");
		first = false;
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	public List	 getNewSeriesNo(String finYear) {
		return getJpaTemplate()
				.find("SELECT max(e.issueId),max(issueDate) FROM IssueMasterEntity e WHERE e.finYear=?1 and e.deletedFlag=0 ",
						finYear);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<IssueDetailMasterEntity> findByItemId(Integer itemId) {
		return getJpaTemplate()
				.find("FROM IssueDetailMasterEntity e WHERE e.itemEntity.itemId=?1 and e.deletedFlag=?2",
						itemId, false);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<IssueMasterEntity> findIssueNumber(Integer issueAutoId) {
		return getJpaTemplate()
				.find("FROM IssueMasterEntity e WHERE e.issueAutoId=?1 and e.deletedFlag=?2",
						issueAutoId, false);

	}

	@Override
	public List<IssueMasterEntity> findMaterialIssuePagination(Integer next) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		Query query = entityManager
				.createQuery("FROM IssueMasterEntity e WHERE e.deletedFlag=0 ORDER BY e.issueAutoId DESC");
		query.setFirstResult(next);
		query.setMaxResults(15);
		List<IssueMasterEntity> list = query.getResultList();
		entityManager.close();
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public IssueDetailMasterEntity updateIssueDetail(
			IssueDetailMasterEntity entity) {

		return getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<IssueDetailMasterEntity> findByIssueNoAndItemId(
			String issueNumber, Integer itemId) {
		return getJpaTemplate()
				.find("FROM IssueDetailMasterEntity e WHERE e.itemEntity.itemId=?1 and e.issueNumber='"
						+ issueNumber + "' and e.deletedFlag=?2", itemId, false);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List findIssueDateByIssueNo(String issueNumber) {
		return getJpaTemplate().find(
				"SELECT e.issueDate FROM IssueMasterEntity e WHERE e.issueNumber='"
						+ issueNumber + "' and e.deletedFlag=?1", false);
	}
}
