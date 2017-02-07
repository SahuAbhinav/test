package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;

import com.advanz.erp.masters.model.msg.LeaveTypeMastInputMessage;
import com.advanz.erp.masters.model.msg.LeaveTypeMastOutputMessage;

public interface ILeaveTypeMastService extends IAdvanzErpBaseSerivce{

	
	public LeaveTypeMastOutputMessage createLeaveTypeMast(LeaveTypeMastInputMessage leaveTypeMastInputMessage);
	
	public LeaveTypeMastOutputMessage updateLeaveTypeMast(LeaveTypeMastInputMessage leaveTypeMastInputMessage);
	
	public LeaveTypeMastOutputMessage deleteLeaveTypeMast(LeaveTypeMastInputMessage leaveTypeMastInputMessage);
	
	public LeaveTypeMastOutputMessage findLeaveTypeMastById(LeaveTypeMastInputMessage leaveTypeMastInputMessage);
	
	public LeaveTypeMastOutputMessage findAllLeaveTypeMasts();
	
	public LeaveTypeMastOutputMessage search(LeaveTypeMastInputMessage leaveTypeMastInputMessage);
	
	public LeaveTypeMastOutputMessage checkBeforeRemove(LeaveTypeMastInputMessage leaveTypeMastInputMessage);
	
	public LeaveTypeMastOutputMessage getLeaveType();
	public LeaveTypeMastOutputMessage findByLeaveName(LeaveTypeMastInputMessage leaveTypeMastInputMessage);
}
