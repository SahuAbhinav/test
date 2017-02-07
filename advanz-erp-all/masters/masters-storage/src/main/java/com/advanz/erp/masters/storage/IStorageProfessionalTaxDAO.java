package com.advanz.erp.masters.storage;
import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.ProfessionalTaxEntity;

public interface IStorageProfessionalTaxDAO  extends IStorageDAO<ProfessionalTaxEntity>{
	    public List<ProfessionalTaxEntity> load();
	   public List<ProfessionalTaxEntity> findByNameAndCode(String slabName, String pTaxCode);
	    public List<ProfessionalTaxEntity> search(String slabName, String deductAmount);
	    public  List<ProfessionalTaxEntity> findById(Integer id);
	    public List<ProfessionalTaxEntity> findByCode(String ptaxCode);
	    public List<ProfessionalTaxEntity> findBySlabName(String slabName);
	    /*  public List<ProfessionalTaxEntity> getListByPTaxId(String pTaxId);
		public<E> List<ProfessionalTaxEntity> findByProfessionalTaxSlabName(String slabName);		
*/
	}
