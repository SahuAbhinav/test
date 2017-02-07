package com.advanz.erp.masters.storage.test;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import junit.framework.Assert;

import org.junit.Ignore;
import org.springframework.aop.framework.Advised;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

import com.advanz.erp.common.entity.jpa.BaseEntity;
import com.advanz.erp.common.storage.IStorageDAO;
import com.advanz.erp.masters.storage.util.EntityCreationTestUtils;

@Ignore("This is a set up class for Storage DAO test")
public class AbstractStorageServiceTest extends
        AbstractTransactionalJUnit4SpringContextTests {
    @Resource(name = "entityManagerFactory")
    protected EntityManagerFactory emf;
    
    protected List<BaseEntity> testData;

    @BeforeTransaction
    public void setUp() {
 /*       Assert.assertEquals(0, this.countRowsInTable("M_COMPANY"));
        Assert.assertEquals(0, this.countRowsInTable("M_USER"));
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        // Company Data
        testData = EntityCreationTestUtils.setUpTestData(em);
        //User Data
        testData.addAll(EntityCreationTestUtils.setUpUserTestData(em));
        
        em.getTransaction().commit();*/
    }

    @AfterTransaction
    public void tearDown() {
 /*       EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        EntityCreationTestUtils.cleanUpTestData(em, testData);
        em.getTransaction().commit();

        Assert.assertEquals(0, this.countRowsInTable("M_COMPANY"));
        Assert.assertEquals(0, this.countRowsInTable("M_USER"));*/
    }

    public void flushUnderlyingJpaTemplate(IStorageDAO storageService) {
        JpaTemplate jpaTemplate = getUnderlyingJpaTemplate(storageService);
        jpaTemplate.setFlushEager(true);
        jpaTemplate.flush();
    }

    public JpaTemplate getUnderlyingJpaTemplate(IStorageDAO storageService) {
        try {
            Object targetObject = null;
            if (storageService instanceof Advised) {
                targetObject = ((Advised) storageService).getTargetSource().getTarget();
            } else {
                targetObject = storageService;
            }
            return ((JpaDaoSupport) targetObject).getJpaTemplate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
