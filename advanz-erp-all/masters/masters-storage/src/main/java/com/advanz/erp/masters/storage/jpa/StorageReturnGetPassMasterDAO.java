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
import com.advanz.erp.masters.entity.jpa.ReturnGetPassDetailEntity;
import com.advanz.erp.masters.entity.jpa.ReturnGetPassMasterEntity;
import com.advanz.erp.masters.storage.IStorageReturnGetPassMasterDAO;

@Component
public class StorageReturnGetPassMasterDAO extends JpaDaoSupport implements
		IStorageReturnGetPassMasterDAO {
	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(ReturnGetPassMasterEntity entity) {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new IllegalArgumentException("Cannot create a null entity");
		}
		getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public ReturnGetPassMasterEntity read(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("uid must not be null");
		}
		return getJpaTemplate().find(ReturnGetPassMasterEntity.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ReturnGetPassMasterEntity update(ReturnGetPassMasterEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot update a null entity");
		}
		List<ReturnGetPassDetailEntity> detailEntityList = entity
				.getReturnGetPassDetailEntitiesList();
		List<ReturnGetPassDetailEntity> l = getJpaTemplate()
				.find("FROM ReturnGetPassDetailEntity e where e.deletedFlag=?1  AND e.returnGatePassNumber='"
						+ entity.getReturnGatePassNumber() + "'", false);
		for (int i = 0; i < l.size(); i++) {
			ReturnGetPassDetailEntity detailEntity = l.get(i);
			for (int j = 0; j < detailEntityList.size(); j++) {
				ReturnGetPassDetailEntity detailEntity2 = detailEntityList
						.get(j);
				if (detailEntity2.getReturnGatePassAutoId() != detailEntity
						.getReturnGatePassAutoId()) {
					detailEntity.setReturnGatePassAutoId(null);
					detailEntity.setDeletedFlag(true);
					entity.getReturnGetPassDetailEntitiesList().add(
							detailEntity);
				}
			}
		}
		entity.setDeletedFlag(false);
		return getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(ReturnGetPassMasterEntity entity1) {
		if (entity1 == null) {
			throw new IllegalArgumentException("Cannot remove a null entity");
		}

		ReturnGetPassMasterEntity entity = getJpaTemplate().find(
				ReturnGetPassMasterEntity.class,
				entity1.getReturnGatePassAutoId());
		entity.setDeletedFlag(true);
		List<ReturnGetPassDetailEntity> detailEntities = entity
				.getReturnGetPassDetailEntitiesList();
		for (ReturnGetPassDetailEntity e : detailEntities) {
			e.setDeletedFlag(true);
			e.setModifiedUserId(entity1.getModifiedUserId());
		}
		entity.setModifiedUserId(entity1.getModifiedUserId());
		getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReturnGetPassMasterEntity> load() {
		// TODO Auto-generated method stub
		return getJpaTemplate()
				.find("FROM ReturnGetPassMasterEntity e where e.deletedFlag=?1  ORDER BY e.returnGatePassAutoId DESC",
						false);
	}

	@Override
	@Transactional(readOnly = true)
	public <E> List<ReturnGetPassMasterEntity> findById(E id) {
		// TODO Auto-generated method stub
		return getJpaTemplate()
				.find("FROM ReturnGetPassMasterEntity e WHERE e.returnGatePassAutoId =?1 and e.deletedFlag=?2",
						id, false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReturnGetPassMasterEntity> search(String passNumber, Date date,
			String partyName, String itemName) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM ReturnGetPassMasterEntity e");
		boolean first = true;

		if (StringUtils.hasText(passNumber)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.returnGatePassNumber like :returnGatePassNumber");
			params.put("returnGatePassNumber", "%" + passNumber.trim() + "%");
			first = false;
		}
		if (date != null) {

			hql.append(first ? " where " : " and ");
			String stringDate = "";
			if (date != null) {
				stringDate = DataUtility.getDate(date);
			}
			hql.append("DATE(e.returnGatePassDate) >= '" + stringDate
					+ "' AND DATE(e.returnGatePassDate) <= '" + stringDate
					+ "'");
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
			hql.append("e.returnGatePassNumber IN (SELECT DISTINCT ge.returnGatePassNumber FROM ReturnGetPassDetailEntity ge, ItemEntity ie WHERE ge.itemEntity.itemId=ie.itemId AND ie.itemName LIKE "
					+ "'%" + itemName + "%'" + " AND ie.deletedFlag=0)");
			first = false;
		}

		// logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());

		hql.append(first ? " where " : " and ");
		hql.append(" e.deletedFlag=0 ORDER BY e.returnGatePassAutoId DESC");
		first = false;

		// logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	@Transactional(readOnly = true)
	public List getNewSeriesNo(String finYear) {

		return getJpaTemplate()
				.find("SELECT max(e.returnGatePassId), max(returnGatePassDate) FROM ReturnGetPassMasterEntity e WHERE e.finyr=?1 and e.deletedFlag=0 ",
						finYear);
	}

	@Override
	public List<ReturnGetPassMasterEntity> findReturnGetPassPagination(
			Integer next) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		Query query = entityManager
				.createQuery("FROM ReturnGetPassMasterEntity e WHERE e.deletedFlag=0 ORDER BY e.returnGatePassId DESC");
		query.setFirstResult(next);
		query.setMaxResults(15);
		List<ReturnGetPassMasterEntity> list = query.getResultList();
		entityManager.close();
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public <E> List<ReturnGetPassMasterEntity> findByReturnGetPassNumber(E id) {
		// TODO Auto-generated method stub
		return getJpaTemplate()
				.find("FROM ReturnGetPassMasterEntity e WHERE e.returnGatePassNumber =?1 and e.deletedFlag=?2",
						id, false);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ReturnGetPassMasterEntity> findForReturnGetPassNumber(
			Integer returnGetPassAutoId) {
		// TODO Auto-generated method stub
		return getJpaTemplate()
				.find("FROM ReturnGetPassMasterEntity e WHERE e.returnGatePassAutoId=?1 and e.deletedFlag=?2",
						returnGetPassAutoId, false);
	}

	@Override
	public List getPendingQty(String passNumber, Integer itemId) {
		/*
		 * List list1=getJpaTemplate().find(
		 * "SELECT e.pendingQuantity FROM ReturnGetPassDetailEntity e WHERE e.gatePassNumber=?1 and e.itemEntity.itemId='"
		 * +itemId+"' and e.deletedFlag=0 ",passNumber); Double qty=0.0;
		 * if(list1!=null && list1.size()>0){ qty=(Double)list1.get(0); }
		 */

		// List list = new ArrayList();
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		List list = entityManager
				.createNativeQuery(
						"SELECT rd.return_gate_pass_quantity FROM t_return_gate_pass_mast rgm LEFT JOIN t_return_gate_pass_detail rd ON rgm.return_gate_pass_auto_id=rd.return_gate_pass_auto_id WHERE rgm.gate_pass_number ='"
								+ passNumber
								+ "' AND rd.item_id='"
								+ itemId
								+ "' AND rd.deleted_flag=0 AND rgm.deleted_flag=0")
				.getResultList();

		entityManager.close();

		return list;
	}

	@Override
	public List sumPendingQty(String passNumber, Integer itemId) {
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		List list = entityManager
				.createNativeQuery(
						"SELECT sum(rd.return_gate_pass_quantity) FROM t_return_gate_pass_mast rgm LEFT JOIN t_return_gate_pass_detail rd ON rgm.return_gate_pass_auto_id=rd.return_gate_pass_auto_id WHERE rgm.gate_pass_number ='"
								+ passNumber
								+ "' AND rd.item_id='"
								+ itemId
								+ "' AND rd.deleted_flag=0 AND rgm.deleted_flag=0")
				.getResultList();

		entityManager.close();
		return list;
	}

}
