package com.advanz.erp.masters.storage;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.IssueDetailMasterEntity;
import com.advanz.erp.masters.entity.jpa.IssueMasterEntity;


public interface IStorageIssueDAO extends IStorageDAO<IssueMasterEntity>{
    public List<IssueMasterEntity> load();
    public <E> List<IssueMasterEntity> findById(E id);
    public List<IssueMasterEntity> search(String issueNumber,Date fromDate,Date toDate,String issueType,String itemName,String raisedBy);
    public List getNewSeriesNo(String finYear);
    public List<IssueDetailMasterEntity> findByItemId(Integer itemId);
    
	public<E> List<IssueMasterEntity> findByIssuesNumber(E id);
	public List<IssueMasterEntity> findIssueNumber(Integer issueAutoId);
	public List<IssueMasterEntity> findMaterialIssuePagination(Integer next);

	
	public IssueDetailMasterEntity updateIssueDetail(IssueDetailMasterEntity entity);
	public List<IssueDetailMasterEntity> findByIssueNoAndItemId(String issueNumber, Integer itemId);
	public List findIssueDateByIssueNo(String issueNumber);
}
