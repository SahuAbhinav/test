package com.advanz.erp.masters.storage;

import java.util.Date;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.MastersEntity;

public interface IStorageMastersDAO extends IStorageDAO<MastersEntity>{
    public List<MastersEntity> load();
    public List<String> getFormName();
    public List<MastersEntity> findById(Integer id,Integer formId); 
    public List<MastersEntity> findById(Integer id);
    public List<MastersEntity> findByFormId(Integer formId); 
    public List<MastersEntity> findByFormName(String formName); 
	public List<MastersEntity> findByNameAndCode(String name,String code, Integer formId);
	public List<MastersEntity> search(String formName,String name,String code);
	public List<MastersEntity> findSubdepartment(Integer id,Integer formId);
	public boolean isInUsed(Integer id);
	public List<MastersEntity> findByFormIdForMelterLogBook(Integer formId);
	public List findByDate(Date date);
	 
	 public List findByMasterNameById(Integer masterId); 
}
