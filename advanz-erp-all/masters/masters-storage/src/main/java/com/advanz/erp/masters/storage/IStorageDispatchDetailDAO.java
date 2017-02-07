package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.AreaEntity;
import com.advanz.erp.masters.entity.jpa.DispatchDetailEntity;
import com.advanz.erp.masters.entity.jpa.DispatchMasterEntity;
import com.advanz.erp.masters.entity.jpa.MastersEntity;

public interface IStorageDispatchDetailDAO extends
		IStorageDAO<DispatchDetailEntity> {
	public List<DispatchDetailEntity> load();

	public <E> List<DispatchDetailEntity> findById(Integer sno);

	public List<DispatchMasterEntity> getMaxId();

	public List<DispatchDetailEntity> findByNameAndCode(String areaName,
			String areaCode);

	public List<DispatchDetailEntity> search(String areaName, String areaCode,
			String regionName);

	
	public List<DispatchDetailEntity> findByDispatchId(String dispatchNumber);
	
	public List<DispatchDetailEntity> findByItemId(Integer itemId);
}
