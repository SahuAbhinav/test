package com.advanz.erp.masters.storage;

import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.SalaryHeadEntity;

public interface IStorageSalaryHeadDAO extends IStorageDAO<SalaryHeadEntity>{
    public List<SalaryHeadEntity> load();
    public List<String> getHeadType();
    public <E> List<SalaryHeadEntity> findById(E id);
	public List<SalaryHeadEntity> findByNameAndCode(String salaryHeadName,String salaryHeadCode);
	public List<SalaryHeadEntity> search(String salaryHeadName,String salaryHeadCode,String headType,Integer id);
	public List<SalaryHeadEntity> findBasicSalaryFlag();
	public  List<SalaryHeadEntity> findSalaryHeadByCode(String code);
	public  List<SalaryHeadEntity> findSalaryHeadByName(String name);
	public  List<SalaryHeadEntity> findSalaryHeadByTypeAndSeqNo(String headType,Integer sequeNo);
	public  Integer findSalaryHeadByType(String type);
//	public List<SalaryHeadEntity> findSalaryHeadPagination(Integer next);
	
}
