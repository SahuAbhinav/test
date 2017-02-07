package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.AreaEntity;
import com.advanz.erp.masters.entity.jpa.CityEntity;

public interface IStorageCityDAO extends IStorageDAO<CityEntity>{
	 public List<CityEntity> load();
	    public <E> List<CityEntity> findById(E id);
		public List<CityEntity> findByNameAndCode(String cityName,String cityCode);
		public List<CityEntity> search(String cityName,String cityCode,String areaName);
		 public boolean isInUsed(Integer id);
		
}
