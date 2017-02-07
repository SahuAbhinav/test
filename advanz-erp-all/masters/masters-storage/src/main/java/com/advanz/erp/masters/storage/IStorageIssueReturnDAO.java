package com.advanz.erp.masters.storage;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.IssueReturnMasterEntity;
import com.advanz.erp.masters.entity.jpa.SalesOrderMasterEntity;

public interface IStorageIssueReturnDAO extends IStorageDAO<IssueReturnMasterEntity>{
	public List<IssueReturnMasterEntity> load();
	public List<IssueReturnMasterEntity> search(String returnNumber,Date fromDate,Date toDate,Integer departmentId);
	public List getNewSeriesNo(String finYear);
	public  List<IssueReturnMasterEntity> findById(Integer id);
	public List<IssueReturnMasterEntity> findDuplicate(String returnNumber,Date date);
	public List<Double> findReturnQuantity(String issueNumber,Integer itemId);
	public List getIssueList(Integer index,String operationFlage);
	public List searchIssueList(String itemName,String itemCode,String issueNo,String operationFlage,String raisedBy,String loanType);
	public List<Double> countIssueReturnQuantity(String issueNumber, Integer itemId,String issueReturnNumber);
	public List getIssueDeatilList(String issueReturnNumber);
	public List getIssueDeatilListByIssueNumber(String issueNumber);
	public List getIssueRetunDeatilByIssueNumberAndItemId(String issueNumber, Integer itemId) ;
	public List<IssueReturnMasterEntity> findIssueReturnPagination(Integer next);
}
