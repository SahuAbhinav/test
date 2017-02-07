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

import com.advanz.erp.masters.entity.jpa.BatchEntity;
import com.advanz.erp.masters.storage.IStorageBatchDAO;
import com.advanz.erp.masters.storage.util.EntityCreationTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test/config/advanz-erp-test-masters-storage-context.xml"})
@Transactional
public class StorageBatchDAOTest extends AbstractStorageServiceTest {
    @Autowired
    private IStorageBatchDAO storageBatchDAO;

    
//    @BeforeTransaction
//	public void setUp() {		
//	
//
//		// Assert.assertEquals(0, this.countRowsInTable("M_COMPANY"));
//		// Assert.assertEquals(0, this.countRowsInTable("M_Batch_GROUP"));
//
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//	//	testData = EntityCreationTestUtils.setUpTestData(em);
//		testData = EntityCreationTestUtils.setUpTestData(em,BatchEntity.class);
//		em.getTransaction().commit();
//
//	}
//    
//    
    
    @Test
    public void testCreateBatch() {
    	
        BatchEntity batch = EntityCreationTestUtils.createBatch("batch_test");
        batch.setCreationDate(new Date());
        batch.setModifiedDate(new Date());
       // batch.setItemId(1);

        
        storageBatchDAO.create(batch);
        
     
        //select All
        List<BatchEntity > cl=storageBatchDAO.load();
     
        Assert.assertEquals("Data not have 3 Row",3, cl.size());
        System.out.println("****"+cl);
        
   //     List<BatchEntity > cl1=storageBatchDAO.findById(1);
  //      Assert.assertEquals("data not found for id 1",1, cl1.size());
        
        flushUnderlyingJpaTemplate(storageBatchDAO);
   //     Assert.assertNotNull(batch.getUid());
        Assert.assertNotNull(batch.getCreationDate());
        Assert.assertNotNull(batch.getModifiedDate());
    }

   
}
