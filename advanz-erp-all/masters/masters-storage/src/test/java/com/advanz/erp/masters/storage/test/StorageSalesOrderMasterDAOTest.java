package com.advanz.erp.masters.storage.test;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.erp.masters.entity.jpa.SalesOrderMasterEntity;
import com.advanz.erp.masters.storage.IStorageSalesOrderMasterDAO;
import com.advanz.erp.masters.storage.util.EntityCreationTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test/config/advanz-erp-test-masters-storage-context.xml"})
@Transactional
public class StorageSalesOrderMasterDAOTest extends AbstractStorageServiceTest {
    @Autowired
    private IStorageSalesOrderMasterDAO storageSalesOrderMasterDAO;

    
    @BeforeTransaction
	public void setUp() {		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
	//	testData = EntityCreationTestUtils.setUpTestData(em);
		testData = EntityCreationTestUtils.setUpSalesOrderMasterTestData(em);
		em.getTransaction().commit();
	}
    
    
    
    @Test
    public void testCreateBatch() {
    	
        SalesOrderMasterEntity som = EntityCreationTestUtils.createSalesOrderMaster("SO-1213-3");
        som.setCreationDate(new Date());
        som.setModifiedDate(new Date());
       // batch.setItemId(1);

        
        storageSalesOrderMasterDAO.create(som);
        
     
        //select All
        List<SalesOrderMasterEntity > list=storageSalesOrderMasterDAO.load();
     
        Assert.assertEquals("Data not have 3 Row",3, list.size());
        System.out.println("****"+list);
        
   //     List<SalesOrderMasterEntity > cl1=storageSalesOrderMasterDAO.findById(1);
  //      Assert.assertEquals("data not found for id 1",1, cl1.size());
        
        flushUnderlyingJpaTemplate(storageSalesOrderMasterDAO);
   //     Assert.assertNotNull(batch.getUid());
        Assert.assertNotNull(som.getCreationDate());
        Assert.assertNotNull(som.getModifiedDate());
    }

   
}
