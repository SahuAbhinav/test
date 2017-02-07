package com.advanz.erp.masters.storage.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.advanz.erp.masters.entity.jpa.BranchEntity;
import com.advanz.erp.masters.storage.IStorageBranchDAO;
import com.advanz.erp.masters.storage.util.EntityCreationTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test/config/advanz-erp-test-masters-storage-context.xml"})
@Transactional
public class StorageBranchDAOTest extends AbstractStorageServiceTest {
    @Autowired
    private IStorageBranchDAO storageBranchDAO;

    @Test
    public void testCreateBranch() {
        BranchEntity branch1 = EntityCreationTestUtils.createBranch("branch1");
        BranchEntity branch2 = EntityCreationTestUtils.createBranch("branch2");
        BranchEntity branch3 = EntityCreationTestUtils.createBranch("branch3");
       /* branch.setCreationDate(null);
        branch.setModifiedDate(null);
*/
//        Assert.assertNull(branch1.getBranchId());
//        storageBranchDAO.create(branch1);
//        storageBranchDAO.create(branch2);
//        storageBranchDAO.create(branch3);
//       
//        flushUnderlyingJpaTemplate(storageBranchDAO);
//        Assert.assertNotNull(branch1.getBranchId());
//        Assert.assertNotNull(branch1.getCreationDate());
//        Assert.assertNotNull(branch1.getModifiedDate());
    }

   
}
