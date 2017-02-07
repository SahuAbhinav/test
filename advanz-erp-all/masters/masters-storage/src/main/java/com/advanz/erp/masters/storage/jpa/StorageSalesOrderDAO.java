package com.advanz.erp.masters.storage.jpa;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.erp.masters.entity.jpa.AreaEntity;
import com.advanz.erp.masters.entity.jpa.SalesOrderEntity;
import com.advanz.erp.masters.storage.IStorageSalesOrderDAO;

public class StorageSalesOrderDAO extends JpaDaoSupport implements
		IStorageSalesOrderDAO {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(SalesOrderEntity entity) {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new IllegalArgumentException("Cannot create a null entity");
		}
		getJpaTemplate().persist(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public SalesOrderEntity read(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("id must not be null");
		}
		return getJpaTemplate().find(SalesOrderEntity.class, id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public SalesOrderEntity update(SalesOrderEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot update a null entity");
		}
		// entity.setDeletedFlag(false);
		return getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(SalesOrderEntity entity) {
		// TODO Auto-generated method stub
		if (entity == null) {
			throw new IllegalArgumentException("Cannot remove a null entity");
		}
		entity = getJpaTemplate().find(SalesOrderEntity.class,
				entity.getSalesOrderAutoId());
		entity.setDeletedFlag(true);
		getJpaTemplate().merge(entity);
	}

	@Override
	public List<SalesOrderEntity> load() {
		//return getJpaTemplate().find("FROM SalesOrderEntity e where e.deletedFlag=0 ORDER BY e.modifiedDate DESC");
		return getJpaTemplate().find("FROM SalesOrderEntity e where e.deletedFlag=0 ORDER BY e.salesOrderAutoId DESC");
	}


	@Override
	@Transactional(readOnly = true)
	public List<SalesOrderEntity> search(String salesOrderNumber,
			Date salesOrderDate, Integer partyId) {
		// TODO Auto-generated method stub
		//System.out.println("SSSSSSSSSales ORDER NO is:" + salesOrderNumber);
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM SalesOrderEntity e");
		boolean first = true;

		//System.out.println("SSSSSSSSSales ORDER NO is:" + salesOrderDate);
		if (StringUtils.hasText(salesOrderNumber)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.salesOrderNumber like :salesOrderNumber");
			params.put("salesOrderNumber", "%" + salesOrderNumber.trim()+ "%");
			first = false;
		}
		//logger.info("HQL >>>>>>>>>>>> : " + hql.toString());

		if (salesOrderDate!=null) {
			hql.append(first ? " where " : " and ");
			hql.append("e.salesOrderDate = :salesOrderDate");
			params.put("salesOrderDate", salesOrderDate);
			first = false;
		}
		//logger.info("HQL >>>>>>>>>>>> : " + hql.toString());

		if (partyId!=null) {
			hql.append(first ? " where " : " and ");
			hql.append("e.partyId :invoice");
			params.put("partyId",  partyId);
			first = false;
		}
		//logger.info("HQL >>>>>>>>>>>> : " + hql.toString());
		hql.append(first ? " where " : " and ");
		hql.append("e.deletedFlag=:flag");
		params.put("flag", false);
		//logger.info("HQL >>>>>>>>>>>> : " + hql.toString());
		List l=getJpaTemplate().findByNamedParams(hql.toString(), params);
		//System.out.println(" Sales Order List size  in search DAO :  " + l.size());
		return l;
	   }

}
