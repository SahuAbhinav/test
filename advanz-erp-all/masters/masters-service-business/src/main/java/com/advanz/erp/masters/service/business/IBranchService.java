package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.BranchInputMessage;
import com.advanz.erp.masters.model.msg.BranchOutMessage;

public interface IBranchService extends IAdvanzErpBaseSerivce{

	
	public BranchOutMessage createBranch(BranchInputMessage branchInputMessage);
	
	public BranchOutMessage updateBranch(BranchInputMessage branchInputMessage);
	
	public BranchOutMessage deleteBranch(BranchInputMessage branchInputMessage);
	
	public BranchOutMessage findBranchById(BranchInputMessage branchInputMessage);
	
	public BranchOutMessage findAllBranches();

	public BranchOutMessage findBranch(BranchInputMessage branchInputMessage);
	
	public BranchOutMessage checkBeforeRemove(BranchInputMessage branchInputMessage);
	public BranchOutMessage preloaded();
}
