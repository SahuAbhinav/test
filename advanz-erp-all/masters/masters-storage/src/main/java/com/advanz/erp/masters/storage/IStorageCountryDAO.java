package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.masters.entity.jpa.AreaEntity;
import com.advanz.erp.masters.entity.jpa.CountryEntity;
import com.advanz.erp.masters.entity.jpa.ItemCategoryEntity;
import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.CountryEntity;

public interface IStorageCountryDAO extends IStorageDAO<CountryEntity>{
    public List<CountryEntity> load();
    public <E> List<CountryEntity> findById(E id);
    public List<CountryEntity> findAllCountry();
    public List<CountryEntity> search(String countryName,String countryCode);
    public List<CountryEntity> findByNameAndCode(String countryName,String countryCode);
    public boolean isInUsed(Integer id);
}
