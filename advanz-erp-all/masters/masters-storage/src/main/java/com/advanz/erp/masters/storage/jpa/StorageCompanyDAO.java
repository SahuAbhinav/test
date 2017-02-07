package com.advanz.erp.masters.storage.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.erp.masters.entity.jpa.BlanketWeightRecordEntity;
import com.advanz.erp.masters.entity.jpa.CompanyEntity;
import com.advanz.erp.masters.entity.jpa.ItemCategoryEntity;
import com.advanz.erp.masters.entity.jpa.LoggerEntity;
import com.advanz.erp.masters.storage.IStorageCompanyDAO;

@Component
public class StorageCompanyDAO extends JpaDaoSupport implements
		IStorageCompanyDAO {
	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(CompanyEntity entity) {

		if (entity == null) {
			throw new IllegalArgumentException("Cannot create a null entity");
		}
		getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public CompanyEntity read(Integer uid) {
		if (uid == null) {
			throw new IllegalArgumentException("uid must not be null");
		}
		return getJpaTemplate().find(CompanyEntity.class, uid);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public CompanyEntity update(CompanyEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot update a null entity");
		}
		return getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(CompanyEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Cannot delete a null entity");
		}
		entity = getJpaTemplate().find(CompanyEntity.class,
				entity.getCompanyId());
		entity.setDeletedFlag(true);
		getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CompanyEntity> findById(Integer uid) {
		return getJpaTemplate()
				.find("FROM CompanyEntity e WHERE e.companyId = ?1 and e.deletedFlag = ?2 ORDER BY e.companyName",
						uid, false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CompanyEntity> findByName(String companyName) {

		return getJpaTemplate()
				.find("FROM CompanyEntity e WHERE e.companyName = ?1 and deletedFlag=?2 ORDER BY e.companyName",
						companyName.trim(), false);

	}

	@Override
	@Transactional(readOnly = true)
	public List<CompanyEntity> findByCode(String companyCode) {

		return getJpaTemplate()
				.find("FROM CompanyEntity e WHERE e.companyCode=?1 and deletedFlag=?2 ORDER BY e.companyName",
						companyCode.trim(), false);

	}

	@Override
	@Transactional(readOnly = true)
	public List<CompanyEntity> load() {
		return getJpaTemplate()
				.find("FROM CompanyEntity e WHERE e.deletedFlag = ?1 ORDER BY e.companyName",
						false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CompanyEntity> search(String companyName, String companyCity,
			String companyCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM CompanyEntity e");
		boolean first = true;
		if (StringUtils.hasText(companyName)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.companyName like :companyName");
			params.put("companyName", "%" + companyName.trim() + "%");
			first = false;
		}
		// logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		if (StringUtils.hasText(companyCity)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.companyCity like :companyCity");
			params.put("companyCity", "%" + companyCity.trim() + "%");
			first = false;
		}
		// logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		if (StringUtils.hasText(companyCode)) {
			hql.append(first ? " where " : " and ");
			hql.append("e.companyCode like :companyCode");
			params.put("companyCode", "%" + companyCode.trim() + "%");
			first = false;
		}
		// logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		hql.append(first ? " where " : " and ");
		hql.append("e.deletedFlag = :flag");
		params.put("flag", false);
		// logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());

		hql.append(" ORDER BY e.companyName");
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	public Boolean getEmailFlag() {
		Boolean b = false;
		List l = getJpaTemplate().find("SELECT emailFlag FROM CompanyEntity");
		if (l != null && l.size() > 0) {
			b = (Boolean) l.get(0);
		}
		return b;
	}

	@Override
	public Date getSalaryGenaratingDate() {
		List l = getJpaTemplate().find(
				"SELECT salaryGenaratingFromDate FROM CompanyEntity");
		Date salaryGenaratingFromDate = null;
		if (l != null && l.size() > 0) {
			salaryGenaratingFromDate = (Date) l.get(0);
		}
		return salaryGenaratingFromDate;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void createBlanketWeightRecord(
			BlanketWeightRecordEntity blanketWeightRecordEntity) {
		getJpaTemplate().persist(blanketWeightRecordEntity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void createLoggerRecord(LoggerEntity loggerEntity) {
		getJpaTemplate().persist(loggerEntity);

	}

	@Override
	public String getWeightingFilePath() {
		String path = null;
		List l = getJpaTemplate().find(
				"SELECT weightingFilePath FROM CompanyEntity");
		if (l != null && l.size() > 0) {
			path = (String) l.get(0);
		}
		return path;

	}

	@Override
	public String callWeightedSP() {
		System.out.println("calling sp.............");
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();

		try {
			entityManager.createNativeQuery("CALL test()").getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		System.out.println("calling sp.............End");

		return null;

	}
}
