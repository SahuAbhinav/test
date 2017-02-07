package com.advanz.erp.masters.storage.jpa;

import java.sql.Timestamp;
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
import com.advanz.erp.masters.entity.jpa.IssueReturnMasterEntity;
import com.advanz.erp.masters.entity.jpa.ShiftConsumedDetailEntity;
import com.advanz.erp.masters.entity.jpa.ShiftEngInterruptionDetailEntity;
import com.advanz.erp.masters.entity.jpa.ShiftReportMasterEntity;
import com.advanz.erp.masters.entity.jpa.ShiftSpinInterruptionDetailEntity;
import com.advanz.erp.masters.storage.IStorageShiftReportMasterDAO;
import com.advanz.erp.masters.storage.IStorageZoneDAO;

public class StorageShiftReportMasterDAO extends JpaDaoSupport implements
		IStorageShiftReportMasterDAO {

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(ShiftReportMasterEntity entity) {

		if (entity == null) {
			throw new IllegalArgumentException("Cannot create a null entity");
		}
		getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public ShiftReportMasterEntity read(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("uid must not be null");
		}
		return getJpaTemplate().find(ShiftReportMasterEntity.class, id);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ShiftReportMasterEntity update(ShiftReportMasterEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot update a null entity");
		}
		entity.setDeletedFlag(false);
		return getJpaTemplate().merge(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(ShiftReportMasterEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot remove a null entity");
		}
		entity = getJpaTemplate().find(ShiftReportMasterEntity.class,
				entity.getShiftReportId());
		entity.setDeletedFlag(true);
		getJpaTemplate().merge(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void deleteShiftEng(ShiftEngInterruptionDetailEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot remove a null entity");
		}
		entity = getJpaTemplate().find(ShiftEngInterruptionDetailEntity.class,
				entity.getSno());
		entity.setDeletedFlag(true);
		getJpaTemplate().merge(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void deleteShiftSpin(ShiftSpinInterruptionDetailEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot remove a null entity");
		}
		entity = getJpaTemplate().find(ShiftSpinInterruptionDetailEntity.class,
				entity.getSno());
		entity.setDeletedFlag(true);
		getJpaTemplate().merge(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void deleteConsumedDetail(ShiftConsumedDetailEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot remove a null entity");
		}
		entity = getJpaTemplate().find(ShiftConsumedDetailEntity.class,
				entity.getSno());
		entity.setDeletedFlag(true);
		getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ShiftReportMasterEntity> load() {
		return getJpaTemplate()
				.find("FROM ShiftReportMasterEntity e where e.deletedFlag=?1  ORDER BY e.shiftReportId DESC",
						false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ShiftReportMasterEntity> getDuplicateCheckList() {
		return getJpaTemplate()
				.find("Select e.mastersEntity.mastersId,e.shifReportDate,e.runNo  FROM ShiftReportMasterEntity e where e.deletedFlag=?1  ORDER BY e.modifiedDate DESC",
						false);
	}

	@Override
	@Transactional(readOnly = true)
	public <E> List<ShiftReportMasterEntity> findById(E id) {
		// logger.info("a service  >>>>>>>>>>>> : "+id);
		return getJpaTemplate()
				.find("FROM ShiftReportMasterEntity e WHERE e.shiftReportId=?1 and e.deletedFlag=?2",
						id, false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ShiftReportMasterEntity> search(String runNo, Date fromDate,
			Date toDate) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM ShiftReportMasterEntity e");
		boolean first = true;

		if (StringUtils.hasText(runNo)) {
			hql.append(first ? " where " : " and ");
			hql.append("runNo like :runNo");
			params.put("runNo", "%" + runNo.trim() + "%");
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

			hql.append("e.shifReportDate >= '" + fromDate1
					+ "' AND e.shifReportDate <= '" + toDate1 + "'");
			first = false;
		}

		// logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());

		hql.append(first ? " where " : " and ");
		hql.append(" e.deletedFlag=0 ORDER BY e.shiftReportId DESC ");

		first = false;

		// logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	public List<ShiftReportMasterEntity> checkDuplicateEntry(Date date,
			String runNo, Integer shiftId) {
		String date1 = DataUtility.getDate(date);
		return getJpaTemplate().find(
				"FROM ShiftReportMasterEntity e where e.mastersEntity.mastersId ="
						+ shiftId + " AND e.shifReportDate ='" + date1
						+ "' AND e.runNo ='" + runNo
						+ "' AND e.deletedFlag=?1 ", false);
	}

	@Override
	public List<ShiftReportMasterEntity> findIShiftReportPagination(Integer next) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		Query query = entityManager
				.createQuery("FROM ShiftReportMasterEntity e WHERE e.deletedFlag=0 ORDER BY e.shiftReportId DESC");
		query.setFirstResult(next);
		query.setMaxResults(15);
		List<ShiftReportMasterEntity> list = query.getResultList();
		entityManager.close();
		return list;
	}

	@Override
	public Timestamp getLastShiftDate() {
		Timestamp date = null;
		List list = getJpaTemplate()
				.find("select max(shifReportDate) FROM ShiftReportMasterEntity e where  e.deletedFlag=0");
		if (list != null && list.size() > 0) {
			date = (Timestamp) list.get(0);
		}
		return date;
	}
}
