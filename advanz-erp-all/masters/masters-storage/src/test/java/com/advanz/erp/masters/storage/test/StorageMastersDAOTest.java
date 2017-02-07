package com.advanz.erp.masters.storage.test;

import java.sql.Time;
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

import com.advanz.erp.masters.entity.jpa.MastersEntity;
import com.advanz.erp.masters.storage.IStorageMastersDAO;
import com.advanz.erp.masters.storage.util.EntityCreationTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test/config/advanz-erp-test-masters-storage-context.xml"})
@Transactional
public class StorageMastersDAOTest extends AbstractStorageServiceTest {
    @Autowired
    private IStorageMastersDAO storageMastersDAO;

    
    @BeforeTransaction
	public void setUp() {
	

		// Assert.assertEquals(0, this.countRowsInTable("M_Master_GROUP"));

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
	//	testData = EntityCreationTestUtils.setUpTestData(em);
		testData = EntityCreationTestUtils.setUpMastersTestData(em);
		em.getTransaction().commit();
	}
    
    
    
    @Test
    public void testCreateMaster() {
    	
        MastersEntity master = EntityCreationTestUtils.createMaster(1, 3, "DEPT", "Account", "ACC");
        master.setShiftFromTime(new Time(new Date().getTime()));
        master.setShiftToTime(new Time(new Date().getTime()));
        storageMastersDAO.create(master);
        
     
        //select All
        List<MastersEntity> cl=storageMastersDAO.load();
     
        Assert.assertEquals("Data not have 3 Row",3, cl.size());
        System.out.println("****"+cl);
        
    
        flushUnderlyingJpaTemplate(storageMastersDAO);
   //     Assert.assertNotNull(master.getUid());
        Assert.assertNotNull(master.getCreationDate());
        Assert.assertNotNull(master.getModifiedDate());
    }

   
}
