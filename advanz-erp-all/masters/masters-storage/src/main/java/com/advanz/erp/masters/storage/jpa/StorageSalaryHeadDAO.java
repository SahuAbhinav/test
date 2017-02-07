package com.advanz.erp.masters.storage.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.erp.masters.entity.jpa.SalaryHeadEntity;

import com.advanz.erp.masters.storage.IStorageSalaryHeadDAO;

@Component
public class StorageSalaryHeadDAO extends JpaDaoSupport implements
		IStorageSalaryHeadDAO {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(SalaryHeadEntity entity) {

		if (entity == null) {
			throw new IllegalArgumentException("Cannot create a null entity");
		}
		getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public SalaryHeadEntity read(Integer uid) {
		if (uid == null) {
			throw new IllegalArgumentException("uid must not be null");
		}
		return getJpaTemplate().find(SalaryHeadEntity.class, uid);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public SalaryHeadEntity update(SalaryHeadEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot update a null entity");
		}
		// entity.setDeletedFlag(false);
		return getJpaTemplate().merge(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(SalaryHeadEntity entity1) {
		if (entity1 == null) {
			throw new IllegalArgumentException("Cannot remove a null entity");
		}
		SalaryHeadEntity entity = getJpaTemplate().find(SalaryHeadEntity.class,
				entity1.getSalaryHeadId());
		entity.setDeletedFlag(true);
		entity1.setModifiedUserId(entity1.getModifiedUserId());
		getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SalaryHeadEntity> load() {

		return getJpaTemplate().find("FROM SalaryHeadEntity e where e.deletedFlag=?1  ORDER BY e.salaryHeadId",false);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<String> getHeadType() {

		return getJpaTemplate().find("SELECT e.headType FROM SalaryHeadEntity e where e.deletedFlag=?1 GROUP BY e.headType ",false);
	}

	@Override
	@Transactional(readOnly = true)
	public <E> List<SalaryHeadEntity> findById(E uid) {
		return getJpaTemplate().find("FROM SalaryHeadEntity e WHERE e.salaryHeadId =?1 and e.deletedFlag=?2",uid, false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SalaryHeadEntity> search(String salaryHeadName,
			String salaryHeadCode, String headType,Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM SalaryHeadEntity e");
		boolean first = true;

		if (StringUtils.hasText(salaryHeadName)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.salaryHeadName like :salaryHeadName");
			params.put("salaryHeadName", "%" + salaryHeadName.trim() + "%");
			first = false;
		}
		if (StringUtils.hasText(salaryHeadCode)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.salaryHeadCode like :salaryHeadCode");
			params.put("salaryHeadCode", "%" + salaryHeadCode.trim() + "%");
			first = false;
		}
		if (StringUtils.hasText(headType)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.headType like :headType");
			params.put("headType", "%" + headType.trim() + "%");
			first = false;
		}
		if (id !=null) {
			hql.append(first ? " where " : " and ");
			hql.append("e.salaryHeadId !="+id);
			first = false;
		}

		hql.append(first ? " where " : " and ");
		hql.append(" e.deletedFlag=0 ORDER BY e.salaryHeadId");

		first = false;

		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SalaryHeadEntity> findByNameAndCode(String salaryHeadName,String salaryHeadCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM SalaryHeadEntity e");
		boolean first = true;

		if (StringUtils.hasText(salaryHeadName)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.salaryHeadName = :salaryHeadName");
			params.put("salaryHeadName", salaryHeadName.trim());
			first = false;
		}
		if (StringUtils.hasText(salaryHeadCode)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.salaryHeadCode = :salaryHeadCode");
			params.put("salaryHeadCode", salaryHeadCode.trim());
			first = false;
		}

		hql.append(first ? " where " : " and ");
		hql.append(" e.deletedFlag=0 ORDER BY e.salaryHeadName");

		first = false;

		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	
	@Override
	@Transactional(readOnly = true)
	public List<SalaryHeadEntity> findBasicSalaryFlag() {
	return getJpaTemplate().find("FROM SalaryHeadEntity e where e.deletedFlag=0 and e.basicSalaryFlag=1 ORDER BY e.salaryHeadName");
	}

	@Override
	@Transactional(readOnly = true)
	public List<SalaryHeadEntity> findSalaryHeadByCode(String code) {
	return getJpaTemplate().find("FROM SalaryHeadEntity e WHERE e.salaryHeadCode=?1 and e.deletedFlag=?2",code,false); }

	@Override
	@Transactional(readOnly = true)
	public List<SalaryHeadEntity> findSalaryHeadByName(String name) {
	return getJpaTemplate().find("FROM SalaryHeadEntity e WHERE e.salaryHeadName=?1 and e.deletedFlag=?2",name,false);
	}

	@Override
	public Integer findSalaryHeadByType(String type) {
		 int id=0;
	        List list=getJpaTemplate().find("SELECT max(e.salaryHeadSequence) FROM SalaryHeadEntity e WHERE e.headType=?1 and e.deletedFlag=0 ",type);
	        if(list!=null && list.size()>0){
	        	Number n=(Number)list.get(0);
	        	if(n!=null)
	        	id=n.intValue();
	        }
	        id++;
		return id;
	
	
	}

	@Override
	public List<SalaryHeadEntity> findSalaryHeadByTypeAndSeqNo(String headType,Integer sequeNo) {
		return getJpaTemplate().find("FROM SalaryHeadEntity e WHERE e.headType=?1 and e.salaryHeadSequence=?2 and e.deletedFlag=?3",headType,sequeNo,false);
	}

}
