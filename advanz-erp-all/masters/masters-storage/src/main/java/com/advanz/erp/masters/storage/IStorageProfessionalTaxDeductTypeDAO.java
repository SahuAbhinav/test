package com.advanz.erp.masters.storage;

	import java.util.List;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.ProfessionalTaxDeductTypeEntity;

	public interface IStorageProfessionalTaxDeductTypeDAO extends IStorageDAO<ProfessionalTaxDeductTypeEntity> {
		   public List<ProfessionalTaxDeductTypeEntity> load();

	}

	