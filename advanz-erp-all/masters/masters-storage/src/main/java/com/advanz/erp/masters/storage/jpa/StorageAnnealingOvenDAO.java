package com.advanz.erp.masters.storage.jpa;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.common.DataUtility;
import com.advanz.erp.masters.entity.jpa.AnnealingOvenDetailEntity;
import com.advanz.erp.masters.entity.jpa.AnnealingOvenMasterEntity;
import com.advanz.erp.masters.storage.IStorageAnnealingOvenDAO;

@Component
public class StorageAnnealingOvenDAO extends JpaDaoSupport implements
		IStorageAnnealingOvenDAO {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(AnnealingOvenMasterEntity entity) {

		if (entity == null) {
			throw new IllegalArgumentException("Cannot create a null entity");
		}
		entity.setDeletedFlag(false);
		getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public AnnealingOvenMasterEntity read(Integer annealingOvenId) {
		if (annealingOvenId == null) {
			throw new IllegalArgumentException(
					"annealingOvenId must not be null");
		}
		return getJpaTemplate().find(AnnealingOvenMasterEntity.class,
				annealingOvenId);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public AnnealingOvenMasterEntity update(AnnealingOvenMasterEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot update a null entity");
		}
		entity.setDeletedFlag(false);
		return getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(AnnealingOvenMasterEntity entity1) {
		if (entity1 == null) {
			throw new IllegalArgumentException("Cannot remove a null entity");
		}
		AnnealingOvenMasterEntity entity = getJpaTemplate().find(
				AnnealingOvenMasterEntity.class, entity1.getOvenId());
		entity.setDeletedFlag(true);

		List<AnnealingOvenDetailEntity> detailEntities = entity
				.getAnnealingOvenDetailEntity();
		for (AnnealingOvenDetailEntity e : detailEntities) {
			e.setDeletedFlag(true);
			e.setModifiedUserId(entity1.getModifiedUserId());
		}

		entity.setModifiedUserId(entity1.getModifiedUserId());
		getJpaTemplate().merge(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public List<AnnealingOvenMasterEntity> load() {
		return getJpaTemplate()
				.find("FROM AnnealingOvenMasterEntity e where e.deletedFlag=0 ORDER BY e.ovenId DESC");
	}

	@Override
	@Transactional(readOnly = true)
	public List<AnnealingOvenMasterEntity> findById(Integer annealingOvenId) {

		return getJpaTemplate().find(
				"FROM AnnealingOvenMasterEntity e WHERE e.ovenId = "
						+ annealingOvenId + "ORDER BY e.ovenDate");
	}

	@Override
	@Transactional(readOnly = true)
	public List<AnnealingOvenMasterEntity> search(Date fromDate, Date toDate,
			String shift) {

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM AnnealingOvenMasterEntity e");
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

			hql.append("e.ovenDate >= '" + fromDate1 + "' AND e.ovenDate <= '"
					+ toDate1 + "'");
			first = false;
		}

		if (StringUtils.hasText(shift)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.shiftMasters.name like :shift");
			params.put("shift", "%" + shift.trim() + "%");
			first = false;
		}

		hql.append(first ? " where " : " and ");
		hql.append(" e.deletedFlag=0 ");

		first = false;

		logger.info("HQL >>>>>>>>>>>> : " + hql.toString());
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	public List<AnnealingOvenMasterEntity> checkDuplicateEntry(Date date,
			Integer shift) {
		String date1 = DataUtility.getDate(date);
		return getJpaTemplate().find(
				"FROM AnnealingOvenMasterEntity e where e.ovenDate ='" + date1
						+ "' AND e.shiftMasters.mastersId =" + shift
						+ " AND e.deletedFlag=0 ");
	}

	@Override
	public Timestamp lastAnnealingOvenDate() {
		Timestamp date = null;
		List list = getJpaTemplate()
				.find("select max(e.ovenDate) FROM AnnealingOvenMasterEntity e where  e.deletedFlag=0 ");
		System.out.println("list size"+list.size());
		if (list != null && list.size() > 0) {
			date = (Timestamp) list.get(0);
		}
		return date;

	}

}
