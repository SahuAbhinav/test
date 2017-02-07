package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.ItemEntity;
import com.advanz.erp.masters.entity.jpa.RegionEntity;

public interface IStorageRegionDAO  extends IStorageDAO<RegionEntity>{
    public List<RegionEntity> load();
    public <E> List<RegionEntity> findById(E id);
    public List<RegionEntity> search(String regionName,String regionCode,String stateName);
    public List<RegionEntity> findByNameAndCode(String regionName,String regionCode);
    public List<RegionEntity> findByStateId(Integer stateId);
    public List<RegionEntity> findReginByStateId(Integer stateId);
    public boolean isInUsed(Integer id);

}
