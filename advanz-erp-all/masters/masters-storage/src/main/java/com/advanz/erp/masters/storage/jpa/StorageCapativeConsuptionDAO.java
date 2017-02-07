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
import com.advanz.erp.masters.entity.jpa.AnnealingOvenDetailEntity;
import com.advanz.erp.masters.entity.jpa.AnnealingOvenMasterEntity;
import com.advanz.erp.masters.entity.jpa.CapativeConsuptionEntity;
import com.advanz.erp.masters.entity.jpa.FinishedGoodsMasterEntity;
import com.advanz.erp.masters.storage.IStorageAnnealingOvenDAO;
import com.advanz.erp.masters.storage.IStorageCapativeConsuptionDAO;

@Component
public class StorageCapativeConsuptionDAO extends JpaDaoSupport implements
		IStorageCapativeConsuptionDAO {
	@Autowired
	EntityManagerFactory entityManagerFactory;
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(CapativeConsuptionEntity entity) {

		if (entity == null) {
			throw new IllegalArgumentException("Cannot create a null entity");
		}
		entity.setDeletedFlag(false);
		getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public CapativeConsuptionEntity read(Integer sno) {
		if (sno == null) {
			throw new IllegalArgumentException(
					"CapativeConsuption must not be null");
		}
		return getJpaTemplate().find(CapativeConsuptionEntity.class, sno);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public CapativeConsuptionEntity update(CapativeConsuptionEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot update a null entity");
		}
		entity.setDeletedFlag(false);
		return getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(CapativeConsuptionEntity entity1) {
		if (entity1 == null) {
			throw new IllegalArgumentException("Cannot remove a null entity");
		}
		CapativeConsuptionEntity entity = getJpaTemplate().find(
				CapativeConsuptionEntity.class, entity1.getSno());
		entity.setDeletedFlag(true);
		entity.setModifiedUserId(entity1.getModifiedUserId());
		getJpaTemplate().merge(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public List<CapativeConsuptionEntity> load() {
		return getJpaTemplate()
				.find("FROM CapativeConsuptionEntity e where e.deletedFlag=0 ORDER BY e.sno DESC");
	}

	@Override
	@Transactional(readOnly = true)
	public List<CapativeConsuptionEntity> findById(Integer sno) {

		return getJpaTemplate().find(
				"FROM CapativeConsuptionEntity e WHERE e.sno = "
						+ sno + "ORDER BY e.ovenDate");
	}

	@Override
	@Transactional(readOnly = true)
	public List<CapativeConsuptionEntity> search(Date fromDate,String sourceItemCode,String targetItemCode) {

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM CapativeConsuptionEntity e");
		boolean first = true;

		if (fromDate != null ) {
			hql.append(first ? " where " : " and ");
			String fromDate1 = "";
			if (fromDate != null) {
				fromDate1 = DataUtility.getDate(fromDate);
			}
			hql.append("e.enteredDate = '" + fromDate1+"'");
			first = false;
		}
		if (StringUtils.hasText(sourceItemCode)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.sourceItemId =(select e.itemId from ItemEntity e where e.itemCode='"+sourceItemCode+"' and e.deletedFlag=0) ");
		    first=false;
		}
		if (StringUtils.hasText(targetItemCode)) {
		    hql.append(first ? " where " : " and ");
		    hql.append("e.targetItemId =(select e.itemId from ItemEntity e where e.itemCode='"+targetItemCode+"' and e.deletedFlag=0)");
		    first=false;
		}

		hql.append(first ? " where " : " and ");
		hql.append(" e.deletedFlag=0 ");

		first = false;

		logger.info("HQL >>>>>>>>>>>> : " + hql.toString());
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	public List<CapativeConsuptionEntity> checkDuplicateEntry(Date date,
			Integer shift) {
		String date1 = DataUtility.getDate(date);
		return getJpaTemplate().find(
				"FROM CapativeConsuptionEntity e where e.enteredDate ='" + date1
						+ " AND e.deletedFlag=0 ");
	}
	@Override
	public List<CapativeConsuptionEntity> FindCapativeConsumptionPagination(Integer next) {
	EntityManager entityManager=entityManagerFactory.createEntityManager();
	Query query=entityManager.createQuery("FROM CapativeConsuptionEntity e WHERE e.deletedFlag=0 ORDER BY e.sno DESC");
	query.setFirstResult(next);
	query.setMaxResults(15);
	List<CapativeConsuptionEntity> list = query.getResultList();
	entityManager.close();
	return list;
	}

	@Override
	public Integer getNewSeriesNo() {
		int id = 0;
		List list = getJpaTemplate()
				.find("SELECT max(e.capativeConsumptionId) FROM CapativeConsuptionEntity e WHERE e.deletedFlag=0");
		if (list != null && list.size() > 0) {
			Number n = (Number) list.get(0);
			if (n != null)
				id = n.intValue();
		}
		id++;
		return id;
	}
	
}
