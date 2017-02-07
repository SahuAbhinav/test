package com.advanz.erp.masters.storage.test;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.erp.masters.entity.jpa.CompanyEntity;
import com.advanz.erp.masters.storage.IStorageCompanyDAO;
import com.advanz.erp.masters.storage.util.EntityCreationTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test/config/advanz-erp-test-masters-storage-context.xml"})
@Transactional
public class StorageCompanyDAOTest extends AbstractStorageServiceTest {
    @Autowired
    private IStorageCompanyDAO storageCompanyDAO;

    @Test
    public void testCreateCompany() {
        CompanyEntity company = EntityCreationTestUtils.createCompany("company3");
        company.setCreationDate(new Date());
        company.setModifiedDate(new Date());       

        Assert.assertNull(company.getCompanyId());
        storageCompanyDAO.create(company);
        List<CompanyEntity > cl=storageCompanyDAO.load();
        System.out.println(cl);
        flushUnderlyingJpaTemplate(storageCompanyDAO);
        Assert.assertNotNull(company.getCompanyId());
        Assert.assertNotNull(company.getCreationDate());
        Assert.assertNotNull(company.getModifiedDate());
    }

   
}
