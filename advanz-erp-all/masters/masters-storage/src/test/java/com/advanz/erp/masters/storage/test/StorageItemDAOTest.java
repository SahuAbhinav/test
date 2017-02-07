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
import com.advanz.erp.masters.storage.IStorageItemDAO;
import com.advanz.erp.masters.storage.util.EntityCreationTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test/config/advanz-erp-test-masters-storage-context.xml"})
@Transactional
public class StorageItemDAOTest extends AbstractStorageServiceTest {
    @Autowired
    private IStorageItemDAO storageItemDAO;

    
    @BeforeTransaction
	public void setUp() {		

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		testData = EntityCreationTestUtils.setUpTestData(em,ItemEntity.class);
		em.getTransaction().commit();

	}
    
    
    
    @Test
    public void testCreateItem() {
    	
        ItemEntity item = EntityCreationTestUtils.createItem("item_test");
        item.setCreationDate(new Date());
        item.setModifiedDate(new Date());
        

        Assert.assertNull(item.getItemId());
        
        storageItemDAO.create(item);
        
        Assert.assertNotNull(item.getItemId());

      
        //select All
        List<ItemEntity > cl=storageItemDAO.load();     
    
        System.out.println("****"+cl);
        
        List<ItemEntity > cl1=storageItemDAO.findById(1);
        Assert.assertEquals(1, cl1.size());
        
        ItemEntity i1=storageItemDAO.read(1);
        Assert.assertNotNull(i1);
        
        flushUnderlyingJpaTemplate(storageItemDAO);
        Assert.assertNotNull(item.getItemId());
        Assert.assertNotNull(item.getCreationDate());
        Assert.assertNotNull(item.getModifiedDate());
    }

   
}
