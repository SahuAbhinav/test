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

import com.advanz.erp.masters.entity.jpa.ItemEntity;
import com.advanz.erp.masters.entity.jpa.ItemGroupEntity;
import com.advanz.erp.masters.storage.IStorageItemGroupDAO;
import com.advanz.erp.masters.storage.util.EntityCreationTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test/config/advanz-erp-test-masters-storage-context.xml"})
@Transactional
public class StorageItemGroupDAOTest extends AbstractStorageServiceTest {
    @Autowired
    private IStorageItemGroupDAO storageItemGroupDAO;

  @BeforeTransaction
	public void setUp() {		

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		testData = EntityCreationTestUtils.setUpTestData(em,ItemEntity.class);
		em.getTransaction().commit();

	}
    
    
    @Test
    public void testCreateItemGroup() {
    	System.out.println("StorageItemGroupDAOTest----test");
    	EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
        ItemGroupEntity itemGroup = EntityCreationTestUtils.createItemGroup("itemGroup3");
        itemGroup.setCreationDate(new Date());
        itemGroup.setModifiedDate(new Date());
        

        Assert.assertNull(itemGroup.getUid());
        
        storageItemGroupDAO.create(itemGroup);
        em.getTransaction().commit();
        //select All
        List<ItemGroupEntity > cl=storageItemGroupDAO.load();
     
        System.out.println(cl +" size "+ cl.size());
        em.getTransaction().commit();
        
//        List<ItemGroupEntity > cl1=storageItemGroupDAO.findById(1);
//        Assert.assertEquals(1, cl1.size());
       // flushUnderlyingJpaTemplate(storageItemGroupDAO);
        Assert.assertNotNull(itemGroup.getUid());
        Assert.assertNotNull(itemGroup.getCreationDate());
        Assert.assertNotNull(itemGroup.getModifiedDate());
    }

   
}
