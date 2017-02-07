package com.advanz.erp.masters.storage.jpa;

import java.util.List;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.ProfessionalTaxDeductTypeEntity;
import com.advanz.erp.masters.storage.IStorageProfessionalTaxDeductTypeDAO;
import com.advanz.erp.masters.storage.jpa.StorageProfessionalTaxDeductTypeDAO;


import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.entity.jpa.ProfessionalTaxDeductTypeEntity;

public class StorageProfessionalTaxDeductTypeDAO extends JpaDaoSupport implements IStorageProfessionalTaxDeductTypeDAO {
	 
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(ProfessionalTaxDeductTypeEntity entity) {
		
        if (entity == null) {
            throw new IllegalArgumentException("Cannot create a null entity");
        }
        getJpaTemplate().persist(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public ProfessionalTaxDeductTypeEntity read(Integer uid) {
        if (uid == null) {
            throw new IllegalArgumentException("uid must not be null");
        }
        return getJpaTemplate().find(ProfessionalTaxDeductTypeEntity.class, uid);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ProfessionalTaxDeductTypeEntity update(ProfessionalTaxDeductTypeEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Cannot update a null entity");
        }
        return getJpaTemplate().merge(entity);
	}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void delete(ProfessionalTaxDeductTypeEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
    @Transactional(readOnly = true)
	public List<ProfessionalTaxDeductTypeEntity> load() {
		
		List<ProfessionalTaxDeductTypeEntity> list= getJpaTemplate().find("FROM ProfessionalTaxDeductTypeEntity e ORDER BY e.ptaxDeductType DESC");
	//  System.out.println("professionalTax>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+list);
	  return list;
	}
	
	
}


