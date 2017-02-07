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
import com.advanz.erp.masters.entity.jpa.GetPassDetailEntity;
import com.advanz.erp.masters.entity.jpa.GetPassMasterEntity;
import com.advanz.erp.masters.entity.jpa.IssueDetailMasterEntity;
import com.advanz.erp.masters.entity.jpa.IssueMasterEntity;
import com.advanz.erp.masters.entity.jpa.ReturnGetPassDetailEntity;
import com.advanz.erp.masters.storage.IStorageGetPassMasterDAO;

@Component
public class StorageGetPassMasterDAO extends JpaDaoSupport implements
		IStorageGetPassMasterDAO {
	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(GetPassMasterEntity entity) {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new IllegalArgumentException("Cannot create a null entity");
		}
		getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public GetPassMasterEntity read(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("uid must not be null");
		}
		return getJpaTemplate().find(GetPassMasterEntity.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public GetPassMasterEntity update(GetPassMasterEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot update a null entity");
		}
		List<GetPassDetailEntity> detailEntityList = entity
				.getGetPassDetailEntitiesList();
		List<GetPassDetailEntity> l = getJpaTemplate().find(
				"FROM GetPassDetailEntity e where e.deletedFlag=?1  AND e.gatePassNumber='"
						+ entity.getGatePassNumber() + "'", false);
		for (int i = 0; i < l.size(); i++) {
			GetPassDetailEntity detailEntity = l.get(i);
			// detailEntity.setGatePassAutoId(entity.getGatePassAutoId());
			for (int j = 0; j < detailEntityList.size(); j++) {
				GetPassDetailEntity detailEntity2 = detailEntityList.get(j);
				if (detailEntity2.getGatePassAutoId() != detailEntity
						.getGatePassAutoId()) {
					detailEntity.setGatePassAutoId(null);
					detailEntity.setDeletedFlag(true);
					entity.getGetPassDetailEntitiesList().add(detailEntity);
				}
			}
		}
		entity.setDeletedFlag(false);
		return getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(GetPassMasterEntity entity1) {
		if (entity1 == null) {
			throw new IllegalArgumentException("Cannot remove a null entity");
		}

		GetPassMasterEntity entity = getJpaTemplate().find(
				GetPassMasterEntity.class, entity1.getGatePassAutoId());

		List<GetPassDetailEntity> detailEntities = entity
				.getGetPassDetailEntitiesList();
		for (GetPassDetailEntity e : detailEntities) {
			e.setDeletedFlag(true);
			e.setModifiedUserId(entity1.getModifiedUserId());
		}
		entity.setModifiedUserId(entity1.getModifiedUserId());
		entity.setDeletedFlag(true);
		getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GetPassMasterEntity> load() {
		// TODO Auto-generated method stub
		return getJpaTemplate()
				.find("FROM GetPassMasterEntity e where e.deletedFlag=?1  ORDER BY e.gatePassAutoId DESC",
						false);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public GetPassDetailEntity updateGatePassDetail(GetPassDetailEntity entity) {
		return getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<GetPassDetailEntity> findByGatePassNoAndItemId(
			String gatePassNumber, Integer itemId) {
		return getJpaTemplate()
				.find("FROM GetPassDetailEntity e WHERE e.itemEntity.itemId=?1 and e.gatePassNumber='"
						+ gatePassNumber + "' and e.deletedFlag=?2", itemId,
						false);
	}

	@Override
	@Transactional(readOnly = true)
	public <E> List<GetPassMasterEntity> findById(E id) {
		// TODO Auto-generated method stub
		return getJpaTemplate()
				.find("FROM GetPassMasterEntity e WHERE e.gatePassAutoId =?1 and e.deletedFlag=?2",
						id, false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GetPassMasterEntity> search(String passNumber, Date date,
			String partyName, String itemName, String gatePassType, Date toDate) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM GetPassMasterEntity e");
		boolean first = true;

		if (StringUtils.hasText(passNumber)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.gatePassNumber like :gatePassNumber");
			params.put("gatePassNumber", "%" + passNumber.trim() + "%");
			first = false;
		}
		if (date != null || toDate != null) {
			hql.append(first ? " where " : " and ");
			String stringDate = "";
			if (date != null) {
				stringDate = DataUtility.getDate(date);
			}
			String stringToDate = "";
			if (toDate != null) {
				stringToDate = DataUtility.getDate(toDate);
			}
			hql.append("DATE(e.gatePassDate) >= '" + stringDate
					+ "' AND DATE(e.gatePassDate) <= '" + stringToDate + "'");
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
			hql.append("e.gatePassNumber IN (SELECT DISTINCT ge.gatePassNumber FROM GetPassDetailEntity ge, ItemEntity ie WHERE ge.itemEntity.itemId=ie.itemId AND ie.itemName LIKE "
					+ "'%" + itemName + "%'" + " AND ie.deletedFlag=0)");
			first = false;
		}
		if (StringUtils.hasText(gatePassType)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.gatePassType like :gatePassType");
			params.put("gatePassType", gatePassType.trim() + "%");
			first = false;
		}

		// logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());

		hql.append(first ? " where " : " and ");
		hql.append(" e.deletedFlag=0 ORDER BY e.gatePassAutoId DESC");
		first = false;

		// logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	@Transactional(readOnly = true)
	public List getNewSeriesNo(String finYear) {

		return getJpaTemplate()
				.find("SELECT max(e.gatePassId),max(gatePassDate) FROM GetPassMasterEntity e WHERE e.finyr=?1 and e.deletedFlag=0 ",
						finYear);
	}

	@Override
	public List<GetPassMasterEntity> findGatePassPagination(Integer next) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		Query query = entityManager
				.createQuery("FROM GetPassMasterEntity e WHERE e.deletedFlag=0 ORDER BY e.gatePassAutoId DESC");
		query.setFirstResult(next);
		query.setMaxResults(15);
		List<GetPassMasterEntity> list = query.getResultList();
		entityManager.close();
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public <E> List<GetPassMasterEntity> findByGetPassNumber(E id) {
		// TODO Auto-generated method stub
		return getJpaTemplate()
				.find("FROM GetPassMasterEntity e WHERE e.gatePassNumber =?1 and e.deletedFlag=?2",
						id, false);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<GetPassMasterEntity> findForGetPassNumber(Integer getPassAutoId) {
		// TODO Auto-generated method stub
		return getJpaTemplate()
				.find("FROM GetPassMasterEntity e WHERE e.gatePassAutoId=?1 and e.deletedFlag=?2",
						getPassAutoId, false);
	}

	@Override
	public List<GetPassDetailEntity> findByPassNo(String getPassAutoNo) {
		// TODO Auto-generated method stub
		return getJpaTemplate()
				.find("FROM GetPassDetailEntity e WHERE e.gatePassNumber=?1 and e.pendingQuantity>0 and e.deletedFlag=?2",
						getPassAutoNo, false);
	}

	@Override
	public List<GetPassMasterEntity> findByGatePassType(String gatePassType,
			Integer next) {

		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		Query query = entityManager
				.createQuery("FROM GetPassMasterEntity e WHERE e.gatePassType ='"
						+ gatePassType
						+ "' and e.deletedFlag=0 ORDER BY e.gatePassAutoId DESC");
		query.setFirstResult(next);
		query.setMaxResults(15);
		List<GetPassMasterEntity> list = query.getResultList();
		entityManager.close();

		return list;
	}

}
