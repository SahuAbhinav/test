package com.advanz.erp.masters.storage.jpa;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.advanz.common.DataUtility;
import com.advanz.erp.masters.entity.jpa.IssueReturnDetailEntity;
import com.advanz.erp.masters.entity.jpa.IssueReturnMasterEntity;
import com.advanz.erp.masters.entity.jpa.SalesOrderMasterEntity;
import com.advanz.erp.masters.storage.IStorageIssueReturnDAO;

public class StorageIsssueReturnDAO extends JpaDaoSupport implements
		IStorageIssueReturnDAO {

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(IssueReturnMasterEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Entity shouldn't be null");
		}
		getJpaTemplate().persist(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public IssueReturnMasterEntity update(IssueReturnMasterEntity entity) {
		if (entity == null) {
			throw new IllegalArgumentException(
					"Entity must not be Null when update");
		}
		
		
		
		List<IssueReturnDetailEntity> detailEntityList=entity.getIssueReturnDetailEntities();
		 
		 List<IssueReturnDetailEntity>  l=getJpaTemplate().find("FROM IssueReturnDetailEntity e where e.deletedFlag=?1  AND e.issueReturnNumber='"+entity.getIssueReturnNumber()+"'",false);
		
		 for(int i=0;i<l.size();i++){
			 IssueReturnDetailEntity detailEntity=	l.get(i);
				for(int j=0;j<detailEntityList.size();j++){
					IssueReturnDetailEntity detailEntity2=detailEntityList.get(j);
					if(detailEntity2.getIssueReturnAutoId()!=detailEntity.getIssueReturnAutoId() ){
						detailEntity.setIssueReturnAutoId(null);
						detailEntity.setDeletedFlag(true);
						detailEntity.setModifiedUserId(entity.getModifiedUserId());
						entity.getIssueReturnDetailEntities().add(detailEntity);
					}}}
	    entity.setDeletedFlag(false);
		return getJpaTemplate().merge(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(IssueReturnMasterEntity entity1) {
		if (entity1 == null) {
			throw new IllegalArgumentException(
					"Entity can't be null when remove");
		}

		IssueReturnMasterEntity entity = getJpaTemplate().find(IssueReturnMasterEntity.class, entity1.getIssueReturnAutoId());
		entity.setDeletedFlag(true);

		List<IssueReturnDetailEntity> detailEntities = entity.getIssueReturnDetailEntities();
		for (IssueReturnDetailEntity e : detailEntities) {
			e.setDeletedFlag(true);
			e.setModifiedUserId(entity1.getModifiedUserId());
		}

		entity.setModifiedUserId(entity1.getModifiedUserId());
		getJpaTemplate().merge(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public IssueReturnMasterEntity read(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("Id mustn't be null");
		}
		return getJpaTemplate().find(IssueReturnMasterEntity.class, id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<IssueReturnMasterEntity> load() {
		return getJpaTemplate().find(
				"From IssueReturnMasterEntity e where e.deletedFlag=?1 ORDER BY e.issueReturnAutoId DESC", false);
	}

	@Override
	public List<IssueReturnMasterEntity> search(String returnNumber,
			Date fromDate, Date toDate, Integer departmentId) {

		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("FROM IssueReturnMasterEntity e");
		boolean first = true;

		if (StringUtils.hasText(returnNumber)) {
			hql.append(first ? " where " : " and ");
			hql.append("issueReturnNumber like :issueReturnNumber");
			params.put("issueReturnNumber", "%" + returnNumber.trim() + "%");
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

			hql.append("cast(e.issueReturnDate as date) >= '" + fromDate1
					+ "' AND cast(e.issueReturnDate as date) <= '" + toDate1 + "'");
			first = false;
		}

		if (departmentId != null && departmentId > 0) {
			hql.append(first ? " where " : " and ");
			hql.append("e.mastersEntity.mastersId like :mastersId");
			params.put("mastersId", departmentId);
			first = false;
		}

		hql.append(first ? " where " : " and ");
		hql.append(" e.deletedFlag=0 ORDER BY e.issueReturnAutoId DESC");

		first = false;

		// logger.info("HQL >>>>>>>>>>>> : "+ hql.toString());
		return getJpaTemplate().findByNamedParams(hql.toString(), params);
	}

	@Override
	@Transactional(readOnly = true)
	public List getNewSeriesNo(String finYear) {
		
		return getJpaTemplate()
				.find("SELECT max(e.issueReturnId),max(issueReturnDate) FROM IssueReturnMasterEntity e WHERE e.finYear=?1 and e.deletedFlag=0 ",
						finYear);
	}

	@Override
	public List<IssueReturnMasterEntity> findById(Integer id) {
		// TODO Auto-generated method stub
		List<IssueReturnMasterEntity> list = getJpaTemplate()
				.find("FROM IssueReturnMasterEntity e WHERE e.issueReturnAutoId=?1 and e.deletedFlag=?2",
						id, false);
		return list;
	}

	@Override
	public List<IssueReturnMasterEntity> findDuplicate(String returnNumber,
			Date date) {
		List<IssueReturnMasterEntity> list = getJpaTemplate()
				.find("FROM IssueReturnMasterEntity e WHERE e.issueReturnNumber=?1 and e.issueReturnDate=?2 and e.deletedFlag=?3",
						returnNumber, date, false);
		return list;
	}

	@Override
	public List<Double> findReturnQuantity(String issueNumber, Integer itemId) {
		List<Double> list = getJpaTemplate()
				.find("SELECT e.issueReturnQuantity FROM IssueReturnDetailEntity e WHERE e.issueNumber='"
						+ issueNumber
						+ "' and e.itemEntity.itemId="
						+ itemId
						+ " and e.deletedFlag=0 ");
		return list;
	}

	@Override
	public List getIssueList(Integer index, String operationFlage) {
		/*
		 * List list=getJpaTemplate().find(
		 * "SELECT t_issue_detail.issueNumber ,t_issue_mast.issueDate ,t_issue_mast.issuedBy ,m_masters.name ,m_item.itemName ,m_item.itemCode ,m_item.itemId FROM IssueDetailMasterEntity t_issue_detail, ItemEntity m_item, IssueMasterEntity t_issue_mast, MastersEntity m_masters WHERE t_issue_detail.pendingQuantity>0 AND t_issue_mast.issueNumber=t_issue_detail.issueNumber AND t_issue_detail.issueType='Non-Returnable' AND m_masters.mastersId=t_issue_mast.departmentId AND t_issue_detail.itemEntity.itemId=m_item.itemId and t_issue_detail.deletedFlag=0 "
		 * ); return list;
		 */
		List list = null;
		if (operationFlage != null && operationFlage.equalsIgnoreCase("All")) {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			Query query = entityManager.createQuery("SELECT t_issue_detail.issueNumber ,t_issue_mast.issueDate ,t_issue_mast.issuedBy ,m_masters.name ,m_item.itemName ,m_item.itemCode ,m_item.itemId ,t_issue_detail.pendingQuantity ,t_issue_detail.issueQuantity,t_issue_mast.loanType,t_issue_mast.raisedBy FROM IssueDetailMasterEntity t_issue_detail, ItemEntity m_item, IssueMasterEntity t_issue_mast, MastersEntity m_masters WHERE t_issue_mast.issueNumber=t_issue_detail.issueNumber AND m_masters.mastersId=t_issue_mast.departmentId AND t_issue_detail.itemEntity.itemId=m_item.itemId and t_issue_detail.deletedFlag=0 and t_issue_mast.deletedFlag=0 and m_item.deletedFlag=0 and m_masters.deletedFlag=0 ORDER BY t_issue_detail.issueAutoId DESC");
			query.setFirstResult(index);
			query.setMaxResults(15);
			list = query.getResultList();
			
		} else {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			Query query = entityManager.createQuery("SELECT t_issue_detail.issueNumber ,t_issue_mast.issueDate ,t_issue_mast.issuedBy ,m_masters.name ,m_item.itemName ,m_item.itemCode ,m_item.itemId ,t_issue_detail.pendingQuantity ,t_issue_detail.issueQuantity,t_issue_mast.loanType,t_issue_mast.raisedBy FROM IssueDetailMasterEntity t_issue_detail, ItemEntity m_item, IssueMasterEntity t_issue_mast, MastersEntity m_masters WHERE t_issue_detail.pendingQuantity>0 AND t_issue_mast.issueNumber=t_issue_detail.issueNumber AND t_issue_detail.issueType='Returnable' AND t_issue_detail.pendingQuantity>0 AND m_masters.mastersId=t_issue_mast.departmentId AND t_issue_detail.itemEntity.itemId=m_item.itemId and t_issue_detail.deletedFlag=0 and t_issue_mast.deletedFlag=0 and m_item.deletedFlag=0 and m_masters.deletedFlag=0 ORDER BY t_issue_detail.issueAutoId DESC");
			query.setFirstResult(index);
			query.setMaxResults(15);
			list = query.getResultList();
		}
		return list;
	}

	@Override
	public List searchIssueList(String itemName, String itemCode,String issueNo, String operationFlag,String raisedBy,String loanType) {
		StringBuffer hql =null;
		Map<String, Object> params = new HashMap<String, Object>();
		if (operationFlag != null && operationFlag.equalsIgnoreCase("All")) {
		hql = new StringBuffer("SELECT t_issue_detail.issueNumber ,t_issue_mast.issueDate ,t_issue_mast.issuedBy ,m_masters.name ,m_item.itemName ,m_item.itemCode ,m_item.itemId ,t_issue_detail.pendingQuantity ,t_issue_detail.issueQuantity,t_issue_mast.loanType,t_issue_mast.raisedBy FROM IssueDetailMasterEntity t_issue_detail, ItemEntity m_item, IssueMasterEntity t_issue_mast, MastersEntity m_masters WHERE t_issue_mast.issueNumber=t_issue_detail.issueNumber AND m_masters.mastersId=t_issue_mast.departmentId AND t_issue_detail.itemEntity.itemId=m_item.itemId and t_issue_detail.deletedFlag=0 and t_issue_mast.deletedFlag=0 and m_item.deletedFlag=0 and m_masters.deletedFlag=0 ");
		}else{
		hql = new StringBuffer("SELECT t_issue_detail.issueNumber ,t_issue_mast.issueDate ,t_issue_mast.issuedBy ,m_masters.name ,m_item.itemName ,m_item.itemCode ,m_item.itemId ,t_issue_detail.pendingQuantity ,t_issue_detail.issueQuantity,t_issue_mast.loanType,t_issue_mast.raisedBy FROM IssueDetailMasterEntity t_issue_detail, ItemEntity m_item, IssueMasterEntity t_issue_mast, MastersEntity m_masters WHERE t_issue_detail.pendingQuantity>0 AND t_issue_mast.issueNumber=t_issue_detail.issueNumber AND t_issue_detail.issueType='Returnable' AND m_masters.mastersId=t_issue_mast.departmentId AND t_issue_detail.itemEntity.itemId=m_item.itemId and t_issue_detail.deletedFlag=0 and t_issue_mast.deletedFlag=0 and m_item.deletedFlag=0 and m_masters.deletedFlag=0 ");
		}
		if (itemName != null) {
		
		    hql.append(" and m_item.itemName like  "+"'%"+itemName+"%'");
		    //params.put("itemName", "%"+itemName.trim()+"%");
		}
		if (itemCode != null) {
			hql.append(" and m_item.itemCode like  "+"'%"+itemCode+"%'");
		}if (issueNo != null) {
			hql.append(" and t_issue_detail.issueNumber like  "+"'%"+issueNo+"%'");
		}
		
		if (raisedBy != null) {
			hql.append(" and t_issue_mast.raisedBy like  "+"'%"+raisedBy+"%'");
		}
		
		if (loanType != null) {
			
			hql.append(" and t_issue_mast.loanType like  "+"'%"+loanType.trim()+"%'");
		}
		List list = getJpaTemplate().findByNamedParams(hql.toString(), params);
		return list;
	}
	
	@Override
	public List<Double> countIssueReturnQuantity(String issueNumber, Integer itemId,String issueReturnNumber) {
		List<Double> l =null;
		if(issueReturnNumber!=null){
			l = getJpaTemplate().find("SELECT SUM(e.issueReturnQuantity) FROM IssueReturnDetailEntity e WHERE e.deletedFlag=0 and e.itemEntity.itemId = '"+ itemId+"' and e.issueNumber='"+issueNumber+"' and issueReturnNumber NOT IN("+"'"+issueReturnNumber.trim()+"'"+")" );
		}else{
		l = getJpaTemplate().find("SELECT SUM(e.issueReturnQuantity) FROM IssueReturnDetailEntity e WHERE e.deletedFlag=0 and e.itemEntity.itemId = '"+ itemId+"' and e.issueNumber='"+issueNumber+"'");
		}
		return l;
	}

	
	@Override
	public List getIssueDeatilList(String issueReturnNumber) {
		List l = getJpaTemplate().find("FROM IssueReturnDetailEntity e WHERE e.deletedFlag=0 and e.issueReturnNumber='"+issueReturnNumber+"'");
		return l;
	}

	@Override
	public List getIssueDeatilListByIssueNumber(String issueNumber) {
		List l = getJpaTemplate().find("FROM IssueReturnDetailEntity e WHERE e.deletedFlag=0 and e.issueNumber='"+issueNumber+"'");
		return l;
	}

	@Override
	public List getIssueRetunDeatilByIssueNumberAndItemId(String issueNumber,
			Integer itemId) {
		List l=null;
		try {
			l = getJpaTemplate().find("FROM IssueReturnDetailEntity e WHERE e.deletedFlag=0 and e.itemEntity.itemId = '"+ itemId+"' and e.issueNumber='"+issueNumber+"'");
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	
	@Override
	public List<IssueReturnMasterEntity> findIssueReturnPagination(Integer next) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		Query query=entityManager.createQuery("FROM IssueReturnMasterEntity e WHERE e.deletedFlag=0 ORDER BY e.issueReturnAutoId DESC");
		query.setFirstResult(next);
		query.setMaxResults(15);
		List<IssueReturnMasterEntity> list = query.getResultList();
		entityManager.close();
		return list;
	} 
}
