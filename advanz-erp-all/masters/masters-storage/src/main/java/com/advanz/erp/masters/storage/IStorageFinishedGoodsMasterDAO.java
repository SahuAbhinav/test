package com.advanz.erp.masters.storage;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.FinishedGoodsDetailEntity;
import com.advanz.erp.masters.entity.jpa.FinishedGoodsMasterEntity;
import com.advanz.erp.masters.entity.jpa.SalesOrderMasterEntity;

public interface IStorageFinishedGoodsMasterDAO extends
		IStorageDAO<FinishedGoodsMasterEntity> {
	public List<FinishedGoodsMasterEntity> load();

	public <E> List<FinishedGoodsMasterEntity> findById(E id);

	public List<FinishedGoodsMasterEntity> search(String finishedGoodsNumber,
			Date fromDate, Date toDate, String itemName);

	public List getNewSeriesNo(String finYear);

	public List<FinishedGoodsDetailEntity> findByItemId(Integer itemId);

	public <E> List<FinishedGoodsMasterEntity> findByFinishGoodNumber(E id,
			Date date);

	public List getFinishGoodInfoToEmail(String date);

	public List<FinishedGoodsMasterEntity> FindFinishedGoodPagination(
			Integer next);

	public Integer findLastFinishedGoodDetail(Integer finishGoodId,
			Integer itemId);

}
