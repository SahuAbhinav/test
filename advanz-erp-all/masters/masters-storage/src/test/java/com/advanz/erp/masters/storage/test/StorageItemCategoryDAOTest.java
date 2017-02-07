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

import com.advanz.erp.masters.entity.jpa.ItemCategoryEntity;
import com.advanz.erp.masters.storage.IStorageItemCategoryDAO;
import com.advanz.erp.masters.storage.util.EntityCreationTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test/config/advanz-erp-test-masters-storage-context.xml"})
@Transactional
public class StorageItemCategoryDAOTest extends AbstractStorageServiceTest {
    @Autowired
    private IStorageItemCategoryDAO storageItemCategoryDAO;

//	@BeforeTransaction
//	public void setUp() {		
//		System.out.println("StorageItemCategoryTest----SetUp");
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//		testData = EntityCreationTestUtils.setUpItemCategoryTestData(em);
//		em.getTransaction().commit();
//
//	}
    
    @Test
    public void testCreateItemCategory() {
    	
        ItemCategoryEntity itemCategory = EntityCreationTestUtils.createItemCategory("itemCategory_test");
        itemCategory.setCreationDate(new Date());
        itemCategory.setModifiedDate(new Date());
        

        Assert.assertNull(itemCategory.getItemCategoryId());
        
        storageItemCategoryDAO.create(itemCategory);
        
        Assert.assertNotNull(itemCategory.getItemCategoryId());
      
      
        //select All
        List<ItemCategoryEntity > cl=storageItemCategoryDAO.load();
     
        System.out.println(cl);
        
        List<ItemCategoryEntity > cl1=storageItemCategoryDAO.findById(1);
        Assert.assertEquals(1, cl1.size());
        
        flushUnderlyingJpaTemplate(storageItemCategoryDAO);
        Assert.assertNotNull(itemCategory.getItemCategoryId());
        Assert.assertNotNull(itemCategory.getCreationDate());
        Assert.assertNotNull(itemCategory.getModifiedDate());
    }

   
}
