package com.advanz.erp.masters.storage.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.erp.masters.entity.jpa.BarcodeLedgerEntity;
import com.advanz.erp.masters.storage.IStorageBarcodeLedgerDAO;

@Component
public class StorageBarcodeLedgerDAO extends JpaDaoSupport implements
		IStorageBarcodeLedgerDAO {

	@Transactional(propagation = Propagation.REQUIRED)
	public BarcodeLedgerEntity createBarcode(BarcodeLedgerEntity entity) {

		if (entity == null) {
			throw new IllegalArgumentException("Cannot create a null entity");
		}
		/*entity=getJpaTemplate().merge(entity);
		entity.setBarcodeSno(BarcodeLedgerEntity.BARCODE_SNO+entity.getId()+"");
		entity.setBarcode(entity.getBarcode()+entity.getId());*/
		//getJpaTemplate().merge(entity);
		
		getJpaTemplate().persist(entity);
		entity.setBarcodeSno(BarcodeLedgerEntity.BARCODE_SNO+entity.getId()+"");
		entity.setBarcode(entity.getBarcode()+entity.getId());
		
		return entity;
	}

	@Override
	@Transactional(readOnly = true)
	public BarcodeLedgerEntity read(Integer uid) {
		if (uid == null) {
			throw new IllegalArgumentException("uid must not be null");
		}
		return getJpaTemplate().find(BarcodeLedgerEntity.class, uid);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public BarcodeLedgerEntity update(BarcodeLedgerEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot update a null entity");
		}
		// entity.setDeletedFlag(false);
		return getJpaTemplate().merge(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(BarcodeLedgerEntity entity1) {
		if (entity1 == null) {
			throw new IllegalArgumentException("Cannot remove a null entity");
		}
		BarcodeLedgerEntity entity = getJpaTemplate().find(
				BarcodeLedgerEntity.class, entity1.getId());
		entity.setDeletedFlag(true);
		entity.setModifiedUserId(entity1.getModifiedUserId());
		getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BarcodeLedgerEntity> load() {

		return getJpaTemplate()
				.find("FROM BarcodeLedgerEntity e where e.deletedFlag=?1  ORDER BY e.id",
						false);
	}

	@Override
	@Transactional(readOnly = true)
	public <E> List<BarcodeLedgerEntity> findById(E uid) {
		return getJpaTemplate()
				.find("FROM BarcodeLedgerEntity e WHERE e.idd =?1 and e.deletedFlag=?2  ORDER BY e.areaName",
						uid, false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BarcodeLedgerEntity> search(Integer itemId, String barcode,
			String transactionType, Integer transactionId) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM BarcodeLedgerEntity e");
		boolean first = true;

		if (itemId != null && itemId > 0) {
			hql.append(first ? " where " : " and ");
			hql.append("e.itemId like :itemId");
			params.put("itemId", "%" + itemId + "%");
			first = false;
		}

		if (StringUtils.hasText(barcode)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.barcode like :barcode");
			params.put("barcode", "%" + barcode.trim() + "%");
			first = false;
		}
		if (StringUtils.hasText(transactionType)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.transactionType like :transactionType");
			params.put("transactionType", "%" + transactionType.trim() + "%");
			first = false;
		}
		if (transactionId != null && transactionId > 0) {
			hql.append(first ? " where " : " and ");
			hql.append("e.transactionId like :transactionId");
			params.put("transactionId", "%" + transactionId + "%");
			first = false;
		}

		hql.append(first ? " where " : " and ");
		hql.append(" e.deletedFlag=0 ORDER BY e.id");

		first = false;

		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BarcodeLedgerEntity> findByTranasactionType(
			String transactionType) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM AreaEntity e");
		boolean first = true;

		if (StringUtils.hasText(transactionType)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.transactionType like :transactionType");
			params.put("transactionType", "%" + transactionType.trim() + "%");
			first = false;
		}

		hql.append(first ? " where " : " and ");
		hql.append(" e.deletedFlag=0 ORDER BY e.areaName ");

		first = false;

		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	public void create(BarcodeLedgerEntity entity) {
		// Here we not use Default Create Method because it return void and we
		// want sno return back to service for BarcodeSno

	}

}
