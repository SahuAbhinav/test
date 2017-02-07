package com.advanz.erp.masters.storage.jpa;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.common.DataUtility;
import com.advanz.erp.masters.entity.jpa.DispatchMasterEntity;
import com.advanz.erp.masters.storage.IStorageDispatchMasterDAO;

public class StorageDispatchMasterDAO extends JpaDaoSupport implements
		IStorageDispatchMasterDAO {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(DispatchMasterEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot create a null entity");
		}
		entity.setDeletedFlag(false);
		getJpaTemplate().persist(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public DispatchMasterEntity read(Integer dispatchAutoId) {
		if (dispatchAutoId == null) {
			throw new IllegalArgumentException("uid must not be null");
		}
		return getJpaTemplate()
				.find(DispatchMasterEntity.class, dispatchAutoId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public DispatchMasterEntity update(DispatchMasterEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot update a null entity");
		}
		entity.setDeletedFlag(false);
		return getJpaTemplate().merge(entity);
	}

	@Override
	public <E> List<DispatchMasterEntity> findByDispatchNumber(
			String dispatchNumber) {
		return getJpaTemplate().find(
				"FROM DispatchMasterEntity e WHERE e.deletedFlag=0 and e.dispatchNumber = "
						+ "'" + dispatchNumber + "'");
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(DispatchMasterEntity entity1) {
		if (entity1 == null) {
			throw new IllegalArgumentException("Cannot remove a null entity");
		}
		DispatchMasterEntity entity = getJpaTemplate().find(
				DispatchMasterEntity.class, entity1.getDispatchAutoId());
		entity.setDeletedFlag(true);
		entity.setModifiedUserId(entity1.getModifiedUserId());
		getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<DispatchMasterEntity> load() {
		// TODO Auto-generated method stub
		return getJpaTemplate()
				.find("FROM DispatchMasterEntity e where e.deletedFlag=0 ORDER BY e.modifiedDate DESC");
	}

	@Override
	@Transactional(readOnly = true)
	public <E> List<DispatchMasterEntity> findById(Integer dispatchAutoId) {
		// TODO Auto-generated method stub
		return getJpaTemplate().find(
				"FROM DispatchMasterEntity e WHERE e.dispatchAutoId = " + "'"
						+ dispatchAutoId + "'"
						+ " ORDER BY e.modifiedDate DESC");
	}

	@Override
	public List<DispatchMasterEntity> getMaxId() {
		// TODO Auto-generated method stub
		return getJpaTemplate()
				.find("FROM DispatchMasterEntity e   where e.deletedFlag=0 ORDER BY e.dispatchId DESC LIMIT 1");

	}

	@Override
	public List<DispatchMasterEntity> findByNameAndCode(String areaName,
			String areaCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DispatchMasterEntity> search(String dispatchNumber,
			String partyName, Date fromDate, Date toDate, String itemName) {

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM DispatchMasterEntity e");
		boolean first = true;

		if (dispatchNumber != null) {
			hql.append(first ? " where " : " and ");
			hql.append("e.dispatchNumber like :dispatchNumber");
			params.put("dispatchNumber", "%" + dispatchNumber + "%");
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

			hql.append("e.dispatchDate >= '" + fromDate1
					+ "' AND e.dispatchDate <= '" + toDate1 + "'");
			first = false;
		}

		if (itemName != null && itemName.trim().length() > 0) {
			hql.append(first ? " where " : " and ");
			hql.append("e.dispatchNumber IN (SELECT DISTINCT de.dispatchNumber FROM DispatchDetailEntity de, ItemEntity ie WHERE de.itemId=ie.itemId AND ie.itemName LIKE "
					+ "'%" + itemName + "%'" + " AND ie.deletedFlag=0)");
			// params.put("invoiceNumber",
			// "%"+"IN (SELECT DISTINCT invoice_number FROM t_bill_detail, m_item WHERE t_bill_detail.item_id=m_item.item_id AND m_item.item_name LIKE 'Copp%' AND m_item.deleted_flag=0)"+"%");
			first = false;
		}

		logger.info("HQL >>>>>>>>>>>> : " + hql.toString());
		hql.append(first ? " where " : " and ");
		hql.append(" e.deletedFlag=0  ORDER BY e.modifiedDate DESC ");
		first = false;
		List l = getJpaTemplate().findByNamedParams(hql.toString(), params);
		System.out.println("LIST SIZE :::::::" + l.size());
		return l;
	}

	@Override
	public List getNewSeriesNo(String finYear) {

		return getJpaTemplate()
				.find("SELECT max(e.dispatchId),max(e.dispatchDate) FROM DispatchMasterEntity e WHERE e.finyr=?1 and e.deletedFlag=0 ",
						finYear);

	}

}
