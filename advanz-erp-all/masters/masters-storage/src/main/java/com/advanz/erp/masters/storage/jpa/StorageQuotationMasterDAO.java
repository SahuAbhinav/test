package com.advanz.erp.masters.storage.jpa;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import com.advanz.erp.masters.entity.jpa.PurchaseOrderMasterEntity;
import com.advanz.erp.masters.entity.jpa.QuotationDetailEntity;
import com.advanz.erp.masters.entity.jpa.QuotationMasterEntity;
import com.advanz.erp.masters.storage.IStorageQuotationMasterDAO;

@Component
public class StorageQuotationMasterDAO extends JpaDaoSupport implements
		IStorageQuotationMasterDAO {
	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(QuotationMasterEntity entity) {

		if (entity == null) {
			throw new IllegalArgumentException("Cannot create a null entity");
		}
		getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public QuotationMasterEntity read(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("uid must not be null");
		}
		return getJpaTemplate().find(QuotationMasterEntity.class, id);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public QuotationMasterEntity update(QuotationMasterEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot update a null entity");
		}
		List<QuotationDetailEntity> detailDeletedList = getJpaTemplate()
				.find("FROM QuotationDetailEntity e where e.deletedFlag=?1 and e.quotationAutoId=?2",
						true, entity.getQuotationAutoId());
		if (detailDeletedList != null) {
			for (QuotationDetailEntity e : detailDeletedList) {
				e.setModifiedUserId(entity.getModifiedUserId());
				entity.getQuotationDetailEntities().add(e);
			}
		}
		return getJpaTemplate().merge(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(QuotationMasterEntity entity) {
		if (entity != null) {
			entity = getJpaTemplate().find(QuotationMasterEntity.class,
					entity.getQuotationAutoId());
		}

		if (entity == null) {
			throw new IllegalArgumentException("Cannot remove a null entity");
		}

		List<Integer> soList = getJpaTemplate()
				.find("Select salesOrderAutoId FROM SalesOrderEntity e WHERE e.quotationNumber =?1",
						entity.getQuotationNumber());
		if (soList != null && soList.size() > 0) {
			throw new IllegalArgumentException(
					"Quotation can not be Removed,Used in SalesOrder");
		}

		entity.setDeletedFlag(true);
		List<QuotationDetailEntity> detailEntities = entity
				.getQuotationDetailEntities();
		for (QuotationDetailEntity e : detailEntities) {
			e.setDeletedFlag(true);
			e.setModifiedUserId(entity.getModifiedUserId());
		}

		getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<QuotationMasterEntity> load() {
		// return
		// getJpaTemplate().find("FROM QuotationMasterEntity e where e.deletedFlag=?1  ORDER BY e.quotationNumber",false);
		return getJpaTemplate()
				.find("FROM QuotationMasterEntity e where e.deletedFlag=?1  ORDER BY e.quotationAutoId DESC",
						false);

	}

	@Override
	public List<QuotationMasterEntity> FindQuotationPagination(Integer next) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		Query query = entityManager
				.createQuery("FROM QuotationMasterEntity e WHERE e.deletedFlag=0 ORDER BY e.quotationAutoId DESC");
		query.setFirstResult(next);
		query.setMaxResults(15);
		List<QuotationMasterEntity> list = query.getResultList();
		entityManager.close();
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public <E> List<QuotationMasterEntity> findById(E id) {
		return getJpaTemplate()
				.find("FROM QuotationMasterEntity e WHERE e.quotationAutoId =?1 and e.deletedFlag=?2",
						id, false);
	}

	@Override
	@Transactional(readOnly = true)
	public <E> List<QuotationMasterEntity> findByQutationNumber(E id) {
		return getJpaTemplate()
				.find("FROM QuotationMasterEntity e WHERE e.quotationNumber =?1 and e.deletedFlag=?2",
						id, false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<QuotationMasterEntity> search(String quotationNumber,
			Date fromDate, Date toDate, String partyName, String itemName) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM QuotationMasterEntity e");
		boolean first = true;

		if (StringUtils.hasText(quotationNumber)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.quotationNumber like :quotationNumber");
			params.put("quotationNumber", "%" + quotationNumber.trim() + "%");
			first = false;
		}

		if (fromDate != null || toDate != null) {
			hql.append(first ? " where " : " and ");
			String fromDate1 = "";
			if (fromDate != null) {
				fromDate1 = getDate(fromDate);
			}
			String toDate1 = null;
			if (toDate != null) {
				toDate1 = getDate(toDate);
			}

			hql.append("e.quotationDate >= '" + fromDate1
					+ "' AND e.quotationDate <= '" + toDate1 + "'");
			first = false;
		}

		if (StringUtils.hasText(partyName)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.partyEntity.partyName like :partyName");
			params.put("partyName", "%" + partyName.trim() + "%");
			first = false;
		}

		if (itemName != null && itemName.trim().length() > 0) {
			hql.append(first ? " where " : " and ");
			hql.append("e.quotationNumber IN (SELECT DISTINCT qde.quotationNumber FROM QuotationDetailEntity qde, ItemEntity ie WHERE qde.itemEntity.itemId=ie.itemId AND ie.itemName LIKE "
					+ "'%" + itemName + "%'" + " AND ie.deletedFlag=0)");
			// params.put("invoiceNumber",
			// "%"+"IN (SELECT DISTINCT invoice_number FROM t_bill_detail, m_item WHERE t_bill_detail.item_id=m_item.item_id AND m_item.item_name LIKE 'Copp%' AND m_item.deleted_flag=0)"+"%");
			first = false;
		}
		hql.append(first ? " where " : " and ");
		// hql.append(" e.deletedFlag=0 ORDER BY e.modifiedDate DESC");
		hql.append(" e.deletedFlag=0 ORDER BY e.quotationAutoId DESC");
		first = false;

		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	public List getNewSeriesNo(String finYear) {
		return getJpaTemplate()
				.find("SELECT max(e.quotationId),max(quotationDate) FROM QuotationMasterEntity e WHERE e.finYear=?1 and e.deletedFlag=0",
						finYear);
	}

	@Override
	public boolean isInUsed(Integer id) {
		List<String> list = getJpaTemplate()
				.find("Select quotationNumber FROM QuotationMasterEntity e WHERE e.quotationAutoId =?1",
						id);
		if (list != null && list.size() > 0) {
			List<Integer> soList = getJpaTemplate()
					.find("Select salesOrderAutoId FROM SalesOrderMasterEntity e WHERE e.deletedFlag=0 and e.quotationNumber =?1",
							list.get(0));
			if (soList != null && soList.size() > 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<QuotationDetailEntity> findByItemId(Integer itemId) {
		return getJpaTemplate()
				.find("FROM QuotationDetailEntity e WHERE e.itemEntity.itemId=?1 and e.deletedFlag=?2",
						itemId, false);

	}

	private String getDate(Date date) {

		String s2 = "";
		if (date != null) {
			try {
				DateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd'T'HH:mm:ss.SSSZ");
				String s = df.format(date);
				Date d = (new SimpleDateFormat("yyyy-MM-dd")).parse(s);
				s2 = (new SimpleDateFormat("yyyy-MM-dd")).format(d);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return s2;
	}
}
