package com.advanz.erp.masters.storage;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.BlanketWeightRecordEntity;
import com.advanz.erp.masters.entity.jpa.CompanyEntity;
import com.advanz.erp.masters.entity.jpa.LoggerEntity;

public interface IStorageCompanyDAO extends IStorageDAO<CompanyEntity>{
	public List<CompanyEntity> load();
    public List<CompanyEntity> findById(Integer uid);
    public List<CompanyEntity> search(String companyName, String companyCity, String ieCode); 
    public List<CompanyEntity> findByName(String companyName);
    public List<CompanyEntity> findByCode(String companyCode);
    public Boolean getEmailFlag();
    public Date getSalaryGenaratingDate();
    public void createBlanketWeightRecord(BlanketWeightRecordEntity blanketWeightRecordEntity);
    public void createLoggerRecord(LoggerEntity loggerEntity);
    public String getWeightingFilePath();
    public String callWeightedSP() ;
}
