package com.advanz.erp.masters.storage;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.AreaEntity;
import com.advanz.erp.masters.entity.jpa.BillEntity;
import com.advanz.erp.masters.entity.jpa.DispatchMasterEntity;
import com.advanz.erp.masters.entity.jpa.MastersEntity;

public interface IStorageDispatchMasterDAO extends
		IStorageDAO<DispatchMasterEntity> {
	public List<DispatchMasterEntity> load();

	public <E> List<DispatchMasterEntity> findById(Integer dispatchAutoId);
	  public List<DispatchMasterEntity> getMaxId();
	public List<DispatchMasterEntity> findByNameAndCode(String areaName,
			String areaCode);
	public List<DispatchMasterEntity> search(String dispatchNumber, String partyName,
			Date fromDate,Date toDate,String itemName);
	public <E> List<DispatchMasterEntity> findByDispatchNumber(String dispatchNumber) ;
    public List getNewSeriesNo(String finYear);
}
