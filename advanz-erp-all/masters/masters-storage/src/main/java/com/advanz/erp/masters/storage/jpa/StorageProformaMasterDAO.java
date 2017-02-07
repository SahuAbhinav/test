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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.common.DataUtility;
import com.advanz.erp.masters.entity.jpa.ProformaMasterEntity;
import com.advanz.erp.masters.storage.IStorageProformaMasterDAO;

public class StorageProformaMasterDAO extends JpaDaoSupport implements
		IStorageProformaMasterDAO {
	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(ProformaMasterEntity entity) {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new IllegalArgumentException("Cannot create a null entity");
		}
		getJpaTemplate().persist(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public ProformaMasterEntity read(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("uid must not be null");
		}
		return getJpaTemplate().find(ProformaMasterEntity.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ProformaMasterEntity update(ProformaMasterEntity entity) {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new IllegalArgumentException("Cannot update a null entity");
		}
		entity.setDeletedFlag(false);
		return getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(ProformaMasterEntity entity) {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new IllegalArgumentException("Cannot delete a null entity");
		}
		entity.setDeletedFlag(true);
		getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProformaMasterEntity> load() {
		// TODO Auto-generated method stub
		return getJpaTemplate()
				.find("FROM ProformaMasterEntity e  where e.deletedFlag=0 ORDER BY e.invoiceAutoId DESC");

	}

	@Override
	@Transactional(readOnly = true)
	public <E> List<ProformaMasterEntity> findById(Integer invoiceAutoId) {
		// TODO Auto-generated method stub
		return getJpaTemplate().find(
				"FROM ProformaMasterEntity e WHERE e.invoiceAutoId = '"
						+ invoiceAutoId + "' ORDER BY e.modifiedDate DESC");
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProformaMasterEntity> getLastByInvoiceId() {
		// TODO Auto-generated method stub
		return getJpaTemplate()
				.find("FROM ProformaMasterEntity e   where e.deletedFlag=0 ORDER BY e.invoiceId DESC LIMIT 1");
	}

	@Override
	public <E> List<ProformaMasterEntity> findByInvoiceSeries(
			String invoiceSeries) {
		return getJpaTemplate().find(
				"FROM ProformaMasterEntity e WHERE e.deletedFlag=0 and e.invoiceNumber = "
						+ "'" + invoiceSeries + "'");
	}

	@Override
	public <E> List<ProformaMasterEntity> findByExsiceInvoiceNo(
			String exciseInvoiceSeries) {
		return getJpaTemplate().find(
				"FROM ProformaMasterEntity e WHERE e.deletedFlag=0 and e.exciseInvoiceNo = "
						+ "'" + exciseInvoiceSeries + "'");
	}

	@Override
	public List<ProformaMasterEntity> search(Date fromDate, Date toDate,
			String partyName, String invoiceNumber, String itemName) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM ProformaMasterEntity e");
		boolean first = true;

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

			hql.append("cast(e.invoiceDate as date) >= '" + fromDate1
					+ "' AND cast(e.invoiceDate as date) <= '" + toDate1 + "'");
			first = false;
		}

		if (invoiceNumber != null) {
			hql.append(first ? " where " : " and ");
			hql.append("e.invoiceNumber like :invoiceNumber");
			params.put("invoiceNumber", "%" + invoiceNumber + "%");
			first = false;
		}

		if (partyName != null && partyName.trim().length() > 0) {
			hql.append(first ? " where " : " and ");
			hql.append("e.partyEntity.partyName like :partyName");
			params.put("partyName", "%" + partyName + "%");
			first = false;
		}

		if (itemName != null && itemName.trim().length() > 0) {
			hql.append(first ? " where " : " and ");
			hql.append("e.invoiceNumber IN (SELECT DISTINCT be.invoiceNumber FROM ProformaDetailEntity be, ItemEntity ie WHERE be.itemId=ie.itemId AND ie.itemName LIKE "
					+ "'%" + itemName + "%'" + " AND ie.deletedFlag=0)");
			// params.put("invoiceNumber",
			// "%"+"IN (SELECT DISTINCT invoice_number FROM t_bill_detail, m_item WHERE t_bill_detail.item_id=m_item.item_id AND m_item.item_name LIKE 'Copp%' AND m_item.deleted_flag=0)"+"%");
			first = false;
		}

		logger.info("HQL >>>>>>>>>>>> : " + hql.toString());
		hql.append(first ? " where " : " and ");
		hql.append(" e.deletedFlag=0  ORDER BY e.modifiedDate DESC ");
		first = false;
		logger.info("HQL >>>>>>>>>>>> : " + hql.toString());
		List l = getJpaTemplate().findByNamedParams(hql.toString(), params);
		System.out.println("LIST SIZE :::::::" + l.size());
		return l;
	}

	@Override
	public List<ProformaMasterEntity> findByEmployeeId(Integer empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProformaMasterEntity> getMaxInvoiceId() {
		// TODO Auto-generated method stub
		return getJpaTemplate()
				.find("FROM ProformaMasterEntity e   where e.deletedFlag=0 ORDER BY e.invoiceAutoId DESC LIMIT 1");
	}

	@Override
	public List<ProformaMasterEntity> getFinacialYear() {
		// TODO Auto-generated method stub
		return getJpaTemplate()
				.find("SELECT finyr FROM  ProformaMasterEntity e  where e.deletedFlag=0 GROUP BY e.finyr");
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProformaMasterEntity> getProformaListWithPagination(
			Integer index) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		Query query = entityManager
				.createQuery("FROM ProformaMasterEntity e where e.deletedFlag=0 ORDER BY e.invoiceAutoId DESC");
		query.setFirstResult(index);
		query.setMaxResults(13);

		List<ProformaMasterEntity> list = query.getResultList();
		entityManager.close();
		return list;

	}

	@Override
	public List getNewSeriesNo(String finYear) {
		return getJpaTemplate()
				.find("SELECT max(e.invoiceId),max(e.invoiceDate) FROM ProformaMasterEntity e WHERE e.finyr=?1 and e.deletedFlag=0 ",
						finYear);
	}

}
