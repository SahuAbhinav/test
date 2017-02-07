package com.advanz.erp.masters.storage.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.advanz.erp.common.entity.jpa.BaseEntity;
import com.advanz.erp.masters.entity.jpa.BatchEntity;
import com.advanz.erp.masters.entity.jpa.BranchEntity;
import com.advanz.erp.masters.entity.jpa.CompanyEntity;
import com.advanz.erp.masters.entity.jpa.ItemCategoryEntity;
import com.advanz.erp.masters.entity.jpa.ItemEntity;
import com.advanz.erp.masters.entity.jpa.ItemGroupEntity;
import com.advanz.erp.masters.entity.jpa.MastersEntity;
import com.advanz.erp.masters.entity.jpa.SalesOrderMasterEntity;

import com.advanz.erp.masters.entity.jpa.UserRoleAndRightsEntity;

public class EntityCreationTestUtils {
    public static CompanyEntity createCompany(String name) {
    	CompanyEntity company = new CompanyEntity();
    	company.setCompanyName(name);
        return company;
    }

    public static BranchEntity createBranch(String name) {
    	BranchEntity branch = new BranchEntity();
    	branch.setBranch(name);
    	branch.setMSMECode(name);
        return branch;
    }
    
    
    public static ItemGroupEntity createItemGroup(String name) {
    	ItemGroupEntity itemGroup = new ItemGroupEntity();
    	itemGroup.setItemGroupName(name);
        return itemGroup;
    }
    public static ItemCategoryEntity createItemCategory(String name) {
    	ItemCategoryEntity itemCategory = new ItemCategoryEntity();
    	itemCategory.setItemCategoryName(name);
        return itemCategory;
    }
    public static ItemEntity createItem(String name) {
    	ItemEntity item = new ItemEntity();
    	item.setItemName(name);
        return item;
    }
    public static BatchEntity createBatch(String name) {
    	BatchEntity batch = new BatchEntity();
    	batch.setBatchNo(name);
        return batch;
    }
    public static MastersEntity createMaster(Integer id,Integer formId,String formName,String name,String code) {
    	MastersEntity master=new MastersEntity();
    	master.setId(id);
    	master.setFormId(formId);
    	master.setFormName(formName);
    	master.setName(name);
    	master.setCode(code);
        return master;
    }
    public static SalesOrderMasterEntity createSalesOrderMaster(String salesOrderNumber) {
    	SalesOrderMasterEntity so=new SalesOrderMasterEntity();
    	so.setSalesOrderNumber(salesOrderNumber);
    	so.setSalesOrderDate(new Date());    	
        return so;
    }
    public static void cleanUpTestData(EntityManager em, List<BaseEntity> testData) {
        /*for (BaseEntity data : testData) {
            BaseEntity entity = em.find(data.getClass(), data.getUid());
            if (entity != null) {
                em.remove(entity);
            }
        }*/
    }
    
    public static List<BaseEntity> setUpTestData(EntityManager em) {
        CompanyEntity company1 = createCompany("company1");
        CompanyEntity company2 = createCompany("company2");
        em.persist(company1);
        em.persist(company2);

        List<BaseEntity> testData = new ArrayList<BaseEntity>();
        testData.add(company1);
        testData.add(company2);
        return testData;
    }
    
    public static List<BaseEntity> setUpTestData(EntityManager em,Class obj) {
        ItemEntity company1 = createItem("company1");
        ItemEntity company2 = createItem("company2");
        em.persist(company1);
        em.persist(company2);

        List<BaseEntity> testData = new ArrayList<BaseEntity>();
        testData.add(company1);
        testData.add(company2);
        return testData;
    }
    
    public static List<BaseEntity> setUpItemCategoryTestData(EntityManager em) {
    	ItemCategoryEntity icat1 = createItemCategory("CAT1");
        ItemCategoryEntity icat2 = createItemCategory("CAT2");
        em.persist(icat1);
        em.persist(icat2);

        List<BaseEntity> testData = new ArrayList<BaseEntity>();
        testData.add(icat1);
        testData.add(icat2);
        return testData;
    }
     public static List<BaseEntity> setUpMastersTestData(EntityManager em) {
    	 MastersEntity master1 = createMaster(1, 1, "CAST", "Jain", "JAIN");
         MastersEntity master2 = createMaster(1, 2, "LANG", "Hindi", "HI");
         em.persist(master1);
         em.persist(master2);

         List<BaseEntity> testData = new ArrayList<BaseEntity>();
         testData.add(master1);
         testData.add(master2);
         return testData;
    }
    public static List<BaseEntity> setUpSalesOrderMasterTestData(EntityManager em) {
        SalesOrderMasterEntity entity1 = createSalesOrderMaster("SO-1213-1");
        SalesOrderMasterEntity entity2 = createSalesOrderMaster("SO-1213-2");
        em.persist(entity1);
        em.persist(entity2);

        List<BaseEntity> testData = new ArrayList<BaseEntity>();
        testData.add(entity1);
        testData.add(entity2);
        return testData;
    }
}
