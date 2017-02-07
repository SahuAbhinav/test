package com.advanz.erp.masters.service.business;

import com.advanz.erp.common.service.business.IAdvanzErpBaseSerivce;
import com.advanz.erp.masters.model.msg.StateInputMessage;
import com.advanz.erp.masters.model.msg.StateOutputMessage;

public interface IStateService extends IAdvanzErpBaseSerivce{

	
	public StateOutputMessage createState(StateInputMessage stateInputMessage);
	
	public StateOutputMessage updateState(StateInputMessage stateInputMessage);
	
	public StateOutputMessage deleteState(StateInputMessage stateInputMessage);
	
	public StateOutputMessage findStateById(StateInputMessage stateInputMessage);
	
	public StateOutputMessage findAllStates();
	
	public StateOutputMessage findState(StateInputMessage stateInputMessage);

	StateOutputMessage findStatesByZoneId(StateInputMessage stateInputMessage);
}
