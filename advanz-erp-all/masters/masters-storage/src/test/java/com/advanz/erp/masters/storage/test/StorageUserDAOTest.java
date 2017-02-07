/*package com.advanz.erp.masters.storage.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.erp.common.entity.jpa.BaseEntity;
import com.advanz.erp.masters.entity.jpa.UserEntity;
import com.advanz.erp.masters.storage.IStorageUserDAO;
import com.advanz.erp.masters.storage.util.EntityCreationTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test/config/advanz-erp-test-masters-storage-context.xml"})
@Transactional
public class StorageUserDAOTest  extends AbstractStorageServiceTest  {
	
	   @Autowired
	    private IStorageUserDAO storageUserDAO;
	   
	   @BeforeTransaction
	    public void setUp() {
		    testData = new ArrayList<BaseEntity>();
	        Assert.assertEquals(0, this.countRowsInTable("M_USER"));
	    }

	 
	   @Test
	    public void testCreateUser() {
		   //select All
	        EntityManager em = emf.createEntityManager();
	        em.getTransaction().begin();
	        //User Data
	        testData.addAll(EntityCreationTestUtils.setUpUserTestData(em));
	        
	        em.getTransaction().commit();
	        
	        List<UserEntity> cl=storageUserDAO.load();
	        
	        Assert.assertEquals("Data not have 1 Row",1, cl.size());
	        System.out.println("****"+cl);
	        //flushUnderlyingJpaTemplate(storageUserDAO);
	        // TODO write asserts
	    }

	   @AfterTransaction
	    public void tearDown() {
	        EntityManager em = emf.createEntityManager();
	        em.getTransaction().begin();
	        cleanUpTestData(em, testData);
	        em.getTransaction().commit();

	        Assert.assertEquals(0, this.countRowsInTable("M_USER"));
	    }
	   
	   public static void cleanUpTestData(EntityManager em, List<BaseEntity> testData) {
	        for (BaseEntity data : testData) {
	            BaseEntity entity = em.find(data.getClass(), ((UserEntity)data).getUserId());
	            if (entity != null) {
	                em.remove(entity);
	            }
	        }
	    }
}
*/