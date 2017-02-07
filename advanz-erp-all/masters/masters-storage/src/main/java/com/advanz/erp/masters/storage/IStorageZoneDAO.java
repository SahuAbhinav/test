package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.AreaEntity;
import com.advanz.erp.masters.entity.jpa.CountryEntity;
import com.advanz.erp.masters.entity.jpa.RegionEntity;
import com.advanz.erp.masters.entity.jpa.ZoneEntity;

public interface IStorageZoneDAO extends IStorageDAO<ZoneEntity>{
    public List<ZoneEntity> load();
    public List<ZoneEntity> findById(Integer id);
    public List<ZoneEntity> findAllZones();
    public List<ZoneEntity> search(String zoneName,String zoneCode,String countryName);
    public List<ZoneEntity> findByNameAndCode(String areaName,String areaCode);
    public List<ZoneEntity> findZoneByCountryId(Integer countryId);
    public boolean isInUsed(Integer id);
   
    
}
