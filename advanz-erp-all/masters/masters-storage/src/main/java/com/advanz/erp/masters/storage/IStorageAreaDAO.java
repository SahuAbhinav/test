package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.AreaEntity;
import com.advanz.erp.masters.entity.jpa.MastersEntity;

public interface IStorageAreaDAO extends IStorageDAO<AreaEntity>{
    public List<AreaEntity> load();
    public <E> List<AreaEntity> findById(E id);
	public List<AreaEntity> findByNameAndCode(String areaName,String areaCode);
	public List<AreaEntity> search(String areaName,String areaCode,String regionName);
	public List<AreaEntity> findByRegionId(Integer regionId);
	 public boolean isInUsed(Integer id);

}
